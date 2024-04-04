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
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>

</head>
<style>
.title {
	font-weight: bold;
	color: #7749f8;
	font-size: x-large
}

.image {
	width: 100%;
	max-height: 200px;
	object-fit: cover;
}

.mintitle {
	color: gray;
}

a:link {
	color: #7749f8;
}

a:visited {
	color: #7749f8;
}

a:hover {
	color: blue;
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
			<div class="row justify-content-center">
				<div class="col-8">

					<div class="row justify-content-center mb-5">

						<div class="col-2">
							<h4
								style="background-color: #7749f8; border-radius: 50%; width: 50px; height: 50px; display: flex; justify-content: center; align-items: center; color: white;">
								ALL</h4>
						</div>


						<!-- 회의실 -->
						<div class="col-2">
							<i class="fas fa-chalkboard-teacher fa-3x"
								style="color: #7749f8; border-radius: 50%;"></i>
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

					<div class="row">
						<c:forEach begin="1" end="9">

							<div class="col-4 animate__animated animate__fadeInDownBig " style="border-radius: 10%;">
								<div class="card shadow  mb-5 bg-body rounded">

									<div class="facility mb-2 text-center">
										<img style="border-radius: 2%;" class="image "
											src="https://cdn.pixabay.com/photo/2017/03/28/12/11/chairs-2181960_1280.jpg"
											alt="#"></a>
									</div>
									<span class="title ms-2"><a href="./">회의실 A</a></span>
									<div class="mintitle mb-3 ms-2">본관 3층 A-2 구역</div>

								</div>
							</div>
						</c:forEach>
					</div>

					<div class="d-flex justify-content-center">
						<button id="loadMore" class="btn btn-primary">더 보기</button>
					</div>
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