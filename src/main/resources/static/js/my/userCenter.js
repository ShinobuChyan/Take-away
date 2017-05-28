/**
 * create by songqiankun 2017/5/12
 */
var interval = setInterval(() => {
    if (getResult) {
        clearInterval(interval);
        if (user.id) {
            alert('请登录');
            window.location.href = 'index';
        }
    }
}, 10);

var vm = new Vue({
    el: '#app',
    data: {
        tools: [{ name: '查看订单', component: 'order-list' }, { name: '密码管理', component: 'change-password' }, { name: '地址管理', component: 'change-address' }],
        selectedTools: { name: '查看订单', component: 'order-list' },
    },
    mounted() { },
    methods: {}
});