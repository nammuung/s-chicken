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


  let frm = document.getElementById("frm");

  frm.submit();