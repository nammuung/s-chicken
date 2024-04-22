<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div class="row justify-content-center">
				<div class="col-auto">
					<div class="p-5"  >

						<div class="card-body mt-3 ">
							<form id="joinForm" method="POST" action="/annual/annualInsert" onsubmit="return validateForm()">
								<div class="form-group mb-3 ">
									<label for="history" class="form-label"><b>제목</b></label> <input
										id="history" name="history" type="text" class="form-control"
										placeholder="사유를 적어주세요.">
								</div>
								
								<label for="remainderAnnual" class="form-label"><b>연차 개수</b></label>
								<div class="input-group mb-3">
									<button class="btn btn-primary" type="button" id="minusBtn">-</button>
									<div class="">
									<input type="text" class=" form-control text-center"
										id="remainderAnnual" name="remainderAnnual" value="0" readonly>
										</div>
									<button class="btn btn-primary" type="button" id="plusBtn">+</button>
								</div>

								

								<div class="row justify-content-center  mb-3 w-30">
									<label for="managerId" class="form-label"><b>직원 조회</b></label>
									<div class="form-group mb-3 d-flex">
										<input id="managerId" name="employeeId" type="hidden"
											class="form-control me-1"> <input id="managerName"
											type="text" class="form-control me-1" readonly required>
										<input type="button" id="searchButton" value="직원 조회"
											class="btn btn-primary">
									</div>
								</div>
								<input type="hidden" value="0" name="isAnnual"/>
								<input type="hidden" value="0" name="annual"/>
								<button type="submit" id="submitButton"
									class="btn btn-primary mt-3">등록</button>
							</form>
						</div>
					</div>
				</div>
				</div>
				
	<!-- End #main -->
	<div class="modal"  tabindex="-1" id="dept-modal"  style="margin-top: 10%; margin-left: -3%">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">부서 등록</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div id="orgChart"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline-secondary"
						data-bs-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
