
function markButtonClicked() {
    var idInput = document.getElementById("id").value; // ID 입력란 값 가져오기
    var empButton = document.getElementById("emp"); // 그룹웨어 버튼 가져오기
    var fraButton = document.getElementById("fra"); // 가맹점 버튼 가져오기
    
    if (empButton.checked) {
        // 그룹웨어 버튼이 체크된 경우
        idInput = 'erp' + idInput; // 'erp'를 ID 앞에 붙임
    } else if (fraButton.checked) {
        // 가맹점 버튼이 체크된 경우
        idInput = 'fra' + idInput; // 'fra'를 ID 앞에 붙임
    }
    
    // ID 입력란에 새로운 값을 설정
    document.getElementById("id").value = idInput;
    return true; // 폼 제출
}

