<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>채팅</title>
    <c:import url="../template/head.jsp" />
</head>
<style>
    body, html {
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-rendering: optimizeLegibility;
        text-align: center;
        height: 100%;
        overflow: hidden;
        margin: inherit;
    }

    nav {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        z-index: 1;
        background: #370926;
    }
    nav ul li {
        display: inline-block;
    }
    nav ul li a {
        color: rgba(255, 255, 255, 0.45);
        font-size: 0.875rem;
        display: block;
        text-decoration: none;
        padding: 6px 15px;
    }
    nav ul li a:hover {
        color: #fff;
    }

    section {
        height: 100%;
        width: 100%;
        display: table;
        pointer-events: none;
        z-index: 0;
        -webkit-transition: transform 0.45s cubic-bezier(0, 0, 0.21, 1);
        transition: transform 0.45s cubic-bezier(0, 0, 0.21, 1);
    }
    section h1 {
        display: table-cell;
        vertical-align: middle;
        text-align: center;
        color: #fff;
        font-weight: lighter;
    }

    #one {
        background: #42113C;
    }

    #two {
        background: #618B25;
    }

    #three {
        background: #6BD425;
    }

    a[id="one"]:target ~ #one {
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
    }

    a[id="two"]:target ~ #two {
        -webkit-transform: translate3d(0, -100%, 0);
        transform: translate3d(0, -100%, 0);
    }

    a[id="three"]:target ~ #three {
        -webkit-transform: translate3d(0, -200%, 0);
        transform: translate3d(0, -200%, 0);
    }
</style>
<body>

<a id="one">Section 1</a>
<a id="two">Section 2</a>
<a id="three">Section 3</a>

<nav>
    <div class="container">
        <ul>
            <li><a href="#one">Section 1</a></li>
            <li><a href="#two">Section 2</a></li>
            <li><a href="#three">Section 3</a></li>
        </ul>
    </div>
</nav>

<section id="one">
    <h1>Section 1</h1>
</section>

<section id="two">
    <h1>Section 2</h1>
</section>

<section id="three">
    <h1>Section 3</h1>
</section>

</body>
<c:import url="../template/script.jsp"/>
</html>
