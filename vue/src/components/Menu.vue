<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click="main()">写日报</el-menu-item>
        <el-menu-item index="2" @click="listSelf()">查看自己日报</el-menu-item>
        <el-menu-item index="3" @click="listAll()">查看所有日报</el-menu-item>
        <el-submenu index="4">
            <template slot="title">{{ username }}</template>
            <el-menu-item index="4-1">修改个人信息</el-menu-item>
            <el-menu-item index="4-2">修改密码</el-menu-item>
            <el-menu-item index="4-3" @click="logout()">退出</el-menu-item>
        </el-submenu>
    </el-menu>
</template>

<script>
    export default {
        name: "menu",
        data() {
            return {
                // activeIndex: "1"
                username: localStorage.getItem("username")
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
            listSelf() {
                this.$router.push({path: '/list/self'})
            },
            listAll() {
                this.$router.push({path: '/list/all'})
            },
            main() {
                this.$router.push({path: '/'})
            }
        }
    }
</script>

<style scoped>

</style>