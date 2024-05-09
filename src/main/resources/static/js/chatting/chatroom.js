import {setWhenReceiveMessage, sendMessage, connectChatroom, disConnectChatroom} from '/js/chatting/chatting.js';

const chattingSpace = document.getElementById("chatting-space");
const chattingArea = document.getElementById("chatting-area");
const hiddenChattingArea = document.getElementById("hidden-chatting-area");
const sendMessageBtn = document.getElementById("send-message-btn");
const chatroomListBtn = document.getElementById("chatroom-list-btn");
const chatroomListSpace = document.getElementById("chatroom-list-space");
const loginedId = document.querySelector("[data-logined-id]")?.dataset.loginedId;
const namecardModal = new bootstrap.Modal(document.getElementById("namecard-modal"));
const chatEmployeeList = document.getElementById("chat-employee-list");
const selectedEmployeeDiv = document.getElementById("selected-employee");
const chatroomInfoModalBtn = document.getElementById("chatroom-info-modal-btn");
const chatroomInfoModal = new bootstrap.Modal(document.getElementById("chatroom-info-modal"));
const chatroomTitle = document.getElementById("chatroom-title");
const chatroomMembers = document.getElementById("chatroom-members");
const memberInviteBtn = document.getElementById("member-invite-btn");
const memberInviteCancelBtn = document.getElementById("member-invite-cancel-btn");
const memberInviteSubmitBtn = document.getElementById("member-invite-submit-btn");
const chatroomOutBtn = document.getElementById("chatroom-out-btn");

let memberData = {};
let lastChatting;
let beginChatting;
let nowOpenPage = "";
let nowPageType = "";
let downEnd;
let oldTitle = "";

let isCreateState = false;
let isInviteState = false;

let chatroomList = {};
let selectedEmployees = {};
let selectedEmployeesUncancel = {};

let options = {
    threshold: 0,
};

const infinityScrollObserver = new IntersectionObserver(scrollPagingObserveCallback, options);

function scrollPagingObserveCallback(entries, observer) {
    entries.forEach((entry) => {
        if (entry.isIntersecting) {
            observer.unobserve(entry.target);

            let direction = entry.target.dataset.direction;
            let tempHeight = chattingSpace.scrollHeight + 1;

            getChatting(entry.target.dataset.sendDate, direction).then(chattings => {
                    if (direction === 'up') chattingSpace.scrollTop = chattingSpace.scrollHeight - tempHeight;
                    observeUpAndDown({direction, isEnd: chattings.length < 10});
                }
            );
        }
    });
};

const downEndObserver = new IntersectionObserver(checkNowDownEnd, options);

function checkNowDownEnd(entries, observer) {
    entries.forEach((entry) => {
        downEnd = entry.isIntersecting
    })
}

document.querySelectorAll("[data-element-id=search-input]").forEach(el => el.addEventListener("keyup", event => {
        let searchName = event.target.value;
        let target = event.target.dataset.target;
        const regex = new RegExp(searchName);

        document.querySelectorAll(target).forEach(e => {
            let name = e.dataset.searchName;
            if (regex.test(name)) {
                e.classList.remove("d-none");
                document.getElementById(e.dataset.parentId)?.classList.remove("d-none");
            } else {
                e.classList.add("d-none");
                let elements = document.querySelectorAll(`[data-parent-id=${e.dataset.parentId}]`);
                for (let ele of elements) {
                    if (!ele.classList.contains("d-none")) {
                        return;
                    }
                }

                document.getElementById(e.dataset.parentId)?.classList.add("d-none");
            }
        })
    })
)

function refreshMemberData(){
    fetch("/chatrooms/memberData/" + nowOpenPage)
        .then(res => res.json())
        .then(r => {
            memberData = {};
            r.forEach(member => memberData[member.id] = member);
        });
}

