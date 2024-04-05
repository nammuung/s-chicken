import oc from "/js/orgChart/orgChart.js";

let selected = "";
oc.init("org-chart", data=>{
    selected = data;
    if(selected != null && data.type === 'dept') {
        document.querySelectorAll(".dept-btn")
            .forEach(e=>e.classList.remove("disabled"));
    }
});
