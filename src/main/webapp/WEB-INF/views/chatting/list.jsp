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
    main, nav{
        height: calc(100vh);
    }

    main{
        margin: 0 0 0 90px !important;
        padding: 0 0 0 0 !important;
    }

    nav{
        width: 90px;
        position: fixed;
        margin-top: 0 !important;
        left: 0;
    }

    section{
        height: calc(100vh);
        overflow: hidden;
    }
</style>
</head>
<body class="container-fluid" style="padding: 0 0 0 0 !important;">

    <nav class="text-center bg-warning">
        <div>
            <ul class="list-group">
                <li class="list-group-item">
                    <img class="mb-2" src="/img/logo.png" alt="">
                </li>
                <li class="list-group-item" data-bs-target="#main" data-bs-slide-to="0">
                    직원 목록
                </li>
                <li class="list-group-item" data-bs-target="#main" data-bs-slide-to="1">
                    채팅 방 목록
                </li>
                <li id="chatting-page-btn list-group-item" class="d-none" data-bs-target="#main" data-bs-slide-to="2"></li>
            </ul>
        </div>
    </nav>
    <main id="main" class="carousel slide">
        <div class="carousel-inner">
            <section class="carousel-item active" style="background-color: red">
                <div class="row justify-content-end">
                    <button class="btn btn-primary btn-sm">
                        채팅방 생성
                    </button>
                </div>
                <div>
                    <input type="text" class="form-control" placeholder="부서명, 직책, 이름으로 검색">
                </div>
                <div id="chat-employee-list">
                    <div class="fs-6 bg-secondary-light border-top border-bottom border-black">
                        S-Chicken
                    </div>
                    <div class="d-flex gap-3 bg-white p-2">
                        <div>
                            <img class="rounded-circle" src="/img/logo.png" alt="" width="50px" height="50px">
                        </div>
                        <div>
                            <h4>S-Chicken</h4>
                            <div>사장 김범서</div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="carousel-item" style="background-color: #00ff80">2</section>
            <section class="carousel-item" style="background-color: #0a53be">3</section>
        </div>
    </main>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.2.1/classic/ckeditor.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/js/main.js"></script>
</body>

</html>
