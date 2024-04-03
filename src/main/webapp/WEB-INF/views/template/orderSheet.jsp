<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <td>202303241</td>
                </tr>
                <tr>
                    <th>수신</th>
                    <td>S치킨/김경모</td>
                </tr>
                <tr>
                    <th>TEL</th>
                    <td>031-123-1234</td>
                </tr>
                <tr>
                    <th>FAX</th>
                    <td>02-1234-1234</td>
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
                    <td>S치킨 한라점/김경모</td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>서울시 구로구</td>
                </tr>
                <tr>
                    <th>담당/연락처</th>
                    <td>홍길동/010-1234-1234</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="border border-black border-4 p-2 mt-3 w-100 d-flex justify-content-between">
        <span><b>금액 : 사십만이천오백원 정</b></span>
        <span><b>( 402,500원 ) / VAT포함</b></span>
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
            <tr>
                <td>1</td>
                <td>C-101</td>
                <td>날개[9호]</td>
                <td>10</td>
                <td>KG</td>
                <td>36,000</td>
                <td>36,00</td>
                <td>36,00</td>
            </tr>
        </table>
    </div>
</div>

<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
</body>

</html>