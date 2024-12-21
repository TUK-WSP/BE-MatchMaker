<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4>나의 모임</h4>
<ul class="list-group">
    <c:forEach var="group" items="${groups}">
        <li class="list-group-item">
            <a href="/groups/${group.id}" class="text-decoration-none">${group.name}</a>
        </li>
    </c:forEach>
</ul>
