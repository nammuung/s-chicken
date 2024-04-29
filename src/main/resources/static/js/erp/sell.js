import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {getProduct, getProductList} from "../api/product.js";
import {
    addOrder,
    getOrder,
    getOrderList,
    updateOrder,
    updateOrderDetail
} from "../api/order.js";
import {
    orderStatus,
    productStatus,
    productStatusToKR,
    orderStatusToKR,
    franchiseOrderStatusToKR
} from "../util/orderStatus.js";
import {
    getFranchiseOrder,
    getFranchiseOrderList,
    updateFranchiseOrder,
} from "../api/franchiseOrder.js";

let isChanged = {};
const orderPreviewButton = document.getElementById("orderPreviewButton")
const modifyButtons = document.getElementById("modifyButtons")


Object.defineProperty(isChanged, "change", {
    get: function (){return this._change || false},
    set: function (value){
        this._change = value
        if(this._change){
            modifyButtons.classList.remove("d-none")
        } else {
            modifyButtons.classList.add("d-none")
        }
    }
})

sw.init()
//공급처별 발주 테이블 초기화
let selectedOrder= null;
const orderContainer = document.getElementById('orderListContainer')
const orderCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        orderPreviewButton.classList.remove("d-none")
        selectedOrder = instance.getDataAtCell(row,1)
        searchDetail(selectedOrder)
        if(instance.getDataAtCell(row,5) == "미발주" ) {
            modifyButtons.classList.remove("d-none")
        } else {
            modifyButtons.classList.add("d-none")
        }
    } else {
        selectedOrder = null;
        orderPreviewButton.classList.add("d-none")
        modifyButtons.classList.add("d-none")
    }
})
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','가맹점', '발주일','금액', '상태'],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id"},
        {data:"franchise.name"},
        {data:"orderDate"},
        {data:"price"},
        {data:"status"}
    ],
    colWidths : scaleArrayToSum(Array(5),360),
    height:"50vh",
}
const orderHot = handsontable(orderListContainer, orderTableOptions);
//아이템 테이블 초기화
const selectedProducts = [];
const productContainer = document.getElementById('productListContainer')
const productCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedProducts.push(instance.getDataAtCell(row,1))
    } else {
        selectedProducts.splice(selectedProducts.indexOf(instance.getDataAtCell(row,1)),1)
    }
})
const productTableOptions = {
    data:[],
    colHeaders : ['카테고리', '품명', '규격','단위', '단가', '판매가액','요청수량', '출고수량','재고','상태'],
    columns : [
        {data:"product.category.name"},
        {data:"product.name", renderer:"html"},
        {data:"product.standard"},
        {data:"product.unit.name"},
        {data:"price"},
        {data:"totalPrice"},
        {data:"quantity"},
        {data:"deliverQuantity"},
        {data:"product.stock"},
        {
            data:"status",
            strict:true,
            source: productStatus
        },
    ],
    colWidths : scaleArrayToSum(Array(10),667, true),
    height:"50vh",
}
const productHot = handsontable(productContainer, productTableOptions);

//발주서 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    await searchOrder(selectedOrder);

})
searchOrder();
async function searchOrder(){
    isChanged.change = false;
    orderPreviewButton.classList.add("d-none")
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    if(formData.get("totalId")){
        const ids = formData.get("totalId").split("-")
        if(ids.length === 2){
            formData.append("supplier.id", formData.get("totalId").split("-")[1])
        }
        formData.append("id", ids[0])
        formData.delete("totalId")
    }
    const result = await getFranchiseOrderList(formData);
    const datas = result.data;
    console.log(datas)
    datas.forEach((data,index) => {
        data.price = data.price.toLocaleString()+"원";
        data.status = franchiseOrderStatusToKR(data.status);
    })
    orderHot.loadData(datas);
    productHot.loadData([]);
    if(selectedOrder != null) {
        [...orderListContainer.querySelectorAll("td:nth-child(2)")].forEach((el, index) => {
            if(el.innerText == selectedOrder) {
                orderListContainer.querySelectorAll("td:nth-child(1) input")[index].click();
            }
        })
    }
}

