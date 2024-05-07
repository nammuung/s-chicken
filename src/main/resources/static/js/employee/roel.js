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


    // 부서와 팀을 분류하여 배열에 추가
    data.forEach(item => {
            departments.push(item); // 부서 배열에 추가
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
        const selectedDepartmentId = parseInt(this.value); 
        console.log(selectedDepartmentId);
        const url2 = `http://localhost/employee/roles?id=${selectedDepartmentId}`;
        fetch(url2)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(data => {

            document.getElementsByName("rolId").forEach(e => e.checked = false);

            console.log(data);
            data.forEach(d => {
                console.log(d);
                let checkbox = document.querySelector(`input[name='rolId'][value='${d.rolId}']`);
                console.log(checkbox);
            checkbox.checked = true; 
            })
        })
 
    });
  })
  .catch(error => {
    console.error('Error fetching data:', error); // 데이터를 불러오는 동안 발생한 오류 처리
  });