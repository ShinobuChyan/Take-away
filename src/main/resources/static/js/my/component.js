/**
 * Created by songqiankun on 2017/5/12.
 */
Vue.filter('money', function (num) {
    return '￥' + (num / 100).toFixed(2);
});

Vue.component('change-password', {
    template: `<div id="changePass">
                <div class="input-margin">
                        <div class="input-group">
                            <span class="input-group-addon">原密码:</span>
                            <input type="password" v-model="oldPass" @blur="oldPssVer" class="form-control" id="registerName" placeholder="请输入原密码" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips1}}</p>
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon">新密码:</span>
                            <input type="password" v-model="pwd1" @blur="pwd1Ver" class="form-control" id="registerPwd" placeholder="请输入密码" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips2}}</p>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">确认密码:</span>
                            <input type="password" v-model="pwd2" @blur="pwd2Ver" class="form-control" id="registerPwd2" placeholder="请再次输入密码" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips3}}</p>
                        </div>
                        <button type="button" id="register-submit" class="btn btn-default btn-submit" @click="changePass">修改</button>
                </div>
            </div>`,
    props: [],
    data: function () {
        return {
            oldPass: '',
            pwd1: '',
            pwd2: '',
            tips1: '',
            tips2: '',
            tips3: '',
            pwdreg: /^[a-z0-9_]{6,15}$/,
        }
    },
    methods: {
        oldPssVer() {
            if (!this.pwdreg.test(this.oldPass)) {
                this.tips1 = '旧密码应该为6-15个字母数字下划线';
                return false;
            }
            this.tips1 = '';
        },
        pwd1Ver() {
            if (!this.pwdreg.test(this.pwd1)) {
                this.tips2 = '新密码应该为6-15个字母数字下划线';
                return false;
            }
            this.tips2 = '';
        },
        pwd2Ver() {
            if (this.pwd2 !== this.pwd1) {
                this.tips3 = '两次密码输入不一致';
                return false;
            }
            this.tips3 = '';
        },
        changePass() {
            if (this.tips1 == this.tips2 == this.tips3 == '') {
                $.post('userCenter/changePwd', { oldPwd: this.oldPass, newPwd: this.pwd2 }, (res) => {
                    if (res.code === '1') {
                        this.$message({
                            showClose: true,
                            message: res.msg
                        });
                        return false;
                    } else if (res.code === '0') {
                        // 修改密码成功
                        this.$message({
                            showClose: true,
                            message: res.msg
                        });
                        this.oldPass = this.pwd1 = this.pwd2 = '';
                    }
                });
            }
        }
    }
});

Vue.component('change-address', {
    template: `<div id="changeAddress">
                <div class="input-margin">
                        <div class="input-group">
                            <span class="input-group-addon">收货人:</span>
                            <input type="text" v-model="personName" @blur="oldPssVer" class="form-control" id="registerName" placeholder="请输入收货人" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips1}}</p>
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon">手机号:</span>
                            <input type="number" v-model="phoneNum" @blur="pwd1Ver" class="form-control" id="registerPwd" placeholder="请输入手机号" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips2}}</p>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">地&emsp;址:</span>
                            <input type="text" v-model="address" @blur="pwd2Ver" class="form-control" id="registerPwd2" placeholder="请输入收货地址" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips3}}</p>
                        </div>
                        <button type="button" id="register-submit" class="btn btn-default btn-submit" @click="changePass">{{wantChange.id?'修改':'添加'}}</button>
                        <button type="button" id="register-submit" v-if="wantChange.id" class="btn btn-default btn-submit" @click="cancel">取消</button>
                </div>
                <el-table
                    :data="tableData"
                    border
                    style="width: 100%;margin-top: 100px;">
                    <el-table-column
                      label="收货人"
                      width="100">
                      <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.name }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="手机号"
                      width="130">
                      <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.phone }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="地址"
                      width="300">
                      <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.address }}</span>
                      </template>
                    </el-table-column>
                    <el-table-column label="操作">
                      <template scope="scope">
                        <el-button
                          size="small"
                          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                          size="small"
                          type="danger"
                          @click="deleteAdd(scope.$index, scope.row)">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
            </div>`,
    props: [],
    data: function () {
        return {
            personName: '',
            phoneNum: '',
            address: '',
            tips1: '',
            tips2: '',
            tips3: '',
            phonereg: /^[0-9]{7,15}$/,
            wantChange: {},
            tableData: []
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            $.get('userCenter/getAddressList', (res) => {
                console.log('getAddressList', res);
                this.tableData = res;
            });
        },
        oldPssVer() {
            if (this.personName === '') {
                this.tips1 = '收货人不能为空';
                return false;
            }
            this.tips1 = '';
        },
        pwd1Ver() {
            if (!this.phonereg.test(this.phoneNum)) {
                this.tips2 = '手机号应该为7-15个数字';
                return false;
            }
            this.tips2 = '';
        },
        pwd2Ver() {
            if (this.address === '') {
                this.tips3 = '地址不能为空';
                return false;
            }
            this.tips3 = '';
        },
        changePass() {
            if (this.tips1 == this.tips2 == this.tips3 == '') {
                $.post('userCenter/changeAddress', {
                    id: this.wantChange.id || '',
                    userId: user.userId,
                    name: this.personName,
                    address: this.phoneNum,
                    phone: this.address
                }, (res) => {
                    this.$message({
                        showClose: true,
                        message: '地址添加或修改成功'
                    });
                    this.personName = this.phoneNum = this.address = '';
                    this.init();
                });
            }
        },
        deleteAdd(index, row) {
            $.post('userCenter/deleteAddress', {
                id: this.tableData[index].id
            }, (res) => {
                this.$message({
                    showClose: true,
                    message: res.msg
                });
                if (res.code === '0') {
                    this.tableData.splice(index, 1);
                }
            });
        },
        handleEdit(index, row) {
            this.wantChange = Object.assign({}, this.tableData[index]);
        },
        cancel() {
            this.wantChange = {};
        }
    },
    watch: {
        wantChange() {
            if (this.wantChange.id) {
                this.personName = this.wantChange.name;
                this.phoneNum = this.wantChange.phone;
                this.address = this.wantChange.address;
            } else {
                this.personName = this.phoneNum = this.address = '';
            }
        }
    }
});

