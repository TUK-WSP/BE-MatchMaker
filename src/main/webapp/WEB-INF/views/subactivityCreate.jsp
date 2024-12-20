<%--
  Created by IntelliJ IDEA.
  User: june
  Date: 24. 12. 20.
  Time: 오후 6:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>활동 기록 추가</h2>

<form action="/subgroup/activity/sgactivity/${subgroupId}" method="POST">
    <input type="hidden" name="subGroupId" value="${subgroupId}" />

    <label for="title">활동 제목:</label>
    <input type="text" id="title" name="title" required /><br>

    <label for="content">활동 내용:</label>
    <textarea id="content" name="content" required></textarea><br>

    <button type="submit" class="btn btn-success">활동 기록 생성</button>
</form>
