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
.title {
	font-weight: bold;
	color: #7749f8;
	font-size: x-large
}
.image {
	width: 100%;
	max-height: 250px;
	object-fit:cover;
}
.mintitle{
color : gray;
}
a:link {
  color: #7749f8;
}
a:visited {
  color: #7749f8;
}

a:hover {
  color : blue;
}
</style>



<body>
	<!-- ======= Header ======= -->
	<c:import url="../template/header.jsp" />
	<!-- ======= Sidebar ======= -->
	<c:import url="../template/sidebar.jsp" />
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>시설 목록</h1>
		</div>
		<section class="section">
			<div class="row ">
				<div class="col-12">
					
					<div class="row justify-content-center ">
					
						<div class="col-1">
							<h4
								style="background-color: #7749f8; border-radius: 50%; width: 50px; height: 50px; display: flex; justify-content: center; align-items: center; color: white;">
								ALL</h4>
						</div>


						<!-- 회의실 -->
						<div class="col-1">
							<i class="fas fa-chalkboard-teacher fa-3x"
								style="color: #7749f8; border-radius: 50%;"></i>
						</div>
						<!-- 차 -->
						<div class="col-1">
							<i class="fas fa-car fa-3x" style="color: #7749f8;"></i>
						</div>
						<!-- 콘도 -->
						<div class="col-1">
							<i class="fas fa-hotel fa-3x" style="color: #7749f8;"></i>
						</div>
					</div>
				</div>
				<div class="mb-5"></div>

				<c:forEach begin="1" end="10">				
				<div class="col-2"></div>
				<div class="col-2">
					<div class="card shadow p-3 mb-5 bg-body rounded">
						<div class="card-body mt-2 ">
							<div class="facility mb-2 text-center">
								<img class="image mt-2"  
									src="data:image/jpeg;base64,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"
									alt="#"></a>
							</div>
							<span class="title"><a href="./">회의실 A</a></span>
							<div class="mintitle">본관 3층 A-2 구역</div>
						</div>
					</div>
				</div>
				</c:forEach>
				<div class="mb-0"></div>
				

				

				<div class="col-5"></div>
				<div class="col-2 d-flex justify-content-center">
				<button id="loadMore" class="btn btn-primary">더 보기</button>
				</div>

			</div>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<c:import url="../template/footer.jsp" />
	<!-- ======= Script ======= -->
	<script>

	</script>
	<c:import url="../template/script.jsp" />
</body>

</html>