<template>
    <div class="main">
        <el-header>
            <Menu active-index="4"></Menu>
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
                                <el-tag type="success" v-if="house.status===0">可租</el-tag>
                                <el-tag type="danger" v-else>已租</el-tag>
                                <i class="el-icon-star-on collection-icon" @click="deleteCollection(house.id)"></i>
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
        name: "MyCollection",
        components: {
            Menu
        },
        data() {
            return {
                size: 10,
                data: {},
                current: 1
            }
        },
        mounted: function () {
            this.getPage(1);
            this.getHouseIds();
        },
        methods: {
            getPage(current) {
                this.current = current;
                this.axios.post('/api/house/list/collection?current=' + current + '&size=' + this.size,
                    {}
                ).then((res) => {
                    if (res.status === 200) {
                        this.data = res.data
                    }
                }).catch(() => {
                    this.$message.error("服务错误！")
                })
            },
            deleteCollection(houseId) {
                this.axios.delete("/api/collection/byHouseId?house_id=" + houseId)
                    .then(res => {
                        if (res.status === 204) {
                            this.$message.success("取消收藏成功");
                            this.getPage(this.current)
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
</style>