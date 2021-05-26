import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    storage: window.sessionStorage,
    username: "",
    userid: "",
  },
  getters: {
    storage(state) {
      return state.storage;
    },
    username(state) {
      return state.storage.getItem("login_username");
    },
    userid(state) {
      return state.storage.getItem("login_userid");
    },
  },
  mutations: {
    setStorageJ(state, payload) {
      state.storage.setItem("jwt-auth-token", payload);
    },
    setStorageL(state, payload) {
      state.storage.setItem("login_username", payload);
      state.username = payload;
    },
    setuserid(state, payload) {
      state.storage.setItem("login_userid", payload);
      state.userid = payload;
    },
  },
  actions: {},
});
