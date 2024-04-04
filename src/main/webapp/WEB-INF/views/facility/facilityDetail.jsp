<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>S치킨-그룹웨어</title>
<c:import url="../template/head.jsp" />

</head>

<body>
	<!-- ======= Header ======= -->
	<c:import url="../template/header.jsp" />
	<!-- ======= Sidebar ======= -->
	<c:import url="../template/sidebar.jsp" />
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>${facility}예약목록</h1>
		</div>
		<section class="section">

			<div class="row justify-content-center">
				<div class="col-8">
					<div class="card">
						<div class="card-body">
							<div class="mt-3 mb-3">
								<h1 class="title">회의실 A</h1>
							</div>

							<div id="carouselExampleRide" class=" carousel slide  col-11"
								data-bs-ride="true" style="margin: 0 auto;">
								<div class="carousel-inner">
									<c:forEach begin="1" end="3">
										<div class="carousel-item active " data-bs-interval="2000">
											<img
												src="https://cdn.pixabay.com/photo/2017/03/28/12/11/chairs-2181960_1280.jpg"
												class="d-block w-100" alt="...">
										</div>
									</c:forEach>
								</div>
								<button class="carousel-control-prev" type="button"
									data-bs-target="#carouselExampleRide" data-bs-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button"
									data-bs-target="#carouselExampleRide" data-bs-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Next</span>
								</button>
							</div>
							<div>
								<h3 class="mintitle mt-3">본관 3층 A-2 구역</h3>
							</div>
						</div>
					</div>

					<div class="card">
						<div class="card-body ms-5 mt-2 facilitybody">
							<span>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book. It has survived not only five centuries, but also
								the leap into electronic typesetting, remaining essentially
								unchanged. It was popularised in the 1960s with the release of
								Letraset sheets containing Lorem Ipsum passages, and more
								recently with desktop publishing software like Aldus PageMaker
								including versions of Lorem Ipsum.</span>
						</div>
					</div>

					<div class="card" style="margin-bottom: 0">
						<div class="card-body ms-5 mt-3 announcement">
							<span>● 수용인원 최소 1명 ~ 최대 10명</span>
						</div>
					</div>

					
				</div>
				
				<div class="col-4">
				<div class="card">
				
				<div>
						<h4 class="facilityday">예약 일시</h4>
					</div>


					<div class="d-flex">
					<div class="form-group col-5" style=" margin-left: 30px;">
						<label for="birth" class="form-label" style="font-size:80%; margin-bottom: 0;">start Date</label> 
						<input id="birth" type="date" class="form-control mt-0">
					</div>
					
					<div class="form-group col-5" style=" margin-left: 10px;">
						<label for="birth" class="form-label" style="font-size:80%; margin-bottom: 0;">end Date</label> 
						<input id="birth" type="date" class="form-control mt-0">
					</div>
					</div>
					
					
					
					<div class="facilityday">예약 시간</div>
					<div class="d-flex">
					<div class="col-1"></div>
					<div class="col-2 listcheckbox">&nbsp;</div>
					<span class="facilitySpan">&nbsp;예약 불가</span>
					<div class="col-2"></div>
					<div class="col-2 listcheckbox2">&nbsp;</div>
					<span class="facilitySpan"> 예약 가능</span>
					</div>
				
				<!-- 시간  비동기 식으로 구현 필요-->
				<div class="d-flex justify-content-center">
				<div class="col-3 listcheckbox text-center">09:00</div>
				<div class="col-3 listcheckbox text-center">10:00</div>
				<div class="col-3 listcheckbox text-center">11:00</div>
				</div>
				
				<div class="d-flex justify-content-center ">
				<div class="col-3 listcheckbox2 text-center">12:00</div>
				<div class="col-3 listcheckbox2 text-center">13:00</div>
				<div class="col-3 listcheckbox2 text-center">14:00</div>
				</div>
				
				<div class="d-flex justify-content-center ">
				<div class="col-3 listcheckbox2 text-center" >15:00</div>
				<div class="col-3 listcheckbox2 text-center" >16:00</div>
				<div class="col-3 listcheckbox2 text-center" >17:00</div>
				</div>
				
				<div class="d-flex justify-content-center ">
				<div class="col-3 listcheckbox2 text-center">18:00</div>
				<div class="col-3 listcheckbox2 text-center">19:00</div>
				<div class="col-3 listcheckbox2 text-center">20:00</div>
				</div>
				
				
				</div>
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