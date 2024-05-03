<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>채팅</title>

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

    <!-- fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

    <!-- Template Main CSS File -->
    <link href="/css/chatting.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
</head>
<body class="container-fluid" style="padding: 0 0 0 0 !important;">

    <div class="d-none" data-logined-id="${myProfile.id}"></div>

    <nav class="text-center">
        <div>
            <ul class="list-group">
                <li class="list-group-item">
                    <img class="mb-2" src="/img/logo.png" alt="" height="30px">
                </li>
                <li id="employee-list-btn" class="list-group-item text-nowrap now-page" data-bs-target="#main" data-bs-slide-to="0">
                    직원목록
                </li>
                <li id="chatroom-list-btn" class="list-group-item text-nowrap" data-bs-target="#main" data-bs-slide-to="1">
                    채팅방
                </li>
                <li id="chatting-page-btn" class="d-none" data-bs-target="#main" data-bs-slide-to="2"></li>
            </ul>
        </div>
    </nav>

    <main id="main" class="carousel slide">
        <div class="carousel-inner">
            <section class="carousel-item active">
                <div class="row justify-content-end">
                    <button class="btn btn-primary btn-sm">
                        +
                    </button>
                </div>
                <div>
                    <input data-element-id="search-input" data-target="[data-employee-search]" type="text" class="form-control" placeholder="직책, 이름으로 검색">
                </div>

                <div id="chat-employee-list" class="p-3 pt-0 accordion" style="height:calc(100vh - 68px);overflow: auto">
                    <div class="d-flex gap-3 p-1 my-3">
                        <div>
                            <img class="rounded-circle" src="${myProfile.profileImg}" alt="" width="55px" height="55px">
                        </div>
                        <div class="p-2">
                            <h4 style="font-family: 'Pretendard-Regular'">${myProfile.departmentName} ${myProfile.name}</h4>
                        </div>
                    </div>
                    <c:forEach items="${orgList}" var="dept">
                        <div class="accordion-item" id="accordion-${dept.id}">
                            <div class="accordion-header">
                                <button
                                        class="accordion-button p-2 small"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#collapse-${dept.id}"
                                        aria-expanded="true"
                                        aria-controls="collapse-id"
                                        style="font-family: 'Pretendard-Regular'"
                                >
                                    ${dept.name}
                                </button>
                            </div>
                            <div id="collapse-${dept.id}" class="accordion-collapse collapse show">
                                <div class="accordion-body" style="padding: 0;">
                                    <c:forEach items="${dept.employees}" var="emp">
                                        <div class="d-flex gap-3 p-2 aaa" onclick="onProfileClick(${emp.id})" data-employee-search data-parent-id="accordion-${dept.id}" data-search-name="${emp.name}">
                                            <div>
                                                <img class="rounded-circle" src="${emp.profileImg}" alt="" width="50px" height="50px">
                                            </div>
                                            <div class="p-2">
                                                <h4>${emp.name}</h4>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>

            <section class="carousel-item">
                <div>
                    <input data-element-id="search-input" data-target="[data-chatroom-search]" type="text" class="form-control" placeholder="직책, 이름으로 검색">
                </div>
                <div id="chatroom-list-space" class="py-2" style="height: calc(100vh - 38px); overflow-y: auto">

                </div>
            </section>

            <section class="carousel-item" data-now-open>
                <div class="row justify-content-center chatroom-title">
                    <h1 id="chatroom-name" class="col-8 text-center">채팅방 이름</h1>
                </div>
                <%-- 채팅 공지용 --%>
<%--                <div class="border-bottom row py-3">--%>
<%--                    <div class="col-1 ps-3"><i class="bi bi-megaphone-fill"></i></div>--%>
<%--                    <h2 class="col-10 text-center">이 채팅방의 공지입니다이 채팅방의 공지입니다이 채팅방의 공지입니다이 채팅방의 공지입니다이 채팅방의 공지입니다</h2>--%>
<%--                    <div class="col-1"></div>--%>
<%--                </div>--%>
                <div id="chatting-space">
                </div>

                    <%-- 채팅에서 여러가지 공유용 --%>
<%--                <div class="row d-none">--%>
<%--                    <div class="col-4 text-center fs-4">일정</div>--%>
<%--                    <div class="col-4 text-center fs-4">파일</div>--%>
<%--                    <div class="col-4 text-center fs-4">사진</div>--%>
<%--                </div>--%>

                <div class="input-group" style="height: 83px">
                    <textarea id="chatting-area" class="form-control" style="height: 100%;" maxlength="300"></textarea>
                    <div class="d-none" id="hidden-chatting-area"></div>
                    <button id="send-message-btn" class="btn btn-primary"><i class="far fa-paper-plane"></i></button>
                </div>
            </section>
        </div>
    </main>


    <div class="modal fade" tabindex="-1" id="namecard-modal">
        <div class="modal-dialog modal-dialog-centered mx-auto" style="max-width: 300px">
            <div class="modal-content">
                <div class="card pt-3 mb-0">
                    <div class="card-body text-center">
                        <img data-profile-type="img" class="rounded-circle shadow w-50 mb-3">
                        <h4 data-profile-type="departmentName" class="card-subtitle mb-2 text-muted"></h4>
                        <h2 data-profile-type="name" class="card-title"></h2>
                        <h4 data-profile-type="phoneNumber" class="card-text"></h4>
                        <a data-chatroom-info data-profile-type="chatting" href="#" class="btn btn-primary w-50">채팅하기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.2.1/classic/ckeditor.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/js/chatting/chatroom.js" type="module"></script>
<script>
    const namecardModal = new bootstrap.Modal(document.getElementById("namecard-modal"));
    async function onProfileClick(empId){
        let info= await fetch('/employee/getProfile?id=' + empId).then(res=>res.json())

        document.querySelectorAll("[data-profile-type]")
            .forEach(e => {
                let profileType = e.dataset.profileType;
                switch (profileType){
                    case 'img':
                        e.setAttribute("src", info.profileImg == null ? '/img/기본.jpg' : info.profileImg);
                        break;
                    case 'chatting':
                        e.dataset.targetId = empId;
                        break;
                    default:
                        e.innerText = info[profileType];
                }
            })
        namecardModal.show();
    }

    window.addEventListener("DOMContentLoaded", ()=>{
        let targetId = (new URLSearchParams(window.location.search)).get("target");
        if(targetId == null) return;
        if(document.querySelector("[data-logined-id]").dataset.loginedId === targetId) return;

        let chatStartBtn = document.querySelector("a[data-profile-type=chatting]");
        chatStartBtn.dataset.targetId = targetId;
        chatStartBtn.click();
    })
</script>
</body>

</html>
