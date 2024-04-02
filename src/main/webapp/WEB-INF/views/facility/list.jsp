<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>S치킨-그룹웨어</title>
<c:import url="../template/head.jsp" />
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
</head>
<style>
</style>



<body>
	<!-- ======= Header ======= -->
	<c:import url="../template/header.jsp" />
	<!-- ======= Sidebar ======= -->
	<c:import url="../template/sidebar.jsp" />
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>직원목록</h1>
		</div>
		<section class="section">
			<div class="row justify-content-end p-3">
				<div class="col-auto">
					<form class="search-form d-flex align-items-center " method="POST"
						action="#">
						<label> <select class="form-select w-auto me-1">
								<option value="0">이름</option>
								<option value="1">부서</option>
								<option value="2">직급</option>
						</select>
						</label> <input type="text" name="query" placeholder="검색"
							title="Enter search keyword">
						<button type="submit" title="Search">
							<i class="bi bi-search"></i>
						</button>
					</form>
				</div>
			</div>
			<div class="row ">
				<div class="col-12">

					<div class="row justify-content-center d-flex">
					<div class="col-2">
						<h1 style="background-color: #7749f8; border-radius: 50%;width:80px;height: 80px;display : flex;justify-content : center; align-items : center; color: white;">ALL</h1>
					</div>
					
					
					<!-- 회의실 -->
					<div class="col-2">
						<i class="fas fa-chalkboard-teacher fa-3x" style="color: #7749f8; border-radius: 50%;"></i>
					</div>
					<!-- 차 -->
					<div class="col-2">
						<i class="fas fa-car fa-3x" style="color: #7749f8;"></i>
					</div>
					<!-- 콘도 -->
					<div class="col-2">
						<i class="fas fa-hotel fa-3x" style="color: #7749f8;"></i>
					</div>
					</div>
					<button class="btn btn-primary float-end">엑셀 변환</button>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<c:import url="../template/footer.jsp" />
	<!-- ======= Script ======= -->
	<c:import url="../template/script.jsp" />
</body>

</html>