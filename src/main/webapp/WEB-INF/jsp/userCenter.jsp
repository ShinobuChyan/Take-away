<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>O2O外卖系统</title>
        <link rel="stylesheet" type="" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="" href="css/animate.css">
        <link rel="stylesheet" type="" href="css/element-ui.css">
        <link rel="stylesheet" type="" href="css/userCenter.css">
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div id="app">
            <div id="content">
                <div class="row">
                    <div class="col-md-2 col-margin">
                    </div>
                    <div class="col-md-2 col-margin">
                        <ul class="list-group left-margin">
                            <li class="list-group-item" :class="{active:selectedTools.name==item.name}" v-for="item in tools" @click="selectedTools=item">
                                {{item.name}}
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-8 col-margin">
                        <component :is="selectedTools.component"></component>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script></script>
    <script src="js/jquery-3.1.0.js"></script>
    <script src="js/animate.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/element-ui.js"></script>

    <script src="js/my/component.js"></script>
    <script src="js/my/userCenter.js"></script>
    </html>