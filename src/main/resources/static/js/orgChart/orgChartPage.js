import oc from "/js/orgChart/orgChart.js";

let selected = "";
oc.init("org-chart", data=>{
    if(data.isSelect){
        selected = data;
    } else {
        selected = null;
    }
    if(selected != null && data.type === 'dept') {
        document.querySelectorAll(".dept-btn")
            .forEach(e=>e.classList.remove("disabled"));
    } else{
        document.querySelectorAll(".dept-btn")
            .forEach(e=>e.classList.add("disabled"));
    }
});
