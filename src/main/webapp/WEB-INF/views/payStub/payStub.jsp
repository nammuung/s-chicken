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
        <h1>급여명세서</h1>
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
                <div class="row justify-content-center">
                    <div class="col-6 shadow">
                        <div class="row py-3">
                            <h2 class="text-center">2024-02</h2>
                            <h1 class="text-center">급여명세서</h1>
                            <hr>
                            <p class="col-6 text-end fs-4">기본급 : </p>
                            <p class="col-6 fs-4">2,146,000,000 원</p>
                            <p class="col-6 text-end fs-4">상여금 : </p>
                            <p class="col-6 fs-4">900,000 원</p>
                            <p class="col-6 text-end fs-4">지출? : </p>
                            <p class="col-6 fs-4">583,647 원</p>
                            <hr>
                            <div class="col-6 text-end fs-4">실지급액 : </div>
                            <div class="col-6 fs-4">2,147,483,647 원</div>
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
