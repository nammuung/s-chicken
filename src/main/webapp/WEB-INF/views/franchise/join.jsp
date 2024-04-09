<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <h1>가맹점 등록</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body mt-3">
                        <form id="joinForm" type="POST" enctype="multipart/form-data"  class="d-flex flex-column align-items-center">
                            <div class="row justify-content-center mb-3 w-50">
                                <div class="form-group mb-3 mt-3">
                                    <label for="franchiseName" class="form-label"><b>가맹점명</b></label>
                                    <input id="franchiseName" name="name" type="text" class="form-control" placeholder="이름">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="ownerName" class="form-label"><b>점장명</b></label>
                                    <input id="ownerName" name="ownerName" type="text" class="form-control" placeholder="전화번호">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="email" class="form-label"><b>이메일</b></label>
                                    <input id="email" name="email" type="email" class="form-control" placeholder="이메일">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="contactNumber" class="form-label"><b>전화번호</b></label>
                                    <input id="contactNumber" name="contactNumber" type="email" class="form-control" placeholder="이메일">
                                </div>

                                <label for="postCode" class="form-label"><b>주소</b></label>
                                <div class="form-group mb-3 d-flex">
                                    <input type="text" id="postCode" placeholder="우편번호" class="form-control me-1">
                                    <input type="button" onclick="execPostcode()" value="우편번호 조회" class="btn btn-primary">
                                </div>

                                <div class="form-group mb-3 d-flex">
                                    <input type="text" id="address" name="address" class="form-control me-1" placeholder="주소">
                                    <input type="text" id="addressDetail" name="addressDetail" class="form-control" placeholder="상세주소">
                                </div>

                                <div class="form-group mb-3">
                                    <label for="registerNumber" class="form-label"><b>사업자번호</b></label>
                                    <input id="registerNumber" name="registrationNumber" type="text" class="form-control">
                                </div>
                                <label for="personInCharge" class="form-label"><b>담당자</b></label>
                                <div class="form-group mb-3 d-flex">
                                    <input id="personInCharge" name="managerId" type="text" class="form-control me-1">
                                    <input type="button" onclick="searchEmployee()" value="담당자 조회" class="btn btn-primary">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="contractDate" class="form-label"><b>계약일</b></label>
                                    <input id="contractDate" name="contractDate" type="date" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="register" class="form-label"><b>사업자등록증</b></label>
                                    <input id="register" type="file" class="form-control" name="attach">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="contract" class="form-label"><b>계약서</b></label>
                                    <input id="contract" type="file" class="form-control" name="attach">
                                </div>
                            </div>

                            <button type="button" id="submitButton" class="btn btn-primary mt-3">등록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execPostcode() {
        new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if(data.userSelectedType === 'R'){
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraAddr !== ''){
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
//                         document.getElementById("addressDetail").value = extraAddr;

                    } else {
                        document.getElementById("addressDetail").value = '';
                    }
                    console.log(data.zonecode);
                    console.log(addr);
                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('postCode').value = data.zonecode;
                    document.getElementById("address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("addressDetail").focus();
                }
            }).open();
    }
    function searchEmployee() {}

    const submitButton = document.getElementById("submitButton")

    submitButton.addEventListener("click", async function () {
        const joinForm = document.getElementById("joinForm");
        const formData = new FormData(joinForm);
        const response = await fetch("/franchise/join", {
            method: "POST",
            body: formData,
        });
        const data = await response.json();
        if(response.status === 200) {
            alert("가맹점 추가에 성공했습니다.")
            location.href = "/franchise/detail?id="+data.id;
        } else {
            alert("가맹점 추가에 실패했습니다.")
        }
    })

    function checkData(){
        const name = document.getElementById("name");
        const ownerName = document.getElementById("ownerName");
        const email = document.getElementById("email");
        const contactNumber = document.getElementById("contactNumber");
        const address = document.getElementById("address");
        const addressDetail = document.getElementById("addressDetail");
        const managerId = document.getElementById("managerId");
        const register = document.getElementById("register");
        const contract = document.getElementById("contract");
        
    }
</script>
</body>

</html>
