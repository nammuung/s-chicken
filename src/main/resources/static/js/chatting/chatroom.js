const namecardModal = new bootstrap.Modal(document.getElementById("namecard-modal"));
function onProfileClick(empId){
    fetch('/employee/getProfile?id=' + empId)
        .then(res=>res.json())
        .then(info=>{
            console.log("info = ", info)
            document.querySelectorAll("[data-profile-type]")
                .forEach(e => {
                    let profileType = e.dataset.profileType;
                    console.log(e.dataset, profileType)
                    switch (profileType){
                        case 'img':
                            e.setAttribute("src", info.profileImg == null ? '/img/기본.jpg' : info.profileImg);
                            break;
                        case 'noteMessage':
                            break;
                        case 'chatting':
                            break;
                        default:
                            e.innerText = info[profileType];
                    }
                })
            namecardModal.show();
        })
}

document.getElementById("search-input").addEventListener("keyup", event=>{
    let searchName = event.target.value;
    const regex = new RegExp(searchName);

    document.querySelectorAll("[data-search-name]").forEach(e => {
        let name = e.dataset.searchName;
        if(regex.test(name)){
            e.classList.remove("d-none");
            document.getElementById(e.dataset.parentId).classList.remove("d-none");
        } else {
            e.classList.add("d-none");
            let elements = document.querySelectorAll(`[data-parent-id=${e.dataset.parentId}]`);
            for (let ele of elements) {
                if(!ele.classList.contains("d-none")){
                    return;
                }
            }

            document.getElementById(e.dataset.parentId).classList.add("d-none");
        }
    })


})
