<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="euc-kr" %>
<!DOCTYPE html>
<html>
<head>
    <title>�α���</title>
</head>
<body>
<h2>�α���</h2>
<form:form action="${pageContext.request.contextPath}/login" method="post">
    <!-- CSRF ��ū�� �����մϴ� -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div>
        <label for="username">���̵�:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">��й�ȣ:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">�α���</button>
    </div>
    <c:if test="${not empty error}">
        <div style="color: red;">���̵� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.</div>
    </c:if>
</form:form>
</body>
</html>
