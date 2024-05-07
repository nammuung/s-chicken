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
                <h1>매출 조회</h1>
                <nav class="ms-3">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/franchise/inquiry">가맹점 조회</a></li>
                        <li class="breadcrumb-item active">매출 조회</li>
                    </ol>
                </nav>
            </div>
        </div>
        <div class="d-flex justify-content-center m-3 text-black">
            <h1>${franchise.name}</h1>
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
            <!-- Sales Card -->
            <div class="col-4">
                <div class="row">
                    <div class="col-12" id="sellContainer">
                        <div class="card info-card sales-card">
                            <div class="card-body">
                                <h5 class="card-title">판매 <span>| 오늘</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-cart"></i>
                                    </div>
                                    <div class="ps-3">
                                        <h6 id="totalSell"></h6>
                                        <span class="text-success small pt-1 fw-bold"  id="sellIncreasePercent"></span> <span class="text-muted small pt-2 ps-1" id="sellIncreaseType"></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div><!-- End Sales Card -->
                    <div class="col-12" id="salesContainer">
                        <div class="card info-card revenue-card">
                            <div class="card-body">
                                <h5 class="card-title">매출 <span>| 오늘</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-currency-dollar"></i>
                                    </div>
                                    <div class="ps-3">
                                        <h6 id="totalSales"></h6>
                                        <span class="text-success small pt-1 fw-bold" id="salesIncreasePercent"></span> <span class="text-muted small pt-2 ps-1" id="salesIncreaseType"></span>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
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
</main>
<!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp" />
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp" />
<script type="module" src="/js/franchise/inquirySales.js"></script>
</body>
</html>
