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

let noteMessageFrom;
let noteMessageDate;
let noteMessageEditor;
let noteMessageAttach;

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

            listForm.set(noteMessageBody, page[0], openRead);
            sendForm.set(noteMessageBody, page[1]);
            pages.read = page[2];
        })
}


//============================================ 모달 페이지 전환 ==========================================================

function openRead(messageId){
    noteMessageBody.innerHTML = pages.read;

    noteMessageFrom = document.getElementById("note-message-from");
    noteMessageDate = document.getElementById("note-message-date");
    noteMessageEditor = document.getElementById("note-message-editor");
    noteMessageAttach = document.getElementById("note-message-attach");

    fetch(`/message/getMessage?id=${messageId}`)
        .then(res=>res.json())
        .then(r => {
            noteMessageFrom.innerHTML = `<h5>${r.senderName}</h5>`;
            noteMessageDate.innerHTML = r.date;
            noteMessageEditor.value = r.content;
            noteMessageAttach.innerHTML = r.filename;

            ClassicEditor.create(document.getElementById("note-message-editor"), {toolbar:[]})
                .then(editor => {
                    editor.enableReadOnlyMode("note-message-editor");
                    editor.editing.view.change(writer => {
                        writer.setStyle('height', '300px', editor.editing.view.document.getRoot());
                        writer.setStyle('overflow', 'auto', editor.editing.view.document.getRoot());
                    });
                })
        });
}


//========================================== 이벤트 리스너 등록 ==========================================================
noteMessageSendFormBtn.addEventListener("click", sendForm.open)
noteMessageReceiveBoxBtn.addEventListener("click", listForm.open)

noteMessageNav.addEventListener("click", () => {
    listForm.open();
    bsNoteMessageModal.show();
})
