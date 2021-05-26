<template>
  <div id="app">
    <Menu></Menu>
    <router-view style="margin-top:100px"/>
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');

#app {
  font-family: 'Noto Sans KR', sans-serif;
}

</style>

<script>
import Menu from '@/components/header/menu.vue'

export default {
  name:'App',
  components:{
    Menu,
  }
}
</script>