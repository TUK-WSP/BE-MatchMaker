<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>그룹 생성</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">그룹 생성</h2>
    <form action="/group/create" method="post" enctype="multipart/form-data">
        <!-- 그룹 이름 -->
        <div class="mb-3">
            <label for="groupName" class="form-label">그룹 이름</label>
            <input type="text" class="form-control" id="groupName" name="group_name" required>
        </div>
        <!-- 그룹 이미지 -->
        <div class="mb-3">
            <label for="groupThumbnailImage" class="form-label">그룹 이미지</label>
            <input type="file" class="form-control" id="groupThumbnailImage" name="group_thumbnail_image_url" accept="image/*">
        </div>
        <!-- 그룹 설명 -->
        <div class="mb-3">
            <label for="groupDescription" class="form-label">그룹 설명</label>
            <textarea class="form-control" id="groupDescription" name="group_description" rows="3" required></textarea>
        </div>
        <!-- 그룹 상태 -->
        <div class="mb-3">
            <label for="groupStatus" class="form-label">그룹 상태</label>
            <select class="form-select" id="groupStatus" name="group_status" required>
                <option value="active">활성</option>
                <option value="inactive">비활성</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary w-100">그룹 생성</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>