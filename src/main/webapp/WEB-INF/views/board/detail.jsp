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
    <div class="pagetitle" style="text-align: center;">
        <h1>
			<c:if test="${board eq 'all'}">전체</c:if>
			<c:if test="${board eq 'represent'}">대표</c:if>
			<c:if test="${board eq 'coc'}">경조사</c:if>
			 게시판
		</h1>
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
                    
<!--                             <p style="line-height: 24px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt; font-size: 10pt;">회원 여러분들께 보다 나은 서비스를 제공해드리기 위해 시스템 점검 작업이 진행될 예정입니다.</span></span></p>
                            <p style="line-height: 24px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-size: 10pt;">&nbsp;</span></p>
                            <p style="line-height: 24px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt; font-size: 10pt;">작업 시간 중에는 다나와 서비스 접속 및 이용이 일시 중단될 수 있니 점검 시간을 반드시 확인하여 주시기 바랍니다.<br><br></span></span></p>
                            <p style="line-height: 28px; font-size: 12pt;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">◈ 일 시&nbsp;:&nbsp;</span><b><span style="font-family: '맑은 고딕'; color: red; letter-spacing: -0.25pt;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;">2024.&nbsp;03.&nbsp;20. (수</span></span></b><b><span style="font-family: '맑은 고딕'; color: red; letter-spacing: -0.25pt;">) 00:00 ~ 04:00 (약&nbsp;4시간)</span></b></p>
                            <p style="line-height: 28px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">◈ 사 유&nbsp;: 서버 장비상태 점검</span></span></p>
                            <p style="line-height: 28px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">◈ 영 향&nbsp;:&nbsp;</span><b><span style="font-family: '맑은 고딕'; color: red; letter-spacing: -0.25pt;">점검시간 중 전체 서비스&nbsp;1~2분&nbsp;중단(총&nbsp;2회)</span></b><b><span style="font-family: '맑은 고딕'; color: red;"></span></b></span></p>
                            <p style="line-height: 28px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">※ 진행 상황에 따라 작업 완료 시간은 변경될 수 있습니다.</span></span></p>
                            <p style="line-height: 28px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-size: 10pt;">&nbsp;</span></p>
                            <p style="line-height: 24px; font-size: 12pt; text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="text-shadow: transparent 0px 0px 0px, rgba(0, 0, 0, 0.68) 0px 0px 0px !important;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">더 나은 서비스를 이용하실 수 있도록 최선의 노력을 다하겠습니다.</span></span></p>
                            <p style="line-height: 24px; font-size: 12pt;"><span style="font-size: 10pt;">&nbsp;</span></p>
                            <p style="line-height: 24px; font-size: 12pt;"><span style="font-family: '맑은 고딕'; letter-spacing: -0.25pt;">감사합니다.&nbsp;</span></p></div> -->
                    </div>
                    <div>      
                    	<c:forEach items="${vo.fileVO}" var="file">
                            <table>
                    		    <tr>
                                    <td> 첨부파일 : <a href="/fileDown?id=${file.id}">${file.originName}</a></td>
                                </tr>
                            </table>
                    	</c:forEach>
                    </div>
                </div>
                 <div class="row justify-content-end p-3">
		            <div class="col-auto">     
		                
		                <sec:authorize access="hasAnyRole('ADMIN', 'PERSONNEL_WRITER')"> 	               	
			               	<a href="./update" id="update" class="btn btn-primary">수정하기</a>
		               	</sec:authorize>
		               	
		               	
		               	 <sec:authorize	access="hasAnyRole('ADMIN')">
			               	<a href="./delete" id="del" class="btn btn-primary">삭제하기</a>
			             </sec:authorize>
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