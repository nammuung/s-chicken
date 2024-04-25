<%--
  Created by IntelliJ IDEA.
  User: gimgyeongmo
  Date: 3/31/24
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
    <div data-logined-id="${principal.id}"></div>
</sec:authorize>
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <a href="/" class="logo d-flex align-items-center">
            <img src="/img/logo.png" alt="">
            <span class="d-none d-lg-block"> Chicken </span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="검색" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <li class="nav-item d-block d-lg-none">
                <a class="nav-link nav-icon search-bar-toggle " href="#">
                    <i class="bi bi-search"></i>
                </a>
            </li><!-- End Search Icon-->

            <!-- 알림 드롭다운 -->
            <li class="nav-item dropdown">
                <a id="notification-icon" class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                    <i class="bi bi-bell"></i>
                    <span id="notification-badge" class="badge bg-danger badge-number d-none" style="padding: 5px;"> </span>
                </a><!-- End Notification Icon -->

                <div class="dropdown-menu dropdown-menu-end dropdown-menu-arrow w-22rem">
                    <ul id="schicken-notification-list" class="notifications">
                        <div>
                            <li data-no-notification class="notification-item">
                                <div>
                                    <h4></h4>
                                    <p>모든 알림을 읽었습니다.</p>
                                    <p></p>
                                </div>
                            </li>
                            <li data-no-notification><hr class="dropdown-divider"></li>
                        </div>
                    </ul>
                    <div id="notification-list-footer" class="dropdown-footer border-top border-1 p-0">
                        <a href="/notificationPage" class="d-block w-100 p-2">
                            모든 알림 보기
                        </a>
                    </div>

                </div><!-- End Notification Dropdown Items -->

            </li><!-- End Notification Nav -->

            <!-- 채팅 드롭다운 -->
            <li class="nav-item dropdown">
                <a id="chatting-popup-btn" class="nav-link nav-icon" href="#">
                    <i class="bi bi-chat-left-text"></i>
                </a><!-- End Messages Icon -->
            </li><!-- End Messages Nav -->

            <!-- 쪽지 창 -->
            <li class="nav-item dropdown">
                <a id="note-message-nav"  class="nav-link nav-icon" href="#">
                    <i class="bi bi-envelope-paper"></i>
                </a><!-- End Messages Icon -->
            </li><!-- End NoteMessages Nav -->

            <li class="nav-item dropdown pe-3">

                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <img src="/img/profile-img.jpg" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>
                </a><!-- End Profile Iamge Icon -->

                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6>Kevin Anderson</h6>
                        <span>Web Designer</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                            <i class="bi bi-person"></i>
                            <span>My Profile</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                            <i class="bi bi-gear"></i>
                            <span>Account Settings</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
                            <i class="bi bi-question-circle"></i>
                            <span>Need Help?</span>
                        </a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>

                    <li>
                        <a class="dropdown-item d-flex align-items-center" href="/employee/logout">
                            <i class="bi bi-box-arrow-right"></i>
                            <span>Sign Out</span>
                        </a>
                    </li>

                </ul><!-- End Profile Dropdown Items -->
            </li><!-- End Profile Nav -->

        </ul>
    </nav><!-- End Icons Navigation -->

</header><!-- End Header -->
