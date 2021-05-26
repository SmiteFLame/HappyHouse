<template>
  <div class="container">
    <h2 class="text-center">공지사항 수정</h2>
    <table class="table table-condensed">
      <tr>
        <th>글쓴이</th>
        <td>
          <input type="text" class="form-control" v-model="name" ref="name" id="name" val="name" readonly>
        </td>
      </tr>
      <tr>
        <th>제목</th>
        <td>
          <input type="text" class="form-control" v-model="title" ref="title" id="title" placeholder="제목을 입력해주세요">
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea  class="form-control" id="info" v-model="info" ref="info" cols="30" rows="10"></textarea>
        </td>
      </tr>
    </table>

    <div class="text-right">
      <button class="btn btn-primary" @click="createHandler">수정</button>
      <button class="btn btn-primary" @click="moveHandler">목록</button>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
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

      !this.name && ((msg = "작성자를 입력해 주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.title &&
        ((msg = "제목을 입력해 주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.info &&
        ((msg = "내용을 입력해 주세요"), (err = false), this.$refs.info.focus());

      if (!err) {
        alert(msg);
      } else {
        axios
          .put(`http://localhost:8675/happyhouse/api/qna`, {
            id: this.id,
            name: this.name,
            title: this.title,
            info: this.info
          })
          .then(() => {
			  alert("공지사항이 수정되었습니다.");
			this.$router.push('/qnalist');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/qnalist");
    },
  },
  created() {
    this.id = this.$route.query.id;
    this.name = this.$route.query.name;
  },
}
</script>
