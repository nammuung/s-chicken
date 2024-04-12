import noteMessage from "/js/noteMessage/noteMessage.js";
import oc from "/js/orgChart/orgChart.js";

let noteMessageNav = document.getElementById("note-message-nav");
let noteMessageModal = document.getElementById("note-message-modal");
let bsNoteMessageModal = new bootstrap.Modal(noteMessageModal);
let noteMessageBody = document.getElementById("note-message-body");
let sendMessageBtn = document.getElementById("send-message-btn");
let noteMessageSelectedList;
let addReceiversBtn;
let removeReceiversBtn;

let listPage;
let sendPage;
let readPage;

window.onload=()=>{
    fetch("/html/noteMessage/noteMessageTemplate.html")
        .then(res=>res.text())
        .then(r=>{
            const pages = r.split("^^^^^^^^^^");

            listPage = pages[0];
            sendPage = pages[1];
            readPage = pages[2];
        })
}

let selectedItems = {};
let depAndEmp;
function onSelectOrgChart({id, type}) {
    console.log("note-message-select", id, type)

    let opt = {
        dept : `?deptId=${id}`,
        person : `?empId=${id}`
    }[type];

    if(opt != null){
        fetch('/organization/employees' + opt)
            .then(res => res.json())
            .then(r => {
                depAndEmp = [];
                for(let emps of r){
                    depAndEmp.push({
                        id : emps.id,
                        name : emps.text,
                        sort : emps.sort
                    });
                }
            })
    }
}

function drawSelectedList(){
    let result = [];

    for (let key in selectedItems) {
        const li = `<li data-id="${selectedItems[key].id}" class="list-group-item text-center bg-schicken-light-hover">${selectedItems[key].name}</li>`;
        result.push(li);
    }

    result.sort();

    return result;
}

function addReceivers(){
    for (let element of depAndEmp) {
        selectedItems[element.id] = element;
    }

    noteMessageSelectedList.innerHTML = drawSelectedList().join("");

    depAndEmp = [];
}

let selectedToDelete = {};
function selectItemToDelete(event){
    if(event.target.tagName !== 'LI'){
        return;
    }

    const id = event.target.getAttribute("data-id");

    if(selectedToDelete[id] != null){
        event.target.classList.remove("selected");
        delete selectedToDelete[id];
    } else {
        event.target.classList.add("selected");
        selectedToDelete[id] = id;
    }

    console.log(selectedToDelete);
}

function deleteSelectedItems(){
    for (let key in selectedToDelete) {
        delete selectedItems[key];
    }

    selectedToDelete = {};

    noteMessageSelectedList.innerHTML = drawSelectedList().join("");
}

//============================================ 모달 페이지 전환 ==========================================================


function openListPage(){
    noteMessageBody.innerHTML = listPage;
}

function openSendPage() {
    noteMessageBody.innerHTML = sendPage;
    selectedItems = {};
    depAndEmp = [];
    addReceiversBtn = document.getElementById("add-receivers-btn");
    noteMessageSelectedList = document.getElementById("note-message-selected-list");
    removeReceiversBtn = document.getElementById("remove-receivers-btn");
    noteMessageSelectedList.addEventListener("click", selectItemToDelete);
    addReceiversBtn.addEventListener("click", addReceivers);
    removeReceiversBtn.addEventListener("click", deleteSelectedItems);
    oc.init("note-message-org-chart", onSelectOrgChart, '', false, { checkbox: true, type : 'person' });
}


//========================================== 이벤트 리스너 등록 ==========================================================
sendMessageBtn.addEventListener("click", ()=>{
    openSendPage();
})

noteMessageNav.addEventListener("click", () => {
    openListPage();
    bsNoteMessageModal.show();
})
