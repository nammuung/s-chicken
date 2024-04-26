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
    <link href="/css/style.css" rel="stylesheet">
<style>
    @font-face {
        font-family: 'Pretendard-Regular';
        src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
        font-weight: 400;
        font-style: normal;
    }

    main, nav{
        height: calc(100vh);
    }

    main{
        margin: 0 0 0 90px !important;
        padding: 0 0 0 0 !important;
        background-color: #F8F8FD;
    }

    main *{
        font-family: 'Pretendard-Regular';
    }

    nav{
        width: 90px;
        position: fixed;
        margin-top: 0 !important;
        background-color: #FFF;
        border-right: 1px solid #00000030;
        left: 0;
    }

    section{
        height: calc(100vh);
        overflow: hidden;
    }

    .aaa:hover{
        background-color: #7749F830;
        cursor: pointer;
    }

    .accordion-item{
        border: 0 !important;
        border-top: 1px solid rgba(0,0,0,0.1) !important;
        background-color: rgba(0,0,0,0);
    }

    .accordion-button{
        background-color: rgba(0,0,0,0) !important;
    }

    .now-page{
        border-right: 3px solid #7749F8 !important;
        font-weight: bolder;
    }

    nav .list-group > li{
        border: 0;
        padding-top: 10px;
        padding-bottom: 10px;
        background-color: rgba(0,0,0,0);
    }

    nav .list-group > li:not(:first-child):hover{
        background-color: #7749F830 !important;
        cursor: pointer;
    }

    button:active{
        border:0 !important;
    }

    .chatroom-title{
        padding: 1rem 0 1rem 0;
        border-bottom: 1px solid #00000030;
    }
</style>
</head>
<body class="container-fluid" style="padding: 0 0 0 0 !important;">

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
            <section class="carousel-item">
                <div class="row justify-content-end">
                    <button class="btn btn-primary btn-sm">
                        +
                    </button>
                </div>
                <div>
                    <input id="search-input" type="text" class="form-control" placeholder="직책, 이름으로 검색">
                </div>

                <div id="chat-employee-list" class="p-3 pt-0 accordion" style="height : 91%;overflow: auto">
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
                                        <div class="d-flex gap-3 p-2 aaa" onclick="onProfileClick(${emp.id})" data-parent-id="accordion-${dept.id}" data-search-name="${emp.name}">
                                            <div>
                                                <img class="rounded-circle" src="${emp.profileImg}" alt="" width="50px" height="50px">
                                            </div>
                                            <div class="p-2">
                                                <h4 style="font-family: 'Pretendard-Regular'">${emp.name}</h4>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
            <section class="carousel-item"></section>
            <section class="carousel-item active">
                <div class="row justify-content-center chatroom-title">
                    <h1 class="col-8 text-center">채팅방 이름</h1>
                </div>
                <div>공지창</div>
                <div>채팅창</div>
                <div class="row">
                    <div class="col-4 text-center fs-4">일정</div>
                    <div class="col-4 text-center fs-4">파일</div>
                    <div class="col-4 text-center fs-4">사진</div>
                    <div class="input-group">
                        <textarea class="form-control"></textarea>
                        <button class="btn btn-primary"><i class="far fa-paper-plane"></i></button>
                    </div>
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
                        <a data-profile-type="chatting" href="#" class="btn btn-primary w-50">채팅하기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.2.1/classic/ckeditor.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/js/chatting/chatroom.js"></script>
</body>

</html>
