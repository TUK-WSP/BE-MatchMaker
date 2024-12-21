<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container vh-100 d-flex justify-content-center align-items-center">
    <div class="col-md-6">
        <div class="card shadow-lg">
            <div class="card-header text-center">
                <h2>로그인</h2>
            </div>
            <div class="card-body">
                <form id="loginForm">
                    <div class="mb-3">
                        <label for="email" class="form-label">이메일:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">비밀번호:</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-lg w-100">로그인</button>
                </form>
                <div class="text-center mt-3">
                    <a href="/auth/register" class="btn btn-link">회원가입</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#loginForm').submit(function(e) {
            e.preventDefault();

            var loginData = {
                userEmail: $('#email').val(),
                userPassword: $('#password').val()
            };

            if (!loginData.userEmail.includes('@')) {
                alert('유효한 이메일 주소를 입력하세요.');
                return;
            }

            console.log('로그인 데이터 전송: ', loginData);

            $.ajax({
                url: '/api/auth/login',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(loginData),
                success: function(response) {
                    if (response && response.code === 'COMMON200') {
                        alert('로그인 성공!');
                        sessionStorage.setItem('user', JSON.stringify(response.data || {}));
                        window.location.href = '/view/main';
                    } else {
                        const message = response?.message || '알 수 없는 이유로 실패했습니다.';
                        alert(`로그인 실패: ${message}`);
                    }
                },
                error: function(xhr) {
                    const errorMessages = {
                        400: '요청이 잘못되었습니다. 입력을 확인하세요.',
                        401: '인증 실패: 이메일 또는 비밀번호를 확인하세요.',
                        500: '서버 오류: 잠시 후 다시 시도해주세요.'
                    };
                    alert(errorMessages[xhr.status] || `알 수 없는 오류가 발생했습니다. 상태 코드: ${xhr.status}`);
                }
            });

        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
