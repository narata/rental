import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import './plugins/axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from './plugins/store'
import 'jquery/dist/jquery.min.js'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import iView from 'iview'

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(Vuex);
Vue.use(iView, {
  transfer: true,
  size: 'large'
});

router.beforeEach((to, from, next) => {
  const outTime = 24 * 60 * 60 * 1000;
let getFlag = localStorage.getItem("Flag");
let getTime = localStorage.getItem("time");
let nowTime = new Date().getTime();
if (getFlag !== "isLogin" || getTime === null || nowTime - outTime > parseInt(getTime)) {
  if (to.meta.isLogin) {
    next({
      path: "/login"
    })
  } else {
    next()
  }
} else {
  next()
}
});

router.afterEach(() => {
  window.scroll(0, 0);
});

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');