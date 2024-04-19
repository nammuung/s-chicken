// 조직도 받아오는 url
const url = 'http://localhost:80/department/list';

fetch(url)
  .then(response => {
    return response.json(); 
  })
  .then(data => {
    // 부서 select와 팀 select 요소를 가져옴
    const departmentSelect = document.getElementById("department");
    const teamSelect = document.getElementById("team");

    // 부서와 팀을 저장할 배열 초기화
    const departments = [];
    const teams = [];

    // 부서와 팀을 분류하여 배열에 추가
    data.forEach(item => {
        if (item.upperId === null || item.upperId === 1) { // upperId가 null 또는 1이면 부서
            departments.push(item); // 부서 배열에 추가
        } else { // 그 외의 경우는 팀
            teams.push(item); // 팀 배열에 추가
        }
    });

    // 부서 select를 채워넣음
    departments.forEach(department => {
        const option = document.createElement("option");
        option.value = department.id; // 부서의 id를 option의 값으로 설정
        option.textContent = department.name; // 부서의 이름을 option의 텍스트로 설정
        departmentSelect.appendChild(option); // 부서 select에 option을 추가
    });

    // 부서 select의 변경 이벤트를 추가
    departmentSelect.addEventListener('change', function() {
        const selectedDepartmentId = parseInt(this.value); // 선택한 부서의 id를 가져옴

        // 팀 select를 초기화
        teamSelect.innerHTML = '';

        // 선택한 부서에 해당하는 팀을 필터링
        // filter -> true만 새로운 배열로 담음
        // team객체 만들어서 각각의 team 에 upperId, selectedDepartmentId비교  조건을 만족하면 
        const filteredTeams = teams.filter(team => team.upperId === selectedDepartmentId);

        // 선택한 부서가 팀 select에서 선택되어 있도록 처리
        const departmentOption = document.createElement("option");
        departmentOption.value = selectedDepartmentId;
        departmentOption.textContent = this.options[this.selectedIndex].text;
        teamSelect.appendChild(departmentOption);

        // 필터링된 팀을 팀 select에 추가
        filteredTeams.forEach(team => {
            const option = document.createElement("option");
            option.value = team.id; // 팀의 id를 option의 값으로 설정
            option.textContent = team.name; // 팀의 이름을 option의 텍스트로 설정
            teamSelect.appendChild(option); // 팀 select에 option을 추가
        });
    });
  })
  .catch(error => {
    console.error('Error fetching data:', error); // 데이터를 불러오는 동안 발생한 오류 처리
  });






  let frm = document.querySelector("form"); 




// 하이픈을 제거하는 함수
function removeHyphen(dateString) {
  return dateString.replace(/-/g, "");
}











// 폼이 서브밋될 때 실행되는 함수
function submitForm() {
  // 생년월일과 입사일 입력 필드의 값을 yyyy-mm-dd 형식으로 변환
  let resident = document.getElementById("residentNumber").value;
  let employment = document.getElementById("dateOfEmployment").value;

  // 하이픈 제거
  let password = removeHyphen(employment);

  // 비밀번호 입력 필드에 생년월일을 설정 (하이픈 제거된 값)
  document.getElementById("password").value = password;

  // 입력 값의 유효성 검사
  return validateForm();
}

// 입력값 유효성 검사 함수
function validateForm() {
  var name = document.getElementById("name").value;
  var phoneNumber = document.getElementById("phoneNumber").value;
  var email = document.getElementById("email").value;
  var postcode = document.getElementById("postcode").value;
  var address = document.getElementById("address").value;
  var addressDetail = document.getElementById("addressDetail").value;
  var residentNumber = document.getElementById("residentNumber").value;
  var dateOfEmployment = document.getElementById("dateOfEmployment").value;
  var posId = document.getElementById("posId").value;
  var department = document.getElementById("department").value;
  var team = document.getElementById("team").value;
  var bankName = document.getElementById("bankName").value;
  var accountNumber = document.getElementById("accountNumber").value;

  // 필수 입력 필드가 비어 있는지 확인
  if (!name || !phoneNumber || !email || !postcode || !address || !addressDetail || !residentNumber || !dateOfEmployment || posId === "0" || department === "0" || team === "0" || bankName === "0" || !accountNumber) {
      // 필수 입력 필드 중 하나라도 비어 있는 경우 오류 메시지 표시 및 폼 제출 중지
      alert("모든 필수 항목을 입력해주세요.");
      return false;
  }

  // 이메일 형식을 검증
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
      alert("올바른 이메일 주소를 입력해주세요.");
      return false;
  }

  // 휴대폰 번호에서 하이픈 제거
  phoneNumber = phoneNumber.replace(/-/g, '');

  // 계좌번호에서 하이픈 제거
  accountNumber = accountNumber.replace(/-/g, '');

  // 제거된 값 다시 필드에 설정
  document.getElementById("phoneNumber").value = phoneNumber;
  document.getElementById("accountNumber").value = accountNumber;

  // 모든 검증 통과 시 폼 제출 허용
  return true;
}
