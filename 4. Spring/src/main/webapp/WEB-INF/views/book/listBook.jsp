<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table	align="center">
		<caption> 책 목록 </caption>
		<thead>
			<tr><td colspan="4" align="right"><a href="insertBookForm.do">책 등록 하기</a></td></tr>	
			<tr><td width="150">isbn</td>
				<td width="150">제목</td>
				<td width="100">저자</td>
				<td width="100">출판사</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach  var="book"		items="${list}">
				<tr>
					<td width="150">${book.isbn}</td>
					<td width="150"><a href="searchBook.do?isbn=${book.isbn}">${book.title}</a></td>
					<td width="100">${book.author}</td>
					<td width="100">${book.publisher}</td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
</body>
</html>