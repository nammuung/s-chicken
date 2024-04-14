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
			<h1>회원가입</h1>
		</div>
		<section class="section">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body mt-3">
						
							<form class="d-flex flex-column align-items-center ">
									<div class="form-group mb-3 col-4">
										<label for="department" class="form-label"><b>부서</b></label> <select
											class="form-select" id="department">
											<option value="0">부서 선택</option>

										</select>
									</div>


							<div>
    <table>
        <thead>
            <tr>
                <th>권한명</th>
                <th>사용여부</th>
                <!-- 추가 데이터 필드에 대한 테이블 헤더를 여기에 추가할 수 있습니다. -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="data">
                <tr>
                    <td>${data.position.name}</td>
                    <td><input type="checkbox" value="${data.department.name}"></td>
                    <!-- 추가 데이터 필드에 대한 셀을 여기에 추가할 수 있습니다. -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
								<button type="submit" class="btn btn-primary mt-3">확인</button>
							</form>
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
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/js/employee/roel.js"> </script>
	<c:import url="../template/script.jsp" />
</body>

</html>