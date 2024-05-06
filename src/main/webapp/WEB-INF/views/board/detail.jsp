<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
<sec:authentication property="principal" var="user"/>
<div data-login-id="${user.id}"></div>
    <div class="pagetitle">
        <h1>${board} 공지</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="d-flex justify-content-between p-3 border-bottom">
                        <div>
                            <span class="me-3">${vo.employeeVO.name}</span>
                            <b>${vo.title}</b>
                        </div>
                        <span class="text-muted">${vo.writeDate}</span>
                    </div>
                    <div class="card-body pt-3">
                    ${vo.content}
                    </div>

                    <div class="d-flex justify-content-between p-3">
                        <div>
                            <table>
                                <tr>
                                    <td> 첨부파일 :
                                        <c:forEach items="${vo.fileVO}" var="file">
                                        <a href="/fileDown?id=${file.id}">${file.originName}</a>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div>
                            <sec:authorize	access="hasAnyRole('ADMIN')">
                                <a href="./delete" id="del" class="btn btn-outline-danger">삭제</a>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')">
                                <a href="./update" id="update" class="btn btn-primary">수정</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
		        <form id="frm" action="./update" method="get">
		        	<input type="text" id="id" name="id" value="${vo.id}" hidden>
		        	<input type="text" id="title" name="title" value="${vo.title}" hidden>
		        </form>
            </div>
            <div class="col-12">
                <div class="card">
                    <div class="card-body mt-3">
                        <div class="mb-3">
                            <a href="/${board}/list" class="link-body-emphasis">
                                <i class="bi bi-list toggle-sidebar-btn button"></i>
                                <span>목록</span>
                            </a>
                        </div>

	                        <div class="mb-2">
	                        	<c:if test="${not empty move[0].id}">
		                            <i class="bi bi-caret-up-fill toggle-sidebar-btn button"></i>
		                            <span class="me-3">이전</span>
		                            <a href="/${board}/detail?id=${move[0].id}" class="link-body-emphasis">
		                            ${move[0].title}	                            
	                            	</a>
	                            </c:if>
	                        </div>

                        <div>
	                        <c:if test="${not empty next[0].id}">
	                            <i class="bi bi-caret-down-fill toggle-sidebar-btn button"></i>
	                            <span class="me-3">다음</span>
	                            <a href="/${board}/detail?id=${next[0].id}" class="link-body-emphasis">
	                            ${next[0].title}                             
                            	</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        <div class="col-12">
            <div class="card">
                <div class="card-header">

                    	<span class="card-title">댓글</span>

                </div>
                
                <div id="reply-list-div" class="card-body mt-3">	
                
                    
                
                					
                                  
                </div>
                <div class="card-footer">
                	<h3 class="comment-reply-title"><span>Leave a comment</span></h3>
						
							<div class="row">
								<input type="hidden" name="boardNum" value="${dto.boardNum}">
								
								<div class="col-12">
									<div class="form-box form-group">
										<textarea id="replyText" name="replyText" class="form-control form-control-custom" placeholder="댓글을 남겨보세요."></textarea>
									</div>
								</div>
								
								<div class="row justify-content-end p-3">
									<div class="button" id="add_btn">
										<button type="button" id="replyAdd" class="btn btn-primary" data-id="${vo.id}">댓글달기</button>
									</div>
								</div>
								
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
<script src="../js/board/detail.js"></script>
</body>

</html>