import {setWhenReceiveMessage, sendMessage} from '/js/chatting/chatting.js';

const chattingSpace = document.getElementById("chatting-space");
const chattingArea = document.getElementById("chatting-area");
const sendMessageBtn = document.getElementById("send-message-btn");

let memberData = {};
let lastChatting;
let beginChatting;
let nowOpenPage = "";
let nowPageType = "";


let options = {
    threshold: 0,
};
let $end;
const callback = (entries, observer) => {
    entries.forEach(async (entry) => {
        if (entry.isIntersecting) {
            const $result = document.querySelector("#messageBox");
            observer.unobserve($end);
            const loadingElement = document.createElement("div");
            loadingElement.classList.add(
                "d-flex",
                "flex-row",
                "justify-content-center"
            );
            const loadingHtml = `
            <div class="spinner-border mb-3 mt-1" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            `;

            loadingElement.innerHTML = loadingHtml;
            $result.insertAdjacentElement("afterbegin", loadingElement);
            loadingElementHeight = loadingElement.offsetHeight;
            const data = await getMessage();
            loadingElement.remove();
            let messageElements = new DocumentFragment();
            for (let message of data.messages) {
                $result.prepend();
                messageElements.appendChild(
                    showMessage(message, messageElements)
                );
            }
            const tempHeight = messageBox.scrollHeight;
            $result.insertBefore(messageElements, $result.firstChild);
            $end = $result.firstElementChild;
            messageBox.scrollTop =
                messageBox.scrollHeight - tempHeight - loadingElementHeight;
            if (data.messages.length < 20) return;
            observer.observe($end);
        }
    });
};

const observer = new IntersectionObserver(callback, options);


document.getElementById("search-input").addEventListener("keyup", event=>{
    let searchName = event.target.value;
    const regex = new RegExp(searchName);

    document.querySelectorAll("[data-search-name]").forEach(e => {
        let name = e.dataset.searchName;
        if(regex.test(name)){
            e.classList.remove("d-none");
            document.getElementById(e.dataset.parentId).classList.remove("d-none");
        } else {
            e.classList.add("d-none");
            let elements = document.querySelectorAll(`[data-parent-id=${e.dataset.parentId}]`);
            for (let ele of elements) {
                if(!ele.classList.contains("d-none")){
                    return;
                }
            }

            document.getElementById(e.dataset.parentId).classList.add("d-none");
        }
    })
})

function openChatting(event, type){
    const targetId = event.target.dataset.targetId;

    pageChange();
    namecardModal.hide();
    document.getElementById("chatting-page-btn").click()
    nowOpenPage=targetId;
    nowPageType = type;

    setChatroom(targetId);
}

async function setChatroom(targetId) {
    let option = {
        'one' : `/one/${targetId}`,
        'many' : `/many/${targetId}`
    }[nowPageType];

    if(option == null) return;

    let chattingData = await fetch('/chatrooms/getData' + option).then(res => res.json());

    memberData = {};
    chattingSpace.innerHTML = "";
    chattingArea.value = "";
    lastChatting = null;
    beginChatting = null;
    chattingData.members.forEach(member => memberData[member.id] = member);
    console.log("memberData : " , memberData);

    chattingData.chatMessages.forEach(chatMessage => appendChatting(chatMessage));
}

function getChatting(from, direction){
    let option = nowPageType === 'one' ? [document.querySelector("[data-logined-id]")?.dataset.loginedId, nowOpenPage].sort().join("") : nowOpenPage

    fetch(`/chatrooms/chattings/${option}?from=${from}&direction=${direction}`)
        .then(res=>res.json())
        .then(r=>console.log(r));
}

function prependChatting(data){
    if(beginChatting == null || beginChatting.dataset.senderId !== data.senderId){
        beginChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.prepend(beginChatting);
    }

    const messageSpace = chattingSpace.firstElementChild.querySelector(".chatting-content");
    messageSpace.prepend(createChattingMessage(data.content));
}

function appendChatting(data, noScroll){
    if(lastChatting == null || lastChatting.dataset.senderId !== data.senderId){
        lastChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.append(lastChatting);
    }

    const messageSpace = chattingSpace.lastElementChild.querySelector(".chatting-content");
    messageSpace.append(createChattingMessage(data.content));


    if(!noScroll && chattingSpace.scrollTop !== chattingSpace.scrollHeight)
        chattingSpace.scrollTop = chattingSpace.scrollHeight;
}

function createChattingProfile(data, sendDate){
    let div = makeElement("div", {className : ["chatting", "d-flex", "mt-2", "ms-1"], dataset:{"senderId" : data.id}});
    let div2 = makeElement("div", {className : ["chatting-profile-img"]});

    let imgOption = {
        src : data.profileImg,
        width : "55px",
        height : "55px"
    }

    let img = makeElement("img", {className : ["rounded-circle"], option : imgOption})
    img.addEventListener("click", ()=> onProfileClick(data.id))

    div2.append(img);
    div.append(div2);


    let div3 = makeElement("div", {className : ["chatting-content"]});
    let div4 = makeElement("div", {dataset : {"sendInfo" : ""}});
    let span = makeElement("span", {className : ["me-2", "linkable", "text-black", "hovercursor"]});
    span.innerText = data.name;
    span.addEventListener("click", ()=> onProfileClick(data.id))
    let span2 = makeElement("span", {className : ["small", "text-secondary"]});
    span2.innerText = chatDateFormat(sendDate);

    div4.append(span, span2);
    div3.append(div4);

    div.append(div3);

    return div;
}

function chatDateFormat(date){
    let year = date.substring(0,4);
    let month = date.substring(4,6);
    let day = date.substring(6,8);
    let hour = date.substring(8,10);
    let minute = date.substring(10,12);
    let afternoon;

    if(Number(hour) >= 12){
        afternoon = "오후";
        hour = Number(hour)%12 + 1;
    } else {
        afternoon = "오전";
    }

    return year + "-" + month + "-" + day + " " + afternoon + " " + hour + ":" + minute;
}

function createChattingMessage(data){
    let div = makeElement("div", {className : ["mt-1", "p-2", "rounded-3", "text-break", "bg-schicken-light", "d-inline-block"], dataset : {"sendMessage" : ""}})
    div.innerText = data;

    let div2 = makeElement("div");
    div2.append(div);

    return div2;
}

function makeElement(tagName ,{className, option, dataset} = {}){
    let element = document.createElement(tagName);

    if(className != null) element.classList.add(...className);

    for (let key in option) {
        element.setAttribute(key, option[key]);
    }

    for (let key in dataset) {
        element.dataset[key] = dataset[key];
    }

    return element;
}

function pageChange(to){
    [...document.getElementsByClassName("now-page")].forEach(e => e.classList.remove("now-page"));

    if(to == null) return;

    to.classList.add("now-page");
}

function onSendMessageBtnClick(){
    const chatMsg = chattingArea.value;
    if(chatMsg.length === 0) return;

    let data = {
        chatroomId : nowOpenPage,
        type : "Message",
        pageType : nowPageType,
        content : chatMsg
    };

    sendMessage(data);

    chattingArea.value = "";
}

setWhenReceiveMessage(appendChatting);

sendMessageBtn.addEventListener("click", onSendMessageBtnClick);
document.querySelector("a[data-profile-type=chatting]").addEventListener("click", event=>openChatting(event, 'one'))
document.getElementById("employee-list-btn").addEventListener("click",event=>pageChange(event.target))
document.getElementById("chatroom-list-btn").addEventListener("click",event=>pageChange(event.target))
