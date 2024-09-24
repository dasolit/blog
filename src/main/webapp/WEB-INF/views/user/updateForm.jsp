<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container-fluid mt-3">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="mb-3 mt-3">
            <label for="username" class="form-label">username:</label>
            <input type="text" value="${principal.user.username}" class="form-control" id="username"
                   placeholder="Enter username" name="username" readonly>
        </div>
        <c:if test="${empty principal.user.oauth}">
            <div class="mb-3 mt-3">
                <label for="password" class="form-label">password:</label>
                <input type="password" class="form-control" id="password"
                       placeholder="Enter password" name="password">
            </div>
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">email:</label>
                <input type="email" value="${principal.user.email}" class="form-control" id="email"
                       placeholder="Enter email" name="email">
            </div>
        </c:if>


    </form>
    <button id="btn-user-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>


