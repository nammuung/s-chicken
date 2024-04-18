import handsontable from "/js/lib/handsontable.js";

const searchButton = document.getElementById("searchButton");
const detailForm = document.getElementById("detailForm");


searchButton.addEventListener("click", function () {
    searchProduct()
    modifyButton.classList.add("d-none")
    detailForm.reset()
})
// let data
async function searchProduct(){
    const searchForm = document.getElementById("searchForm");
    const formData = new FormData(searchForm);
    const searchData = {
        "id": formData.get("id"),
        "name": formData.get("name"),
        "standard": formData.get("standard"),
        "categoryId": formData.get("categoryId"),
    }
    const response = await fetch(`/v1/api/products?id=${searchData.id}&name=${searchData.name}&standard=${searchData.standard}&categoryId=${searchData.categoryId}`);
    const result = await response.json();
    const data = result.data;
    renderTable(data);
}
function renderTable(data){
    hot.loadData(data)
}

const options = {
    useCheckbox: true,
    checkboxSize:30,
    colHeaders:['ID','카테고리', '품명', '규격'],
    dataKeys:['id','category','name','standard'],
    colWidths: [2,10,10,20],
    containerWidth: 500,
}
const callbacks = {
    checkbox: ({checked, instance, td, row, col})=>{
        const id = instance.getDataAtCell(row, 1);
        detailForm.reset();
        if (checked){
            modifyButton.classList.remove("d-none")
            loadProductDetail(id);
        } else {
            modifyButton.classList.add("d-none")
        }
    }
}
console.log("모듈진입")
let hot = handsontable("#example", options, [], callbacks);
console.log("모듈탈출")
async function loadProductDetail(id){
    const response = await fetch("/v1/api/products/"+id);
    const result = await response.json();
    const data = result.data;
    dataSetterWithId(data);
    const categoty = document.getElementById("category");
    Array.prototype.slice.call(categoty)
        .forEach(option=>{
            if(option.value === data.categoryId){
                option.selected = true;
            }
        })
}

const modalEl = document.getElementById("common-modal")
const modal = new bootstrap.Modal(modalEl);
const addButton = document.getElementById("addButton");
addButton.addEventListener("click",function (){
    modal.show()
})

const saveButton = document.getElementById("saveButton");
saveButton.addEventListener("click", function(){
    const addForm = document.getElementById("addForm");
    const formData = new FormData(addForm);
    const name = formData.get("name");
    const standard = formData.get("standard");
    const categoryId = formData.get("categoryId");
    const data = {
        "name": name,
        "standard": standard,
        "categoryId": categoryId
    }
    saveProduct(data)
    detailForm.reset()
})

async function saveProduct(data){
    const response = await fetch("/v1/api/products", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
    const result = await response.json();
    alert(result.message);
    if (result.status === "OK") {
        modal.hide()
    }
}

const modifyButton = document.getElementById("modifyButton");
modifyButton.addEventListener("click", function(){
    const detailForm = document.getElementById("detailForm");
    const formData = new FormData(detailForm);
    const id = formData.get("id");
    const name = formData.get("name");
    const standard = formData.get("standard");
    const categoryId = formData.get("categoryId");
    const data = {
        "id": id,
        "name": name,
        "standard": standard,
        "categoryId": categoryId
    }
    if(confirm("수정 하시겠습니까?")){
        modifyProduct(data)
    }
})

async function modifyProduct(data){
    const response = await fetch("/v1/api/products", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
    const result = await response.json();
    alert(result.message);
    if(result.status === "OK"){
        searchProduct()
    }
}

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
