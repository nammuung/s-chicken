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
    <div class="pagetitle">
        <h1>직원목록</h1>
    </div>
    <section class="section">
        <div class="row justify-content-end p-3">
            <div class="col-auto">
                <form class="search-form d-flex align-items-center" action="./list">
                    <label for="search">
                        <select class="form-select w-auto me-1" name="kind">
                            <option value="kind1">부서</option>
                            <option value="kind2">이름</option>
                        </select>
                    </label>
                    <input type="text" id="search" name="search" placeholder="검색" >
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
                                <th>부서</th>
                                <th>직급</th>
                                <th>이름</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="data">
                            <tr>
                                <td>${data.department.name}</td>
                                <td>${data.position.name}</td>
                                <td><a href="/employee/profile?id=${data.id}">${data.name}</td>
                            </tr>
                            </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item" id="prev">
                            <input type="hidden" id="prev_val" value="${pager.startNum}">
                            <a class="page-link" href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a>
                        </li>
                        
                        <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
	                        <li class="page-item">
	                        <a class="page-link" href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
	                        </li>
                        </c:forEach>
                        <li class="page-item" id="next">
                            <input type="hidden" id="next_val" value="${pager.lastNum}">
                            <a class="page-link" href="./list?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a>
                            
                        </li>                     
                    </ul>
                    
                </nav>
                <button class="btn btn-primary float-end">엑셀 변환</button>
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