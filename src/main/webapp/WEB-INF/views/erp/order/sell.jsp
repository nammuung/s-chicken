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
            <h1>판매내역</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="card">
                    <c:import url="./sellSearch.jsp"/>
                    <div class="card-body mt-3 row">
                        <div class="p-3 d-flex" style="width: 1200px;">
                            <div class="me-2" style="width: 500px;">
                                <div style="line-height: 40px; padding-bottom: 3px;">
                                    <b>목록</b>
                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="orderListContainer" ></div>
                                </div>
                            </div>
                            <div style="width: 700px;">
                                <div class="d-flex justify-content-between" style="line-height: 40px; padding-bottom: 3px;">
                                    <b>상세</b>
                                    <div>
                                        <button class="btn btn-outline-primary d-none" id="orderPreviewButton">발주서</button>
                                        <button class="btn btn-primary d-none" id="orderButton">발주</button>
                                    </div>
                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="productListContainer"></div>
                                </div>
                                <div class="d-flex justify-content-end">
                                    <div id="modifyButtons" class="d-none">
                                        <button class="btn btn-outline-primary" id="approveOrderButton">접수</button>
                                        <button class="btn btn-outline-danger" id="refuseOrderButton">반려</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<div class="modal" tabindex="-1" id="receive-modal">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">입고 현황</h5>
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
                                        <c:forEach items="${category}" var="dept">
                                            <option value="${dept.id}">${dept.name}</option>
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
                                        <c:forEach items="${unit}" var="dept">
                                            <option value="${dept.id}">${dept.name}</option>
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
                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#product-overview">품목 정보</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#supplier-overview">공급 거래처</button>
                    </li>
                </ul>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="position-relative" style="height: 50vh">
                    <div class="tab-content pt-2">
                        <div class="tab-pane fade show active profile-overview" id="product-overview">
                            <form id="editForm" class="text-nowrap text-end">
                                <div class="row">
                                    <div class="col-6">
                                        <input type="hidden" name="id" sw="value_id">
                                        <input type="hidden" name="managerId" sw="value_managerId">
                                        <div class="mb-3 row">
                                            <label for="category" class="col-4 col-form-label">카테고리</label>
                                            <div class="col-8">
                                                <select type="text" class="form-control form-select" id="category" name="category.id">
                                                    <option value=""></option>
                                                    <c:forEach items="${category}" var="dept">
                                                        <option value="${dept.id}">${dept.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="mb-3 row">
                                            <label for="editOwnerName" class="col-4 col-form-label">규격</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editOwnerName" name="standard" sw="value_standard">
                                            </div>
                                        </div>

                                        <div class="mb-3 row">
                                            <label for="editSellPrice" class="col-4 col-form-label">판매 단가</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editSellPrice" name="sellPrice" sw="value_sellPrice">
                                            </div>
                                        </div>


                                    </div>
                                    <div class="col-6">
                                        <div class="mb-3 row">
                                            <label for="editName" class="col-4 col-form-label">품목명</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editName" name="name" sw="value_name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="unit" class="col-4 col-form-label">단위</label>
                                            <div class="col-8">
                                                <select type="text" class="form-control form-select" id="unit" name="unit.id">
                                                    <option value=""></option>
                                                    <c:forEach items="${unit}" var="dept">
                                                        <option value="${dept.id}">${dept.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
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
                        <div class="tab-pane fade profile-overview" id="supplier-overview">
                            <div class="row overflow-auto" style="max-height: 50vh">
                                <table class="table" id="supplierTable">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">공급 거래처</th>
                                        <th scope="col">공급 가격</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                <div class="" style="box-shadow: 0 0 0 1px #ccc inset;" >
                    <div id="receiveListContainer"></div>
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
<script type="module" src="/js/erp/sell.js"> </script>

</body>

</html>
