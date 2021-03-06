<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>O2O外卖系统</title>
        <link rel="stylesheet" type="" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="" href="css/animate.css">
        <link rel="stylesheet" type="" href="css/header.css">
    </head>

    <body>
        <header>
            <div class="menu">
                <span id="index">O2O外卖系统</span>
                <div class="login-item" id="noLogin">
                    <span id="login">登陆</span>
                    <span class="line"></span>
                    <span id="register">注册</span>
                </div>
                <div class="login-item" id="userMsg">
                    <span id="cancellation">注销</span>
                    <span class="line"></span>
                    <span id="userName"></span>
                </div>
            </div>
            <div class="dlg login-margin">
                <span class="subtitle">登陆</span>
                <span class="glyphicon glyphicon-remove close" aria-hidden="true"></span>
                <div class="input-margin">
                    <div class="input-group">
                        <span class="input-group-addon">账号:</span>
                        <input type="text" class="form-control" id="loginName" name="username" placeholder="请输入账号" aria-describedby="basic-addon3">
                        <p class="err-tips"></p>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">密码:</span>
                        <input type="text" class="form-control" id="loginPwd" name="password" placeholder="请输入密码" aria-describedby="basic-addon3" onfocus="this.type='password'">
                        <p class="err-tips"></p>
                    </div>
                    <button type="button" id="login-submit" class="btn btn-default btn-submit">登陆</button>
                </div>
            </div>
            <div class="dlg register-margin">
                <span class="subtitle">注册</span>
                <span class="glyphicon glyphicon-remove close" aria-hidden="true"></span>
                <div class="input-margin">
                    <form id="register-form">
                        <div class="input-group">
                            <span class="input-group-addon">账号:</span>
                            <input type="text" class="form-control" id="registerName" placeholder="请输入账号" aria-describedby="basic-addon3">
                            <p class="err-tips"></p>
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon">密码:</span>
                            <input type="password" class="form-control" id="registerPwd" placeholder="请输入密码" aria-describedby="basic-addon3" onfocus="this.type='password'">
                            <p class="err-tips"></p>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">确认密码:</span>
                            <input type="password" class="form-control" id="registerPwd2" placeholder="请再次输入密码" aria-describedby="basic-addon3" onfocus="this.type='password'">
                            <p class="err-tips"></p>
                        </div>
                        <button type="button" id="register-submit" class="btn btn-default btn-submit">注册</button>
                    </form>
                </div>
            </div>
        </header>
    </body>
    <script src="js/jquery-3.1.0.js"></script>
    <script src="js/my/header.js"></script>

    </html>