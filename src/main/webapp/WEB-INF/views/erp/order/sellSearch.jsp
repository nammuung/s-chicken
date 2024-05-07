<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="p-3 border-bottom">
    <b>검색</b>
    <form id="searchForm">
        <div class="mb-3 row">
            <div class="row col">
                <label for="searchName" class="col-3 col-form-label text-nowrap">품명</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchName" name="orderDetails[0].product.name" value="">
                </div>
            </div>
            <div class="row col">
                <label for="searchStatus" class="col-3 col-form-label text-nowrap">상태</label>
                <div class="col-9">
                    <select id="searchStatus" class="form-select" name="status">
                        <option value="7">전체</option>
                        <option value="6">대기 + 진행</option>
                        <option value="1">대기</option>
                        <option value="2">진행</option>
                        <option value="3">완료</option>
                        <option value="4">반려</option>
                    </select>
                </div>
            </div>

            <div class="row col">
                <label for="searchStartDate" class="col-3 col-form-label text-nowrap">발주일</label>
                <div class="col-9">
                    <div class="d-flex">
                        <input type="date" class="form-control" id="searchStartDate" name="startDate" value="2024-01-01">
                        <input type="date" class="form-control" id="searchEndDate" name="endDate">
                    </div>
                </div>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="row col">
                <label for="searchFranchise" class="col-3 col-form-label text-nowrap">가맹점</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchFranchise" name="franchise.name" value="">
                </div>
            </div>
            <div class="row col">
            </div>
            <div class="row col">
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-primary" id="searchButton">검색</button>
        </div>
    </form>
</div>