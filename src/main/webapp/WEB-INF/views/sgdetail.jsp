<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>소모임 상세 보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/subgroup">MatchMaker 소모임</a>
    </div>
</nav>

<div class="container mt-5">
    <h1>소모임 상세 보기</h1>

    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">${subGroup.subgroupName}</h5>
            <p class="card-text"><strong>소모임 설명:</strong> ${subGroup.description}</p>
            <p class="card-text"><strong>상위 그룹:</strong> ${subGroup.groupName}</p>
            <p class="card-text"><strong>상태:</strong> ${subGroup.status}</p>
            <p class="card-text"><strong>생성일:</strong> ${subGroup.createdAt}</p>
            <p class="card-text"><strong>수정일:</strong> ${subGroup.updatedAt}</p>
        </div>
    </div>

    <h3>소모임 멤버들</h3>
    <div class="list-group">
        <c:forEach var="member" items="${subGroup.members}">
            <div class="list-group-item">
                <p><strong>사용자 ID:</strong> ${member.userId}</p>
                <p><strong>사용자 이름:</strong> ${member.userName}</p>
                <p><strong>가입 날짜:</strong> ${member.joinedDate}</p>
            </div>
        </c:forEach>
    </div>

    <h3>소모임 활동</h3>
    <div class="list-group">
        <c:forEach var="activity" items="${subGroup.activities}">
            <div class="list-group-item">
                <p><strong>활동 제목:</strong> ${activity.title}</p>
                <p><strong>활동 생성일:</strong> ${activity.createdAt}</p>
            </div>
        </c:forEach>
    </div>

    <a href="/subgroup/read/sglist" class="btn btn-secondary mt-4">뒤로 가기</a>
</div>

<footer class="bg-light text-center py-3 mt-5">
    <p>&copy; 2024 MatchMaker. All Rights Reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>