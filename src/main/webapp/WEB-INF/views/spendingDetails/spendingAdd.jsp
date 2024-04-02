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
        <h1>팀별 예산 등록</h1>
    </div>

    <div class="card pb-4">
        <div class="row justify-content-center mt-5">
            <div class="col-10">
                <div class="row mb-5">
                    <div class="col-3">
                        <form>
                            <label for="spending-date">월 선택</label>
                            <input class="form-control" type="month" id="spending-date"/>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <table class="table shadow-sm">
                            <thead>
                            <tr>
                                <th>부서명</th>
                                <th>이번 월 예산</th>
                                <th>이번 월 현재 사용액</th>
                                <th>차이</th>
                                <th>다음 달 예산 입력</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>총무팀</td>
                                <td>&#8361;520,000</td>
                                <td>&#8361;330,000</td>
                                <td>90,000</td>
                                <td>
                                    <input id="spending-input" type="text" class="form-control">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-12">
                        <div class="row justify-content-around">
                            <div class="col-3"> </div>
                            <div class="col-6">
                                <button class="btn btn-primary w-100">
                                    등록하기
                                </button>
                            </div>
                            <div class="col-3">

                            </div>
                        </div>
                    </div>
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
