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

function drawReceiverItem(datas){
    let divs = "";

    datas.forEach(data => {
        divs += `
            <li class="list-group-item linkable text-black text-center">
                ${data.name}
        </li>
    `
    })

    return divs;
}

function drawReceiverList(data){
    if(data.length === 1)
        return `<h5 class="linkable text-black">${data[0].name}</h5>`;

    return `
<div class="accordion" id="note-message-receivers-accordion">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button collapsed py-1" type="button" data-bs-toggle="collapse" data-bs-target="#note-message-receivers-name-list" aria-expanded="false" aria-controls="note-message-receivers-name-list">
        받은 사람 리스트
      </button>
    </h2>
    <div id="note-message-receivers-name-list" class="accordion-collapse collapse" data-bs-parent="#note-message-receivers-accordion">
      <div class="accordion-body list-group p-0">
       ${drawReceiverItem(data)}
       </div>
    </div>
  </div>
</div>
    `
}

function openRead(messageId, page, type){
    noteMessageBody.innerHTML = pages.read;

    noteMessageFrom = document.getElementById("note-message-from");
    noteMessageDate = document.getElementById("note-message-date");
    noteMessageEditor = document.getElementById("note-message-editor");
    noteMessageAttach = document.getElementById("note-message-attach");
    noteMessageBackList = document.getElementById("note-message-back-list");
    noteMessageReplyBtn = document.getElementById("note-message-reply-btn");

    noteMessageBackList.addEventListener("click", ()=> listForm.open(page, type))

    if(type === 'send'){
        noteMessageReplyBtn.classList.add("d-none");
    } else {
        noteMessageReplyBtn.addEventListener("click", event=> sendForm.reply(event.target.getAttribute("data-sender-id")))
    }

    document.getElementById("note-message-detail-title").innerText = type === 'send' ? "To" : "From";


    fetch(`/message/getMessage?id=${messageId}&type=${type}`)
        .then(res=>res.json())
        .then(r => {
            if(type === 'send'){
                noteMessageFrom.innerHTML = drawReceiverList(r.receiversVO);
            } else {
                noteMessageFrom.innerHTML = `<h5 class="linkable text-black">${r.senderName}</h5>`;
            }
            noteMessageDate.innerHTML = `<p>${readFormDateFormat(r.date)}</p>`;
            noteMessageEditor.value = r.content;
            if(r.file != null) {
                noteMessageAttach.innerHTML = `
                <a href='/fileDown?id=${r.file}'>
                    <i class='fas fa-save fa-lg anchorable' style='color: #7749F8;margin-top: 0.5em'></i>
                    ${r.filename}
                </a>`;
            }

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
noteMessageSendFormBtn.addEventListener("click", sendForm.open);
noteMessageReceiveBoxBtn.addEventListener("click", ()=>listForm.open(1, 'receive'));
noteMessageSaveBoxBtn.addEventListener("click", ()=>listForm.open(1,'save'));
noteMessageDeleteBoxBtn.addEventListener("click", ()=>listForm.open(1,'delete'));
noteMessageSendBoxBtn.addEventListener("click", ()=>listForm.open(1,'send'));

noteMessageNav.addEventListener("click", () => {
    listForm.open(1,'receive');
    bsNoteMessageModal.show();
});
