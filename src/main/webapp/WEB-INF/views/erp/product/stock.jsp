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
            <h1>재고 관리</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="card">
                    <c:import url="./productSearch.jsp"/>
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
                                <button id="editButton" class="btn btn-outline-primary me-1" ><i class="bi bi-pencil"></i>수정</button>
                                <button id="exportButton" class="btn btn-primary"><i class="bi bi-file-earmark-spreadsheet-fill"></i>다운로드</button>
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
                                <label for="addCategory" class="col-4 col-form-label">카테고리</label>
                                <div class="col-8">
                                    <select type="text" class="form-control form-select" id="addCategory" name="category.id">
                                        <option value="">선택</option>
                                        <c:forEach items="${category}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <label for="addStandard" class="col-4 col-form-label">규격</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addStandard" name="standard" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addSellPrice" class="col-4 col-form-label">판매단가</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addStandard" name="sellPrice" value="">
                                </div>
                            </div>

                        </div>
                        <div class="col-6">
                            <div class="mb-3 row">
                                <label for="addName" class="col-4 col-form-label">품목명</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addName" name="name" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addUnit" class="col-4 col-form-label">단위</label>
                                <div class="col-8">
                                    <select type="text" class="form-control form-select" id="addUnit" name="unit.id">
                                        <option value="">선택</option>
                                        <c:forEach items="${unit}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#product-overview">재고 수정</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#supplier-overview">수불대장</button>
                    </li>
                </ul>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="position-relative" style="height: 400px">
                    <div class="tab-content pt-2">
                        <div class="tab-pane fade show active profile-overview" id="product-overview">
                            <form id="editForm" class="text-nowrap text-end">
                                <div class="row">
                                    <div class="col-6">
                                        <input type="hidden" name="product.id" sw="value_id">
                                        <div class="mb-3 row">
                                            <label for="category" disabled class="col-4 col-form-label">카테고리</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="category" sw="value_categoryName">
                                            </div>
                                        </div>

                                        <div class="mb-3 row">
                                            <label for="editOwnerName" class="col-4 col-form-label">규격</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editOwnerName" sw="value_standard">
                                            </div>
                                        </div>

                                        <div class="mb-3 row">
                                            <label for="editSellPrice" class="col-4 col-form-label">판매 단가</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editSellPrice" sw="value_sellPrice">
                                            </div>
                                        </div>


                                    </div>
                                    <div class="col-6">
                                        <div class="mb-3 row">
                                            <label for="editName" class="col-4 col-form-label">품목명</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editName" sw="value_name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="unit" class="col-4 col-form-label">단위</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="unit" sw="value_unitName"/>
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editStock" class="col-4 col-form-label">재고</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editStock" sw="value_stock">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <div class="mb-3 row">
                                            <label for="editComment" class="col-2 col-form-label">사유</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="editComment" name="history" placeholder="입고 및 출고 사유를 작성해주세요.">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editQuantity" class="col-2 col-form-label">수량</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control mb-1 text-end" id="editQuantity" name="quantity" value="0">
                                                <div class="d-flex justify-content-between">
                                                    <div>
                                                        <button type="button" onclick="calcQuantity(-100)" class="btn btn-outline-danger ">-100</button>
                                                        <button type="button" onclick="calcQuantity(-10)" class="btn btn-outline-danger ">-10</button>
                                                        <button type="button" onclick="calcQuantity(-1)" class="btn btn-outline-danger ">-1</button>
                                                    </div>
                                                    <div>
                                                        <button type="button" onclick="calcQuantity(1)" class="btn btn-outline-primary ">+1</button>
                                                        <button type="button" onclick="calcQuantity(10)" class="btn btn-outline-primary ">+10</button>
                                                        <button type="button" onclick="calcQuantity(100)" class="btn btn-outline-primary ">+100</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mb-3 row">

                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="position-absolute end-0 bottom-0">
                                <button type="button" class="btn btn-danger" id="editOutButton">출고</button>
                                <button type="button" class="btn btn-primary" id="editInButton">입고</button>
                            </div>
                        </div>
                        <div class="tab-pane fade profile-overview" id="supplier-overview">
                            <div class="row overflow-auto" style="max-height: 400px">
                                <table class="table text-center" id="supplierTable">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">내용</th>
                                            <th scope="col">수량</th>
                                            <th scope="col">날짜</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
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
<script src="/js/erp/stock.js" type="module"></script>
</body>

</html>