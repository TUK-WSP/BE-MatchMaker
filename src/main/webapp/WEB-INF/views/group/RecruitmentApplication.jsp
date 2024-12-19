<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>모집 신청 답변 작성 페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-5">

<h1 class="mb-4">모집 신청 답변 조회</h1>

<form id="answerForm" class="mb-3">
    <div class="mb-3">
        <label for="answer_id" class="form-label">Answer ID</label>
        <input type="text" class="form-control" id="answer_id" name="answer_id" placeholder="Answer ID 입력">
    </div>
    <button type="button" id="searchBtn" class="btn btn-primary">조회</button>
</form>

<div id="result"></div>

<script>
    $(document).ready(function() {
        $("#searchBtn").click(function() {
            var requestData = {
                "answer_id": $("#answer_id").val()
            };

            $.ajax({
                url: "<%=request.getContextPath()%>/recruitment/answer",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    var userGroups = response.userGroups;
                    if (!userGroups || userGroups.length === 0) {
                        $("#result").html("<div class='alert alert-warning mt-4'>조회 결과가 없습니다.</div>");
                        return;
                    }

                    var html = "<h4 class='mt-4'>조회 결과</h4>";
                    html += "<table class='table table-bordered mt-3'><thead><tr><th>UserGroup ID</th><th>Group ID</th><th>Group Name</th><th>Thumbnail URL</th><th>Description</th></tr></thead><tbody>";

                    for (var i = 0; i < userGroups.length; i++) {
                        var ug = userGroups[i];
                        var g = ug.group;
                        html += "<tr>";
                        html += "<td>" + ug.userGroupId + "</td>";
                        html += "<td>" + g.groupId + "</td>";
                        html += "<td>" + g.groupName + "</td>";
                        html += "<td>" + g.groupThumbnailImageUrl + "</td>";
                        html += "<td>" + g.groupDescription + "</td>";
                        html += "</tr>";
                    }

                    html += "</tbody></table>";
                    $("#result").html(html);
                },
                error: function(xhr, status, error) {
                    var html = "<div class='alert alert-danger mt-4' role='alert'>" +
                        "<h4 class='alert-heading'>조회 실패</h4>" +
                        "<p>" + xhr.responseText + "</p>" +
                        "</div>";
                    $("#result").html(html);
                }
            });
        });
    });
</script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
