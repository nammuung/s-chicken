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
                                <div class="row col">
                                    <label for="searchId" class="col-3 col-form-label text-nowrap">품번</label>
                                    <div class="col-9">
                                        <input type="text" class="form-control" id="searchId" name="id" value="">
                                    </div>
                                </div>
                                <div class="row col">
                                    <label for="searchName" class="col-3 col-form-label text-nowrap">품목</label>
                                    <div class="col-9">
                                        <input type="text" class="form-control" id="searchName" name="name" value="">
                                    </div>
                                </div>
                                <div class="row col">
                                    <label for="searchStandard" class="col-3 col-form-label text-nowrap">규격</label>
                                    <div class="col-9">
                                        <input type="text" class="form-control" id="searchStandard" name="standard" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="row col">
                                    <label for="searchCategory" class="col-3 col-form-label text-nowrap">카테고리</label>
                                    <div class="col-9">
                                        <select type="text" class="form-control form-select" id="searchCategory" name="categoryId">
                                            <option value="">전체</option>
                                            <c:forEach items="${category}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row col">

                                </div>
                                <div class="row col">

                                </div>
                            </div>

                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-primary" id="searchButton">검색</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-body mt-3 row">
                        <div class="me-3" style="width: 500px">
                            <div class="mb-1">
                                <b >결과</b>
                            </div>
                            <div class="mb-3" style=" box-shadow: 0 0 0 1px #ccc inset;">
                                <div id="example"></div>
                            </div>
                            <div class="d-flex justify-content-end mt-3">
                                <button id="exportButton" class="btn btn-primary">내보내기</button>
                            </div>
                        </div>
                        <div class="col ">
                            <div class="mb-1">
                                <b >상세</b>
                            </div>
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
                                        <form id="detailForm" class="text-nowrap text-end mt-3 ms-1">
                                            <div class="mb-3 row">
                                                <label for="id" class="col-2 col-form-label">품번</label>
                                                <div class="col-6">
                                                    <input type="text" readonly class="form-control" id="id" name="id" value="">
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="name" class="col-2 col-form-label">품목</label>
                                                <div class="col-6">
                                                    <input type="text" class="form-control" id="name" name="name" value="">
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="standard" class="col-2 col-form-label">규격</label>
                                                <div class="col-6">
                                                    <input type="text" class="form-control" id="standard" name="standard" value="">
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="category" class="col-2 col-form-label">카테고리</label>
                                                <div class="col-6">
                                                    <select type="text" class="form-control form-select" id="category" name="categoryId">
                                                        <option value=""></option>
                                                        <c:forEach items="${category}" var="item">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mb-3 row">
                                                <label for="stock" class="col-2 col-form-label">재고</label>
                                                <div class="col-6">
                                                    <input type="text" disabled readonly class="form-control" id="stock" value="">
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-end mb-2 me-2 position-absolute end-0 bottom-0">
                                                <button type="button" class="btn btn-primary d-none" id="modifyButton">수정</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>
                            <div class="d-flex justify-content-end mt-3">
                                <button id="addButton" class="btn btn-primary">추가</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<div class="modal" tabindex="-1" id="common-modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">상품추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addForm" class="text-nowrap text-end mt-3 ms-3">
                    <div class="mb-3 row">
                        <label for="name" class="col-2 col-form-label">품목</label>
                        <div class="col-4">
                            <input type="text" class="form-control" name="name" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="standard" class="col-2 col-form-label">규격</label>
                        <div class="col-4">
                            <input type="text" class="form-control" name="standard" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="category" class="col-2 col-form-label">카테고리</label>
                        <div class="col-4">
                            <select type="text" class="form-control form-select" name="categoryId">
                                <c:forEach items="${category}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveButton">저장</button>
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script src="/js/product/product.js"></script>
</body>

</html>