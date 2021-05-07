<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<!DOCTYPE html>
<html lang="ko">
<head>
  <title>상품 등록 성공</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
 </head>
<body>


<div class="container" align="center">
	<c:choose>
		<c:when test="${not empty listone }">
			<table class="table table-hover">
				<tbody>
					<tr>
						<td> <b>글 번호</b>
						</td>
						<td> ${listone.no}
						</td>
						<td> <b>작성자</b>
						</td>
						<td> ${listone.name}
						</td>
					</tr>
					
					<tr>
						<td colspan="1"> <b>제목</b>
						</td>
						<td colspan="3"> ${listone.title}
						</td>
					</tr>
					
					<tr height="150">
						<td colspan="1"> <b>내용</b>
						</td>
						<td colspan="3"> ${listone.info}
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			 <div class="col-lg-6"><span>${msg}</span></div>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>








