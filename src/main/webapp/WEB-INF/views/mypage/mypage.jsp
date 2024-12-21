<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border-radius: 15px;
        }
        .card-title {
            font-weight: bold;
        }
        .badge-list {
            margin-top: 10px;
        }
        .badge-item {
            background-color: #e9ecef;
            border-radius: 5px;
            padding: 5px 10px;
            margin-right: 5px;
            display: inline-block;
        }

    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">내 정보</h1>
        <ul class="nav nav-tabs justify-content-center mb-4">
            <li class="nav-item">
                <a class="nav-link active" href="/mypage">내 정보</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/update">정보 수정</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/application">나의 모집신청</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/group">나의 모임</a>
            </li>
        </ul>
        <div class="card shadow">
            <div class="card-body">
                <p><strong>아이디:</strong> ${userName}</p>
                <p><strong>이메일:</strong> ${userEmail}</p>
                <p><strong>성별:</strong> ${gender}</p>
                <p><strong>취미:</strong>
                    <c:forEach var="hobby" items="${hobbies}">
                        <span class="badge bg-info text-dark">${hobby.hobbyName}</span>
                    </c:forEach>
                </p>
                <p><strong>뱃지:</strong>
                    <div class="badge-list">
                        <c:forEach var="badge" items="${badges}">
                            <span class="badge-item">
                                ${badge.badgeName} - ${badge.badgeDescription} (획득일: ${badge.obtainedDate})
                            </span>
                        </c:forEach>
                    </div>
                </p>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
