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
        <h1>대표 게시판</h1>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center " method="POST">
                    <label>
                        <select class="form-select w-auto me-1">
                            <option value="0">제목</option>
                            <option value="1">내용</option>
                            <option value="2">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="query" placeholder="검색" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
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
				                            <tr>
				                                <td>${vo.id}</td>
				                                <td class="text-start"><a href="./detail?id=${vo.id}">${vo.title}</a></td>
				                                <td>${vo.writeDate}</td>
				                                <td>등록자</td>
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
                        <li class="page-item disabled">
                            <a class="page-link" href="./list?page=${pager.startNum-1}&kind=${kind}&search=${search}" tabindex="-1" aria-disabled="true">이전</a>
                        </li>
                        
                        <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
	                        <li class="page-item">
	                        <a class="page-link" href="./list?page=${i}&kind=${kind}&search=${search}">${i}</a>
	                        </li>
                        </c:forEach>
                        <li class="page-item">
                            <a class="page-link" href="./list?page=${pager.lastNum+1}&kind=${kind}&search=${search}">다음</a>
                        </li>                     
                    </ul>
                    
                </nav>
            </div>
            
        </div>
        <div class="row justify-content-end p-3">
            <div class="col-auto">            	               	
               	<a href="./write" class="btn btn-primary">글쓰기</a>
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