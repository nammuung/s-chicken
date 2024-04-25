import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {getSupplierList} from "../api/supplier.js";
import {addOrder, getOrder, getOrderList} from "../api/order.js";

sw.init()
searchOrder()
//공급처별 발주 테이블 초기화
let selectedSupplier= null;
const supplierContainer = document.getElementById('supplierListContainer')
const supplierCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedSupplier = instance.getDataAtCell(row,1)
        searchDetailItem(selectedSupplier)
    } else {
        selectedSupplier = null;
    }
})
const supplierTableOptions = {
    data:[],
    colHeaders : ['','ID','거래처', '내용','총 공급가액', '총 부가세'],
    columns : [
        {renderer:supplierCheckboxRenderer},
        {data:"id"},
        {data:"supplier.name"},
        {data:"content"},
        {data:"price"},
        {data:"vat"},
    ],
    colWidths : scaleArrayToSum(Array(6),763),
    height:"144",
}
const supplierHot = handsontable(supplierContainer, supplierTableOptions);
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
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '계약단가', '수량', '공급가액'],
    columns : [
        {renderer:itemCheckboxRenderer},
        {data:"item.id"},
        {data:"item.product.category.name"},
        {data:"item.product.name", renderer:"html"},
        {data:"item.product.standard"},
        {data:"item.product.unit.name"},
        {data:"price"},
        {data:"quantity"},
        {data:"totalPrice"},
    ],
    colWidths : scaleArrayToSum(Array(9),763),
    height:"280",
}
const itemHot = handsontable(itemContainer, itemTableOptions);


//발주 테이블 초기화
let selectedOrder = null;
const orderListContainer = document.getElementById('orderListContainer')
const orderCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedOrder = instance.getDataAtCell(row, 1)
        searchDetail(selectedOrder);
    } else {
        selectedOrder = null;
    }
})
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','내용', '작성일', '작성자'],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id"},
        {data:"content"},
        {data:"orderDate"},
        {data:"employee.name"},
    ],
    colWidths : scaleArrayToSum(Array(4),360),
    height:"466",
}
const orderHot = handsontable(orderListContainer, orderTableOptions);

//발주서 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    searchOrder();
})
async function searchOrder(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    const result = await getOrderList(formData);
    const datas = result.data;
    datas.forEach((data,index) => {
        if(data.orderItems.length == 1){
            data.content = `${data.orderItems[0].item.product.name}`
        } else {
            data.content = `${data.orderItems[0].item.product.name} 외 ${data.orderItems.length-1}개`
        }
    })
    orderHot.loadData(datas);
}


//상세 검색
let detailItems = [];
async function searchDetail(id){
    const result = await getOrder(id);
    const datas = result.data;
    detailItems = [];
    datas.forEach((data,index) => {
        data.id = data.supplier.id;
        if(data.orderItems.length == 1){
            data.content = `${data.orderItems[0].item.product.name}`
        } else {
            data.content = `${data.orderItems[0].item.product.name} 외 ${data.orderItems.length-1}개`
        }
        data.vat = Math.floor(data.price / 10).toLocaleString()+"원";
        data.price = Math.floor(data.price).toLocaleString()+"원";
        console.log(data)
        detailItems.push({id:data.supplier.id,orderItems:data.orderItems})
    })
    supplierHot.loadData(datas);
    [...supplierContainer.querySelectorAll("tr:nth-child(1) td:nth-child(1) input")].forEach(
        el=> el.click()
    )
}

//디테일 아이템
async function searchDetailItem(supplierId){
    const orderItems = detailItems.filter(obj => obj.id === supplierId)[0].orderItems
    itemHot.loadData(orderItems.map(orderItem=> {
        orderItem.totalPrice = (orderItem.price * orderItem.quantity).toLocaleString()+"원";
        orderItem.price = orderItem.price.toLocaleString()+"원"
        return orderItem;
    }));
}

// //발주 아이템 추가
// const itemToOrderButton = document.getElementById("itemToOrderButton");
// itemToOrderButton.addEventListener("click", async function(){
//     if(selectedItems.length == 0){
//         alert("품목을 선택해 주세요.")
//         return;
//     }
//     selectedItems.sort((a,b)=>b-a);
//     const datas = [];
//     for(let item of selectedItems){
//         const result = await getItem(item);
//         datas.push(result.data);
//     }
//     for(let data of datas){
//         orderHot.alter("insert_row_above",0);
//         orderHot.setDataAtRowProp(0,"id",data.id)
//         orderHot.setDataAtRowProp(0,"product.category.name",data.product.category.name)
//         orderHot.setDataAtRowProp(0,"product.name",data.product.name)
//         orderHot.setDataAtRowProp(0,"product.standard",data.product.standard)
//         orderHot.setDataAtRowProp(0,"product.unit.name",data.product.unit.name)
//         orderHot.setDataAtRowProp(0,"contractPrice",data.contractPrice)
//         orderHot.setDataAtRowProp(0,"product.sellPrice",data.product.sellPrice)
//         orderHot.setDataAtRowProp(0,"supplier.name",data.supplier.name)
//     }
// })



//발주서 미리보기
const orderPreviewButton = document.getElementById("orderPreviewButton")
orderPreviewButton.addEventListener("click",  async function(){
    if(selectedSupplier == null){
        alert("발주서를 선택해 주세요.")
        return;
    }
    window.open("/getOrderSheet?id="+selectedOrder+"&supplier.id="+selectedSupplier, "new", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800, height=1000, left=0, top=0" )
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


