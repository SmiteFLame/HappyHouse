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
<title>Login</title>

<script type="text/javascript">
	function login() {
		if (document.getElementById("userid").value == "") {
			alert("아이디 입력!!!");
			return;
		} else if (document.getElementById("userpwd").value == "") {
			alert("비밀번호 입력!!!");
			return;
		} else {
			document.getElementById("loginform").action = "login";
			document.getElementById("loginform").submit();
		}
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<div class="card col-lg-6" align="center">
			<div class="card-header">
				<br>
				<h2>로그인</h2>
				<br>
			</div>
			<div class="card-body">
				<form id="loginform" method="post" action="post">
					<div class="form-group" align="left">
						<label for="">아이디</label> <input type="text" class="form-control"
							id="userid" name="userid" placeholder="">
					</div>
					<div class="form-group" align="left">
						<label for="">비밀번호</label> <input type="password"
							class="form-control" id="userpwd" name="userpwd" placeholder=""
							onkeydown="javascript:if(event.keyCode == 13) {login();}">
					</div>
					<div class="form-group" align="center">
						<button type="button" class="btn btn-warning"
							onclick="javascript:login();">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>