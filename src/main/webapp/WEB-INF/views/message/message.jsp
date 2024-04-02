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
        <h1>쪽지함</h1>
    </div>

    <div class="card pb-4">
        <div class="row mt-5">
            <div class="col-2 border-end">
                <button class="btn btn-primary w-100 mb-2">쪽지보내기</button>
                <button class="btn btn-outline-primary w-100 border-0 mb-2">받은 쪽지함</button>
                <button class="btn btn-outline-primary w-100 border-0 mb-2">쪽지 보관함</button>
                <button class="btn btn-outline-primary w-100 border-0 mb-2">보낸 쪽지함</button>
                <button class="btn btn-outline-primary w-100 border-0 mb-2">휴지통</button>
            </div>
            <div class="col-10 p-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th>선택</th>
                            <th>보낸 사람</th>
                            <th>내용</th>
                            <th>첨부파일</th>
                            <th>수신날짜</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="checkbox" class="form-check">
                            </td>
                            <td class="linkable">식품개발팀 팀장 이동일</td>
                            <td class="linkable">3분기 실적보고서 아직 멀었나요? 내일까지 마무리 부..</td>
                            <td></td>
                            <td>03.25 16:56</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

<!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>
