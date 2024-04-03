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
        <h1>채팅방 이름</h1>
    </div>

    <div class="row justify-content-center">
        <div class="col-9">
            <div class="card p-3">
                <div class="row">
                    <div class="col-12 d-flex">
                        <div>< 돌아가기</div>
                        <h2 class="ms-3">영업팀 단톡</h2>
                    </div>
                    <div class="col-12 border bg-white shadow-sm">
                        <div class="text-sm-start my-1">채팅방 공지</div>
                        <h2>채팅방 공지 입니다.</h2>
                    </div>
                    <div class="col-12">

                        <!-- 채팅방 채팅 내용 -->
                        <div class="row chatting-content">
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <!-- 말풍선 -->
                                    <div class="d-inline-block mt-2 border p-2 shadow" style="background-color: #00000010; border-color:#000!important; border-radius: 0 15px 15px 15px">
                                        채팅 내용채팅 내용채팅채팅 내용채팅 <span class="linkable">#내용채팅채팅</span> 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <!-- 일정 공유 -->
                                    <div class="card border p-3 w-22rem">
                                        <h3 class="my-2">일정 제목</h3>
                                        <h6 class="text-secondary">2024-04-04 16:00</h6>
                                        <div class="my-2">일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 일정 내용 </div>
                                        <div class="linkable border-top text-center p-2">일정 확인 하기</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <!-- 사진 공유 -->
                                    <div class="card w-22rem">
                                        <img src="/img/card.jpg" class="card-img">
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <!-- url공유 -->
                                    <div class="card url-share" data-url="https://www.w3.org/">

                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <div class="d-inline-block mt-2 border p-2" style="background-color: #00000010; border-color:#000!important; border-radius: 0 15px 15px 15px">
                                        채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <div class="d-inline-block mt-2 border p-2" style="background-color: #00000010; border-color:#000!important; border-radius: 0 15px 15px 15px">
                                        채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <div class="d-inline-block mt-2 border p-2" style="background-color: #00000010; border-color:#000!important; border-radius: 0 15px 15px 15px">
                                        채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 d-flex my-3">
                                <div>
                                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle" width="75">
                                </div>
                                <div>
                                    <div>
                                        <span class="">영업팀 팀원 이동일</span><small class="ms-3">2024-04-03 02:25PM</small>
                                    </div>
                                    <div class="d-inline-block mt-2 border p-2" style="background-color: #00000010; border-color:#000!important; border-radius: 0 15px 15px 15px">
                                        채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅채팅 내용채팅 내용채팅
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 채팅 입력 -->

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script>
    window.onload = () =>{
        let tags = [];
        document.querySelectorAll(".url-share").forEach(e => {
            const url = e.getAttribute("data-url");
            fetch(url)
                .then(res => res.text())
                .then(text=> {
                    text = text.substring(text.indexOf("<head>") + 6, text.indexOf("</head>"));
                    console.log(text);
                    let idx = text.indexOf("<meta ");
                })
        })
    }
</script>
</body>

</html>
