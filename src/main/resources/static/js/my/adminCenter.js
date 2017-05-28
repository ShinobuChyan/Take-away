/**
 * create by songqiankun 2017/5/28
 */

// if(user.id) {
//     alert('请登录');
//     window.location.href = 'index';
// }

var vm = new Vue({
    el:'#app',
    data: {
        tools: [{name:'订单管理',component:'admin-order-list'},{name:'餐品管理',component: ''}],
        selectedTools: {name:'订单管理',component:'admin-order-list'},
    },
    mounted() {},
    methods: {}
});