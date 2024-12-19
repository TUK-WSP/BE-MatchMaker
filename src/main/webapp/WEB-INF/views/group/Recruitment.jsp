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

    <form action="RecruitmentController" method="POST">
        <input type="hidden" name="groupId" value="${groupId}"> <!-- 그룹 ID hidden 필드 -->

        <div class="mb-3">
            <label for="recruitmentTitle" class="form-label">모집 제목</label>
            <input type="text" class="form-control" id="recruitmentTitle" name="recruitment_title" required>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">모집 설명</label>
            <textarea class="form-control" id="description" name="recruitment_description" rows="4" required></textarea>
        </div>

        <div class="mb-3">
            <label for="deadline" class="form-label">모집 마감일</label>
            <input type="date" class="form-control" id="deadline" name="recruitment_deadline" required>
        </div>

        <div class="mb-3">
            <label for="number" class="form-label">모집 인원</label>
            <input type="number" class="form-control" id="number" name="recruitmentNumber" min="1" required>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">모집 상태</label>
            <select class="form-select" id="status" name="recruitment_status" required>
                <option value="OPEN">열림</option>
                <option value="CLOSED">마감</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">모집글 생성</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
