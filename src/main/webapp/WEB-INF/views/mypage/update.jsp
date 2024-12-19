<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h4>내 정보 수정</h4>
<form id="updateForm" class="row g-3 mt-2">
    <input type="hidden" name="userId" value="${user.userId}"/>
    <div class="col-md-6">
        <label for="userName" class="form-label">이름</label>
        <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}">
    </div>
    <div class="col-md-6">
        <label for="userEmail" class="form-label">이메일</label>
        <input type="email" class="form-control" id="userEmail" name="userEmail" value="${user.userEmail}">
    </div>
    <div class="col-md-6">
        <label for="gender" class="form-label">성별</label>
        <select class="form-select" id="gender" name="gender">
            <option value="MALE" ${user.gender == 'MALE' ? 'selected' : ''}>남성</option>
            <option value="FEMALE" ${user.gender == 'FEMALE' ? 'selected' : ''}>여성</option>
        </select>
    </div>
    <div class="col-md-6">
        <label for="hobbies" class="form-label">취미</label>
        <input type="text" class="form-control" id="hobbies" name="hobbies" value="${user.userHobbies}">
    </div>
    <div class="col-12">
        <button type="submit" class="btn btn-primary">수정하기</button>
    </div>
</form>

<!-- jQuery (AJAX를 위한 라이브러리) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    // 폼 제출 시 AJAX로 처리
    $(document).ready(function() {
        $("#updateForm").on("submit", function(e) {
            e.preventDefault(); // 폼의 기본 제출을 막음

            // 폼 데이터 수집
            var formData = {
                userName: $("#userName").val(),
                userEmail: $("#userEmail").val(),
                gender: $("#gender").val(),
                hobbies: $("#hobbies").val()
            };

            // AJAX 요청
            $.ajax({
                url: "/mypage/update",  // 서버에서 내 정보 수정 처리하는 URL
                type: "PUT",  // PUT 메소드 사용
                contentType: "application/json",  // 요청의 콘텐츠 타입을 JSON으로 설정
                data: JSON.stringify(formData),  // 폼 데이터를 JSON 형식으로 변환하여 보냄
                success: function(response) {
                    alert("정보가 성공적으로 수정되었습니다.");
                },
                error: function(xhr, status, error) {
                    // 오류 처리
                    var errorMessage = xhr.responseJSON ? xhr.responseJSON.message : "정보 수정에 실패했습니다. 다시 시도해주세요.";
                    alert(errorMessage);
                }
            });
        });
    });
</script>
