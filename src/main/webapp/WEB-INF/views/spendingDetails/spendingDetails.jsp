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
        <h1>---팀 비품 지출 내역</h1>
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
                    <div class="col-4">
                        <div class="row">
                            <div class="col-6 text-end">
                                2024-04 예산 :
                            </div>
                            <div class="col-6">
                                ₩2,147,483,647
                            </div>
                            <div class="col-6 text-end">
                                사용액 :
                            </div>
                            <div class="col-6">
                                ₩47,483,647
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <table class="table shadow-sm">
                            <thead>
                            <tr>
                                <th>물품명</th>
                                <th>금액</th>
                                <th>날짜</th>
                                <th>영수증</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>핫초코100개</td>
                                <td>&#8361;32,000</td>
                                <td>2024년 03월 04일</td>
                                <td><a href="#">확인</a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-12">
                        <div class="row justify-content-around">
                            <div class="col-4"> </div>
                            <div class="col-4">

                            </div>
                            <div class="col-4">
                                <button class="btn btn-primary">
                                    그래프로 보기
                                </button>
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
