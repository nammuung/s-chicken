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
<main id="main" class="main erp" style="height: 80vh">
    <div class="pagetitle">
        <h1>납품처 관리</h1>
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
                                    <label for="searchName" class="col-3 col-form-label text-nowrap">납품처</label>
                                    <div class="col-9">
                                        <input type="text" class="form-control" id="searchName" name="name" value="">
                                    </div>
                                </div>
                                <div class="row col">
                                    <label for="searchWriter" class="col-3 col-form-label text-nowrap">등록자</label>
                                    <div class="col-9">
                                        <input type="text" class="form-control" id="searchWriter" name="writer" value="">
                                    </div>
                                </div>
                                <div class="row col">

                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="row col">

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
                        <div class="p-0 ms-3" style="width: 500px">
                            <div class="mb-1">
                                <b >결과</b>
                            </div>
                            <div class="mb-3" style=" box-shadow: 0 0 0 1px #ccc inset;">
                                <div id="example"></div>
                            </div>
                            <div class="d-flex justify-content-end mt-3">
                                <button id="exportButton" class="btn btn-primary"><i class="bi bi-file-earmark-spreadsheet-fill"></i> 저장</button>
                            </div>
                        </div>
                        <div class="col ">
                            <div class="mb-1">
                                <b >상세</b>
                            </div>
                            <div class="border position-relative" style="height: 50vh">

                                <ul class="nav nav-tabs nav-tabs-bordered">

                                    <li class="nav-item">
                                        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#supplier-overview">납품처 정보</button>
                                    </li>

                                    <li class="nav-item">
                                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#item-list">납품 품목</button>
                                    </li>

                                    <li class="nav-item">
                                        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#supplier-edit">수정</button>
                                    </li>

                                </ul>
                                <div class="tab-content profile">
                                    <div class="mt-2 p-2 tab-pane fade show active supplier-overview profile-overview" id="supplier-overview">
                                        <form id="detailForm" class="text-nowrap text-end">
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="mb-3 row">
                                                        <label for="name" class="col-4 col-form-label label">납품처명</label>
                                                        <div id="test" class="col-8 text-start align-self-center" sw="inner_name">

                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="ownerName" class="col-4 col-form-label label">대표명</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_ownerName">
                                                            대표명
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="contactNumber" class="col-4 col-form-label label">연락처</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_contactNumber">
                                                            연락처
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="address" class="col-4 col-form-label label">주소</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_address">
                                                            주소
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="addressDetail" class="col-4 col-form-label label">상세주소</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_addressDetail">
                                                            상세 주소
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="mb-3 row">
                                                        <label for="contractDate" class="col-4 col-form-label label">계약일</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_contractDate">
                                                            계약일
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="registerNumber" class="col-4 col-form-label label">사업자번호</label>
                                                        <div class="col-8 text-start align-self-center" sw="inner_registrationNumber">
                                                            사업자번호
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="attach" class="col-4 col-form-label label">사업자등록증</label>
                                                        <div class="col-8 text-start align-self-center">
                                                            사업자등록증
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="d-flex justify-content-end mb-2 me-2 position-absolute end-0 bottom-0">
                                                <button type="button" class="btn btn-primary d-none" id="modifyButton">수정</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="tab-content">
                                    <div class="mt-2 p-2 tab-pane fade item-list" id="item-list">
                                    </div>
                                </div>
                                <div class="tab-content ">
                                    <div class="mt-2 p-2 tab-pane fade supplier-edit" id="supplier-edit">
                                        <form id="editForm" class="text-nowrap text-end">
                                            <div class="row">
                                                <div class="col-6">
                                                    <div class="mb-3 row">
                                                        <label for="name" class="col-4 col-form-label">납품처명</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="name" name="name" value="">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="ownerName" class="col-4 col-form-label">대표명</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="ownerName" name="ownerName" value="">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="contactNumber" class="col-4 col-form-label">연락처</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="contactNumber">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="address" class="col-4 col-form-label">주소</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="address" name="address" value="">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="addressDetail" class="col-4 col-form-label">상세주소</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="addressDetail" name="addressDetail" value="">
                                                        </div>
                                                    </div>


                                                </div>
                                                <div class="col-6">
                                                    <div class="mb-3 row">
                                                        <label for="contractDate" class="col-4 col-form-label">계약일</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="contractDate" name="contractDate" value="">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="registrationNumber" class="col-4 col-form-label">사업자번호</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="registrationNumber" name="registrationNumber" value="">
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 row">
                                                        <label for="attach" class="col-4 col-form-label">사업자등록증</label>
                                                        <div class="col-8">
                                                            <input type="text" class="form-control" id="attach" name="attach" value="">
                                                        </div>
                                                    </div>
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
<script>


    //사용자단
    // sw.setState("name", "김경모");
    // sw.init();




</script>
<script src="/js/supplier/supplier.js" type="module"></script>

</body>

</html>