import oc from "/js/orgChart/orgChart.js";

const deptAddBtn = document.getElementById("dept-add-btn");
const deptModBtn = document.getElementById("dept-mod-btn");
const deptModal = document.getElementById("dept-modal");
const bsDeptModal = new bootstrap.Modal(deptModal);
const deptSubmitBtn = document.getElementById("dept-submit-btn");
const upperName = document.getElementById("upper-name");
const deptName = document.getElementById("dept-name");
const deptNumber = document.getElementById("dept-number");
const deptList = document.getElementById("dept-list");
const conNumNotice = document.getElementById("con-num-notice");
let selected;
let deptData;
let exceptNumber;

function getDepartmentData(){
    fetch('/department/list')
        .then(res=>res.json())
        .then(r=>{
            console.log(r);
            deptData = r;
        });
}

function ocInitFunction(data) {
    console.log(data);
    if(data.isSelect){
        selected = data;
    } else {
        selected = null;
    }
    if(selected != null && data.type === 'dept') {
        if(data.depth < 3){
            document.querySelectorAll(".dept-btn")
                .forEach(e=>e.classList.remove("disabled"));
        } else {
            deptModBtn.classList.remove("disabled");
            deptAddBtn.classList.add("disabled");
        }

        if(data.id == 1){
            deptModBtn.classList.add("disabled");
        }
    } else {
        document.querySelectorAll(".dept-btn")
            .forEach(e=>e.classList.add("disabled"));
    }
}

function setModalAddDept(data){
    upperName.setAttribute("data-id", data.id);
    upperName.innerHTML = `
        <div class="form-control bg-secondary-light">${data.name}</div>
    `

    deptList.classList.add("d-none");

    deptName.value = null;
    deptNumber.value = null;

    deptSubmitBtn.classList.add("disabled");

    exceptNumber = null;
}

function getDepartmentLis({id, upperId}){
    console.log("getDepartmentLis", id ,upperId);
    let isId = false;

    let arr = deptData
        .filter(d => d.upperId == upperId)
        .sort((a,b) => a.sort - b.sort)
        .map(e=>{
            let li = document.createElement("li");
            let i;

            li.classList.add("list-group-item","d-flex","justify-content-between", "filtered");
            li.setAttribute("data-sort", e.sort);
            if(id == e.id){
                isId = true;

                li.classList.add("bg-schicken-light");
                li.classList.remove("filtered");
                li.id="target-li";

                i = document.createElement("i");
                i.classList.add("fas","fa-bars");
                i.id = "drag-icon";
                i.draggable = true;
            }

            let div = document.createElement("div");
            div.classList.add("content-name");
            div.innerText = e.name;

            li.append(div);
            if(i != null) li.append(i);

            return li;
        })

    if(!isId){
        let idData = deptData.filter(d => d.id == id)[0];
        console.log("idData", idData);
        let li = document.createElement("li");
        li.classList.add("list-group-item","d-flex","justify-content-between");
        li.classList.add("bg-schicken-light");
        li.setAttribute("data-sort",idData.sort);
        li.id="target-li";

        let div = document.createElement("div");
        div.classList.add("content-name");
        div.innerText = idData.name;

        let i = document.createElement("i");
        i.classList.add("fas","fa-bars");
        i.id = "drag-icon";
        i.draggable = true;

        li.append(div, i);

        arr.push(li);
    }

    return arr;
}

function setModalUpdateDept(data){
    console.log(deptData)
    let options = deptData
        .filter(d => d.upperId == null || d.upperId == 1)
        .map(e=> `<option value="${e.id}" ${e.id == data.upperId? "selected" : ""}>${e.name}</option>`)
        .join();

    upperName.setAttribute("data-id", data.upperId);
    upperName.innerHTML = `
        <select class="form-select">
            ${options}
        </select>
    `

    deptList.classList.remove("d-none");
    deptList.innerHTML = "";
    deptList.append(...getDepartmentLis(data));

    upperName.querySelector("select.form-select").addEventListener("change",e=>{
        console.log(e.target.value);
        deptList.innerHTML = "";
        upperName.setAttribute("data-id", e.target.value);
        deptList.append(...getDepartmentLis({"id" : data.id,"upperId" : e.target.value}));
        setDataSort();
    })

    $(deptList).sortable({
        filter : ".filtered",
        animation : 50,
        onEnd : setDataSort
    });

    deptName.value = data.name;
    deptNumber.value = data.contactNumber;

    deptSubmitBtn.classList.remove("disabled");
    exceptNumber = data.contactNumber;
}

