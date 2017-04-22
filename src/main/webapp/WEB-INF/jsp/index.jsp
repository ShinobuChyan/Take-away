<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>O2O外卖系统</title>
        <link rel="stylesheet" type="" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="" href="css/animate.css">
        <link rel="stylesheet" type="" href="css/index.css">
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div class="search">
            <div class="input-group">
                <input type="text" id="search-input" class="form-control" placeholder="请搜索美食">
                <span class="input-group-addon" id="search-btn"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></span>
            </div>
            <a href="main"><button type="button" class="btn btn-warning near">查看附近美食</button></a>
        </div>

        <canvas id="reactive-bg-canvas"></canvas>
    </body>
    <script></script>
    <script src="js/jquery-3.1.0.js"></script>
    <script src="js/TweenLite.min.js"></script>
    <script src="js/magic-canvas.js"></script>
    <script src="js/animate.js"></script>

    <script src="js/my/index.js"></script>

    </html>