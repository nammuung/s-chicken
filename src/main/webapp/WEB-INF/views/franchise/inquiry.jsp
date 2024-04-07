<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9487982a6e9b4d2638f7bb9426cd5683"></script>

</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <h1>가맹점 조회</h1>
    </div>
    <section class="section">
        <div class="row justify-content-center p-3">
            <div class="col-6">
                <div id="map" style="width:100%;height:100%;"></div>
            </div>
            <div class="col-6">
                <form class="search-form d-flex align-items-center mb-3" method="POST" action="#">
                    <label>
                        <select class="form-select w-auto me-1">
                            <option value="0">제목</option>
                            <option value="1">내용</option>
                            <option value="2">제목+내용</option>
                        </select>
                    </label>
                    <input type="text" name="query" placeholder="검색" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="d-flex flex-column">
                            <b>한라점</b>
                            <span>010-1234-5678</span>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="d-flex flex-column">
                            <b>한라점</b>
                            <span>010-1234-5678</span>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="d-flex flex-column">
                            <b>한라점</b>
                            <span>010-1234-5678</span>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="d-flex flex-column">
                            <b>한라점</b>
                            <span>010-1234-5678</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>

<script>
    var container = document.getElementById('map');
    var options = {
        center: new kakao.maps.LatLng(37.47767, 126.8795),
        level: 3
    };

    var map = new kakao.maps.Map(container, options);
</script>

</body>

</html>
