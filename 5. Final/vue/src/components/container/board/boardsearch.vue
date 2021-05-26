<template>
  <div class="container">
    <h4 class="text-center">게시글 내용</h4>
    <table class="table table-condensed" v-if="board">
		
      <tr>
        <th style="width:10%">번호</th>
        <td style="width:40%" v-text="board.no"></td>
        <th style="width:10%">글쓴이</th>
        <td style="width:40%" v-text="board.name"></td>
      </tr>

      <tr>
        <th style="width:10%">제목</th>
        <td colspan="3" style="width:90%" v-text="board.title"></td>
      </tr>

      <tr>
        <td colspan="4" style="height:400px" v-text="board.info"></td>
      </tr>
    </table>

    <div class="text-center">
      <router-link class="btn btn-light" to="/boardlist">목록</router-link>&nbsp;
      <button class="btn btn-light"  v-if="username=='admin'" @click="update(board.no, board.name)">수정</button>&nbsp;
      <button class="btn btn-light"  v-if="username=='admin'" @click="remove()">삭제</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
	data(){
		return{
			board: {}
		}
	},
	computed:{
		...mapGetters(["username"])
	},methods:{
		remove(){
			 axios
      		.delete(`http://localhost:8675/happyhouse/api/board/${this.board.no}`)
      		.then(({ data }) => {
        	if (data == "success") {
          		alert("삭제 완료!!!");
          		this.$router.push("/boardlist");
        	} else {
          		alert("삭제중 오류 발생");
          		this.$router.push("/boardlist");
        		}
      		})
      		.catch(() => {
        		alert("삭제중 오류 발생");
          		this.$router.push("/boardlist");
     		 });
		},
		update(id, name){
			this.$router.push("/boardupdate?id=" + id + "&name="+ name);
		}
	},
	created(){
		axios
		.get(`http://localhost:8675/happyhouse/api/board/${this.$route.query.no}`)
		.then(({data}) =>{
			this.board = data;
		})
		.catch(() =>{
			alert("오류 발생!!!");
		})
	}
};
</script>
