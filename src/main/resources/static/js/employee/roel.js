// 스크립트: 부서 선택 상자와 역할 테이블 채우기

// 부서 선택 상자와 역할 테이블에 대한 참조 가져오기
const departmentSelect = document.getElementById("department"); // "department" ID를 가진 요소를 찾아서 가져옴
const roleTable = document.getElementById("roleTable"); // "roleTable" ID를 가진 요소를 찾아서 가져옴
const roleTableBody = roleTable.querySelector("tbody"); // roleTable 내부에서 "tbody" 요소를 찾아서 가져옴


fetch('http://localhost:80/department/list') // 서버로 요청을 보내어 부서 데이터 가져오기
.then(response => {
    // 응답이 성공적인지 확인
    if (!response.ok) {
        // 실패한 경우 오류 발생
        throw new Error('부서 데이터를 가져오는 데 실패했어');
    }
    // 성공한 경우 응답을 JSON으로 파싱
    return response.json();
})
.then(data => {
    // 부서 데이터를 반복하며 각 부서에 대한 옵션 생성
    data.forEach(department => {
        const option = document.createElement("option"); // 새로운 <option> 요소 생성
        // 각 부서의 ID를 옵션 값으로 설정
        option.value = department.id;
        // 각 부서의 이름을 옵션 텍스트로 설정
        option.textContent = department.name;
        // 선택 상자에 옵션 추가
        departmentSelect.appendChild(option);
    });
})
.catch(error => {
    console.error('부서 데이터를 가져오는 중 오류 발생:', error);
});

// 고유한 역할 이름 및 ID를 필터링하는 함수
function getUniqueRoles(data) {
  const uniqueRoles = {};
  data.forEach(item => {
      // 역할 이름이 이미 고유한 역할 목록에 포함되어 있지 않으면 추가
      if (!uniqueRoles[item.position.name]) {
          uniqueRoles[item.position.name] = item.position.id;
          console.log(uniqueRoles);
      }
  });
  return uniqueRoles;
}

// 특정 부서에 역할이 속하는지 확인하는 함수
function isRoleInDepartment(role, selectedDepartmentId) {
    return role.department && role.department.id === selectedDepartmentId;
}

// 부서 선택 상자에 대한 이벤트 리스너
departmentSelect.addEventListener('change', function() {
  // 선택한 부서의 ID를 숫자로 변환
  const selectedDepartmentId = parseInt(this.value);
  // 서버에서 직원 역할 데이터 가져오기
  fetch('http://localhost:80/employee/roles')
  .then(response => {
      // 응답이 성공적인지 확인
      if (!response.ok) {
          // 실패한 경우 오류 발생
          throw new Error('직원 역할 데이터를 가져오는 데 실패');
      }
      // 성공한 경우 응답을 JSON으로 파싱
      return response.json();
  })
  .then(data => {
      // 테이블 행 초기화
      roleTableBody.innerHTML = '';
      // 고유한 역할 이름과 ID 가져오기
      const uniqueRoles = getUniqueRoles(data);
      // 각 역할 이름에 대한 테이블 행 추가
      Object.keys(uniqueRoles).forEach(roleName => {
          const roleId = uniqueRoles[roleName]; // 역할 이름에 해당하는 ID 가져오기
          const row = roleTableBody.insertRow(); // 새로운 테이블 행 추가
          const cell1 = row.insertCell(0); // 첫 번째 열 추가
          const cell2 = row.insertCell(1); // 두 번째 열 추가
          cell1.textContent = roleName; // 역할 이름을 첫 번째 열에 설정
          cell2.className = "text-center"; // 텍스트 가운데 정렬
          // 체크박스 생성
          const checkbox = document.createElement("input");
          checkbox.type = "checkbox"; // 타입은 체크박스
          checkbox.name = "roleId"; // 이름은 "roleId"
          checkbox.value = roleId; // 체크박스 값은 역할 ID
          // 선택한 부서에 해당 역할이 있는지 확인하고 체크박스 설정
          checkbox.checked = data.some(item => {
            return item.position.name === roleName && isRoleInDepartment(item, selectedDepartmentId);
        });
        
        
          cell2.appendChild(checkbox); // 두 번째 열에 체크박스 추가
      });
  })
  .catch(error => {
      console.error('오류 발생:', error);
  });
});
