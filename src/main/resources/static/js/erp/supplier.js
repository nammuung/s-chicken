import {getSupplierList, getSupplier, updateSupplier, addSupplier} from "../api/supplier.js";
import {handsontable, checkboxRenderer, scaleArrayToSum} from "../lib/handsontable.js";
import {getItemList, getItem} from "../api/item.js";

const registerModalEl = document.getElementById("register-modal")
const editModalEl = document.getElementById("edit-modal")
const registerModal = new bootstrap.Modal(registerModalEl);
const editModal = new bootstrap.Modal(editModalEl);

sw.init()


const searchButton = document.getElementById("supplierSearchButton");

searchSupplier();
searchButton.addEventListener("click", function () {
    searchSupplier();
})
async function searchSupplier(){
    const searchForm = document.getElementById("supplierSearchForm");
    const formData = new FormData(searchForm);
    const result = await getSupplierList(formData);
    const data = result.data;
    const stringData = JSON.stringify(data);
    const jsonData = JSON.parse(stringData);
    exportHot.loadData(jsonData);
    data.forEach((object,index) => {
        data[index].name = `<a href="#" onclick="return false" data-id="${object.id}" class="detail">${object.name}</a>`
    })
    hot.loadData(data);
}
async function loadSupplierDetail(id){
    const result = await getSupplier(id);
    const data = result.data;
    sw.matchData(data);
}
//선택한 컬럼 아이디에 해당하는 모달 오픈
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
// 테이블 초기화
const container = document.querySelector("#example");

async function setDetailDataToEditModal(id) {
    const result = await getSupplier(id);
    const data = result.data;
    sw.matchData(data)
    sw.matchData({managerName: data.manager.name})
    const formData = new FormData();
    formData.append("supplier.id", id);
    const items = await getItemList(formData);
    const productTable = document.querySelector("#productTable tbody")
    productTable.innerHTML = ""
    let innerHtml = ""
        items.data.forEach((item,index) => {
            innerHtml += `
            <tr>
                <td>
                    ${index+1}
                </td>
                <td>${item.product.name}</td>
                <td>${item.product.standard}</td>
                <td>${item.product.unit.name}</td>
                <td>${item.contractPrice}</td>
            </tr>
        `
    })
    await productTable.insertAdjacentHTML("beforebegin", innerHtml)
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

const myCheckboxRenderer = checkboxRenderer(({checked, instance, td, row, col})=>{
    if(checked){
        selectedRowId = instance.getDataAtCell(row,1);
    } else {
        selectedRowId = null
    }
})
const tableOptions = {
    data:[],
    colHeaders : ["","ID","거래처명","대표자명", "전화", "등록일", "등록자"],
    columns : [
        {renderer:myCheckboxRenderer},
        {data:"id"},
        {data:"name", renderer:"html"},
        {data:"ownerName"},
        {data:"contactNumber"},
        {data:"contractDate"},
        {data:"manager.name"}
    ],
    colWidths : scaleArrayToSum(Array(7),1130),
    height:"50vh",
}
const hot = handsontable(container, tableOptions);
hot.addHook("afterRender", function () {
    addNameEventListener();
})

//수정
const editSubmitButton = document.getElementById('editSubmitButton');
editSubmitButton.addEventListener('click', async function () {
    if(confirm("수정하시겠습니까?")){
        const editForm = document.getElementById("editForm");
        const formData = new FormData(editForm);
        const result = await updateSupplier(formData);
        alert(result.message)
        if(result.status == "OK"){
            await searchSupplier();
        }
    }
})


//추가
const addSubmitButton = document.getElementById('addSubmitButton');
addSubmitButton.addEventListener('click', async function () {
    if(confirm("추가하시겠습니까?")){
        const addForm = document.getElementById("addForm");
        const formData = new FormData(addForm);
        const result = await addSupplier(formData);
        const id = formData.get("id");
        await searchSupplier();
        alert(result.message);
        if(result.status === "OK"){
            registerModal.hide();
        }
    }
})



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
        filename: '거래처_[YYYY]-[MM]-[DD]',
        mimeType: 'text/csv',
        rowDelimiter: '\r\n',
        rowHeaders: false,
        range:[0,1]
    });
})