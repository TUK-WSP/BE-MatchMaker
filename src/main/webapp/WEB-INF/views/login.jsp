<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="euc-kr" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form:form action="${pageContext.request.contextPath}/login" method="post">
    <!-- CSRF 토큰을 포함합니다 -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div>
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">로그인</button>
    </div>
    <c:if test="${not empty error}">
        <div style="color: red;">아이디나 비밀번호가 올바르지 않습니다.</div>
    </c:if>
</form:form>
</body>
</html>