Vue.component('order-list', {
    template: `<div id="orderList">
                <div class="order" v-for="item in listData">
                    <span class="orderNum">订单号：{{item.orderNo}}</span>
                    <span class="orderTime">时间：{{item.timeStamp}}</span>
                    <div class="course" v-for="courseItem in item.courses">
                        <span class="coursePrice">{{courseItem.course.name}}</span>
                        <span class="courseCount">/{{courseItem.course.price|money}}</span>
                        <span class="courseName">&emsp;× {{courseItem.count}}</span>
                    </div>
                    <span class="orderTime">总价：{{item.totalPrice}}</span><br/>
                    <span class="orderNum">地址：{{item.address}}</span>
                </div>
                <div id="page-margin">
                    <el-pagination layout="prev, pager, next" :page-count="pageCount" :current-page="currentPage">
                    </el-pagination>
                </div>
            </div>`,
    props: [],
    data: function () {
        return {
            listData: [],
            pageCount: 1,
            currentPage: 1
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            $.get('userCenter/orderList', { page: this.currentPage }, (res) => {
                console.log('orderList', res);
                this.pageCount = res.totalPages;
                this.listData = res.content.map(item => {
                    return Object.assign({ courses: JSON.parse(item.coursesString) }, item);
                });
            });
        },
    }
});

Vue.component('admin-order-list', {
    template: `<div id="orderList">
                <div class="order" v-for="item in listData">
                    <span class="orderNum">订单号：{{item.orderNo}}</span>
                    <span class="orderTime">时间：{{item.timeStamp}}</span>
                    <div class="course" v-for="courseItem in item.courses">
                        <span class="coursePrice">{{courseItem.course.name}}</span>
                        <span class="courseCount">/{{courseItem.course.price|money}}</span>
                        <span class="courseName">&emsp;× {{courseItem.count}}</span>
                    </div>
                    <span class="orderTime">总价：{{item.totalPrice}}</span><br/>
                    <span class="orderNum">地址：{{item.address}}</span> <br />
                    <el-button type="primary" :disabled="item.state === 0" style="margin-left:350px;" @click="distribute(item)">配送</el-button>
                </div>
                
                <div id="page-margin">
                    <el-pagination layout="prev, pager, next" :page-count="pageCount" :current-page="currentPage">
                    </el-pagination>
                </div>
            </div>`,
    props: [],
    data: function () {
        return {
            listData: [],
            pageCount: 1,
            currentPage: 1,

            dialogTableVisible: false,
            address: '',
            visible2: '',
            addList: [{
                id: '111',
                address: 'a',
                name: 'b',
                phone: 'c'
            }]
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            $.get('manager/getOrderList', { page: this.currentPage }, (res) => {
                console.log('getOrderList', res);
                this.pageCount = res.totalPages;
                this.listData = res.content.map(item => {
                    return Object.assign({ courses: JSON.parse(item.coursesString) }, item);
                });
            });
        },
        distribute(item) {
            $.get('manager/changeState', { id: item.id }, (res) => {
                console.log('changeState', res);
                if (res.code === '0')
                    item.state = 0
                this.$message({
                    showClose: true,
                    message: res.msg
                });
            });
        }
    }
});

