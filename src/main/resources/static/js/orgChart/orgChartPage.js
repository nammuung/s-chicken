import oc from "/js/orgChart/orgChart.js";

const deptAddBtn = document.getElementById("dept-add-btn");
const deptModBtn = document.getElementById("dept-mod-btn");
const deptModal = document.getElementById("dept-modal");
const bsDeptModal = new bootstrap.Modal(deptModal);
const deptSubmitBtn = document.getElementById("dept-submit-btn");
const upperName = document.getElementById("upper-name");
const deptName = document.getElementById("dept-name");
const deptNumber = document.getElementById("dept-number");
const conNumNotice = document.getElementById("con-num-notice");
let selected;

function createOrgChart(){
    oc.init("org-chart", data=>{
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
        } else {
            document.querySelectorAll(".dept-btn")
                .forEach(e=>e.classList.add("disabled"));
        }
    });
}

function setModalAddDept(data){
    upperName.innerText = data.name;
    upperName.value = data.id;
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
    console.log("submit" , type)

    if(type === 'add' && selected.depth >= 3){
        alert('이 노드에는 추가할 수 없습니다.');
        return;
    }

    let data = {
        name :deptName.value,
        contactNumber : deptNumber.value,
        upperId : upperName.value
    }

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
            console.log(r);
            /* r은 id가 생성된 department 데이터 */
            data.id = r.id;
            oc.create(data);
            bsDeptModal.hide();
        })

}

deptName.addEventListener("keyup",e=>{
    if(e.target.value.length === 0){
        deptSubmitBtn.classList.add("disabled");
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

    fetch('/department/checkContactNumber?contactNumber=' + e.target.value)
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

deptAddBtn.addEventListener("click",()=>{
    if(selected == null){
        return;
    }
    fetch('/department/getDepartment?id=' + selected.id)
        .then(res=>res.json())
        .then(r=>{
            console.log(r);
            setModalAddDept(r);
            deptName.value = null;
            deptNumber.value = null;
            deptSubmitBtn.classList.add("disabled");
            bsDeptModal.show();
            deptSubmitBtn.addEventListener("click", ()=>deptSubmit('add'), { once : true });
        });
})

createOrgChart();
