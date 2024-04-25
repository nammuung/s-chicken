import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {
    addOrder,
    getOrder,
    getOrderList,
    getOrderSheet,
    getOrderSheetList,
    updateOrder,
    updateOrderItem
} from "../api/order.js";
import {orderStatus, itemStatus, itemStatusToKR, orderStatusToKR} from "../util/orderStatus.js";
let isChanged = false;

sw.init()
//공급처별 발주 테이블 초기화
let selectedOrder= null;
const orderContainer = document.getElementById('orderListContainer')
const orderCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedOrder = instance.getDataAtCell(row,1)
        searchDetail(selectedOrder)
    } else {
        selectedOrder = null;
    }
})
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','구분','거래처', '내용', '총 공급가액', '총 부가세','발주일','발주자'],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id"},
        {data:"status"},
        {data:"supplier.name"},
        {data:"content"},
        {data:"price"},
        {data:"vat"},
        {data:"orderDate"},
        {data:"employee.name"},
    ],
    colWidths : scaleArrayToSum(Array(8)),
    height:"144",
}
const orderHot = handsontable(orderContainer, orderTableOptions);
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
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '계약단가', '공급가액','발주수량', '입고수량','상태'],
    columns : [
        {renderer:itemCheckboxRenderer},
        {data:"id"},
        {data:"item.product.category.name"},
        {data:"item.product.name", renderer:"html"},
        {data:"item.product.standard"},
        {data:"item.product.unit.name"},
        {data:"price"},
        {data:"totalPrice"},
        {data:"quantity"},
        {data:"deliverQuantity", readOnly: false},
        {
            data:"status",
            readOnly: false,
            type:"dropdown",
            strict:true,
            source: itemStatus
        },
    ],
    colWidths : scaleArrayToSum(Array(10)),
    height:"280",
}
const itemHot = handsontable(itemContainer, itemTableOptions);

//발주서 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    searchOrder(selectedOrder);
})
searchOrder();
async function searchOrder(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    formData.append("status", 1);
    const result = await getOrderSheetList(formData);
    const datas = result.data;
    console.log(datas)
    datas.forEach((data,index) => {
        if(data.orderItems.length == 1){
            data.content = `${data.orderItems[0].item.product.name}`
        } else {
            data.content = `${data.orderItems[0].item.product.name} 외 ${data.orderItems.length-1}개`
        }
        data.id = data.id+"-"+data.supplier.id
        data.vat = Math.floor(data.price / 10).toLocaleString()+"원";
        data.price = Math.floor(data.price).toLocaleString()+"원";
        data.status = orderStatusToKR(data.status);
    })
    orderHot.loadData(datas);
}


//상세 검색
let detailItems = [];
async function searchDetail(id){
    const result = await getOrderSheet(id);
    const data = result.data;
    console.log(data)
    // detailItems = [];
    // detailItems.push({
        // id:orderItem.order.id,
        // orderItems:data.orderItems.map(orderItem=> {
        //     orderItem.totalPrice = (orderItem.price * orderItem.quantity).toLocaleString()+"원";
        //     orderItem.price = orderItem.price.toLocaleString()+"원"
        //     return orderItem;
        // })})
    // data.orderItems.forEach((orderItem,index) => {
    //     // data.id = data.order.id;
    //     // if(data.orderItems.length == 1){
    //     //     data.content = `${data.orderItems[0].item.product.name}`
    //     // } else {
    //     //     data.content = `${data.orderItems[0].item.product.name} 외 ${data.orderItems.length-1}개`
    //     // }
    //     // data.vat = Math.floor(data.price / 10).toLocaleString()+"원";
    //     // data.price = Math.floor(data.price).toLocaleString()+"원";
    //     // console.log(data)
    //
    // })
    // orderHot.loadData(datas);
    // [...orderContainer.querySelectorAll("tr:nth-child(1) td:nth-child(1) input")].forEach(
    //     el=> el.click()
    // )
    itemHot.loadData(
        data.orderItems.map(orderItem=> {
        orderItem.totalPrice = (orderItem.price * orderItem.quantity).toLocaleString()+"원";
        orderItem.price = orderItem.price.toLocaleString()+"원";
        orderItem.status = itemStatusToKR(orderItem.status)
        return orderItem;
    }));
}

// //디테일 아이템
// async function searchDetailItem(orderId){
//     const orderItems = detailItems.filter(obj => obj.id === orderId)[0].orderItems
//
// }

//변경 저장  버튼
const saveChangeButton = document.getElementById("saveChangeButton");
saveChangeButton.addEventListener("click", async function () {
    if(selectedOrder == null){
        alert("발주를 선택해 주세요.")
        return;
    }
    const orderItems = [];
    const formData = new FormData();
    formData.append("id", selectedOrder);
    formData.append("status", 1);
    itemHot.getData().forEach(item => {
        console.log(item)
        orderItems.push({
            id:item[1],
            deliverQuantity:item[9],
            status:itemStatus.indexOf(item[10]),
            order:{
                id:selectedOrder.split("-")[0]
            },
            supplier:{
                id:selectedOrder.split("-")[1]
            },
        })
    })
    console.log(orderItems)
    const result = await updateOrderItem(orderItems);
    alert(result.message);
    if(result.status == "OK"){
        searchOrder();
    }
})



//발주서 미리보기
const orderPreviewButton = document.getElementById("orderPreviewButton")
orderPreviewButton.addEventListener("click",  async function(){
    if(selectedOrder == null){
        alert("발주서를 선택해 주세요.")
        return;
    }
    const orderId = selectedOrder.split("-")[0]
    const supplierId = selectedOrder.split("-")[1]
    window.open("/getOrderSheet?id="+orderId+"&supplier.id="+supplierId, "new", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800, height=1000, left=0, top=0" )
})
//변경감지 훅
itemHot.addHook("afterChange", changes => {
    changes?.forEach(async ([row, prop, before, after]) => {
        const quantity = itemHot.getDataAtRowProp(row, "quantity");
        if(prop == 'status'  && after && before != after) {
            if(after == "완료") {
                itemHot.setDataAtRowProp(row,"deliverQuantity",quantity);
            }
        }
        if(prop == 'deliverQuantity'  && after && before != after){
            if(after > quantity) {
                itemHot.setDataAtRowProp(row,"deliverQuantity",quantity);
            }
            if(after == quantity) {
                itemHot.setDataAtRowProp(row,"status","완료");
            }
            if(after < quantity) {
                console.log(after, quantity)
                itemHot.setDataAtRowProp(row,"status","진행");
            }
            if(isNaN(after)){
                alert("숫자만 입력가능합니다.")
                itemHot.setDataAtRowProp(row,"deliverQuantity",0);
            }
            isChanged = true;
        }

    })
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
//     sw.matchData({orderName: data.order.name})
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
//     if(selectedOrder != null && selectedProduct != null){
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


