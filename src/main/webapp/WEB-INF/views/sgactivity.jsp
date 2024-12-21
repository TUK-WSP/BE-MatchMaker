<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>소모임 활동 기록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/subgroup">MatchMaker 소모임</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/">홈</a></li>
                <li class="nav-item"><a class="nav-link" href="/subgroup/sgcreate">소모임 생성</a></li>
                <li class="nav-item"><a class="nav-link" href="/subgroup/sglist">소모임 조회</a></li>
                <li class="nav-item"><a class="nav-link active" href="/subgroup/sgActivity">활동 기록</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="mb-4">소모임 활동 기록</h1>

    <!-- 활동 기록 목록 -->
    <div class="list-group mb-5">
        <c:forEach var="activity" items="${activities}">
            <div class="list-group-item">
                <h5>${activity.title}</h5>
                <p>${activity.content}</p>
                <small class="text-muted">작성일: ${activity.createdAt}</small>
            </div>
        </c:forEach>
    </div>

    <!-- 활동 기록 추가 폼 -->
    <h3 class="mb-4">새 활동 기록 추가</h3>
    <form action="/subgroup/activity/sgactivity/${subgroupId}" method="POST">
        <div class="mb-3">
            <label for="title" class="form-label">활동 제목</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">활동 내용</label>
            <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">활동 기록 추가</button>
    </form>
</div>

<footer class="bg-light text-center py-3 mt-5">
    <p>&copy; 2024 MatchMaker. All Rights Reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>