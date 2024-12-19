<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>모집 신청 리스트 페이지</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h1>모집 신청 조회</h1>

<form id="applicationForm">
    <label for="application_id">Application ID:</label>
    <input type="text" id="application_id" name="application_id" placeholder="신청 ID 입력">
    <button type="button" id="searchBtn">조회</button>
</form>

<hr>

<div id="result"></div>

<script>
    $(document).ready(function() {
        $("#searchBtn").click(function() {
            var requestData = {
                "application_id": $("#application_id").val()
            };

            $.ajax({
                url: "${pageContext.request.contextPath}/recruitment/application",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    // response:
                    // {
                    //   "recruitmentId": "...",
                    //   "recruitmentApplication": {
                    //     "application_id": "...",
                    //     "application_status": "...",
                    //     "user_id": "...",
                    //     "application": "..."
                    //   }
                    // }

                    var html = "<h3>조회 결과</h3>";
                    html += "<p>Recruitment ID: " + response.recruitmentId + "</p>";

                    var app = response.recruitmentApplication;
                    html += "<h4>Recruitment Application 정보</h4>";
                    html += "<p>Application ID: " + app.application_id + "</p>";
                    html += "<p>Application Status: " + app.application_status + "</p>";
                    html += "<p>User ID: " + app.user_id + "</p>";
                    html += "<p>Application: " + app.application + "</p>";

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