function setDataSort(){
    const target = deptList.querySelector(".bg-schicken-light");
    target.setAttribute("data-sort",
        target.previousElementSibling == null ? '0' : parseInt(target.previousElementSibling.getAttribute("data-sort")) + 1);
}

/**
 * 부서명, 내선번호, 상위부서가 null 인지 체크
 * 생성하려면 부서의 depth 가 3 이하인지 확인
 * 사람의 아래에 생성하는 것은 아닌지 확인
 */
function validateDept(data){
    if(data.name == null || data.name === ''){
        alert('부서명이 올바르지 않습니다.')
        return false;
    }

    if(data.contactNumber == null || data.contactNumber.length !==4){
        alert('부서 내선번호가 올바르지 않습니다.')
        return false;
    }

    if(data.upperId == null){
        alert('상위 부서가 있어야합니다.')
        return false;
    }

    return true;
}

function deptSubmit(type){
    if(type==='add' && selected.depth >= 3){
        alert('이 노드에는 추가할 수 없습니다.');
        return;
    }

    let data = {
        id : selected.id,
        name :deptName.value,
        contactNumber : deptNumber.value,
        upperId : upperName.getAttribute("data-id")
    }

    if(type == 'update'){
        data.sort = deptList.querySelector("li.bg-schicken-light").getAttribute("data-sort");
    }

    console.log("data = ", data);

    if(!validateDept(data)){
        return;
    }

    fetch(`/department/${type}Department`, {
        method : 'post',
        headers : {
            'content-type' : 'application/json;charset=utf-8'
        },
        body : JSON.stringify(data)
    }).then(res => res.json())
        .then(r => {
            /* r은 department 리스트 */
            oc.refresh();
            bsDeptModal.hide();
            deptData = r;
        })

}

let callSubmitFunction;
function onDeptBtnClick(type) {
    if(selected == null){
        return;
    }
    fetch('/department/getDepartment?id=' + selected.id)
        .then(res=>res.json())
        .then(r=>{
            console.log(r);
            if(type === 'add') setModalAddDept(r);
            if(type === 'update') setModalUpdateDept(r);
            deptNumber.classList.remove("border-danger");
            deptNumber.classList.remove("border-success");
            conNumNotice.classList.add("d-none");
            bsDeptModal.show();
            callSubmitFunction = ()=>deptSubmit(type);
        });
}


deptName.addEventListener("keyup",e=>{
    if(e.target.value.length === 0){
        deptSubmitBtn.classList.add("disabled");
        return;
    }

    const targetLi = document.getElementById("target-li");
    if(targetLi != null){
        targetLi.querySelector("div").innerText = e.target.value;
        return;
    }

    if(deptNumber.classList.contains("border-success")){
        deptSubmitBtn.classList.remove("disabled");
    } else {
        deptSubmitBtn.classList.add("disabled");
    }
})

deptNumber.addEventListener("keyup", e=>{
    if(e.target.value.length !== 4){
        return;
    }
    let param = `?contactNumber=${e.target.value}`
    if(exceptNumber != null){
        param += `&except=${exceptNumber}`;
    }

    fetch('/department/checkContactNumber' + param)
        .then(res=> res.json())
        .then(r=> {
            if(r){
                e.target.classList.remove("border-danger");
                e.target.classList.add("border-success");
                conNumNotice.classList.add("d-none");
                if(deptName.value.length === 0){
                    deptSubmitBtn.classList.add("disabled");
                }
                else {
                    deptSubmitBtn.classList.remove("disabled");
                }
            } else {
                e.target.classList.remove("border-success");
                e.target.classList.add("border-danger");
                conNumNotice.classList.remove("d-none");
                deptSubmitBtn.classList.add("disabled");
            }
        });
})


deptAddBtn.addEventListener("click",()=>onDeptBtnClick('add'))
deptModBtn.addEventListener("click",()=>onDeptBtnClick('update'))
deptSubmitBtn.addEventListener("click", ()=>callSubmitFunction());

oc.init("org-chart", data=>ocInitFunction(data));
getDepartmentData();
