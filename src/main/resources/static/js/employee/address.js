

function openPostcodePopup() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 우편번호와 주소 정보를 해당 필드에 채움
            document.getElementById('postcode').value = data.zonecode; // 우편번호 (5자리 새우편번호 사용)
            document.getElementById('address').value = data.address; // 전체 주소
            
            // 상세 주소 필드에 포커스
            document.getElementById('addressDetail').focus();
        }
    }).open();
}

