<template>
    <div class="main">
        <el-header>
            <Menu active-index="1"></Menu>
        </el-header>
        <el-main class="show">
            <el-row :gutter="20" class="box padding-search">
                <el-col :span="4">
                    <el-select v-model="search_type" placeholder="选择搜索类型">
                        <el-option label="租金(小等于)" value="0"></el-option>
                        <el-option label="位置" value="1"></el-option>
                        <el-option label="户型" value="2"></el-option>
                        <el-option label="大小(前后10平米)" value="3"></el-option>
                        <el-option label="楼层" value="4"></el-option>
                        <el-option label="朝向" value="5"></el-option>
                        <el-option label="详情" value="6"></el-option>
                    </el-select>
                </el-col>
                <el-col :span="6">
                    <el-input v-model="search_input" placeholder="请输入内容"></el-input>
                </el-col>
                <el-col :span="6">
                    <el-button type="primary" @click="search">搜索</el-button>
                </el-col>
            </el-row>
            <el-row :gutter="20" class="box" v-for="house in data.records">
                <el-col :span="12">
                    <div class="show-in">
                        <img :src="'api/' + house.image" @click="detail(house)">
                    </div>
                </el-col>
                <el-col :span="10" :offset="2">
                    <div class="show-in">
                        <el-card class="box-card">
                            <div class="text item">
                                <el-tag type="success" v-if="house.status===0">可租</el-tag>
                                <el-tag type="danger" v-else>已租</el-tag>
                                <i class="el-icon-star-on collection-icon" v-if="houseIds.includes(house.id)" @click="deleteCollection(house.id)"></i>
                                <i class="el-icon-star-off collection-icon" v-else @click="collectionHouse(house.id)"></i>
                                <i class="el-icon-phone collection-icon" @click="userInfo(house.user_id)"></i>
                            </div>
                            <div class="text item">租金：{{house.rent}} / 月</div>
                            <div class="text item">位置)：{{house.location}}</div>
                            <div class="text item">户型：{{house.type}}</div>
                            <div class="text item">大小：{{house.size}} 平方米</div>
                            <div class="text item">楼层：{{house.floor}} 层</div>
                            <div class="text item">朝向：{{house.orientation}}</div>
                            <div class="text item">详情：{{house.description}}</div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>

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
    </div>
</template>

<script>
    import Menu from './Menu'

    export default {
        name: "GetHouse",
        components: {
            Menu
        },
        data() {
            return {
                size: 10,
                data: {},
                houseIds: [],
                search_type: '',
                search_input: null,
                search_data_init: {
                    rent_house: 0,
                    rent: null,
                    location: null,
                    type: null,
                    size: null,
                    floor: null,
                    orientation: null,
                    description: null
                },
                search_data: {
                    rent_house: 0,
                    rent: null,
                    location: null,
                    type: null,
                    size: null,
                    floor: null,
                    orientation: null,
                    description: null
                }
            }
        },
        mounted: function () {
            this.getPage(1);
            this.getHouseIds();
        },
        methods: {
            search() {
                this.search_data = this.search_data_init;
                switch (this.search_type) {
                    case "0": this.search_data.rent = this.search_input; break;
                    case "1": this.search_data.location = this.search_input; break;
                    case "2": this.search_data.type = this.search_input; break;
                    case "3": this.search_data.size = this.search_input; break;
                    case "4": this.search_data.floor = this.search_input; break;
                    case "5": this.search_data.orientation = this.search_input; break;
                    case "6": this.search_data.description = this.search_input; break;
                }
                this.getPage(1)
            },
            getPage(current) {
                this.axios.post('/api/house/list?self=0&current=' + current + '&size=' + this.size, this.search_data)
                    .then((res) => {
                    if (res.status === 200) {
                        this.data = res.data
                    }
                }).catch(() => {
                    this.$message.error("服务错误！")
                })
            },
            getHouseIds() {
                this.axios.get('/api/collection/list/houseId')
                    .then((res) => {
                    if (res.status === 200) {
                        this.houseIds = res.data
                    }
                }).catch(() => {
                    this.$message.error("服务错误！")
                })
            },
            collectionHouse(houseId) {
                let data = new FormData();
                data.append("house_id", houseId);
                this.axios.post("/api/collection", data)
                    .then(res => {
                        if (res.status === 201) {
                            this.houseIds.push(houseId);
                            this.$message.success("收藏成功")

                        } else {
                            this.$message.error("收藏失败！")
                        }
                    }).catch(() => {
                    this.$message.error("收藏失败！")
                })
            },
            deleteCollection(houseId) {
                this.axios.delete("/api/collection/byHouseId?house_id=" + houseId)
                    .then(res => {
                        if (res.status === 204) {
                            let index = this.houseIds.indexOf(houseId);
                            this.houseIds.splice(index, 1);
                            this.$message.success("取消收藏成功");
                        } else {
                            this.$message.error("取消收藏失败！")
                        }
                    }).catch(() => {
                    this.$message.error("取消收藏失败！")
                })
            },
            detail(house) {
                this.$store.commit('setHouse', house);
                this.$router.push({path: '/detail'})
            },
            userInfo(userId) {
                this.axios.get("/api/user/info?user_id=" + userId)
                    .then(res => {
                        if (res.status === 200) {
                            this.$alert('<p>姓名：'+ res.data.name + '</p><p>邮箱：'+ res.data.email + '</p><p>电话：'+ res.data.phone + '</p>' , '发帖人信息', {
                                dangerouslyUseHTMLString: true
                            });
                        } else {
                            this.$message.error("获取信息失败！")
                        }
                    }).catch(() => {
                    this.$message.error("获取信息失败！")
                })
            }
        }
    }
</script>

<style scoped>
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

    .collection-icon {
        font-size: 18px;
        float: right;
        margin: 0 10px 0 10px;
    }

    .padding-search {
        padding: 10px;
    }
</style>