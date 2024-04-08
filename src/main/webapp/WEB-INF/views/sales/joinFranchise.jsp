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
                        <form class="d-flex flex-column align-items-center">
                            <div class="row justify-content-center mb-3 w-50">
                                <div class="form-group mb-3 mt-3">
                                    <label for="name" class="form-label"><b>가맹점명</b></label>
                                    <input id="name" type="text" class="form-control" placeholder="이름">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="phone" class="form-label"><b>점장명</b></label>
                                    <input id="phone" type="text" class="form-control" placeholder="전화번호">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="email" class="form-label"><b>이메일</b></label>
                                    <input id="email" type="email" class="form-control" placeholder="이메일">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="email" class="form-label"><b>전화번호</b></label>
                                    <input id="email" type="email" class="form-control" placeholder="이메일">
                                </div>

                                <label for="sample6_postcode" class="form-label"><b>주소</b></label>
                                <div class="form-group mb-3 d-flex">
                                    <input type="text" id="sample6_postcode" placeholder="우편번호" class="form-control">
                                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn btn-primary">
                                </div>


                                <div class="form-group mb-3 d-flex">
                                    <input type="text" id="sample6_address" class="form-control" placeholder="주소">
                                    <input type="text" id="sample6_detailAddress" class="form-control ml-2" placeholder="상세주소">
                                </div>


                                <div class="form-group mb-3">
                                    <label for="birth" class="form-label"><b>사업자번호</b></label>
                                    <input id="birth" type="text" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="담당자" class="form-label"><b>담당자</b></label>
                                    <select id="담당자" class="form-select">
                                        <option value="0">김경모</option>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="position" class="form-label"><b>계약일</b></label>
                                    <input id="register" type="date" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="position" class="form-label"><b>사업자등록증</b></label>
                                    <input id="register" type="file" class="form-control">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="position" class="form-label"><b>계약서</b></label>
                                    <input id="register" type="file" class="form-control">
                                </div>
                            </div>

                            <button type="button" class="btn btn-primary mt-3">등록</button>
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
</body>

</html>