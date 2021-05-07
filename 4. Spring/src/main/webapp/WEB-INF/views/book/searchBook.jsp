<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set  var='root' value='${pageContext.request.contextPath}'/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
<link href="${root}/css/basic.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function a(){
		console.log('aaa');
	}
	window.onload = function(){
		var btn = document.getElementById("btn1");
		btn.addEventListener('click', function(){
			var isbn = document.getElementById("isbn");
			var btype = document.getElementById("booktype");
			var price = document.getElementById("price");
			if(isNaN(price.value)){
				alert('가격은 숫자로만 입력하세요')
				price.focus();
				return false;
			}
			if(isbn.value==''){
				alert('도서 번호는 반드시 입력해야합니다.')
				isbn.focus();
				return false;
			}
			if(btype.selectedIndex==0){
				alert('도서 종류를 선택하세요')
				btype.focus();
				return false;
			} 
			
			if(isbn.value.length <7){
				alert('도서 번호는 7자리 이상 입력하세요');
				isbn.focus();
				return false;
			}
			var frm = document.getElementById("frm");
			frm.submit();
		})
	}
</script>
</head>
<body>
<form id="frm" method="post"	action="insertBook.do">
<table align="center" cellspacing="0">
<tr><td colspan="2" class="caption"> 도서 등록 화면</td></tr>
<tr><td colspan="2" class="bpadding"> &nbsp;</td></tr>
<tr><td colspan="2" align="right" class="tbasic"> <img src="${root}/img/Indent_squareOrange.gif">표시가 된 항목은 필수 입력 항목입니다.</td></tr>
<tr><td colspan="2" class="tbasic tspacial"> &nbsp;</td></tr>
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareOrange.gif">도서번호</td>
	<td class="tbasic">${book.title}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareOrange.gif">도서제목</td>
	<td class="tbasic">${book.title}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">도서종류</td>
	<td class="tbasic">${book.catalogue}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">출판국가</td>
	<td class="tbasic">${book.nation}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">출 판 일</td>
	<td class="tbasic">${book.publish_date}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">출판사</td>
	<td class="tbasic">	${book.publisher}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareOrange.gif">저자</td>
	<td class="tbasic">${book.author}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">도서가격</td>
	<td class="tbasic">${book.price}${book.currency}</td>
</tr>	
<tr>
	<td class="tbasic"><img src="${root}/img/Indent_squareGray.gif">요약내용</td>
	<td class="tbasic tspacial" >
		<pre>${book.description}</pre>
	</td>
</tr>	
<tr><td colspan="2" class="bpadding"> &nbsp;</td></tr>
<tr><td colspan="2" align="center" class="tspacial tfoot">
 <a href="listBook.do">목록</a>
			<a href="removeBook.do?isbn=${book.isbn}">삭제</a>
			<a href="updateBookForm.do?isbn=${book.isbn}">수정</a>
 </td></tr>
</table>
</form>
</body>
</html>