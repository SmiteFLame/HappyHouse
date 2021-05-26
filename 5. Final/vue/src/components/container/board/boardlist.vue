<template>
  <div class="container">
    <h4 class="text-center">공지사항 목록</h4>
    <div class="text-right">
      <button class="btn btn-light" v-if="username=='admin'" @click="movePage">등록</button>
    </div>
	<br>
    <div v-if="boards.length > 0">
      <table class="table">
        <colgroup>
          <col width="15%" />
          <col width="65%" />
          <col width="20%" />
        </colgroup>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
        </tr>
        <tr v-for="(board, index) in boards" :key="index">
          <td>{{ board.no }}</td>
          <td>
            <router-link :to="'boardsearch?no=' + board.no">{{ board.title }}</router-link>
          </td>
          <td v-text="board.name"></td>
        </tr>
      </table>
    </div>
    <div v-else>게시글이 없습니다.</div>
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
		  boards:[],
		  bean:{},
		  pageLinks:[1, 2, 3, 4, 5]
	  }
  }, methods:{
	  movePage(){
          this.$router.push("/boardinsert");
	  },
	 searchAll(){
				
			axios
			.get("http://localhost:8675/happyhouse/api/board")
			.then(({data}) =>{
				this.boards = data;
				
				axios
				.get("http://localhost:8675/happyhouse/api/board/bean")
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
			
			
			}
  }, created(){
	  this.searchAll()
	  }
  }
</script>
