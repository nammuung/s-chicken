import oc from "/js/orgChart/orgChart.js";
//import("oc")

document.addEventListener("DOMContentLoaded", function() {
    const annualLink = document.getElementById("annual-link");
    if (annualLink) {
        annualLink.addEventListener("click", function(event) {
            event.preventDefault(); // 링크의 기본 동작을 막음
            loadAnnualPage(); // 연차 JSP를 비동기적으로 불러오는 역할
        });
    }
});



// 연차 JSP를 비동기적으로 불러와 모달에 표시하는 함수
function loadAnnualPage() {
    fetch("/annual/annualInsert")
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.text();
        })
        .then(data => {
            
            const modalContent = document.querySelector("#annual-modal .modal-content");
            if (modalContent) {
                modalContent.innerHTML = data;

                
                const modal = new bootstrap.Modal(document.getElementById("annual-modal"));
                modal.show();

      

            
            const searchButton = document.getElementById("searchButton");
            searchButton.addEventListener("click", ()=>{
                const modals = new bootstrap.Modal(document.getElementById("dept-modal"));   
                modals.show();
                console.log(123123123)
                oc.init("orgChart", (data)=>{
                    console.log(data);
                    modals.hide();
                    managerId.value = data.id;
                    managerName.value = data.name;
                },"person");
            })
            console.log("org 차트 로그임", oc);


			document.getElementById("plusBtn").addEventListener("click",plus);
			document.getElementById("minusBtn").addEventListener("click",minus);
			
            //});
        } 
    })
    .catch(error => {
        console.error("Fetch error:", error);
    });
}

function validateForm() {
    var historyInput = document.getElementById('history').value.trim();
    var remainderAnnualInput = document.getElementById('remainderAnnual').value.trim();

    if (historyInput === "") {
        alert("제목을 입력하세요.");
        return false;
    }
    return true; 
}
	
function plus() {
    var input = document.getElementById('remainderAnnual');
	console.log("click");
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue + 1;
}

function minus() {
    var input = document.getElementById('remainderAnnual');
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue - 1;
}



