<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta name="viewport" content="initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
    <!-- css 파일 -->
    <link rel="stylesheet" href="/css/documentDetail.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	

	<c:import url="../../template/head.jsp"></c:import>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</head>

<body>
	<sec:authentication property="principal" var="user"/>
    <div data-login-id="${user.id}"></div>
            <%
    Date date = new Date();
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    
    String strDate = simpleDate.format(date);
    %>
    <div id="date" data-strDate="<%= strDate %>"></div>
    
    <form method="post">
        <span>
            <p style="line-height: 150%; font-family: 맑은 고딕; font-size: 10pt; margin-top: 0px; margin-bottom: 0px;"><span
                    style="font-family: 맑은 고딕; font-size: 10pt;"></span>&nbsp;</p>
            <table
                style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; font-family: malgun gothic,dotum,arial,tahoma; margin-top: 1px; border-collapse: collapse;">
                <!-- Header -->
                <colgroup>
                    <col width="310" />
                    <col width="490" />
                </colgroup>
                
                <tbody>
                    <tr>
                        <td style="padding: 0px !important; border: 0px currentColor; border-image: none;text-align: right;font-weight: bold; vertical-align: middle;"
                        colspan="2" class="">
                            <c:if test="${list[0].temp eq 1}">
                                <div class="mb-2">
                                    <button class="btn btn-primary">인쇄미리보기</button>
                                    <button class="btn btn-primary" id="sangsin" data-temp=1>상신</button>
                                    <button class="btn btn-primary" type="button" id = "cancel">취소</button>
                                </div>
                                    <button type="button" id="callModalButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#call">
		                                불러오기
		                            </button>
                                    <button class="btn btn-primary" id="updateSave" data-temp=1>임시저장</button>
                            </c:if>
                            
                            <c:if test="${list[0].temp eq 0}">
                                <div class="mb-2">
                                    <button class="btn btn-primary">인쇄미리보기</button>
                                    <button class="btn btn-primary" id="sangsin" data-temp=0>상신</button>
                                    <button class="btn btn-primary" type="button" id = "cancel">취소</button>
                                </div>
                                    <button type="button" id="callModalButton" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#call">
		                                불러오기
		                            </button>
                                    <button class="btn btn-primary" id="updateSave" data-temp=0>임시저장</button>
                            </c:if>
                            
                        </td>
                    </tr>

                    <tr>

                        <td style="background: white; padding: 0px !important; border: 0px currentColor; border-image: none; height: 90px; text-align: center; color: black; font-size: 36px; font-weight: bold; vertical-align: middle;"
                            colspan="2" class=""> 급여신청서
                            <div style="text-align: right;">
                                    <button type="button" id="modal_show" class="btn btn-primary">
                                        결재선지정
                                    </button>
                            </div>
                        </td>

                        <!-- <td><button>결제미리보기</button></td> -->

                    </tr>
                    <c:if test="${list[0].id ne null}">
	                    <input type="hidden" value="${list[0].id}" name="documentId">
						<input type="hidden" value="${list[0].id}" name="id">						
					</c:if>
					
					<input type="hidden" value="<%=strDate %>" id="strDate" name="date">
                    <input type="hidden" value="<%=strDate %>" name="WriteDate">
                    <input type="hidden" name="status" value="0">
                    <input type="hidden" name="templateId" value="1">
                    <input type="hidden" id="me" name="writerId" value="${list[0].employeeVO.id}">
                    
                    <tr>
                        <td
                            style="background: white; padding: 0px !important; border: currentColor; border-image: none; width: 506px; text-align: left; color: black; font-size: 12px; font-weight: normal; vertical-align: top;">

                            <table
                                style="border: 1px solid rgb(0, 0, 0); border-image: none; width: 356px; height: 130px; font-family: &quot;malgun gothic&quot;, dotum, arial, tahoma; margin-top: 1px; border-collapse: collapse;">
                                <!-- User -->
                                <colgroup>
                                    <col width="100" />
                                    <col width="210" />
                                </colgroup>

                                <tbody>
                                	<c:if test="${list[0].temp eq 1}">
	                                    <tr>
	                                        <td style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
	                                            문서번호
	                                        </td>
	                                        <td style="background: rgb(255, 255, 255); padding: 0; border: 1px solid black; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
	                                            <div style="width: 100%; text-align: center;">
	                                                ${list[0].id}
	                                            </div>
	                                        </td>
	                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
                                            문서종류
                                        </td>
                                        <td style="background: rgb(255, 255, 255); padding: 0; border: 1px solid black; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                                            <div style="width: 100%; text-align: center;">
                                                ${list[0].templateVO.tempName}
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
                                            신청일
                                        </td>
                                        <td style="background: rgb(255, 255, 255); padding: 0; border: 1px solid black; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                                            <div style="width: 100%; text-align: center;">
                                                ${list[0].writeDate}
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
                                            기안자
                                        </td>
                                        <td style="background: rgb(255, 255, 255); padding: 0; border: 1px solid black; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                                            <div style="width: 100%; text-align: center;">
                                                ${list[0].employeeVO.name}
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
                                            부서
                                        </td>
                                        <td style="background: rgb(255, 255, 255); padding: 0; border: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                                            <div style="width: 100%; text-align: center;">
                                               	${list[0].department.name}
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td
                            style="background: white; padding: 0px !important; border: currentColor; border-image: none; width: 500px; text-align: right; color: black; font-size: 12px; font-weight: normal; vertical-align: top;">

                            <p style="text-align: right; line-height: 150%; font-family: &quot;malgun gothic&quot;, dotum, arial, tahoma; font-size: 9pt; margin-top: 0px; margin-bottom: 0px;">
                                <span class="sign_type1_inline"style="font-family: &quot;malgun gothic&quot;, dotum, arial, tahoma; font-size: 9pt;"
                                    data-is-reception="" data-group-type="type1" data-group-max-count="7" data-group-name="결재선" data-group-seq="0">
                                    <span class="sign_tit_wrap">
                                        <span class="sign_tit"><strong>결재선</strong></span>
                                    </span>
                                        <span class="sign_member_wrap">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank" id="name">${list[0].codeVO.name}</span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date" data-id="${list[0].employeeVO.id}">${list[0].employeeVO.name}</span>
                                            </span>

                                            <span class="sign_wrap">
                                                <span class="sign_name"><strong>ok</strong></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date ">${list[0].writeDate}</span>
                                            </span>
                                        </span>
                                    </span>
                                    
                                    <c:if test="${list[1].writerId eq null}">
                                    	<span class="sign_member_wrap" id="">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank"></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date " id="name"></span>
                                            </span>

                                            <span class="sign_wrap">
                                                <span class="sign_name"><strong></strong></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date " id=""></span>
                                            </span>
                                        </span>
                                    </span>
                                    </c:if>

                                    <c:if test="${list[2].writerId eq null}">
                                    	<span class="sign_member_wrap" id="">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank"></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date " id="name"></span>
                                            </span>

                                            <span class="sign_wrap">
                                                <span class="sign_name"><strong></strong></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date " id=""></span>
                                            </span>
                                        </span>
                                    </span>
                                    </c:if>
                                    	<c:if test="${list[3].writerId eq null}">
                                    		<span class="sign_member_wrap" id="">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank"></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date" id="name"></span>
                                            </span>

                                            <span class="sign_wrap">
                                                <span class="sign_name"><strong></strong></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date"></span>
                                            </span>
                                        </span>
                                    </span>                                    	
                                    	</c:if> 
                                    

                                    <c:forEach items="${list}" begin="1" var="vo">
	                                    <span class="sign_member_wrap haveId" id="">
	                                        <span class="sign_member">                                           
	                                            <span class="sign_rank_wrap">
	                                                <span class="sign_rank">${vo.codeVO.name}</span>
	                                            </span>
	                                            <span class="sign_date_wrap">
	                                                <span class="sign_date" id="name" data-id="${vo.employeeVO.id}" data-level="${vo.codeVO.name}" data-name="${vo.employeeVO.name}">${vo.employeeVO.name}</span>
	                                            </span>
	
	                                            <span class="sign_wrap">
	                                                <span class="sign_name"><strong class="sign"></strong></span>
	                                            </span>
	                                            <span class="sign_date_wrap">
	                                            	<c:forEach items="${vo.approvalVOs}" var="get">
	                                                	<span class="sign_date date">${get.date}</span>
	                                                </c:forEach>
	                                            </span>
	                                        </span>
	                                    </span>
									</c:forEach>
                                </span>
                            </p>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table
                style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; height: 89px; font-family: &quot;malgun gothic&quot;, dotum, arial, tahoma; margin-top: 10px; border-collapse: collapse;">
                <colgroup>
                    <col width="100" />
                    <col width="300" />

                </colgroup>
                <tbody>                   
                   
                    <tr>
                        <td
                            style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
                            제목 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">
                            <input type="text" class="form-control" value ="${list[0].title}" id="title" name="title" style="width: 100%;">
                            
                        </td>
                    </tr>

                    <tr>
                        <td
                            style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
                            대상자 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">
                            
                            <input type="text" readonly class="form-control" value ="${list[0].bonusVO.codeVO.name} ${list[0].bonusVO.employeeVO.name}" data-id="${list[0].bonusVO.employeeId}" id="bonuspeo" style="width: 100%;">
                        </td>
                    </tr>

                    <tr>
                        <td
                            style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
                            금액 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">
                            <input type="text" class="form-control" value ="${list[0].bonusVO.bonus}" id="bonus" name="bonus" style="width: 100%;">
                            
                        </td>
                    </tr>
                </tbody>
            </table>
            <table
                style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; height: 10px; font-family: &quot;malgun gothic&quot;, dotum, arial, tahoma; margin-top: 1px; border-collapse: collapse;">
                <tbody>
                    <tr>
                        <td style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;"
                            colspan="2">
                            신청사유
                        </td>
                    </tr>
                    <tr>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 50px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                            <div id="editor">${list[0].content}</div>
                        </td>
                    </tr>
                    <c:if test="${list[nowCount-1].approvalVOs[0].comment ne null}">
	                     <tr>
	                        <td style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;"
	                            colspan="2">
	                            반려사유
	                        </td>
	                    </tr>
	                    <tr>
	                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 50px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
	                            <div>${list[nowCount-1].approvalVOs[0].comment}</div>
	                        </td>
	                    </tr>
					</c:if>
                </tbody>
            </table>
