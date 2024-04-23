import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {getSupplierList} from "../api/supplier.js";
import {addOrder} from "../api/order.js";

sw.init()
searchItem()
//아이템 테이블 초기화
const selectedItems = [];
const itemContainer = document.getElementById('itemListContainer')
const itemCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedItems.push(instance.getDataAtCell(row,1))
    } else {
        selectedItems.splice(selectedItems.indexOf(instance.getDataAtCell(row,1)),1)
    }
},false)
const itemTableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '계약단가','판매단가', '거래처', '등록일', '등록자'],
    columns : [
        {renderer:itemCheckboxRenderer},
        {data:"id"},
        {data:"product.category.name"},
        {data:"product.name", renderer:"html"},
        {data:"product.standard"},
        {data:"product.unit.name"},
        {data:"contractPrice"},
        {data:"product.sellPrice"},
        {data:"supplier.name"},
        {data:"createDate"},
        {data:"writer.name"},
    ],
    colWidths : scaleArrayToSum(Array(11)),
    height:"20vh",
}
const itemHot = handsontable(itemContainer, itemTableOptions);


//발주 테이블 초기화
let selectedOrders = [];
const orderListContainer = document.getElementById('orderListContainer')
const orderCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedOrders.push(row)
    } else {
        selectedOrders.splice(selectedItems.indexOf(row),1)
    }
},false)
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '계약단가','판매단가', '거래처', '발주수량', ''],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id", readOnly: false},
        {data:"product.category.name"},
        {data:"product.name", renderer:"html"},
        {data:"product.standard"},
        {data:"product.unit.name"},
        {data:"contractPrice"},
        {data:"product.sellPrice"},
        {data:"supplier.name"},
        {data:"orderQuantity", readOnly:false}
    ],
    colWidths : scaleArrayToSum(Array(10)),
    height:"30vh",
}
const orderHot = handsontable(orderListContainer, orderTableOptions);

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
    itemHot.loadData(data);
}


//발주 아이템 추가
const itemToOrderButton = document.getElementById("itemToOrderButton");
itemToOrderButton.addEventListener("click", async function(){
    if(selectedItems.length == 0){
        alert("품목을 선택해 주세요.")
        return;
    }
    selectedItems.sort((a,b)=>b-a);
    const datas = [];
    for(let item of selectedItems){
        const result = await getItem(item);
        datas.push(result.data);
    }
    for(let data of datas){
        orderHot.alter("insert_row_above",0);
        orderHot.setDataAtRowProp(0,"id",data.id)
        orderHot.setDataAtRowProp(0,"product.category.name",data.product.category.name)
        orderHot.setDataAtRowProp(0,"product.name",data.product.name)
        orderHot.setDataAtRowProp(0,"product.standard",data.product.standard)
        orderHot.setDataAtRowProp(0,"product.unit.name",data.product.unit.name)
        orderHot.setDataAtRowProp(0,"contractPrice",data.contractPrice)
        orderHot.setDataAtRowProp(0,"product.sellPrice",data.product.sellPrice)
        orderHot.setDataAtRowProp(0,"supplier.name",data.supplier.name)
    }
})
let clickedOrderId;
orderHot.addHook("afterChange", async ([row, prop, oldValue, newValue])=>{
    if(row[1] == 'id'  && row[3] && row[2] != row[3]){
        const result = await getItem(row[3]);
        const data = result.data;
        console.log(data)
        if(data) {
            orderHot.setDataAtRowProp(row[0],"product.category.name",data.product.category.name)
            orderHot.setDataAtRowProp(row[0],"product.name",data.product.name)
            orderHot.setDataAtRowProp(row[0],"product.standard",data.product.standard)
            orderHot.setDataAtRowProp(row[0],"product.unit.name",data.product.unit.name)
            orderHot.setDataAtRowProp(row[0],"contractPrice",data.contractPrice)
            orderHot.setDataAtRowProp(row[0],"product.sellPrice",data.product.sellPrice)
            orderHot.setDataAtRowProp(row[0],"supplier.name",data.supplier.name)
        } else {
            alert("잘못된 품번입니다.")
            orderHot.setDataAtRowProp(row[0],"id",row[2]);
        }
    }
})
//발주 행 추가
const addRowButton = document.getElementById("addRowButton")
addRowButton.addEventListener("click", function(){
    orderHot.alter("insert_row_below");
})

