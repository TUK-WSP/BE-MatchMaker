<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>활동 기록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>소모임 활동 기록</h1>
    <p>${activities.size()}개의 활동 기록이 있습니다.</p>

    <!-- 활동 기록 추가 버튼 -->
    <a href="/subgroup/activity/create" class="btn btn-primary mb-4">활동 기록 추가</a>

    <div class="list-group">
        <c:forEach var="activity" items="${activities}">
            <div class="list-group-item">
                <h5>${activity.title}</h5>
                <p>${activity.content}</p>
                <small>작성일: ${activity.createdAt}</small>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>