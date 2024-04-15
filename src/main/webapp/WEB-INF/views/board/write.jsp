<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
    <style>
        #editor {
            height: 50vh;
        }
    </style>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp"/>
<main id="main" class="main">

<!-- 오늘날짜 가져오기 -->
<%
Date date = new Date();
SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
String strDate = simpleDate.format(date);
%>
    <div class="pagetitle">
        <h1>공지사항</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body mt-3">
                        <form id="frm" method="post" action="write" enctype="multipart/form-data">

                            <div class="mb-3">
                                <label for="sort" class="form-label">종류</label>
                                <select class="form-select" style="width: 200px;"name="sort" id=sort>
                                    <option value="0">경조사 게시</option>
                                    <option value="1">대표 게시</option>
                                </select>
                            </div>

                            <input type="hidden" name="writerId" value="123">
                            <input type="hidden" name="isDelete" value="0">
                            <input type="hidden" name="writeDate" value="<%=strDate %>">

                            <div class="mb-3">
                                <label for="title" class="form-label"><b>제목</b></label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요.">
                            </div>
                            <textarea name="content" id="editor">

					        </textarea>
                            <div class="form-check form-switch mt-3 mb-3">
                                <label for="important" class="form-label">중요 공지</label>
                                <input type="checkbox" class="form-check-input" id="important" name="important">
                                <input type="hidden" name="_important" value="on">
                            </div>
                            <div>
                            	<input type="file" name="attach">
                            </div>


                            <button type="submit" class="btn btn-primary float-end" id="btn">게시하기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>

<script src="/js/board/write.js"></script>
</body>

</html>
