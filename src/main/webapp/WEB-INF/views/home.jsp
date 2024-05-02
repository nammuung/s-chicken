<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html lang="kr">

		<head>
			<script src="https://cdn.jsdelivr.net/npm/@yaireo/tagify"></script>
			<meta charset="utf-8">
			<meta content="width=device-width, initial-scale=1.0" name="viewport">
			<title>S치킨-그룹웨어</title>
			<c:import url="template/head.jsp" />

			<style>
				/* 일요일 날짜 빨간색 */
				.fc-day-sun a {
					color: red;
					text-decoration: none;
					/* 밑줄 제거 */
				}

				/* 토요일 날짜 파란색 */
				.fc-day-sat a {
					color: blue;
					text-decoration: none;
					/* 밑줄 제거 */
				}

				/* 월요일부터 금요일까지의 날짜 검정색 */
				.fc-day-mon a,
				.fc-day-tue a,
				.fc-day-wed a,
				.fc-day-thu a,
				.fc-day-fri a {
					color: black;
					text-decoration: none;
					/* 밑줄 제거 */
				}

				#calendar {
					height: 380px;
					/* 원하는 높이로 조정 */
				}
			</style>

		</head>

		<body>

			<!-- ======= Header ======= -->
			<c:import url="template/header.jsp" />
			<!-- ======= Sidebar ======= -->
			<c:import url="template/sidebar.jsp" />
			<main id="main" class="main">

				<section class="section ms-auto me-auto" style="width: 1200px;">
					<div class="pagetitle">
						<h1>메인 화면</h1>
					</div>
					<div class="row justify-content-center">
						<div class="col">
							<div class="card" style="width: 800px;">
								<div class="card-body">
									<div id='calendar-container' class="mt-3">
										<div id='calendar'></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<c:import url="./template/weather.jsp"/>
						</div>
					</div>
					<div class="row justify-content-start d-flex">
						<div class="col-3 pagetitle" style="margin-left: 18.5%; margin-bottom: 15px">
							<h1>공지사항</h1>
						</div>
						<div class="col-3 pagetitle" style="margin-left: 25.5%; margin-bottom: 15px">
							<h1>결재목록</h1>
						</div>
					</div>
					<!-- border -->
					<div class="row justify-content-center d-flex">

						<div class=" col">
							<table class="table text-center text-nowrap">
								<thead>
									<tr>
										<th style="width: 5%">no</th>
										<th style="width: 65%">제목</th>
										<th style="width: 10%">작성일</th>
										<th style="width: 10%">등록자</th>
										<th style="width: 10%">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="vo" end="5">
										<c:if test="${vo.sort eq 0}">
											<tr id="important">
												<input type="hidden" id="important_val" value="${vo.important}">
												<td>${vo.id}</td>
												<td class="text-start"><a
														href="./all/detail?id=${vo.id}">${vo.title}</a></td>
												<td>${vo.writeDate}</td>
												<td>${vo.employeeVO.name}</td>
												<td>${vo.hit}</td>
											</tr>
										</c:if>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<!-- 전자결재 -->

						<div class=" col">
							<table class="table text-center text-nowrap">
								<thead>
									<tr>
										<th style="width: 5%">no</th>
										<th style="width: 65%">제목</th>
										<th style="width: 10%">작성일</th>
										<th style="width: 10%">등록자</th>
										<th style="width: 10%">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="vo" end="5">
										<c:if test="${vo.sort eq 0}">
											<tr id="important">
												<input type="hidden" id="important_val" value="${vo.important}">
												<td>${vo.id}</td>
												<td class="text-start"><a href="./detail?id=${vo.id}">${vo.title}</a>
												</td>
												<td>${vo.writeDate}</td>

												<td>${vo.employeeVO.name}</td>

												<td>${vo.hit}</td>
											</tr>
										</c:if>
									</c:forEach>

								</tbody>
							</table>
						</div>

					</div>


				</section>
			</main>
			<!-- End #main -->
			<!-- ======= Footer ======= -->
			<c:import url="template/footer.jsp" />
			<!-- ======= Script ======= -->
			<c:import url="template/script.jsp" />

			<!-- 모달 추가 -->
			<div class="modal fade modal" id="eventModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="eventModalLabel">일정</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="d-flex">
								<div class="col-5 me-2">
									<label for="start" class="form-label"><b>시작</b></label>
									<input id="start" name="start" type="datetime-local" class="form-control mb-3">
								</div>
								<div class="col-5 ms-2 me-2">
									<label for="end" class="form-label"><b>종료</b></label>
									<input id="end" name="end" type="datetime-local" class="form-control mb-3 col-2">
								</div>
								<input type="checkbox" value="" class="mt-3 me-1" id="alltime"><span
									style="margin-top: 40px;">종일</span>
							</div>
							<label for="title" class="form-label"><b>제목</b></label>
							<input type="text" id="title" class="form-control mb-3" placeholder="제목을 입력해주세요.">
							<label for="content" class="form-label"><b>내용</b></label>
							<input type="text" id="content" class="form-control mb-3" placeholder="내용을 입력해주세요.">
							<label for="share" class="form-label"><b>공유자</b></label>
							<input type="text" id="share" nanem="share" class="form-control mb-3" disabled
								value="${profile.name}">
							<input type="hidden" name="share" value="${profile.id}" id="emid" />


							<!--  -->
							<div class="row mb-3 w-30">
								<div class="d-flex justify-content-between">
									<label for="managerId" class="form-label"><b>공유</b></label>
									<input type="button" id="searchButton" value="직원 조회" class="btn btn-primary">
								</div>
								<input id="managerId" name="employeeId" type="hidden" class="form-control me-1">
							</div>
							<div class="row">
								<div class="form-group mb-3 col">
									<textarea name="tags2" placeholder="Enter tags" id="managerName"
										style="width: 100%;"></textarea>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							<button type="button" id="saveEventBtn" class="btn btn-primary">저장</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal" tabindex="-1" id="dept-modal" style="margin-top: 10%; margin-left: -3%">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">부서 등록</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div id="orgChart"></div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">저장</button>
						</div>
					</div>
				</div>
			</div>

		</body>

		<script type="module">
			import orgChart from "/js/orgChart/orgChart.js";

			const searchButton = document.getElementById("searchButton");
			const removeButton = document.getElementById("removeButton");
			const managerIdInput = document.getElementById("managerId");
			const managerNameInput = document.getElementById("managerName");
			const employeeId = document.getElementById("emid").value;
			const title = document.getElementById("title").value;
			const share = document.getElementById("share").value;

			let selectedEmployees = []; // 배열에 선택된 직원을 저장
			let uniqueids = [];


			orgChart.init("orgChart", (data) => {

				if (selectedEmployees.includes(data.id) || uniqueids.includes(data.id)){
					alert('이미 선택 되었습니다.');
					return;
				}

				if (!selectedEmployees.some(emp => emp.id === data.id || data.id === employeeId)) {
					// 선택된 직원을 배열에 추가
					selectedEmployees.push(data);

					// 선택된 직원들의 이름을 쉼표로 구분하여 입력란에 표시
					updateSelectedEmployees();
				} else {
					// 이미 선택된 직원인 경우 메시지 출력 또는 다른 처리 가능
					alert('이미 선택 되었습니다.');
				}
			});

			searchButton.addEventListener("click", () => {
				const modals = new bootstrap.Modal(document.getElementById("dept-modal"));
				modals.show();
			});


			function updateSelectedEmployees() {
				// 중복된 값을 제거한 배열 생성
				const uniqueNames = [];
				const uniqueEmployees = [];
				for (const employee of selectedEmployees) {
					if (!uniqueNames.includes(employee.name)) {
						uniqueNames.push(employee.name);
						uniqueEmployees.push(employee);
					}
				}


				// 입력란 업데이트
				managerNameInput.value = uniqueNames.join(", ");
				managerIdInput.value = JSON.stringify(uniqueEmployees.map(employee => ({ value: employee.id })));
				console.log(managerIdInput.value);
				// 선택된 직원이 없으면 태그 초기화하지 않음
				if (uniqueNames.length === 0) {
					return;
				}

				if (!tagify) {
					initializeTagify(uniqueNames);  // 태그에 
				} else {
					// 기존 태그를 유지하면서 새로운 태그를 추가
					uniqueNames.forEach(name => {
						if (!tagify.value.some(tag => tag.value === name)) {
							tagify.addTags([{ value: name }]);
						}
					});
				}
			}
			const ids = [];
			
			const idName = [];


			fetch('http://localhost/userlist', {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
				},
			})
				.then(response => {
					return response.json();
				})
				.then(data => {
					const names = [];
					// NAME
					data.forEach(department => {
						names.push(department.name); // 부서명을 배열에 추가
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								names.push(employee.name); // 직원의 이름을 배열에 추가.
							});
						}
					});

					// ID
					data.forEach(department => {
						// 부서명과 부서 ID를 배열에 추가
						ids.push({ id: department.id });
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								// 직원의 이름과 ID를 배열에 추가
								ids.push({ id: employee.id });
							});
						}
					});
					// ID, NAME
					data.forEach(department => {
						// 부서명과 부서 ID를 배열에 추가
						idName.push({ id: department.id, name: department.name });
						if (department.employees && department.employees.length > 0) {
							department.employees.forEach(employee => {
								// 직원의 이름과 ID를 배열에 추가
								idName.push({ id: employee.id, name: employee.name });
							});
						}
					});
					console.log(ids);
					managerNameInput.value = names.join(", ");

					console.log(names);
					initializeTagify(names);
				})
				.catch(error => {
					console.error('에러났음요:', error);
				});

			let tagify; // 전역 변수로 tagify 선언

			function initializeTagify(names) {
				var input = document.querySelector('textarea[name=tags2]');
				tagify = new Tagify(input, {
					enforceWhitelist: true,
					delimiters: null,
					whitelist: names,
					callbacks: {
						add: function (e) {
							const tagName = e.detail.data.value; // 추가된 태그의 이름 가져오기
    console.log("태그 추가됨: ", e.detail.data);
    console.log("dlq" + tagName);
    // 태그의 이름을 기반으로 해당하는 ID를 찾기
    const foundIds = idName.filter(item => item.name === tagName).map(item => item.id);
    console.log(foundIds);
    // 찾은 ID를 uniqueids 배열에 추가
    uniqueids.push(...foundIds);
    console.log(uniqueids);
    // uniqueids 배열에 있는 ID들을 managerIdInput에 추가
    managerIdInput.value = JSON.stringify(uniqueids.map(id => ({ value: id }))); // ID만 추가
    console.log(managerIdInput.value); // managerIdInput의 값 확


						},
						remove: function (e) {
							console.log("태그 제거됨: ", e.detail.data);
                const removedItem = e.detail.data.value;
                console.log(removedItem);
                // 제거된 태그에 해당하는 ID를 uniqueids 배열에서 제거
                uniqueids = uniqueids.filter(id => !idName.some(item => item.name === removedItem && id === item.id));
                console.log(uniqueids);
                // uniqueids 배열에 있는 ID들을 managerIdInput에 추가
                managerIdInput.value = JSON.stringify(uniqueids.map(id => ({ value: id }))); // 변경된 ID 배열을 다시 할당
                console.log(managerIdInput.value); // managerIdInput의 값 확인
							const index = selectedEmployees.findIndex(employee => employee.name === removedItem);
							if (index !== -1) {
								selectedEmployees.splice(index, 1);
							}
						}
					}
				});
			}


		</script>



		<script>
			(function () {
				$(function () {
					var calendarEl = document.getElementById('calendar');
					var calendar = new FullCalendar.Calendar(calendarEl, {
						height: '50vh', // calendar 높이 설정
						expandRows: true, // 화면에 맞게 높이 재설정
						slotMinTime: '08:00', // Day 캘린더에서 시작 시간
						slotMaxTime: '20:00', // Day 캘린더에서 종료 시간
						headerToolbar: {
							left: 'prev,next today',
							center: 'title',
							right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
						},
						initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
						navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
						editable: true, // 수정 가능?
						selectable: true, // 달력 일자 드래그 설정가능
						nowIndicator: true, // 현재 시간 마크
						dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
						locale: 'ko', // 한국어 설정
						googleCalendarApiKey: "AIzaSyBMcaAD8R_KhHKRl3WMVS0pndILwlEtiDc",
						eventSources: [
							{
								googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com'
								, color: 'white'   // an option!
								, textColor: 'red' // an option!
							}
						],
						
						eventAdd: function (obj) { // 이벤트가 추가되면 발생하는 이벤트
							console.log(obj);
							console.log("추가임");
						},
						eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트
							console.log(obj);
							console.log("수정임");
							console.log(obj.event.start);
							console.log(obj.event.end);
							console.log(obj.event.content);
							console.log(obj.event.title);
							console.log(obj.event.id);
							$.ajax({
										url: '/update',
										type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({
											title: obj.event.title,
											content: obj.event.content,
											start: obj.event.start,
											end: obj.event.end,
											id: obj.event.id
										}),
										success: function (response) {
											console.log('수정 성공:', response);
										},
										error: function (error) {
											console.error('수정 실패:', error);
										}
									});
						},
						eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
							console.log(obj);
							console.log("삭제임");
						},
						eventClick : function(obj){
							console.log(obj)
							console.log("클릭임");
						},
						select: function (arg) {
							$('#eventModal').modal('show');
							// 부트스트랩 모달 열기
							
							var startDate = arg.start.toISOString().slice(0, 10); // 선택된 날짜의 날짜 부분만 추출
							var endDate = arg.end ? arg.end.toISOString().slice(0, 10) : startDate; // 종료 날짜가 있으면 그 날짜를 선택, 없으면 시작 날짜와 같은 날짜 선택
							
							// 시작 날짜에 하루를 더하여 설정
							var nextDay = new Date(arg.start);
							nextDay.setDate(nextDay.getDate() + 1);
							var nextDayISO = nextDay.toISOString().slice(0, 10);
							
							$('#start').val(nextDayISO + 'T' + getCurrentTime()); // 시작 날짜 입력란에 선택된 날짜와 현재 시간 값 설정
							$('#end').val(endDate + 'T18:00'); // 종료 날짜 입력란에 선택된 날짜 값 설정
							
							// 현재 시간을 HH:mm 형식으로 반환하는 함수
							function getCurrentTime() {
								var now = new Date();
								var hour = now.getHours().toString().padStart(2, '0'); // 시간을 두 자리로 변환하고 앞에 0을 채움
								var minute = now.getMinutes().toString().padStart(2, '0'); // 분을 두 자리로 변환하고 앞에 0을 채움
								return hour + ':' + minute; // 시간과 분을 합쳐서 반환
							}
							
							
							// 모달에서 이벤트 제목 입력 후 저장 버튼 클릭 시
							$('#saveEventBtn').off('click').click(function () {
								var employeeId = $('#emid').val();
								var content = $('#content').val();
								if (content.trim() === "") {
									alert("내용이 입력되지 않았습니다.")
									return;
								}
								var title = $('#title').val(); // 모달의 이벤트 제목 입력란에서 제목 가져오기
								if (title.trim() === "") {
									alert("제목을 입력하지 않았습니다.");
									return;
								}

								if (title) {
									calendar.addEvent({
										title: title,
										start: arg.start,
										end: arg.end,
										allDay: arg.allDay

									});

									// 저장 버튼 클릭 시 AJAX를 통해 데이터를 서버에 전송
									let start = document.getElementById("start").value;
									let end = document.getElementById("end").value;
									$.ajax({
										url: '/insert',
										type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({
											title: title,
											content: content,
											start: start,
											end: end,
											employeeId: managerId.value
										}),
										success: function (response) {
											console.log('저장 성공:', response);
										},
										error: function (error) {
											console.error('저장 실패:', error);
										}
									});
								}
								calendar.unselect();
								
								$('#eventModal').modal('hide'); // 모달 닫기
							});
						},

					});
					// 캘린더 랜더링
					$(document).ready(function () {
						// 서버에서 데이터를 가져오는 AJAX 요청
						$.ajax({
							url: 'http://localhost/list',
							type: 'GET',
							success: function (data) {
								// 가져온 데이터를 풀캘린더에 추가
								data.forEach(function (eventData) {
									console.log(eventData.id+"LLLLL");
									calendar.addEvent({
										id: eventData.id, // 이벤트 ID
										title: eventData.title, // 이벤트 제목
										start: eventData.start, // 이벤트 시작 날짜
										end: eventData.end, // 이벤트 종료 날짜
										// 기타 이벤트 속성 등을 추가할 수 있습니다.
									});
								});
							},
							error: function (error) {
								console.error('데이터를 가져오는 데 실패했습니다:', error);
							}
						});
					});
					calendar.render();
				});
			})();
			$(document).ready(function () {
				// 종일 체크박스 클릭 이벤트 처리
				$('#alltime').change(function () {
					if (this.checked) {
						// 종일 옵션이 체크되었을 때
						$('#end').val(getEndDateAllDay()); // 종료 시간을 23:59:59로 설정
					} else {
						// 종일 옵션이 해제되었을 때
						$('#end').val(getEndDateNormal()); // 종료 시간을 기존의 18:00으로 설정
					}
				});

				// 종일 옵션 체크 시 종료 시간을 23:59:59로 반환하는 함수
				function getEndDateAllDay() {
					var endDate = $('#start').val().slice(0, 10); // 시작 날짜를 가져와서 ISO 형식으로 변환
					return endDate + 'T23:59:59'; // 종료 시간을 23:59:59로 설정하여 반환
				}

				// 종일 옵션 해제 시 종료 시간을 18:00으로 반환하는 함수
				function getEndDateNormal() {
					var endDate = $('#start').val().slice(0, 10); // 시작 날짜를 가져와서 ISO 형식으로 변환
					return endDate + 'T18:00'; // 기존의 종료 시간(18:00)으로 설정하여 반환
				}
			});

		</script>
		<script type="module" src="/js/weather/script.js"></script>
		</html>