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
const deptDelBtn = document.getElementById("dept-del-btn");
// 조직도에서 클릭한 Object
let selected;
// department list
let deptData;
// 부서 수정시 선택된 부서의 원래 내선번호
let exceptNumber;

/**
 * 부서의 리스트를 가져와 deptData에 저장
 */
function getDepartmentData(){
    fetch('/department/list')
        .then(res=>res.json())
        .then(r=>{
            console.log(r);
            deptData = r;
        });
}

/**
 * 조직도를 클릭하면 호출될 함수
 * selected에 선택된 데이터를 저장하고 선택된 개체에따라 버튼의 disabled를 수정
 */
function ocCallbackFunction(data) {
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

/**
 * 부서 삭제버튼 눌렀을 때 실행될 함수
 * 기본적으로 비어있고, 삭제가 가능한 상황에만 함수를 채워넣음
  */
let deptDelete = () => {};

/**
 * 하위에 부서 추가 버튼 클릭시 실행
 * 모달을 생성하고 띄워줌
 */
function setModalAddDept(data){
    document.getElementById("dept-modal-title").innerText = '부서 등록'
    upperName.setAttribute("data-id", data.id);
    upperName.innerHTML = `
        <div class="form-control bg-secondary-light">${data.name}</div>
    `

    deptList.classList.add("d-none");

    deptName.value = null;
    deptNumber.value = null;

    deptSubmitBtn.classList.add("disabled");

    exceptNumber = null;

    deptDelBtn.classList.add("d-none");

    deptDelete = ()=>{};
}

/**
 * 부서 수정시에 특정 상위 부서의 하위부서를 보여주는데 사용함
 * 하위부서들을 li 태그의 리스트로 리턴
 * 선택된 부서는 상위부서에 속해있으면 원래자리
 * 그렇지 않으면 제일 아래에 표시함
 */
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
            if(id == e.id) {
                isId = true;

                li.classList.add("bg-schicken-light");
                li.classList.remove("filtered");
                li.id = "target-li";

                i = document.createElement("i");
                i.classList.add(...("bi bi-arrows-vertical".split(" ")));
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

/**
 * 부서 수정 버튼 클릭시 실행
 * 모달을 생성하고 띄워줌
 * 부서 삭제가 가능한경우 여기서 deptDelete 함수를 채워줌
 */
function setModalUpdateDept(data){
    document.getElementById("dept-modal-title").innerText = '부서 수정'
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
        ghostClass : "ghost",
        animation : 150,
        onEnd : setDataSort
    });

    deptName.value = data.name;
    deptNumber.value = data.contactNumber;

    deptSubmitBtn.classList.remove("disabled");
    exceptNumber = data.contactNumber;

    if(selected.children.length === 0){
        deptDelBtn.classList.remove("d-none");
        deptDelete = ()=>{
            fetch('/department/deleteDepartment',{
                method : 'post',
                headers : {
                    'content-type' : "application/json;charset=utf-8"
                },
                body : JSON.stringify({id : selected.id})
            }).then(res=>res.json())
                .then(r=>{
                    deptData = r;
                    bsDeptModal.hide();
                    oc.refresh();
                });
        }
    } else {
        deptDelBtn.classList.add("d-none");
        deptDelete = () =>{};
    }
}

/**
 * 부서의 정렬순서를 정할 sort값을 계산해줌
 */
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

/**
 * type이 add 거나 update에 따라 호출하고
 * 선택된 데이터를 서버로 보내 적용하는 함수
 */
function deptSubmit(type){
    if(type !== 'add' && type !== 'update'){
        console.log(`deptSubmit 의 type은 'add' 나 'update' 만 가능 : `, type);
        return;
    }

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

/**
 * deptSubmit함수를 실행해주는 함수
 * 클릭한 버튼이 추가, 수정 인지에 따라 add, update 적용하는 함수를 실행함
 */
let callSubmitFunction;

/**
 * 부서를 선택하고, 추가또는 수정버튼을 클릭하면 모달을 세팅하고 띄워줌
 * callSubmitFunction을 여기서 정의해줌
 */
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


deptAddBtn?.addEventListener("click",()=>onDeptBtnClick('add'))
deptModBtn?.addEventListener("click",()=>onDeptBtnClick('update'))
deptSubmitBtn?.addEventListener("click", ()=>callSubmitFunction());
deptDelBtn?.addEventListener("click", ()=>deptDelete());

oc.init("org-chart", data=>ocCallbackFunction(data),'',true);
getDepartmentData();
