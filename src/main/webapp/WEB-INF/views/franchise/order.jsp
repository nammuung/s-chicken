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
        <h1>발주</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="p-3 border-bottom">
                        <b>검색</b>
                        <form>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">상품명</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">상품코드</label>
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
                                    <th>코드</th>
                                    <th>품명</th>
                                    <th>규격</th>
                                </tr>
                                </thead>
                                <tbody class="">
                                <tr>
                                    <td>
                                        <input type="checkbox">
                                    </td>
                                    <th>1</th>
                                    <td>C101-1</td>
                                    <td>닭다리</td>
                                    <td>9호</td>
                                </tr>
                                </tbody>
                            </table>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">이전</a>
                                    </li>
                                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#">다음</a>
                                    </li>
                                </ul>
                            </nav>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn-primary">추가</button>
                            </div>
                        </div>
                        <div class="col-8">
                            <b>선택</b>
                            <table class="table table-bordered text-nowrap text-center">
                                <thead>
                                <tr>
                                    <th style="width: 5%">#</th>
                                    <th style="width: 5%">번호</th>
                                    <th style="width: 5%">코드</th>
                                    <th style="width: 70%">품명</th>
                                    <th style="width: 5%">규격</th>
                                    <th style="width: 5%">수량</th>
                                    <th style="width: 5%">단위</th>
                                </tr>
                                </thead>
                                <tbody class="">
                                <tr>
                                    <td>
                                        <input type="checkbox">
                                    </td>
                                    <th>1</th>
                                    <td>C101-1</td>
                                    <td>닭다리</td>
                                    <td>9호</td>
                                    <td class="p-0">
                                        <input type="text" class="form-control border-0" placeholder="" value="0">
                                    </td>
                                    <td>KG</td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-end">
                                <button type="button" class="btn btn-outline-danger me-3">삭제</button>
                                <button type="button" class="btn btn-primary">발주</button>
                            </div>
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