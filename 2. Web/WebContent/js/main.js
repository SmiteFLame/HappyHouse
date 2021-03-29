
var menu = 1;
var search = 0;
var post = 0;
let map;
var markers = []
var geocoder;

$(document).ready(function() {
	// 시작하자마자 전체 데이터를 로드 해서 데이터를 업데이트
	loadDataHome(0, '');
	updateMainSite(0);
	geocoder = new google.maps.Geocoder();
	// 로그인 버튼
	$('#loginModal #login').click(function(){
		if(!$('#loginModal #loginID').val()){
			alert('아이디를 입력하세요')
			return;
		}
		if(!$('#loginModal #loginPassword').val()){
			alert('비밀번호를 입력하세요')
			return;
		}
		$.ajax({
			url:'json/personData.json',
			dataType:'json',
			success:function(jsonData){
				var check = false;
				$.each(jsonData,function(index, item){
					if(item.id == $('#loginModal #loginID').val()){
						check = true;
						if(item.password == $('#loginModal #loginPassword').val()){
							alert('로그인 완료');
							$('#header .loginX').each(function(index, item){
								$(item).css('display','none');
							});
							$('#header .loginO').each(function(index, item){
								$(item).css('display','');
							});
							$('#ID').text(item.name);
							return;
						} else{
							alert('비밀번호가 틀립니다');
							return;
						}
					}
				});
				if(check == false){
					alert("없는 아이디 입니다.")
					return;
				}
			}
		})
	});
	
	// 회원 가입 버튼
	$('#joinModal #join').click(function(){
		if(!$('#joinModal #usrID').val()){
			alert('아이디를 입력하세요')
			return;
		}
		if(!$('#joinModal #usrPassword').val()){
			alert('비밀번호를 입력하세요')
			return;
		}
		if(!$('#joinModal #usrPasswordCheck').val()){
			alert('비밀번호 확인를 입력하세요')
			return;
		}
		if(!$('#joinModal #usrName').val()){
			alert('이름를 입력하세요')
			return;
		}
		
		if($('#joinModal #usrPassword').val() != $('#joinModal #usrPasswordCheck').val()){
			alert('비밀번호가 일치하지 않습니다.')
			return;
		}
		
		$.ajax({
			url:'json/personData.json',
			dataType:'json',
			success:function(jsonData){
				var check = false;
				$.each(jsonData,function(index, item){
					if(item.id == $('#joinModal #usrID').val()){
						check = true;
						alert('아이디가 중복됩니다.')
					}
				});
				
				if(check == false){
					alert("회원가입 완료")
					return;
				}
			}
		})
	})
	
	// 회원 정보 수정
	$('#personUpdateModal #Update').click(function(){
		if(!$('#personUpdateModal #puID').val()){
			alert('아이디를 입력하세요')
			return;
		}
		if(!$('#personUpdateModal #puPassword').val()){
			alert('비밀번호를 입력하세요')
			return;
		}
		if(!$('#personUpdateModal #puPasswordCheck').val()){
			alert('비밀번호 확인를 입력하세요')
			return;
		}
		if(!$('#personUpdateModal #puName').val()){
			alert('이름를 입력하세요')
			return;
		}
		
		if($('#personUpdateModal #puPassword').val() != $('#personUpdateModal #puPasswordCheck').val()){
			alert('비밀번호가 일치하지 않습니다.')
			return;
		}
		
		$.ajax({
			url:'json/personData.json',
			dataType:'json',
			success:function(jsonData){
				var check = false;
				$.each(jsonData,function(index, item){
					if(item.id == $('#personUpdateModal #puID').val()){
						check = true;
					}
				});
				
				if(check == false){
					alert("아이디가 없습니다.")
					return;
				} else{
					alert("회원 정보 변경 완료")
					return;
				}
			}
		})
	})
	
	// 회원 정보 삭제
	$('#personDeleteModal #Update').click(function(){
		if(!$('#personDeleteModal #pdID').val()){
			alert('아이디를 입력하세요')
			return;
		}
		if(!$('#personDeleteModal #pdPassword').val()){
			alert('비밀번호를 입력하세요')
			return;
		}
		if(!$('#personDeleteModal #pdPasswordCheck').val()){
			alert('비밀번호 확인를 입력하세요')
			return;
		}
		
		if($('#personDeleteModal #pdPassword').val() != $('#personDeleteModal #pdPasswordCheck').val()){
			alert('비밀번호가 일치하지 않습니다.')
			return;
		}
		
		$.ajax({
			url:'json/personData.json',
			dataType:'json',
			success:function(jsonData){
				var check = false;
				$.each(jsonData,function(index, item){
					if(item.id == $('#personDeleteModal #pdID').val()){
						check = true;
					}
				});
				
				if(check == false){
					alert("아이디가 없습니다.")
					return;
				} else{
					alert("회원 정보 삭제 완료")
					return;
				}
			}
		})
	})
	// 로그 아웃 버튼
	$('#logout').click(function(){
		$('#header .loginX').each(function(index, item){
			$(item).css('display','');
		});
		$('#header .loginO').each(function(index, item){
			$(item).css('display','none');
		});
	});
	
	$('#findPasswordbtn').click(function(){
		if(!$('#findPassword #pfid').val()){
			alert('아이디를 입력하세요')
			return;
		}
		if(!$('#findPassword #pfemail').val()){
			alert('이메일을 입력하세요')
			return;
		}
		$.ajax({
			url:'json/personData.json',
			dataType:'json',
			success:function(jsonData){
				$.each(jsonData,function(index, item){
					var check = false;
					if(item.id == $('#findPassword #pfid').val()){
						if(item.email == $('#findPassword #pfemail').val()){
							alert(item.password);
						} else{
							alert("이메일이 일치하지 않습니다.")
						}
						check = true;
						return;
					}
				});
				if(check == false){
					alert("아이디가 없습니다.")
				}
				return;
			}
		})
	});
	
	
	// 검색기준 클릭

	
	// 검색기능
	$("#dataSearch").click(function(){
		clearMarkers();
		if(menu == 1){
			loadDataHome(search, $('#dataInput').val());
		} else if(menu == 2){
			loadDataStore(search, $('#dataInput').val());
		} else if(menu == 3){
			loadDataAir(search, $('#dataInput').val());
		} else if(menu == 4){
			loadDataHospital(search, $('#dataInput').val());
		}
	});

	// 버튼 입력시 검색 목록 변경
	var searchTitle = $('#search-title');
	$('#home-table').click(function() {
		clearMarkers();
		searchTitle.text('실거래가');
		loadDataHome(0,'');
		menu = 1;
		search = 0;
		$('#dropdown-menu').html(
		`<a class="dropdown-item searchStd" onclick=searchChange(0,'전체') href="#">전체</a> <a
									class="dropdown-item searchStd" onclick=searchChange(1,'동') href="#">동</a> <a
									class="dropdown-item searchStd" onclick=searchChange(2,'아파트') href="#">아파트</a> <a
									class="dropdown-item searchStd" onclick=searchChange(3,'거래종류') href="#">거래종류</a>`		
		);
	});

	$('#store-table').click(function() {
		clearMarkers();
		searchTitle.text('동네업종');
		loadDataStore(0,'');
		menu = 2;
		search = 0;
		$('#dropdown-menu').html(
				`<a class="dropdown-item searchStd" onclick=searchChange(0,'전체') href="#">전체</a> <a
									class="dropdown-item searchStd" onclick=searchChange(1,'판매 업종') href="#">판매업종</a> <a
									class="dropdown-item searchStd" onclick=searchChange(2,'가게이름') href="#">가게이름</a> <a
									class="dropdown-item searchStd" onclick=searchChange(3,'지역') href="#">지역</a>`		
		);
	});

	$('#air-table').click(function() {
		clearMarkers();
		searchTitle.text('대기오염');
		loadDataAir();
		menu = 3;
		search = 0;
		$('#dropdown-menu').html(
				`<a class="dropdown-item searchStd"onclick=searchChange(0,'전체') href="#">전체</a>
				<a class="dropdown-item searchStd" onclick=searchChange(1,'업종코드') href="#">업종코드</a>
				<a class="dropdown-item searchStd" onclick=searchChange(2,'처분대상') href="#">처분대상</a>
				<a class="dropdown-item searchStd" onclick=searchChange(3,'점검사항') href="#">점검대상</a>`		
		);
	});
	
	$('#hospital-table').click(function() {
		clearMarkers();
		searchTitle.text('코로나 검사');
		loadDataHospital(0,'');
		menu = 4;
		search = 0;
		$('#dropdown-menu').html(
				`<a class="dropdown-item searchStd"onclick=searchChange(0,'전체') href="#">전체</a>
				<a class="dropdown-item searchStd" onclick=searchChange(1,'종류') href="#">종류</a
				<a class="dropdown-item searchStd" onclick=searchChange(2,'의료기관명') href="#">의료기관명</a>
				<a class="dropdown-item searchStd" onclick=searchChange(3,'주소') href="#">주소</a>`		
		);
	});
	
	// 게시판 관련 기능
	$('.newPost').click(function(){
		updateInfoSite(1)
	})
	
})

