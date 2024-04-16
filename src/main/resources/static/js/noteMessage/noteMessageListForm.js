let noteMessageDatas;
let noteMessageSaveBtn;
let noteMessageDeleteBtn;
let noteMessagePagination;

let noteMessageBody;
let listPage;
let openMessage;
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
    return `<small class="linkable">${names[0]}</small><div>${names[1]} ${names[2]}</div>`
}

function contentSetForList(text){
    return text.replace(/<br>/g,"\n").replace(/<[^>]*>?/g, '');
}

function drawNoteMessageTr(data){
    return `
        <div class="d-flex border-1 border-bottom mt-2">
            <div class="col-1 px-3 text-center form-check">
                <input data-id="note-message-select" data-content="${data.id}" type="checkbox" class="form-check-input" style="margin-left: 1em; margin-top: 0.5em">
            </div>
            <div data-sender-id="${data.senderId}" class="col-2 px-3 text-center linkable text-black">${senderNameSetting(data.senderName)}</div>
            <div data-content="${data.id}" class="col-6 px-3 text-center text-truncate linkable text-black">${contentSetForList(data.content)}</div>
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

function getNoteMessageList(page, type){
    let param = "?page="+page;

    fetch('/message/getList/' + type + param)
        .then(res => res.json())
        .then(r => {
            noteMessageDatas.innerHTML=drawNoteMessageTableRows(r.data);
            toggleSaveAndDeleteBtnByCheckbox();
            document.querySelectorAll("div[data-content]")
                .forEach(div => div.addEventListener("click", evt => openMessage(evt.target.getAttribute("data-content"), page)));
            noteMessagePagination.innerHTML=setPaginaion(r.page);
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

function moveBox(to, page){
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
        if(res.ok) openListPage(page);

        else alert('box를 옮기는데 실패했습니다.');
    })
}


function openListPage(page){
    noteMessageBody.innerHTML = listPage;

    noteMessageDatas = document.getElementById("note-message-datas");
    noteMessageSaveBtn = document.getElementById("note-message-save-btn");
    noteMessageDeleteBtn = document.getElementById("note-message-delete-btn");
    noteMessagePagination = document.getElementById("note-message-pagination");

    noteMessageSaveBtn.addEventListener("click",()=>moveBox('save', page))
    noteMessageDeleteBtn.addEventListener("click",()=>moveBox('delete', page))
    noteMessagePagination.addEventListener("click",event=>{
        let page = event.target.getAttribute("data-to-page");
        if(page == null) return;

        openListPage(page);
    })

    getNoteMessageList(page == null ? 1 : page, 'receive');
}

export default {
    set : setPage,
    open : openListPage
}
