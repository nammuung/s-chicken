<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<div class="pagetitle">
			<h1>Profile</h1>
		</div>
		<section class="section">
			<section class="section profile">
				<div class="row">
					<div class="col-xl-4">

						<div class="card">
							<div
								class="card-body profile-card pt-4 d-flex flex-column align-items-center">
								<img src="/fileDown?id=${detail.file.id}" alt="Profile"
									class="rounded-circle"
									onerror="this.onerror=null; this.src='/img/기본.jpg';">

								<h2>${detail.name}</h2>
								<h3>${detail.department.name}/${detail.position.name}</h3>
							</div>
						</div>

					</div>

					<div class="col-xl-8">

						<div class="card">
							<div class="card-body pt-3">
								<!-- Bordered Tabs -->
								<ul class="nav nav-tabs nav-tabs-bordered">

									<li class="nav-item">
										<button class="nav-link active" data-bs-toggle="tab"
											data-bs-target="#profile-overview">Detail</button>
									</li>

									<li class="nav-item">
										<button class="nav-link" data-bs-toggle="tab"
											data-bs-target="#profile-edit">Edit Profile</button>
									</li>


									<li class="nav-item">
										<button class="nav-link" data-bs-toggle="tab"
											data-bs-target="#profile-change-password">Change
											Password</button>
									</li>
									<li class="nav-item">
										<form action="./employeeResetPassword" method="post"
											id="reset">

											<input type="hidden" value="${detail.id}" name="hiddenId">
											<input type="hidden" value="${detail.dateOfEmployment}"
												name="dateOf">
											<button id="resertPassword" class="nav-link text-danger"
												type="submit"
												<sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                
            											</sec:authorize>
												<sec:authorize access="!hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                										disabled 
            											</sec:authorize>>
												<sec:authorize
													access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">비밀번호 초기화</sec:authorize>
											</button>
										</form>
									</li>
								</ul>
								<!-- Detail -->
								<div class="tab-content pt-2">

									<div class="tab-pane fade show active profile-overview"
										id="profile-overview">
										<h5 class="card-title">상세 페이지</h5>

										<div class="row">
											<div class="col-lg-3 col-md-4 label ">사원 번호</div>
											<div class="col-lg-9 col-md-8">${detail.id}</div>

										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">이름</div>
											<div class="col-lg-9 col-md-8">${detail.name}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">입사일</div>
											<div class="col-lg-9 col-md-8">${detail.dateOfEmployment}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">전화번호</div>
											<div class="col-lg-9 col-md-8">${detail.phoneNumber}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">이메일</div>
											<div class="col-lg-9 col-md-8">${detail.email}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">생일</div>
											<div class="col-lg-9 col-md-8">${detail.residentNumber}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">부서</div>
											<div class="col-lg-9 col-md-8">${detail.department.name}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">직급</div>
											<div class="col-lg-9 col-md-8">${detail.position.name}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">은행/계좌</div>
											<div class="col-lg-9 col-md-8">${detail.bankName}/${detail.accountNumber}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">주소</div>
											<div class="col-lg-9 col-md-8">${detail.address}</div>
										</div>

										<div class="row">
											<div class="col-lg-3 col-md-4 label">상세주소</div>
											<div class="col-lg-9 col-md-8">${detail.addressDetail}</div>
										</div>

										<sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                																<div class="row">
											<div class="col-lg-3 col-md-4 label">퇴사여부</div>
											<div class="col-lg-9 col-md-8">${detail.isLeaved}</div>
										</div>
            											</sec:authorize>								

										<div class="row">
											<div class="col-lg-3 col-md-4 label">연차</div>
											<div class="col-lg-9 col-md-8">
												<a href=".">연차내역확인</a>
											</div>
										</div>

									</div>

									<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

										<!-- Profile Edit Form -->
										<form action="./updateEmployee" method="post"
											enctype="multipart/form-data" onsubmit="return submitForm()">
											<div class="row mb-3">
												<label for="profileImage"
													class="col-md-4 col-lg-3 col-form-label">프로필</label>
												<div class="col-md-8 col-lg-9">
													<img src="/fileDown?id=${detail.file.id}" alt="Profile"
														onerror="this.onerror=null; this.src='/img/기본.jpg';">
													<div class="pt-2">
														<input type="file" id="fileInput" name="attach"
															style="display: none;" /> <a href="#"
															class="btn btn-primary btn-sm"
															title="Upload new profile image" id="uploadLink"> <i
															class="bi bi-upload"></i></a>
														<!-- <a href="#"
															class="btn btn-danger btn-sm"
															title="Remove my profile image"> <i
															class="bi bi-trash"></i></a> -->
													</div>
												</div>
											</div>

											<div class="row mb-3">
												<label for="name" class="col-md-4 col-lg-3 col-form-label">이름</label>
												<div class="col-md-8 col-lg-9">
													<input name="name" type="text" class="form-control"
														id="name" value="${detail.name}" disabled>
												</div>
											</div>


											<div class="row mb-3">
												<label for="dateOfEmployment"
													class="col-md-4 col-lg-3 col-form-label">입사일</label>
												<div class="col-md-8 col-lg-9">
													<input id="dateOfEmployment" type="text"
														name="dateOfEmployment" class="form-control"
														value="${detail.dateOfEmployment}" disabled>
												</div>
											</div>

											<div class="row mb-3">
												<label for="phoneNumber"
													class="col-md-4 col-lg-3 col-form-label">전화번호</label>
												<div class="col-md-8 col-lg-9">
													<input name="phoneNumber" type="text" class="form-control"
														id="phoneNumber" value="${detail.phoneNumber}" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="email" class="col-md-4 col-lg-3 col-form-label">이메일</label>
												<div class="col-md-8 col-lg-9">
													<input id="email" type="email" class="form-control"
														name="email" placeholder="이메일" value="${detail.email}" />
												</div>
											</div>

											<div class="row mb-3">
												<label for="residentNumber"
													class="col-md-4 col-lg-3 col-form-label">생년월일</label>
												<div class="col-md-8 col-lg-9">
													<input id="residentNumber" type="text" class="form-control"
														name="residentNumber" value="${detail.residentNumber}"
														<sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                
            											</sec:authorize>
														<sec:authorize access="!hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                										disabled 
            											</sec:authorize>>
												</div>
											</div>

											<div class="row mb-3">
    <label for="department" class="col-md-4 col-lg-3 col-form-label">부서</label>
    <div class="col-md-8 col-lg-9">
        <select class="form-select" id="department"
            <sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
        
            </sec:authorize>
            <sec:authorize access="!hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                disabled value="gld"
            </sec:authorize>>
            <option value="${detail.departmentId}">${detail.department.name}</option>
        </select>
    </div>
