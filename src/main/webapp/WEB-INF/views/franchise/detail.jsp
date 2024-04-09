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
                                    <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">수정</button>
                                </li>
                                <li class="nav-item">
                                    <button class="nav-link text-danger">비밀번호 초기화</button>
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
                                        <div class="col-lg-9 col-md-8">${vo.managerId}</div>
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
                                        <div class="col-lg-9 col-md-8"><a href="${vo.contract.url}">${vo.contract.originName}</a></div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label">사업자등록증</div>
                                        <div class="col-lg-9 col-md-8"><a href="${vo.register.url}">${vo.register.originName}</a></div>
                                    </div>

                                </div>

                                <div class="tab-pane fade profile-edit" id="profile-edit">
                                    <h5 class="card-title">수정하기</h5>
                                    <!-- Profile Edit Form -->
                                    <form method="POST" action="/franchise/update" id="updateForm">
                                        <div class="row mb-3">
                                            <label for="fullName" class="col-md-4 col-lg-3 col-form-label">지점명</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="fullName" type="text" class="form-control" id="fullName" value="한라점" disabled>
                                            </div>
                                        </div>


                                        <div class="row mb-3">
                                            <label for="ownerName" class="col-md-4 col-lg-3 col-form-label">대표</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input id="ownerName" type="text" class="form-control" value="${vo.ownerName}" disabled>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="contractDate" class="col-md-4 col-lg-3 col-form-label">계약일</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input id="contractDate" type="date" class="form-control" value="${vo.contractDate}" disabled>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="contactNumber" class="col-md-4 col-lg-3 col-form-label">전화번호</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input name="contactNumber" type="text" class="form-control" id="contactNumber" value="${vo.contactNumber}">
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label for="email" class="col-md-4 col-lg-3 col-form-label">이메일</label>
                                            <div class="col-md-8 col-lg-9">
                                                <input id="email" type="email" class="form-control" placeholder="이메일" value="${vo.email}" disabled>
                                            </div>
                                        </div>



                                        <div class="row mb-3">
                                            <label for="managerId" class="col-md-4 col-lg-3 col-form-label">담당자</label>
                                            <div class="col-md-8 col-lg-9">
                                                <select class="form-select" id="managerId">
                                                    <option value="0">${vo.managerId}</option>
                                                    <option value="1">영업팀</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="row mb-3">
                                            <label class="col-md-4 col-lg-3 col-form-label">우편번호</label>
                                            <div class="col-md-8 col-lg-9 d-flex">
                                                <input type="text" value="${vo.postCode}" class="form-control" disabled>
                                            </div>
                                        </div>


                                        <div class="row mb-3">
                                            <label class="col-md-4 col-lg-3 col-form-label">주소</label>
                                            <div class="col-md-8 col-lg-9 d-flex">
                                                <input type="text" value="${vo.address}" class="form-control me-1" placeholder="주소" disabled>
                                                <input type="text" value="${vo.addressDetail}" class="form-control" placeholder="상세주소" disabled>
                                            </div>
                                        </div>


                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary">저장 하기</button>
                                        </div>
                                    </form><!-- End Profile Edit Form -->

                                </div>
                            </div><!-- End Bordered Tabs -->

                        </div>
                    </div>

                </div>
            </div>
        </section>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script>
    const updateForm = document.getElementById("updateForm");
    updateForm.addEventListener("submit", async function (event){
        event.preventDefault();
        event.stopPropagation();
        const formData = new FormData(updateForm);
        const response = await fetch("/franchise/update", {
            method: "PUT",
            body: formData
        });
        const result = await response.json();
        alert(result.message);
        location.reload();
    })
</script>
</body>

</html>
