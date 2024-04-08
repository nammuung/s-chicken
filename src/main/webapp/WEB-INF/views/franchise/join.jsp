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
                                    <button type="button" onclick="execPostcode()" class="btn btn-primary">우편번호 조회</button>
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
                                    <button type="button" onclick="searchEmployee()" class="btn btn-primary">담당자 조회</button>
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

<script>
    function execPostcode() {}
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
</script>
</body>

</html>