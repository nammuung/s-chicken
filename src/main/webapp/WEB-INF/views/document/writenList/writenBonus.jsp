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
    
    <form action="post">
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
                      
                        
                                                        
                        </td>
                    </tr>

                    <tr>

                        <td style="background: white; padding: 0px !important; border: 0px currentColor; border-image: none; height: 90px; text-align: center; color: black; font-size: 36px; font-weight: bold; vertical-align: middle;"
                            colspan="2" class=""> 상여금신청서
                            <div style="text-align: right;">

                               
                                <button id="approval_btn" type="button" class="btn btn-primary">결재하기</button>
                        		

                            </div>
                        </td>

                        <!-- <td><button>결제미리보기</button></td> -->

                    </tr>
					<input type="hidden" value="${list[0].id}" name="documentId">
					<input type="hidden" value="<%=strDate %>" name="date">
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
                                                <span class="sign_rank">${list[0].level}</span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date ">${list[0].employeeVO.name}</span>
                                            </span>

                                            <span class="sign_wrap">
                                                <span class="sign_name"><strong>ok</strong></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date ">${list[0].writeDate}</span>
                                            </span>
                                        </span>
                                    </span>
                                  
                                    <c:if test="${list[2].id eq null}">
                                    	<span class="sign_member_wrap" id="">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank"></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date "></span>
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
                                    	<c:if test="${list[3].id eq null}">
                                    		<span class="sign_member_wrap" id="">                                        
                                        <span class="sign_member">                                        
                                            <span class="sign_rank_wrap">
                                                <span class="sign_rank"></span>
                                            </span>
                                            <span class="sign_date_wrap">
                                                <span class="sign_date"></span>
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
	                                                <span class="sign_rank">${vo.level}</span>
	                                            </span>
	                                            <span class="sign_date_wrap">
	                                                <span class="sign_date" data-id="${vo.employeeVO.id}">${vo.employeeVO.name}</span>
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
                            대상자 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">

                            값넣기
                        </td>
                    </tr>

                    <tr>
                        <td
                            style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
                            참조 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">

                            값넣기
                        </td>
                    </tr>

                    <tr>
                        <td
                            style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
                            제목 </td>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
                            colspan="5">

                            ${list[0].title}
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
                            상여사유
                        </td>
                    </tr>
                    <tr>
                        <td style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 50px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
                            <div>${list[0].content }</div>
                        </td>
                    </tr>

                </tbody>
            </table>
   	</form>
            <div>
                <label for="file">파일첨부하기</label><br>
            <input type="file" name="attach">
            </div>
    
        <p
            style="line-height: 150%; font-family: &quot;맑은 고딕&quot;; font-size: 10pt; margin-top: 0px; margin-bottom: 0px;">
            &nbsp;</p>
    </span>
   <c:import url="../../template/script.jsp"></c:import>



<script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>
<script src="/js/document/writen/approval.js"></script>
</body>

</html>
