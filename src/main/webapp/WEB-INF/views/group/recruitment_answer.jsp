<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>모집 신청 답변 작성 페이지</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-5">

<h1 class="mb-4">모집 신청 답변 조회</h1>

<!-- answer_id를 입력받는 폼 -->
<form id="answerForm" class="mb-3">
    <div class="mb-3">
        <label for="answer_id" class="form-label">Answer ID</label>
        <input type="text" class="form-control" id="answer_id" name="answer_id" placeholder="answer_id를 입력하세요">
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
                url: "${pageContext.request.contextPath}/recruitment/answer",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    // 응답 예시:
                    // {
                    //   "userGroups": [
                    //     {
                    //       "userGroupId": "1001",
                    //       "group": {
                    //         "groupId": "g-123",
                    //         "groupName": "Sample Group",
                    //         "groupThumbnailImageUrl": "https://example.com/img.png",
                    //         "groupDescription": "This is a sample group description."
                    //       }
                    //     }
                    //   ]
                    // }

                    var userGroups = response.userGroups;
                    if (!userGroups || userGroups.length === 0) {
                        $("#result").html("<p>조회 결과가 없습니다.</p>");
                        return;
                    }

                    var html = "<h3>조회 결과</h3>";
                    html += "<table class='table table-bordered mt-3'>";
                    html += "<thead><tr><th>UserGroup ID</th><th>Group ID</th><th>Group Name</th><th>Thumbnail URL</th><th>Description</th></tr></thead><tbody>";

                    for (var i = 0; i < userGroups.length; i++) {
                        var ug = userGroups[i];
                        html += "<tr>";
                        html += "<td>" + ug.userGroupId + "</td>";
                        html += "<td>" + (ug.group ? ug.group.groupId : "") + "</td>";
                        html += "<td>" + (ug.group ? ug.group.groupName : "") + "</td>";
                        html += "<td>" + (ug.group ? ug.group.groupThumbnailImageUrl : "") + "</td>";
                        html += "<td>" + (ug.group ? ug.group.groupDescription : "") + "</td>";
                        html += "</tr>";
                    }

                    html += "</tbody></table>";
                    $("#result").html(html);
                },
                error: function(xhr, status, error) {
                    $("#result").html("<h3>조회 실패</h3><p>" + xhr.responseText + "</p>");
                }
            });
        });
    });
</script>

</body>
</html>