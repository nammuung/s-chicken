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
        <h1>${facility}예약 목록</h1>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center " method="POST" action="#">
                    <label>
                        <select class="form-select w-auto me-1">
                            <option value="0">전체</option>
                            <option value="1">확인</option>
                            <option value="2">승인</option>
                            <option value="3">거절</option>
                        </select>
                    </label>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table text-center text-nowrap">
                            <thead>
                            <tr>
                                <th style="width: 5%">#</th>
                                <th>예약상태</th>
                                <th>시작일자</th>
                                <th>종료일자</th>
                                <th>사용시간</th>
                                <th>제목</th>
                                <th>부서</th>
                                <th>이름</th>
                                <th>예약취소</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>확인</td>
                                <td>2024.04.02</td>
                                <td>2024.04.02</td>
                                <td>09:00~11:00</td>
                                <td><a href="./">리조트</a></td>
                                <td>영업팀</td>
                                <td>남명균</td>
                                <td><input type="checkbox" value="1"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">이전</a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">다음</a>
                        </li>
                    </ul>
                </nav>
                <button class="btn btn-primary float-end">확인</button>
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