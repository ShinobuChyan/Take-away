var menuList = [{
    id: 1,
    name: '鱼香肉丝',
    img: 'img/001.png',
    type: 1,
    price: 1200,
    volume: 12,
    num: 0
}];

var typeMap = {
    '0': '荤菜',
    '1': '素菜',
    '2': '汤'
};

function getUrlParam(name) {
    //创建一个查找参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配当前url的目标参数
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null; //返回参数值
}

var searchStr = getUrlParam('search');

Vue.filter('money', function (num) {
    return '￥' + (num / 100).toFixed(2);
});

var vm = new Vue({
    el: '#app',
    data: {
        currentPage: 1,
        pageCount: 1,
        list: [],
        typeMap,
        typeLIst: [],
        selectedType: {},
        searchstr: searchStr,
        dialogTableVisible: false,
        address: '',
        addList: [],
        orderList: []
    },
    mounted() {
        $.get('main/getMenu', (res) => {
            this.typeLIst = res;
        });
        $.get('userCenter/getAddressList', (res) => {
            console.log('getAddressList', res);
            this.addList = res;
            this.address = this.addList[0].id;
        });
        this.search();
    },
    computed: {
        selectedList() {
            return this.list.filter(item => {
                return item.num > 0;
            });
        }
    },
    methods: {
        commitList() {
            this.orderList = this.list.filter(item => {
                return item.num > 0;
            }).map(item => {
                return {
                    id: item.id,
                    count: item.num
                }
            });

            if (!getResult || !user.id) {
                this.$message({
                    showClose: true,
                    message: '登陆后才能提交订单，请登录'
                });
                return;
            }

            this.dialogTableVisible = true;
        },
        commit() {
            this.dialogTableVisible = false;
            console.log('当前选中地址id', this.address);

            $.post('main/submit', {
                newOrder: this.orderList,
                addressId: this.address
            }, (res) => {
                console.log('提交订单返回：', res);
                if (res.code === '1') {
                    this.$message({
                        showClose: true,
                        message: res.msg
                    });
                    return false;
                } else if (res.code === '0') {
                    this.$message({
                        showClose: true,
                        message: res.msg
                    });
                    this.list.forEach(item => {
                        item.num = 0;
                    });
                }
            });
        },
        search() {
            console.log('搜索', this.searchstr);
            $.post('main/courseSearch', {
                page: this.currentPage,
                courseName: this.searchstr,
                type: this.selectedType.type
            }, (res) => {
                this.pageCount = res.totalPages;
                var newList = [];
                res.content.map((item) => {
                    var newItem = Object.assign({}, item, { num: 0 });
                    newList.push(newItem);
                });
                this.list = newList;
            });
        },
        changeType(item) {
            if (this.selectedType.type === item.type) {
                this.selectedType = {};
            } else {
                this.selectedType = item;
            }
            this.search();
        }
    }
});