function loadDataHome(idx, string) {
	// 전체 데이터 업데이트
	var html = `<tr><th>동</th><th>아파트이름</th><th>거래금액</th><th>건축년도</th><th>거래종류</th></tr>`;
	$('#mainTable_head').html(html);
	$('#mainTable_body').html(``);
	$.ajax({
		url:'data/AptDealHistory.xml',
		dataType:'xml',
		success : function(xmlData){
			if(idx != 3 || (idx == 3 && "아파트매매".indexOf(string) != -1)){
				var newHtml = ``;
				$('item',xmlData).each(function(index, item){
					if(idx == 0 || idx == 3 || (idx == 1 && $('법정동', item).text().indexOf(string)) != -1 || (idx == 2 && $('아파트', item).text().indexOf(string)!= -1)){
					newHtml +=`<tr>
						<td>${$('법정동', item).text()}</td>
						<td>${$('아파트', item).text()}</td>
						<td>${$('거래금액', item).text()}</td>
						<td>${$('건축년도', item).text()}</td>
						<td>아파트매매</td></tr>`
						geocodeAddress($('법정동', item).text() + $('아파트', item).text());
					}
				})
				$('#mainTable_body').html($('#mainTable_body').html() + newHtml);
			}
		}
	})
	$.ajax({
		url:'data/AptRentHistory.xml',
		dataType:'xml',
		success : function(xmlData){
			if(idx != 3 || (idx == 3 && "아파트전월세".indexOf(string) != -1)){
				var newHtml = ``;
				$('item',xmlData).each(function(index, item){
					if(idx == 0|| idx == 3 || (idx == 1 && $('법정동', item).text().indexOf(string)) != -1 || (idx == 2 && $('아파트', item).text().indexOf(string)!= -1)){
						newHtml +=`<tr>
							<td>${$('법정동', item).text()}</td>
							<td>${$('아파트', item).text()}</td>
							<td>${$('보증금액', item).text()}</td>
							<td>${$('건축년도', item).text()}</td>
							<td>아파트전월세</td></tr>`
							geocodeAddress($('법정동', item).text() + $('아파트', item).text());
					}
				})
				$('#mainTable_body').html($('#mainTable_body').html() + newHtml);
			}
		}
	})
	$.ajax({
		url:'data/HouseDealHistory.xml',
		dataType:'xml',
		success : function(xmlData){
			if(idx != 3 || (idx == 3 && "주택매매".indexOf(string) != -1)){
				var newHtml = ``;
				$('item',xmlData).each(function(index, item){
					if(idx == 0|| idx == 3 || (idx == 1 && $('법정동', item).text().indexOf(string))!= -1 || (idx == 2 && $('연립다세대', item).text().indexOf(string)!= -1)){					
						newHtml +=`<tr>
						<td>${$('법정동', item).text()}</td>
						<td>${$('연립다세대', item).text()}</td>
						<td>${$('거래금액', item).text()}</td>
						<td>${$('건축년도', item).text()}</td>
						<td>주택매매</td></tr>`
						geocodeAddress($('법정동', item).text() + $('연립다세대', item).text());
					}
				})
				$('#mainTable_body').html($('#mainTable_body').html() + newHtml);
			}
		}
	})
	$.ajax({
		url:'data/HouseRentHistory.xml',
		dataType:'xml',
		success : function(xmlData){
			if(idx != 3 || (idx == 3 && "주택전월세".indexOf(string) != -1)){
				
				var newHtml = ``;
					$('item',xmlData).each(function(index, item){
						if(idx == 0|| idx == 3 || (idx == 1 && $('법정동', item).text().indexOf(string))!= -1 || (idx == 2 && $('연립다세대', item).text().indexOf(string)!= -1)){
							newHtml +=`<tr>
							<td>${$('법정동', item).text()}</td>
							<td>${$('연립다세대', item).text()}</td>
							<td>${$('보증금액', item).text()}</td>
							<td>${$('건축년도', item).text()}</td>
							<td>주택전월세</td></tr>`
						}
						geocodeAddress($('법정동', item).text() + $('연립다세대', item).text());
				})
				$('#mainTable_body').html($('#mainTable_body').html() + newHtml);	
			}
		}
	})
	
}
function loadDataStore(idx, string) {
	var html = `<tr><th style="width:15%">판매 업종</th>
	<th style="width:20%">가게이름</th>
	<th style="width:15%">상세 분류</th>
	<th style="width:50%">지역</th></tr>`;
	$('#mainTable_head').html(html);
	$.ajax({
		url:'data/CommercialArea.csv',
		dataType:'text',
		success : function(csvData){
			var allRows = csvData.split(/\r?\n|\r/);
			var newHtml = ``;
			for(var i = 1, end = allRows.length; i < end - 1; i++){
				var temp = "";
				var check = true;

				var allCols = [];
				var j = 0;
				for(k = 0, end = allRows[i].length; j < end; j++) {
					if(allRows[i].charAt(j) == '"'){
						check = !check;
					}
					if(check && allRows[i].charAt(j) == ',') {
						if(temp.length > 0 && temp.charAt(0) == '"') {
							temp = temp.substring(1, temp.length - 2);
						}
						allCols[k++] = temp;
						temp = "";
					} else {
						temp += allRows[i].charAt(j);
					}
					if(k == 21) break;
				}

				if(idx == 0 || (idx == 1 && allCols[10].indexOf(string)!= -1) || (idx == 2 && allCols[1].indexOf(string)!= -1) || (idx == 3 && allCols[17].indexOf(string)!= -1)){
					
					newHtml += `<tr>
					<td>${allCols[10]}</td>
					<td>${allCols[1]}</td>
					<td>${allCols[8]}</td>
					<td>${allCols[17]}</td>`;
					var next = allRows[i].split(',');
					var lngt = Number(next[next.length - 2].substring(2, next[next.length - 2].length - 4));
					var latt = Number(next[next.length - 1].substring(2, next[next.length - 1].length - 4));

					var marker = new google.maps.Marker({
						position: {lat: latt, lng: lngt},
						map : map
					})
					markers.push(marker)
				    map.setCenter({lat: latt, lng: lngt});
				}
			}
			$('#mainTable_body').html(newHtml);
		},
		error:function(a,b,c){
			alert(c);
		}
	})
}


