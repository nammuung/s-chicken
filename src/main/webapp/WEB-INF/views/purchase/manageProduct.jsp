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
        <h1>품목 관리</h1>
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
                                <label for="product" class="col-2 col-form-label">담당자</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">품번</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                                <label for="product" class="col-2 col-form-label">품목명</label>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="product" value="">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="product" class="col-2 col-form-label">규격</label>
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
                                    <th>거래처</th>
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
                                    <td>하림</td>
                                    <td>날개</td>
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
                        </div>
                        <div class="col-8 border">
                            <div class="text-nowrap text-end mt-3">
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">카테고리</label>
                                    <div class="col-10">
                                        <select class="form-control" id="product">
                                            <option value="1">정육</option>
                                            <option value="2">양념</option>
                                            <option value="3">비품</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">거래처</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">품명</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">규격</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">공급단가</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">판매단가</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">최소구매</label>
                                    <div class="col-10">
                                        <input type="text" class="form-control" id="product" value="">
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="product" class="col-2 col-form-label">단위</label>
                                    <div class="col-10">
                                        <select class="form-control" id="product">
                                            <option value="1">KG</option>
                                            <option value="2">BOX</option>
                                            <option value="3">L</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-end mb-2">
                                    <button class="btn btn-outline-danger me-3">삭제</button>
                                    <button class="btn btn-primary">수정</button>
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