<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
    </style>
    <script>
        function addHobby() {
            const hobbyInput = document.createElement('input');
            hobbyInput.type = 'text';
            hobbyInput.className = 'form-control mb-3';
            hobbyInput.name = 'hobbies'; // 추가된 취미의 이름
            hobbyInput.placeholder = '취미를 추가하세요';
            document.getElementById('hobbyContainer').appendChild(hobbyInput);
        }

        document.addEventListener("DOMContentLoaded", function () {
            const form = document.getElementById('updateForm');
            form.addEventListener("submit", function(event) {
                event.preventDefault(); // 기본 폼 제출을 막습니다.

                const formData = new FormData(form);
                const data = {};
                formData.forEach((value, key) => {
                    if (Array.isArray(data[key])) {
                        data[key].push(value);
                    } else if (data[key]) {
                        data[key] = [data[key], value];
                    } else {
                        data[key] = value;
                    }
                });

                // PUT 요청을 보냅니다.
                fetch('/mypage/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data), // 데이터를 JSON 형식으로 보냅니다.
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('수정에 실패했습니다.');
                    }
                    return response.json(); // JSON 응답 반환
                })
                .then(data => {
                    if (data.success) {
                        window.location.href = '/api/users/mypage'; // 성공 시 정보 페이지로 이동
                    } else {
                        alert(data.message); // 실패 시 메시지 표시
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('서버 오류가 발생했습니다.'); // 서버 오류 처리
                });
            });
        });
    </script>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">내 정보 수정</h1>

    <!-- 내비게이션 탭 추가 -->
    <ul class="nav nav-tabs justify-content-center mb-4">
        <li class="nav-item">
            <a class="nav-link" href="/mypage">내 정보</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/mypage/update">정보 수정</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/mypage/application">나의 모집신청</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/mypage/group">나의 모임</a>
        </li>
    </ul>

    <form id="updateForm"> <!-- action 속성을 제거하고 ID만 추가 -->
        <input type="hidden" name="userId" value="${user.userId}"> <!-- userId 숨겨진 필드 추가 -->
        <div class="mb-3">
            <label for="userName" class="form-label">사용자 이름</label>
            <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}">
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">이메일</label>
            <input type="email" class="form-control" id="userEmail" name="userEmail" value="${user.userEmail}">
        </div>
        <div class="mb-3">
            <label for="gender" class="form-label">성별</label>
            <select class="form-select" id="gender" name="gender">
                <option value="MALE" ${user.gender == 'MALE' ? 'selected' : ''}>남성</option>
                <option value="FEMALE" ${user.gender == 'FEMALE' ? 'selected' : ''}>여성</option>
            </select>
        </div>

        <div class="mb-3" id="hobbyContainer">
            <label class="form-label">취미</label>
            <c:forEach var="hobby" items="${user.userHobbies}">
                <input type="text" class="form-control mb-3" name="hobbies" value="${hobby.hobby.hobbyName}">
            </c:forEach>
            <button type="button" class="btn btn-secondary" onclick="addHobby()">추가 취미</button>
        </div>

        <button type="submit" class="btn btn-primary">수정완료</button>
    </form>
</div>
</body>

</html>
