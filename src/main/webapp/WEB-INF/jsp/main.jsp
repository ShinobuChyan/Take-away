<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title></title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="" href="css/animate.css">
        <link rel="stylesheet" type="" href="css/element-ui.css">
        <link rel="stylesheet" type="" href="css/main.css">
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div id="app">
            <div class="search-menu">
                <div class="search-margin">
                    <div class="input-group">
                        <input type="text" id="search-input" class="form-control" v-model="searchstr" placeholder="请搜索美食" @keyup.enter="search">
                        <span class="input-group-addon" id="search-btn" @click="search"><span class="glyphicon glyphicon-search"
                                                                      aria-hidden="true"></span></span>
                    </div>
                </div>
            </div>
            <div id="content">
                <div class="row">
                    <div class="col-md-3 col-margin">
                        <ul class="list-group left-margin">
                            <li class="list-group-item" v-for="item in typeLIst" @click="changeType(item)">
                                <span class="badge" :class="{selected:selectedType.type==item.type}">{{item.count}}</span> {{typeMap[item.type]}}
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-6 col-margin">
                        <ul class="list-group mid-margin">
                            <li class="list-group-item list-item" :class="{isSelect:item.num>0}" v-for="item in list">
                                <div class="img-margin"><img :src="item.img||'img/001.png'" alt=""></div>
                                <span class="text title" v-text="item.name"></span>
                                <span class="text volume" v-cloak>销量：{{item.volume||0}}</span>
                                <span class="text price" v-cloak>价格：<span>{{item.price|money}}</span></span>
                                <div class="num-margin">
                                    <el-input-number v-model="item.num" :min="0" :max="99"></el-input-number>
                                </div>
                            </li>
                        </ul>
                        <div id="page-margin">
                            <el-pagination layout="prev, pager, next" :page-count="pageCount" :current-page="currentPage">
                            </el-pagination>
                        </div>
                    </div>
                    <div class="col-md-3 col-margin">
                        <div id="cart-margin" v-show="selectedList.length>0">
                            <span class="subTitle">购物车</span>
                            <div class="cart" v-for="courseItem in selectedList">
                                <span class="coursePrice">{{courseItem.name}}</span>
                                <span class="courseCount">/{{courseItem.price|money}}</span>
                                <span class="courseName">&emsp;× {{courseItem.num}}</span>
                            </div>
                            <el-button class="commit" @click="commitList">提交</el-button>

                        </div>
                    </div>
                </div>
            </div>
            <el-dialog title="选择收货地址" :visible.sync="dialogTableVisible">
                <el-radio-group v-model="address">
                    <el-radio :label="add.id" :key="add.id" v-for="add in addList">地址：{{add.address}} 收货人：{{add.name}}{{add.phone}}</el-radio>
                </el-radio-group>
            </el-dialog>

            <el-dialog title="收货地址" :visible="dialogTableVisible" :show-close="false">
                <el-radio-group v-model="address">
                    <el-radio :label="add.id" :key="add.id" v-for="add in addList">地址：{{add.address}} 收货人：{{add.name}}手机号:{{add.phone}}</el-radio><br>
                </el-radio-group>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogTableVisible = false">取 消</el-button>
                    <el-button type="primary" @click="commit">确 定</el-button>
                </div>
            </el-dialog>
        </div>
        <script src="js/jquery-3.1.0.js"></script>
        <script src="js/animate.js"></script>
        <script src="js/vue.js"></script>
        <script src="js/element-ui.js"></script>

        <script src="js/my/main.js"></script>
    </body>

    </html>