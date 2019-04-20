import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login'
import Main from './components/Main'

Vue.use(Router);

let router = new Router({
    routes: [
        {
            path: "/login",
            component: Login,
            meta: {
                isLogin: false
            }
        },
        {
            path: "/",
            component: Main,
            meta: {
                isLogin: false
            }
        }
    ]
});

export default router;