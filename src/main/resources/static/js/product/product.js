const searchButton = document.getElementById("searchButton");
const detailForm = document.getElementById("detailForm");


searchButton.addEventListener("click", function () {
    searchProduct()
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
const container = document.querySelector('#example');
const hot = new Handsontable(container, {
    data:[],
    columns: [
        {
            renderer: checkboxRenderer,
            manualColumnResize: false
        },
        {
            data:'id',

        },
        {
            data:'category',

        },
        {
            data:'name',
        },
        {
            data:'standard',
            autoColumnSize: true
        }
    ],
    className: 'htCenter htMiddle',
    readOnly: true,
    // rowHeaders: true,
    height: '50vh',
    autoWrapRow: true,
    autoWrapCol: true,
    width: "100%",
    colWidths:[22,25,100,100,100],
    colHeaders: ['','ID','카테고리', '품명', '규격'],
    stretchH:"all",
    licenseKey: 'non-commercial-and-evaluation', // for non-commercial use only
    // manualColumnResize: true,
});
// Handsontable의 사용자 지정 셀 렌더러 정의
function checkboxRenderer(instance, td, row, col, prop, value, cellProperties) {
    var checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = value === true;
    checkbox.style.width="20px";
    checkbox.style.height="20px";
    checkbox.style.marginTop="4px";
    checkbox.addEventListener('change', function(event) {
        var checked = event.target.checked;

        for(let i = 0; i < instance.countRows(); i++) {
            instance.setDataAtCell(i,0,false)
        }
        instance.setDataAtCell(row, col, checked);
        const id = instance.getDataAtCell(row, 1);
        detailForm.reset();
        if (checked){
            loadProductDetail(id);
        }

    });

    Handsontable.dom.empty(td);
    td.appendChild(checkbox);
}
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
    console.log(data);
    modifyProduct(data)
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