<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value='${pageContext.request.contextPath}' />
<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">
	$(function() {
		//검색 버튼에 이벤트 연결
		$('#btn').click(function() {
			pagelist(1);
		})
		<c:if test='${not empty param.key}'>
		$('#key').val('${param.key}')
		</c:if>
	})
	function pagelist(cpage) {
		//input 양식의 hidden으로 선언된 page에 요청된 페이지 정보 셋팅 
		$("#pageNo").val(cpage);
		var frm = $("#frm");
		frm.attr('action', "${root}/list");
		frm.submit();
	}
</script>

</head>
<body>
	<div class="container">
		<h2>공지사항</h2>
		<form class="form-inline" id='frm'>
			<input type='hidden' name='pageNo' id='pageNo'> <select
				class="form-control" name='key' id='key'>
				<option value='all'>--선택하세요--</option>
				<option value='title'>제목</option>
				<option value='name'>작성자</option>
			</select> <input type="text" class="form-control"
				placeholder="Enter search word" id="word" name='word'
				value='${bean.word}'> <a href='#' class="btn btn-primary"
				id='btn'>검색</a>
		</form>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>글 번호</th>
					<th>글 제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty board }">
						<tr>
							<td colspan="3">조회할 상품 목록이 없습니다.</td>
					</c:when>
					<c:otherwise>
						<c:forEach var="board" items="${board}">
							<tr>
								<td>${board.no}</td>
								<td><a href="${root}/listno?no=${board.no}">${board.title}</a></td>
								<td>${board.name}</td>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<th colspan='3' align='center'>${bean.pageLink}</th>
				</tr>
			</tfoot>
		</table>
		<button onclick="location.href='${root}/write'" type="button" class="btn btn-primary">글 작성</button>
	</div>
</body>
</html>
