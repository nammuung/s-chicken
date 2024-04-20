document.addEventListener("DOMContentLoaded", function() {
    const annualLink = document.getElementById("annual-link");
    if (annualLink) {
        annualLink.addEventListener("click", function(event) {
            event.preventDefault(); // 링크의 기본 동작을 막음
            loadAnnualPage(); // 연차 JSP를 비동기적으로 불러오는 함수 호출
        });
    } else {
        console.error("연차 링크를 찾을 수 없습니다.");
    }

    const searchButton = document.getElementById("searchButton");
    const modal = new bootstrap.Modal(document.getElementById("dept-modal"));

    searchButton.addEventListener("click", () => {
        modal.show();
    });

    orgChart.init("orgChart", (data) => {
        console.log(data);
        modal.hide();
        managerId.value = data.id;
        managerName.value = data.name;
    }, "person");
});

// 연차 JSP를 비동기적으로 불러와 모달에 표시하는 함수
function loadAnnualPage() {
    fetch("/annual/annualInsert") // 연차 JSP의 URL
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.text();
        })
        .then(data => {
            // 모달 내의 modal-content div에 가져온 페이지 내용을 삽입
            const modalContent = document.querySelector("#annual-modal .modal-content");
            if (modalContent) {
                modalContent.innerHTML = data;
                // 모달을 보여줌
                const modal = new bootstrap.Modal(document.getElementById("annual-modal"));
                modal.show();
            } else {
                throw new Error("Modal content element not found");
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

