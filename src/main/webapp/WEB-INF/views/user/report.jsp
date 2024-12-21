<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고 페이지</title>
    <!-- 부트스트랩 CSS (선택 사항) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery 포함 (AJAX 용) -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h2>신고 제출</h2>
    <form id="reportForm">
        <!-- 숨겨진 필드: userId -->
        <input type="hidden" id="userId" name="userId" value="1430ae8a-cca6-4f5e-83db-448adac81530">

        <!-- 신고 대상 ID 입력 필드 -->
        <div class="form-group">
            <label for="reportTargetId">신고 대상 ID</label>
            <input type="text" class="form-control" id="reportTargetId" name="reportTargetId" placeholder="신고 대상의 ID를 입력하세요" required>
        </div>

        <!-- 신고 내용 텍스트 영역 (사전 채움) -->
        <div class="form-group">
            <label for="reportContent">신고 내용</label>
            <textarea class="form-control" id="reportContent" name="reportContent" rows="5" required>이 사용자는 스팸 메시지를 반복적으로 보냅니다.</textarea>
        </div>

        <!-- 신고 유형 선택 -->
        <div class="form-group">
            <label for="reportType">신고 유형</label>
            <select class="form-control" id="reportType" name="reportType" required>
                <option value="">유형을 선택하세요</option>
                <option value="USER" selected>사용자</option>
                <option value="GROUP">모임</option>
                <option value="SUBGROUP">소모임</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">신고 제출</button>
    </form>
    <div id="responseMessage" class="mt-3"></div>
</div>

<script>
    $(document).ready(function(){
        // 신고 유형 변경 시 신고 내용 기본값 설정
        $('#reportType').on('change', function(){
            var selectedType = $(this).val();
            if(selectedType === 'USER'){
                $('#reportContent').val('이 사용자는 스팸 메시지를 반복적으로 보냅니다.');
            } else if(selectedType === 'GROUP'){
                $('#reportContent').val('이 그룹은 부적절한 활동을 하고 있습니다.');
            } else if(selectedType === 'SUBGROUP'){
                $('#reportContent').val('이 소모임은 규칙을 위반하고 있습니다.');
            } else {
                $('#reportContent').val('');
            }
        });

        $('#reportForm').on('submit', function(event){
            event.preventDefault(); // 기본 폼 제출 방지

            // 폼 데이터 수집
            var formData = {
                userId: $('#userId').val(),
                reportTargetId: $('#reportTargetId').val(),
                reportContent: $('#reportContent').val(),
                reportType: $('#reportType').val()
            };

            // AJAX 요청 전송
            $.ajax({
                url: '/api/users/report', // 서버의 엔드포인트 URL로 변경하세요
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function(response){
                    if(response.success){
                        $('#responseMessage').html('<div class="alert alert-success">' + response.message + '</div>');
                        // 폼 초기화 (숨겨진 필드는 유지)
                        $('#reportForm')[0].reset();
                        // 기본값 설정
                        $('#reportType').val('USER');
                        $('#reportContent').val('이 사용자는 스팸 메시지를 반복적으로 보냅니다.');
                    } else {
                        $('#responseMessage').html('<div class="alert alert-danger">' + response.message + '</div>');
                    }
                },
                error: function(xhr, status, error){
                    $('#responseMessage').html('<div class="alert alert-danger">신고 중 오류가 발생했습니다.</div>');
                }
            });
        });
    });
</script>
</body>
</html>
