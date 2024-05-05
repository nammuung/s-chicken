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
    <section class="section dashboard erp ms-auto me-auto">
        <div class="pagetitle">
            <h1>메인 화면</h1>
        </div>
        <div class="row ">
            <div class="col-8">
                <div class="row">
                    <!-- Sales Card -->
                    <div class="col-6" id="sellContainer">
                        <div class="card info-card sales-card">
                            <div class="card-body">
                                <h5 class="card-title">판매 <span>| 오늘</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-cart"></i>
                                    </div>
                                    <div class="ps-3">
                                        <h6 id="totalSell"></h6>
                                        <span class="text-success small pt-1 fw-bold"  id="sellIncreasePercent"></span> <span class="text-muted small pt-2 ps-1" id="sellIncreaseType"></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div><!-- End Sales Card -->

                    <!-- Revenue Card -->
                    <div class="col-6" id="salesContainer">
                        <div class="card info-card revenue-card">
                            <div class="card-body">
                                <h5 class="card-title">매출 <span>| 오늘</span></h5>

                                <div class="d-flex align-items-center">
                                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                        <i class="bi bi-currency-dollar"></i>
                                    </div>
                                    <div class="ps-3">
                                        <h6 id="totalSales"></h6>
                                        <span class="text-success small pt-1 fw-bold" id="salesIncreasePercent"></span> <span class="text-muted small pt-2 ps-1" id="salesIncreaseType"></span>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-- End Revenue Card -->

                    <!-- Reports -->
                    <div class="col-12" id="salesChartContainer">
                        <div class="card">
                            <div class="filter">
                                <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                    <li class="dropdown-header text-start">
                                        <h6>필터</h6>
                                    </li>

                                    <li><a class="dropdown-item" href="#" data-value="days">일</a></li>
                                    <li><a class="dropdown-item" href="#" data-value="weeks">주</a></li>
                                    <li><a class="dropdown-item" href="#" data-value="month">월</a></li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">매출추이<span id="perUnit">/일</span></h5>
                                <div id="reportsChart"></div>
                            </div>
                        </div>
                    </div><!-- End Reports -->

                </div>
            </div>
            <div class="col-4">
                <c:import url="../template/weather.jsp"/>
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
<script type="module">
    import {getSalesPerWeeks,getSalesPerMonth,getSalesPerDays,getSalesDays, getSellDays} from "/js/api/sales.js";
    const chartFilter = document.querySelectorAll("#salesChartContainer .dropdown-item");
    console.log(chartFilter);
    [...chartFilter].forEach(el => {
        el.onclick = (event) => {
            handleFilterClick(event)
        }
    })
    async function loadSalesPerDays(){
        const result = await getSalesPerDays();
        return result.data
    }
    async function loadSalesPerWeeks(){
        const result = await getSalesPerWeeks();
        return result.data
    }
    async function loadSalesPerMonth(){
        const result = await getSalesPerMonth();
        return result.data
    }
    async function loadSalesDays(){
        const result = await getSalesDays();
        return result.data
    }
    async function loadSellDays(){
        const result = await getSellDays();
        return result.data
    }

    let chart
    document.addEventListener("DOMContentLoaded", async () => {
        const data = await loadSalesPerDays();
        const sales = await loadSalesDays();
        const sell = await loadSellDays();
        const series = data.map(d => {
            return {
                x: new Date(d.salesDate),
                y: d.price
            }
        });
        chart = new ApexCharts(document.querySelector("#reportsChart"), {
            series: [{
                name:"매출",
                data: series
            }],
            chart: {
                height: 350,
                type: 'area',
                toolbar: {
                    show: false
                },
                defaultLocale: 'ko',
                locales: [{
                    name: 'ko',
                    options: {
                        months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                        shortMonths: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                        days: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                        shortDays: ['일', '월', '화', '수', '목', '금', '토'],
                        toolbar: {
                            download: 'SVG로 다운로드',
                            selection: '선택',
                            selectionZoom: '선택 확대',
                            zoomIn: '확대',
                            zoomOut: '축소',
                            pan: '이동',
                            reset: '확대/축소 초기화',
                        }
                    }
                }]
            },
            markers: {
                size: 4
            },
            colors: ['#4154f1', '#2eca6a', '#ff771d'],
            fill: {
                type: "gradient",
                gradient: {
                    shadeIntensity: 1,
                    opacityFrom: 0.3,
                    opacityTo: 0.4,
                    stops: [0, 90, 100]
                }
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                curve: 'smooth',
                width: 2
            },
            xaxis: {
                type: 'datetime',
                labels: {
                    datetimeFormatter: {
                        year: 'yyyy',
                        month: 'yy MMM',
                        day: 'MMM dd일'
                    },
                },
            },
            yaxis: {
                labels: {
                    formatter: function (val) {
                        return new Intl.NumberFormat('en-US', {
                            style: 'currency',
                            currency: 'KRW'
                        }).format(val);
                    }
                }
            },
            tooltip: {
                x: {
                    format: 'yy/MM/dd'
                },
            }
        })
        chart.render();

        document.getElementById("totalSales").innerText = sales.totalSales.toLocaleString("ko-KR") + "원";
        document.getElementById("totalSell").innerText = sell.totalSell;
        document.getElementById("salesIncreasePercent").innerText = Math.floor(sales.increase / sales.totalSales * 100) +"%";
        document.getElementById("sellIncreasePercent").innerText = Math.floor(sell.increase / sell.totalSell * 100) + "%";
        if(sales.increase > 0){
            document.getElementById("salesIncreaseType").innerText = "증가";
        } else {
            document.getElementById("salesIncreaseType").innerText = "감소";
        }
        if(sell.increase > 0){
            document.getElementById("sellIncreaseType").innerText = "증가";
        } else {
            document.getElementById("sellIncreaseType").innerText = "감소";
        }
    })
    async function handleFilterClick(event) {
        event.preventDefault();
        console.log(event)
        const perUnit = document.getElementById("perUnit");
        const filterValue = event.target.getAttribute('data-value');
        let data = []
        switch(filterValue){
            case 'days':
                data = await loadSalesPerDays();
                perUnit.innerText = "/일";
                break;
            case 'weeks':
                data =  await loadSalesPerWeeks();
                perUnit.innerText = "/주";
                break;
            case'month':
                data = await loadSalesPerMonth();
                perUnit.innerText = "/월";
                break;
        }
        const series = data.map(d => {
            return {
                x: new Date(d.salesDate),
                y: d.price
            }
        });
        chart.updateSeries([{
            data: series
        }]);
    }
</script>
</body>
</html>
