<template>
	<div class="container text-center">
		<h2>HappyHouse</h2>
		<p>사이트맵</p>
		<div class="alert alert-success">
			<router-link class="nav-link" to="/boardlist">공지사항</router-link>
		</div>
		<div class="alert alert-info">
			<router-link class="nav-link" to="/deal">거래검색</router-link>
		</div>
		<div class="alert alert-warning">
			<router-link class="nav-link" to="/comlist">커뮤니티</router-link>
		</div>
		<div class="alert alert-danger">
			<router-link class="nav-link" to="/qnalist">Q&A 게시판</router-link>
		</div>
		<div class="alert alert-primary">
			<router-link class="nav-link" to="/sitemap">사이트맵</router-link>
		</div>
		<div class="alert alert-secondary">
			<router-link class="nav-link" to="/member">회원정보</router-link>
		</div>
		<div class="alert alert-dark">
			<router-link class="nav-link" to="/">메인홈페이지</router-link>
		</div>
	</div>
</template>
