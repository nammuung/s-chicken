<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>

    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp" />
    <style>
        .weather-container {
            width: 400px;
            height: 480px;
            color: white;
            border-radius: 30px;
        }
        .weather-container.d01 {
            background: linear-gradient(
                    180deg,
                    rgba(0, 117, 255, 1) 0%,
                    rgba(149, 204, 255, 1) 100%
            );
            position: relative;
        }

        .weather-container .weatherImage {
            position: absolute;
            left: 60px;
            top: 20px;
            z-index: 0;
        }
        .weather-container .nowInfo {
            position: relative;
            margin-top: 80px;
            z-index: 55;
        }
        .weather-container .nowTemp {
            font-size: 100px;
            font-weight: 700;
            line-height: 100px;
        }
        .weather-container .weatherBox {
            padding: 5px;
            background: rgba(255, 255, 255, 0.38);
            border-radius: 23px;
            height: 85px;
        }
    </style>
</head>

<body>

<!-- ======= Header ======= -->
<c:import url="../template/header.jsp" />
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp" />

<main id="main" class="main">
    <div class="pagetitle">
        <h1>메인 화면</h1>
    </div>
    <section class="section">
        <div class="row justify-content-center">
            <div class="card" style="width: 1300px;">
                <div class="card-body">
                    <div class="weather-container position-relative d01 mt-3 p-3">
                        <img id="weatherIcon" class="weatherImage" src="/img/weather/01d.png" width="150" alt="icon"/>
                        <div class="d-flex flex-column align-items-center nowInfo">
                            <span id="nowTemp" class="nowTemp">20°</span>
                            <div class="mb-3">
                                <span id="weatherDescription">맑음</span>
                            </div>
<%--                            <span>--%>
<%--                                <span class="">체감: 18°</span>--%>
<%--                            </span>--%>
                        </div>
                        <div class="">시간별</div>
                        <div id="timeWeather" class="weatherBox w-100 d-flex justify-content-around ">
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>지금</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>12시</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>13시</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>14시</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>15시</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                            <div class="d-flex flex-column text-nowrap align-items-center">
                                <span>16시</span>
                                <img src="/img/weather/01d.png" width="30" alt="icon"/>
                                <div>
                                    <span class="">18°</span>
                                </div>
                            </div>
                        </div>
                        <div class=" mt-2">상세</div>
                        <div class="weatherBox"></div>
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
<script type="module" src="/js/weather/script.js"></script>
</body>
</html>
