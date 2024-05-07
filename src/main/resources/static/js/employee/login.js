function markButtonClicked() {
    var idInput = document.getElementById("idz").value; // ID 입력란 값 가져오기
    var empButton = document.getElementById("emp"); // 그룹웨어 버튼 가져오기
    var fraButton = document.getElementById("fra"); // 가맹점 버튼 가져오기
    
    if (empButton.checked) {
        // 그룹웨어 버튼이 체크된 경우에만 ID 값에 "erp"를 추가
        idInput = 'emp' + idInput; // 'erp'를 ID 앞에 붙임
    } else if (fraButton.checked) {
        // 가맹점 버튼이 체크된 경우에만 ID 값에 "fra"를 추가
        idInput = 'fra' + idInput; // 'fra'를 ID 앞에 붙임
    }
    
    // Hidden input의 값을 설정
    document.getElementById("id").value = idInput;
    return true; // 폼 제출
}
// 서버로 비밀번호 찾기 요청을 보내는 함수
function submitPasswordReset() {
    var email = document.getElementById("email").value; // 이메일 입력란의 값 가져오기
    let id = document.getElementById("userid").value;
    var name = document.getElementById("name").value; // 이름 입력란의 값 가져오기
    console.log(id);
    if (email.trim() === "") { // 이메일이 비어있는지 확인
        alert("이메일을 입력하세요.");
        return;
    }
    if(id.trim() === ""){
        alert("아이디를 입력하세요.");
        return;
    }
    if(name.trim() === ""){
        alert("이름을 입력하세요.");
        return;
    }

    // 서버로 이메일 전송 요청
    $.ajax({
        type: "POST",
        url: "/employee/resetPassword", // 로컬 서버의 비밀번호 찾기 처리 엔드포인트 URL
        data: { email: email , id:id, name:name},
        success: function(response) {
            // 성공적으로 요청을 처리한 경우
            alert("임시 비밀번호가 이메일로 전송되었습니다.");
            $('#passwordResetModal').modal('hide'); // 모달 닫기
        },
        error: function(xhr, status, error) {
            // 요청 처리 중 에러가 발생한 경우
            alert("비밀번호 찾기 요청에 실패했습니다. 다시 시도해주세요.");
        }
    });
}
