import ws from "/js/websocket/websocket.js"

const added = {};
function mapping(path, callback){
    let id = ws.add(path , callback);
    added[id] = path;
}

const schickenNotificationList = document.getElementById("schicken-notification-list");
const notificationBadge = document.getElementById("notification-badge");


/* controller */
mapping("noti", appendNotificationList);
function appendNotificationList(noti){
    if(schickenNotificationList.children.length >= 10) {
        schickenNotificationList.lastElementChild.remove();
        schickenNotificationList.lastElementChild.remove();
    }
    document.querySelectorAll("[data-no-notification]").forEach(e => e.remove());

    notificationBadge.classList.remove("d-none");
    const listItem = drawNotificationDropdownItem(noti);
    schickenNotificationList.prepend(listItem);
}

/* functions */
function drawNotificationDropdownItem({title, content, time, occurer}){
    let li = document.createElement("li");
    li.setAttribute("data-link-occurer", occurer);
    li.classList.add("notification-item");

    let div = document.createElement("div");

    let h4 = document.createElement("h4");
    h4.innerText = title;

    let p1 = document.createElement("p");
    p1.innerText = content;

    let p2 = document.createElement("p");
    p2.innerText = time;

    div.append(h4, p1, p2);
    li.append(div);

    let divider = document.createElement("li");
    let hr = document.createElement("hr");
    hr.classList.add("dropdown-divider");
    divider.append(hr);

    let returnDiv = document.createElement("div");
    returnDiv.append(li, divider);

    return returnDiv;
}

function onNotificationClick(event){
    const linkOccurer = event.target.dataset.linkOccurer;
    if(linkOccurer == null) return;

    console.log(linkOccurer);
}
/* 이벤트 리스너등록 */
schickenNotificationList.addEventListener("click", onNotificationClick);

document.getElementById("notification-icon").addEventListener("click", ()=>notificationBadge.classList.add("d-none"))
