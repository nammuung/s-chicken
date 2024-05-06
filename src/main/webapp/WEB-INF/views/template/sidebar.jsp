<%--
  Created by IntelliJ IDEA.
  User: gimgyeongmo
  Date: 3/31/24
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<aside id="sidebar" class="sidebar">
	<ul class="sidebar-nav" id="sidebar-nav">
    <sec:authorize access="!hasRole('ROLE_FRANCHISE')">
        <li class="nav-item"><a class="nav-link " href="home.jsp"> <i
				class="bi bi-grid"></i> <span>대시보드</span>
		</a></li>
		<!-- End Dashboard Nav -->

        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#components-nav'}">collapsed</c:if>" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-menu-button-wide"></i><span>공지사항</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="components-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#components-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/all/list">
                        <i class="bi bi-circle"></i><span>전체</span>
                    </a>
                </li>
                <li>
                    <a href="/represent/list">
                        <i class="bi bi-circle"></i><span>대표 공지</span>
                    </a>
                </li>
                <li>
                    <a href="/cac/cacList">
                        <i class="bi bi-circle"></i><span>경조사 공지</span>
                    </a>
                </li>
            </ul>
        </li><!-- End Components Nav -->

        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#forms-nav'}">collapsed</c:if>" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-journal-text"></i><span>전자결재</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="forms-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#forms-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/document/list">
                        <i class="bi bi-circle"></i><span>결재 문서</span>
                    </a>
                </li>
                <li>
                    <a href="/document/document">
                        <i class="bi bi-circle"></i><span>상신함</span>
                    </a>
                </li>
                <li>
                    <a href="/document/approvalList">
                        <i class="bi bi-circle"></i><span>결재함</span>
                    </a>
                </li>
                <li>
                    <a href="/document/ref">
                        <i class="bi bi-circle"></i><span>참조함</span>
                    </a>
                </li>
                <li>
                    <a href="/document/tempList">
                        <i class="bi bi-circle"></i><span>임시저장</span>
                    </a>
                </li>
            </ul>
        </li><!-- End Forms Nav -->

        <li class="nav-item"><a class="nav-link collapsed"
                                data-bs-target="#tables-nav" data-bs-toggle="collapse" href="#">
            <i class="bi bi-layout-text-window-reverse"></i><span>시설예약</span><i
                class="bi bi-chevron-down ms-auto"></i>
        </a>
            <ul id="tables-nav" class="nav-content collapse "
                data-bs-parent="#sidebar-nav">
                <li><a href="tables-general.html"> <i class="bi bi-circle"></i><span>시설목록</span>
                </a></li>
                <li><a href="tables-data.html"> <i class="bi bi-circle"></i><span>
					</span>
                </a></li>
            </ul></li>
        <!-- End Tables Nav -->
        <sec:authorize access="hasAnyRole('ADMIN','PERSONNEL_WRITER')">
        <li class="nav-item"><a class="nav-link collapsed"
                                data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
            <i class="bi bi-bar-chart"></i><span>인사관리</span><i
                class="bi bi-chevron-down ms-auto"></i>
        </a>
            <ul id="charts-nav" class="nav-content collapse "
                data-bs-parent="#sidebar-nav">
                <li><a href="/employee/list"> <i class="bi bi-circle"></i><span>직원목록</span>
                </a></li>
                <li><a href="/employee/isuserList"> <i class="bi bi-circle"></i><span>퇴사자목록</span></a></li>
                
                <li><a href="/employee/role"> <i class="bi bi-circle"></i><span>권한</span></a></li>
                <li><a href="/employee/join"> <i class="bi bi-circle"></i><span>직원등록</span></a></li>
            </ul></li>
        <!-- End Charts Nav -->
        </sec:authorize>
        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#icons-nav'}">collapsed</c:if>" data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-houses"></i><span>가맹점 관리</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="icons-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#icons-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/franchise/join">
                        <i class="bi bi-circle"></i><span>가맹점 가입</span>
                    </a>
                </li>
                <li>
                    <a href="/franchise/inquiry">
                        <i class="bi bi-circle"></i><span>가맹점 조회</span>
                    </a>
                </li>
                <li>
                    <a href="/franchise/qna/list">
                        <i class="bi bi-circle"></i><span>가맹점 QnA</span>
                    </a>
                </li>
                <li>
                    <a href="/franchise/faq/list">
                        <i class="bi bi-circle"></i><span>가맹점 F.A.Q</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#erp-nav'}">collapsed</c:if>" data-bs-target="#sell-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-receipt"></i><span>구매 및 판매</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="sell-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#sell-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/order">
                        <i class="bi bi-circle"></i><span>구매</span>
                    </a>
                </li>
                <li>
                    <a href="/order/sell">
                        <i class="bi bi-circle"></i><span>판매</span>
                    </a>
                </li>
                <li>
                    <a href="/sales">
                        <i class="bi bi-circle"></i><span>매출 관리</span>
                    </a>
                </li>
            </ul>
        </li><!-- End Icons Nav -->
        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#erp-nav'}">collapsed</c:if>" data-bs-target="#warehouse-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-arrow-down-up"></i><span>입·출고</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="warehouse-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#warehouse-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/order/list">
                        <i class="bi bi-circle"></i><span>입고</span>
                    </a>
                </li>
                <li>
                    <a href="/order/release">
                        <i class="bi bi-circle"></i><span>출고</span>
                    </a>
                </li>
                <li>
                    <a href="/products/stock">
                        <i class="bi bi-circle"></i><span>재고 관리</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link <c:if test="${cookie.navToggle.value ne '#erp-nav'}">collapsed</c:if>" data-bs-target="#item-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-box-seam"></i><span>상품관리</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="item-nav" class="nav-content collapse <c:if test="${cookie.navToggle.value eq '#item-nav'}">show</c:if>" data-bs-parent="#sidebar-nav">
                <li>
                    <a href="/items">
                        <i class="bi bi-circle"></i><span>상품 관리</span>
                    </a>
                </li>
                <li>
                    <a href="/products">
                        <i class="bi bi-circle"></i><span>품목 관리</span>
                    </a>
                </li>

                <li>
                    <a href="/supplier">
                        <i class="bi bi-circle"></i><span>납품처 관리</span>
                    </a>
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/insight">
                <i class="bi bi-person-raised-hand"></i><span>인사이트</span>
            </a>
        </li>


		<li class="nav-heading">Pages</li>

		<li class="nav-item"><a class="nav-link collapsed"
			href="../employee/profile?id=${id}"> <i class="bi bi-person"></i>
				<span>마이페이지</span>
		</a></li>
		<!-- End Profile Page Nav -->

    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_FRANCHISE')">
		<!-- End Blank Page Nav -->
        <li class="nav-heading">가맹점</li>
        <li class="nav-item">
            <a class="nav-link " href="/franchise/home">
                <i class="bi bi-grid"></i><span>대시보드</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/order">
                <i class="bi bi-box-seam"></i><span>발주서작성</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/order/list">
                <i class="bi bi-box-seam"></i><span>발주내역</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/sales">
                <i class="bi bi-bar-chart"></i><span>매출</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/qna/list">
                <i class="bi bi-person-raised-hand"></i><span>QnA</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/faq/list">
                <i class="bi bi-question-circle"></i><span>F.A.Q</span>
            </a>
        </li>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal" var="user"/>
            <li class="nav-item">
            <a class="nav-link collapsed" href="/franchise/detail?id=${user.id}">
                <i class="bi bi-person"></i><span>마이페이지</span>
            </a>
        </li>
        </sec:authorize>

    </sec:authorize>
	</ul>
</aside>



