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
        <a href="./list">
            <h1>가맹점 FAQ</h1>
        </a>
    </div>
    <section class="section ms-3 me-3">
        <c:if test="${importantList != null}">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">

                            <div class="d-flex justify-content-center m-3 p-3">
                                <h1><b>자주 묻는 질문</b></h1>
                            </div>
                            <div class="accordion accordion-flush" id="accordionFlushExample">
                                <c:forEach items="${importantList}" var="item" varStatus="status">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header">
                                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${status.index}" aria-expanded="false" aria-controls="flush-collapse${status.index}">
                                                    ${item.title}
                                            </button>
                                        </h2>
                                        <div id="flush-collapse${status.index}" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                                            <div class="accordion-body">${item.content}</div>
                                            <div class="d-flex justify-content-end mb-3">
                                                <a href="/franchise/faq/detail?id=${item.id}">
                                                    <span class="text-muted">자세히 보기</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center ">
                    <label>
                        <select class="form-select w-auto me-1" name="kind">
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                            <option value="title+content">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="search" placeholder="검색" title="Enter search keyword" value="${pager.search}">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover text-center ">
                            <thead>
                            <tr>
                                <th style="width: 5%">no</th>
                                <th style="width: 70%">제목</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${commonList}" var="item" varStatus="status">
                                    <tr onclick="location.href = '/franchise/faq/detail?id=${item.id}'">
                                        <td>${status.index+1+pager.startIndex}</td>
                                        <td class="text-start">
                                            <a href="#" class="link-dark">${item.title}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${!pager.start}">
                    <li class="page-item">
                        <a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${pager.startNum-1}">이전</a>
                    </li>
                </c:if>
                <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="page">
                    <li class="page-item <c:if test="${pager.page == page}">active</c:if>"><a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${page}">${page}</a></li>
                </c:forEach>
                <c:if test="${!pager.last}">
                    <li class="page-item">
                        <a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${pager.lastNum+1}">다음</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="d-flex justify-content-end">
            <a href="/franchise/faq/add" class="btn btn-primary float-end">FAQ 작성</a>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script>
    Array.prototype.slice.call(document.querySelector("select"))
        .forEach(options => {
            if(options.value == '${pager.kind}') options.selected = true;
        })
</script>
</body>

</html>
