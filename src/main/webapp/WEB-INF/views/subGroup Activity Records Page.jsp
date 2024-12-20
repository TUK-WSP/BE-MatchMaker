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

<!-- 헤더 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">MatchMaker 소모임</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/">홈</a></li>
                <li class="nav-item"><a class="nav-link active" href="/subgroup/sglist">소모임 조회</a></li>
                <li class="nav-item"><a class="nav-link" href="/subgroup/sgActivity">활동 기록</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 활동 기록 목록 -->
<div class="container mt-4">
    <h1>활동 기록</h1>
    <form action="/subgroup/activity/sgactivity/${subGroupId}" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">활동 제목</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">활동 내용</label>
            <textarea class="form-control" id="content" name="content" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">활동 추가</button>
    </form>

    <h2 class="mt-4">활동 기록 목록</h2>
    <ul class="list-group">
        <c:forEach var="activity" items="${activities}">
            <li class="list-group-item">
                <h5>${activity.title}</h5>
                <p>${activity.content}</p>
                <small>활동 일시: ${activity.createdAt}</small>
            </li>
        </c:forEach>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>