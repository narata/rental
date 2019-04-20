<template>
    <div id="main">
        <div class="container">
            <div class="login-container">
                <div class="avatar"></div>
                <div class="form-box">
                    <input v-model="username" type="text" placeholder="username">
                    <input v-model="password" type="password" placeholder="password">
                    <button class="btn btn-info btn-block login" type="submit" @click="check">登 录</button>
                    <p id="info" style="color: red;">{{value}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: "login",
        data() {
            return {
                value: "",
                username: "",
                password: ""
            }
        },
        methods: {
            check() {
                // console.log(this.name);
                this.axios.post("/api/login_j", {
                        username: this.username,
                        password: this.password
                    }
                ).then((res) => {
                    if (res.status === 200) {
                        localStorage.setItem("Flag", "isLogin");
                        localStorage.setItem("username", this.username);
                        localStorage.setItem("time", new Date().getTime().toString());
                        this.$router.push({path: '/'});
                    }
                }).catch(() => {
                    this.$message.error("登录失败");
                });
            }
        }
    }

</script>

<style scoped>


    .container {
        text-align: center;
    }

    .login-container {
        top: 100px;
        position: relative;
        width: 300px;
        margin: 80px auto;
        padding: 20px 40px 40px;
        text-align: center;
        background: #fff;
        border: 1px solid #ccc;
    }

    #output {
        position: absolute;
        width: 300px;
        top: -75px;
        left: 0;
        color: #fff;
    }

    #output.alert-success {
        background: rgb(25, 204, 25);
    }

    #output.alert-danger {
        background: rgb(228, 105, 105);
    }


    .login-container::before, .login-container::after {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        top: 3.5px;
        left: 0;
        background: #fff;
        z-index: -1;
        -webkit-transform: rotateZ(4deg);
        -moz-transform: rotateZ(4deg);
        -ms-transform: rotateZ(4deg);
        border: 1px solid #ccc;

    }

    .login-container::after {
        top: 5px;
        z-index: -2;
        -webkit-transform: rotateZ(-2deg);
        -moz-transform: rotateZ(-2deg);
        -ms-transform: rotateZ(-2deg);

    }

    .avatar {
        width: 100px;
        height: 100px;
        margin: 10px auto 30px;
        border-radius: 100%;
        /*border: 2px solid #aaa;*/
        background-size: cover;
    }

    #picture {
        height: 100px;
        width: 100px;
        border-radius: 50%;
    }

    .form-box input {
        width: 100%;
        padding: 10px;
        text-align: center;
        height: 40px;
        border: 1px solid #ccc;;
        background: #fafafa;
        transition: 0.2s ease-in-out;

    }

    .form-box input:focus {
        outline: 0;
        background: #eee;
    }

    .form-box input[type="text"] {
        border-radius: 5px 5px 0 0;
        text-transform: lowercase;
    }

    .form-box input[type="password"] {
        border-radius: 0 0 5px 5px;
        border-top: 0;
    }

    .form-box button.login {
        margin-top: 15px;
        padding: 10px 20px;
    }

    .animated {
        -webkit-animation-duration: 1s;
        animation-duration: 1s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
    }

    @-webkit-keyframes fadeInUp {
        0% {
            opacity: 0;
            -webkit-transform: translateY(20px);
            transform: translateY(20px);
        }

        100% {
            opacity: 1;
            -webkit-transform: translateY(0);
            transform: translateY(0);
        }
    }

    @keyframes fadeInUp {
        0% {
            opacity: 0;
            -webkit-transform: translateY(20px);
            -ms-transform: translateY(20px);
            transform: translateY(20px);
        }

        100% {
            opacity: 1;
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0);
        }
    }

    .fadeInUp {
        -webkit-animation-name: fadeInUp;
        animation-name: fadeInUp;
    }
</style>