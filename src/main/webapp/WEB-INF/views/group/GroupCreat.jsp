<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>그룹 생성</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h1 class="mb-4">그룹 생성</h1>
<form action="<%=request.getContextPath()%>/group/create" method="post">
    <div class="mb-3">
        <label for="group_name" class="form-label">그룹 이름</label>
        <input type="text" class="form-control" id="group_name" name="group_name" placeholder="그룹 이름을 입력하세요">
    </div>

    <div class="mb-3">
        <label for="group_description" class="form-label">그룹 설명</label>
        <textarea class="form-control" id="group_description" name="group_description" placeholder="그룹 설명을 입력하세요"></textarea>
    </div>

    <div class="mb-3">
        <label for="group_status" class="form-label">그룹 상태 (ACTIVE, INACTIVE)</label>
        <input type="text" class="form-control" id="group_status" name="group_status" placeholder="그룹 상태를 입력하세요">
    </div>

    <div class="mb-3">
        <label for="group_thumbnail_image_url" class="form-label">그룹 썸네일 이미지 URL</label>
        <input type="text" class="form-control" id="group_thumbnail_image_url" name="group_thumbnail_image_url" placeholder="이미지 URL을 입력하세요">
    </div>

    <button type="submit" class="btn btn-primary">생성</button>
</form>

<!-- Bootstrap JS (필요하다면) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
