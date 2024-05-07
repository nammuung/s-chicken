<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>S치킨-그룹웨어</title>
    <c:import url="../../template/head.jsp"/>
    <style>
        #editor {
            height: 50vh;
        }
    </style>
</head>

<body>
<!-- ======= Header ======= -->
<c:import url="../../template/header.jsp"/>
<!-- ======= Sidebar ======= -->
<c:import url="../../template/sidebar.jsp"/>
<main id="main" class="main">
    <div class="pagetitle">
        <a href="./list">
            <h1>가맹점 QnA</h1>
        </a>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body mt-3">
                        <form id="addForm" method="POST">
                            <div class="mb-3">
                                <label for="title" class="form-label"><b>제목</b></label>
                                <input name="title" type="text" class="form-control" id="title" placeholder="제목을 입력해주세요.">
                            </div>
                            <input id="content" type="hidden" name="content">
                            <div id="editor"></div>


                            <button id="submitButton" type="button" class="btn btn-primary float-end mt-3">게시하기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main><!-- End #main -->
<!-- ======= Footer ======= -->
<c:import url="../../template/footer.jsp"/>
<!-- ======= Script ======= -->
<c:import url="../../template/script.jsp"/>
<script>
    let editor;
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(newEditor => {
            editor = newEditor;
            editor.editing.view.change(writer => {
                writer.setStyle('height', '50vh', editor.editing.view.document.getRoot());
            });
        })
        .catch(error => {
            console.error(error);
        });

    document.getElementById("submitButton").addEventListener("click", function (event) {
        const contentInput = document.getElementById("content");
        contentInput.value = editor.getData();
        console.log(editor.getData());
        document.getElementById("addForm").submit()
    })

</script>
</body>

</html>
