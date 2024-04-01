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
        <h1>전자결재</h1>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center " method="POST" action="#">
                    <label>
                        <select class="form-select w-auto me-1">
                            <option value="0">제목</option>
                            <option value="0">문서종류</option>
                            <option value="1">내용</option>
                            <option value="2">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="query" placeholder="검색" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>
        </div>
        <div class="row text-nowrap justify-content-center">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <table class="table text-center ">
                            <thead>
                                <tr>
                                    <th style="width: 5%">#</th>
                                    <th style="width: 60%">제목</th>
                                    <th style="width: 10%">문서종류</th>
                                    <th style="width: 10%">상신일</th>
                                    <th style="width: 5%">상태</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td class="text-start">3월 출장비 지출결의서입니다.</td>
                                    <td>지출결의서</td>
                                    <td>2024.03.12</td>
                                    <td>진행중</td>
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