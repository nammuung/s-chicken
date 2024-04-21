import {checkboxRenderer, handsontable, scaleArrayToSum} from "../lib/handsontable.js";
import {addProduct, getProduct, getProductList, updateProduct} from "../api/product.js";

searchProduct()
sw.init()
//품목 검색
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", async function () {
    searchProduct();
})
async function searchProduct(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    const result = await getProductList(formData);
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

//디테일 모달
async function editModalShow(id){
    const result = await getProduct(id);
    const data = result.data;
    sw.matchData(data)
    const categoty = document.getElementById("category");
    Array.prototype.slice.call(categoty)
        .forEach(option=>{
            if(option.value === data.categoryId){
                option.selected = true;
            }
        })
    editModal.show();
}
// 테이블 초기화
const container = document.getElementById('example')
const myCheckboxRenderer = checkboxRenderer(()=>{
    console.log("Hello")
})
const tableOptions = {
    data:[],
    colHeaders : ['','ID','카테고리', '품명', '규격'],
    columns : [
        {renderer:myCheckboxRenderer},
        {data:"id"},
        {data:"category"},
        {data:"name", renderer:"html"},
        {data:"standard"},
    ],
    colWidths : scaleArrayToSum(Array(5)),
}
const hot = handsontable(container, tableOptions);


//모달
const registerModalEl = document.getElementById("register-modal")
const editModalEl = document.getElementById("edit-modal")
const registerModal = new bootstrap.Modal(registerModalEl);
const editModal = new bootstrap.Modal(editModalEl);


//품목 추가
const addButton = document.getElementById("addButton");
addButton.addEventListener("click", async function(){
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
const editButton = document.getElementById("editButton");
editButton.addEventListener("click", async function(){
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
