import noteMessage from "/js/noteMessage/noteMessage.js";
import oc from "/js/orgChart/orgChart.js";

let noteMessageNav = document.getElementById("note-message-nav");
let noteMessageModal = document.getElementById("note-message-modal");
let bsNoteMessageModal = new bootstrap.Modal(noteMessageModal);
let noteMessageBody = document.getElementById("note-message-body");
let sendMessageBtn = document.getElementById("send-message-btn");

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

function openListPage(){
    noteMessageBody.innerHTML = listPage;
}

function openSendPage() {
    noteMessageBody.innerHTML = sendPage;
    oc.init("note-message-org-chart", data => console.log(data));
}

sendMessageBtn.addEventListener("click", ()=>{
    openSendPage();
})

noteMessageNav.addEventListener("click", () => {
    openListPage();
    bsNoteMessageModal.show();
})
