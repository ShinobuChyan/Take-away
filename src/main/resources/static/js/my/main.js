var menuList = [{
    id: 1,
    name: '鱼香肉丝',
    img: 'img/001.jpg',
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
console.log(searchStr);

$('#search-input').val(searchStr);
search(searchStr);

// 触发搜索事件
$('#search-input').click(function () {
    if ($('#search-input').val() === '') {
        return;
    }
    search($('#search-input').val());
});
$('#search-btn').keydown(function (event) {
    if ($('#search-input').val() === '') {
        return;
    }
    switch (event.keyCode) {
        case 13:
            search($('#search-input').val());
            break;
        default:
            break;
    }
});

// 搜索的函数
function search(searchStr) {
    // do something...
    console.log('搜索');
}

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
        typeLIst: []
    },
    mounted(){
        $.get('main/getMenu', (res) => {
            this.typeLIst = res;
        });
        $.post('main/courseSearch', {page: this.currentPage}, (res) => {
            this.pageCount = res.totalPages;
            var newList = [];
            res.content.map((item) => {
                var newItem = Object.assign({}, item, {num: 0});
                newList.push(newItem);
            });
            this.list = newList;
        });
    },
    computed: {
        selectedList() {
            return this.list.filter(item => {
                return item.num > 0;
            });
        }
    }
});