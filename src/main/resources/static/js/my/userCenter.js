/**
 * create by songqiankun 2017/5/12
 */

if(user.id) {
    alert('请登录');
    window.location.href = 'index';
}

var vm = new Vue({
    el:'#app',
    data: {
        tools: [{name:'查看订单',component:'order-list'},{name:'密码管理',component: 'change-password'},{name:'地址管理',component: 'change-address'}],
        selectedTools: {name:'查看订单',component:'order-list'},
    },
    mounted() {},
    methods: {}
});