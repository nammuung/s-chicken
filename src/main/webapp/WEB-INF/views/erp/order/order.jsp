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
            <h1>발주</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="card">
                    <c:import url="../item/itemSearch.jsp"/>
                    <div class="card-body mt-3 row">
                        <div class="p-3 d-flex flex-column" style="width: 1200px;">
                            <div >
                                <div style="line-height: 40px; padding-bottom: 3px;">
                                    <b>목록</b>
                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="itemListContainer" ></div>
                                </div>
                                <div>
                                    <button class="btn btn-outline-primary" id="itemToOrderButton">추가</button>
                                </div>
                                <div class="" style="line-height: 40px; padding-bottom: 3px;">
                                    <b>발주</b>

                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="orderListContainer"></div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <button class="btn btn-outline-primary" id="addRowButton">행추가</button>
                                        <button class="btn btn-outline-danger" id="deleteRowButton">삭제</button>
                                    </div>
                                    <button class="btn btn-primary" id="orderPreviewButton">저장</button>
                                </div>
                            </div>
<%--                            <div class="d-flex justify-content-start">--%>
<%--                                <button id="" class="btn btn-outline-primary me-1" data-bs-toggle="modal" data-bs-target="#register-modal"><i class="bi bi-database-add"></i>신규</button>--%>
<%--                                <button id="editButton" class="btn btn-outline-primary me-1"><i class="bi bi-pencil"></i>수정</button>--%>
<%--                                <button id="exportButton" class="btn btn-primary"><i class="bi bi-file-earmark-spreadsheet-fill"></i> 저장</button>--%>
<%--                            </div>--%>
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
                <h5 class="modal-title">납품처 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <form id="addForm" class="text-nowrap text-end">
                    <div class="row">
                        <div class="col-6">
                            <div class="mb-3 row">
                                <label for="addFormName" class="col-4 col-form-label">납품처명</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addFormName" name="name" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addFormOwnerName" class="col-4 col-form-label">대표명</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addFormOwnerName" name="ownerName" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addFormContactNumber" class="col-4 col-form-label">연락처</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addFormContactNumber" name="contactNumber">
                                </div>
                            </div>



                        </div>
                        <div class="col-6">
                            <div class="mb-3 row">
                                <label for="addFormContractDate" class="col-4 col-form-label">계약일</label>
                                <div class="col-8">
                                    <input type="date" class="form-control" id="addFormContractDate" name="contractDate" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addFormRegistrationNumber" class="col-4 col-form-label">사업자번호</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" id="addFormRegistrationNumber" name="registrationNumber" value="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="mb-3 row">
                                <label for="addEmail" class="col-2 col-form-label">이메일</label>
                                <div class="col-10">
                                    <input type="email" class="form-control" id="addEmail" name="email" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addFormAddress" class="col-2 col-form-label">주소</label>
                                <div class="col-10">
                                    <input type="text" class="form-control" id="addFormAddress" name="address" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="addFormAddressDetail" class="col-2 col-form-label">상세주소</label>
                                <div class="col-10">
                                    <input type="text" class="form-control" id="addFormAddressDetail" name="addressDetail" value="">
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
                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#supplier-overview">거래처 정보</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#product-overview">계약 품목</button>
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
                                            <label for="editName" class="col-4 col-form-label">납품처명</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editName" name="name" sw="value_name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editOwnerName" class="col-4 col-form-label">대표명</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editOwnerName" name="ownerName" sw="value_ownerName">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editContactNumber" class="col-4 col-form-label">연락처</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="editContactNumber" name="contactNumber" sw="value_contactNumber">
                                            </div>
                                        </div>



                                    </div>
                                    <div class="col-6">
                                        <div class="mb-3 row">
                                            <label for="editContractDate" class="col-4 col-form-label">등록일</label>
                                            <div class="col-8">
                                                <input type="date" disabled class="form-control" id="editContractDate" name="contractDate" sw="value_contractDate">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editRegistrationNumber" class="col-4 col-form-label">사업자번호</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editRegistrationNumber" name="registrationNumber" sw="value_registrationNumber">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editManagerName" class="col-4 col-form-label">등록자</label>
                                            <div class="col-8">
                                                <input type="text" disabled class="form-control" id="editManagerName" name="managerName" sw="value_managerName">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <div class="mb-3 row">
                                            <label for="editEmail" class="col-2 col-form-label">이메일</label>
                                            <div class="col-10">
                                                <input type="email" class="form-control" id="editEmail" name="email" sw="value_email">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editAddress" class="col-2 col-form-label">주소</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="editAddress" name="address" sw="value_address">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="editAddressDetail" class="col-2 col-form-label">상세주소</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="editAddressDetail" name="addressDetail" sw="value_addressDetail">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="position-absolute end-0 bottom-0">
                                <button type="button" class="btn btn-primary" id="editSubmitButton">수정</button>
                            </div>
                        </div>
                        <div class="tab-pane fade profile-overview" id="product-overview">
                            <div class="row overflow-auto" style="max-height: 50vh">
                                <table class="table" id="productTable">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">품명</th>
                                        <th scope="col">규격</th>
                                        <th scope="col">단위</th>
                                        <th scope="col">계약단가</th>
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
<script type="module" src="/js/erp/order.js"> </script>

</body>

</html>