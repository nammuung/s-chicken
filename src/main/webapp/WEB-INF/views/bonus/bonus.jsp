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
        <h1>상여 대상 목록</h1>
    </div>

    <div class="card pb-4">
        <div class="row justify-content-center mt-5">
            <div class="col-10">
                <div class="row justify-content-between mb-5">
                    <div class="col-3">
                        <form>
                            <label for="spending-date">월 선택</label>
                            <input class="form-control" type="month" id="spending-date"/>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <h2 class="text-center">2024-03</h2>
                    <h1 class="text-center mb-3">상여대상자</h1>
                    <table class="table shadow-sm">
                        <thead>
                        <tr>
                            <th>부서명</th>
                            <th>직책</th>
                            <th>이름</th>
                            <th>결재문서</th>
                            <th>상여금액</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>총무팀</td>
                            <td>팀장</td>
                            <td>이동일</td>
                            <td><span class="linkable">확인하기</span></td>
                            <td>9,000,000 원</td>
                            <td>
                                <button class="btn btn-danger">제외</button>
                            </td>
                        </tr>
                        <tr>
                            <td>총무팀</td>
                            <td>팀장</td>
                            <td>이동일</td>
                            <td><span class="linkable">확인하기</span></td>
                            <td>9,000,000 원</td>
                            <td>
                                <button class="btn btn-danger">제외</button>
                            </td>
                        </tr>
                        <tr>
                            <td>총무팀</td>
                            <td>팀장</td>
                            <td>이동일</td>
                            <td><span class="linkable">확인하기</span></td>
                            <td>9,000,000 원</td>
                            <td>
                                <button class="btn btn-danger">제외</button>
                            </td>
                        </tr>
                        <tr>
                            <td>총무팀</td>
                            <td>팀장</td>
                            <td>이동일</td>
                            <td><span class="linkable">확인하기</span></td>
                            <td>9,000,000 원</td>
                            <td>
                                <button class="btn btn-danger">제외</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script>
    window.onload = () => {
        const nowDate = new Date();
        const yearMonth = nowDate.getFullYear() + "-" + ((nowDate.getMonth() + 1) > 9 ? nowDate.getMonth() + 1 : '0' + (nowDate.getMonth() + 1));
        document.getElementById("spending-date").max = yearMonth;
    }
</script>
</body>

</html>