Vue.component('change-food', {
    template: `<div id="changeAddress">
                <div class="input-margin">
                        <div class="input-group">
                            <span class="input-group-addon">菜名:</span>
                            <input type="text" v-model="personName" @blur="oldPssVer" class="form-control" id="registerName" placeholder="请输入菜名" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips1}}</p>
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon">类型:</span>
                            <el-select v-model="phoneNum" placeholder="请选择">
                                <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">价格:</span>
                            <input type="number" v-model="address" @blur="pwd2Ver" class="form-control" id="registerPwd2" placeholder="请输入价格" aria-describedby="basic-addon3">
                            <p class="err-tips">{{tips3}}</p>
                        </div>
                        <button type="button" id="register-submit" class="btn btn-default btn-submit" @click="changePass">{{wantChange.id?'修改':'添加'}}</button>
                        <button type="button" id="register-submit" v-if="wantChange.id" class="btn btn-default btn-submit" @click="cancel">取消</button>
                </div>
                <ul class="list-group mid-margin">
                    <li class="list-group-item list-item" :class="{isSelect:item.num>0}" v-for="(item,index) in list">
                        <div class="img-margin"><img :src="item.img||'img/001.jpg'" alt=""></div>
                        <span class="text title" v-text="item.name"></span>
                        <span class="text volume" v-cloak>销量：{{item.volume||0}}</span>
                        <span class="text price" v-cloak>价格：<span>{{item.price|money}}</span></span>
                        <div class="num-margin">
                            <el-button
                            size="small"
                            @click="handleEdit(item)">编辑</el-button>
                            <el-button
                            size="small"
                            type="danger"
                            @click="deleteFood(item,index)">删除</el-button>
                        </div>
                    </li>
                </ul>
                <div id="page-margin">
                    <el-pagination layout="prev, pager, next" :page-count="pageCount" :current-page="currentPage">
                    </el-pagination>
                </div>
            </div>`,
    props: [],
    data: function () {
        return {
            personName: '',
            phoneNum: '0',
            address: '',
            tips1: '',
            tips2: '',
            tips3: '',
            phonereg: /^[0-9]{7,15}$/,
            wantChange: {},
            tableData: [],
            options: [{
                value: '0',
                label: '素菜'
            }, {
                value: '1',
                label: '荤菜'
            }, {
                value: '2',
                label: '汤'
            }],
            currentPage: 1,
            pageCount: 1,
            list: []
        }
    },
    mounted() {
        this.init();
    },
    methods: {
        init() {
            $.post('main/courseSearch', {
                page: this.currentPage
            }, (res) => {
                this.pageCount = res.totalPages;
                this.list = res.content;
            });
        },
        oldPssVer() {
            if (this.personName === '') {
                this.tips1 = '菜名不能为空';
                return false;
            }
            this.tips1 = '';
        },
        pwd1Ver() {
            if (!this.phonereg.test(this.phoneNum)) {
                this.tips2 = '手机号应该为7-15个数字';
                return false;
            }
            this.tips2 = '';
        },
        pwd2Ver() {
            if (this.address === '') {
                this.tips3 = '价格不能为空';
                return false;
            }
            this.tips3 = '';
        },
        deleteFood(item, index) {
            $.post('manager/deleteCourse', {
                id: item.id
            }, (res) => {
                this.$message({
                    showClose: true,
                    message: res.msg
                });
                if (res.code === '0') {
                    this.list.splice(index, 1);
                }
            });
        },
        changePass() {
            if (this.tips1 == this.tips2 == this.tips3 == '') {
                $.post('manager/modifyCourse', {
                    id: this.wantChange.id || '',
                    name: this.personName,
                    type: this.phoneNum,
                    price: this.address
                }, (res) => {
                    this.$message({
                        showClose: true,
                        message: '菜品添加或修改成功'
                    });
                    this.personName = this.phoneNum = this.address = '';
                });
            }
        },
        handleEdit(item) {
            this.wantChange = Object.assign({}, item);
        },
        cancel() {
            this.wantChange = {};
        }
    },
    watch: {
        wantChange() {
            if (this.wantChange.id) {
                this.personName = this.wantChange.name;
                this.phoneNum = this.wantChange.type + "";
                this.address = (this.wantChange.price / 100).toFixed(2);
                return ;
            } else {
                this.personName = this.phoneNum = this.address = '';
                return ;
            }
        }
    }
});