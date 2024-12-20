<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>소모임 생성</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- 헤더 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/subgroup">MatchMaker 소모임</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/">홈</a></li>
                <li class="nav-item"><a class="nav-link" href="/subgroup">내 소모임</a></li>
                <li class="nav-item"><a class="nav-link" href="/notice">공지사항</a></li>
                <li class="nav-item"><a class="nav-link" href="/profile">프로필 설정</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 메인 콘텐츠 -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h1 class="text-center mb-4">소모임 생성</h1>
            <form action="/subgroup/create" method="post">
                <div class="mb-3">
                    <label for="groupId" class="form-label">상위 그룹 ID</label>
                    <input type="text" class="form-control" id="groupId" name="groupId" placeholder="상위 그룹 ID를 입력하세요" required>
                </div>
                <div class="mb-3">
                    <label for="subgroupName" class="form-label">소모임 이름</label>
                    <input type="text" class="form-control" id="subgroupName" name="subgroupName" placeholder="소모임 이름을 입력하세요" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">소모임 설명</label>
                    <textarea class="form-control" id="description" name="description" rows="4" placeholder="소모임에 대한 설명을 입력하세요"></textarea>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label">소모임 상태</label>
                    <select class="form-select" id="status" name="status" required>
                        <option value="활성">활성</option>
                        <option value="비활성">비활성</option>
                    </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">소모임 생성</button>
                    <a href="/subgroup" class="btn btn-secondary">취소</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 푸터 -->
<footer class="bg-light text-center py-3 mt-5">
    <p>&copy; 2024 MatchMaker. All Rights Reserved.</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>