<!--             <div>
                <label for="file">파일첨부하기</label><br>
            <input type="file" name="attach">
            </div> --> 
        </span>           
   	</form>

        <!-- The Modal -->
        <div class="modal" id="myModal">
            <div class="modal-dialog modal-dialog-scrollable" id="modalContent">
              <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header" >
                  <h4 class="modal-title">결재선지정</h4>
                  <button type="button" class="close" data-bs-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                  <!-- 모달 내용 시작 -->
                  <div class="container-fluid mmdd">
                    <div class="row mmdd">
                      <div class="col-md-5 card mmdd">
                          <div class="card-body">
                              <h5>조직도</h5>
                              <div id="note-message-org-chart"></div>
                          </div>
      
                      </div>
                      
                      <div class="col-md-2 mmdd d-flex justify-content-center align-items-center flex-column">
                          <div class="text-center">
                              <button class="btn btn-primary btn-sm mb-3" id="addbtn"> >> </button>
                          </div>
                          <div class="text-center">
                              <button class="btn btn-primary btn-sm mt-3" id="delbtn"> &lt;&lt; </button>
                          </div>
                      </div>
                      <div class="col-md-5 mmdd">
                        <div id="right-top" class="row ssdd">
                            <div class="card">
                                <div class="card-body">
                                    <div class="h5" style="text-align: left;">결재선 등록</div>
      
                                    <div>
                                      <ul class="list-group" id="approval_List">
      
                                      </ul>
                                    </div>
                                    <div class="position-absolute end-0 bottom-0">
                                        <div class="col-auto">
                                            <div class="mb-2">
                                                <button class="btn btn-outline-primary btn-sm" id="save_btn">결재선 저장</button>
                                                <button class="btn btn-primary btn-sm" id="register">등록하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div id="right-bottom" class="row ssdd">
                            <div class="card">
                                <div class="card-body">
                                    <div class="h5" style="text-align: left;">나의 결재목록</div>
      
                                      <div class="list-item-container">
                                        <ol class="list-group" id="getSave">
                                              <c:forEach items="${title}" var="get">
                                                  <li class="list-group-item" data-title="${get.title}">
                                                      <span style="line-height: 38px;">${get.title}</span><button class="saveDel btn" style="float: right;"><i class="bi bi-trash-fill" data-title="${get.title}" ></i></button>
                                                  </li>
                                              </c:forEach>
                                          </ol>
                                      </div>
      
                                       <div class="text-right mt-2 align-self-end">
                                            <div class="col-auto">
      
                                            </div>
                                        </div>
                                </div>
                        </div>
                        
                      </div>
                    </div>
                  </div>
                  </div>
                  <!-- 모달 내용 끝 -->
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
              </div>
            </div>
          </div>
      
      <!-- 불러오기 모달 -->
      <div class="modal fade" id="call" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- 모달 내용 -->
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">불러오기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                   
                    
                </div>
                <!-- 모달 footer (선택적) -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- 대상자모달 -->
    <div class="modal fade" id="bonusModal" tabindex="-1" aria-labelledby="bonusModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <!-- 모달 내용 -->
                <div class="modal-header">
                    <h5 class="modal-title" id="bonusModalLabel">대상자</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div id="note-message-org-chart2"></div>
                <!-- 모달 footer (선택적) -->
                <div class="modal-footer">
                    <button type="button" id="bonus_btn" class="btn btn-primary">지정하기</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    
                </div>
            </div>
        </div>
    </div>

    	
        <p
            style="line-height: 150%; font-family: &quot;맑은 고딕&quot;; font-size: 10pt; margin-top: 0px; margin-bottom: 0px;">
            &nbsp;</p>
    </span>
   <c:import url="../../template/script.jsp"></c:import>



<script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
<script src="/js/document/temp/temp.js" type="module"></script>
</body>

</html>
