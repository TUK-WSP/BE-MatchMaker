<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h2>회원가입</h2>
                </div>
                <div class="card-body">
                    <form id="registerForm" method="POST">
                        <div class="mb-3">
                            <label for="userName" class="form-label">이름:</label>
                            <input type="text" class="form-control" id="userName" name="userName" required>
                        </div>
                        <div class="mb-3">
                            <label for="userEmail" class="form-label">이메일:</label>
                            <input type="email" class="form-control" id="userEmail" name="userEmail" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">성별:</label>
                            <select class="form-select" id="gender" name="gender" required>
                                <option value="">선택하세요</option>
                                <option value="MALE">남성</option>
                                <option value="FEMALE">여성</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">가입하기</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#registerForm').submit(function(e) {
            e.preventDefault();

            var registerData = {
                userName: $('#userName').val(),
                userEmail: $('#userEmail').val(),
                password: $('#password').val(),
                gender: $('#gender').val()
            };

            $.ajax({
                url: '/api/auth/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(registerData),
                success: function(response) {
                    if (response && response.code === "COMMON200") {
                        alert('회원가입 성공!');
                        window.location.href = '/auth/login';
                    } else {
                        alert('회원가입 실패: ' + (response.message || '알 수 없는 이유로 실패했습니다.'));
                    }
                },
                error: function(xhr) {
                    if (xhr.status === 403) {
                        alert('접근이 거부되었습니다: 권한이 없습니다.');
                    } else if (xhr.status === 405) {
                        alert('잘못된 메서드 요청입니다. 요청 메서드를 확인하세요.');
                    } else {
                        alert('회원가입 실패: ' + xhr.responseText);
                    }
                }
            });
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
