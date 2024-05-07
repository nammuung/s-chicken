<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../../template/head.jsp"/>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <a href="./list">
            <h1>매출</h1>
        </a>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center ">
                    <label>
                        <select class="form-select w-auto me-1" name="kind">
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                            <option value="title+content">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="search" placeholder="검색" title="Enter search keyword" value="${pager.search}">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover text-center text-nowrap">
                            <thead>
                            <tr>
                                <th style="width: 5%">주문번호</th>
                                <th style="width: 70%">메뉴</th>
                                <th style="width: 10%">금액</th>
                                <th style="width: 10%">날짜</th>
                            </tr>
                            </thead>
                            <tbody id="salesListContainer">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script type="module" src="/js/franchise/sales.js"></script>
</body>

</html>
