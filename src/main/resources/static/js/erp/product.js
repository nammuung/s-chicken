import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addProduct, getProduct, getProductList, updateProduct} from "../api/product.js";
import {addItem, getItemList} from "../api/item.js";
import {getSupplierList} from "../api/supplier.js";

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
    const stringData = JSON.stringify(data);
    const jsonData = JSON.parse(stringData);
    exportHot.loadData(jsonData);
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
    sw.matchData(data)
    const category = document.getElementById("category");
    Array.prototype.slice.call(category)
        .forEach(option=>{
            if(option.value === data.category.id){
                option.selected = true;
            }
        })
    const unit = document.getElementById("unit");
    Array.prototype.slice.call(unit)
        .forEach(option=>{
            if(option.value === data.unit.id){
                option.selected = true;
            }
        })
    const formData = new FormData();
    formData.append("product.id", id);
    const items = await getItemList(formData)
    const supplierTable = document.querySelector("#supplierTable tbody")
    supplierTable.innerHTML = ""
    items.data.forEach((item,index) => {
        supplierTable.innerHTML += `
            <tr>
                <td>
                    ${index+1}
                </td>
                <td>${item.supplier.name}</td>
                <td>${item.contractPrice}</td>
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



const supplierRegisterModalEl = document.getElementById("supplierRegister-modal")
const supplierRegisterModal = new bootstrap.Modal(supplierRegisterModalEl);
//거래처 검색
const supplierSearchButton = document.getElementById("supplierSearchButton")
supplierSearchButton.addEventListener("click", async function(){
    const supplierSearchForm = document.getElementById("supplierSearchForm");
    const formData = new FormData(supplierSearchForm);
    const result = await getSupplierList(formData);
    const data = result.data;
    supplierHot.loadData(data);
})

//거래처 테이블 초기화
const supplierContainer = document.getElementById("supplierContainer");
let selectedSupplier = {}
const supllierCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedSupplier.id = instance.getDataAtCell(row,1);
        selectedSupplier.name = instance.getDataAtCell(row,2);
        sw.matchData({supplierName:selectedSupplier.name, supplierId: selectedSupplier.id})
    } else {
        selectedSupplier = {}
    }
    // addNameEventListener();
})

let supplierHot = null

const regiButton = document.getElementById("regiButton")
regiButton.addEventListener("click", async function(){
    if(selectedRowId == null) {
        alert("품목을 선택해 주세요.")
        return;
    }
    await setDetailDataToRegisterModal(selectedRowId);
    supplierRegisterModal.show();
    const productProcessButton = document.querySelector("button[data-bs-target='#supplier-select']");
    productProcessButton.click();
    if(supplierHot == null){
        const tableHeight = document.querySelector(".modal-body .position-relative").getBoundingClientRect().height- document.getElementById("supplierSearchContainer").getBoundingClientRect().height-document.getElementById("next")-document.getElementById("nextButton2").getBoundingClientRect().height -20
        const supplierTableOptions = {
            data:[],
            colHeaders : ["","ID","거래처명","대표자명", "전화", "등록일", "등록자"],
            columns : [
                {renderer:supllierCheckboxRenderer},
                {data:"id"},
                {data:"name", renderer:"html"},
                {data:"ownerName"},
                {data:"contactNumber"},
                {data:"contractDate"},
                {data:"manager.name"}
            ],
            colWidths : scaleArrayToSum(Array(7),1130),
            height:tableHeight,
        }
        supplierHot = handsontable(supplierContainer, supplierTableOptions);
    }
    supplierSearchButton.click()
})

async function setDetailDataToRegisterModal(id){
    const result = await getProduct(id);
    const data = result.data;
    console.log(data)
    sw.matchData({
        productId: data.id,
        productName: data.name,
        productStandard: data.standard,
        productUnitName: data.unit.name,
        productSellPrice: data.sellPrice,
    })
}

const itemTap = document.querySelector('button[data-bs-target="#item-select"]')
const nextButton2 = document.getElementById("nextButton2")
nextButton2.addEventListener("click", function(){
    const bsTab = new bootstrap.Tab(itemTap)
    bsTab.show()
})

//품목 추가
const supplierSubmitButton = document.getElementById("supplierSubmitButton");
supplierSubmitButton.addEventListener("click", async function(){
    if(selectedSupplier != null){
        if(!checkValidation()) {
            alert("정보를 기입해주세요.")
            return;
        }
        const supplierForm = document.getElementById("supplierForm");
        const formData = new FormData(supplierForm);
        const result = await addItem(formData);
        supplierHot.clear();
        alert(result.message);
        if (result.status === "OK") {
            supplierRegisterModal.hide()
            supplierForm.reset();
        }
    }
})
const checkValidation = () => {
    const inputs = [...document.querySelectorAll("#supplierForm input")]
    const selects = [...document.querySelectorAll("#supplierForm select")]
    let valid = true;
    inputs.forEach(el => {
        if(el.value == "" || el.value == undefined || el.value == null || el.value == 0){
            valid = false;
        }
    })
    selects.forEach(el => {
        if(el.value == "" || el.value == undefined || el.value == null || el.value == 0){
            valid = false;
        }
    })
    return valid;
}


const exportHotContainer = document.createElement('div')
const exportHot = handsontable(exportHotContainer, tableOptions)
const exportPlugin = exportHot.getPlugin('exportFile');


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