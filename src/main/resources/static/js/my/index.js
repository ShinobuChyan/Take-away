// 为页面添加动态背景
function random_move() {
    $.magicCanvas.draw({
        type: "random-move",
        rgb: function(circlePos) {
            var px = circlePos.x;
            var py = circlePos.y;
            // do some computation....
            return {
                r: parseInt(px % 255),
                g: parseInt(py % 255),
                b: 203
            };
        }
    })
};
random_move();

var canLogin = false;
var canRegis = false;

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

/*
 *进行表单验证
 */
// 用户名和密码的正则
var namereg = /^[a-z0-9_]{3,15}$/;
var pwdreg = /^[a-z0-9_]{6,15}$/;
var tips = $('#error-tips');
// 注册操作
// 验证注册用户名是否存在
$('#registerName').blur(function() {
    $(this).next('.err-tips').text('');
    let registerName = $(this).val();
    if (namereg.test(registerName)) {
        $.post('register/verify', { userName: registerName }, (res) => {
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
        if (res.code === '1') {
            alert(res.msg);
            return false;
        } else if (res.code === '0') {
            // 登陆成功
            $('.close').click();
        }
    });
});
$('#login-submit').click(function() {
    var name = $('#loginName').val();
    var pwd = $('#loginPwd').val();
    if (!namereg.test(name)) {
        tips.text('用户名应该为长度为3-15的字母数字下划线的组合');
        tips.show();
        return false;
    }
    if (!pwdreg.test(pwd)) {
        tips.text('密码应该为长度为6-15的字母数字下划线的组合');
        tips.show();
        return false;
    }
    // do login ...

});


$('input').focus(function() {
    tips.hide();
    tips.text('');
});



$('.close').click(function() {
    $(this).parent('.dlg').animateCss('fadeOut');
    setTimeout(() => {
        $(this).parent('.dlg').removeClass('show');
    }, 740);
});

// 触发搜索事件
$('#search-btn').click(function() {
    if ($('#search-input').val() === '') {
        return;
    }
    search($('#search-input').val());
});
$('#search-input').keydown(function(event) {
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
    document.location.href = 'main.html?search=' + searchStr;
}