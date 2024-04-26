import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {getSupplierList} from "../api/supplier.js";
import {addOrder, getOrder, getOrderList, updateOrder} from "../api/order.js";
import {orderStatusToKR} from "../util/orderStatus.js";

const orderPreviewButton = document.getElementById("orderPreviewButton")
const modifyButtons = document.getElementById("modifyButtons")
sw.init()
searchOrder()
//공급처별 발주 테이블 초기화
let selectedSupplier= null;
const supplierContainer = document.getElementById('supplierListContainer')
const supplierCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedSupplier = instance.getDataAtCell(row,1)
        orderPreviewButton.classList.remove("d-none")
        searchDetailItem(selectedSupplier)
        if(instance.getDataAtCell(row,2) == "대기" ) {
            modifyButtons.classList.toggle("d-none")
        } else {
            modifyButtons.classList.add("d-none")
        }
    } else {
        selectedSupplier = null;
        orderPreviewButton.classList.add("d-none")
        modifyButtons.classList.add("d-none")
    }
})
const supplierTableOptions = {
    data:[],
    colHeaders : ['','ID','구분','거래처', '내용','총 공급가액', '총 부가세'],
    columns : [
        {renderer:supplierCheckboxRenderer},
        {data:"id"},
        {data:"status"},
        {data:"supplier.name"},
        {data:"content"},
        {data:"price"},
        {data:"vat"},
    ],
    colWidths : scaleArrayToSum(Array(7),763),
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
        {data:"id"},
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
const orderCheckboxRenderer = checkboxRenderer(async ({checked, instance, td, row, col})=>{
    if(checked){
        selectedOrder = instance.getDataAtCell(row, 1)
        await searchDetail(selectedOrder);
    } else {
        orderPreviewButton.classList.add("d-none")
        selectedOrder = null;
        supplierHot.loadData([]);
        itemHot.loadData([]);
    }
})
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','내용', '작성일', '작성자'],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id"},
        {data:"content"},
        {data:"writeDate"},
        {data:"employee.name"},
    ],
    colWidths : scaleArrayToSum(Array(4),360),
    height:"466",
}
const orderHot = handsontable(orderListContainer, orderTableOptions);

//발주서 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    await searchOrder();
})
async function searchOrder(){
    orderPreviewButton.classList.add("d-none")
    modifyButtons.classList.add("d-none")
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
    supplierHot.loadData([]);
    itemHot.loadData([]);
    if(selectedOrder != null) {
        [...orderListContainer.querySelectorAll("td:nth-child(2)")].forEach((el, index) => {
            if(el.innerText == selectedOrder) {
                orderListContainer.querySelectorAll("td:nth-child(1) input")[index].click();
            }
        })
    }
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
        data.status = orderStatusToKR(data.status);
        console.log(data)
        detailItems.push({id:data.supplier.id,orderItems:data.orderItems.map(orderItem=> {
                orderItem.totalPrice = (orderItem.price * orderItem.quantity).toLocaleString()+"원";
                orderItem.price = orderItem.price.toLocaleString()+"원"
                return orderItem;
            })})
    })
    supplierHot.loadData(datas);
    supplierContainer.querySelector("tr:nth-child(1) td:nth-child(1) input").click();
}

//디테일 아이템
async function searchDetailItem(supplierId){
    const orderItems = detailItems.filter(obj => obj.id === supplierId)[0].orderItems
    itemHot.loadData(orderItems);
}

//발주 승인 버튼
const approveOrderButton = document.getElementById("approveOrderButton");
approveOrderButton.addEventListener("click", async function () {
    if(selectedOrder == null){
        alert("발주를 선택해 주세요.")
        return;
    }
    const formData = new FormData();
    formData.append("id", selectedOrder);
    formData.append("supplier.id", selectedSupplier);
    formData.append("status", 1);
    const result = await updateOrder(formData);
    alert(result.message);
    if(result.status == "OK"){
        searchDetail(selectedOrder);
    }
})

//발주 반려 버튼
const refuseOrderButton = document.getElementById("refuseOrderButton");
refuseOrderButton.addEventListener("click", async function () {
    if(selectedOrder == null){
        alert("발주를 선택해 주세요.")
        return;
    }
    const formData = new FormData();
    formData.append("id", selectedOrder);
    formData.append("supplier.id", selectedSupplier);
    formData.append("status", 3);
    const result = await updateOrder(formData);
    alert(result.message);
    if(result.status == "OK"){
        searchDetail(selectedOrder);
    }
})



//발주서 미리보기
orderPreviewButton.addEventListener("click",  async function(){
    if(selectedSupplier == null){
        alert("발주서를 선택해 주세요.")
        return;
    }
    window.open("/getOrderSheet?id="+selectedOrder+"&supplier.id="+selectedSupplier, "new", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800, height=1000, left=0, top=0" )
})


//발주일 설정
const searchStartDate = document.getElementById("searchStartDate")
const searchEndDate = document.getElementById("searchEndDate")
const todayDate = dayjs();
const threeMonthBefore = dayjs(dayjs().set('month',dayjs().get('month')-3)).format("YYYY-MM-DD")
searchStartDate.value = threeMonthBefore
searchEndDate.value = todayDate.format("YYYY-MM-DD")
searchStartDate.addEventListener("change", function (e) {
    if(dayjs(e.target.value).isAfter(dayjs(searchEndDate.value))){
        alert("종료일 보다 뒤의 날짜는 설정할 수 없습니다.")
        e.target.value=dayjs(searchEndDate.value).format("YYYY-MM-DD")
    }
})
searchEndDate.addEventListener("change", function (e) {
    if(dayjs(e.target.value).isAfter(todayDate)){
        alert("금일보다 뒤의 날짜는 설정할 수 없습니다.")
        e.target.value=todayDate.format("YYYY-MM-DD")
    }
    if(dayjs(e.target.value).isBefore(dayjs(searchStartDate.value))){
        alert("시작일 앞의 날짜는 설정할 수 없습니다.")
        e.target.value=dayjs(searchStartDate.value).format("YYYY-MM-DD")
    }
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


