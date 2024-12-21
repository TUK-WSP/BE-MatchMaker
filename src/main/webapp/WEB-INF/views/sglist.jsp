<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>소모임 조회</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/subgroup">MatchMaker 소모임</a>
  </div>
</nav>

<div class="container mt-5">
  <h1>소모임 조회</h1>

  <!-- 검색 폼 -->
  <form method="get" action="/subgroup/read/sglist" class="mb-4">
    <div class="input-group">
      <input type="text" name="query" value="${query}" class="form-control" placeholder="소모임 검색">
      <button type="submit" class="btn btn-primary">검색</button>
    </div>
  </form>

  <!-- 검색 결과 메시지 -->
  <div class="alert alert-info">
    ${message}
  </div>

  <div class="row">
    <!-- 소모임 목록 -->
    <c:forEach var="subGroup" items="${subGroups}">
      <div class="col-md-4 mb-4">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">${subGroup.subgroupName}</h5>
            <p class="card-text">${subGroup.description}</p>
            <a href="/subgroup/read/sgdetail/${subGroup.subgroupId}" class="btn btn-primary">소모임 상세 보기</a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<footer class="bg-light text-center py-3 mt-5">
  <p>&copy; 2024 MatchMaker. All Rights Reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>