//발주 행 삭제
const deleteRowButton = document.getElementById("deleteRowButton")
deleteRowButton.addEventListener("click", function(){
    if(selectedOrders.length == 0){
        alert("발주를 선택해 주세요.")
        return;
    }
    selectedOrders.forEach((row, index) => {
        orderHot.alter("remove_row", row-index);
    })
    selectedOrders = [];
})

//발주서 미리보기
const orderPreviewButton = document.getElementById("orderPreviewButton")
orderPreviewButton.addEventListener("click",  async function(){
    const tableData = orderHot.getData();
    let datas = [];
    console.log(tableData)
    for (let i = 0; i < tableData.length; i++) {
        const row = tableData[i]
        const result = await getItem(row[1]);
        console.log(row[1], result)
        datas.push(result.data);
    }
    await addOrder({
        orderItems: datas,
    })
    console.log(datas);
})

// function addIdChangeEventListener(){
//     [...orderListContainer.querySelectorAll("td:nth-child(2)")].forEach(
//         el=>el.addEventListener("click",async function (){
//             clickedOrderId = el.innerText
//         })
//     )
// }

// async function setDetailDataToEditModal(id){
//     const result = await getItem(id);
//     const data = result.data;
//     sw.matchData({supplierName: data.supplier.name})
//     sw.matchData({productName: data.product.name})
//     sw.matchData({productUnitName: data.product.unit.name})
//     sw.matchData({productStandard: data.product.standard})
//     sw.matchData({productSellPrice: data.product.sellPrice})
//     sw.matchData(data)
// }



//모달
// const registerModalEl = document.getElementById("register-modal")
// const editModalEl = document.getElementById("edit-modal")
// const registerModal = new bootstrap.Modal(registerModalEl);
// const editModal = new bootstrap.Modal(editModalEl);
//
//
//품목 추가
// const addSubmitButton = document.getElementById("addSubmitButton");
// addSubmitButton.addEventListener("click", async function(){
//     if(selectedSupplier != null && selectedProduct != null){
//         if(!checkValidation()) {
//             alert("정보를 기입해주세요.")
//             return;
//         }
//         const addForm = document.getElementById("addForm");
//         const formData = new FormData(addForm);
//         const result = await addItem(formData);
//         alert(result.message);
//         if (result.status === "OK") {
//             await searchItem();
//             registerModal.hide()
//             addForm.reset();
//         }
//     }
// })

// const checkValidation = () => {
//     const inputs = [...document.querySelectorAll("#addForm input")]
//     const selects = [...document.querySelectorAll("#addForm select")]
//     let valid = true;
//     inputs.forEach(el => {
//         if(el.value == "" || el.value == undefined || el.value == null || el.value == 0){
//             valid = false;
//         }
//     })
//     selects.forEach(el => {
//         if(el.value == "" || el.value == undefined || el.value == null || el.value == 0){
//             valid = false;
//         }
//     })
//     return valid;
// }

//품목 수정
// const editSubmitButton = document.getElementById("editSubmitButton");
// editSubmitButton.addEventListener("click", async function(){
//     const editForm = document.getElementById("editForm");
//     const formData = new FormData(editForm);
//     if(confirm("수정 하시겠습니까?")){
//         const result = await updateItem(formData);
//         alert(result.message);
//         if(result.status === "OK"){
//             await searchItem()
//         }
//     }
// })

//
// const exportPlugin = hot.getPlugin('exportFile');
// const exportButton = document.getElementById("exportButton");
// exportButton.addEventListener("click", function(){
//     exportPlugin.downloadFile('csv', {
//         bom: false,
//         columnDelimiter: ',',
//         columnHeaders: true,
//         exportHiddenColumns: true,
//         fileExtension: 'csv',
//         filename: '품목_[YYYY]-[MM]-[DD]',
//         mimeType: 'text/csv',
//         rowDelimiter: '\r\n',
//         rowHeaders: false,
//         range:[0,1]
//     });
// })


