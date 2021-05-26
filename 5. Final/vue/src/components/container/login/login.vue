<template>
	<div class="container" align="center">
		<div class="card col-lg-12" align="center">
			<div class="row" style="height:600px">
				<div class="card-header col-sm-6">
					<br><br>
					<h2>로그인</h2>
					<img src="../../../assets/img/logo.png" style="width:400px; height:360px;  margin-top:-20px; margin-bottom:-25px">
				</div>
				<div class="card-body col-sm-6" style="margin:auto">
					<form id="loginform" method="post" action="post">
						<div class="form-group" align="left">
							<label for="">아이디</label> <input type="text" class="form-control"
								id="userid" ref="userid" v-model="userid" placeholder="">
						</div>
						<br>
						<div class="form-group" align="left">
							<label for="">비밀번호</label> <input type="password"
								class="form-control" id="userpwd" ref="userpwd" v-model="userpwd" placeholder=""
								onkeydown="javascript:if(event.keyCode == 13) {login();}">
						</div>
						<br>
						<div class="form-group" align="center">
							<button type="button" class="btn btn-warning"
								@click="login">로그인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
	computed:{
		...mapGetters(["storage"])
	},
	data(){
		return {
			userid: "",
			userpwd: "",
			token: "",
			info:""
		};
	},
	methods:{
		setInfo(status, token, info){
			this.status = status;
			this.token = token;
			this.info = info;
		},
		login(){
			let err = true;
			let msg = "";
			!this.userid && ((msg = "작성자를 입력해 주세요"), (err = false), this.$refs.userid.focus());
			err && !this.userpwd && ((msg = "작성자를 입력해 주세요"), (err = false), this.$refs.userpwd.focus());
			if(!err){
				alert(msg);
			} else{
				
			this.$store.commit('setStorageJ', "");
			this.$store.commit('setStorageL', "");
				axios
				.post(`http://localhost:8675/happyhouse/api/member/login`, {
					userid: this.userid,
					userpwd: this.userpwd
				})
          		.then(res => {
					  if(res.data.status){
						//   console.dir(res.header["jwt-auth-token"]);
						  this.setInfo(
							  "성공",
							  res.headers["jwt-auth-token"],
							  JSON.stringify(res.data.data)
						  );
						  
						this.$store.commit('setStorageJ', res.headers["jwt-auth-token"]);
						this.$store.commit('setStorageL', res.data.data.username);
						this.$store.commit('setuserid', res.data.data.userid);
						this.$router.push("/")
						location.reload();
					  }
          		})
          		.catch(() => {
            		alert("아이디와 비밀번호를 확인해주세요");
          		});
			}
		}
	}
}
</script>

