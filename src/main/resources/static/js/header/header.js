import sendForm from '/js/noteMessage/noteMessageSendForm.js'
import listForm from '/js/noteMessage/noteMessageListForm.js'

let noteMessageNav = document.getElementById("note-message-nav");
let noteMessageBody = document.getElementById("note-message-body");
let noteMessageModal = document.getElementById("note-message-modal");
let bsNoteMessageModal = new bootstrap.Modal(noteMessageModal);

let noteMessageSendFormBtn = document.getElementById("note-message-send-form-btn");
let noteMessageReceiveBoxBtn = document.getElementById("note-message-receive-box-btn");
let noteMessageSaveBoxBtn = document.getElementById("note-message-save-box-btn");
let noteMessageSendBoxBtn = document.getElementById("note-message-send-box-btn");
let noteMessageDeleteBoxBtn = document.getElementById("note-message-delete-box-btn");


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

            listForm.set(noteMessageBody, page[0]);
            sendForm.set(noteMessageBody, page[1]);
            pages.read = page[2];
        })
}


//============================================ 모달 페이지 전환 ==========================================================




//========================================== 이벤트 리스너 등록 ==========================================================
noteMessageSendFormBtn.addEventListener("click", sendForm.open)
noteMessageReceiveBoxBtn.addEventListener("click", listForm.open)

noteMessageNav.addEventListener("click", () => {
    listForm.open();
    bsNoteMessageModal.show();
})
