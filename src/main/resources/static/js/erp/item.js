import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {getSupplierList} from "../api/supplier.js";

sw.init()

//메인 테이블 초기화
const container = document.getElementById('example')
const myCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedRowId = instance.getDataAtCell(row,1);
        setDetailDataToEditModal(selectedRowId)
    } else {
        selectedRowId = null
    }
    addNameEventListener();
})
const tableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '계약단가', '거래처', '등록일', '등록자'],
    columns : [
        {renderer:myCheckboxRenderer},
        {data:"id"},
        {data:"product.category.name"},
        {data:"product.name", renderer:"html"},
        {data:"product.standard"},
        {data:"unit.name"},
        {data:"contractPrice"},
        {data:"supplier.name"},
        {data:"createDate"},
        {data:"writer.name"},
    ],
    colWidths : scaleArrayToSum(Array(10)),
    height:"50vh",
}
const hot = handsontable(container, tableOptions);


//품목 테이블 초기화
// 테이블 초기화
const productContainer = document.getElementById('productContainer')
let selectedProduct = {}
const productCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedProduct.id = instance.getDataAtCell(row,1);
        selectedProduct.name = instance.getDataAtCell(row,3);
        sw.matchData({productName:selectedProduct.name, productId:selectedProduct.id})
    } else {
        selectedProduct = {}
    }
    // addNameEventListener();
})
let productHot = null

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
searchItem()
//품목 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    searchItem();
})
async function searchItem(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    const result = await getItemList(formData);
    const data = result.data;
    data.forEach((object,index) => {
        data[index].name = `<a href="#" onclick="return false" data-id="${object.id}" class="detail">${object.name}</a>`
    })
    console.log(data)
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
    const result = await getItem(id);
    const data = result.data;
    const unit = document.getElementById("unit");
    console.log(data)
    Array.prototype.slice.call(unit)
        .forEach(option=>{
            console.log(option.value);
            if(option.value === data.unit.id){
                option.selected = true;
            }
        })
    sw.matchData({supplierName: data.supplier.name})
    sw.matchData({productName: data.product.name})
    sw.matchData(data)
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



//모달
const registerModalEl = document.getElementById("register-modal")
const editModalEl = document.getElementById("edit-modal")
const registerModal = new bootstrap.Modal(registerModalEl);
const editModal = new bootstrap.Modal(editModalEl);


//품목 추가
const addSubmitButton = document.getElementById("addSubmitButton");
addSubmitButton.addEventListener("click", async function(){
    if(selectedSupplier != null && selectedProduct != null){
        if(!checkValidation()) {
            alert("정보를 기입해주세요.")
            return;
        }
        const addForm = document.getElementById("addForm");
        const formData = new FormData(addForm);
        const result = await addItem(formData);
        alert(result.message);
        if (result.status === "OK") {
            await searchItem();
            registerModal.hide()
            addForm.reset();
        }
    }
})

const checkValidation = () => {
    const inputs = [...document.querySelectorAll("#addForm input")]
    const selects = [...document.querySelectorAll("#addForm select")]
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

//품목 수정
const editSubmitButton = document.getElementById("editSubmitButton");
editSubmitButton.addEventListener("click", async function(){
    const editForm = document.getElementById("editForm");
    const formData = new FormData(editForm);
    if(confirm("수정 하시겠습니까?")){
        const result = await updateItem(formData);
        alert(result.message);
        if(result.status === "OK"){
            await searchItem()
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



//품목 상품 검색
const productSearchButton = document.getElementById("productSearchButton")
productSearchButton.addEventListener("click", async function(){
    const productSearchForm = document.getElementById("productSearchForm");
    const formData = new FormData(productSearchForm);
    const result = await getProductList(formData);
    const data = result.data;
    productHot.loadData(data);
})

//거래처 검색
const supplierSearchButton = document.getElementById("supplierSearchButton")
supplierSearchButton.addEventListener("click", async function(){
    const supplierSearchForm = document.getElementById("supplierSearchForm");
    const formData = new FormData(supplierSearchForm);
    const result = await getSupplierList(formData);
    const data = result.data;
    supplierHot.loadData(data);
})
const productTap = document.querySelector('button[data-bs-target="#product-select"]')
productTap.addEventListener("shown.bs.tab", function (){

})
const addButton = document.getElementById("addButton")
addButton.addEventListener("click", function(){
    registerModal.show();
    productSearchButton.click();
    const tableHeight = document.querySelector(".modal-body .position-relative").getBoundingClientRect().height- document.getElementById("productSearchContainer").getBoundingClientRect().height-document.getElementById("next")-document.getElementById("nextButton1").getBoundingClientRect().height -20
    const productTableOptions = {
        data:[],
        colHeaders : ['','ID','카테고리', '품명', '규격'],
        columns : [
            {renderer:productCheckboxRenderer},
            {data:"id"},
            {data:"category.name"},
            {data:"name", renderer:"html"},
            {data:"standard"},
        ],
        colWidths : scaleArrayToSum(Array(5)),
        height:tableHeight,
    }
    productHot = handsontable(productContainer, productTableOptions);
})

const supplierTap = document.querySelector('button[data-bs-target="#supplier-select"]')
const itemTap = document.querySelector('button[data-bs-target="#item-select"]')
supplierTap.addEventListener("shown.bs.tab" , function(){
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
                {data:"managerName"}
            ],
            colWidths : scaleArrayToSum(Array(7)),
            height:tableHeight,
        }
        supplierHot = handsontable(supplierContainer, supplierTableOptions);
        supplierSearchButton.click()
    }
})
const nextButton1 = document.getElementById("nextButton1")
nextButton1.addEventListener("click", function(){
    const bsTab = new bootstrap.Tab(supplierTap)
    bsTab.show()
})
const nextButton2 = document.getElementById("nextButton2")
nextButton2.addEventListener("click", function(){
    const bsTab = new bootstrap.Tab(itemTap)
    bsTab.show()
})
