<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
        <h1>${board} 게시판</h1>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">

                <form class="search-form d-flex align-items-center">
                    <label for="search">
                        <select class="form-select w-auto me-1" name="kind">
                            <option value="kind1">제목</option>
                            <option value="kind2">내용</option>
                            <option value="kind3">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" id="search" name="search" placeholder="검색">
                    <button type="submit"><i class="bi bi-search"></i></button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table text-center text-nowrap">
                            <thead>
	                            <tr>
	                                <th style="width: 5%">no</th>
	                                <th style="width: 70%">제목</th>
	                                <th style="width: 10%">작성일</th>
	                                <th style="width: 10%">등록자</th>
	                                <th style="width: 5%">조회수</th>
	                            </tr>
                            </thead>
                            <tbody>
                            	
	                            	<c:forEach items="${list}" var="vo">
	                            		<c:if test="${vo.sort eq 1}">
				                            <tr id="important">
                                                <input type="hidden" id="important_val" value="${vo.important}">
                                                
				                                <td>${vo.id}</td>
				                                <td class="text-start"><a href="./detail?id=${vo.id}">${vo.title}</a></td>
				                                <td>${vo.writeDate}</td>
				                                <td>${vo.employeeVO.name}</td>
				                                <td>${vo.hit}</td>
				                            </tr>
			                            </c:if>
		                            </c:forEach>
		                            
		                            <c:forEach items="${list}" var="vo">
	                            		<c:if test="${vo.sort eq 0}">
				                            <tr id="important">
                                                <input type="hidden" id="important_val" value="${vo.important}">
				                                <td>${vo.id}</td>
				                                <td class="text-start"><a href="./detail?id=${vo.id}">${vo.title}</a></td>
				                                <td>${vo.writeDate}</td>
				                                
				                                	<td>${vo.employeeVO.name}</td>
				                                
				                                <td>${vo.hit}</td>
				                            </tr>
			                            </c:if>
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
		                    <li class="page-item <c:if test="${pager.page == page}">active</c:if>"><a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${page}">${page}</a></li>
		                </c:forEach>
		                <c:if test="${!pager.last}">
		                    <li class="page-item">
		                        <a class="page-link" href="list?kind=${pager.kind}&search=${pager.search}&page=${pager.lastNum+1}">다음</a>
		                    </li>
		                </c:if>
		            </ul>                   
                </nav>
            </div>
            
        </div>
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <sec:authorize
											access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">        	               	
               		<a href="./write" class="btn btn-primary">글쓰기</a>
               	</sec:authorize>
            </div>
        </div>
        
    </section>
</main><!-- End #main -->
<script src="../js/board/list.js"></script>
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>