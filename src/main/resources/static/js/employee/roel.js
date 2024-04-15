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
  })
  .catch(error => {
    console.error('Error fetching data:', error); // 데이터를 불러오는 동안 발생한 오류 처리
  });

const url2 = 'http://localhost:80/employee/role'
fetch(url2)
.then(response => {
  return response.text();
})

.then(data => {
    let uniqueArr = [];
    data.forEach((response) => {
      if(!uniqueArr.includes(response)){
        uniqueArr.push(response);
      }
    })


  })
  
