<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>그룹 생성</title>
</head>
<body>
<h1>그룹 생성</h1>
<form action="${pageContext.request.contextPath}/group/create" method="post">
    <label>그룹 이름:</label><br>
    <input type="text" name="group_name"><br><br>

    <label>그룹 설명:</label><br>
    <textarea name="group_description"></textarea><br><br>

    <label>그룹 상태(예: ACTIVE, INACTIVE):</label><br>
    <input type="text" name="group_status"><br><br>

    <label>그룹 썸네일 이미지 URL:</label><br>
    <input type="text" name="group_thumbnail_image_url"><br><br>

    <input type="submit" value="생성">
</form>
</body>
</html>
