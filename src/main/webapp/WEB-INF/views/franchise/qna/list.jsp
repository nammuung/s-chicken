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
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-hover text-center text-nowrap">
                            <thead>
                            <tr>
                                <th style="width: 5%">#</th>
                                <th style="width: 70%">제목</th>
                                <th style="width: 10%">가맹점</th>
                                <th style="width: 10%">작성일</th>
                                <th style="width: 5%">답변여부</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="item" varStatus="status">
                                    <tr onclick="location.href='detail?id=${item.id}'">
                                            <td>${status.index+1}</td>
                                        <td class="text-start"><a href="#" class="link-dark">${item.title}</a></td>
                                            <td>${item.writer.name}</td>
                                            <td>${item.writeDate}</td>
                                            <td>
                                                <c:if test="${item.comment != null}">완료</c:if>
                                                <c:if test="${item.comment == null}">미완</c:if>
                                            </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
                            <li class="page-item active"><a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${page}">1</a></li>
                        </c:forEach>
                        <c:if test="${!pager.last}">
                            <li class="page-item">
                                <a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${pager.lastNum+1}">다음</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
                <button type="button" class="btn btn-primary float-end">답변하기</button>
            </div>
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
