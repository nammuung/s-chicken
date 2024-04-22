<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>S치킨-그룹웨어</title>
<c:import url="template/head.jsp" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView : 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
		headerToolbar : { // 헤더에 표시할 툴 바
			start : 'prev next today',
			center : 'title',
			end : 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		titleFormat : function(date) {
			return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
		},
		//initialDate: '2021-07-15', // 초기 날짜 설정 (설정하지 않으면 오늘 날짜가 보인다.)
		selectable : true, // 달력 일자 드래그 설정가능
		droppable : true,
		editable : true,
		nowIndicator: true, // 현재 시간 마크
		locale: 'ko' // 한국어 설정
	});
	calendar.render();
});
</script>
<style>
    #calendar {
        height: 500px; /* 원하는 높이로 조정 */
    }
</style>
</head>

<body>

	<!-- ======= Header ======= -->
	<c:import url="template/header.jsp" />
	<!-- ======= Sidebar ======= -->
	<c:import url="template/sidebar.jsp" />
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>메인 화면</h1>
		</div>
		<section class="section">

			<div class="row justify-content-center">
				<div style="width: 1800px;">
					<div class="card">
						<div class="card-body">
							<div id='calendar'></div>
						</div>
					</div>
				</div>
			</div>

		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<c:import url="template/footer.jsp" />
	<!-- ======= Script ======= -->
	<c:import url="template/script.jsp" />
</body>

</html>
