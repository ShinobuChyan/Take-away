// 存贮用户信息
var user = {};
var getResult = false;

$.get('login/getLoginInfo', (res) => {
    console.log(res);
    if (!res) { // 未登陆
        $('#noLogin').css("display", "block");
    } else { // 已登录
        user = res;
        $('#userName').text(res.userName);
        $('#userMsg').css("display", "block");
        getResult = true;
    }
});

// 点击回到主页按钮
$('#index').click(function () {
    window.location.href = 'index';
});

// 登陆注册按钮点击事件
$('#login').click(function() {
    console.log('登陆');
    $('.dlg').removeClass('show');
    $('.login-margin').animateCss('fadeIn');
    $('.login-margin').addClass('show');
});

$('#register').click(function() {
    console.log('注册');
    $('.dlg').removeClass('show');
    $('.register-margin').animateCss('fadeIn');
    $('.register-margin').addClass('show');
});


var canLogin = false;
var canRegis = false;

/*
 *进行表单验证
 */
// 用户名和密码的正则
var namereg = /^[a-z0-9_]{3,15}$/;
var pwdreg = /^[a-z0-9_]{6,15}$/;
// 注册操作
// 验证注册用户名是否存在
$('#registerName').blur(function() {
    $(this).next('.err-tips').text('');
    let registerName = $(this).val();
    if (namereg.test(registerName)) {
        $.get('register/verify', { userName: registerName }, (res) => {
            if (res.code === '1') {
                $(this).next('.err-tips').text('用户名已存在');
                return false;
            }
        });
    } else {
        $(this).next('.err-tips').text('用户名为3-15个字母数字下划线的组合');
        return false;
    }
    canRegis = true;
});
// 验证密码是否正确
$('#registerPwd').blur(function() {
    $(this).next('.err-tips').text('');
    let registerPwd = $(this).val();
    if (!pwdreg.test(registerPwd)) {
        $(this).next('.err-tips').text('密码为6-15个字母数字下划线的组合');
        return false;
    }
});
// 验证密码是否相同
$('#registerPwd2').blur(function() {
    $(this).next('.err-tips').text('');
    let registerPwd = $('#registerPwd').val();
    let registerPwd2 = $(this).val();
    if (registerPwd !== registerPwd2) {
        $(this).next('.err-tips').text('两次密码不同');
        return false;
    }
});
// 验证注册
$('#register-submit').click(function() {
    var name = $('#registerName').val();
    var pwd = $('#registerPwd').val();
    var pwd2 = $('#registerPwd2').val();
    if (!canRegis) {
        return false;
    }
    if (!namereg.test(name)) {
        $('#registerName + .err-tips').text('用户名为3-15个字母数字下划线的组合');
        return false;
    }
    if (!pwdreg.test(pwd)) {
        $('#registerPwd + .err-tips').text('密码应该为6-15个字母数字下划线');
        return false;
    }
    if (pwd2 !== pwd) {
        $('#registerPwd2 + .err-tips').text('两次密码输入不一致');
        return false;
    }
    $('.err-tips').text('');
    // do register ...
    $.post('register', { userName: name, password: pwd }, (res) => {
        if (res.code !== '0') {
            alert(res.msg);
            return false;
        } else if (res.code === '0') {
            alert(res.msg);
            window.location.reload();
            $('.close').click();
        }
    });
});
// 验证登陆用户名是否正确
$('#loginName').blur(function() {
    $(this).next('.err-tips').text('');
    let loginName = $(this).val();
    if (!namereg.test(loginName)) {
        $(this).next('.err-tips').text('用户名为3-15个字母数字下划线的组合');
        return false;
    }
});
// 验证登陆密码是否正确
$('#loginPwd').blur(function() {
    $(this).next('.err-tips').text('');
    let loginPwd = $(this).val();
    if (!pwdreg.test(loginPwd)) {
        $(this).next('.err-tips').text('密码为6-15个字母数字下划线的组合');
        return false;
    }
});
$('#login-submit').click(function() {
    var name = $('#loginName').val();
    var pwd = $('#loginPwd').val();
    if (!namereg.test(name)) {
        $('#loginName + .err-tips').text('用户名为3-15个字母数字下划线的组合');
        return false;
    }
    if (!pwdreg.test(pwd)) {
        $('#loginPwd + .err-tips').text('密码为6-15个字母数字下划线的组合');
        return false;
    }
    // do login ...
    $.post('login', { userName: name, password: pwd }, (res) => {
        if (res.code !== '0') {
            alert(res.msg);
            return false;
        } else if (res.code === '0') {
            alert(res.msg);
            window.location.reload();
            $('.close').click();
        }
    });

});

$('.close').click(function() {
    $(this).parent('.dlg').animateCss('fadeOut');
    setTimeout(() => {
        $(this).parent('.dlg').removeClass('show');
    }, 740);
});

// 注销事件
$('#cancellation').click(function() {
    $.get('login/logOut',function (res) {

        if (res.code === '0') {
            alert(res.msg);
            window.location.href = 'index';
        } else {
            alert("注销失败");
        }

    })
});

// 前往个人中心
$('#userName').click(function () {
    window.location.href = 'userCenter';
});

// 用户名改变时清空密码
$("#loginName").bind("change", function () {
    $("#loginpwd").val("");
})

// 登陆弹框
function showMsg(msg) {
    $("#showMsg").html(msg);
    $("#showMsg").show();
    return true;
}