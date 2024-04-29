<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<body>
<sec:authentication property="principal" var="user"/>
<div data-login-id="${user.id}"></div>
<!-- ======= Header ======= -->
<!-- ======= Sidebar ======= -->

<div>
    <div class="pagetitle" style="text-align: center;">
        <h1>결재상신함</h1>
    </div>
    
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto float-start">
                <label>
                    <select class="form-select w-auto me-1" name="kind" style="float: left;">
                        <option value="0">제목</option>
                        <option value="1">문서종류</option>
                        <option value="2">내용</option>
                        <option value="3">제목+내용</option>
                    </select>
                </label>
            </div>
            <div class="col-auto">
                <form class="search-form d-flex align-items-center">
                    <input type="text" name="search" placeholder="검색" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
            </div>
        </div>
        
        <div class="row text-nowrap justify-content-center">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <table class="table text-center">
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
			                                        
			                                        
			                                        <c:if test="${vo.templateVO.tempName eq '상여신청서'}"> <td class="text-start"><a href="#" onclick="openbonus(${vo.id})">${vo.content}</a></td></c:if>
			                                        
			                                        
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
	                        <li class="page-item <c:if test="${pager.page == page}">active</c:if>"><a class="page-link" href="/document/document?page=${pager.page}&search=${pager.search}&kind=${pager.kind}">${page}</a></li>
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
</div><!-- End #main -->
<!-- ======= Footer ======= -->

<script src="/js/document/writen/list.js"></script>
