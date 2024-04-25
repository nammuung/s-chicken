import {mapping, loginedId, send, cutMapping} from "/js/websocket/websocket.js";

async function getMyChatrooms(){
    return fetch('/chatrooms/my').then(res=>res.json());
}

window.addEventListener("DOMContentLoaded", async ()=>{
    const chatrooms = await getMyChatrooms();
    mapping('chat/' + loginedId, getChatting);
    chatrooms.forEach(chatroom => mapping('chat/' + chatroom.id, getChatting));
})

function getChatting(data){
    console.log(data);
}
