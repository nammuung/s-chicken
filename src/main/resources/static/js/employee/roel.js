// 조직도 받아오는 url
const url = 'http://localhost:80/department/list';

fetch(url)
  .then(response => {
    return response.json(); // JSON 형식으로 응답을 반환
  })
  .then(data => {
    // 부서 선택을 위한 셀렉트 박스 요소 가져오기
    const departmentSelect = document.getElementById("department");

    // 받아온 데이터를 기반으로 부서 셀렉트 박스 옵션 생성
    data.forEach(department => {
        const option = document.createElement("option");
        option.value = department.id; // 부서의 id를 option의 값으로 설정
        option.textContent = department.name; // 부서의 이름을 option의 텍스트로 설정
        departmentSelect.appendChild(option); // 부서 select에 option을 추가
    });

    // 부서 선택 시 체크박스 처리
    departmentSelect.addEventListener("change", () => {
      // 선택한 부서의 이름 가져오기
      const selectedDepartmentName = departmentSelect.options[departmentSelect.selectedIndex].text;

      // 모든 체크박스의 상태 초기화
      const checkboxes = document.querySelectorAll('input[type="checkbox"]');
      checkboxes.forEach(checkbox => {
        checkbox.checked = false; // 모든 체크박스의 체크 상태를 해제
      });

      // 선택한 부서에 해당하는 체크박스 체크
      const departmentCheckboxes = document.querySelectorAll(`input[type="checkbox"][value="${selectedDepartmentName}"]`);
      departmentCheckboxes.forEach(checkbox => {
        checkbox.checked = true; // 선택한 부서의 이름과 일치하는 체크박스를 체크
      });
    });
  })
  .catch(error => {
    console.error('Error fetching data:', error); // 데이터를 불러오는 동안 발생한 오류 처리
  });
