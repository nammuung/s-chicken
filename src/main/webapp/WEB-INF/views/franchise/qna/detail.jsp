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
    <div class="pagetitle">
        <h1>가맹점 QnA</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="d-flex justify-content-between p-3 border-bottom">
                        <div>
                            <span class="me-3">${vo.writer.name}</span>
                            <b>${vo.title}</b>
                        </div>
                        <span class="text-muted">${vo.writeDate}</span>
                    </div>
                    <div class="card-body pt-3" style="min-height: 10vh">
                        ${vo.content}
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div class="card">
                <div class="card-body mt-3">
                    <div class="mb-3">
                        <a href="/notice/list" class="link-body-emphasis">
                            <i class="bi bi-list toggle-sidebar-btn button"></i>
                            <span>목록</span>
                        </a>
                    </div>

                    <div class="mb-2">
                        <i class="bi bi-caret-up-fill toggle-sidebar-btn button"></i>
                        <span class="me-3">이전</span>
                        <a href="/notice/detail" class="link-body-emphasis">사내 전산망 교체로 전산 사용불가</a>
                    </div>
                    <div>
                        <i class="bi bi-caret-down-fill toggle-sidebar-btn button"></i>
                        <span class="me-3">다음</span>
                        <a href="/notice/detail" class="link-body-emphasis">사내 전산망 교체로 전산 사용불가</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <span class="card-title">답변</span>
                </div>
                <div class="card-body mt-3">
                    <div class="d-flex align-items-center mb-2">
                        <img width="50" height="50" src="avatar.png" alt="프로필" class="me-3">
                        <div class="d-flex flex-column">
                            <span><b>김경모 사원</b></span>
                            <span>영업3팀</span>
                        </div>
                    </div>
                    <div>
                        <p>군사재판을 관할하기 위하여 특별법원으로서 군사법원을 둘 수 있다.</p>
                        <p>대통령은 국회에 출석하여 발언하거나 서한으로 의견을 표시할 수 있다.</p>
                    </div>
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
                        <label for="floatingTextarea2">답변</label>
                        <button class="btn btn-primary float-end mt-3" type="submit">답변하기</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
</body>

</html>