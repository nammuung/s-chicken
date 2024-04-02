<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>S치킨-그룹웨어</title>
<c:import url="../template/head.jsp" />

</head>
<style>
.tagify {
	width: 100%;
	max-width: 700px;
}

.customSuggestionsList>div {
	max-height: 200px;
	border: 2px solid #7749F8;
	min-height: 199px;
	overflow: auto;
}
</style>
<body>
	<!-- 소스 다운 -->
	<script src="https://unpkg.com/@yaireo/tagify"></script>
	<!-- 폴리필 (구버젼 브라우저 지원) -->
	<script
		src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
	<link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css"
		rel="stylesheet" type="text/css" />


	<!-- ======= Header ======= -->
	<c:import url="../template/header.jsp" />
	<!-- ======= Sidebar ======= -->
	<c:import url="../template/sidebar.jsp" />
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>권한관리</h1>
		</div>
		<section class="section">
			<div class="row justify-content-start p-3">
				<div class="col-auto">
					<form class="search-form d-flex align-items-center " method="POST"
						action="#">
						<label> <select class="form-select w-auto me-1">
								<option value="0">이름</option>
								<option value="1">부서</option>
								<option value="2">직급</option>
						</select>
						</label> <input type="text" name="query" placeholder="검색"
							title="Enter search keyword">
						<button type="submit" title="Search">
							<i class="bi bi-search"></i>
						</button>
					</form>
				</div>
			</div>
			<div class="row justify-content-between">
				<div class="col-5">
					<div class="card">
						<input name='tags' placeholder='직원을 클릭하세요.'>
					</div>
				</div>

				<div
					class="col-1 d-flex flex-column justify-content-center align-items-center">
					<!-- 버튼을 세로로 중앙에 배치 -->
					<button class="btn btn-primary mb-2">>></button>
					<button class="btn btn-primary"><<</button>
				</div>

				<div class="col-5">
					<div class="card">
						<input name='tags2' placeholder='직원을 클릭하세요.'>
					</div>
				</div>
			</div>

			<div class="row justify-content-end">
				<div class="col-auto">
					<button class="btn btn-primary mb-2">확인</button>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->
	<!-- ======= Footer ======= -->
	<c:import url="../template/footer.jsp" />
	<!-- ======= Script ======= -->
	<c:import url="../template/script.jsp" />
</body>
<script>
var whitelist = ["대리_남명균","대리_이동일","대리_김경모","대리_김범서","부장_남명균","부장_이동일","부장_김경모","부장_김범서"]

var input = document.querySelector('input[name=tags]');

    // init Tagify script on the above inputs
var tagify = new Tagify(input, {
        whitelist: whitelist,
        dropdown: {
            position: "manual",
            maxItems: Infinity,
            enabled: 0,
            classname: "customSuggestionsList"
        },
        enforceWhitelist: true
    })

    tagify.on("dropdown:show", onSuggestionsListUpdate)
          .on("dropdown:hide", onSuggestionsListHide)
          .on('dropdown:scroll', onDropdownScroll)

    renderSuggestionsList()

    // ES2015 argument destructuring
    function onSuggestionsListUpdate({ detail:suggestionsElm }){
        console.log(  suggestionsElm  )
    }

    function onSuggestionsListHide(){
        console.log("hide dropdown")
    }

    function onDropdownScroll(e){
        console.log(e.detail)
      }

    // https://developer.mozilla.org/en-US/docs/Web/API/Element/insertAdjacentElement
    function renderSuggestionsList(){
        tagify.dropdown.show.call(tagify) // load the list
        tagify.DOM.scope.parentNode.appendChild(tagify.DOM.dropdown)
    }
        
    var input = document.querySelector('input[name=tags2]');

 // init Tagify script on the above inputs
 var tagify = new Tagify(input, {
     whitelist: whitelist,
     dropdown: {
         position: "manual",
         maxItems: Infinity,
         enabled: 0,
         classname: "customSuggestionsList"
     },
     enforceWhitelist: true
 })

 tagify.on("dropdown:show", onSuggestionsListUpdate)
       .on("dropdown:hide", onSuggestionsListHide)
       .on('dropdown:scroll', onDropdownScroll)

 renderSuggestionsList()

 // ES2015 argument destructuring
 function onSuggestionsListUpdate({ detail:suggestionsElm }){
     console.log(  suggestionsElm  )
 }

 function onSuggestionsListHide(){
     console.log("hide dropdown")
 }

 function onDropdownScroll(e){
     console.log(e.detail)
 }

 // https://developer.mozilla.org/en-US/docs/Web/API/Element/insertAdjacentElement
 function renderSuggestionsList(){
     tagify.dropdown.show.call(tagify) // load the list
     tagify.DOM.scope.parentNode.appendChild(tagify.DOM.dropdown)
 }
      
        
</script>
</html>