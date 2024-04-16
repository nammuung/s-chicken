<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../../template/head.jsp"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/handsontable/dist/handsontable.full.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/handsontable/dist/handsontable.full.min.css" />
    <style>
        /*col:nth-child(2) {*/
        /*    width: 30px!important;*/
        /*}*/
    </style>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../../template/sidebar.jsp"/>
<main id="main" class="main" style="height: 80vh">
    <div class="pagetitle">
        <h1>품목 관리</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="p-3 border-bottom">
                        <b>검색</b>
                        <form id="searchForm">
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">품번</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" name="id" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">품목</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" name="name" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">규격</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" name="standard" value="">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-primary" id="searchButton">검색</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-body mt-3 row">
                        <div class="me-3" style="width: 500px">
                            <b>결과</b>
                            <div class="mb-3" style=" box-shadow: 0 0 0 1px #ccc inset;">
                                <div id="example"></div>
                            </div>
                        </div>
                        <div class="col ">
                            <b>결과</b>
                            <div class="border position-relative" style="height: 50vh">

                                <ul class="nav nav-tabs nav-tabs-bordered">

                                    <li class="nav-item">
                                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#supplier-overview">품목 정보</button>
                                    </li>

                                    <li class="nav-item">
                                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">납품처</button>
                                    </li>


                                </ul>
                                <div class="tab-content pt-2">
                                    <div class="tab-pane fade show active profile-overview" id="supplier-overview">
                                        <div class="text-nowrap text-end mt-3">
                                            <div class="mb-3 row">
                                                <label for="product" class="col-1 col-form-label">품목</label>
                                                <div class="col-2">
                                                    <input type="text" class="form-control" id="product" value="">
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="product" class="col-1 col-form-label">규격</label>
                                                <div class="col-2">
                                                    <input type="text" class="form-control" id="product" value="">
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="product" class="col-1 col-form-label">재고</label>
                                                <div class="col-2">
                                                    <input type="text" class="form-control" id="product" value="">
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-end mb-2 me-2 position-absolute end-0 bottom-0">
                                                <button class="btn btn-primary">수정</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="d-flex justify-content-end mt-3">
                            <button class="btn btn-primary">추가</button>
                        </div>
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
<script src="/js/product/product.js"></script>
</body>

</html>