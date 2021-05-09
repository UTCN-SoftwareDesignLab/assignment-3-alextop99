import Vue from "vue";
import VueRouter from "vue-router";
import Admin from "@/pages/Admin";
import Login from "@/pages/Login";
import Secretary from "@/pages/Secretary";
import Doctor from "@/pages/Doctor";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
  },
  {
    path: "/secretary",
    name: "Secretary",
    component: Secretary,
  },
  {
    path: "/doctor",
    name: "Doctor",
    component: Doctor,
  },
];

const router = new VueRouter({
  routes,
  mode: 'history',
});

export default router;