function openChatting(event, type) {
    const info = event.target.dataset.chatroomInfo;
    if (info == null) {
        event.target.parentElement.click();
    }

    const targetId = event.target.dataset.targetId;
    if (loginedId === targetId) {
        alert("자기자신과는 채팅 할 수 없습니다.")
        return;
    }

    if (type == null) {
        type = event.target.dataset.chatroomType;
        if (type == null) {
            return;
        }
    }

    if(type === 'One'){
        chatroomInfoModalBtn.classList.add("d-none");
    } else {
        chatroomInfoModalBtn.classList.remove("d-none");
    }

    pageChange();
    namecardModal.hide();
    document.getElementById("chatting-page-btn").click();
    nowOpenPage = targetId;
    nowPageType = type;


    setChatroom(targetId).then(() => {
        setTimeout(() => {
            document.querySelector("[data-last-read]")?.scrollIntoView({block: "center"})
        }, 500)
    })

}

async function setChatroom(targetId) {

    let option = {
        'One': `/one/${targetId}`,
        'Many': `/many/${targetId}`
    }[nowPageType];

    if (option == null) return;

    let chattingData = await fetch('/chatrooms/getData' + option).then(res => res.json());

    chatroomRenderByData(chattingData)
}

async function chatroomRenderByData(chattingData) {
    memberData = {};
    chattingSpace.innerHTML = "";
    chattingArea.value = "";
    lastChatting = null;
    beginChatting = null;
    downEnd = false;

    document.getElementById("chatroom-name").innerText = chattingData.chatroomName;
    chattingData.members.forEach(member => memberData[member.id] = member);
    chattingData.chatMessages.forEach(chatMessage => {
        let created;
        if(chatMessage.type === 'Message') created = appendChatting(chatMessage);
        else if(chatMessage.type === 'Join'||chatMessage.type === 'Out') created = appendNotice(chatMessage);

        if (chattingData.lastReadTime === chatMessage.sendDate) {
            created.dataset.lastRead = "";
        }
    });

    beginChatting = document.querySelector("[data-chatting-profile]");

    observeUpAndDown();
}

function observeUpAndDown(opt) {
    let chattings = chattingSpace.querySelectorAll("[data-send-date]");
    if (chattings.length !== 0) {
        chattings[0].dataset.direction = "up";
        chattings[chattings.length - 1].dataset.direction = "down";

        if ((opt == null) || (opt.direction === 'up' && !opt.isEnd)) {
            infinityScrollObserver.observe(chattings[0]);
        }

        if ((opt == null) || (opt.direction === 'down' && !opt.isEnd)) {
            infinityScrollObserver.observe(chattings[chattings.length - 1]);
        } else {
            downEndObserver.observe(chattings[chattings.length - 1]);
        }
    }
}

async function getChatting(from, direction) {
    let option = nowPageType === 'One' ? [loginedId, nowOpenPage].sort().join("") : nowOpenPage

    return fetch(`/chatrooms/chattings/${option}?from=${from}&direction=${direction}`)
        .then(res => res.json())
        .then(chattings => {
            if (direction === 'up') {
                chattings.forEach(chatting => {
                    if(chatting.type === 'Message') prependChatting(chatting);
                    else if(chatting.type === 'Join'||chatting.type === 'Out') prependNotice(chatting);
                });
            } else if (direction === 'down') {
                chattings.forEach(chatting => {
                    if(chatting.type === 'Message') appendChatting(chatting);
                    else if(chatting.type === 'Join'||chatting.type === 'Out') appendNotice(chatting);
                });
            }
            return chattings;
        });
}

function prependNotice(data){
    lastChatting = null;
    const created = createNoticeChatting(data);
    chattingSpace.prepend(created);
    return created;
}

function prependChatting(data) {
    if (beginChatting == null || beginChatting.dataset.senderId !== data.senderId || (beginChatting.dataset.senderId === data.senderId && isDiffMinute(beginChatting.dataset.sendProfileDate, data.sendDate))) {
        beginChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.prepend(beginChatting);
    }

    const messageSpace = beginChatting.querySelector(".chatting-message-space");
    const created = createChattingMessage(data.content, data.sendDate, data.senderId);
    messageSpace.prepend(created);

    return created
}

function appendNotice(data){
    lastChatting = null;
    const created = createNoticeChatting(data);
    chattingSpace.append(created);
    return created;
}

