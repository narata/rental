import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/Login'
import GetHouse from './components/GetHouse'
import Release from './components/Release'
import RentHouse from './components/RentHouse'
import MyCollection from './components/MyCollection'
import Detail from './components/Detail'
import GetAllSelf from './components/GetAllSelf'
import Edit from './components/Edit'
import EditUserInfo from './components/EditUserInfo'
import ManageUser from './components/ManageUser'

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
            path: "/release",
            component: Release,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/get_house",
            component: GetHouse,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/rent_house",
            component: RentHouse,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/my_collection",
            component: MyCollection,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/detail",
            component: Detail,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/getAllSelf",
            component: GetAllSelf,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/edit",
            component: Edit,
            meta: {
                isLogin: true
            }
        },
        {
            path: "/editUserInfo",
            component: EditUserInfo,
            meta: {
                isLogin: true
            }
        },
        ,
        {
            path: "/manageUser",
            component: ManageUser,
            meta: {
                isLogin: true
            }
        }
    ]
});

export default router;