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
        <h1>채팅방 목록</h1>
    </div>

    <div class="row justify-content-center">
        <div class="col-9">
            <div class="card p-3">
                <div class="row justify-content-between">
                    <div class="col-6">
                        <div class="input-group mb-3">
                            <input class="form-control" type="text" placeholder="검색할 내용을 입력하세요 (부서, 직책명, 이름)">
                            <button class="btn btn-primary">검색</button>
                        </div>
                    </div>
                    <div class="col-4 text-end">
                        <button class="btn btn-primary">팝업으로 보기</button>
                        <button class="btn btn-primary">채팅방 만들기</button>
                    </div>
                    <c:forEach begin="1" end="10">
                    <div class="col-12 chatroom-item p-3 mb-2">
                        <h4>채팅방 이름</h4>
                        <span class="text-secondary">부서 직책 이름 : 채팅방의 최근 대화</span>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>
