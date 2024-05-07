<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<main id="main" class="main" data-id="${vo.id}">
    <div class="pagetitle">
        <a href="./list">
            <h1>가맹점 QnA</h1>
        </a>
    </div>
    <section class="section">
        <div class="row">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>안내</strong> 목록에서 답변이 없는 질문을 순차적으로 볼 수 있습니다.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
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
                        <%--                        <sec:authorize access="hasAnyRole('ADMIN', 'FRANCHISE')">--%>
                        <div class="d-flex justify-content-end">
                            <a class="btn btn-primary me-1" href="./update?id=${vo.id}">수정</a>
                            <button class="btn btn-outline-danger" id="deleteButton">삭제</button>
                        </div>
                        <%--                        </sec:authorize>--%>
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

                    <c:if test="${vo.nextQna != null}">
                        <div class="mb-2">
                            <i class="bi bi-caret-up-fill toggle-sidebar-btn button"></i>
                            <span class="me-3">이전</span>
                            <a href="?id=${vo.nextQna.id}" class="link-body-emphasis">${vo.nextQna.title}</a>
                        </div>
                    </c:if>
                    <c:if test="${vo.preQna != null}">
                        <div>
                            <i class="bi bi-caret-down-fill toggle-sidebar-btn button"></i>
                            <span class="me-3">다음</span>
                            <a href="?id=${vo.preQna.id}" class="link-body-emphasis">${vo.preQna.title}</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <%--        <sec:authorize access="hasAnyRole('ADMIN','EMPLOYEE')">--%>
        <c:if test="${vo.comment == null}">
            <div class="form-floating mb-3 position-relative" style="height: 10vh;" id="commentInputContainer">
                <textarea maxlength="500" style="height: 100%; resize:none;" name="content" class="form-control" placeholder="Leave a comment here" id="content"></textarea>
                <label for="content">답변</label>
                <button class="btn btn-primary position-absolute end-0 bottom-0 mb-3 me-3" type="button" id="commentButton">답변하기</button>
            </div>
        </c:if>
        <%--        </sec:authorize>--%>
        <c:if test="${vo.comment != null}">
        <div class="col-12">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span class="card-title p-0 m-0">답변</span>
                    <div class="text-nowrap text-muted">
                        <b class="me-1">${vo.comment.employee.name} 매니저</b> ${vo.comment.writeDate}
                    </div>

                </div>
                <div class="card-body mt-3" id="commentBox">
                    <c:if test="${vo.comment != null}">
                        <div class="d-flex mb-2">
                            <div class="d-flex justify-content-between w-100">
                                <div class="d-flex">
                                    <div class="me-3 text-nowrap">
                                        <b></b>
                                    </div>
                                    <div class="me-3">
                                        <c:out value="${vo.comment.content}" />
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        </c:if>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script src="/js/qna/script.js"></script>
</body>

</html>