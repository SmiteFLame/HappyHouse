<template>
  <div class="container">
    <h4 class="text-center">게시글 내용</h4>
    <table class="table table-condensed" v-if="community">
		
      <tr>
        <th style="width:10%">번호</th>
        <td style="width:35%" v-text="community.no"></td>
        <th style="width:10%">글쓴이</th>
        <td colspan="2" style="width:45%" v-text="community.username"></td>
      </tr>

      <tr>
        <th style="width:10%">제목</th>
        <td colspan="4" style="width:90%" v-text="community.title"></td>
	  </tr>
      <tr>
        <td colspan="5" style="height:400px" v-text="community.content"></td>
      </tr>
	  
      <tr>
        <th colspan="3" style="width:10%">작성된 시간</th>
        <td colspan="2" style="width:90%" v-text="community.regtime"></td>
	  </tr>

      <tr>
		  <td class="text-center" colspan="5"></td>
      </tr>
      <tr>
        <td class="text-center" colspan="5"> 댓글</td>
      </tr>
	 
     <tr v-for="(comment, index) in comments" :key="index">
        <td v-text="comment.username"></td>
        <td colspan="2" v-text="comment.info"></td>
        <td v-text="comment.regtime"></td>
        <td>
			<button v-if="comment.username == username" class="btn btn-light" @click="deleteComment(comment.no)">X</button>
		</td>
     </tr>
	 <tr v-if="username">
        <td v-text="username"></td>
        <td colspan="3"><input type="text" class="form-control" v-model="newcomment" /></td>
        <td>
			<button class="btn btn-light" @click="addComment()">+</button>
		</td>
	 </tr>
    </table>

    <div class="text-center">
      <router-link class="btn btn-light" to="/communitylist">목록</router-link>&nbsp;
      <button class="btn btn-light"  v-if="username==community.username" @click="update(community.no)">수정</button>&nbsp;
      <button class="btn btn-light"  v-if="username==community.username" @click="remove()">삭제</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
	data(){
		return{
			community: {},
			comments:{},
			newcomment:"",
		}
	},
	computed:{
		...mapGetters(["username"]),
		...mapGetters(["userid"]),
	},methods:{
		remove(){
			 axios
      		.delete(`http://localhost:8675/happyhouse/api/community/${this.community.no}`)
      		.then(({ data }) => {
        	if (data == "success") {
          		alert("삭제 완료!!!");
          		this.$router.push("/communitylist");
        	} else {
          		alert("삭제중 오류 발생");
          		this.$router.push("/communitylist");
        		}
      		})
      		.catch(() => {
        		alert("삭제중 오류 발생");
          		this.$router.push("/communitylist");
     		 });
		},
		update(id){
			this.$router.push("/communityupdate?no=" + id);
		},
		addComment(){
		axios
          .post(`http://localhost:8675/happyhouse/api/community/comment`, {
            username: this.userid,
			cno: this.community.no,
			info: this.newcomment
          })
          .then(() => {
			alert("댓글이 등록되었습니다.");
			location.reload();
          })
          .catch(() => {
            alert("error 발생");
          });
		},
		deleteComment(no){
			axios
      		.delete(`http://localhost:8675/happyhouse/api/community/comment/${no}`)
      		.then(({ data }) => {
        	if (data == "success") {
          		alert("삭제 완료!!!");
				location.reload();
        	} else {
          		alert("삭제중 오류 발생");
        		}
      		})
      		.catch(() => {
        		alert("삭제중 오류 발생");
     		 });
		}
	},
	created(){
		axios
		.get(`http://localhost:8675/happyhouse/api/community/${this.$route.query.no}`)
		.then(({data}) =>{
			this.community = data;
		})
		.catch(() =>{
			alert("오류 발생!!!");
		})

		
		axios
		.get(`http://localhost:8675/happyhouse/api/community/comment/${this.$route.query.no}`)
		.then(({data}) =>{
			this.comments = data;
		})
		.catch(() =>{
			alert("오류 발생!!!");
		})
	}
};
</script>
