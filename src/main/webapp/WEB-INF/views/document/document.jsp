<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
</head>

<body>
<sec:authentication property="principal" var="user"/>
<div data-login-id="${user.id}"></div>
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
			class="nav nav-pills list-group-horizontal d-flex justify-content-center" id="cateList"
			name="category">
			
			<li class="nav-item px-2"><a
				class="nav-link active BOARDCATEGORY" href="/document/document?category=all" data-category="all">전체</a></li>
				
			<li class="nav-item px-2" name="category0" id="category0"><a
				class="nav-link BOARDCATEGORY" href="/document/document?category=category0" data-category="category0">진행</a></li>
				
			<li class="nav-item px-2" name="category1" id="category1"><a
				class="nav-link BOARDCATEGORY" href="/document/document?category=category1" data-category="category1">반려</a></li>
				
			<li class="nav-item px-2" name="category2" id="category2"><a
				class="nav-link BOARDCATEGORY" href="/document/document?category=category2" data-category="category2">완료</a></li>
		</ul>
	</div>
    
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center">
                    <label>
                        <select class="form-select w-auto me-1" name="kind">
                            <option value="0">제목</option>
                            <option value="1">문서종류</option>
                            <option value="2">내용</option>
                            <option value="3">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="search" placeholder="검색" title="Enter search keyword">
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
                           	<c:forEach items="${list}" var="vo">
	                            <c:if test="${vo.writerId eq user.id}">
		                            <tbody>
		
			                                    <tr>
			                                        <td>${vo.id}</td>
			                                        
			                                        
			                                        <c:if test="${vo.templateVO.tempName eq '상여신청서'}"> <td class="text-start"><a href="#" onclick="openbonus(${vo.id})">${vo.title}</a></td></c:if>
			                                        <c:if test="${vo.templateVO.tempName eq '휴가계획서'}"> <td class="text-start"><a href="#" onclick="openvacation(${vo.id})">${vo.title}</a></td></c:if>
			                                        
			                                        <td>${vo.templateVO.tempName}</td>
			                                        <td>${vo.writeDate}</td>
			                                        <c:if test="${vo.status eq 0}"> <td>진행중</td> </c:if>
			                                        <c:if test="${vo.status eq 1}"> <td>완료</td> </c:if>
			                                        <c:if test="${vo.status eq 2}"> <td>반려</td> </c:if>
			                                    </tr>
		                                    
		                            </tbody>
	                            </c:if>
                           </c:forEach>
                        </table>
                    </div>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                    	<c:if test="${!pager.start}">
	                        <li class="page-item disabled">
	                            <a class="page-link" href="/document?page=${pager.startNum-1}&search=${pager.search}&kind=${pager.kind}" tabindex="-1" aria-disabled="true">이전</a>
	                        </li>
						</c:if>                      
						<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="page">
                            
	                        <li class="page-item <c:if test='${pager.page == page}'>active</c:if>"><a class="page-link" href="/document/document?page=${page}&search=${pager.search}&kind=${pager.kind}">${page}</a></li>
	                    </c:forEach>                        
                        <c:if test="${!pager.last}">
	                        <li class="page-item">
	                            <a class="page-link" href="/document/document?page=${pager.lastNum-1}&search=${pager.search}&kind=${pager.kind}">다음</a>
	                        </li>
                        </c:if>
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
<script src="/js/document/writen/list.js"></script>
</body>

</html>