function appendChatting(data) {
    if (lastChatting == null || lastChatting.dataset.senderId !== data.senderId || (lastChatting.dataset.senderId === data.senderId && isDiffMinute(lastChatting.dataset.sendProfileDate, data.sendDate))) {
        lastChatting = createChattingProfile(memberData[data.senderId], data.sendDate);
        chattingSpace.append(lastChatting);
    }

    const messageSpace = lastChatting.querySelector(".chatting-message-space");
    const created = createChattingMessage(data.content, data.sendDate, data.senderId);
    messageSpace.append(created);

    return created;
}

function createNoticeChatting(data){
    const div = makeElement("div", {className : ["d-flex","justify-content-center","my-1"], dataset: {"sendDate": data.sendDate}});
    const msgDiv = makeElement("div", {className : ["bg-schicken-light","py-1","px-3"], option : {"style" : "border-radius: 20px"}});
    msgDiv.innerText = data.content;
    div.append(msgDiv);
    return div;
}

function isDiffMinute(date1, date2) {
    return date1.substring(0, 12) !== date2.substring(0, 12);
}

function onGetChattingOne(data) {
    if(data.type === 'Join'){
        connectChatroom(data.chatroomId);

        if(nowOpenPage === 'chatroom'){
            document.getElementById("chatroom-list-btn").click();
        } else {
            chatroomListBtn.classList.add("get-message");
        }
        return;
    }
    onGetMessage(data, [loginedId, nowOpenPage].sort().join(""));
}

function onGetMessage(data, nowpage = nowOpenPage) {
    if (nowpage === data.chatroomId) {
        if(data.type === 'Out'){
            if(memberData[data.senderId] != null) delete memberData[data.senderId];
        }

        if(data.type === 'Join'){
            refreshMemberData();
        }
        getChattingInChatroom(data);
        return;
    }

    if (nowOpenPage === 'chatroom') {
        const target = chatroomList[data.chatroomId];
        updateChatroomListElement(target, data);
        chatroomListSpace.prepend(target);
        return;
    }

    chatroomListBtn.classList.add("get-message");
}

function updateChatroomListElement(target, data) {
    if (target.querySelector("[data-read-counter]") == null) {
        let counterEndDiv = makeElement("div", {className: ["text-end"], dataset: {"readCounter": ""}});
        let counterDiv = makeElement("div", {
            className: ["small", "bg-danger", "text-white", "d-inline-block", "recent-message-counter"],
            dataset: {"readCountNum": ""}
        })

        counterDiv.innerText = 0;

        counterEndDiv.append(counterDiv);

        target.querySelector("[data-chatroom-list-info]").append(counterEndDiv);
    }

    target.querySelector("[data-chat-list-time]").innerText = chatDateFormat(data.sendDate);

    const countNumDiv = target.querySelector("[data-read-count-num]");
    countNumDiv.innerText = Number(countNumDiv.innerText) + 1;

    const filteredEmp = [...document.querySelectorAll("[data-employee-search]")].filter(emp => emp.dataset.employeeSearch === data.senderId)[0];

    target.querySelector("[data-chat-recent-message]").innerText = reduceContentLength(filteredEmp.dataset.searchName + " : " + data.content);
}

function getChattingInChatroom(data) {
    let created = null;
    if(data.type === 'Message') created = appendChatting(data);
    else if(data.type === 'Join'||data.type === 'Out') created = appendNotice(data);

    if(created == null) return;

    if (downEnd) {
        chattingSpace.scrollTop = chattingSpace.scrollHeight;
        downEndObserver.disconnect();
        downEndObserver.observe(created);
    }

    if (data.senderId !== loginedId) {
        fetch('/chatrooms/readChatting', {
            method: "put",
            headers: {"Content-Type": "application/json;charset=utf-8"},
            body: JSON.stringify({
                chatroomId: data.chatroomId,
                id: data.id
            })
        }).then(res => res.text())
            .then(r => console.log("readChat : ", r));
    }
}

