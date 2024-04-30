import {setWhenReceiveMessage, sendMessage} from '/js/chatting/chatting.js';

const chattingSpace = document.getElementById("chatting-space");
const chattingArea = document.getElementById("chatting-area");
const sendMessageBtn = document.getElementById("send-message-btn");

let memberData = {};
let lastChatting;
let beginChatting;
let nowOpenPage = "";
let nowPageType = "";
let upEnd;
let downEnd;

let options = {
    threshold: 0,
};

const infinityScrollObserver = new IntersectionObserver(scrollPagingObserveCallback, options);
function scrollPagingObserveCallback(entries, observer) {
    entries.forEach(async (entry) => {
        if (entry.isIntersecting) {
            observer.unobserve(entry.target);
            let direction = entry.target.dataset.direction;
            let tempHeight = chattingSpace.scrollHeight + 1;

            getChatting(entry.target.dataset.sendDate, direction).then(() => {
                if(direction === 'up') chattingSpace.scrollTop = chattingSpace.scrollHeight - tempHeight;
                }
            );
        }
    });
};

document.getElementById("search-input").addEventListener("keyup", event => {
    let searchName = event.target.value;
    const regex = new RegExp(searchName);

    document.querySelectorAll("[data-search-name]").forEach(e => {
        let name = e.dataset.searchName;
        if (regex.test(name)) {
            e.classList.remove("d-none");
            document.getElementById(e.dataset.parentId).classList.remove("d-none");
        } else {
            e.classList.add("d-none");
            let elements = document.querySelectorAll(`[data-parent-id=${e.dataset.parentId}]`);
            for (let ele of elements) {
                if (!ele.classList.contains("d-none")) {
                    return;
                }
            }

            document.getElementById(e.dataset.parentId).classList.add("d-none");
        }
    })
})

function openChatting(event, type) {
    const targetId = event.target.dataset.targetId;

    pageChange();
    namecardModal.hide();
    document.getElementById("chatting-page-btn").click();
    nowOpenPage = targetId;
    nowPageType = type;


    setChatroom(targetId).then(()=>{
        setTimeout(()=>{
            document.querySelector("[data-last-read]")?.scrollIntoView({block: "center"})
        }, 500)
    })

}

async function setChatroom(targetId) {
    let option = {
        'one': `/one/${targetId}`,
        'many': `/many/${targetId}`
    }[nowPageType];

    if (option == null) return;

    let chattingData = await fetch('/chatrooms/getData' + option).then(res => res.json());

    memberData = {};
    chattingSpace.innerHTML = "";
    chattingArea.value = "";
    lastChatting = null;
    beginChatting = null;
    upEnd = false;
    downEnd = false;

    document.getElementById("chatroom-name").innerText = chattingData.chatroomName;
    chattingData.members.forEach(member => memberData[member.id] = member);
    chattingData.chatMessages.forEach(chatMessage => {
        const created = appendChatting(chatMessage);

        if (chattingData.lastReadId === chatMessage.id) {
            created.dataset.lastRead = "";
        }
    });

    observeUpAndDown();
}

function observeUpAndDown(opt) {
    let chattings = chattingSpace.querySelectorAll("[data-send-date]");
    if (chattings.length !== 0) {
        chattings[0].dataset.direction = "up";
        chattings[chattings.length - 1].dataset.direction = "down";

        if ((opt == null) || (opt.direction === 'up' && !opt.isEnd)) {
            infinityScrollObserver.observe(chattings[0]);
        } else {
            upEnd = true;
        }
        if ((opt == null) || (opt.direction === 'down' && !opt.isEnd)) {
            infinityScrollObserver.observe(chattings[chattings.length - 1]);
            downEnd = true;
        }
    }
}

async function getChatting(from, direction) {
    let option = nowPageType === 'one' ? [document.querySelector("[data-logined-id]")?.dataset.loginedId, nowOpenPage].sort().join("") : nowOpenPage

    return fetch(`/chatrooms/chattings/${option}?from=${from}&direction=${direction}`)
        .then(res => res.json())
        .then(chattings => {
            if (direction === 'up') {
                chattings.forEach(chatting => prependChatting(chatting));
            } else if (direction === 'down') {
                chattings.forEach(chatting => appendChatting(chatting));
            }
            observeUpAndDown({direction, isEnd: chattings.length < 10});
        });
}

