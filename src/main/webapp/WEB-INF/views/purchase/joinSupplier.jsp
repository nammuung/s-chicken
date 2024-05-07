<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <h1>납품저 관리</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="p-3 border-bottom">
                        <b>검색</b>
                        <form>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">거래처</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">담당자</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button class="btn btn-primary">검색</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-body mt-3 row">
                        <div class="col-4">
                            <b>결과</b>
                            <table class="table table-bordered text-nowrap text-center">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>번호</th>
                                    <th>남품처</th>
                                    <th>담당자</th>
                                </tr>
                                </thead>
                                <tbody class="">
                                <tr>
                                    <td>
                                        <input type="checkbox">
                                    </td>
                                    <th>1</th>
                                    <td>하림</td>
                                    <td>김경모</td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn-primary">추가</button>
                            </div>
                        </div>
                        <div class="col-8 border">
                            <ul class="nav nav-tabs nav-tabs-bordered">

                                <li class="nav-item">
                                    <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#supplier-overview">거래처 정보</button>
                                </li>

                                <li class="nav-item">
                                    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">거래처 품목</button>
                                </li>


                            </ul>
                            <div class="tab-content pt-2">
                                <div class="tab-pane fade show active profile-overview" id="supplier-overview">
                                    <div class="text-nowrap text-end mt-3">
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">거래처명</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">주소</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">상세주소</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">연락처</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">대표명</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">계약일</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">사업자번호</label>
                                            <div class="col-10">
                                                <input type="text" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="product" class="col-2 col-form-label">사업자등록증</label>
                                            <div class="col-10">
                                                <input type="file" class="form-control" id="product" value="">
                                            </div>
                                        </div>
                                        <div class="d-flex justify-content-end mb-2">
                                            <button class="btn btn-outline-danger me-3">삭제</button>
                                            <button class="btn btn-primary">수정</button>
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
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>