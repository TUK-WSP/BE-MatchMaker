<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">마이페이지</h2>
    <!-- 탭 메뉴 -->
    <ul class="nav nav-tabs" id="myPageTabs" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="info-tab" data-bs-toggle="tab" data-bs-target="#info" type="button" role="tab" aria-controls="info" aria-selected="true">내 정보</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="update-tab" data-bs-toggle="tab" data-bs-target="#update" type="button" role="tab" aria-controls="update" aria-selected="false">내 정보 수정</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="application-tab" data-bs-toggle="tab" data-bs-target="#application" type="button" role="tab" aria-controls="application" aria-selected="false">나의 모집 신청</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="group-tab" data-bs-toggle="tab" data-bs-target="#group" type="button" role="tab" aria-controls="group" aria-selected="false">나의 모임</button>
        </li>
    </ul>

    <!-- 탭 내용 -->
    <div class="tab-content mt-3" id="myPageTabsContent">
        <!-- 내 정보 -->
        <div class="tab-pane fade show active" id="info" role="tabpanel" aria-labelledby="info-tab">
            <jsp:include page="myinfo.jsp" />
        </div>
        <!-- 내 정보 수정 -->
        <div class="tab-pane fade" id="update" role="tabpanel" aria-labelledby="update-tab">
            <jsp:include page="update.jsp" />
        </div>
        <!-- 나의 모집 신청 -->
        <div class="tab-pane fade" id="application" role="tabpanel" aria-labelledby="application-tab">
            <jsp:include page="application.jsp" />
        </div>
        <!-- 나의 모임 -->
        <div class="tab-pane fade" id="group" role="tabpanel" aria-labelledby="group-tab">
            <jsp:include page="group.jsp" />
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
