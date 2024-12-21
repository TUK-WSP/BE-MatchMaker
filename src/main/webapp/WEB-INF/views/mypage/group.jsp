<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 가입된 모임</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .list-group-item {
            border-radius: 10px;
            margin-bottom: 15px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            background-color: white;
            transition: transform 0.2s;
            cursor: pointer; /* 커서 모양 변경 */
        }
        .list-group-item:hover {
            transform: scale(1.02);
        }
        .group-thumbnail {
            border-radius: 10px 0 0 10px;
            height: 100px;
            width: 100px;
            object-fit: cover;
        }
        .group-details {
            padding: 15px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">나의 모임</h1>
        <ul class="nav nav-tabs justify-content-center mb-4">
            <li class="nav-item">
                <a class="nav-link" href="/mypage">내 정보</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/update">정보 수정</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mypage/application">나의 모집신청</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/mypage/group">나의 모임</a>
            </li>
        </ul>

        <div class="list-group">
            <c:if test="${not empty userGroups}">
                <c:forEach var="userGroup" items="${userGroups}">
                    <a href="/group/${userGroup.group.groupId}" class="list-group-item d-flex align-items-center">
                        <img src="${userGroup.group.groupThumbnailImageUrl}" alt="Thumbnail" class="group-thumbnail">
                        <div class="group-details flex-grow-1">
                            <h5 class="mb-1">모임명: ${userGroup.group.groupName}</h5>
                            <p class="mb-1"><strong>설명:</strong> ${userGroup.group.groupDescription}</p>
                        </div>
                        <button type="button" class="btn btn-danger btn-sm ms-auto" onclick="leaveGroup('${userGroup.userGroupId}')">모임 탈퇴</button>
                    </a>
                </c:forEach>
            </c:if>
            <c:if test="${empty userGroups}">
                <div class="alert alert-warning" role="alert">
                    가입된 모임이 없습니다.
                </div>
            </c:if>
        </div>

        <script>
            function leaveGroup(userGroupId) {
                if (confirm('정말로 이 모임에서 탈퇴하시겠습니까?')) {
                    fetch(`/mypage/group/leave?userGroupId=${userGroupId}`, {
                        method: 'DELETE',
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('탈퇴에 실패했습니다. 서버 상태 코드: ' + response.status);
                        }
                        return response.json(); // JSON 응답 반환
                    })
                    .then(data => {
                        if (data.success) {
                            alert('탈퇴가 완료되었습니다.');
                            window.location.reload(); // 페이지 새로 고침
                        } else {
                            alert(data.message || '알 수 없는 오류가 발생했습니다.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('서버 오류가 발생했습니다. 관리자에게 문의하세요.');
                    });
                }
            }
        </script>
    </div>
</body>
</html>
