import "/js/websocket/stompClient.js";

let subs = {};
const stompClient = new StompJs.Client({
    brokerURL : 'ws://localhost/ws',
    debug : str => console.log(str),
    reconnectDelay : 5000,
    heartbeatIncoming : 4000,
    heartbeatOutgoing : 4000
});

stompClient.onConnect = function (frame){
    console.log(frame);
}

stompClient.onStompError = function (frame) {
    console.log('Broker reported error: ' + frame.headers['message']);
    console.log('Additional details: ' + frame.body);
};

stompClient.activate();

/**
 * 메세지를 받아 callback에 message의 content를 보내줌
 * @param message 서버가 보내준 message
 * @param callback
 */
function receiveMessage(message, callback){
    console.log("message = " , message);
    callback(message.content);
}

function addHandler(id, sub, handler){
    subs[id] = stompClient.subscribe(sub, message => receiveMessage(message, handler));
}

async function deleteHandler(id) {
    let bool = await subs[id].unsubscribe();

    if (bool)
        delete subs[id];
    else
        alert("unsubscribe fail");
}

function sendMessage(pub, message){
    stompClient.publish({
        destination : pub,
        body : JSON.stringify(message)
    })
}

export default {
    add : addHandler,
    send : sendMessage,
    del : deleteHandler
}
