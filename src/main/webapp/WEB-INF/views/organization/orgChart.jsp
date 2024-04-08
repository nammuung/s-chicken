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
        <h1>조직도</h1>
    </div>

    <div class="row justify-content-center">
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <div id="org-chart"></div>
                </div>
                <div class="card-body align-self-end">
                    <button id="dept-add-btn" class="btn btn-primary dept-btn disabled">하위에 부서추가</button>
                    <button id="dept-mod-btn" class="btn btn-primary dept-btn disabled">부서수정</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" tabindex="-1" id="dept-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">부서 등록</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row p-3">
                        <div class="col-4 mb-4">
                            <h5>상위 부서</h5>
                        </div>
                        <div class="col-8 mb-4">
                            <div id="upper-name" class="form-control h-100 bg-secondary-light"></div>
                        </div>
                        <div class="col-4 mb-4">
                            <h5>부서명</h5>
                        </div>
                        <div class="col-8">
                            <input id="dept-name" type="text" class="form-control" placeholder="부서명을 입력하세요">
                        </div>
                        <div class="col-4 mb-4">
                            <h5>부서 내선번호</h5>
                        </div>
                        <div class="col-8">
                            <input id="dept-number" type="text" class="form-control" placeholder="부서 내선번호를 입력하세요" maxlength="4">
                            <span id="con-num-notice" class="small text-danger d-none">내선번호가 중복되었습니다.</span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="dept-submit-btn" type="button" class="btn btn-primary">저장</button>
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script src="/js/orgChart/orgChartPage.js" type="module"></script>
</body>

</html>