function prependChatting(data) {
    if (beginChatting == null || beginChatting.dataset.senderId !== data.senderId) {
        beginChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.prepend(beginChatting);
    }

    const messageSpace = beginChatting.querySelector(".chatting-message-space");
    const created = createChattingMessage(data.content, data.sendDate);
    messageSpace.prepend(created);

    return created
}

function appendChatting(data) {
    if (lastChatting == null || lastChatting.dataset.senderId !== data.senderId) {
        lastChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.append(lastChatting);
    }

    const messageSpace = lastChatting.querySelector(".chatting-message-space");
    const created = createChattingMessage(data.content, data.sendDate);
    messageSpace.append(created);

    return created;
}

function createChattingProfile(data, sendDate) {
    console.log("data : ", data);
    let div = makeElement("div", {className: ["chatting", "d-flex", "mt-2", "ms-1"], dataset: {"senderId": data.id}});
    let div2 = makeElement("div", {className: ["chatting-profile-img"]});

    let imgOption = {
        src: data.profileImg,
        width: "55px",
        height: "55px"
    }

    let img = makeElement("img", {className: ["rounded-circle"], option: imgOption})
    img.addEventListener("click", () => onProfileClick(data.id))

    div2.append(img);
    div.append(div2);

    let div3 = makeElement("div", {className: ["chatting-content"]});
    let div4 = makeElement("div", {dataset: {"sendInfo": ""}});
    let span = makeElement("span", {className: ["me-2", "linkable", "text-black", "hovercursor"]});
    span.innerText = data.name;
    span.addEventListener("click", () => onProfileClick(data.id))
    let span2 = makeElement("span", {className: ["small", "text-secondary"]});
    span2.innerText = chatDateFormat(sendDate);

    let div5 = makeElement("div", {className: ["chatting-message-space"]});

    div4.append(span, span2);
    div3.append(div4, div5);

    div.append(div3);

    return div;
}

function chatDateFormat(date) {
    let year = date.substring(0, 4);
    let month = date.substring(4, 6);
    let day = date.substring(6, 8);
    let hour = date.substring(8, 10);
    let minute = date.substring(10, 12);
    let afternoon;

    if (Number(hour) >= 12) {
        afternoon = "오후";
        hour = Number(hour) % 12 + 1;
    } else {
        afternoon = "오전";
    }

    return year + "-" + month + "-" + day + " " + afternoon + " " + hour + ":" + minute;
}

function createChattingMessage(data, sendDate) {
    let div = makeElement("div", {className: ["mt-1", "p-2", "rounded-3", "text-break", "bg-schicken-light", "d-inline-block"]})
    div.innerHTML = data;

    let div2 = makeElement("div", {dataset: {"sendMessage": "", "sendDate": sendDate}});
    div2.append(div);

    return div2;
}

function makeElement(tagName, {className, option, dataset} = {}) {
    let element = document.createElement(tagName);

    if (className != null) element.classList.add(...className);

    for (let key in option) {
        element.setAttribute(key, option[key]);
    }

    for (let key in dataset) {
        element.dataset[key] = dataset[key];
    }

    return element;
}

function pageChange(to) {
    infinityScrollObserver.disconnect();
    [...document.getElementsByClassName("now-page")].forEach(e => e.classList.remove("now-page"));

    if (to == null) return;

    to.classList.add("now-page");
}

function onSendMessageBtnClick() {
    const chatMsg = chattingArea.value;
    if (chatMsg.length === 0) return;

    let data = {
        chatroomId: nowOpenPage,
        type: "Message",
        pageType: nowPageType,
        content: chatMsg
    };

    sendMessage(data);

    chattingArea.value = "";
}

setWhenReceiveMessage(appendChatting);

sendMessageBtn.addEventListener("click", onSendMessageBtnClick);
document.querySelector("a[data-profile-type=chatting]").addEventListener("click", event => openChatting(event, 'one'))
document.getElementById("employee-list-btn").addEventListener("click", event => pageChange(event.target))
document.getElementById("chatroom-list-btn").addEventListener("click", event => pageChange(event.target))
