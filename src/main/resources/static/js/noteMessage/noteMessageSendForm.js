import oc from "/js/orgChart/orgChart.js";

let noteMessageSelectedList;
let addReceiversBtn;
let removeReceiversBtn;
let selectCompleteBtn;
let noteMessageReceiversList;
let noteMessageForm;
let noteMessageReceiverInput;
let noteMessageSubmitBtn;
let noteMessageTextArea;

let noteMessageBody;
let sendPage;
function setPage(body, page){
    noteMessageBody = body;
    sendPage = page;
}

let selectedItems = {};
let depAndEmp;
function onSelectOrgChart({id, type}, callback) {
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
            .then(()=>{
                if(callback != null){
                    callback();
                }
            })
    }
}

function getNoteMessageElement(type, element){
    return {
        list : `
    <li data-id="${element.id}" 
        class="list-group-item text-center bg-schicken-light-hover ${selectedToDelete[element.id] == null? "" :"selected"} ">
            ${element.name}
    </li>
`,
        div : `
        <div class="note-message-receiver-item text-center">
                ${element.name}
        </div>
        `
    }[type];
}

function drawSelectedElement(type){
    let result = [];

    for (let key in selectedItems) {
        const element = getNoteMessageElement(type, selectedItems[key]);
        result.push(element);
    }

    return result;
}

function getNoteMessageSelectedId(){
    return Object.values(selectedItems).map(i => i.id).join(",");
}

function addReceivers(){
    for (let element of depAndEmp) {
        selectedItems[element.id] = element;
    }

    noteMessageSelectedList.innerHTML = drawSelectedElement('list').join("");
    noteMessageReceiversList.innerHTML = drawSelectedElement('div').join("");
    noteMessageReceiverInput.value = getNoteMessageSelectedId();

    depAndEmp = [];

    if(Object.keys(selectedItems).length !== 0){
        selectCompleteBtn.classList.remove("disabled");
    } else {
        selectCompleteBtn.classList.add("disabled");
    }
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

    if(Object.keys(selectedItems).length !== 0){
        selectCompleteBtn.classList.remove("disabled");
    } else {
        selectCompleteBtn.classList.add("disabled");
    }

    noteMessageSelectedList.innerHTML = drawSelectedElement('list').join("");
    noteMessageReceiversList.innerHTML = drawSelectedElement('div').join("");
    noteMessageReceiverInput.value = getNoteMessageSelectedId();
}

function noteMessageSubmit(){
    let data = new FormData(noteMessageForm);
    fetch('/message/sendMessage',{
        method:'post',
        body : data
    }).then(res=>res.text())
        .then(r=> {
            alert(r)
            if(r === "쪽지를 보냈습니다."){
                /* 보낸 쪽지함으로 이동 */
                document.getElementById("note-message-send-box-btn").click();
            }
        })
}

let noteMessageCheckBoolean;
function noteMessageCheck(event){
    let text = event.target.value;

    document.getElementById("note-message-content-count").innerText = text.length;
    if(text.length > 0 && noteMessageCheckBoolean){
        return;
    }

    if(text.length === 0){
        noteMessageCheckBoolean = false;
        noteMessageSubmitBtn.classList.add("disabled");
        return;
    }

    noteMessageCheckBoolean = true;
    noteMessageSubmitBtn.classList.remove("disabled");
}

function replyTo(receiver){
    openSendPage();
    onSelectOrgChart({
        id : receiver,
        type : "person"
    }, ()=>{
        addReceivers();
        document.getElementById("note-message-select-complete-btn").click();
    })
}

function openSendPage() {
    noteMessageBody.innerHTML = sendPage;
    selectedItems = {};
    depAndEmp = [];
    noteMessageCheckBoolean = false;

    addReceiversBtn = document.getElementById("add-receivers-btn");
    noteMessageSelectedList = document.getElementById("note-message-selected-list");
    removeReceiversBtn = document.getElementById("remove-receivers-btn");
    selectCompleteBtn = document.getElementById("note-message-select-complete-btn");
    noteMessageReceiversList = document.getElementById("note-message-receivers-list");
    noteMessageForm = document.getElementById("note-message-form");
    noteMessageReceiverInput = document.getElementById("note-message-receiver-input");
    noteMessageSubmitBtn = document.getElementById("note-message-submit-btn");
    noteMessageTextArea = document.getElementById("note-message-textarea");

    addReceiversBtn.addEventListener("click", (e)=>addReceivers(e));
    noteMessageSelectedList.addEventListener("click", (e)=>selectItemToDelete(e));
    removeReceiversBtn.addEventListener("click", (e)=>deleteSelectedItems(e));
    noteMessageSubmitBtn.addEventListener("click", (e)=>noteMessageSubmit(e));
    noteMessageTextArea.addEventListener("keyup", (e)=>noteMessageCheck(e));

    oc.init("note-message-org-chart", onSelectOrgChart, '', false, { checkbox: true, type : 'person' });
}


export default {
    set : setPage,
    open : openSendPage,
    reply : replyTo
}
