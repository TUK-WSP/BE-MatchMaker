<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.UUID" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recruitment 생성</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h1>모집글 생성</h1>
<form id="recruitmentForm">
    <label for="groupId">Group ID:</label><br>
    <input type="text" id="groupId" name="groupId" placeholder="그룹 ID(UUID)" value=""><br><br>

    <label for="recruitment_title">제목:</label><br>
    <input type="text" id="recruitment_title" name="recruitment_title" placeholder="모집글 제목"><br><br>

    <label for="recruitment_description">설명:</label><br>
    <textarea id="recruitment_description" name="recruitment_description" placeholder="모집글 설명"></textarea><br><br>

    <label for="recruitment_deadline">마감일(yyyy-MM-dd):</label><br>
    <input type="text" id="recruitment_deadline" name="recruitment_deadline" placeholder="예: 2024-12-31"><br><br>

    <label for="recruitmentNumber">모집인원:</label><br>
    <input type="number" id="recruitmentNumber" name="recruitmentNumber" placeholder="모집 인원 수"><br><br>

    <label for="recruitment_status">상태:</label><br>
    <input type="text" id="recruitment_status" name="recruitment_status" placeholder="예: ACTIVE or INACTIVE"><br><br>

    <button type="button" id="submitBtn">등록</button>
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
                url: "${pageContext.request.contextPath}/recruitment",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    // response: { "recruitmentId", "groupId", "title", "description", "deadline", "recruitmentNumber" }
                    $("#result").html(
                        "<h3>등록 성공!</h3>" +
                        "<p>Recruitment ID: " + response.recruitmentId + "</p>" +
                        "<p>Group ID: " + response.groupId + "</p>" +
                        "<p>제목: " + response.title + "</p>" +
                        "<p>설명: " + response.description + "</p>" +
                        "<p>마감일: " + response.deadline + "</p>" +
                        "<p>모집인원: " + response.recruitmentNumber + "</p>"
                    );
                },
                error: function(xhr, status, error) {
                    $("#result").html("<h3>등록 실패</h3><p>" + xhr.responseText + "</p>");
                }
            });
        });
    });
</script>

</body>
</html>