function loadDataAir(idx, string) {
	var html = `<tr><th style="width:20%">이름</th>
		<th style="width:20%">업종코드</th>
		<th style="width:10%">지도 점검 구분</th>
		<th style="width:10%">처분 대상 여부</th>
		<th style="width:20%">점검사항</th>
		<th style="width:20%">소재시주소</th></tr>`;
		$('#mainTable_head').html(html);
		$.ajax({
			url:'data/서울시 종로구 환경 지도점검 내역 현황.csv',
			contentType: "application/x-www-form-urlencoded; charset=euc-kr",
			dataType:'text',
			success : function(csvData){
				var allRows = csvData.split(/\r?\n|\r/);
				var newHtml = ``;
				for(var i = 1, end = allRows.length; i < end; i++){
					var allCols = allRows[i].split(',');
					newHtml += `<tr>
						<td>${allCols[0]}</td>
						<td>${allCols[1]}</td>
						<td>${allCols[3]}</td>
						<td>${allCols[7]}</td>
						<td>${allCols[8]}</td>
						<td>${allCols[6]}</td>`;
				}
				
				$('#mainTable_body').html(newHtml);
			}
		})
}

function loadDataHospital(idx, string) {
    let input = event.target;
    let reader = new FileReader();
	var html = `<tr>
		<th style="width:20%">종류</th>
		<th style="width:30%">의료기관명</th>
		<th style="width:30%">주소</th>
		<th style="width:20%">전화번호</th>`;
	$('#mainTable_head').html(html);
	$.ajax({
		url:'data/hospital.csv',
		dataType:'text',
		success : function(csvData){
			csvData = decodeURIComponent(csvData);
			var allRows = csvData.split(/\r?\n|\r/);
			var newHtml = ``;
			for(var i = 1, end = allRows.length; i < end; i++){
				var allCols = allRows[i].split(',');
				if(idx == 0 || (idx == 1 && "일반병원".indexOf(string)!= -1) || (idx == 2 && allCols[3].indexOf(string)!= -1) || (idx == 3 && allCols[4].indexOf(string)!= -1)){
					newHtml += `<tr>
					<td>일반병원</td>
					<td>${allCols[3]}</td>
					<td>${allCols[4]}</td>
					<td>${allCols[6]}</td>`;
					geocodeAddress(allCols[4]);
				}
			}
			
			$('#mainTable_body').html(newHtml);
		}
	})
	$.ajax({
	url:'data/선별진료소.csv',
	dataType:'text',
	success : function(csvData){
		csvData = decodeURIComponent(csvData);
		var allRows = csvData.split(/\r?\n|\r/);
		var newHtml = ``;
		for(var i = 1, end = allRows.length; i < end; i++){
			var allCols = allRows[i].split(',');
			if(idx == 0 || (idx == 1 && "진료소".indexOf(string)!= -1) || (idx == 2 && allCols[4].indexOf(string)!= -1) || (idx == 3 && allCols[5].indexOf(string)!= -1)){	
				newHtml += `<tr>
				<td>진료소</td>
				<td>${allCols[4]}</td>
				<td>${allCols[5]}</td>
				<td>${allCols[9]}</td>`;
				geocodeAddress(allCols[5]);
				}
			}
			$('#mainTable_body').html($('#mainTable_body').html() + newHtml);
		}
	})
}
function updateMainSite(idx){
	$('#showData').css('display','none');
	$('#personData').css('display','none');
	$('#mainInfo').css('display','none');
	$('#websiteInfo').css('display','none');
	$('#findPassword').css('display','none');
	$('#siteMap').css('display','none');
	if(idx == 0){
		$('#showData').css('display','');
	} else if(idx == 1){	
		$('#personData').css('display','');
		openPerson();
	} else if(idx == 2){
		$('#mainInfo').css('display','');	
		openTable();
	} else if(idx == 3){
		$('#websiteInfo').css('display','');	
	} else if(idx == 4){
		$('#findPassword').css('display','');	
	} else if(idx == 5){
		$('#siteMap').css('display','');	
	}
}

