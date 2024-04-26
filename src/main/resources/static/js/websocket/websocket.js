import "/js/websocket/stompClient.js";

let waitting = [];

let subs = {};
const stompClient = new StompJs.Client({
    brokerURL : 'ws://localhost/ws',
    debug : str => console.log(str),
    reconnectDelay : 5000,
    heartbeatIncoming : 4000,
    heartbeatOutgoing : 4000
});
stompClient.activate();

stompClient.onConnect = function (frame){
    waitting.forEach(e => {
        addHandler(e.sub, e.handler, e.id);
    })
    console.log(frame);
}

stompClient.onStompError = function (frame) {
    console.log('Broker reported error: ' + frame.headers['message']);
    console.log('Additional details: ' + frame.body);
};


function receiveMessage(message, callback){
    callback(JSON.parse(message.body));
}

function addHandler(sub, handler, id){
    if(id == null) {
        id = crypto.randomUUID();
        waitting.push({sub, handler, id});
    }

    if(stompClient.connected){
        subs[id] = stompClient.subscribe('/sub/' + sub, message => receiveMessage(message, handler));
    }

    return id;
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
        destination : "/pub/" + pub,
        body : JSON.stringify(message)
    })
}

const added = {};
export function mapping(path, callback){
    added[path] = addHandler(path, callback);
}

export function cutMapping(path){
    deleteHandler(added[path]);
}

export function send(path, message){
    sendMessage(path, message);
}

export const loginedId = document.querySelector("[data-logined-id]")?.dataset.loginedId;
