<template>
    <div id="main">
        <el-header>
            <Menu active-index="5"></Menu>
        </el-header>
        <el-main class="show" v-if="this.$store.getters.getHouse != null">
            <el-row :gutter="20" class="box">
                <el-col :span="12">
                    <div class="show-in">
                        <img :src="'api/' + house.image">
                    </div>
                </el-col>
                <el-col :span="10" :offset="2">
                    <div class="show-in">
                        <el-card class="box-card">
                            <div class="text item">
                                <el-tag type="success" v-if="house.status===0">可租</el-tag>
                                <el-tag type="danger" v-else>已租</el-tag>
                            </div>
                            <div class="text item">租金：{{house.rent}} / 月</div>
                            <div class="text item">位置：{{house.location}}</div>
                            <div class="text item">户型：{{house.type}}</div>
                            <div class="text item">大小：{{house.size}} 平方米</div>
                            <div class="text item">楼层：{{house.floor}} 层</div>
                            <div class="text item">朝向：{{house.orientation}}</div>
                            <div class="text item">详情：{{house.description}}</div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>
            <el-row :gutter="20" class="box">
                <el-col :span="24">
                    <div class="add-comment">
                        <el-form ref="form" :model="form" status-icon label-width="60px" :rules="rules"
                                 label-position="left">
                            <el-form-item label="评论" prop="description">
                                <el-input type="textarea" v-model="form.comment" rows="4"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="onSubmit">提交评论</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-col>
            </el-row>
            <el-row :gutter="20" class="box">
                <div class="comment" v-for="comment in data.records">
                    <p style="color: cornflowerblue">{{comment.username}}</p>
                    <p style="padding-left: 40px">{{comment.comment}}</p>
                </div>

                <div class="block">
                    <el-col>
                        <el-pagination
                                @current-change="getPage"
                                @prev-click="getPage"
                                @next-click="getPage"
                                layout="prev, pager, next"
                                :page-size="size"
                                :total="data.total">
                        </el-pagination>
                    </el-col>

                </div>
            </el-row>
        </el-main>
    </div>
</template>

<script>
    import Menu from './Menu'

    export default {
        name: "Detail",
        data() {
            return {
                house: {},
                form: {
                    comment: ''
                },
                data: {},
                size: 10
            }
        },
        components: {
            Menu
        },
        mounted() {
            this.initHouse();
            this.getPage(1);
        },
        methods: {
            initHouse() {
                this.house = this.$store.getters.getHouse;
                console.log(this.house)
            },
            onSubmit() {
                let data = new FormData();
                data.append("comment", this.form.comment);
                data.append("house_id", this.house.id);
                this.axios.post('/api/comment', data)
                    .then((res) => {
                        if (res.status === 201) {
                            this.$message.success("评论成功！");
                            this.getPage(1);
                        }
                    }).catch(() => {
                    this.$message.error("服务错误！")
                })
            },
            getPage(current) {
                this.axios.get('/api/comment', {
                        params: {
                            "house_id": this.house.id,
                            "current": current,
                            "size": this.size
                        }
                    }
                ).then((res) => {
                    if (res.status === 200) {
                        this.data = res.data
                    }
                }).catch(() => {
                    this.$message.error("服务错误！")
                })
            },
        }
    }
</script>

<style scoped>
    p {
        margin-bottom: 5px;
    }
    .comment {
        margin: 20px 40px 10px 40px;
    }

    .box {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin-top: 10px;
    }

    .block {
        margin-top: 20px;
        text-align: center;
    }

    img {
        max-width: 100%;
        max-height: 300px;
        margin: 20px 30px;
    }

    .show-in {
        text-align: center;
    }

    .text {
        font-size: 14px;
        text-align: left;
    }

    .item {
        padding: 5px 0;
    }

    .box-card {
        margin: 20px;
        max-width: 100%;
        max-height: 300px;
        overflow: auto;
    }

    .add-comment {
        margin-top: 20px;
    }
</style>