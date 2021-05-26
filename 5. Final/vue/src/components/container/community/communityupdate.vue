<template>
  <div class="container">
    <h2 class="text-center">게시글 수정</h2>
    <table class="table table-condensed">
      <tr>
        <th>제목</th>
        <td>
          <input type="text" class="form-control" v-model="title" ref="title" id="title" value="community.title" placeholder="제목을 입력해주세요">
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea  class="form-control" id="info" v-model="info" ref="info" cols="30" rows="10"  value="community.content"></textarea>
        </td>
      </tr>
    </table>

    <div class="text-center">
      <button class="btn btn-light" @click="createHandler">수정</button>&nbsp;
      <button class="btn btn-light" @click="moveHandler">목록</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
			community: {},
      id: "",
      name: "",
      title: "",
      info: ""
    };
  },
  methods: {
    createHandler() {
      //데이타 검증
      let err = true;
      let msg = "";

      !this.title &&
        ((msg = "제목을 입력해 주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.info &&
        ((msg = "내용을 입력해 주세요"), (err = false), this.$refs.info.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .put(`http://localhost:8675/happyhouse/api/community`, {
            id: this.id,
            name: this.name,
            title: this.title,
            info: this.info
          })
          .then(() => {
			  alert("게시글이 수정되었습니다.");
			this.$router.push('/communitylist');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/communitylist");
    },
  },
  created() {
    axios
		.get(`http://localhost:8675/happyhouse/api/community/${this.$route.query.no}`)
		.then(({data}) =>{
			this.community = data;
      console.log(data);
		})
		.catch(() =>{
			alert("오류 발생!!!");
		})
  },
}
</script>
