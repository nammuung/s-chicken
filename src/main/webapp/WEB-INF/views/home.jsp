<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>S치킨-그룹웨어</title>
		<c:import url="template/head.jsp" />
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
				<div id="widget" class="justify-content-center">
					<div class="row nested-sortable">
						<div class="col-8">
							<div class="card" style="">
								<div class="card-body">
									<div id='calendar-container' class="mt-3">
										<div id='calendar'></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-4">
							<c:import url="./template/weather.jsp"/>
						</div>
					</div>
					<div class="row nested-sortable">
						<div class="col pagetitle  ">
							<div class="card h-100" >
								<div class="card-body mt-3">
								<h1>공지사항</h1>
								<table class="table text-center text-nowrap m-0">
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
							</div>
						</div>
						<div class="col pagetitle  ">
							<div class="card h-100" >
								<div class="card-body mt-3">
									<h1>결재목록</h1>
									<table class="table text-center text-nowrap m-0">
										<thead>
									<tr>
                                    <th style="width: 15%">문서번호</th>                                    
                                    <th style="width: 40%">제목</th>
                                    <th style="width: 10%">기안자</th>
                                    <th style="width: 10%">문서종류</th>
                                    <th style="width: 10%">상신일</th>
                                    <th style="width: 5%">상태</th>
                                </tr>
										</thead>
										<tbody>
    <c:choose>
        <c:when test="${empty dlist}">
            <tr>
                <td colspan="6">현재 진행중인 결재 내역이 존재 하지 않습니다.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${dlist}" var="vo" end="5">
                <c:if test="${vo.approvalVOs[0].rank ne 0}">                       		
                    <tr>
                        <td>${vo.id}</td>
                        <c:if test="${vo.templateVO.tempName eq '상여신청서'}"> 
                            <td class="text-start"><a href="./document/writenList/writenBonus?id=${vo.id}" onclick="openbonus(${vo.id})">${vo.title}</a></td>
                        </c:if>
                        <td>${vo.employeeVO.name}</td>
                        <td>${vo.templateVO.tempName}</td>
                        <td>${vo.writeDate}</td>
                        <c:choose>
                            <c:when test="${vo.status eq 0}">
                                <td>진행중</td>
                            </c:when>
                            <c:when test="${vo.status eq 1}">
                                <td>결재완료</td>
                            </c:when>
                            <c:when test="${vo.status eq 2}">
                                <td>반려</td>
                            </c:when>
                        </c:choose>	                                    
                    </tr>
                </c:if>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</tbody>
									</table>
								</div>
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
								<input id="start" name="start" type="datetime-local" class="form-control mb-3" value="obj.event.start">
							</div>
							<div class="col-5 ms-2 me-2">
								<label for="end" class="form-label"><b>종료</b></label>
								<input id="end" name="end" type="datetime-local" class="form-control mb-3 col-2" >
							</div>
							<input type="checkbox" value="" class="mt-3 me-1" id="alltime"><span
								style="margin-top: 40px;">종일</span>
						</div>
						<label for="title" class="form-label"><b>제목</b></label>
						<input type="text" id="title" class="form-control mb-3" placeholder="제목을 입력해주세요." >
						<label for="content" class="form-label"><b>내용</b></label>
						<input type="text" id="content" class="form-control mb-3" placeholder="내용을 입력해주세요.">
						
						<input type="hidden" id="share" nanem="share" class="form-control mb-3" disabled
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

		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">일정</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			  </div>
			  <div class="modal-body">
				<form>
				  <div class="d-flex">
					<div class="col-5 me-2">
						<label for="start2" class="form-label"><b>시작</b></label>
						<input id="start2" name="start" type="datetime-local" class="form-control mb-3" >
					</div>
					<div class="col-5 ms-2 me-2">
						<label for="end2" class="form-label"><b>종료</b></label>
						<input id="end2" name="end" type="datetime-local" class="form-control mb-3 col-2" >
					</div>
				</div>
					<label for="title2" class="form-label"><b>제목</b></label>
					<input type="text" id="title2" name="title" class="form-control mb-3"  >
					<label for="content2" class="form-label"><b>내용</b></label>
					<input type="text" id="content2" class="form-control mb-3" >
					<label for="share2" class="form-label"><b>공유자</b></label>
					<input type="text" id="share2" name="share" class="form-control mb-3" disabled >
					<div class="d-flex justify-content-end">
					<button type="button" id="updateButton" class="btn btn-primary" style="margin-right: 10px">저장</button>
					<button type="button" id="deleteButton" class="btn btn-danger">삭제</button>
					</div>
				</form>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			  </div>
			</div>
		  </div>
		</div>
		<script type="module" src="/js/weather/script.js"></script>
		<script src="/js/home/sortable.js"></script>
		<script type="module" src="/js/calendar/calendarmodule.js"></script>
		<script src="/js/calendar/calendar.js"> </script>
	</body>
</html>
