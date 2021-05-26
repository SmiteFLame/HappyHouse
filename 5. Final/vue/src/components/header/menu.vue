<template>
	<div>
	<nav class="navbar navbar-expand-sm bg-light navbar-light fixed-top">
		<!-- <a class="navbar-brand" href="${root}/">HappyHouse</a> -->
		<router-link to="/"><img src="../../assets/img/logo.png" style="width:100px; height:80px;  margin-top:-20px; margin-bottom:-25px"></router-link>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo02"
			aria-controls="navbarTogglerDemo02" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item"><router-link class="nav-link" to="/boardlist">공지사항</router-link></li>

				<li class="nav-item"><router-link class="nav-link" to="/deal">거래 검색</router-link></li>

				<li class="nav-item"><router-link class="nav-link" to="/communitylist">커뮤니티</router-link></li>

				<li class="nav-item"><router-link class="nav-link" to="/qnalist">Q&A 게시판</router-link></li>

				<li class="nav-item"><router-link class="nav-link" to="/sitemap">사이트맵</router-link></li>
			</ul>

			<ul class="navbar-nav my-2 my-lg-0">
				<li class="nav-item"><router-link class="nav-link" to="/login" v-if="username == ''">로그인</router-link></li>
				<li class="nav-item"><router-link class="nav-link" to="/join" v-if="username == ''">회원가입</router-link></li>
				
				<li class="nav-item"><a href="#" class="nav-link" @click="logout" v-if="username != ''">로그아웃</a></li>
				<li class="nav-item"><router-link class="nav-link" to="/join" v-if="username != ''">{{username}}님 환영합니다</router-link></li>

				
				<li class="nav-item"><router-link class="nav-link" to="/member" v-if="username =='admin'"> 회원 관리 </router-link></li>
				
			</ul>

		</div>
	</nav>
	</div>
</template>

<script>
import {mapGetters} from "vuex";

export default{
	computed:{
		...mapGetters(["storage"]),
		...mapGetters(["username"])
	},methods:{
		logout(){
			alert("로그 아웃 완료");
			this.$store.commit('setStorageJ', "");
			this.$store.commit('setStorageL', "");
			this.$store.commit('setuserid', "");
			location.reload();
		}
	}
}

</script>