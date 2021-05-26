<template>
	<div class="container" align="center">
		<div class="card col-lg-12" align="center">
			<div class="row" style="height:600px">
		  	<div class="card-header col-sm-6">
		  		<br>
		  		<h2>회원정보 수정</h2>
		  		<img src="../../../assets/img/logo.png" style="width:400px; height:360px;  margin-top:-20px; margin-bottom:-25px">
		  	</div>
		  	<div class="card-body col-sm-6" style="margin:auto">
		  		<form id="joinform" method="post" action="post">
		  			<div class="form-group" align="left">
		  				<label for="">아이디</label> <input type="text" class="form-control"
		  					id="userid" ref="userid" v-model="userid" val="userid" readonly>
		  			</div>
		  			<div class="form-group" align="left">
		  				<label for="">비밀번호</label> <input type="password"
		  					class="form-control" ref="userpwd" id="userpwd" v-model="userpwd">
		  			</div>
		  			<div class="form-group" align="left">
		  				<label for="">비밀번호 확인</label> <input type="password"
		  					class="form-control" ref="userpwd2" id="userpwd2"  v-model="userpwd2">
		  			</div>
		  			<div class="form-group" align="left">
		  				<label for="">이름</label> <input type="text" class="form-control"
		  					id="username" ref="username" v-model="username" placeholder="">
		  			</div>
		  			<div class="form-group" align="left">
		  				<label for="">이메일</label> <input type="email" class="form-control"
		  					id="emailid" ref="emailid" v-model="emailid" placeholder="">
		  			</div>
		  			<div class="form-group" align="center">
		  				<button type="button" class="btn btn-warning"
		  					@click="createHandler">회원 정보 수정</button>
		  			</div>
		  		</form>
		  	</div>
		  </div>
    </div>
	</div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
		userid:"",
		userpwd:"",
		userpwd2:"",
		username:"",
		emailid:"",
    };
  },
  methods: {
    createHandler() {
      //데이타 검증
      let err = true;
      let msg = "";

      !this.userid && ((msg = "아이디를 입력해 주세요"), (err = false), this.$refs.userid.focus());
      err &&
        !this.userpwd &&
        ((msg = "비밀번호을 입력해 주세요"), (err = false), this.$refs.userpwd.focus());
      err &&
        !this.userpwd2 &&
        ((msg = "비밀번호 확인을 입력해 주세요"), (err = false), this.$refs.userpwd2.focus());
      err &&
        this.userpwd != this.userpwd2 &&
        ((msg = "비밀번호가 서로 다릅니다."), (err = false), this.$refs.userpwd2.focus());
      err &&
        !this.username &&
        ((msg = "이름을 입력해 주세요"), (err = false), this.$refs.username.focus());
      err &&
        !this.emailid &&
        ((msg = "내용을 입력해 주세요"), (err = false), this.$refs.emailid.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .put(`http://localhost:8675/happyhouse/api/member/update`, {
            userid: this.userid,
            userpwd: this.userpwd,
            username: this.username,
            emilid: this.emailid,

          })
          .then(({data}) => {
			  if(data == "success"){
			  	alert("회원 수정이 되었습니다.");
			  } else{
				alert("?????");
			  }
			this.$router.push('/');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/boardlist");
    }
  }, created(){
    this.userid = this.$route.query.no;
  }
}
</script>
