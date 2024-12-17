<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">

    <!-- 사용자 기본 정보 -->
    <div id="userInfo" class="mb-4">
        <p><strong>이름:</strong> <span id="userName"></span></p>
        <p><strong>이메일:</strong> <span id="userEmail"></span></p>
        <p><strong>성별:</strong> <span id="gender"></span></p>
    </div>

    <!-- 취미 정보 -->
    <h4>취미</h4>
    <ul id="hobbyList" class="list-group mb-4"></ul>

    <!-- 뱃지 정보 -->
    <h4>획득한 뱃지</h4>
    <div id="badgeList" class="row"></div>
</div>

<script>
    // 사용자 ID 값 (서버에서 설정할 수 있습니다)
    const userId = "<%= request.getParameter("userId") %>";

    // 서버에서 사용자 정보를 가져오는 함수
    async function fetchUserInfo() {
        const response = await fetch(`/mypage/myinfo?userId=${userId}`);
        const userData = await response.json();

        // 기본 정보 바인딩
        document.getElementById("userName").innerText = userData.userName;
        document.getElementById("userEmail").innerText = userData.userEmail;
        document.getElementById("gender").innerText = userData.gender;

        // 취미 목록 바인딩
        const hobbyList = document.getElementById("hobbyList");
        userData.hobbies.forEach(hobby => {
            const li = document.createElement("li");
            li.className = "list-group-item";
            li.innerText = hobby.hobbyName;
            hobbyList.appendChild(li);
        });

        // 뱃지 목록 바인딩
        const badgeList = document.getElementById("badgeList");
        userData.badges.forEach(badge => {
            const badgeCard = `
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${badge.badgeName}</h5>
                            <p class="card-text">${badge.badgeDescription}</p>
                            <p class="text-muted">획득일: ${badge.obtainedDate}</p>
                        </div>
                    </div>
                </div>
            `;
            badgeList.insertAdjacentHTML("beforeend", badgeCard);
        });
    }

    // 페이지 로드 시 사용자 정보 불러오기
    fetchUserInfo();
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
