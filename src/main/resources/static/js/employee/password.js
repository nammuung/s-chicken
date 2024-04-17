var form = document.querySelector('#profile-change-password form');

// Add event listener for form submission
form.addEventListener('submit', function(event) {
  // Prevent form submission
  event.preventDefault();

  // Get password, new password, and confirm password fields
  var password = document.getElementById('password').value;
  var newPassword = document.getElementById('newPassword').value;
  var renewPassword = document.getElementById('renewPassword').value;

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
