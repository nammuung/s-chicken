let noteMessageDatas;
let noteMessageSaveBtn;
let noteMessageDeleteBtn;
let noteMessageExceptBtn;
let noteMessageDeleteAcceptBtn;

let noteMessagePagination;
let noteMessageBtnDiv;

let noteMessageBody;
let listPage;
let openMessage;

let nowBox;

let listTitle =  {
        'receive' : '받은 쪽지함',
        'save' : '쪽지 보관함',
        'delete' : '휴지통',
        'send' : '보낸 쪽지함'
    }
function setPage(body, page, readFunc){
    noteMessageBody = body;
    listPage = page;
    openMessage = readFunc;
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
    let name = "";
    for (let i = 1; i < names.length; i++) {
        name += names[i] + " ";
    }

    return `<small class="linkable">${names[0]}</small><div>${name}</div>`
}

function contentSetForList(text){
    return text.replace(/<br>/g,"\n").replace(/<[^>]*>?/g, '');
}

function drawNoteMessageTr(data, type){
    return `
        <div class="d-flex border-1 border-bottom mt-2">
            <div class="col-1 px-3 text-center form-check">
                <input data-id="note-message-select" data-content="${data.id}" type="checkbox" class="form-check-input ${type === 'send' ? "d-none" : ""}" style="margin-left: 1em; margin-top: 0.5em">
            </div>
            <div onclick="onProfileClick('${data.senderId}')" data-sender-id="${data.senderId}" class="col-3 px-3 text-center linkable text-black hovercursor">${senderNameSetting(data.senderName)}</div>
            <div data-content="${data.id}" class="col-5 px-3 text-center text-truncate linkable text-black hovercursor">${contentSetForList(data.content)}</div>
            <div class="col-1 px-3 text-center">
                ${data.file == null ? "" : `<a href='/fileDown?id=${data.file}'><i class='fas fa-save fa-lg anchorable' style='color: #7749F8;margin-top: 0.5em'></i></a>`}
            </div>
            <div class="col-2 px-3 text-center">${dateFormatting(data.date)}</div>
        </div>
    `
}

function drawNoteMessageTableRows(data, type){
    if(data.length === 0){
        return `<div class="d-flex justify-content-center border-1 border-bottom mt-2">쪽지가 없습니다.</div>`
    }

    return data.map(d => drawNoteMessageTr(d, type)).join("");
}

function setPaginaion(pager){
    let paginaion = "";

    if(pager.page-3 > 0){
        paginaion += `<li class="page-item"><a class="page-link" data-to-page="${pager.page-3}"> < </a></li>`;
    }

    for (let i = Math.max(1,pager.page-2); i <= Math.min(pager.totalPage,pager.page+2); i++) {
        paginaion += `<li class="page-item"><a class="page-link ${pager.page == i ? "now-page" : ""}"  data-to-page="${i}">${i}</a></li>`;
    }

    if(pager.totalPage >= pager.page + 3){
        paginaion += `<li class="page-item"><a class="page-link" data-to-page="${pager.page+3}"> > </a></li>`;
    }

    return paginaion
}

function setBtnByMessageList(page, type){
    noteMessageBtnDiv.innerHTML ={
        'receive' : `            
            <button id="note-message-save-btn" data-kind='note-message-util-btn' class="btn btn-primary btn-sm disabled ms-1 align-self-start">보관</button>
            <button id="note-message-delete-btn" data-kind='note-message-util-btn' class="btn btn-outline-danger btn-sm disabled ms-1 align-self-start">삭제</button>`,
        'save' : `
            <button id="note-message-except-btn" data-kind='note-message-util-btn' class="btn btn-outline-secondary btn-sm disabled ms-1 align-self-start">제외</button>`,
        'delete' : `
            <button id="note-message-delete-accept-btn" data-kind='note-message-util-btn' class="btn btn-danger btn-sm disabled ms-1 align-self-start">완전삭제</button>
            <button id="note-message-except-btn" data-kind='note-message-util-btn' class="btn btn-outline-secondary btn-sm disabled ms-1 align-self-start">제외</button>`,
        'send' : ``
    }[type];

    switch (type) {
        case "receive" :
            noteMessageSaveBtn = document.getElementById("note-message-save-btn");
            noteMessageDeleteBtn = document.getElementById("note-message-delete-btn");
            noteMessageSaveBtn.addEventListener("click", () => moveBox('save'));
            noteMessageDeleteBtn.addEventListener("click", () => moveBox('delete'));
            break;
        case "save" :
            noteMessageExceptBtn = document.getElementById("note-message-except-btn");
            noteMessageExceptBtn.addEventListener("click", () => moveBox('receive'));
            break;
        case "delete" :
            noteMessageExceptBtn = document.getElementById("note-message-except-btn");
            noteMessageDeleteAcceptBtn = document.getElementById("note-message-delete-accept-btn");
            noteMessageExceptBtn.addEventListener("click", () => moveBox('receive'));
            noteMessageDeleteAcceptBtn.addEventListener("click", () => deleteMessageComplete());
            break;
    }
}

