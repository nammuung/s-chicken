<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="p-3 border-bottom" id="productSearchContainer">
    <b>검색</b>
    <form id="productSearchForm">
        <div class="mb-3 row">
            <div class="row col">
                <label for="searchId" class="col-3 col-form-label text-nowrap">품번</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchId" name="id" value="">
                </div>
            </div>
            <div class="row col">
                <label for="searchName" class="col-3 col-form-label text-nowrap">품목</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchName" name="name" value="">
                </div>
            </div>
            <div class="row col">
                <label for="searchStandard" class="col-3 col-form-label text-nowrap">규격</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchStandard" name="standard" value="">
                </div>
            </div>
        </div>
        <div class="mb-3 row">
            <div class="row col">
                <label for="searchCategory" class="col-3 col-form-label text-nowrap">카테고리</label>
                <div class="col-9">
                    <select type="text" class="form-control form-select" id="searchCategory" name="category.id">
                        <option value="">전체</option>
                        <c:forEach items="${category}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row col">

            </div>
            <div class="row col">

            </div>
        </div>

        <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-primary" id="productSearchButton">검색</button>
        </div>
    </form>
</div>