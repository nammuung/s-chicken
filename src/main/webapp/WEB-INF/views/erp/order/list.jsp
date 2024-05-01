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
            <h1>입고</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="card">
                    <c:import url="../order/listSearch.jsp"/>
                    <div class="card-body mt-3 row">
                        <div class="p-3 d-flex flex-column" style="width: 1200px;">
                            <div class="">
                                <div class="d-flex justify-content-between" style="line-height: 40px; padding-bottom: 3px;">
                                    <b>목록</b>
                                    <div>
                                        <button class="btn btn-outline-primary d-none" id="allCompleteButton">전체입고</button>
                                        <button class="btn btn-outline-primary d-none" id="history">내역</button>
                                        <button class="btn btn-outline-primary d-none" id="orderPreviewButton">발주서</button>
                                    </div>
                                </div>
                                <div class="" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="orderListContainer"></div>
                                </div>
                                <div class="" style="line-height: 40px; padding-bottom: 3px;">
                                    <b>상세</b>
                                </div>
                                <div class="mb-3" style="box-shadow: 0 0 0 1px #ccc inset;" >
                                    <div id="itemListContainer"></div>
                                </div>
                                <div class="d-flex justify-content-end">
                                    <div class="d-none" id="modifyButtons">
                                        <button class="btn btn-outline-primary" id="saveChangeButton">저장</button>
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
<div class="modal" tabindex="-1" id="history-modal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">입고 내역</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" style="height: 400px">
                <div class="row overflow-auto" style="max-height: 400px">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">날짜</th>
                                <th scope="col">내용</th>
                            </tr>
                        </thead>
                        <tbody id="historyListContainer">

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addSubmitButton">저장</button>
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>


<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script type="module" src="/js/erp/orderList.js"> </script>

</body>

</html>