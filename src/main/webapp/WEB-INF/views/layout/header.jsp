<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- 스프링 시큐리티 설정, isAuthenticated로 시큐리티 인증/인가를 받았는지 체크 --%>
<sec:authorize access="isAuthenticated()">
    <%-- 스프링 시큐리티에 저장된 속성 principal를 해당 jsp의 principal 변수로 바인딩 --%>
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>블로그!</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <c:choose>
                <c:when test="${empty principal}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/loginForm">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/joinForm">회원가입</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/board/saveForm">글쓰기</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/user/updateForm">회원정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">로그아웃</a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>