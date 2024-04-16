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
let noteMessageBackList;
let noteMessageReplyBtn;

let pages = {
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

function readFormDateFormat(date){
    let year = date.substring(0,4);
    let month = date.substring(4,6);
    let day = date.substring(6,8);
    let hour = date.substring(8,10);
    let minute = date.substring(10,12);
    let second = date.substring(12,14);

    return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}


//============================================ 모달 페이지 전환 ==========================================================

function openRead(messageId, page){
    noteMessageBody.innerHTML = pages.read;

    noteMessageFrom = document.getElementById("note-message-from");
    noteMessageDate = document.getElementById("note-message-date");
    noteMessageEditor = document.getElementById("note-message-editor");
    noteMessageAttach = document.getElementById("note-message-attach");
    noteMessageBackList = document.getElementById("note-message-back-list");
    noteMessageReplyBtn = document.getElementById("note-message-reply-btn");

    noteMessageBackList.addEventListener("click", ()=> listForm.open(page))
    noteMessageReplyBtn.addEventListener("click", event=> sendForm.reply(event.target.getAttribute("data-sender-id")))

    fetch(`/message/getMessage?id=${messageId}`)
        .then(res=>res.json())
        .then(r => {
            noteMessageFrom.innerHTML = `<h5 class="linkable text-black">${r.senderName}</h5>`;
            noteMessageDate.innerHTML = `<p>${readFormDateFormat(r.date)}</p>`;
            noteMessageEditor.value = r.content;
            noteMessageAttach.innerHTML = `
                <a href='/fileDown?id=${r.file}'>
                    <i class='fas fa-save fa-lg anchorable' style='color: #7749F8;margin-top: 0.5em'></i>
                    ${r.filename}
                </a>`;

            noteMessageReplyBtn.setAttribute("data-sender-id", r.senderId);

            ClassicEditor.create(document.getElementById("note-message-editor"), {toolbar:[]})
                .then(editor => {
                    editor.enableReadOnlyMode("note-message-editor");
                    editor.editing.view.change(writer => {
                        writer.setStyle('height', '300px', editor.editing.view.document.getRoot());
                        writer.setStyle('overflow', 'auto', editor.editing.view.document.getRoot());
                        writer.setStyle('border-radius', '0.5em', editor.editing.view.document.getRoot());
                    });
                    editor.ui.view.toolbar.element.style.display = "none";
                }).then(()=>document.getElementById("note-message-editor").classList.remove("d-none"))
        });
}


//========================================== 이벤트 리스너 등록 ==========================================================
noteMessageSendFormBtn.addEventListener("click", sendForm.open)
noteMessageReceiveBoxBtn.addEventListener("click", ()=>listForm.open())

noteMessageNav.addEventListener("click", () => {
    listForm.open();
    bsNoteMessageModal.show();
})
