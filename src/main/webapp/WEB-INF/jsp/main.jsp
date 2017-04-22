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
<div class="search-menu">
    <div class="search-margin">
        <div class="input-group">
            <input type="text" id="search-input" class="form-control" placeholder="请搜索美食">
            <span class="input-group-addon" id="search-btn"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
        </div>
    </div>
</div>
<div id="content">
    <div class="row">
        <div class="col-md-3 col-margin">
            <ul class="list-group left-margin">
                <li class="list-group-item">
                    <span class="badge">12</span>
                    哈哈
                </li>
                <li class="list-group-item">
                    <span class="badge">23</span>
                    荤菜
                </li>
                <li class="list-group-item">
                    <span class="badge">5</span>
                    饮料
                </li>
            </ul>
        </div>
        <div class="col-md-6 col-margin">
            <ul class="list-group mid-margin">
                <li class="list-group-item list-item" :class="{isSelect:item.num>0}"  v-for="item in list">
                    <div class="img-margin"><img :src="item.img" alt=""></div>
                    <span class="text title" v-text="item.name"></span>
                    <span class="text volume" v-cloak>销量：{{item.volume}}</span>
                    <span class="text price" v-cloak>价格：<span>{{item.price|money}}</span></span>
                    <div class="num-margin">
                        <el-input-number v-model="item.num" :min="0" :max="99"></el-input-number>
                    </div>
                </li>
            </ul>
        </div>
        <div class="col-md-3 col-margin">
            <ul class="list-group right-margin">

            </ul>
        </div>
    </div>
</div>
<script src="js/jquery-3.1.0.js"></script>
<script src="js/animate.js"></script>
<script src="js/vue.js"></script>
<script src="js/element-ui.js"></script>

<script src="js/my/main.js"></script>
</body>

</html>