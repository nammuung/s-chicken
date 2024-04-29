import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addItem, getItemList, updateItem, getItem} from "../api/item.js";
import {getProduct, getProductList} from "../api/product.js";
import {getSupplierList} from "../api/supplier.js";
import {addFranchiseOrder} from "../api/franchiseOrder.js";

sw.init()

//아이템 테이블 초기화
let selectedProducts = [];
const productContainer = document.getElementById('productListContainer')
const productCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedProducts.push(instance.getDataAtCell(row,1))
    } else {
        selectedProducts.splice(selectedProducts.indexOf(instance.getDataAtCell(row,1)),1)
    }
},false)
const productTableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '단가'],
    columns : [
        {renderer:productCheckboxRenderer},
        {data:"id"},
        {data:"category.name"},
        {data:"name"},
        {data:"standard"},
        {data:"unit.name"},
        {data:"sellPrice"},
    ],
    colWidths : scaleArrayToSum(Array(6), 381),
    height:"50vh",
}
const productHot = handsontable(productContainer, productTableOptions);

searchProduct()

//발주 테이블 초기화
let selectedOrders = [];
const orderListContainer = document.getElementById('orderListContainer')
const orderCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedOrders.push(row)
    } else {
        selectedOrders.splice(selectedProducts.indexOf(row),1)
    }
},false)
const orderTableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격','단위', '단가', '수량'],
    columns : [
        {renderer:orderCheckboxRenderer},
        {data:"id", readOnly: false},
        {data:"category.name"},
        {data:"name"},
        {data:"standard"},
        {data:"unit.name"},
        {data:"sellPrice"},
        {data:"orderQuantity", readOnly:false}
    ],
    colWidths : scaleArrayToSum(Array(8),667),
    height:"50vh",
}
const orderHot = handsontable(orderListContainer, orderTableOptions);
const orderButton = document.getElementById("orderButton");
orderHot.addHook("afterRender", function () {
    if(orderHot.getData().length > 0) {
        orderButton.classList.remove("d-none")
    } else {
        orderButton.classList.add("d-none")
    }
})
//품목 검색
const productSearchButton = document.getElementById("productSearchButton");
productSearchButton.addEventListener("click", async function () {
    searchProduct();
})
async function searchProduct(){
    selectedProducts = [];
    const productSearchForm = document.getElementById("productSearchForm");
    const formData = new FormData(productSearchForm);
    const result = await getProductList(formData);
    const data = result.data;
    productHot.loadData(data);
}


//발주 아이템 추가
const productToOrderButton = document.getElementById("productToOrderButton");
productToOrderButton.addEventListener("click", async function(){
    if(selectedProducts.length == 0){
        alert("품목을 선택해 주세요.")
        return;
    }
    const datas = [];
    for(let product of selectedProducts){
        const result = await getProduct(product);
        datas.push(result.data);
    }
    for(let data of datas){
        const lastRow = orderHot.getData().length
        orderHot.batch(()=>{
            orderHot.setDataAtRowProp(lastRow,"id",data.id)
        })
    }
})
let clickedOrderId;

orderHot.addHook("afterChange", changes => {
    changes?.forEach(async ([row, prop, before, after]) => {
        if(prop == 'id'  && after && before != after){
            const result = await getProduct(after);
            orderHot.batch(()=>{
                const data = result.data;
                if(data) {
                    orderHot.setDataAtRowProp(row,"category.name",data.category.name)
                    orderHot.setDataAtRowProp(row,"name",data.name)
                    orderHot.setDataAtRowProp(row,"standard",data.standard)
                    orderHot.setDataAtRowProp(row,"unit.name",data.unit.name)
                    orderHot.setDataAtRowProp(row,"sellPrice",data.sellPrice)
                } else {
                    alert("잘못된 품번입니다.")
                    orderHot.setDataAtRowProp(row,"id",before);
                }
            })
        }
    })
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

//발주서 저장
orderButton.addEventListener("click",  async function(){
    const tableData = orderHot.getData();
    let datas = [];
    let quantity=[];
    for (let i = 0; i < tableData.length; i++) {
        if(!tableData[i][1]) continue;
        const row = tableData[i]
        if(!row[7]) {
            alert("발주수량을 입력해 주세요.")
            return;
        }
        const result = await getProduct(row[1]);
        quantity.push(row[7]);
        datas.push(result.data);
    }
    // const addOrderForm = document.getElementById("addOrderForm");
    const orderDetails = []
    datas.forEach((data,index) => {
        orderDetails.push({
            product:{
                id: data.id
            },
            quantity: quantity[index]
        })
    })
    const request = {
        orderDetails,
    }
    const result = await addFranchiseOrder(
        request
    )
    alert(result.message)
    if(result.status == 'OK'){
        orderHot.loadData([]);
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


