
    // 폼이 전송될 때 실행될 함수
    function enableFields() {
        // disabled 속성이 설정된 모든 요소들을 가져옴
        var disabledElements = document.querySelectorAll('[disabled]');
        
        // 가져온 요소들에 대해 반복하면서 disabled 속성을 제거함
        disabledElements.forEach(function(element) {
            element.removeAttribute('disabled');
        });
    }

