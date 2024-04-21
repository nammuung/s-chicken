import {openNoteMessage} from "/js/header/header.js";

const schickenNotificationList = document.getElementById("schicken-notification-list");
const notificationBadge = document.getElementById("notification-badge");
const notificationIcon = document.getElementById("notification-icon");

/* 알림 클릭시 함수 매핑 */
let notificationByType = {
    NoteMessage : openNoteMessageByLink
}
function onNotificationClick(event){
    const link = event.target.dataset.link;
    const type = event.target.dataset.type;

    if(link != null && type != null) notificationByType[type](link);
}

/* 매핑할 함수들 */
/**
 * 받은 쪽지를 열어줌
 * @param link 받은 쪽지의 id
 */
function openNoteMessageByLink(link){
    openNoteMessage(link)
}


/* functions */
function drawNotificationDropdownItem({content, time, type, link}){
    let nmTitle = {
        "NoteMessage" : "쪽지가 왔습니다",
    }

    let li = document.createElement("li");
    li.setAttribute("data-link", link);
    li.setAttribute("data-type", type);
    li.classList.add("notification-item");

    let div = document.createElement("div");

    div.append(
        createElement("h4", nmTitle[type]),
        createElement("p", content),
        createElement("p", dateFormatter(time))
    );
    li.append(div);

    let divider = document.createElement("li");
    let hr = document.createElement("hr");
    hr.classList.add("dropdown-divider");
    divider.append(hr);

    let returnDiv = document.createElement("div");
    returnDiv.append(li, divider);

    return returnDiv;
}

function dateFormatter(time){
    let year = time.substring(0,4);
    let month = time.substring(4,6);
    let day = time.substring(6,8);
    let hour = time.substring(8,10);
    let minute = time.substring(10,12);

    return `${year}-${month}-${day} ${hour}:${minute}`;
}

function createElement(name, text){
    let element = document.createElement(name);
    element.innerText = text;
    return element;
}

/* 이벤트 리스너등록 */
schickenNotificationList.addEventListener("click", onNotificationClick);
notificationIcon.addEventListener("click", ()=>notificationBadge.classList.add("d-none"))

/* controller에서 사용할 함수 */
export const appendNotificationList = (noti) => {
    if(schickenNotificationList.children.length >= 10) {
        schickenNotificationList.lastElementChild.remove();
        schickenNotificationList.lastElementChild.remove();
    }
    document.querySelectorAll("[data-no-notification]").forEach(e => e.remove());

    notificationBadge.classList.remove("d-none");
    const listItem = drawNotificationDropdownItem(noti);
    schickenNotificationList.prepend(listItem);
};
