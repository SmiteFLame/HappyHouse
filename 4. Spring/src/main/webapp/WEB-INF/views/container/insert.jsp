<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();

System.out.print(root);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SSAFY-글작성</title>
<meta charset="utf-8">
<script type="text/javascript">
	function insertProduct() {
		if (document.getElementById("name").value == "") {
			alert("품목 필수 입력!");
			return;
		} else if (document.getElementById("price").value == "") {
			alert("가격 필수 입력!");
			return;
		} else if (document.getElementById("info").value == "") {
			alert("설명 필수 입력!");
			return;
		} else {
			document.getElementById("insertform").submit();
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<div class="card col-lg-6" align="center">
			<div class="card-header">
				<br>
				<h2>글 작성하기</h2>
				<br>
			</div>
			<div class="card-body">
				<form id="joinform" method="post" action="post">
					<div class="form-group" align="left">
						<label for="">글 제목</label> <input type="text" class="form-control"
							id="userid" name="userid" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">글 내용</label> <input type="text" class="form-control"
							id="userid" name="userid" placeholder="">
					</div>
					<div class="form-group" align="center">
						<button type="button" class="btn btn-warning"
							onclick="javascript:join();">글 작성</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
