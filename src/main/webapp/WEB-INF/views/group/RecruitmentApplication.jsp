<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>모집 신청 리스트 페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-5">

<h1 class="mb-4">모집 신청 조회</h1>

<form id="applicationForm" class="mb-3">
    <div class="mb-3">
        <label for="application_id" class="form-label">Application ID</label>
        <input type="text" class="form-control" id="application_id" name="application_id" placeholder="신청 ID 입력">
    </div>
    <button type="button" id="searchBtn" class="btn btn-primary">조회</button>
</form>

<div id="result"></div>

<script>
    $(document).ready(function() {
        $("#searchBtn").click(function() {
            var requestData = {
                "application_id": $("#application_id").val()
            };

            $.ajax({
                url: "<%=request.getContextPath()%>/recruitment/application",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(requestData),
                dataType: "json",
                success: function(response) {
                    var html = "<div class='card mt-4'>" +
                        "<div class='card-body'>" +
                        "<h5 class='card-title'>조회 결과</h5>" +
                        "<p>Recruitment ID: " + response.recruitmentId + "</p>";

                    var app = response.recruitmentApplication;
                    html += "<h6>Recruitment Application 정보</h6>";
                    html += "<p>Application ID: " + app.application_id + "</p>";
                    html += "<p>Application Status: " + app.application_status + "</p>";
                    html += "<p>User ID: " + app.user_id + "</p>";
                    html += "<p>Application: " + app.application + "</p>";

                    html += "</div></div>";
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
