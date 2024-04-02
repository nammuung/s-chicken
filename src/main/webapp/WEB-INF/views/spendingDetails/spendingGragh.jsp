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
        <h1>---팀 비품 지출 내역</h1>
    </div>

    <div class="card pb-4">
        <div class="row justify-content-center mt-5">
            <div class="col-10">
                <div class="row mb-5">
                    <div class="col-3">
                        <form>
                            <label for="spending-date">월 선택</label>
                            <input class="form-control" type="month" id="spending-date"/>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 mb-4">
                        <!-- Line Chart -->
                        <canvas id="lineChart" style="max-height: 400px;"></canvas>
                        <script>
                            document.addEventListener("DOMContentLoaded", () => {
                                let days = [];
                                let data = [];
                                let prev = 0;
                                for (let i = 1; i < 31; i++) {
                                    days.push(i + '');
                                    const x = Math.abs(Math.sin(i / 17 * 2 * Math.PI) + Math.cos(i / 27 * 2 * Math.PI) + Math.sin(i / 30 * 2 * Math.PI))*10597;
                                    const y = prev + x;
                                    prev = y;
                                    data.push(y*1.7);
                                }

                                new Chart(document.querySelector('#lineChart'), {
                                    type: 'line',
                                    data: {
                                        labels: days,
                                        datasets: [
                                            {
                                                label: '누적 사용량',
                                                data: data,
                                                fill: false,
                                                borderColor: '#7746f8',
                                                tension: 0.4
                                            },
                                            {
                                                label: '책정된 예산',
                                                data: data.map(i => '520000'),
                                                fill: true,
                                                borderColor: '#FF9',
                                                tension: 0.1
                                            }]
                                    },
                                    options: {
                                        scales: {
                                            y: {
                                                beginAtZero: true
                                            }
                                        }
                                    }
                                });
                            });
                        </script>
                        <!-- End Line CHart -->
                    </div>
                    <div class="col-12">
                        <div class="row justify-content-end">
                            <div class="col-4 text-end">
                                <button class="btn btn-primary">
                                    내역보기
                                </button>
                            </div>
                        </div>
                    </div>
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
<script>
    window.onload = () => {
        const nowDate = new Date();
        const yearMonth = nowDate.getFullYear() + "-" + ((nowDate.getMonth() + 1) > 9 ? nowDate.getMonth() + 1 : '0' + (nowDate.getMonth() + 1));
        document.getElementById("spending-date").max = yearMonth;
    }
</script>
</body>

</html>
