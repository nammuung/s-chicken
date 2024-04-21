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

        //////////////////////////////////////////////////////////////////////
            const searchButton = document.getElementById("searchButton");
            searchButton.addEventListener("click", () => {
            console.log("click");
                    const modalDiv = document.createElement("div");
            modalDiv.classList.add("modal");
            modalDiv.setAttribute("tabindex", "-1");
            modalDiv.setAttribute("id", "dept-modal");

            const modalDialogDiv = document.createElement("div");
            modalDialogDiv.classList.add("modal-dialog");

            const modalContentDiv = document.createElement("div");
            modalContentDiv.classList.add("modal-content");

            // Modal header
            const modalHeaderDiv = document.createElement("div");
            modalHeaderDiv.classList.add("modal-header");
            modalHeaderDiv.innerHTML = `
                <h5 class="modal-title">부서 등록</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            `;

            // Modal body
            const modalBodyDiv = document.createElement("div");
            modalBodyDiv.classList.add("modal-body");
            modalBodyDiv.innerHTML = `<div id="orgChart"></div>`;

            // Modal footer
            const modalFooterDiv = document.createElement("div");
            modalFooterDiv.classList.add("modal-footer");
            modalFooterDiv.innerHTML = `
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            `;

            // Append 
            modalContentDiv.appendChild(modalHeaderDiv);
            modalContentDiv.appendChild(modalBodyDiv);
            modalContentDiv.appendChild(modalFooterDiv);
            modalDialogDiv.appendChild(modalContentDiv);
            modalDiv.appendChild(modalDialogDiv);
            document.body.appendChild(modalDiv);
            
            
            // Show the modal
            const modal = new bootstrap.Modal(modalDiv);
            modal.show();

            
            const searchButton = document.getElementById("searchButton");
            searchButton.addEventListener("click", ()=>{
                modals.show();
            })
            oc.init("orgChart", (data)=>{
                console.log(data);
                modals.hide();
                managerId.value = data.id;
                managerName.value = data.name;
            },"person");
            console.log("org 차트 로그임", oc);
            const modals = new bootstrap.Modal(document.getElementById("dept-modal"));   


        });
            } 
        })
        .catch(error => {
            console.error("Fetch error:", error);
        });
}


function validateForm() {
    var historyInput = document.getElementById('history').value.trim();
    var remainderAnnualInput = document.getElementById('remainderAnnual').value.trim();
    var employeeIdInput = document.getElementById('managerId').value.trim(); // Assuming managerId represents employeeId

    if (historyInput === "") {
        alert("제목을 입력하세요.");
        return false;
    }
    if (employeeIdInput === "") {
        alert("직원을 선택하세요.");
        return false;
    }
   
    return true; 
}

function plus() {
    var input = document.getElementById('remainderAnnual');
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue + 1;
}

function minus() {
    var input = document.getElementById('remainderAnnual');
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue - 1;
}



