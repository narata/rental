<template>
    <div class="show">
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
            <el-form-item label="角色" prop="role">
                <el-select v-model="form.role" placeholder="选择用户角色">
                    <el-option label="普通用户" :value=0></el-option>
                    <el-option label="管理员" :value=1></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">修改信息</el-button>
            </el-form-item>
        </el-form>
        <div></div>
    </div>
</template>

<script>
    export default {
        name: "EditUser",
        props: {
            form: Map
        },
        mounted: function() {
            this.form = this.data();
        },
        data() {
            return {
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
                    ],
                    role: [
                        {required: true, message: '请选择用户角色', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
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
        }
    }
</script>

<style scoped>
    .el-input {
        max-width: 500px;
    }
</style>