import {mapping, loginedId, send, cutMapping} from "/js/websocket/websocket.js";

async function getMyChatrooms(){
    return fetch('/chatrooms/list').then(res=>res.json());
}

let getMessage;

window.addEventListener("DOMContentLoaded", async ()=>{
    const chatrooms = await getMyChatrooms();
    mapping('chat/' + loginedId, data=>getMessage(data));
    chatrooms.forEach(chatroom => mapping('chat/' + chatroom.id, data=>getMessage(data)));
})

export default function setWhenReceiveMessage(callback) {
    getMessage = callback;
}
