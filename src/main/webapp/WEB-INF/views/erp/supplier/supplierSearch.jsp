<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="p-3 border-bottom" id="supplierSearchContainer">
    <b>검색</b>
    <form id="supplierSearchForm">
        <div class="mb-3 row">
            <div class="row col">
                <label for="searchName" class="col-3 col-form-label text-nowrap">거래처</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchName" name="name" value="">
                </div>
            </div>
            <div class="row col">
                <label for="searchManager" class="col-3 col-form-label text-nowrap">등록자</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="searchManager" name="manager.name" value="">
                </div>
            </div>
            <div class="row col">

            </div>
        </div>
        <div class="mb-3 row">
            <div class="row col">

            </div>
            <div class="row col">

            </div>
            <div class="row col">

            </div>
        </div>

        <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-primary" id="supplierSearchButton">검색</button>
        </div>
    </form>
</div>