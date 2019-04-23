<template>
    <div id="main">
        <el-header>
            <Menu active-index="7"></Menu>
        </el-header>
        <el-main class="show">
            <el-form ref="form" :model="form" status-icon label-width="60px" :rules="rules" label-position="left">
                <el-form-item label="状态" prop="status">
                    <el-select v-model="form.status" placeholder="选择帖子状态">
                        <el-option label="未租" :value=0></el-option>
                        <el-option label="已租" :value=1></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="租金" prop="rent">
                    <el-input v-model.number="form.rent" class="el-input"></el-input>
                </el-form-item>
                <el-form-item label="位置" prop="location">
                    <el-input v-model="form.location"></el-input>
                </el-form-item>
                <el-form-item label="户型" prop="type">
                    <el-input v-model="form.type"></el-input>
                </el-form-item>
                <el-form-item label="大小" prop="size">
                    <el-input v-model.number="form.size"></el-input>
                </el-form-item>
                <el-form-item label="楼层" prop="floor">
                    <el-input v-model.number="form.floor"></el-input>
                </el-form-item>
                <el-form-item label="朝向" prop="orientation">
                    <el-select v-model="form.orientation" placeholder="选择房屋朝向">
                        <el-option label="东" value="东"></el-option>
                        <el-option label="南" value="南"></el-option>
                        <el-option label="西" value="西"></el-option>
                        <el-option label="北" value="北"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="图片" prop="image">
                    <el-upload
                            class="upload-demo"
                            action="/api/image"
                            :on-success="handleSuccess"
                            :on-remove="handleRemove"
                            :file-list="fileList"
                            with-credentials
                            limit="1"
                            name="image"
                            list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过5MB</div>
                    </el-upload>
                </el-form-item>

                <el-form-item label="详情" prop="description">
                    <el-input type="textarea" v-model="form.description"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
                </el-form-item>

            </el-form>
            <div></div>

        </el-main>
    </div>

</template>

<script>
    import Menu from './Menu'

    export default {
        name: "Edit",
        components: {
            Menu
        },
        mounted: function () {
            this.init();
        },
        data() {
            return {
                form: {
                    rent: '',
                    rent_house: '',
                    status: '',
                    location: '',
                    type: '',
                    size: '',
                    floor: '',
                    orientation: '',
                    image: '',
                    description: ''
                },
                fileList: [],
                rules: {
                    rent: [
                        {required: true, message: '请输入租金', trigger: 'blur'},
                        {type: "number", message: '请输入数字值', trigger: 'blur'}
                    ],
                    status: [
                        {required: true, message: '请选择房屋状态', trigger: 'change'}
                    ],
                    location: [
                        {required: true, message: '请输入地址', trigger: 'blur'}
                    ],
                    type: [
                        {required: true, message: '请输入户型', trigger: 'blur'}
                    ],
                    size: [
                        {required: true, message: '请输入房屋大小', trigger: 'blur'},
                        {type: "number", message: '请输入数字值', trigger: 'blur'}
                    ],
                    floor: [
                        {required: true, message: '请输入楼层', trigger: 'blur'},
                        {type: "number", message: '请输入数字值', trigger: 'blur'}
                    ],
                    orientation: [
                        {required: true, message: '请选择朝向', trigger: 'change'}
                    ],
                    image: [
                        {required: true, message: '请上传图片', trigger: 'change'}
                    ],
                    description: [
                        {required: true, message: '请输入详情', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            init() {
                this.form = this.$store.getters.getEdit;
                this.fileList.push({name: this.form.image, url: '/api/' + this.form.image});
            },
            onSubmit(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        this.axios.post("/api/house", this.form
                        ).then(res => {
                            if (res.status === 201) {
                                this.$message.success("发布成功");
                                this.$store.commit("setEdit", null);
                                this.$router.push({path: '/getAllSelf'});

                            } else {
                                this.$message.error("发布失败");
                            }
                        }).catch(() => {
                            this.$message.error("发布失败");
                        })
                    } else {
                        return false;
                    }
                });

            },
            handleSuccess(response) {
                this.form.image = response.image;
            },
            handleRemove() {
                this.form.image = '';
            }
        }
    }
</script>

<style scoped>
    .el-input {
        max-width: 500px;
    }
</style>