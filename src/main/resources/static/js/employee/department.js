// 부서에 대한 데이터
const url = 'http://localhost:80/department/list';


fetch(url)
  .then(response => {
    
    return response.json();
  })
  .then(data => {
    // 부서 select 와 팀 select 가져옴
    const departmentSelect = document.getElementById("department");
    const teamSelect = document.getElementById("team");

    // 배열에 담음
    const departments = [];
    const teams = [];

        // 팀으로 끝나는 글자를 찾아서 배열에 밀어넣어줌
    data.forEach(item => {
        if (item.name.endsWith("팀")) {
            teams.push(item);
        } else {
            departments.push(item);
        }
    });

    departments.forEach(department => {
        // optin 생성
        const option = document.createElement("option");
        // value 값 department id로 줌
        option.value = department.id;

        //select box 에서 보이게 값을 넣어줌
        option.textContent = department.name;

        //select box 에 상속해서 보이게 함
        departmentSelect.appendChild(option);
    });

    teams.forEach(team => {
        // 상단 departments 로직이랑 같음
        const option = document.createElement("option");
        option.value = team.id;
        option.textContent = team.name;
        teamSelect.appendChild(option);
    });
  })
  .catch(error => {
    // 오류 처리
    console.error('Error fetching data:', error);
  });


  let frm = document.querySelector("form"); 

//   // 날짜 형식을 yyyy-mm-dd 형식으로 변환하는 함수
//   function formatDateToYYYYMMDD(dateString) {
//       const date = new Date(dateString);
//       const year = date.getFullYear();
//       const month = String(date.getMonth() + 1).padStart(2, '0');
//       const day = String(date.getDate()).padStart(2, '0');
//       return `${year}-${month}-${day}`;
//   }
  
  // 폼이 서브밋될 때 실행되는 함수
  function submitForm() {
      // 생년월일과 입사일 입력 필드의 값을 yyyy-mm-dd 형식으로 변환
      var residentNumberValue = formatDateToYYYYMMDD(document.getElementById("residentNumber").value);
      var dateOfEmploymentValue = formatDateToYYYYMMDD(document.getElementById("dateOfEmployment").value);
      
      // 비밀번호 입력 필드
      var passwordInput = document.getElementById("password");
      
      // password input 태그의 값을 dateOfEmployment의 값으로 설정합니다.
      passwordInput.value = dateOfEmploymentValue;
      
      console.log(passwordInput.value);
      
      return true;
  }
  




