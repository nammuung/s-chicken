import ws from "/js/websocket/websocket.js"
import {appendNotificationList} from "/js/notification/notification.js"

const added = {};
function mapping(path, callback){
    let id = ws.add(path , callback);
    added[id] = path;
}

const loginedId = document.querySelector("[data-logined-id]")?.dataset.loginedId;

/* controller */
mapping("noti/" + loginedId, appendNotificationList);
mapping("noti/whole", appendNotificationList);
