import sendForm from '/js/noteMessage/noteMessageSendForm.js'

let noteMessageNav = document.getElementById("note-message-nav");
let noteMessageBody = document.getElementById("note-message-body");
let noteMessageModal = document.getElementById("note-message-modal");
let bsNoteMessageModal = new bootstrap.Modal(noteMessageModal);

let noteMessageSendFormBtn = document.getElementById("note-message-send-form-btn");
let noteMessageReceiveBoxBtn = document.getElementById("note-message-receive-box-btn");
let noteMessageSaveBoxBtn = document.getElementById("note-message-save-box-btn");
let noteMessageSendBoxBtn = document.getElementById("note-message-send-box-btn");
let noteMessageDeleteBoxBtn = document.getElementById("note-message-delete-box-btn");

let noteMessageDatas;

let pages = {
    list : '',
    send : '',
    read : ''
}

window.onload=()=>{
    fetch("/html/noteMessage/noteMessageTemplate.html")
        .then(res=>res.text())
        .then(r=>{
            const page = r.split("^^^^^^^^^^");

            pages.list = page[0];
            sendForm.set(noteMessageBody, page[1]);
            pages.read = page[2];
        })
}

function getToday(){
    let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    month = (month >= 10 ? "" : "0") + month;
    let date = now.getDate();

    return '' + year + month + date;
}

function dateFormatting(date){
    let year = date.substring(0,4);
    let month = date.substring(4,6);
    let day = date.substring(6,8);

    if(date.substring(0,8) !== getToday()){
        return year + "." + month + "." + day;
    }

    let hour = date.substring(8,10);
    let minute = date.substring(10,12);

    return hour + ':' + minute;
}

function senderNameSetting(senderName){
    let names = senderName.split(" ");
    return `<small class="linkable">${names[0]}</small><div>${names[1]} ${names[2]}</div>`
}

function drawNoteMessageTr(data){
    return `
<div class="d-flex border-1 border-bottom mt-2">
        <div class="col-1 px-3 text-center form-check">
            <input type="checkbox" class="form-check-input" style="margin-left: 1em; margin-top: 0.5em">
        </div>
        <div class="col-2 px-3 text-center linkable text-black">${senderNameSetting(data.senderName)}</div>
        <div class="col-6 px-3 text-center text-truncate linkable text-black">${data.content}</div>
        <div class="col-1 px-3 text-center"></div>
        <div class="col-2 px-3 text-center">${dateFormatting(data.date)}</div>
        </div>
    `
}

function drawNoteMessageTableRows(data){
    return data.map(d => drawNoteMessageTr(d)).join("");
}

function getNoteMessageList(page){
    let param = page == null? "?page=1" : "?page="+page;

    fetch('/message/getList' + param)
        .then(res => res.json())
        .then(r => {
            noteMessageDatas.innerHTML=drawNoteMessageTableRows(r);
        });
}


//============================================ 모달 페이지 전환 ==========================================================


function openListPage(){
    noteMessageBody.innerHTML = pages['list'];

    noteMessageDatas = document.getElementById("note-message-datas");

    getNoteMessageList(0);
}



//========================================== 이벤트 리스너 등록 ==========================================================
noteMessageSendFormBtn.addEventListener("click", sendForm.open)
noteMessageReceiveBoxBtn.addEventListener("click", ()=>openListPage())

noteMessageNav.addEventListener("click", () => {
    openListPage();
    bsNoteMessageModal.show();
})
