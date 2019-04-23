<template>
    <div id="main">
        <el-header>
            <Menu active-index="6-3"></Menu>
        </el-header>
        <el-main class="show" v-if="show === 1">
            <el-table
                    :data="data.records"
                    style="width: 100%"
                    @click="alert(1)">
                <el-table-column
                        prop="username"
                        label="用户名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="邮箱">
                </el-table-column>
                <el-table-column
                        label="角色">
                    <template slot-scope="scope">
                        <span v-if="scope.row.role === 1">管理员</span>
                        <span v-else>普通用户</span>
                    </template>
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button type="text" @click="editUser(scope.row)" size="small">编辑</el-button>
                        <el-button type="text" @click="deleteUser(scope.row)" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block">
                <el-pagination
                        @current-change="getPage"
                        @prev-click="getPage"
                        @next-click="getPage"
                        layout="prev, pager, next"
                        :page-size="size"
                        :total="data.total">
                </el-pagination>
            </div>

        </el-main>
        <el-main class="show" v-else>
            <EditUser :form="props"/>
            <el-button type="primary" @click="cancel" style="float: right">返回</el-button>
        </el-main>

    </div>

</template>

<script>
    import Menu from './Menu'
    import EditUser from './admin/EditUser'

    export default {
        name: "ManageUser",
        components: {
            Menu,
            EditUser
        },
        mounted: function () {
            this.getPage(1);
        },
        data() {
            return {
                data: {},
                size: 20,
                current: 1,
                show: 1,
                props: {}
            }
        },
        methods: {
            getPage(current) {
                this.current = current;
                this.axios.get('/api/user/info/all', {
                    params: {
                        current: current,
                        size: this.size
                    }
                })
                    .then(res => {
                        if (res.status === 200) {
                            this.data = res.data
                        }
                    }).catch(() => {
                    this.$message.error("获取用户信息失败");
                })
            },
            editUser(data) {
                this.props = data;
                this.show = 0
            },
            deleteUser(data) {
                this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.axios.delete('/api/user', {
                        params: {
                            user_id: data.id
                        }
                    }).then(res => {
                        if (res.status === 200) {
                            this.getPage(this.current);
                            this.$message.success("删除用户成功")
                        } else {
                            this.$message.error("删除用户失败")
                        }
                    }).catch(() => {
                        this.$message.error("删除用户失败")
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            cancel() {
                this.show = 1;
            }
        }
    }
</script>

<style scoped>
    .block {
        margin-top: 20px;
        text-align: center;
    }
</style>