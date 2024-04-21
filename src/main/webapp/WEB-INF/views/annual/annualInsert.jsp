<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
</head>
<body>
<!-- ======= Header ======= -->
	<main id="main" class="main">
		<div class="pagetitle">

		</div>
	<section class="section">
			<div class="row">
				<div class="col-auto">
					<div class="card">

						<div class="card-body mt-3 ">
							<form id="joinForm" method="POST" onsubmit="return validateForm()">
								<div class="form-group mb-3 ">
									<label for="history" class="form-label"><b>제목</b></label> <input
										id="history" name="history" type="text" class="form-control"
										placeholder="사유를 적어주세요.">
								</div>
								
								<label for="remainderAnnual" class="form-label"><b>연차 개수</b></label>
								<div class="input-group mb-3">
									<button class="btn btn-primary" type="button" onclick="minus()">-</button>
									<div class="">
									<input type="text" class=" form-control text-center"
										id="remainderAnnual" name="remainderAnnual" value="0" readonly>
										</div>
									<button class="btn btn-primary" type="button" onclick="plus()">+</button>
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
								
								<button type="submit" id="submitButton"
									class="btn btn-primary mt-3">등록</button>
							</form>
						</div>
					</div>
				</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->
	<div class="modal" tabindex="-1" id="dept-modal" >
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
	<!-- ======= Footer ======= -->
	<!-- ======= Script ======= -->
	<c:import url="../template/script.jsp" />

	<script type="module ">
    import orgChart from "/js/orgChart/orgChart.js";
    const searchButton = document.getElementById("searchButton");
    searchButton.addEventListener("click", ()=>{
        modal.show();
    })
    orgChart.init("orgChart", (data)=>{
        console.log(data);
        modal.hide();
        managerId.value = data.id;
        managerName.value = data.name;
    },"person");
    const modal = new bootstrap.Modal(document.getElementById("dept-modal"));
</script>
<script>
function validateForm() {
    var historyInput = document.getElementById('history').value.trim();
    var remainderAnnualInput = document.getElementById('remainderAnnual').value.trim();
    var employeeIdInput = document.getElementById('managerId').value.trim(); // Assuming managerId represents employeeId

    if (historyInput === "") {
        alert("제목을 입력하세요.");
        return false;
    }
    if (employeeIdInput === "") {
        alert("직원을 선택하세요.");
        return false;
    }
   
    return true; 
}
function plus() {
    var input = document.getElementById('remainderAnnual');
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue + 1;
}

function minus() {
    var input = document.getElementById('remainderAnnual');
    var currentValue = parseInt(input.value, 10);
    input.value = currentValue - 1;
}

</script>

</body>
</html>
