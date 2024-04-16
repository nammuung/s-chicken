<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>S치킨-그룹웨어</title>
<c:import url="../template/head.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
	<main id="main" class="main" style="margin-left: 0;">
		<section class="section">
			<div class="row justify-content-center">
				<div class="col-8">
					<div class="card">
						<div class="card-body d-flex flex-column align-items-center">
							<div class="p-3">
								<h1>S치킨 그룹웨어</h1>
								<div>
									<h5 style="color: red;">${param.message}</h5>
								</div>
							</div>
							<form action="./login" method="post"
								onsubmit="return markButtonClicked()">
								<div class="btn-group" role="group"
									aria-label="Basic radio toggle button group">
									<input type="radio" class="btn-check" name="options-base"
										id="emp" autocomplete="off" checked> <label
										class="btn btn-outline-secondary" for="emp">그룹웨어</label> <input
										type="radio" class="btn-check" name="options-base" id="fra"
										autocomplete="off"> <label
										class="btn btn-outline-secondary" for="fra">가맹점</label>
								</div>

								<!-- id input -->
								<div class="form-outline mb-4">
									<label class="form-label" for="idz">ID</label> <input
										type="text" id="idz" value="${cookie.rememberId.value}"
										class="form-control form-control-lg" placeholder="ID를 입력하세요." />
								</div>
								<input type="hidden" id="id" name="id"
									value="${cookie.rememberId.value}" />


								<!-- Password input -->
								<div class="form-outline mb-3">
									<label class="form-label" for="password">비밀번호</label> <input
										type="password" id="password" name="password"
										class="form-control form-control-lg"
										placeholder="비밀번호를 입력하세요." />
								</div>

								<div class="d-flex justify-content-between align-items-center">
									<div class="d-flex justify-content-between align-items-center">
										<input type="checkbox" checked name="rememberId"
											class="form-check-input" id="remember-id"> <label
											class="form-check-label" for="remember-me">&nbsp;ID
											저장</label>
									</div>
									<a href="#" class="text-muted" data-bs-toggle="modal"
										data-bs-target="#passwordResetModal">비밀번호 찾기</a>
								</div>

								<div class="text-center text-lg-start mt-3 pt-2 mb-2">
									<button type="submit" class="btn btn-primary btn-lg"
										style="width: 300px;">
										<span style="padding-left: 2.5rem; padding-right: 2.5rem;">로그인</span>
									</button>
								</div>
								<div class="mb-2">
									<a href="/oauth2/authorization/kakao?prompt=login"> <img
										width="300" src="/kakao_login.png" alt="카카오로그인버튼">
									</a>
								</div>
								<div class="mb-2">
									<img width="300" src="/naver_login.png" alt="네이버로그인버튼">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->

	<!-- 비밀번호 찾기 모달 -->
	<div class="modal fade" id="passwordResetModal" tabindex="-1"
		aria-labelledby="passwordResetModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="passwordResetModalLabel">비밀번호 찾기</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="passwordResetForm">
						<div class="mb-3">
							<label for="userid" class="form-label">ID</label> <input
								type="text" class="form-control" id="userid" name="id"
								placeholder="ID를 입력하세요." required>
						</div>
						<div class="mb-3">
							<label for="name" class="form-label">이름</label> <input
								type="text" class="form-control" id="name" name="name"
								placeholder="이름을 입력하세요." required>
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">이메일 주소</label> <input
								type="email" class="form-control" id="email" name="email"
								placeholder="이메일을 입력하세요." required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="submitPasswordReset()">전송</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 파라미터를 초기화 시켜주는 스크립트 -->
	<script type="text/javascript">
		history.replaceState({}, null, location.pathname);
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
<script src="/js/employee/login.js"></script>
</html>