</div>
<div class="row mb-3">
    <label for="team" class="col-md-4 col-lg-3 col-form-label">팀</label>
    <div class="col-md-8 col-lg-9">
        <select class="form-select" id="team" name="departmentId"
            <sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
        
            </sec:authorize>
            <sec:authorize access="!hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                disabled 
            </sec:authorize>>
            <option value="${detail.departmentId}">${detail.department.name}</option>
        </select>
    </div>
</div>


											<div class="row mb-3">
												<label for="posId" class="col-md-4 col-lg-3 col-form-label">직급</label>
												<div class="col-md-8 col-lg-9">
													<select class="form-select" id="posId" name="posId" <sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                
            											</sec:authorize>
														<sec:authorize access="!hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                										disabled 
            											</sec:authorize>>
														<option value="${detail.posId}">${detail.position.name}</option>
														<option value="2">사원</option>
														<option value="3">주임</option>
														<option value="4">계장</option>
														<option value="5">대리</option>
														<option value="6">과장</option>
														<option value="7">차장</option>
														<option value="8">부장</option>
														<option value="9">감사</option>
														<option value="10">이사</option>
														<option value="11">상무</option>
														<option value="12">부사장</option>
														<option value="13">사장</option>
													</select>
												</div>
											</div>







											<div class="row mb-3">
												<label for="bankName"
													class="col-md-4 col-lg-3 col-form-label">은행명</label>
												<div class="col-md-8 col-lg-9">
													<select class="form-select" id="bankName" name="bankName"
														value="${detail.bankName}">
														<option value="0">은행 선택</option>
														<option value="국민은행">국민은행</option>
														<option value="신한은행">신한은행</option>
														<option value="농협">농협</option>
														<option value="신협">신협</option>
													</select>
												</div>
											</div>

											<div class="row mb-3">
												<label for="accountNumber"
													class="col-md-4 col-lg-3 col-form-label">계좌번호</label>
												<div class="col-md-8 col-lg-9">
													<input id="accountNumber" type="text" class="form-control"
														name="accountNumber" placeholder="계좌번호"
														value="${detail.accountNumber}">
												</div>
											</div>

<sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                
            											
											<div class="row mb-3">
												<label for="isLeaved"
													class="col-md-4 col-lg-3 col-form-label">퇴사여부</label>
												<div class="col-md-8 col-lg-9">
													<select class="form-select" id="isLeaved" name="isLeaved" >
														<option value="1">${detail.isLeaved}</option>
														<option value="true">true</option>
														<option value="false">false</option>
													</select>
												</div>
											</div>
</sec:authorize>

											<label for="address" class="form-label"><b>주소</b></label>
											<div class="form-group mb-3 d-flex">
												<input type="text" id="postcode" name="postcode"
													placeholder="우편번호" class="form-control"
													value="${detail.postcode}"> <input type="button"
													onclick="openPostcodePopup()" value="우편번호 찾기"
													class="btn btn-primary" name="">
											</div>

											<div class="form-group mb-3 d-flex">
												<input type="text" id="address" name="address"
													class="form-control" placeholder="주소"
													value="${detail.address}"> <input type="text"
													id="addressDetail" name="addressDetail"
													class="form-control ml-2" placeholder="상세주소"
													value="${detail.addressDetail}">
											</div>


											<div class="text-center">
												<button type="submit" class="btn btn-primary" onclick="enableFields()">Save
													Changes</button>
											</div>
										</form>
										<!-- End Profile Edit Form -->

									</div>


									<div class="tab-pane fade pt-3" id="profile-change-password">
										<!-- Change Password Form -->
										<form action="./updatePassword" method="post">

											<div class="row mb-3">
												<label for="password"
													class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
												<div class="col-md-8 col-lg-9">
													<input type="password" class="form-control" id="password"
														name="password">
												</div>
											</div>

											<div class="row mb-3">
												<label for="newPassword"
													class="col-md-4 col-lg-3 col-form-label">변경할 비밀번호</label>
												<div class="col-md-8 col-lg-9">
													<input name="newpassword" type="password"
														class="form-control" id="newPassword">
												</div>
											</div>

											<div class="row mb-3">
												<label for="renewPassword"
													class="col-md-4 col-lg-3 col-form-label">변경비밀번호 확인</label>
												<div class="col-md-8 col-lg-9">
													<input name="renewpassword" type="password"
														class="form-control" id="renewPassword">
												</div>
											</div>
											<input type="hidden" value="${detail.id}" name="hiddenId">
											<div class="text-center col-mt-5">
												<button type="submit" class="btn btn-primary">Change
													Password</button>
											</div>
										</form>
										<!-- End Change Password Form -->

									</div>

								</div>
								<!-- End Bordered Tabs -->

							</div>
						</div>

					</div>
				</div>
			</section>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<c:import url="../template/footer.jsp" />
	<!-- ======= Script ======= -->
	<c:import url="../template/script.jsp" />
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="/js/employee/address.js"></script>
<script src="/js/employee/department.js">
	
</script>
<script src="/js/employee/password.js"></script>
<script src="/js/employee/employeeupdate.js"></script>
<!--  	<script type="text/javascript">
		history.replaceState({}, null, location.pathname);
		
	</script> -->
<script>
	var posIdValue = "${detail.posId}";
	var bankNameValue = "${detail.bankName}";

	// Get the select element
	var selectElement = document.getElementById("posId");
	var bselectElement = document.getElementById("bankName");

	// Set the value of the select element to the posIdValue
	selectElement.value = posIdValue;
	bselectElement.value = bankNameValue;
	/////////////////////////////
	// Set the selected department
</script>
</html>