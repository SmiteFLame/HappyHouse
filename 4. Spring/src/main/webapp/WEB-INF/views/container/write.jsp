<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
  <script type="text/javascript">
  function write() {
	if(document.getElementById("title").value == "") {
		alert("제목 필수 입력!");
		return;
	}else if(document.getElementById("info").value == "") {
		alert("내용 필수 입력!");
		return;
	} else {
	  	document.getElementById("insertform").submit();
	}
  }
  </script>
</head>
<body>
<div class="container" align="center">
	<div class="col-lg-6">
		<h2>글 작성하기</h2>
		<form id="insertform" method="post" action="insertProduct">
			<div class="form-group" align="left">
				<label for="type">제목</label>
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="form-group" align="left">
				<label for="type">작성자</label>
				<input type="text" class="form-control" id="name" name="name" disabled/>
			</div>
			<div class="form-group" align="left">
				<label for="desc">내용</label>
				<input type="text" class="form-control" id="info" name="info">
			</div>
			<button type="button" class="btn btn-primary" onclick="javascript:write();">작성</button>
			<button type="reset" class="btn btn-warning">초기화</button>
		</form>
	</div>
</div>

</body>
</html>