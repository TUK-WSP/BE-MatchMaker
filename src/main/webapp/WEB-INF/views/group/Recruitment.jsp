<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>모집글 생성</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-5">

<h1 class="mb-4">모집글 생성</h1>

<form id="recruitmentForm">
    <div class="mb-3">
        <label for="groupId" class="form-label">Group ID</label>
        <input type="text" class="form-control" id="groupId" name="groupId" placeholder="그룹 ID(UUID)">
    </div>

    <div class="mb-3">
        <label for="recruitment_title" class="form-label">제목</label>
        <input type="text" class="form-control" id="recruitment_title" name="recruitment_title" placeholder="모집글 제목">
    </div>

    <div class="mb-3">
        <label for="recruitment_description" class="form-label">설명</label>
        <textarea class="form-control" id="recruitment_description" name="recruitment_description" placeholder="모집글 설명"></textarea>
    </div>

    <div class="mb-3">
        <label for="recruitment_deadline" class="form-label">마감일(yyyy-MM-dd)</label>
        <input type="text" class="form-control" id="recruitment_deadline" name="recruitment_deadline" placeholder="예: 2024-12-31">
    </div>

    <div class="mb-3">
        <label for="recruitmentNumber" class="form-label">모집인원</label>
        <input type="number" class="form-control" id="recruitmentNumber" name="recruitmentNumber" placeholder="모집 인원 수">
    </div>

    <div class="mb-3">
        <label for="recruitment_status" class="form-label">상태</label>
        <input type="text" class="form-control" id="recruitment_status" name="recruitment_status" placeholder="예: ACTIVE 또는 INACTIVE">
    </div>

    <button type="button" id="submitBtn" class="btn btn-primary">등록</button>
</form>

<hr>

<div id="result"></div>

<script>
    $(document).ready(function() {
        $("#submitBtn").click(function() {
            var requestData = {
                "groupId": $("#groupId").val(),
                "recruitmentTitle": $("#recruitment_title").val(),
                "recruitmentDescription": $("#recruitment_description").val(),
                "recruitmentDeadline": $("#recruitment_deadline").val(),
                "recruitmentNumber": parseInt($("#recruitmentNumber").val()),
                "recruitmentStatus": $("#recruitment_status").val()
            };

            $.ajax({
                url: "<%=request.getContextPath()%>/group/recruitment",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    $("#result").html(
                        "<div class='alert alert-success' role='alert'>" +
                        "<h4 class='alert-heading'>등록 성공!</h4>" +
                        "<p>Recruitment ID: " + response.recruitmentId + "</p>" +
                        "<p>Group ID: " + response.groupId + "</p>" +
                        "<p>제목: " + response.title + "</p>" +
                        "<p>설명: " + response.description + "</p>" +
                        "<p>마감일: " + response.deadline + "</p>" +
                        "<p>모집인원: " + response.recruitmentNumber + "</p>" +
                        "</div>"
                    );
                },
                error: function(xhr, status, error) {
                    $("#result").html(
                        "<div class='alert alert-danger' role='alert'>" +
                        "<h4 class='alert-heading'>등록 실패</h4>" +
                        "<p>" + xhr.responseText + "</p>" +
                        "</div>"
                    );
                }
            });
        });
    });
</script>

<!-- Bootstrap JS (필요하다면) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
