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
			<h1>연차내역</h1>
		</div>
		<section class="section">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<table class="table text-center text-nowrap">
								<thead>
									<tr>
										<th>날짜</th>
										<th>내역</th>
										<th>받은연차</th>
										<th>사용연차</th>
										<th>남은연차</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="data">
										<tr>
											<td>${data.annualDate}</td>
											<td><c:if test="${not empty data.documentId}">
													<a href="#">${data.history}</a>
												</c:if> <c:if test="${empty data.documentId}">
                ${data.history}
            </c:if></td>
											<td>${data.remainderAnnual}</td>
											<td>${data.annual}</td>
											<td>${data.annualTotal}</td>
										</tr>
									</c:forEach>
							</table>
						</div>
					</div>

					<!--                 <nav aria-label="Page navigation example">
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
                <button class="btn btn-primary float-end">엑셀 변환</button> -->
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