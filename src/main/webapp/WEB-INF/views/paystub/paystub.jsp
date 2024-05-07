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
    <section class="section">
        <div class="row d-flex justify-content-center">
            <div class="col-4">
                <div class="pagetitle mb-3">
                    <h1>급여명세서</h1>
                </div>
                <form class="mb-3">
                    <label for="paystub-year-month">월 선택</label>
                    <input class="form-control" type="month" id="paystub-year-month" value="${paystub.yearMonth}"/>
                </form>
                <div class="card">
                    <div class="card-body">
                        <c:if test="${paystub.basePay ne null}">
                            <div class="row py-3">
                                <h2 class="text-center">${paystub.yearMonth}</h2>
                                <h1 class="text-center">급여명세서 <c:if test="${not paystub.payed}">(예정)</c:if></h1>
                                <hr>
                                <p class="col-5 text-end fs-4">기본급 : </p>
                                <p class="col-7 fs-4">${paystub.basePayByString} 원</p>
                                <c:if test="${paystub.bonusSum ne 0}">
                                    <p class="col-5 text-end fs-4">상여금 : </p>
                                    <p class="col-7 fs-4">${paystub.bonusSumByString} 원</p>
                                </c:if>
                                <hr>
                                <div class="col-5 text-end fs-4">실지급액 : </div>
                                <div class="col-6 fs-4">${paystub.sum} 원</div>
                                <c:if test="${paystub.bonusSum ne 0}">
                                    <div class="accordion mt-3" id="accordionExample">
                                        <div class="accordion-item">
                                            <h2 class="accordion-header">
                                                <button
                                                        class="accordion-button collapsed"
                                                        type="button"
                                                        data-bs-toggle="collapse"
                                                        data-bs-target="#collapseOne"
                                                        aria-expanded="false"
                                                        aria-controls="collapseOne">
                                                    상여금 문서
                                                </button>
                                            </h2>
                                        </div>
                                    </div>
                                </c:if>
                                <div id="collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                                    <div class="accordion-body" style="padding: 0">
                                        <ul class="list-group">
                                            <c:forEach items="${paystub.bonusReason}" var="r">
                                                <li data-document-id="${r.id}" class="list-group-item linkable text-black hovercursor">${r.title}</li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${paystub.basePay eq null or paystub.basePay eq 0}">
                            <div>기록이 없습니다</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </section>
    <!-- ======= 내용을 넣어주세요. ======= -->
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script src="/js/paystub/paystub.js"></script>
</body>

</html>
