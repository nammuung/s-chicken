import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addProduct, getProduct, getProductList, updateProduct} from "../api/product.js";

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
    console.log(data)
    data.forEach((object,index) => {
        data[index].name = `<a href="#" onclick="return false" data-id="${object.id}" class="detail">${object.name}</a>`
    })
    hot.loadData(data);
    addNameEventListener();
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
    sw.matchData(data)
    const category = document.getElementById("category");
    Array.prototype.slice.call(category)
        .forEach(option=>{
            if(option.value === data.category.id){
                option.selected = true;
            }
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
    addNameEventListener();
})
const tableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격'],
    columns : [
        {renderer:myCheckboxRenderer},
        {data:"id"},
        {data:"category.name"},
        {data:"name", renderer:"html"},
        {data:"standard"},
    ],
    colWidths : scaleArrayToSum(Array(5)),
    height:"50vh",
}
const hot = handsontable(container, tableOptions);


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

//품목 수정
const editSubmitButton = document.getElementById("editSubmitButton");
editSubmitButton.addEventListener("click", async function(){
    const editForm = document.getElementById("editForm");
    const formData = new FormData(editForm);
    if(confirm("수정 하시겠습니까?")){
        const result = await updateProduct(formData);
        alert(result.message);
        if(result.status === "OK"){
            await searchProduct()
        }
    }
})


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
