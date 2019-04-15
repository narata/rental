module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:2222',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                },
                // onProxyReq (proxyReq, req, res) {
                //     originHost = req.headers['x-forwarded-for']
                //     const cookie = req.headers['cookie']
                //
                //     if (cookie) {
                //         proxyReq.setHeader('cookie', cookie)
                //     }
                // },
                // onProxyRes(proxyRes, req, res) {
                //     if (proxyRes.headers['set-cookie']) {
                //         // 域名信息与实际业务相关
                //         proxyRes.headers['set-cookie'] = proxyRes.headers['set-cookie'].map(v => {
                //             return v.replace('domain=.mufeng.me', 'domain=' + originHost.split(':')[0])
                //         })
                //     }
                // }
            }
        }
    }
};