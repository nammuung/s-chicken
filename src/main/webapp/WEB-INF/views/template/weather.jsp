<%--
  Created by IntelliJ IDEA.
  User: gimgyeongmo
  Date: 5/2/24
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="weather-container position-relative day p-3">
    <div class="d-flex justify-content-center align-items-center" style="height: 100%" id="weatherLoading">
        <div class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <div class="d-none" id="weatherMain">
        <img id="weatherIcon" class="weatherImage" src="/img/weather/01d.png" width="150" alt="icon"/>
        <div class="d-flex flex-column align-items-center nowInfo">
            <span id="nowTemp" class="nowTemp">20°</span>
            <div class="mb-3">
                <span id="weatherDescription">맑음</span>
            </div>
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
        <div class="row ps-2 pe-2">
            <div class="col p-1">
                <div class="detailTitle mt-2">풍속</div>
                <div id="" class="weatherBoxDetail">
                    <div id="detailWind">

                    </div>
                </div>
            </div>
            <div class="col p-1">
                <div class="detailTitle mt-2">강수량</div>
                <div id="" class="weatherBoxDetail">
                    <div id="detailRain">

                    </div>
                </div>
            </div>
            <div class="col p-1">
                <div class="detailTitle mt-2">습도</div>
                <div id="" class="weatherBoxDetail">
                    <div id="detailHumidity">

                    </div>
                </div>
            </div>
            <div class="col p-1">
                <div class="detailTitle mt-2">체감온도</div>
                <div id="" class="weatherBoxDetail">
                    <div id="realTemp">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>