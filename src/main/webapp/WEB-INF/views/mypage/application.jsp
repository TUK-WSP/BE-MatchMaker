<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4>나의 모집 신청</h4>
<table class="table">
  <thead>
  <tr>
    <th>모집 제목</th>
    <th>상태</th>
    <th>취소</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="application" items="${applications}">
    <tr>
      <td>${application.title}</td>
      <td>${application.status}</td>
      <td>
        <button class="btn btn-danger btn-sm" onclick="cancelApplication('${application.id}')">취소</button>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script>
  function cancelApplication(appId) {
    $.ajax({
      url: "/mypage/application/cancel/" + appId,
      type: "DELETE",
      success: function() {
        alert("신청이 취소되었습니다.");
        location.reload();
      },
      error: function() {
        alert("취소 실패");
      }
    });
  }
</script>
