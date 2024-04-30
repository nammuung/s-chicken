import {mapping, loginedId, send, cutMapping} from "/js/websocket/websocket.js";

let getMessage;

window.addEventListener("DOMContentLoaded", async () => {
    const chatrooms = await fetch('/chatrooms/list').then(res => res.json());
    mapping('chat/' + loginedId, data => getMessage(data));
    chatrooms.forEach(chatroom => {
        if (chatroom.type === 'many') mapping('chat/' + chatroom.id, data => getMessage(data));
    });
})

export function sendMessage(data) {
    data.senderId = loginedId;
    send(`chat/${data.pageType}/${data.chatroomId}`, data);
}

export function setWhenReceiveMessage(callback) {
    getMessage = callback;
}