function openTable(){
	$.ajax({
		url:'json/post.json',
		dataType:'json',
		success:function(data){
			var html = ``;
			$.each(data,function(index, item){
				html += `<tr><td style="width:10%">${item.isbn}</td>
				<td style="width:50%"><a href="#" style="color:black" onclick=movePage(${item.isbn})>${item.title}</a></td>
				<td style="width:20%">${item.author}</td>
				<td style="width:10%">${item.click}</td>
				<td style="width:10%">${item.date}</td></tr>`
			})
			
			$('#inputTable').html(html);
		}
	})
	updateInfoSite(0)
}

function movePage(idx){
	$.ajax({
		url:'json/post.json',
		dataType:'json',
		success:function(data){
			$.each(data,function(index, item){
				console.log(item.isbn)
				if(idx == item.isbn){
					$('#Ttitle').text(item.title);
					$('#Tid').text(item.author);
					$('#Tcheck').text(item.check);
					$('#Tdate').text(item.date);
					$('#Tcontent').text(item.content);
				}
			})
			updateInfoSite(2);
		}
	})
}



function updateInfoSite(idx){
	$('#post-main').css('display','none');
	$('#post-write').css('display','none');
	$('#post-one').css('display','none');
	if(idx == 0){
		$('#post-main').css('display','');
	} else if(idx == 1){	
		$('#post-write').css('display','');
	} else if(idx == 2){
		$('#post-one').css('display','');	
	}
}


