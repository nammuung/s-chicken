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
<main id="main" class="main">
    <section class="section">
        <div class="row justify-content-center">
            <div class="col-8">
                <div class="card">
                    <div class="card-body d-flex flex-column align-items-center">
                        <div class="p-3">
                            <h1>S치킨 그룹웨어</h1>
                        </div>
                        <form action="./login" method="post">
                            <!-- id input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="id">ID</label>
                                <input type="text" id="id" name="id" value="${cookie.rememberId.value}" class="form-control form-control-lg"
                                       placeholder="ID를 입력하세요." />
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <label class="form-label" for="password">비밀번호</label>
                                <input type="password" id="password" name="password" class="form-control form-control-lg"
                                       placeholder="비밀번호를 입력하세요." />
                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Checkbox -->
                                <div class="form-check mb-0">
                                    <input class="form-check-input me-2" type="checkbox" value="" id="remember-me" name="rememberMe"/>
                                    <label class="form-check-label" for="remember-me">
                                        기억하기
                                    </label>
                                </div>
                                <a href="#" class="text-muted">비밀번호 찾기</a>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2 mb-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem; width: 300px;">로그인</button>
                            </div>
                            <div class="mb-2">
                                <img width="300" src="/kakao_login.png" alt="카카오로그인버튼">
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
</main><!-- End #main -->

<!-- 파라미터를 초기화 시켜주는 스크립트 -->
	<script type="text/javascript">
		history.replaceState({}, null, location.pathname);
	</script>
</body>

</html>