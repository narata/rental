<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click="getHouse()">房源信息</el-menu-item>
        <el-menu-item index="2" @click="rentHouse()">求租信息</el-menu-item>
        <el-menu-item index="3" @click="release()">发布信息</el-menu-item>
        <el-menu-item index="4" @click="myCollection()">我的收藏</el-menu-item>
        <el-menu-item index="5" @click="detail()" v-if="this.$store.getters.getHouse != null">帖子详情</el-menu-item>
        <el-menu-item index="7" @click="edit()" v-if="this.$store.getters.getEdit != null">编辑帖子</el-menu-item>
        <el-submenu index="6">
            <template slot="title">个人中心</template>
            <el-menu-item index="6-1" @click="getAllSelf">查看帖子</el-menu-item>
            <el-menu-item index="6-2" @click="editUserInfo">信息修改</el-menu-item>
            <el-menu-item index="6-3" @click="manageUser" v-if="role === '1'">用户管理</el-menu-item>
            <el-menu-item index="6-4" @click="logout()">退出</el-menu-item>
        </el-submenu>
    </el-menu>
</template>

<script>
    export default {
        name: "menu",
        data() {
            return {
                // activeIndex: "1"
                username: localStorage.getItem("username"),
                role: localStorage.getItem("role")
            }
        },
        props: ['activeIndex'],
        methods: {
            logout() {
                this.axios.post("/api/logout"
                ).then((response) => {
                    if (response.status === 200) {
                        localStorage.removeItem("Flag");
                        localStorage.removeItem("username");
                        this.$store.commit("setDaily", null);
                        this.$router.push({path: '/login'});
                    }
                }).catch(() => {
                    this.$message.error("退出登录失败")
                });
            },
            getHouse() {
                this.$router.push({path: '/get_house'})
            },
            rentHouse() {
                this.$router.push({path: '/rent_house'})
            },
            release() {
                this.$router.push({path: '/release'})
            },
            myCollection() {
                this.$router.push({path: '/my_collection'})
            },
            detail() {
                this.$router.push({path: '/detail'})
            },
            getAllSelf() {
                this.$router.push({path: '/getAllSelf'})
            },
            edit() {
                this.$router.push({path: '/edit'})
            },
            editUserInfo() {
                this.$router.push({path: '/editUserInfo'})
            },
            manageUser() {
                this.$router.push({path: '/manageUser'})
            }
        }
    }
</script>

<style scoped>

</style>