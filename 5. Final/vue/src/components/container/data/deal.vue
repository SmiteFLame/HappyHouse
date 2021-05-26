<template>
		<div class="row text-center">
			<div class="col-sm-3">
				<div class="row text-center"> 
					<div class="col-sm-1"/>
					<div class="col-sm-3">
					<select class="form-control" v-model="key" id="key">
              			<option value="all" selected>전체 검색</option>
              			<option value="no">거래번호</option>
              			<option value="AptName">아파트이름</option>
              		</select>
					</div><div class="col-sm-4">
					<input class="form-control" placeholder="Enter search word" type="text" v-model="word"/>
					</div><div class="col-sm-3">
					<button class="btn btn-secondary" @click="searchByKey()">검색</button>
					</div>
				</div>
				<br>
				<table class="table table-hover">
					<thead>
						<tr>
							<th style="width:20%">거래 번호</th>
							<th style="width:20%">동 이름</th>
							<th style="width:60%">아파트 이름</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(deal, index) in deals" :key="index">
							<td>{{deal.no}}</td>
							<td>{{deal.dong}}</td>
							<td @click="searchBoard(deal.no)">{{deal.aptName}}</td>
						</tr>
					</tbody>
					<tfoot>
						<th colspan='5' align='center'>
							<button class="btn" @click="pageLink(0)">{{pageLinks[0]}}</button>
							<button class="btn" @click="pageLink(1)">{{pageLinks[1]}}</button>
							<button class="btn" @click="pageLink(2)">{{pageLinks[2]}}</button>
							<button class="btn" @click="pageLink(3)">{{pageLinks[3]}}</button>
							<button class="btn" @click="pageLink(4)">{{pageLinks[4]}}</button>
						</th>
					</tfoot>
				</table>
			</div>
			<div class="col-sm-5">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="4">{{dealone.no}}번거래정보
								<img src="http://maps.google.com/mapfiles/ms/icons/blue-dot.png">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<!-- <div id="map" style="height: 500px"></div> -->
								<GmapMap ref="mapRef" :center="center" :zoom="16" style="height: 500px">
									
									<!-- 모범 음식점 -->
									<GmapMarker
        								:key="'goodres' + index2"
        								v-for="(m, index2) in markersGoodAddress"
        								:position="m.position"
        								@click="center=m.position"
										 icon="http://maps.google.com/mapfiles/ms/icons/red-dot.png"
										  data-toggle="popover"
										 :title="m.name"/>
      								/>
									
									<!-- 전기 자동차 -->
									<GmapMarker
        								:key="'evcharger' + index2"
        								v-for="(m, index2) in markersEvcharger"
        								:position="m.position"
        								@click="center=m.position"
										 icon="http://maps.google.com/mapfiles/ms/icons/green-dot.png"
										  data-toggle="popover"
										 :title="m.name"/>
      								/>

									  <!-- 학교 -->
									<GmapMarker
        								:key="'school' + index2"
        								v-for="(m, index2) in markersSchool"
        								:position="m.position"
        								@click="center=m.position"
										 icon="http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
										  data-toggle="popover"
										 :title="m.name"/>
      								/>

									   <!-- 문화시설 -->
									<GmapMarker
        								:key="'school' + index2"
        								v-for="(m, index2) in markersHeritage"
        								:position="m.position"
        								@click="center=m.position"
										 icon="http://maps.google.com/mapfiles/ms/icons/pink-dot.png"
										  data-toggle="popover"
										 :title="m.name"/>
      								/>

									<!-- 전체 -->
									<GmapMarker
        								:key="index"
        								v-for="(m, index) in markers"
        								:position="m.position"
        								@click="center=m.position"
										 icon="http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
										 data-toggle="popover"
										 :title="m.name"/>
      								/>
								</GmapMap>
								
							</td>
						</tr>
						<tr>
							<td colspan="2" style="width:50%"><b>아파트 이름</b></td>
							<td colspan="2"  style="width:50%"><b>거래 날짜</b></td>
						</tr>
						<tr>
							<td colspan="2">{{dealone.aptName}}</td>
							<td colspan="2">{{dealone.dealYear}}년 {{dealone.dealMonth}}월
								{{dealone.dealDay}}일</td>
						</tr>

						<tr>
							<td style="width:25%"><b>거래 금액</b></td>
							<td style="width:25%"><b>층수</b></td>
							<td style="width:25%"><b>건축 년도</b></td>
							<td style="width:25%"><b>건물 크기</b></td>
						</tr>
						<tr>
							<td>{{dealone.dealAmount }}</td>
							<td>{{dealone.floor }}</td>
							<td>{{dealone.buildYear }}</td>
							<td>{{dealone.area }}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2">{{dealone.dong}} 근처 모범 음식점
								<img src="http://maps.google.com/mapfiles/ms/icons/red-dot.png">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(mark, index) in markersGoodAddress" :key="index">
							<td @click="moveGoogleMap(mark.name)">{{mark.name}}</td>
						</tr>
					</tbody>
				</table>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>{{dealone.dong}} 근처 전기 자동차 충전소
								<img src="http://maps.google.com/mapfiles/ms/icons/green-dot.png">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(mark, index) in markersEvcharger" :key="index">
							<td @click="moveGoogleMap(mark.name)">{{mark.name}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div v-if="dealone"  class="col-sm-2">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>{{dealone.dong}} 근처 학교
								<img src="http://maps.google.com/mapfiles/ms/icons/yellow-dot.png">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(mark, index) in markersSchool" :key="index">
							<td @click="moveGoogleMap(mark.name)">{{mark.name}}</td>
						</tr>
					</tbody>
				</table>
				
				<table class="table table-hover">
					<thead>
						<tr>
							<th>{{dealone.dong}} 근처 문화 시설
								<img src="http://maps.google.com/mapfiles/ms/icons/pink-dot.png">
							</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(mark, index) in markersHeritage" :key="index">
							<td @click="moveGoogleMap(mark.name)">{{mark.name}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
</template>

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
<script type="text/javascript">
	import Vue from "vue";
	import axios from "axios";
	import {gmapApi} from 'vue2-google-maps'

	import Geocoder from "@pderas/vue2-geocoder";


	Vue.use(Geocoder, {
  		defaultCountryCode: "KR", // e.g. 'CA'
  		defaultLanguage: "ko", // e.g. 'en'
  		defaultMode: "lat-lng", // or 'lat-lng'
  		googleMapsApiKey: "AIzaSyAJbB62ROqMY8zYaObopG8mXy2BqSwESBk",
	});
	Vue.$geocoder.setDefaultMode('address'); 

	export default{
		name:"App",
		data(){
			return {
				key:"",
				word:" ",
				deals:[],
				dealone: {},
				bean:{},
				center:{
					lat:37.5013068,
					lng:127.037471
				},
				markers: [],
				markersGoodAddress: [],
				markersEvcharger: [],
				markersSchool: [],
				markersHeritage: [],
				places: [],
				pageLinks : [],
				
				};
		},
		computed:{
			google:gmapApi
		},
		methods: {
			pageMove(name){
				console.log(name);
			},
			searchAll(){
				
			axios
			.get("http://localhost:8675/happyhouse/api/map")
			.then(({data}) =>{
				this.deals = data;
				
				axios
				.get("http://localhost:8675/happyhouse/api/map/bean")
				.then(({data}) =>{
					this.bean = data;
					this.pageLinks = data.pageLink;
				})
				.catch(() =>{
					alert("오류 발생!!!");
				})
			})
			.catch(() =>{
				alert("오류 발생!!!");
			})
			
			
			},
			searchBoard(no){
				axios
				.get(`http://localhost:8675/happyhouse/api/map/${no}`)
				.then(({data}) =>{
					this.dealone = data;
					
					var addressObj = {
    					address_line_1: this.dealone.aptName,
    					address_line_2: this.dealone.aptName,
    					city:           this.dealone.dong,
						state:			'',
						zip_code:		'',
						country:		'korea'
					}
					var temp1Name = this.dealone.aptName
					Vue.$geocoder.send(addressObj, response => {
						this.center = response.results[0]['geometry']['location'];
						this.markers = [];
						this.markers.push({position : this.center, name : temp1Name});
					});

					// 모범음식점
					axios
					.get(`http://localhost:8675/happyhouse/api/map/near/goodres/${this.dealone.dong}`)
					.then(({data}) =>{
						
						this.markersGoodAddress = [];
						for(var i = 0, end = data.length; i < end; i++){
							var addressObj = {
    							address_line_1: data[i].name,
    							address_line_2: data[i].addr,
    							city:           this.dealone.dong,
								state:			'',
								zip_code:		'',
								country:		'korea'
							}
							let tempName = data[i].name;
							let tempAddr = data[i].addr;

							Vue.$geocoder.send(addressObj, response => {
								let temp = response.results[0]['geometry']['location'];
								this.markersGoodAddress.push({position : temp, name : tempName, addr :tempAddr});

							});	
						}

						
					})

					// 전기 자동차
					axios
					.get(`http://localhost:8675/happyhouse/api/map/near/evcharger/${this.dealone.dong}`)
					.then(({data}) =>{
						
						this.markersEvcharger = [];
						for(var i = 0, end = data.length; i < end; i++){
							var addressObj = {
    							address_line_1: data[i].name,
    							address_line_2: data[i].addr,
    							city:           this.dealone.dong,
								state:			'',
								zip_code:		'',
								country:		'korea'
							}
							let tempName = data[i].name;
							let tempAddr = data[i].addr;

							Vue.$geocoder.send(addressObj, response => {
								let temp = response.results[0]['geometry']['location'];
								this.markersEvcharger.push({position : temp, name : tempName, addr :tempAddr});
							});	
						}
						
					})

					// 학교
					axios
					.get(`http://localhost:8675/happyhouse/api/map/near/school/${this.dealone.dong}`)
					.then(({data}) =>{
						
						this.markersSchool = [];
						for(var i = 0, end = data.length; i < end; i++){
							var addressObj = {
    							address_line_1: data[i].name,
    							address_line_2: data[i].addr,
    							city:           this.dealone.dong,
								state:			'',
								zip_code:		'',
								country:		'korea'
							}
							let tempName = data[i].name;
							let tempAddr = data[i].addr;

							Vue.$geocoder.send(addressObj, response => {
								let temp = response.results[0]['geometry']['location'];
								this.markersSchool.push({position : temp, name : tempName, addr :tempAddr});
							});	
						}
						
					})

					// 문화유산
					axios
					.get(`http://localhost:8675/happyhouse/api/map/near/heritage/${this.dealone.dong}`)
					.then(({data}) =>{
						
						this.markersHeritage = [];
						for(var i = 0, end = data.length; i < end; i++){
							var addressObj = {
    							address_line_1: data[i].name,
    							address_line_2: data[i].addr,
    							city:           this.dealone.dong,
								state:			'',
								zip_code:		'',
								country:		'korea'
							}
							let tempName = data[i].name;
							let tempAddr = data[i].addr;

							Vue.$geocoder.send(addressObj, response => {
								let temp = response.results[0]['geometry']['location'];
								this.markersHeritage.push({position : temp, name : tempName, addr :tempAddr});
							});	
						}
						
					})


				})
				.catch(() =>{
					alert("오류 발생!!!");
				})

				
			},pageLink(no){
				axios
				.get(`http://localhost:8675/happyhouse/api/map/setbean/${this.pageLinks[no]}`)
				.catch(() =>{
					alert("오류 발생!!!");
				})
			
				this.searchAll();
			},searchByKey(){
				if(this.key == 'all'){
					this.word = "all"
				} 
				axios
				.get(`http://localhost:8675/happyhouse/api/map/bean/${this.key}/${this.word}`)
				this.searchAll();
				
			},
			moveGoogleMap(loc){
				window.open(`https://www.google.co.kr/maps/search/` + loc);
			}
		}, created(){
			this.key = 'all';
			this.word = 'all';
			this.searchAll();
		}
	}

</script>
