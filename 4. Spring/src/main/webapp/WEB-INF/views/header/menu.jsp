<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String root = request.getContextPath();
System.out.println(root);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="${root}/">HappyHouse</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo02"
			aria-controls="navbarTogglerDemo02" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item"><a class="nav-link" href="${root}/deal">거래 검색</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/mall">상가 검색</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/corona">코로나 검사 위치</a></li>
			</ul>

			<ul class="navbar-nav my-2 my-lg-0">

				<c:choose>
					<c:when test="${empty userinfo}">
						<li class="nav-item"><a class="nav-link"
							href="${root}/loginPage">로그인</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${root}/joinPage">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="${root}/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${root}/update">${userinfo.username}님</a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link" href="${root}/boardlist">공지사항</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/sitemap">사이트맵</a></li>
			</ul>

		</div>
	</nav>
	<br>
	<br>
</body>
</html>