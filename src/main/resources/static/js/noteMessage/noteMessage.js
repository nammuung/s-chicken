function send(formId){
    let formData = new FormData(document.getElementById(formId));

    fetch('/message/sendMessage',{
        method : 'post',
        body : formData
    }).then(res => res.json())
        .then(r => console.log(r));
}

export default {
    send : send
}
