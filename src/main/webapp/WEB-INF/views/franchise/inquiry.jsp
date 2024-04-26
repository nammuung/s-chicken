<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9487982a6e9b4d2638f7bb9426cd5683&libraries=services"></script>
    <style>
        .selected {
            background-color: #ececec;
        }
    </style>
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
                <div id="map" style="width:100%;height:100%;min-height: 50vh"></div>
            </div>
            <div class="col-6">
                <form class="search-form d-flex align-items-center mb-3 pe-3" method="GET" action="#">
                    <label>
                        <select id="searchSelect" class="form-select w-auto me-1" name="kind" data-kind="${pager.kind}">
                            <option value="name">지점명</option>
                            <option value="manager">담당자명</option>
                        </select>
                    </label>
                    <input type="text" name="search" placeholder="검색" value="${pager.search}" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                </form>
                <div class="overflow-auto" style="height: 50vh;">
                    <ul class="list-group">
                        <c:forEach items="${list}" var="dept" varStatus="status">
                            <li id="item${status.index}" class="list-group-item cursor d-flex justify-content-between align-items-center" onclick="focusMarker('${dept.address}',${status.index})">
                                <a href="#" class="d-flex flex-column link-dark">
                                    <b>${dept.name}</b>
                                    <span>${dept.contactNumber}</span>
                                </a>
                                <div>
                                    <a href="/franchise/detail?id=${dept.id}" class="link-dark me-1">
                                        <i class="bi bi-bar-chart-fill"></i>
                                    </a>
                                    <a href="/franchise/detail?id=${dept.id}" class="link-dark">
                                        <i class="bi bi-info-circle-fill"></i>
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
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
        center: new kakao.maps.LatLng(37.5642135, 127.0016985),
        level: 3
    };

    var map = new kakao.maps.Map(container, options);
    var ps = new kakao.maps.services.Places();
    var geocoder = new kakao.maps.services.Geocoder();
    var infowindow = new kakao.maps.InfoWindow({zIndex: 1});
    Array.prototype.slice.call(document.getElementById("searchSelect"))
        .forEach(child => {
            if (child.value == '${pager.kind}') child.selected = true
        })

    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place, description) {
        // 마커를 생성합니다
        // 마커를 생성하고 지도에 표시합니다
        var coords = new kakao.maps.LatLng(place.y, place.x)
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function () {
            map.setCenter(coords);
            map.setLevel(2);
        });
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseover', function () {
            infowindow.setContent('<div style="padding:5px;font-size:12px;"><b>' + description + '</b></div>');
            infowindow.open(map, marker);
        });
        kakao.maps.event.addListener(marker, 'mouseout', function () {
            infowindow.close(map, marker);
        });
    }

    (async function () {
        const response = await fetch("/v1/api/franchise?kind=${pager.kind}&search=${pager.search}");
        const data = await response.json();
        var bounds = new kakao.maps.LatLngBounds();
        if (data.length === 0) {
            map.setLevel(8);
        }
        data.forEach((franchise) => {
            // 주소로 좌표를 검색합니다
            geocoder.addressSearch(franchise.address, function (result, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    bounds.extend(new kakao.maps.LatLng(result[0].y, result[0].x));
                    // 결과값으로 받은 위치를 마커로 표시합니다
                    displayMarker(result[0], franchise.name);
                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setBounds(bounds);

                }
            });

        })
    })()

    function focusMarker(address, index) {
        const item = document.getElementById("item" + index);
        Array.prototype.slice.call(document.querySelectorAll(".list-group-item"))
            .forEach(el => {
                el.classList.remove("selected");
            })
        item.classList.add("selected");
        geocoder.addressSearch(address, function (result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                map.setCenter(coords);
                map.setLevel(2);
            }
        })

    }

</script>

</body>

</html>