function createChattingProfile(data, sendDate) {
    if(data == null){
        data = {
            id : -1,
            profileImg : '/img/기본.jpg',
            name : '(알 수 없음)'
        }
    }

    let div = makeElement("div", {
        className: ["chatting", "d-flex", "mt-2", "ms-1"],
        dataset: {"senderId": data.id, "sendProfileDate": sendDate, "chattingProfile": ""}
    });
    let div2 = makeElement("div", {className: ["chatting-profile-img"]});

    let imgOption = {
        src: data.profileImg,
        width: "55px",
        height: "55px"
    }

    let img = makeElement("img", {className: ["rounded-circle"], option: imgOption})
    img.addEventListener("click", ()=>onProfileClick(data.id))

    div2.append(img);
    div.append(div2);

    let div3 = makeElement("div", {className: ["chatting-content"]});
    let div4 = makeElement("div", {dataset: {"sendInfo": ""}});
    let span = makeElement("span", {className: ["me-2", "linkable", "text-black", "hovercursor"]});
    span.innerText = data.name;
    span.addEventListener("click", ()=>onProfileClick(data.id))
    let span2 = makeElement("span", {className: ["small", "text-secondary"]});
    span2.innerText = chatDateFormat(sendDate);

    let div5 = makeElement("div", {className: ["chatting-message-space"]});

    div4.append(span, span2);
    div3.append(div4, div5);

    div.append(div3);

    return div;
}

function chatDateFormat(date, noYear = false) {
    if (date == null || date == "") {
        return "";
    }

    let year = date.substring(0, 4);
    let month = date.substring(4, 6);
    let day = date.substring(6, 8);
    let hour = date.substring(8, 10);
    let minute = date.substring(10, 12);
    let afternoon;

    if (Number(hour) >= 12) {
        afternoon = "오후";
        hour = Number(hour) % 13 + 1;
    } else {
        afternoon = "오전";
    }

    return (noYear? "" : year + "-") + month + "-" + day + " " + afternoon + " " + hour + ":" + minute;
}

