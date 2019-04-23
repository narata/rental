<template>
    <div class="main">
        <el-header>
            <Menu active-index="6-1"></Menu>
        </el-header>
        <el-main class="show">
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
                                <el-tag type="success" class="tag" v-if="house.rent_house===0">房源</el-tag>
                                <el-tag type="info" class="tag" v-else>求租</el-tag>
                                <el-tag type="success" class="tag" v-if="house.status===0">可租</el-tag>
                                <el-tag type="danger" class="tag" v-else>已租</el-tag>
                                <i class="el-icon-star-on collection-icon" v-if="houseIds.includes(house.id)" @click="deleteCollection(house.id)"></i>
                                <i class="el-icon-star-off collection-icon" v-else @click="collectionHouse(house.id)"></i>
                                <i class="el-icon-edit collection-icon" @click="editPost(house)"></i>
                                <i class="el-icon-delete collection-icon" @click="deletePost(house)"></i>
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
        name: "GetAllSelf",
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
                    rent: null,
                    location: null,
                    type: null,
                    size: null,
                    floor: null,
                    orientation: null,
                    description: null
                },
                search_data: {
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
            getPage(current) {
                this.axios.post('/api/house/list?self=1&current=' + current + '&size=' + this.size,
                    this.search_data
                ).then((res) => {
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
            editPost(house) {
                this.$store.commit('setEdit', house);
                this.$router.push({path: '/edit'})
            },
            deletePost(house) {
                this.$confirm('此操作将删除该帖子, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.axios.delete('/api/house?house_id=' + house.id)
                        .then(res => {
                            if (res.status === 204) {
                                let index = this.data.records.indexOf(house);
                                this.data.records.splice(index, 1);
                                this.data.totle -= 1;
                                this.$message.success("删除成功")
                            } else {
                                this.$message.error("删除失败")
                            }
                        }).catch(() => {
                        this.$message.error("删除失败")
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
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

    .tag {
        margin-right: 20px;
    }

    .padding-search {
        padding: 10px;
    }
</style>