function deleteMessageComplete(){
    let checkboxes = [...document.querySelectorAll('input[data-id="note-message-select"][data-content]')]
        .filter(e => e.checked)
        .map(e=>e.getAttribute("data-content"));


    fetch('/message/delete',{
        method:"post",
        headers : {
            "content-type": "application/json;charset=utf-8"
        },
        body : JSON.stringify(checkboxes)
    }).then(async res => {
        if(res.ok) {
            alert(await res.text());
            getNoteMessageList(1, 'delete');
        }

        else alert('삭제하는데 실패했습니다.');
    })
}

function getNoteMessageList(page, type){
    let param = "?page="+page;

    nowBox = type

    setBtnByMessageList(page, type);

    document.getElementById("note-message-list-title").innerText = listTitle[type];

    fetch('/message/getList/' + type + param)
        .then(res => res.json())
        .then(r => {
            noteMessageDatas.innerHTML=drawNoteMessageTableRows(r.data, type);
            toggleMessageUtilBtnsByCheckbox();
            document.querySelectorAll("div[data-content]")
                .forEach(div => div.addEventListener("click", evt => openMessage(evt.target.getAttribute("data-content"), page, type)));
            noteMessagePagination.innerHTML=setPaginaion(r.page);
        });
}

function toggleMessageUtilBtnsByCheckbox(){
    let selCount = 0;
    document.querySelectorAll("input[data-id='note-message-select']")
        .forEach(ipt =>ipt.addEventListener("click",e => {
            selCount += e.target.checked? 1 : -1;

            if(selCount <= 0){
                document.querySelectorAll("button[data-kind='note-message-util-btn']")
                    .forEach(e=>e.classList.add("disabled"))
            } else {
                document.querySelectorAll("button[data-kind='note-message-util-btn']")
                    .forEach(e=>e.classList.remove("disabled"))
            }
        }))
}

function moveBox(to){
    let checkboxes = [...document.querySelectorAll('input[data-id="note-message-select"][data-content]')]
        .filter(e => e.checked)
        .map(e=>e.getAttribute("data-content"));


    fetch('/message/moveBox/' + to,{
        method:"post",
        headers : {
            "content-type": "application/json;charset=utf-8"
        },
        body : JSON.stringify(checkboxes)
    }).then(res => {
        if(res.ok) {
            alert(`쪽지를 ${listTitle[to]}으로 이동했습니다.`);
            getNoteMessageList(1, nowBox);
        }

        else alert('box를 옮기는데 실패했습니다.');
    })
}


function openListPage(page, type){
    noteMessageBody.innerHTML = listPage;

    noteMessageDatas = document.getElementById("note-message-datas");
    noteMessagePagination = document.getElementById("note-message-pagination");
    noteMessageBtnDiv = document.getElementById("note-message-btn-div");

    noteMessagePagination.addEventListener("click",event=>{
        let page = event.target.getAttribute("data-to-page");
        if(page == null) return;

        getNoteMessageList(page,type);
    })

    getNoteMessageList(page == null ? 1 : page, type);
}

export default {
    set : setPage,
    open : openListPage
}