function createChattingMessage(data, sendDate, senderId) {
    let classNames = ["mt-1", "p-2", "rounded-3", "text-break", "d-inline-block"];
    classNames.push(senderId === loginedId ? "bg-schicken-light-reverse" : "bg-schicken-light")
    let div = makeElement("div", {className: classNames})
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

function drawChatroomList(data) {
    let div = makeElement("div", {
        className: ["d-flex", "p-2", "aaa"],
        dataset: {
            "targetId": getTargetIdByMembers(data.members, data.id, data.type),
            "chatroomInfo": "",
            "chatroomType": data.type,
            "chatroomSearch": "",
            "searchName": data.name
        }
    });
    let imgDiv = makeElement("div");

    let img = null;
    if (data.type === 'One') {
        img = makeElement("img", {
            option: {
                "width": "50",
                "height": "50",
                "style": "border-radius: 20%",
                "src": getProfileImgByMembers(data.members, data.type)
            }
        })
    } else {
        img = makeElement("div", {className: ["many-chatroom-profile"]});

        const imgs = data.members.filter(member=> member.id !== loginedId).slice(0,4).map(member =>
            makeElement("img", {
                option: {
                    "width": "21",
                    "height": "21",
                    "style": "border-radius: 50%;",
                    "src": member.profileImg
                }
            })
        )

        img.append(...imgs);
    }

    if (img != null) imgDiv.append(img);

    let chatroomDiv = makeElement("div", {className: ["ms-2"]});
    let titleDiv = makeElement("div", {className: ["d-flex"]});
    let titleH5 = makeElement("h5",{className : ["text-truncate", "d-inline-block"], option:{"style" : "max-width:190px"}});
    titleH5.innerText = data.name;

    titleDiv.append(titleH5);

    if(data.type === 'Many'){
        let span = makeElement("span", {className : ["small", "text-secondary", "ms-1"]});
        span.innerText = "[" + data.members.length + "]";
        titleDiv.append(span);
    }

    let recentMessageDiv = makeElement("div", {dataset: {"chatRecentMessage": ""}});
    recentMessageDiv.innerText = reduceContentLength(data.lastMessage.content);

    chatroomDiv.append(titleDiv, recentMessageDiv);

    let infoDiv = makeElement("div", {className: ["ms-auto", "pe-2"], dataset: {"chatroomListInfo": ""}});
    let timeDiv = makeElement("div", {className: ["small"], dataset: {"chatListTime": ""}});
    timeDiv.innerHTML = chatDateFormat(data.lastMessage.sendDate, true);

    infoDiv.append(timeDiv);

    if (data.noReadCount != null && data.noReadCount > 0) {
        let counterEndDiv = makeElement("div", {className: ["text-end"], dataset: {"readCounter": ""}});
        let counterDiv = makeElement("div", {
            className: ["small", "bg-danger", "text-white", "d-inline-block", "recent-message-counter"],
            dataset: {"readCountNum": ""}
        })

        counterDiv.innerText = data.noReadCount;

        counterEndDiv.append(counterDiv);
        infoDiv.append(counterEndDiv);
    }

    div.append(imgDiv, chatroomDiv, infoDiv);

    return div;
}

function getTargetIdByMembers(members, chatroomId, type) {
    if (type === 'Many') return chatroomId;

    for (let member of members) {
        if (member.id !== loginedId) {
            return member.id;
        }
    }

    return null;
}

function getProfileImgByMembers(members, type) {
    if (type === 'One') {
        for (let member of members) {
            if (member.id !== loginedId) {
                return member.profileImg;
            }
        }
    }

    return "/img/기본.jpg"
}

function reduceContentLength(content, len = 14) {
    if (content.length < len) {
        return content;
    }

    return content.substring(0, len) + "...";
}

function pageChange(to) {
    if (isCreateState) {
        document.getElementById("chatroom-list-create-cancel-btn").click();
    }

    if(isInviteState){
        unsetInviteState()
    }

    infinityScrollObserver.disconnect();
    downEndObserver.disconnect();
    [...document.getElementsByClassName("now-page")].forEach(e => e.classList.remove("now-page"));

    if(chatroomInfoModal._isShown){
        chatroomInfoModal.hide()
    }

    if (to == null) return;

    nowOpenPage = to.dataset.pageName;
    to.classList.add("now-page");

    if (nowOpenPage === 'chatroom') {
        to.classList.remove("get-message");
    }
}

function onSendMessageBtnClick() {
    const chatMsg = chattingArea.value;
    if (chatMsg.length === 0) return;

    let data = {
        chatroomId: nowOpenPage,
        type: "Message",
        pageType: nowPageType,
        content: chatMsg.trim()
    };

    sendMessage(data);

    chattingArea.value = "";
    chattingArea.focus();
}

function getChatroomList() {
    fetch('/chatrooms/list')
        .then(res => res.json())
        .then(r => {
            chatroomListSpace.innerHTML = "";
            let elements = r.map(el => {
                const created = drawChatroomList(el);
                chatroomList[el.id] = created;
                return created;
            });
            chatroomListSpace.append(...elements);
        });
}

function getInputKey(event) {
    if (event.key === 'Enter') {
        if (!event.shiftKey) {
            if (event.target.value.trim() === '') return;
            onSendMessageBtnClick();
        }
    }
}

function employeeSelectForm() {
    let selected = $("#selected-employee");
    if (selected.is(":hidden")) {
        isCreateState = true;
        selected.slideDown("fast", () => {
            selected.parent().removeClass("hide-list");
            selected.addClass("d-flex");
            $(".employee-list").addClass("short-list");
        })
    } else {
        isCreateState = false;
        selected.parent().addClass("hide-list");
        selected.removeClass("d-flex");
        selected.hide("fast");
        $(".employee-list").removeClass("short-list");
        clearSelectElement();
    }
}

async function onProfileClick(event) {
    const target = event.target;
    let empId = null;
    if(target != null) empId = target.dataset.employeeSearch;
    else empId = event;

    if(empId == -1) return;

    if (empId == null) {
        target.parentElement.click();
        return;
    }

    if (isCreateState || isInviteState) {
        let empName = target.dataset.searchName;
        let src = target.querySelector("img.rounded-circle").src;
        addSelectedEmployeeList(empId, empName, src);
        return;
    }

    openProfileModal(empId);
}

function addSelectedEmployeeList(id, name, src) {
    if (selectedEmployees[id] != null) {
        unSelectEmployee(id);
        return;
    }

    selectEmployee(id, selectedDiv(id, name, src));
}

function selectEmployee(id, element) {
    selectedEmployees[id] = element;
    selectedEmployeeDiv.prepend(selectedEmployees[id]);

    if (Object.keys(selectedEmployees).length > 0) {
        if(isCreateState) $("#chatroom-create-btn").removeClass("disabled");
        if(isInviteState) $("#member-invite-submit-btn").removeClass("disabled");
    }
}

function unSelectEmployee(id) {
    selectedEmployees[id].remove();
    delete selectedEmployees[id];
    if (Object.keys(selectedEmployees).length === 0) {
        if(isCreateState) $("#chatroom-create-btn").addClass("disabled");
        if(isInviteState) $("#member-invite-submit-btn").addClass("disabled");
    }
}

function clearSelectElement() {
    for (let key in selectedEmployees) {
        selectedEmployees[key].remove();
        delete selectedEmployees[key];
    }
}

function selectEmployeeUncancelType(id, element){
    selectedEmployeesUncancel[id] = element;
    selectedEmployeeDiv.prepend(selectedEmployeesUncancel[id]);
}

function clearUncancelType(){
    for (let key in selectedEmployeesUncancel) {
        selectedEmployeesUncancel[key].remove();
        delete selectedEmployeesUncancel[key];
    }
}

function selectedDiv(id, name, src, canCancel = true) {
    const div = makeElement("div", {className: ["selected-member-item", "text-center"]});
    const img = makeElement("img", {className: ["rounded-circle"], option: {src: src, width: "55px", height: "55px"}});
    const span = makeElement("span", {className: ["ms-2"]});
    span.innerText = name;
    div.append(img, span);

    if(canCancel) {
        const indicator = makeElement("span", {className: ["indicator"], dataset: {"employeeId": id}});
        indicator.innerText = 'x';
        div.append(indicator);
    }

    return div;
}

async function openProfileModal(empId) {
    let info = await fetch('/employee/getProfile?id=' + empId).then(res => res.json())

    document.querySelectorAll("[data-profile-type]")
        .forEach(e => {
            let profileType = e.dataset.profileType;
            switch (profileType) {
                case 'img':
                    e.setAttribute("src", info.profileImg == null ? '/img/기본.jpg' : info.profileImg);
                    break;
                case 'chatting':
                    e.dataset.targetId = empId;
                    break;
                default:
                    e.innerText = info[profileType];
            }
        })
    namecardModal.show();
}

function onIndicatorClick(event) {
    let empId = event.target.dataset.employeeId;
    if (empId == null) {
        event.target.parentElement.click();
        return;
    }

    unSelectEmployee(empId);
}

function createChatroom() {
    const members = Object.keys(selectedEmployees);

    fetch('/chatrooms/create', {
        method: "post",
        headers: {"Content-Type": "application/json;charset=utf-8"},
        body: JSON.stringify(members)
    }).then(res => res.json())
        .then(r => {
            connectChatroom(r.chatroomId);
            pageChange();
            nowOpenPage = r.chatroomId;
            nowPageType = 'Many';
            chatroomInfoModalBtn.classList.remove("d-none");
            document.getElementById("chatting-page-btn").click();

            chatroomRenderByData(r).then(() => {
                setTimeout(() => {
                    document.querySelector("[data-last-read]")?.scrollIntoView({block: "center"})
                }, 500)
            })
        })
}

function isTitleChanged(event){
    const target = event.target;
    if(target.value === oldTitle){
        target.nextElementSibling.classList.add("disabled");
    } else {
        target.nextElementSibling.classList.remove("disabled");
    }
}

function onChatroomInfoModalOpen(){
    oldTitle = document.getElementById("chatroom-name").innerText;
    chatroomTitle.value = oldTitle;
    chatroomMembers.innerHTML = "";
    chatroomTitle.nextElementSibling.classList.add("disabled");

    const memberDivList = [...document.querySelectorAll("[data-employee-search]")]
        .filter(el => memberData[el.dataset.employeeSearch] != null)
        .map(div => div.cloneNode(true));

    chatroomMembers.append(...memberDivList);

    chatroomInfoModal.show()
}

function updateChatroomTitle(event){
    const target = event.target.previousElementSibling;
    if(target.value === oldTitle){
        alert("기존이름과 같습니다.");
        return;
    }

    if(target.value.length > 14){
        alert("14자리 까지만 가능합니다")
        return;
    }

    fetch('/chatrooms/updateTitle', {
        method: "put",
        headers : {"Content-Type" : "application/json;charset=utf-8"},
        body : JSON.stringify({
            id : nowOpenPage,
            name : target.value
        })
    }).then(res=>res.json())
        .then(r => {
            if(!r) {
                alert("변경 실패");
                return;
            }

            alert("채팅방 이름을 변경했습니다");
            oldTitle = target.value;
            target.nextElementSibling.classList.add("disabled");
            document.getElementById("chatroom-name").innerText = target.value;
        });
}

async function setInviteState(){
    isInviteState = true;

    const selected = $('#selected-employee');
    selected.slideDown("fast", () => {
        selected.parent().addClass("invite-state");
        selected.addClass("d-flex");
        $(".employee-list").addClass("short-list");
        chatEmployeeList.querySelectorAll("[data-employee-search]").forEach(el =>{
            if(memberData[el.dataset.employeeSearch] != null){
                el.classList.add("already-selected");
            }
        });

        for (let key in memberData) {
            const member = memberData[key];
            selectEmployeeUncancelType(member.id, selectedDiv(member.id, member.name, member.profileImg, false));
        }
    })
}

async function unsetInviteState(){
    chatEmployeeList.querySelectorAll(".already-selected").forEach(el =>{
        el.classList.remove("already-selected")
    });

    const selected = $('#selected-employee');
    selected.parent().removeClass("invite-state");
    selected.removeClass("d-flex");
    selected.hide("fast");
    $(".employee-list").removeClass("short-list");

    clearSelectElement();
    clearUncancelType();
    isInviteState = false;
}

function onMemberInvite(){
    setInviteState().then(()=>{
        document.getElementById("invite-page-btn").click();
        chatroomInfoModal.hide();
    })

}

function finishMemberInvite(){
    unsetInviteState().then(()=>{
        document.getElementById("chatting-page-btn").click();
    })
}

function submitInviteMember(){
    const members = Object.keys(selectedEmployees);

    if(members.length === 0){
        alert("추가할 인원이 없습니다");
        return;
    }


    fetch('/chatrooms/join/' + nowOpenPage, {
        method: "post",
        headers: {"Content-Type": "application/json;charset=utf-8"},
        body: JSON.stringify(members)
    }).then(res => res.json())
        .then(r => {
            console.log(r);
            finishMemberInvite();
        })
}

function outChatroom(){

    if(!confirm("채팅방을 나가시겠습니까?")) return;

    fetch('/chatrooms/out/' + nowOpenPage,{
        method : 'delete'
    }).then(res => {
        if(res.ok){
            console.log("ok!");
            disConnectChatroom(nowOpenPage);
            chatroomInfoModal.hide();
            document.getElementById("chatroom-list-btn").click();
        }
    })
}

setWhenReceiveMessage(onGetChattingOne, onGetMessage);

sendMessageBtn.addEventListener("click", onSendMessageBtnClick);
chatroomListBtn.addEventListener("click", getChatroomList);
chatroomListSpace.addEventListener("click", (event) => openChatting(event))
chattingArea.addEventListener("keyup", getInputKey);
chatEmployeeList.addEventListener("click", onProfileClick);
selectedEmployeeDiv.addEventListener("click", onIndicatorClick);
chatroomInfoModalBtn.addEventListener("click", onChatroomInfoModalOpen);
chatroomTitle.addEventListener("keyup", isTitleChanged);
chatroomTitle.nextElementSibling.addEventListener("click", updateChatroomTitle);
chatroomMembers.addEventListener("click", onProfileClick);
memberInviteBtn.addEventListener("click", onMemberInvite);
memberInviteCancelBtn.addEventListener("click", finishMemberInvite);
memberInviteSubmitBtn.addEventListener("click", submitInviteMember);
chatroomOutBtn.addEventListener("click", outChatroom);
document.getElementById("chatroom-list-create").addEventListener("click", employeeSelectForm);
document.getElementById("chatroom-list-create-cancel-btn").addEventListener("click", employeeSelectForm);
document.getElementById("chatroom-create-btn").addEventListener("click", createChatroom);
document.querySelector("a[data-profile-type=chatting]").addEventListener("click", event => openChatting(event, 'One'))
document.getElementById("employee-list-btn").addEventListener("click", event => pageChange(event.target))
document.getElementById("chatroom-list-btn").addEventListener("click", event => pageChange(event.target))
