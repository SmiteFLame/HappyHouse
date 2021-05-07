<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Happy House</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- header start-->
	<jsp:include page="/WEB-INF/views/header/title.jsp" />
	<jsp:include page="/WEB-INF/views/header/menu.jsp" />
	<!-- header end-->


	<!-- main start -->
	<c:choose>
		<c:when test="${container eq 'login'}">
			<jsp:include page="/WEB-INF/views/container/login.jsp" />
		</c:when>
		<c:when test="${container eq 'join'}">
			<jsp:include page="/WEB-INF/views/container/join.jsp" />
		</c:when>
		<c:when test="${container eq 'home'}">
			<jsp:include page="/WEB-INF/views/container/home.jsp" />
		</c:when>
		<c:when test="${container eq 'boardlist'}">
			<jsp:include page="/WEB-INF/views/container/boardlist.jsp" />
		</c:when>
		<c:when test="${container eq 'listone'}">
			<jsp:include page="/WEB-INF/views/container/listone.jsp" />
		</c:when>
		<c:when test="${container eq 'sitemap'}">
			<jsp:include page="/WEB-INF/views/container/sitemap.jsp" />
		</c:when>
		<c:when test="${container eq 'dataList'}">
			<jsp:include page="/WEB-INF/views/container/dataList.jsp" />
		</c:when>
		<c:when test="${container eq 'write'}">
			<jsp:include page="/WEB-INF/views/container/write.jsp" />
		</c:when>
		<c:when test="${container eq 'joinsuccess'}">
			<jsp:include page="/WEB-INF/views/container/joinsuccess.jsp" />
		</c:when>
		<c:when test="${container eq 'updatesuccess'}">
			<jsp:include page="/WEB-INF/views/container/updatesuccess.jsp" />
		</c:when>
		<c:when test="${container eq 'deletesuccess'}">
			<jsp:include page="/WEB-INF/views/container/deletesuccess.jsp" />
		</c:when>
		<c:when test="${container eq 'update'}">
			<jsp:include page="/WEB-INF/views/container/update.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/views/container/home.jsp" />
		</c:otherwise>
	</c:choose>
	<!-- main end -->


	<!-- footer start -->
	<jsp:include page="/WEB-INF/views/footer/footer.jsp" />
	<!-- footer end -->

</body>
</html>





