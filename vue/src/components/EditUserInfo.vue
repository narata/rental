<template>
    <div id="main">
        <el-header>
            <Menu active-index="6-2"></Menu>
        </el-header>
        <el-main class="show">
            <el-form ref="form" :model="form" status-icon label-width="60px" :rules="rules" label-position="left">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model.number="form.name" class="el-input"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit('form')">修改信息</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="password" :model="password" status-icon label-width="60px" :rules="rulesPassword" label-position="left">
                <el-form-item label="密码" prop="password">
                    <el-input v-model="password.password" class="el-input"></el-input>
                </el-form-item>
                <el-form-item label="同上" prop="rePassword">
                    <el-input v-model="password.rePassword"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="editPassword('password')">修改密码</el-button>
                </el-form-item>
            </el-form>
            <div></div>

        </el-main>
    </div>

</template>

<script>
    import Menu from './Menu'
    export default {
        name: "EditUserInfo",
        components: {
            Menu
        },
        mounted: function () {
            this.init();
        },
        data() {
            return {
                form: {
                    name: '',
                    email: '',
                    phone: ''
                },
                password: {
                    password: '',
                    rePassword: ''
                },
                fileList: [],
                rules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'change'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'change'}
                    ],
                    phone: [
                        {required: true, message: '请输入电话', trigger: 'change'}
                    ]
                },
                rulesPassword: {
                    password: [
                        {required: true, message: '请输入密码', trigger: 'change'}
                    ],
                    rePassword: [
                        {required: true, message: '请再次输入密码', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            init() {
                this.axios.get('/api/user/info/self')
                    .then(res => {
                        if (res.status === 200) {
                            this.form = res.data
                        }
                    }).catch(() => {
                        this.$message.error("获取用户信息失败");
                })
            },
            onSubmit(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        this.axios.patch("/api/user/update", this.form
                        ).then(res => {
                            if (res.status === 200) {
                                this.$message.success("修改个人信息成功");
                            } else {
                                this.$message.error("修改个人信息失败");
                            }
                        }).catch(() => {
                            this.$message.error("修改个人信息失败");
                        })
                    } else {
                        return false;
                    }
                });
            },
            editPassword(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        if (this.password.password === this.password.rePassword) {
                            let data = new FormData();
                            data.append("password", this.password.password);
                            this.axios.post("/api/user/editPassword", data)
                                .then(res => {
                                    if (res.status === 200) {
                                        this.$message.success("修改密码成功");
                                    } else {
                                        this.$message.error("修改密码失败");
                                    }
                                }).catch(() => {
                                this.$message.error("修改密码失败");
                            })
                        } else {
                            this.$message.info("两次密码输入不一致");
                        }

                    } else {
                        return false;
                    }
                });

            }
        }
    }
</script>

<style scoped>
    .el-input {
        max-width: 500px;
    }
</style>