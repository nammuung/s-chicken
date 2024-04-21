import {getSupplierList, getSupplier, updateSupplier, addSupplier} from "../api/supplier.js";
import {handsontable, checkboxRenderer, scaleArrayToSum} from "../lib/handsontable.js";

const registerModalEl = document.getElementById("register-modal")
const editModalEl = document.getElementById("edit-modal")
const registerModal = new bootstrap.Modal(registerModalEl);
const editModal = new bootstrap.Modal(editModalEl);

sw.init()


const searchButton = document.getElementById("searchButton");

searchSupplier();
searchButton.addEventListener("click", function () {
    searchSupplier();
})
async function searchSupplier(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    const result = await getSupplierList(formData);
    const data = result.data;
    data.forEach((object,index) => {
        data[index].name = `<a href="#" onclick="return false" data-id="${object.id}" class="detail">${object.name}</a>`
    })
    hot.loadData(data);
    [...container.querySelectorAll("a.detail")].forEach(
        el=>el.addEventListener("click", function (){
            editModalShow(el.dataset.id);
        })
    )
}
async function loadSupplierDetail(id){
    const result = await getSupplier(id);
    const data = result.data;
    sw.matchData(data);
}
//선택한 컬럼 아이디에 해당하는 모달 오픈
function editModalShow(id) {
    loadSupplierDetail(id);
    editModal.show();
}

function registerModalShow(){
    registerModal.show();
}
// 테이블 초기화
const container = document.querySelector("#example");
const myCheckboxRenderer = checkboxRenderer(()=>{
    console.log("Hello")
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
        {data:"managerName"}
    ],
    colWidths : scaleArrayToSum(Array(7)),
}
const hot = handsontable(container, tableOptions);


//수정
const editButton = document.getElementById('editButton');
editButton.addEventListener('click', async function () {
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
const addButton = document.getElementById('addButton');
addButton.addEventListener('click', async function () {
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


