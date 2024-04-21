<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <section class="section erp ms-auto me-auto">
        <div class="pagetitle">
            <h1>품목 관리</h1>
        </div>
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
                        <div class="p-3 d-flex flex-column" style="width: 1200px;">
                            <div >
                                <div style="line-height: 40px; padding-bottom: 3px;">
                                    <b>목록</b>
                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="example"></div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-start">
                                <button id="" class="btn btn-outline-primary me-1" data-bs-toggle="modal" data-bs-target="#register-modal"><i class="bi bi-database-add"></i>신규</button>
                                <button id="editButton" class="btn btn-outline-primary me-1" ><i class="bi bi-pencil"></i>수정</button>
                                <button id="exportButton" class="btn btn-primary"><i class="bi bi-file-earmark-spreadsheet-fill"></i> 저장</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<div class="modal" tabindex="-1" id="register-modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">품목 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addForm" class="text-nowrap text-end">
                    <div class="row">
                        <div class="col-6">
                            <div class="mb-3 row">
                                <label for="addName" class="col-4 col-form-label">품목명</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addName" name="name" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addStandard" class="col-4 col-form-label">규격</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addStandard" name="standard" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addCategory" class="col-4 col-form-label">카테고리</label>
                                <div class="col-8">
                                    <select type="text" class="form-control form-select" id="addCategory" name="categoryId">
                                        <option value="">전체</option>
                                        <c:forEach items="${category}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addSubmitButton">저장</button>
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<div class="modal" tabindex="-1" id="edit-modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header pb-0">
                <ul class="nav nav-tabs nav-tabs-bordered">
                    <li class="nav-item">
                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#supplier-overview">품목 정보</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">공급 거래처</button>
                    </li>
                </ul>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="position-relative" style="height: 50vh">
                    <div class="tab-content pt-2">
                        <div class="tab-pane fade show active profile-overview" id="supplier-overview">
                            <form id="editForm" class="text-nowrap text-end">
                                <div class="row">
                                    <div class="col-6">
                                        <input type="hidden" name="id" sw="value_id">
                                        <input type="hidden" name="managerId" sw="value_managerId">
                                        <div class="mb-3 row">
                                            <label for="editName" class="col-4 col-form-label">품목명</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editName" name="name" sw="value_name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editOwnerName" class="col-4 col-form-label">규격</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editOwnerName" name="ownerName" sw="value_standard">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="category" class="col-4 col-form-label">카테고리</label>
                                            <div class="col-8">
                                                <select type="text" class="form-control form-select" id="category" name="categoryId">
                                                    <option value=""></option>
                                                    <c:forEach items="${category}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>



                                    </div>
                                    <div class="col-6">
                                        <div class="mb-3 row">
                                            <label for="editStock" class="col-4 col-form-label">재고</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editStock" name="stock" sw="value_stock">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="mb-3 row">

                                        </div>
                                        <div class="mb-3 row">

                                        </div>
                                        <div class="mb-3 row">

                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="position-absolute end-0 bottom-0">
                                <button type="button" class="btn btn-primary" id="editSubmitButton">수정</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script src="/js/product/product.js" type="module"></script>
</body>

</html>