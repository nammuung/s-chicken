import {mapping, loginedId, send, cutMapping} from "/js/websocket/websocket.js";

let getMessageOne;
let getMessageMany;

window.addEventListener("DOMContentLoaded", async () => {
    const chatrooms = await fetch('/chatrooms/list').then(res => res.json());
    mapping('chat/' + loginedId, data => getMessageOne(data));
    chatrooms.forEach(chatroom => {
        if (chatroom.type === 'Many') mapping('chat/' + chatroom.id, data => getMessageMany(data));
    });
})

export function sendMessage(data) {
    data.senderId = loginedId;
    send(`chat/${data.pageType.toLowerCase()}/${data.chatroomId}`, data);
}

export function setWhenReceiveMessage(oneCallback, manyCallback) {
    getMessageOne = oneCallback;
    getMessageMany = manyCallback;
}
