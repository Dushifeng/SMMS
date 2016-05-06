<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <title>用户登录</title>
    <link href="lib/css/index_style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="lib/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="lib/js/jquery.i18n.properties-1.0.9.js" ></script>
    <script type="text/javascript" src="lib/js/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="lib/js/jquery.validate.js"></script>
    <script type="text/javascript" src="lib/js/md5.js"></script>
    <script type="text/javascript" src="lib/js/page_login.js?lang=zh"></script>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
    <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
    <!--[if IE]>
    <script src="lib/js/html5.js"></script>
    <![endif]-->
    <!--[if lte IE 6]>
    <script src="lib/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
    <script>
        DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
    </script>
    <![endif]-->
</head>
<body style="">
<img style="position:absolute;left:0px;top:0px;width:100%;height:100%;z-Index:-1; border:1px solid blue" src="lib/img/index_bg.jpg" />
<div class="dataEye">

    <div class="page-header text-center">
        <h1>连锁超市物资分配调拨决策支持系统
        </h1>
    </div>
    <div class="loginbox">

        <div class="login-content">
            <div class="loginbox-title">
                <h3>登录</h3>
            </div>
            <form id="signupForm" action="login" method="post">
                <div class="login-error"></div>
                <div class="row">
                    <label class="field">用户名</label>
                    <input type="text" class="input-text-user input-click" name="username" id="email">
                </div>
                <div class="row">
                    <label class="field">密码</label>
                    <input type="password" class="input-text-password input-click" name="password" id="password">
                </div>

                <div class="row">
                    <label style="font-size: 20px">身份选择:</label><br>

                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary active">
                            <input type="radio" name="auth" autocomplete="off" value="0" checked> 总部
                        </label>
                        <label  class="btn btn-primary">
                            <input type="radio" name="auth" value="1" autocomplete="off"> 超市
                        </label>
                        <label  class="btn btn-primary">
                            <input type="radio" name="auth" value="2" autocomplete="off"> 仓库
                        </label>
                    </div>
                </div>
                <div class="row btnArea">
                    <input type="submit" class="login-btn" value="登录">
                </div>
                <div class="row tips">
                    <a href="forget.jsp" class="link">忘记密码</a>
                </div>
            </form>
        </div>

    </div>

</div>
<div class="loading">
    <div class="mask">
        <div class="loading-img">
            <img src="resources/images/loading.gif" width="31" height="31">
        </div>
    </div>
</div>
</body>
</html>