function openPerson(){
	$.ajax({
		url:'json/personData.json',
		dataType:'json',
		success:function(jsonData){
			var html = `<table class="table text-center"><thead>
				<tr>
				<th style="width:25%">ID</th>
				<th style="width:25%">Name</th>
				<th style="width:50%">E-mail</th>
				</tr>
				</thead>
				<tbody>
				`;
			$.each(jsonData,function(index, item){
				html += `<tr><th>${item.id}</th><th>${item.name}</th><th>${item.email}</th></tr>`;
			});
			html += `</tbody></table>`;
			$("#personDataList").html(html);
		}
	})
}

function searchChange(ser, tex){
	$('#searchStdName').text(tex);
	search = ser;
}


function initMap() {
	map = new google.maps.Map(document.getElementById("map"), {
		center: {
			lat: 37.5013068,
			lng: 127.037471
			},
		zoom: 13
	});
}

function geocodeAddress(string) {
  const address = string;
  geocoder.geocode({ address: address }, (results, status) => {
    if (status === "OK") {
      var mak = new google.maps.Marker({
        map: map,
        position: results[0].geometry.location,
      });
  	   markers.push(mak)
       map.setCenter(results[0].geometry.location);
    }
  });
}

function clearMarkers(){
	for(var i = 0, end = markers.length; i < end; i++){
		markers[i].setMap(null);
	}
}