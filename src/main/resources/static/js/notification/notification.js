import {openNoteMessage} from "/js/header/header.js";

const schickenNotificationList = document.getElementById("schicken-notification-list");
const notificationPageList = document.querySelector(".notifications[data-notification-list]")
const notificationBadge = document.getElementById("notification-badge");
const notificationIcon = document.getElementById("notification-icon");

/* 알림 클릭시 함수 매핑 */
let notificationByType = {
    NoteMessage : openNoteMessageByLink
}

function readNotification(id, type, link){
    fetch('/notifications/read', {
        method:'post',
        headers:{"content-Type" : "application/json;charset-utf-8"},
        body:JSON.stringify({id:id})
    })
        .then(res=>{
            if(res.ok) notificationByType[type](link);
        })
}

function onNotificationClick(event){
    const link = event.target.dataset.link;
    const type = event.target.dataset.type;
    const notiId = event.target.dataset.notiId;

    if(link != null && type != null && notiId != null) {
        readNotification(notiId, type, link)
        return;
    }

    event.target.parentElement.click();
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
function drawNotificationDropdownItem({id, title, content, time, type, link}){
    let li = document.createElement("li");
    li.setAttribute("data-noti-id", id);
    li.setAttribute("data-link", link);
    li.setAttribute("data-type", type);
    li.classList.add("notification-item");

    let div = document.createElement("div");

    div.append(
        createElement("h4", title),
        createElement("p", content),
        createElement("p", time)
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

function createElement(name, text){
    let element = document.createElement(name);
    element.innerText = text;
    return element;
}

function onNotificationIconClick(){
    notificationBadge.classList.add("d-none");
}

/* 이벤트 리스너등록 */
schickenNotificationList.addEventListener("click", onNotificationClick);
if(notificationPageList != null) notificationPageList.addEventListener("click", onNotificationClick);
notificationIcon.addEventListener("click", onNotificationIconClick);

/* onDomContextLoad */
window.addEventListener("DOMContentLoaded", ()=>{
    fetch('/notifications?read=false')
        .then(res=>res.json())
        .then(r => {
            if(r.length > 0) {
                let noNotification = document.querySelectorAll("[data-no-notification]");
                if(noNotification.length > 0) {
                    noNotification.forEach(e => e.remove());
                }
                r.forEach(e => appendNotificationList(e))
            }
        });
})

/* controller에서 사용할 함수 */
export const appendNotificationList = (noti) => {
    if(schickenNotificationList.children.length >= 10) {
        schickenNotificationList.lastElementChild.remove();
        schickenNotificationList.lastElementChild.remove();
    }

    console.log(noti);

    notificationBadge.classList.remove("d-none");
    const listItem = drawNotificationDropdownItem(noti);
    schickenNotificationList.prepend(listItem);
};
