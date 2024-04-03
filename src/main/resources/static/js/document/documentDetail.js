console.log("들어오니 ?")

function hyuga(){
    
    const vacation = document.getElementById("vacation");
    const yoen_sel = document.getElementById("yoen_sel");
    const ban_sel = document.getElementById("ban_sel");

    if(vacation.value == "yoen"){        
        yoen_sel.style.display="table-row";
        ban_sel.style.display="none";
    }else if (vacation.value == "ban"){
        ban_sel.style.display="table-row";
        yoen_sel.style.display="none";
    }else{
        yoen_sel.style.display="none";
        ban_sel.style.display="none"
    }
}

  $(document).ready(function(){
    function adjustSize() {
      var windowHeight = $(window).height();
      var windowWidth = $(window).width();
      var modalWidth = windowWidth * 1; // 화면 너비의 50%
      var modalHeight = windowHeight * 0.8; // 화면 높이의 80%
      
      $('#modalContent').css('width', modalWidth);
      $('#modalContent').css('max-width', 'none'); // 최대 너비 제한 해제
      $('#modalContent').css('height', modalHeight);
    }

    // 최초 로드시 크기 조정
    adjustSize();

    // 윈도우 크기가 변경될 때마다 크기 조정
    $(window).resize(function(){
      adjustSize();
    });
  });