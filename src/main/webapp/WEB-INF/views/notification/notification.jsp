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
        <h1>알림</h1>
    </div>

    <div class="row justify-content-center">
        <div class="col-8">
            <div class="card">
                <ul class="notifications pt-4">
                    <li class="notification-item border-bottom">
                        <div>
                            <h4>알림 제목</h4>
                            <p>알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                    <li class="notification-item border-bottom readed">
                        <div>
                            <h4>알림 제목</h4>
                            <p>알림 알림 알림 <span class="linkable">알림</span> 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                    <li class="notification-item border-bottom">
                        <div>
                            <h4>알림 제목</h4>
                            <p>경영지원실 실장 <span class="linkable">조민우</span>님이 알림을 보냈습니다 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                    <li class="notification-item border-bottom">
                        <div>
                            <h4>알림 제목</h4>
                            <p>알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                    <li class="notification-item border-bottom">
                        <div>
                            <h4>알림 제목</h4>
                            <p>알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                    <li class="notification-item border-bottom">
                        <div>
                            <h4>알림 제목</h4>
                            <p>알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 알림 </p>
                            <span>알림 온 시간</span>
                        </div>
                    </li>
                </ul>
                <div class="p-3 text-center">
                    <h4>더보기</h4>
                </div>
                <div class="p-3 text-center">
                    <h4>마지막 알림 입니다</h4>
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
