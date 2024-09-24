<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container-fluid mt-3">
    <form action="/auth/loginProc" method="POST">
        <div class="mb-3 mt-3">
            <label for="username" class="form-label">username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>

        <div class="mb-3 mt-3">
            <label for="password" class="form-label">password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>

        <div class="form-check mb-3">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox" name="remember"> Remember me
            </label>
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${restApi}&redirect_uri=http://localhost:8000/auth/kakao/callback">
            <img height="38" src="/image/kakao_login_button.png">
        </a>
    </form>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


