<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트 맵</title>
</head>
<body>
	<div class="container">
		<h2>HappyHouse</h2>
		<p>사이트맵</p>
		<div class="alert alert-success">
			<a class="nav-link"	href="${root}/">메인 홈페이지</a>
		</div>
		<div class="alert alert-info">
			<a class="nav-link"	href="${root}/deal">거래 검색</a>
		</div>
		<div class="alert alert-warning">
			<a class="nav-link"	href="${root}/mall">상가 검색</a>
		</div>
		<div class="alert alert-danger">
			<a class="nav-link"	href="${root}/corona">코로나 검사 위치</a>
		</div>
		<div class="alert alert-primary">
			<a class="nav-link"	href="${root}/login">로그인</a>
		</div>
		<div class="alert alert-secondary">
			<a class="nav-link"	href="${root}/join">회원가입</a>
		</div>
		<div class="alert alert-dark">
			<a class="nav-link"	href="${root}/list">공지사항</a>
		</div>
	</div>
</body>
</html>