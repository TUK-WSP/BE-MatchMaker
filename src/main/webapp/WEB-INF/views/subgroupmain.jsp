<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>소모임 메인</title>
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
                <li class="nav-item"><a class="nav-link active" href="/subgroup">내 소모임</a></li>
                <li class="nav-item"><a class="nav-link" href="/notice">공지사항</a></li>
                <li class="nav-item"><a class="nav-link" href="/profile">프로필 설정</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 메인 콘텐츠 -->
<div class="container mt-4">
    <!-- 소개 섹션 -->
    <div class="text-center mb-5">
        <h1>소모임에 참여하고 새로운 연결을 만들어보세요!</h1>
        <p class="text-muted">소모임 생성, 조회 및 활동 기록을 확인할 수 있습니다.</p>
    </div>

    <!-- 주요 기능 섹션 -->
    <div class="row text-center">
        <!-- 소모임 생성 -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">소모임 생성하기</h5>
                    <p class="card-text">새로운 소모임을 만들고 친구들과 함께하세요.</p>
                    <a href="/subgroup/sgcreate" class="btn btn-primary">소모임 생성</a>
                </div>
            </div>
        </div>
        <!-- 소모임 조회 -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">소모임 조회하기</h5>
                    <p class="card-text">내가 가입한 소모임을 조회하고 관리하세요.</p>
                    <a href="/subgroup/sglist" class="btn btn-secondary">소모임 조회</a>
                </div>
            </div>
        </div>
        <!-- 활동 기록 -->
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">활동 기록 보기</h5>
                    <p class="card-text">소모임의 활동 기록을 확인하고 공유하세요.</p>
                    <a href="/subgroup/sgactivity" class="btn btn-success">활동 기록</a> <!-- 활동 기록 보기 버튼 -->
                </div>
            </div>
        </div>
    </div>

    <!-- 추천 소모임 섹션 -->
    <div class="mt-5">
        <h2>추천 소모임</h2>
        <div class="row">
            <div class="col-md-6 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">예술을 사랑하는 사람들</h5>
                        <p class="card-text">예술을 좋아하는 사람들과 함께하세요!</p>
                        <button class="btn btn-outline-primary">참가하기</button>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">코딩과 개발</h5>
                        <p class="card-text">개발자 커뮤니티에 참여하세요.</p>
                        <button class="btn btn-outline-primary">참가하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 푸터 -->
<footer class="bg-light text-center py-3 mt-5">
    <p>&copy; 2024 MatchMaker. All Rights Reserved.</p>
    <a href="/policy" class="text-muted me-3">개인정보 처리방침</a>
    <a href="/terms" class="text-muted me-3">서비스 약관</a>
    <a href="/contact" class="text-muted">문의하기</a>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>