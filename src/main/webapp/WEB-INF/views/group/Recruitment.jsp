<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모집글 생성</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">모집글 생성</h2>

    <form action="/recruitment/create" method="post" enctype="multipart/form-data">
        <!-- 그룹 ID (Hidden input, 서버에서 설정할 값) -->
        <input type="hidden" name="groupId" value="${groupId}"/>

        <!-- 모집글 제목 -->
        <div class="mb-3">
            <label for="recruitment_title" class="form-label">모집글 제목</label>
            <input type="text" class="form-control" id="recruitment_title" name="recruitment_title" required>
        </div>

        <!-- 모집글 설명 -->
        <div class="mb-3">
            <label for="recruitment_description" class="form-label">모집글 설명</label>
            <textarea class="form-control" id="recruitment_description" name="recruitment_description" rows="3" required></textarea>
        </div>

        <!-- 모집 기한 -->
        <div class="mb-3">
            <label for="recruitment_deadline" class="form-label">모집 기한</label>
            <input type="datetime-local" class="form-control" id="recruitment_deadline" name="recruitment_deadline" required>
        </div>

        <!-- 모집 인원 -->
        <div class="mb-3">
            <label for="recruitmentNumber" class="form-label">모집 인원</label>
            <input type="number" class="form-control" id="recruitmentNumber" name="recruitmentNumber" required>
        </div>

        <!-- 모집 상태 -->
        <div class="mb-3">
            <label for="recruitment_status" class="form-label">모집 상태</label>
            <select class="form-select" id="recruitment_status" name="recruitment_status" required>
                <option value="ACTIVE">활성</option>
                <option value="INACTIVE">비활성</option>
            </select>
        </div>

        <!-- 제출 버튼 -->
        <button type="submit" class="btn btn-primary">모집글 생성</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
