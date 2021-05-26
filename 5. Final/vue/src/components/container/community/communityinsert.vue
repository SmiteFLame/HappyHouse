<template>
  <div class="container">
    <h2 class="text-center">커뮤니티 등록</h2>
    <table class="table">
      <tr>
        <th>제목</th>
        <td>
          <input class="form-control" type="text" v-model="title" ref="title" id="title" placeholder="제목을 입력해주세요" />
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea class="form-control" id="content" v-model="content" ref="content" cols="30" rows="10"></textarea>
        </td>
      </tr>
    </table>

    <div class="text-center">
      <button class="btn btn-light" @click="createHandler">등록</button>&nbsp;
      <button class="btn btn-light" @click="moveHandler">목록</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  data() {
    return {
      name: "",
      title: "",
      content: ""
    };
  },
	computed:{
		...mapGetters(["userid"])
	},
  methods: {
    createHandler() {
      console.log(this.userid);
      //데이타 검증
      let err = true;
      let msg = "";

      !this.title &&
        ((msg = "제목을 입력해 주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용을 입력해 주세요"), (err = false), this.$refs.content.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .post(`http://localhost:8675/happyhouse/api/community`, {
            username: this.userid,
            title: this.title,
            content: this.content
          })
          .then(() => {
			  alert("게시글이 등록되었습니다.");
			this.$router.push('/communitylist');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/communitylist");
    }
  }
}
</script>
