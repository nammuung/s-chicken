import ws from "/js/websocket/websocket.js"

const added = {};
export function mapping(path, callback){
    added[path] = ws.add(path, callback);
}

export function cutMapping(path){
    ws.del(added[path]);
}

export const loginedId = document.querySelector("[data-logined-id]")?.dataset.loginedId;
