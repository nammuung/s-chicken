import {mapping, loginedId} from "/js/websocket/websocket.js";
import {openNoteMessage} from "/js/header/header.js";

const schickenNotificationList = document.getElementById("schicken-notification-list");
const notificationPageList = document.querySelector(".notifications[data-notification-list]")
const notificationBadge = document.getElementById("notification-badge");
const notificationIcon = document.getElementById("notification-icon");

const moreNotificationBtn = document.getElementById("more-notification-btn");
const lastNotificationBtn = document.getElementById("last-notification-btn");

/* mapping handler */
mapping("noti/" + loginedId, appendNotificationList);
mapping("noti/whole", appendNotificationList);
function appendNotificationList(noti) {
    if(schickenNotificationList.children.length >= 11) {
        schickenNotificationList.lastElementChild.remove();
        schickenNotificationList.lastElementChild.remove();
    }

    let noNotification = document.querySelectorAll("[data-no-notification]");
    if(noNotification.length > 0) {
        noNotification.forEach(e => e.classList.add("d-none"));
    }

    notificationBadge.classList.remove("d-none");
    const listItem = drawNotificationDropdownItem(noti);
    schickenNotificationList.prepend(listItem);
};


/* 알림 클릭시 함수 매핑 */
let notificationByType = {
    NoteMessage : openNoteMessageByLink,
    Notice : openNoticePage,
    Document : openDocumentPopup,
    DocumentAccept : openDocumentPopup,
    DocumentReject : openDocumentPopup,
    Calendar : openCalendar
}

function onNotificationClick(event, isNoti){
    const link = event.target.dataset.link;
    const type = event.target.dataset.type;
    const notiId = event.target.dataset.notiId;

    if(link != null && type != null && notiId != null) {
        if(isNoti) event.target.remove();
        let noNotification = document.querySelectorAll("[data-no-notification]");
        if(noNotification.length > 0 && schickenNotificationList.querySelectorAll("[data-noti-id]").length === 0) {
            noNotification.forEach(e => e.classList.remove("d-none"));
        }
        readNotification(notiId, type, link)
        return;
    }

    event.target.parentElement.click();
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

/* 매핑할 함수들 */
/**
 * 받은 쪽지를 열어줌
 * @param link 받은 쪽지의 id
 */
function openNoteMessageByLink(link){
    openNoteMessage(link)
}

function openNoticePage(link){
    location.href="/전체/detail?id=" + link;
}

function openDocumentPopup(link){
    let options = screen();
    let relativePath = '/document/writenList/writenBonus?id='+link; // 문서의 상대 경로를 설정합니다.

    window.open(relativePath, '_blank', options);
}

function openCalendar(link){
    location.href = link;
}

/* functions */
function drawNotificationDropdownItem({id, title, content, time, type, link, isReaded}){
    let li = document.createElement("li");
    li.setAttribute("data-noti-id", id);
    li.setAttribute("data-link", link);
    li.setAttribute("data-type", type);
    li.classList.add("notification-item", "border-bottom");
    if(isReaded){
        li.classList.add("readed");
    }

    let div = document.createElement("div");

    div.append(
        createElement("h4", title),
        createElement("p", content),
        createElement("p", time)
    );
    li.append(div);

    let returnDiv = document.createElement("div");
    returnDiv.append(li);

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

function getMoreNotification(event){
    const page = Number(event.target.dataset.page) + 1;
    fetch('/notifications?page=' + page)
        .then(res=>res.json())
        .then(r => {
            if(notificationPageList == null) return;

            const listItem = r.map(r => drawNotificationDropdownItem(r));
            notificationPageList.append(...listItem);
            event.target.dataset.page = page;

            if(r.length < 10){
                moreNotificationBtn.classList.add("d-none");
                lastNotificationBtn.classList.remove("d-none");
            }
        })
}

function screen(){
    // 현재 화면의 너비와 높이를 가져옵니다.
    let screenWidth = window.screen.width;
    let screenHeight = window.screen.height;

    // 창의 너비와 높이를 설정합니다.
    let widthPercentage = 50; // 화면 너비의 50%
    let heightPercentage = 100; // 화면 높이의 100%
    let width = (screenWidth * widthPercentage) / 100;
    let height = (screenHeight * heightPercentage) / 100;

    // 창의 위치를 계산하여 화면 중앙에 위치시킵니다.
    let left = (screenWidth - width) / 2;
    let top = (screenHeight - height) / 2;

    // 창의 크기와 위치를 설정합니다.
    return `width=${width}, height=${height}, left=${left}, top=${top}`;
}

/* 이벤트 리스너등록 */
schickenNotificationList.addEventListener("click", (event)=>onNotificationClick(event, true));
if(notificationPageList != null) notificationPageList.addEventListener("click", onNotificationClick);
notificationIcon.addEventListener("click", onNotificationIconClick);
if(moreNotificationBtn != null) moreNotificationBtn.addEventListener("click",getMoreNotification);

/* onDomContextLoad */
window.addEventListener("DOMContentLoaded", ()=>{
    fetch('/notifications?read=false')
        .then(res=>res.json())
        .then(r => {
            if(r.length > 0) {
                let noNotification = document.querySelectorAll("[data-no-notification]");
                if(noNotification.length > 0) {
                    notificationBadge.classList.remove("d-none");
                    noNotification.forEach(e => e.classList.add("d-none"));
                }
                const listItem = r.map(r => drawNotificationDropdownItem(r));
                schickenNotificationList.append(...listItem);
            }

        });
})
