import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addProduct, getProduct, getProductList, updateProduct} from "../api/product.js";
import {getItemList} from "../api/item.js";
import {addStock, getStock} from "../api/stock.js";

sw.init()
searchProduct()
//품목 검색
const searchButton = document.getElementById("productSearchButton");
searchButton.addEventListener("click", async function () {
    searchProduct();
})
async function searchProduct(){
    const productSearchForm = document.getElementById("productSearchForm");
    const formData = new FormData(productSearchForm);
    const result = await getProductList(formData);
    const data = result.data;
    data.forEach((object,index) => {
        data[index].name = `<a href="#" onclick="return false" data-id="${object.id}" class="detail">${object.name}</a>`
    })
    hot.loadData(data);
}

let selectedRowId = null; //체크된 열 아이디
const editButton = document.getElementById("editButton");
editButton.addEventListener("click", async function(){
    if(selectedRowId == null) {
        alert("품목을 선택해 주세요.")
        return;
    }
    await setDetailDataToEditModal(selectedRowId);
    editModal.show();
})
async function setDetailDataToEditModal(id){
    const result = await getProduct(id);
    const data = result.data;
    sw.matchData({categoryName: data.category.name, unitName: data.unit.name})
    sw.matchData(data)
    const formData = new FormData();
    formData.append("product.id", id);
    const stocks = await getStock(id)
    const supplierTable = document.querySelector("#supplierTable tbody")
    supplierTable.innerHTML = ""
    stocks.data.forEach((item,index) => {
        supplierTable.innerHTML += `
            <tr>
                <td>
                    ${index+1}
                </td>
                <td>${item.history}</td>
                <td>${item.quantity}</td>
                <td>${item.createDate}</td>
            </tr>
        `
    })
}
//name 이벤트 리스너 추가
function addNameEventListener(){
    [...container.querySelectorAll("a.detail")].forEach(
        el=>el.addEventListener("click",async function (){
            await setDetailDataToEditModal(el.dataset.id);
            editModal.show();
        })
    )
}
// 테이블 초기화
const container = document.getElementById('example')
const myCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedRowId = instance.getDataAtCell(row,1);
    } else {
        selectedRowId = null
    }
})
const tableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격', '단위','판매단가', "재고"],
    columns : [
        {renderer: myCheckboxRenderer},
        {data:"id"},
        {data:"category.name"},
        {data:"name", renderer:"html"},
        {data:"standard"},
        {data:"unit.name"},
        {data:"sellPrice"},
        {data:"stock"},
    ],
    colWidths : scaleArrayToSum(Array(7),1130),
    height:"50vh",
}
const hot = handsontable(container, tableOptions);
hot.addHook("afterRender", function () {
    addNameEventListener();
})

//모달
const registerModalEl = document.getElementById("register-modal")
const editModalEl = document.getElementById("edit-modal")
const registerModal = new bootstrap.Modal(registerModalEl);
const editModal = new bootstrap.Modal(editModalEl);


//품목 추가
const addSubmitButton = document.getElementById("addSubmitButton");
addSubmitButton.addEventListener("click", async function(){
    const addForm = document.getElementById("addForm");
    const formData = new FormData(addForm);
    const result = await addProduct(formData);
    alert(result.message);
    if (result.status === "OK") {
        await searchProduct();
        registerModal.hide()
        addForm.reset();
    }
})

//입고
const editInButton = document.getElementById("editInButton");
editInButton.addEventListener("click", async function(){
    const editForm = document.getElementById("editForm");
    const formData = new FormData(editForm);
    const quantity = formData.get("quantity");
    const history = formData.get("history");
    if(!history) {
        alert("사유를 입력해 주세요.")
        return;
    }
    if(quantity == 0) {
        alert("입고할 수량을 입력해 주세요.")
        return;
    }
    if(quantity < 0){formData.set("quantity", quantity * -1)}
    if(confirm("입고 하시겠습니까?")){
        const result = await addStock(formData);
        alert(result.message);
        if(result.status === "OK"){
            await searchProduct()
            editModal.hide()
        }
    }
})
//출고
const editOutButton = document.getElementById("editOutButton");
editOutButton.addEventListener("click", async function(){
    const editForm = document.getElementById("editForm");
    const formData = new FormData(editForm);
    const quantity = formData.get("quantity");
    const history = formData.get("history");
    if(!history) {
        alert("사유를 입력해 주세요.")
        return;
    }
    if(quantity == 0) {
        alert("출고할 수량을 입력해 주세요.")
        return;
    }
    if(quantity > 0){formData.set("quantity", quantity * -1)}
    if(confirm("출고 하시겠습니까?")){
        const result = await addStock(formData);
        alert(result.message);
        if(result.status === "OK"){
            await searchProduct()
            editModal.hide()
        }
    }
})


const calcQuantity = (num) => {
    const editQuantity = document.getElementById("editQuantity")
    editQuantity.value = Number(editQuantity.value) + num;
    if(editQuantity.value < 0) {
        editQuantity.value = 0;
    }
}
window.calcQuantity = calcQuantity;

const exportPlugin = hot.getPlugin('exportFile');
const exportButton = document.getElementById("exportButton");
exportButton.addEventListener("click", function(){
    exportPlugin.downloadFile('csv', {
        bom: false,
        columnDelimiter: ',',
        columnHeaders: true,
        exportHiddenColumns: true,
        fileExtension: 'csv',
        filename: '품목_[YYYY]-[MM]-[DD]',
        mimeType: 'text/csv',
        rowDelimiter: '\r\n',
        rowHeaders: false,
        range:[0,1]
    });
})
