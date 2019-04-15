import Vue from 'vue'
import Router from 'vue-router'
import Index from './components/Index'
import Login from './components/Login'
import ListSelf from './components/ListSelf'
import ListAll from './components/ListAll'

Vue.use(Router);

let router = new Router({
    routes: [
        {
            path: '/',
            component: Index,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/login",
            component: Login,
            meta: {
                isLogin: false
            }
        },
        {
            path: "/list/self",
            component: ListSelf,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/list/all",
            component: ListAll,
            meta: {
                isLogin: true
            }
        }
    ]
});

export default router;