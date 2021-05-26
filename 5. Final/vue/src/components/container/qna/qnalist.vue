<template>
  <div class="container">

    <h4 class="text-center">자주 묻는 질문</h4>
    <div class="text-right">
      <button class="btn btn-light" v-if="username=='admin'" @click="movePage">등록</button>
    </div>
	<br>
    <div v-if="qnas.length > 0">
      <table class="table">
        <colgroup>
          <col width="50%" />
          <col width="50%" />
        </colgroup>
        <tr>
          <th>질문</th>
          <th>답변</th>
        </tr>
        <tr v-for="(qna, index) in qnas" :key="index">
          <td>{{ qna.question }}</td>
          <td>{{ qna.answer }}</td>
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
		  qnas:[],
		  bean:{},
		  pageLinks:[1, 2, 3, 4, 5]
	  }
  }, methods:{
	  movePage(){
          this.$router.push("/qnainsert");
	  },
	 searchAll(){
				
			axios
			.get("http://localhost:8675/happyhouse/api/qna")
			.then(({data}) =>{
				this.qnas = data;
				
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
			
			
			}
  }, created(){
	  this.searchAll()
	  }
  }
</script>
