<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../template/head.jsp"/>
    <script src="https://cdn.ckeditor.com/ckeditor5/41.2.1/classic/ckeditor.js"></script>
    <style>
        #editor {
            height: 50vh;
        }
    </style>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <h1>FAQ</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body mt-3">
                        <form class="">
                            <div class="mb-3">
                                <label for="title" class="form-label"><b>제목</b></label>
                                <input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요.">
                            </div>
                            <div id="editor"></div>
                            <div class="form-check form-switch mt-3">
                                <label for="important" class="form-label">중요 FAQ 등록하기</label>
                                <input type="checkbox" class="form-check-input" id="important">
                            </div>

                            <button class="btn btn-primary float-end mt-3">게시하기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../template/script.jsp"/>
<script>
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            editor.editing.view.change(writer => {
                writer.setStyle('height', '50vh', editor.editing.view.document.getRoot());
            });
        })
        .catch(error => {
            console.error(error);
        });

</script>
</body>

</html>