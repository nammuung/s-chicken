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
                <div class="row justify-content-end">
                    <div class="col-2">
                        <a href="/notificationPage?readAll=true" class="btn btn-outline-secondary btn-sm">모두 읽기</a>
                    </div>
                </div>
                <ul class="notifications" data-notification-list>
                    <c:forEach items="${notificationList}" var="vo">
                    <li data-noti-id="${vo.id}" data-type="${vo.type}" data-link="${vo.link}" class="notification-item border-bottom<c:if test="${vo.isReaded}"> readed</c:if>">
                        <div>
                            <h4>${vo.title}</h4>
                            <p>${vo.content}</p>
                            <span>${vo.time}</span>
                        </div>
                    </li>
                    </c:forEach>
                </ul>

                <button id="more-notification-btn" data-page="1" class="d-block border-0 h4 bg-white p-3 text-center<c:if test="${notificationList.size() < 10}"> d-none</c:if>">
                    더보기
                </button>


                <div id="last-notification-btn" class="p-3 text-center<c:if test="${notificationList.size() >= 10}"> d-none</c:if>">
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
