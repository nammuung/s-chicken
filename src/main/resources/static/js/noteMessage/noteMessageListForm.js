let noteMessageDatas;
let noteMessageSaveBtn;
let noteMessageDeleteBtn;

let noteMessageBody;
let listPage;
function setPage(body, page){
    noteMessageBody = body;
    listPage = page;
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
        <div data-note-message="${data.id}" class="d-flex border-1 border-bottom mt-2">
            <div class="col-1 px-3 text-center form-check">
                <input data-id="note-message-select" type="checkbox" class="form-check-input" style="margin-left: 1em; margin-top: 0.5em">
            </div>
            <div data-sender-id="${data.senderId}" class="col-2 px-3 text-center linkable text-black">${senderNameSetting(data.senderName)}</div>
            <div class="col-6 px-3 text-center text-truncate linkable text-black">${data.content}</div>
            <div class="col-1 px-3 text-center">
                ${data.file == null ? "" : `<a href='/fileDown?id=${data.file}'><i class='fas fa-save fa-lg anchorable' style='color: #7749F8;margin-top: 0.5em'></i></a>`}
            </div>
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
            toggleSaveAndDeleteBtnByCheckbox();
        });
}

function toggleSaveAndDeleteBtnByCheckbox(){
    let selCount = 0;
    document.querySelectorAll("input[data-id='note-message-select']")
        .forEach(ipt =>ipt.addEventListener("click",e => {
            selCount += e.target.checked? 1 : -1;

            if(selCount <= 0){
                noteMessageSaveBtn.classList.add("disabled");
                noteMessageDeleteBtn.classList.add("disabled");
            } else {
                noteMessageSaveBtn.classList.remove("disabled");
                noteMessageDeleteBtn.classList.remove("disabled");
            }
        }))
}


function openListPage(){
    noteMessageBody.innerHTML = listPage;

    noteMessageDatas = document.getElementById("note-message-datas");
    noteMessageSaveBtn = document.getElementById("note-message-save-btn");
    noteMessageDeleteBtn = document.getElementById("note-message-delete-btn");

    noteMessageSaveBtn.addEventListener("click",()=>{})
    noteMessageDeleteBtn.addEventListener("click",()=>{})

    getNoteMessageList(0);
}

export default {
    set : setPage,
    open : openListPage
}
