<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value='${pageContext.request.contextPath}' />
<!DOCTYPE html>
<html>
<head>
<title>근처 상가 조회</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJbB62ROqMY8zYaObopG8mXy2BqSwESBk&callback=initMap&libraries=&v=weekly"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		var nowlat = "${location.lat}" * 1;
		var nowlng = "${location.lng}" * 1;
		if(nowlat == 0){
			nowlat = 37.5013068;
			nowlng = 127.037471;
		}
		map = new google.maps.Map(document.getElementById("map"), {
			center: {
				lat: nowlat,
				lng: nowlng
				},
			zoom: 15
		});

		if("${location.dong}" != ""){
			console.log("A");
			var mak = new google.maps.Marker({
				map: map,
				position: new google.maps.LatLng(nowlat, nowlng)
			})
		} 
	})
	
	

	function pagelist(cpage) {
		console.log(cpage);
		//input 양식의 hidden으로 선언된 page에 요청된 페이지 정보 셋팅 
		$("#pageNo").val(cpage);
		var frm = $("#frm");
		frm.attr('action', "${root}/deal");
		frm.submit();
	}
</script>
<style>
#map {
	height: 100%;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
		<div class="row text-center">
			<div class="col-sm-4">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>거래 번호</th>
							<th>동 이름</th>
							<th colspan="2">아파트 이름</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty deallist}">
								<tr>
									<td colspan="3">조회할 상품 목록이 없습니다.</td>
							</c:when>
							<c:otherwise>
								<c:forEach var="deal" items="${deallist}">
									<tr>
										<td>${deal.no}</td>
										<td>${deal.dong}</td>
										<td colspan="2"><a href="${root}/dealno?no=${deal.no}">${deal.aptName}</a>
										</td>
									</tr>
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

				<form class="form-inline" id='frm' style="margin-left:100px">
					<input type='hidden' name='pageNo' id='pageNo'> <select
						class="form-control" name='key' id='key'>
						<c:choose>
							<c:when test="${bean.key eq 'no'}">
								<option value='all'>--선택하세요--</option>
								<option value='no' selected>거래 번호</option>
								<option value='AptName'>아파트 이름</option>
							</c:when>
							<c:when test="${bean.key eq 'AptName'}">
								<option value='all'>--선택하세요--</option>
								<option value='no'>거래 번호</option>
								<option value='AptName' selected>아파트 이름</option>
							</c:when>
							<c:otherwise>
								<option value='all'>--선택하세요--</option>
								<option value='no'>거래 번호</option>
								<option value='AptName'>아파트 이름</option>
							</c:otherwise>
						</c:choose>
					</select> <input type="text" class="form-control"
						placeholder="Enter search word" id="word" name='word'
						value='${bean.word}'>
					<button type="button" class="btn btn-primary"
						onclick="javascript:pagelist(1);">검색</button>
				</form>
			</div>
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="4">${dealone.no}번거래정보</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="map" style="height: 500px"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><b>아파트 이름</b></td>
							<td colspan="2"><b>거래 날짜</b></td>
						</tr>
						<tr>
							<td colspan="2">${dealone.aptName }</td>
							<td colspan="2">${dealone.dealYear }년 ${dealone.dealMonth}월
								${dealone.dealDay}일</td>
						</tr>

						<tr>
							<td><b>거래 금액</b></td>
							<td><b>층수</b></td>
							<td><b>건축 년도</b></td>
							<td><b>건물 크기</b></td>
						</tr>
						<tr>
							<td>${dealone.dealAmount }</td>
							<td>${dealone.floor }</td>
							<td>${dealone.buildYear }</td>
							<td>${dealone.area }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>
