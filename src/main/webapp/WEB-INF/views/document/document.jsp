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
    <div class="pagetitle" style="text-align: center;">
        <h1>결재상신함</h1>
    </div>
    
    	<div class="container mb-4" name="categorySelect">
		<ul
			class="nav nav-pills list-group-horizontal d-flex justify-content-center"
			name="categorySelect">
			<li class="nav-item px-2"><a
				class="nav-link active BOARDCATEGORY" href="#" data-category="all">전체</a></li>
			<li class="nav-item px-2" name="category0" id="category0"><a
				class="nav-link BOARDCATEGORY" href="#" data-category="category0">진행</a></li>
			<li class="nav-item px-2" name="category1" id="category1"><a
				class="nav-link BOARDCATEGORY" href="#" data-category="category1">반려</a></li>
			<li class="nav-item px-2" name="category2" id="category2"><a
				class="nav-link BOARDCATEGORY" href="#" data-category="category2">완료</a></li>
		</ul>
	</div>
    
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center " method="POST" action="#">
                    <label>
                        <select class="form-select w-auto me-1">
                            <option value="0">제목</option>
                            <option value="1">문서종류</option>
                            <option value="2">내용</option>
                            <option value="3">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="query" placeholder="검색" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>
        </div>
        <div class="row text-nowrap justify-content-center">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <table class="table text-center ">
                            <thead>
                                <tr>
                                    <th style="width: 15%">문서번호</th>
                                    <th style="width: 50%">제목</th>
                                    <th style="width: 10%">문서종류</th>
                                    <th style="width: 10%">상신일</th>
                                    <th style="width: 5%">상태</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="vo">
                                    <tr>
                                        <td>${vo.id}</td>
                                        <td class="text-start">${vo.content}</td>
                                        <td>${vo.templateVO.tempName}</td>
                                        <td>${vo.writeDate}</td>
                                        <c:if test="${vo.status eq 0}"> <td>진행중</td> </c:if>
                                        <c:if test="${vo.status eq 1}"> <td>반려</td> </c:if>
                                        <c:if test="${vo.status eq 2}"> <td>완료</td> </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
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
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>