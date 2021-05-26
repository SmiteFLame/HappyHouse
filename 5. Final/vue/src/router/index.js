import Vue from "vue";
import VueRouter from "vue-router";

import Index from "@/components/container/index.vue";
import SiteMap from "@/components/container/sitemap.vue";

import Deal from "@/components/container/data/deal.vue";

import Login from "@/components/container/login/login.vue";
import Join from "@/components/container/login/join.vue";
import Member from "@/components/container/login/member.vue";
import MemberUpdate from "@/components/container/login/memberupdate.vue";

import BoardList from "@/components/container/board/boardlist.vue";
import BoardSearch from "@/components/container/board/boardsearch.vue";
import BoardInsert from "@/components/container/board/boardinsert.vue";
import BoardUpdate from "@/components/container/board/boardupdate.vue";

import QnaList from "@/components/container/qna/qnalist.vue";
import QnaInsert from "@/components/container/qna/qnainsert.vue";
import QnaUpdate from "@/components/container/qna/qnaupdate.vue";

import CommunityList from "@/components/container/community/communitylist.vue";
import CommunitySearch from "@/components/container/community/communitysearch.vue";
import CommunityInsert from "@/components/container/community/communityinsert.vue";
import CommunityUpdate from "@/components/container/community/communityupdate.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
  },
  {
    path: "/sitemap",
    name: "SiteMap",
    component: SiteMap,
  },
  {
    path: "/deal",
    name: "Deal",
    component: Deal,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/Join",
    name: "Join",
    component: Join,
  },
  {
    path: "/Member",
    name: "Member",
    component: Member,
  },
  {
    path: "/MemberUpdate",
    name: "MemberUpdate",
    component: MemberUpdate,
  },
  {
    path: "/boardlist",
    name: "BoardList",
    component: BoardList,
  },
  {
    path: "/boardsearch",
    name: "BoardSearch",
    component: BoardSearch,
  },
  {
    path: "/boardinsert",
    name: "BoardInsert",
    component: BoardInsert,
  },
  {
    path: "/boardupdate",
    name: "BoardUpdate",
    component: BoardUpdate,
  },
  {
    path: "/qnalist",
    name: "QnaList",
    component: QnaList,
  },
  {
    path: "/qnainsert",
    name: "QnaInsert",
    component: QnaInsert,
  },
  {
    path: "/qnaupdate",
    name: "QnaUpdate",
    component: QnaUpdate,
  },
  {
    path: "/communitylist",
    name: "CommunityList",
    component: CommunityList,
  },
  {
    path: "/communitysearch",
    name: "CommunitySearch",
    component: CommunitySearch,
  },
  {
    path: "/communityinsert",
    name: "CommunityInsert",
    component: CommunityInsert,
  },
  {
    path: "/communityupdate",
    name: "CommunityUpdate",
    component: CommunityUpdate,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
