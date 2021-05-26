<template>
  <div class="container">
    <h2 class="text-center">공지사항 등록</h2>
    <table class="table table-condensed">
      <tr>
        <th>제목</th>
        <td>
          <input class="form-control" type="text" v-model="title" ref="title" id="title" placeholder="제목을 입력해주세요" />
        </td>
      </tr>
      <tr>
        <th>내용</th>
        <td>
          <textarea class="form-control"  id="info" v-model="info" ref="info" cols="30" rows="10"></textarea>
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
          .post(`http://localhost:8675/happyhouse/api/board`, {
            name: 'admin',
            title: this.title,
            info: this.info
          })
          .then(() => {
			  alert("공지사항이 등록되었습니다.");
			this.$router.push('/boardlist');
          })
          .catch(() => {
            alert("error 발생");
          });
      }
    },
    moveHandler() {
      this.$router.push("/boardlist");
    }
  }
}
</script>
