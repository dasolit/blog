<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container-fluid mt-3">
    <form>
        <div class="mb-3 mt-3">
            <label for="username" class="form-label">username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
        </div>

        <div class="mb-3 mt-3">
            <label for="password" class="form-label">password:</label>
            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
        </div>

        <div class="mb-3 mt-3">
            <label for="email" class="form-label">email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


