import ws from "/js/websocket/websocket.js"

const added = {};
function mapping(path, callback){
    let id = ws.add(path , callback);
    added[id] = path;
}

mapping("noti", ({content, time ,occurer}) => {
    console.log("content : " , content);
    console.log("time : " , time);
    console.log("occurer : " , occurer);
});
