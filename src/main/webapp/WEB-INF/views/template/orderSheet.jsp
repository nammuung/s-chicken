<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
    <style>
        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
        }

        .page {
            width: 21cm;
            min-height: 29.7cm;
            padding: 1.5cm 1.5cm 2cm 1.5cm;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        @page {
            size: A4;
            margin: 0;
        }

        @media print {
            .page {
                margin: 0;
                border: initial;
                border-radius: initial;
                width: initial;
                min-height: initial;
                box-shadow: initial;
                background: initial;
                page-break-after: always;
            }
        }
        .between {
            display: flex;
            width: 100%;
            justify-content: space-between;
        }
        table {
            border: 2px solid;
            border-collapse: collapse;
        }
        th, td{
            border:1px solid;
            padding: 5px;
        }
    </style>
</head>

<body>

<div class="page">
    <h1>발주서</h1>
    <div class="between mt-3">
        <div class="receiver">
            <table>
                <tbody>
                <tr>
                    <th>일련번호</th>
                    <td>${order.id}-${order.supplier.id}</td>
                </tr>
                <tr>
                    <th>수신</th>
                    <td>${order.supplier.name}/${order.supplier.ownerName}</td>
                </tr>
                <tr>
                    <th>TEL</th>
                    <td>${order.supplier.contactNumber}</td>
                </tr>
                <tr>
                    <th>납기일자</th>
                    <td>2023/03/30</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="sender">
            <table>
                <tbody>
                <tr>
                    <th>사업자등록번호</th>
                    <td>101-12-12345</td>
                </tr>
                <tr>
                    <th>회사명/대표</th>
                    <td>S치킨/김경모</td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>서울시 구로구 312 1201호</td>
                </tr>
                <tr>
                    <th>담당/연락처</th>
                    <td>${order.employee.name}/${order.employee.phoneNumber}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <c:set var="total" value="0" />
    <c:forEach items="${order.orderItems}" var="item">
        <c:set var="total" value="${total + item.quantity*item.price}" />
    </c:forEach>
    <jsp:useBean id="util" class="com.groups.schicken.common.util.NumberToKorean"/>
    <c:set var="koreanNumber" value="${util.convert(total)}"/>


    <div class="border border-black border-4 p-2 mt-3 w-100 d-flex justify-content-between">
        <span><b>금액 : ${koreanNumber} 정</b></span>
        <span><b>( <fmt:formatNumber value="${total}" pattern="#,###"/>원 ) / VAT포함</b></span>
    </div>
    <div class="w-100 mt-3">
        <table class="w-100 text-center">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>상품코드</th>
                    <th>상품명[규격]</th>
                    <th>수량</th>
                    <th>단위</th>
                    <th>단가</th>
                    <th>공급가액</th>
                    <th>부가세</th>
                </tr>
            </thead>
            <tbody class="">
            <c:forEach items="${order.orderItems}" var="item" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${item.id}</td>
                    <td>${item.item.product.name}[${item.item.product.standard}]</td>
                    <td>${item.quantity}</td>
                    <td>${item.item.product.unit.name}</td>
                    <td><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</td>
                    <td><fmt:formatNumber value="${item.price * item.quantity}" pattern="#,###"/>원</td>
                    <td><fmt:formatNumber value="${item.price / 10}" pattern="#,###"/>원</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>