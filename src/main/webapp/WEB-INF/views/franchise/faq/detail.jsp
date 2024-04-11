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
        <h1>가맹점 FAQ</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="d-flex justify-content-between p-3 border-bottom">
                        <div>
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

                    <c:if test="${vo.nextFaq != null}">
                        <div class="mb-2">
                            <i class="bi bi-caret-up-fill toggle-sidebar-btn button"></i>
                            <span class="me-3">이전</span>
                            <a href="?id=${vo.nextFaq.id}" class="link-body-emphasis">${vo.nextFaq.title}</a>
                        </div>
                    </c:if>
                    <c:if test="${vo.preFaq != null}">
                        <div>
                            <i class="bi bi-caret-down-fill toggle-sidebar-btn button"></i>
                            <span class="me-3">다음</span>
                            <a href="?id=${vo.preFaq.id}" class="link-body-emphasis">${vo.preFaq.title}</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script>
    const deleteButton = document.getElementById("deleteButton");
    deleteButton.addEventListener("click", async function (event) {
        if(confirm("삭제 하시겠습니까?")){
            const response = await fetch("/v1/api/franchise/faq/${vo.id}",{
                method: "DELETE",
            });
            const result = await response.json();

            if(result.status === "OK"){
                alert(result.message)
                location.href = "./list"
            } else {
                alert(result.message)
            }
        }
    })
</script>
</body>

</html>