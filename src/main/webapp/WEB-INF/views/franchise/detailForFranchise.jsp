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
        <h1>가맹점 상세정보</h1>
    </div>
    <section class="section">
        <section class="section profile">
            <div class="row">
                <div class="col">

                    <div class="card">
                        <div class="card-body pt-3">
                            <!-- Bordered Tabs -->
                            <ul class="nav nav-tabs nav-tabs-bordered">

                                <li class="nav-item">
                                    <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">정보</button>
                                </li>
                                <li class="nav-item">
                                    <button id="changePasswordButton" class="nav-link text-danger">비밀번호 변경</button>
                                </li>

                            </ul>
                            <!-- Detail -->
                            <div class="tab-content pt-2">

                                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                                    <h5 class="card-title">상세 페이지</h5>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label ">지점명</div>
                                        <div class="col-lg-9 col-md-8">${vo.name}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">대표</div>
                                        <div class="col-lg-9 col-md-8">${vo.ownerName}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">계약일</div>
                                        <div class="col-lg-9 col-md-8">${vo.contractDate}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">전화번호</div>
                                        <div class="col-lg-9 col-md-8">${vo.contactNumber}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">이메일</div>
                                        <div class="col-lg-9 col-md-8">${vo.email}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">담당자</div>
                                        <div class="col-lg-9 col-md-8">${vo.manager.department.name} ${vo.manager.name} ${vo.manager.position.name}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">우편번호</div>
                                        <div class="col-lg-9 col-md-8">${vo.postCode}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">주소</div>
                                        <div class="col-lg-9 col-md-8">${vo.address}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">상세주소</div>
                                        <div class="col-lg-9 col-md-8">${vo.addressDetail}</div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">계약서</div>
                                        <div class="col-lg-9 col-md-8">
                                            <a href="#" data-bs-toggle="tooltip" data-bs-placement="bottom" title="미리보기">
                                                ${vo.contract.originName}
                                            </a>
                                            <a href="/fileDown?id=${vo.contract.id}" class="link-dark" data-bs-toggle="tooltip" data-bs-placement="bottom" title="저장">
                                                <c:if test='${vo.contract.extension eq "pdf"}'>
                                                    <i class="bi bi-file-earmark-pdf"></i>
                                                </c:if>
                                                <c:if test="${vo.contract.extension eq 'png' or vo.contract.extension eq 'jpg' or vo.contract.extension eq 'jpeg'}">
                                                    <i class="bi bi-file-earmark-image"></i>
                                                </c:if>
                                                <c:if test="${!(vo.contract.extension eq 'png' or vo.contract.extension eq 'jpg' or vo.contract.extension eq 'jpeg' or vo.contract.extension eq 'pdf')}">
                                                    <i class="bi bi-file-earmark"></i>
                                                </c:if>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">사업자등록증</div>
                                        <div class="col-lg-9 col-md-8">
                                            <a href="#" data-bs-toggle="tooltip" data-bs-placement="bottom" title="미리보기">
                                                ${vo.register.originName}
                                            </a>
                                            <a href="/fileDown?id=${vo.register.id}" class="link-dark" data-bs-toggle="tooltip" data-bs-placement="bottom" title="저장">
                                                <c:if test="${vo.register.extension eq 'pdf'}">
                                                    <i class="bi bi-file-earmark-pdf"></i>
                                                </c:if>
                                                <c:if test="${vo.register.extension eq 'png' or vo.register.extension eq 'jpg' or vo.register.extension eq 'jpeg'}">
                                                    <i class="bi bi-file-earmark-image"></i>
                                                </c:if>
                                                <c:if test="${!(vo.register.extension eq 'png' or vo.register.extension eq 'jpg' or vo.register.extension eq 'jpeg' or vo.register.extension eq 'pdf')}">
                                                    <i class="bi bi-file-earmark"></i>
                                                </c:if>
                                            </a>
                                        </div>
                                    </div>

                                </div>
                            </div><!-- End Bordered Tabs -->

                        </div>
                    </div>

                </div>
            </div>
        </section>
    </section>
</main><!-- End #main -->
<div class="modal" tabindex="-1" id="dept-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">비밀번호 변경</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="changePasswordForm" class="d-flex flex-column align-items-center">
                    <div class="row justify-content-center mb-3 w-75">
                        <input name="id" type="hidden" value="${vo.id}">
                        <div>
                            <b id="errorMessage" style="color:red"></b>
                        </div>
                        <div class="form-group mb-3 mt-3">
                            <label for="prevPassword" class="form-label"><b>기존 비밀번호</b></label>
                            <input id="prevPassword" name="prevPassword" type="password" class="form-control" placeholder="기존 비밀번호" required>
                        </div>
                        <div class="form-group mb-3 mt-3">
                            <label for="password" class="form-label"><b>새로운 비밀변호</b></label>
                            <input id="password" name="password" type="password" class="form-control" placeholder="새로운 비밀변호" required>
                        </div>
                        <div class="form-group mb-3 mt-3">
                            <label for="passwordCheck" class="form-label"><b>비밀번호 확인</b></label>
                            <input id="passwordCheck" name="passwordCheck" type="password" class="form-control" placeholder="새로운 비밀번호 확인" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">변경</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script>
    const modal = new bootstrap.Modal(document.getElementById("dept-modal"));
    const changePasswordButton = document.getElementById("changePasswordButton");

    changePasswordButton.addEventListener("click", async function (event){
        modal.show();
    })
    const changePasswordForm = document.getElementById("changePasswordForm")
    changePasswordForm.addEventListener('submit', async function (event) {
        const formData = new FormData(changePasswordForm);
        const errorMessage = document.getElementById("errorMessage");
        event.preventDefault()
        event.stopPropagation()
        console.log(formData)
        if(formData.get("password") !== formData.get("passwordCheck")){
            errorMessage.innerHTML = "비밀번호가 동일한지 확인해주세요.";
            return;
        }

        const response = await fetch("/v1/api/franchise/updatePassword",{
            method: "POST",
            body: formData
        });
        const result = await response.json();
        console.log(result.status);
        if (result.status === "OK") {
            alert(result.message);
            location.href = "/employee/login"
        } else {

            errorMessage.innerHTML = result.message;
        }
    }, false)
</script>
</body>

</html>
