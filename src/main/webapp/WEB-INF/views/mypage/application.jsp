<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 모집신청</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .application-item {
            background-color: white;
            border-radius: 10px;
            padding: 15px;
            margin-bottom: 15px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .status {
            font-weight: bold;
        }
        .status-approved {
            color: green;
        }
        .status-rejected {
            color: red;
        }
        .status-pending {
            color: orange;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">나의 모집신청</h1>

        <ul class="nav nav-tabs justify-content-center mb-4">
            <li class="nav-item">
                <a class="nav-link" href="/mypage">내 정보</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/update">정보 수정</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/mypage/application">나의 모집신청</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/group">나의 모임</a>
            </li>
        </ul>

        <div class="list-group">
            <c:if test="${not empty recruitmentApplications}">
                <c:forEach var="application" items="${recruitmentApplications}">
                    <div class="list-group-item application-item" id="application-${application.id}">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <p class="mb-1"><strong>제목:</strong> ${application.recruitment.title}</p>
                                <p class="mb-1"><strong>설명:</strong> ${application.recruitment.description}</p>
                                <p class="mb-1">
                                    <strong>신청 상태:</strong>
                                    <span class="status
                                        ${application.applicationStatus == 'APPROVED' ? 'status-approved' :
                                        application.applicationStatus == 'REJECTED' ? 'status-rejected' :
                                        'status-pending'}">
                                        ${application.applicationStatus}
                                    </span>
                                </p>
                                <p class="mb-1"><strong>신청일:</strong> ${application.createdAt}</p>
                            </div>
                            <button class="btn btn-danger" onclick="cancelApplication('${application.id}')">모집 취소</button>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty recruitmentApplications}">
                <div class="alert alert-warning" role="alert">
                    현재 신청한 모집이 없습니다.
                </div>
            </c:if>
        </div>

    </div>
    <script>
        function cancelApplication(applicationId) {
            if (confirm("정말로 모집을 취소하시겠습니까?")) {
                // AJAX 요청을 사용하여 모집 취소 처리
                fetch('/mypage/cancelApplication?applicationId=' + applicationId, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            alert('모집 취소가 완료되었습니다.');
                            document.getElementById("application-" + applicationId).remove(); // 해당 항목 삭제
                        } else {
                            alert('모집 취소에 실패하였습니다.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('오류가 발생했습니다.');
                    });
            }
        }
    </script>
</body>
</html>
