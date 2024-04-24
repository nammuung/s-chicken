import sendForm from '/js/noteMessage/noteMessageSendForm.js'
import listForm from '/js/noteMessage/noteMessageListForm.js'
import readForm from '/js/noteMessage/noteMessageReadForm.js'

let noteMessageNav = document.getElementById("note-message-nav");
let noteMessageBody = document.getElementById("note-message-body");
let noteMessageModal = document.getElementById("note-message-modal");
let bsNoteMessageModal = new bootstrap.Modal(noteMessageModal);

let noteMessageSendFormBtn = document.getElementById("note-message-send-form-btn");
let noteMessageReceiveBoxBtn = document.getElementById("note-message-receive-box-btn");
let noteMessageSaveBoxBtn = document.getElementById("note-message-save-box-btn");
let noteMessageSendBoxBtn = document.getElementById("note-message-send-box-btn");
let noteMessageDeleteBoxBtn = document.getElementById("note-message-delete-box-btn");

window.onload=()=>{
    fetch("/html/noteMessage/noteMessageTemplate.html")
        .then(res=>res.text())
        .then(r=>{
            const page = r.split("^^^^^^^^^^");

            listForm.set(noteMessageBody, page[0], readForm.openRead);
            sendForm.set(noteMessageBody, page[1]);
            readForm.set(noteMessageBody, page[2]);
        })
}

document.getElementById("chatting-popup-btn").addEventListener("click",()=>{
    let url = "/chatrooms/popup";
    let attr = "_blank"
    let opt = {
        width : 500,
        height : 800,
        top : 50,
        left : 100,
        scrollbars : "no",
        location : "no",
        toolbar : "no",
        status : "no"
    }

    let optStr = "";

    for (const key in opt) {
        optStr += key + " = " + opt[key] + ", "
    }

    let popup = window.open(url, attr, optStr);
    console.log(popup);
})
//========================================== 이벤트 리스너 등록 ==========================================================
noteMessageSendFormBtn.addEventListener("click", sendForm.open);
noteMessageReceiveBoxBtn.addEventListener("click", ()=>listForm.open(1, 'receive'));
noteMessageSaveBoxBtn.addEventListener("click", ()=>listForm.open(1,'save'));
noteMessageDeleteBoxBtn.addEventListener("click", ()=>listForm.open(1,'delete'));
noteMessageSendBoxBtn.addEventListener("click", ()=>listForm.open(1,'send'));
noteMessageNav.addEventListener("click", () => {
    listForm.open(1,'receive');
    bsNoteMessageModal.show();
});

export const openNoteMessage = (link) => {
    readForm.openRead(link, 1, 'receive');
    bsNoteMessageModal.show();
}
