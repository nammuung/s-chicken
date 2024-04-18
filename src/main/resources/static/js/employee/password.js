let dateOfEmployment = document.getElementById('dateOfEmployment');
let form = document.getElementById('reset')

let resetpassword = document.getElementById('resertPassword');
resetpassword.addEventListener('click', function(){
  console.log("click");
  form.submit();
});

// Add event listener for form submission
form.addEventListener('submit', function(event) {
  // Prevent form submission
  event.preventDefault();
  var password = document.getElementById('password').value;
  var newPassword = document.getElementById('newPassword').value;
  var renewPassword = document.getElementById('renewPassword').value;


  // Get password, new password, and confirm password fields

  // Check if any of the fields are empty
  if (!password || !newPassword || !renewPassword) {
    // Display error message
    alert('모든 필드를 작성해야 합니다.');
  } else if (newPassword !== renewPassword) {
    // Check if new password and confirm password match
    // Display error message
    alert('1차 비밀번호와 2차 비밀번호가 일치하지 않습니다.');
  } else {
    // If all fields are filled and passwords match, submit the form

    form.submit();
  }
});



document.getElementById('uploadLink').addEventListener('click', function(e) {
  e.preventDefault(); // 기본 이벤트 막기
  document.getElementById('fileInput').click(); // 파일 입력 창 열기
});

