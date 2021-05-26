<template>
  <div class="container">
    <h4 class="text-center">회원 정보 목록</h4>
    <div class="text-right">
      
      <button class="btn btn-light" v-if="username=='admin'" @click="movePage('join')">회원 정보 등록</button>
		
    </div>
	<br>
    <div v-if="members.length > 0">
      <table class="table">
        <colgroup>
          <col width="20%" />
          <col width="20%" />
          <col v-if="username=='admin'" width="30%" />
          <col v-else width="60%" />
          <col v-if="username=='admin'" width="15%" />
          <col v-if="username=='admin'" width="15%" />
        </colgroup>
        <tr class="text-center">
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
          <th v-if="username=='admin'" >수정</th>
          <th v-if="username=='admin'" >삭제</th>
        </tr>
        <tr class="text-center" v-for="(member, index) in members" :key="index">
          <td>{{ member.userid }}</td>
          <td>
            {{ member.username }}
          </td>
          <td v-text="member.emailid"></td>
          <td><button class="btn btn-light" v-if="username=='admin'" @click="movePage('update', member.userid)">회원 정보 수정</button></td>
		      <td><button class="btn btn-light" v-if="username=='admin'" @click="movePage('delete', member.userid)">회원 정보 삭제</button></td>

        </tr>
      </table>
    </div>
    <div v-else>회원 정보가 없습니다.</div>
					
  </div>
</template>
<script>
import axios from "axios";
import {mapGetters} from "vuex";
// import moment from "moment";

export default {
  name: "list",
	computed:{
		...mapGetters(["username"])
	},
  data(){
	  return{
		  key:"",
		  word:"",
		  members:[],
		  bean:{},
		  pageLinks:[1, 2, 3, 4, 5]
	  }
  }, methods:{
	  movePage(loc, id){
      if(loc == 'join'){
          this.$router.push("/join");
      } else if(loc == 'update'){
          this.$router.push("/memberupdate?no=" + id);
      } else if(loc == 'delete'){
        axios.
        delete(`http://localhost:8675/happyhouse/api/member/delete/${id}`)
        .then(({data}) => {
			    if(data == "success"){
			  	  alert("회원 삭제이 되었습니다.");
			    } else{
				    alert("?????");
			    }
			    this.$router.push('/');
        })
      }
	  },
	 searchAll(){
				
			axios
			.get("http://localhost:8675/happyhouse/api/member")
			.then(({data}) =>{
				this.members = data;
			})
			.catch(() =>{
				alert("오류 발생!!!");
			})
			
    axios
    .get("http://apis.data.go.kr/B552584/EvCharger/getChargerInfo?serviceKey=DkgyQnYgz1OBNzFJcXkEmTliNsW4tqD%2BWA7Q5yMvSZOBSuZcG8pVHdGeh%2Bk0Z6kqSQ44NJxM8Jk6Lk8yPUiQRw%3D%3D&pageNo=1&numOfRows=10&zcode=11")
    .then(res => {
      console.log(res);
    })
			
			}
  }, created(){
	  this.searchAll()
	  }
  }
</script>
