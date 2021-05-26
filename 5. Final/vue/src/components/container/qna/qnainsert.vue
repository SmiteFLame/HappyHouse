<template>
  <div class="container">
    <h2 class="text-center"> Q&A 등록</h2>
    <table class="table table-condensed">
      
      <tr>
        <th>질문</th>
        <td>
          <textarea id="info" v-model="title" ref="title" cols="30" rows="10"></textarea>
        </td>
      </tr>
      <tr>
        <th>답변</th>
        <td>
          <textarea id="info" v-model="info" ref="info" cols="30" rows="10"></textarea>
        </td>
      </tr>
    </table>

    <div class="text-right">
      <button class="btn btn-primary" @click="createHandler">등록</button>
      <button class="btn btn-primary" @click="moveHandler">목록</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
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
          .post(`http://localhost:8675/happyhouse/api/qna`, {
            name: this.name,
            title: this.title,
            info: this.info
          })
          .then(() => {
			  alert("공지사항이 등록되었습니다.");
			this.$router.push('/qnalist');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/qnalist");
    }
  }
}
</script>
