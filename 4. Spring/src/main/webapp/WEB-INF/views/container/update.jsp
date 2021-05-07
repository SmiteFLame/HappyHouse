<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String root = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>

<script type="text/javascript">
	function remove() {
			document.getElementById("updateform").action = "remove";
			document.getElementById("updateform").submit();
	}
</script>
<script type="text/javascript">
	function update() {
		if (document.getElementById("userid").value == "") {
			alert("아이디 입력!!!");
			return;
		} else if (document.getElementById("userpwd").value == "") {
			alert("비밀번호 입력!!!");
			return;
		} else if (document.getElementById("userpwd2").value == "") {
			alert("비밀번호 일치 X!!!");
			return;
		} else if (document.getElementById("username").value == "") {
			alert("이름 입력!!!");
			return;
		} else if (document.getElementById("useremail").value == "") {
			alert("이메일 입력!!!");
			return;
		} else {
			document.getElementById("updateform").action = "update";
			document.getElementById("updateform").submit();
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<div class="card col-lg-6" align="center">
			<div class="card-header">
				<br>
				<h2>회원정보 수정/삭제</h2>
				<br>
			</div>
			<div class="card-body">
				<form id="updateform" method="post" action="post">
					<div class="form-group" align="left">
						<label for="">아이디</label> <input type="text" class="form-control"
							id="userid" name="userid" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">비밀번호</label> <input type="password"
							class="form-control" id="userpwd" name="userpwd" placeholder=""
							onkeydown="javascript:if(event.keyCode == 13) {join();}">
					</div>
					<div class="form-group" align="left">
						<label for="">비밀번호 확인</label> <input type="password"
							class="form-control" id="userpwd2" name="userpwd2" placeholder=""
							onkeydown="javascript:if(event.keyCode == 13) {join();}">
					</div>
					<div class="form-group" align="left">
						<label for="">이름</label> <input type="text" class="form-control"
							id="username" name="username" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">이메일</label> <input type="email" class="form-control"
							id="useremail" name="useremail" placeholder="">
					</div>
					<div class="form-group" align="center">
						<button type="button" class="btn btn-warning"
							onclick="javascript:update();">수정</button>
							
						<button type="button" class="btn btn-warning"
							onclick="javascript:remove();">삭제</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>