let tempDeliver = [];
//상세 검색
async function searchDetail(id){
    isChanged.change = false;
    const result = await getFranchiseOrder(id);
    const data = result.data;
    console.log(data);
    tempDeliver = [];
    const detailProducts =
        data.orderDetails.map(orderDetail=> {
            orderDetail.totalPrice = (orderDetail.price * orderDetail.quantity).toLocaleString()+"원";
            orderDetail.price = orderDetail.price.toLocaleString()+"원";
            orderDetail.status = productStatusToKR(orderDetail.status)
            tempDeliver.push(orderDetail.deliverQuantity);
            return orderDetail;
        })
    productHot.loadData(
        detailProducts
    );
    //상태 진행인 로우만 입력가능하게 풀어줌
    productHot.updateSettings({
        cells(row, col) {
            const cellProperties = {};
            try{
                if (productHot.getData()[row][col+1] === '진행') {
                    cellProperties.readOnly = false;
                }
            } catch (e){
                console.log(e)
            }
            return cellProperties;
        }
    });

}

// //디테일 아이템
// async function searchDetailProduct(orderId){
//     const orderDetails = detailProducts.filter(obj => obj.id === orderId)[0].orderDetails
//
// }

//발주 승인 버튼
const approveOrderButton = document.getElementById("approveOrderButton");
approveOrderButton.addEventListener("click", async function () {
    if(selectedOrder == null){
        alert("발주를 선택해 주세요.")
        return;
    }
    const formData = new FormData();
    formData.append("id", selectedOrder);
    formData.append("status", 1);
    const result = await updateFranchiseOrder(formData);
    alert(result.message);
    if(result.status == "OK"){
        searchOrder()
    }
})

//발주 반려 버튼
const refuseOrderButton = document.getElementById("refuseOrderButton");
refuseOrderButton.addEventListener("click", async function () {
    if(selectedOrder == null){
        alert("발주를 선택해 주세요.")
        return;
    }
    if(confirm("삭제 하겠습니까?")){
        const formData = new FormData();
        formData.append("id", selectedOrder);
        formData.append("status", 7);
        const result = await updateFranchiseOrder(formData);
        alert(result.message);
        if(result.status == "OK"){
            searchOrder()
        }
    }
})



//발주서 미리보기
orderPreviewButton.addEventListener("click",  async function(){
    if(selectedOrder == null){
        alert("발주서를 선택해 주세요.")
        return;
    }
    window.open("/franchise/getOrderSheet?id="+selectedOrder, "new", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=800, height=1000, left=0, top=0" )
})
//변경감지 훅
productHot.addHook("afterChange", changes => {
    changes?.forEach(async ([row, prop, before, after]) => {
        const quantity = productHot.getDataAtRowProp(row, "quantity");
        if(prop == 'status'  && after && before != after) {
            if(after == "완료") {
                productHot.setDataAtRowProp(row,"deliverQuantity",quantity);
            }
        }
        if(prop == 'deliverQuantity'  && after && before != after){
            if(isNaN(after)){
                alert("숫자만 입력가능합니다.")
                productHot.setDataAtRowProp(row,"deliverQuantity",0);
            }
            if(tempDeliver[row] > after) {
                alert("기존 입고된 수량보다 적게 입력할 수 없습니다.");
                productHot.setDataAtRowProp(row,"deliverQuantity",before);
            }
            if(after > quantity) {
                productHot.setDataAtRowProp(row,"deliverQuantity",quantity);
            }
            if(after == quantity) {
                productHot.setDataAtRowProp(row,"status","완료");
            }
            if(after < quantity) {
                productHot.setDataAtRowProp(row,"status","진행");
            }
            isChanged.change = true;
        }

    })
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
//     const result = await getProduct(id);
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
//         const result = await addProduct(formData);
//         alert(result.message);
//         if (result.status === "OK") {
//             await searchProduct();
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
//         const result = await updateProduct(formData);
//         alert(result.message);
//         if(result.status === "OK"){
//             await searchProduct()
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


