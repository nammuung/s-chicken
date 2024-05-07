<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>

    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp" />
</head>

<body>

<!-- ======= Header ======= -->
<c:import url="../template/header.jsp" />
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp" />
<main id="main" class="main" data-id="${franchise.id}">
    <section class="section dashboard">
        <div class="pagetitle d-flex justify-content-between">
            <div class="d-flex justify-content-center">
                <h1>인사이트</h1>
            </div>
        </div>
        <div class="row ">
            <!-- Reports -->
            <div class="col-8" id="salesChartContainer">
                <div class="card">
                    <div class="filter">
                        <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                            <li class="dropdown-header text-start">
                                <h6>필터</h6>
                            </li>

                            <li><a class="dropdown-item" href="#" data-value="days">일</a></li>
                            <li><a class="dropdown-item" href="#" data-value="weeks">주</a></li>
                            <li><a class="dropdown-item" href="#" data-value="month">월</a></li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">매출추이<span id="perUnit">/일</span></h5>
                        <div id="reportsChart"></div>
                    </div>
                </div>
            </div><!-- End Reports -->
            <div class="col-4" id="menuPieContainer">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">메뉴<span id="">/월</span></h5>
                        <div class="pb-3 pt-3"></div>
                        <div class="pb-2"></div>
                        <div class="mt-3 pt-3 mb-3 pb-3" id="menuPie"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row ">
            <!-- Reports -->
            <div class="col-8" id="barChartContainer">
                <div class="card">
                    <div class="filter">
                        <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                            <li class="dropdown-header text-start">
                                <h6>필터</h6>
                            </li>
                            <li><a class="dropdown-item" href="#" data-value="days">일</a></li>
                            <li><a class="dropdown-item" href="#" data-value="weeks">주</a></li>
                            <li><a class="dropdown-item" href="#" data-value="month">월</a></li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">매출 TOP10<span id="perUnitBarChart">/일</span></h5>
                        <div id="barChart"></div>
                    </div>
                </div>
            </div><!-- End Reports -->
<%--            <div class="col-4" id="barChartContainer">--%>
<%--                <div class="card">--%>
<%--                    <div class="card-body">--%>
<%--                        <h5 class="card-title">메뉴<span id="perUnit">/일</span></h5>--%>
<%--                        <div class="pb-1"></div>--%>
<%--                        <div class="mt-3 pt-3" id="barChart"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </section>
</main>
<!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp" />
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp" />
<script type="module" src="/js/franchise/insight.js"></script>
</body>
</html>
