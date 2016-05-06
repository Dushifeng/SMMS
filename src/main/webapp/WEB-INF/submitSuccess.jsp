<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0" name="viewport">
    <script type="text/javascript" src="lib/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="lib/js/elasticsearch.js"></script>
    <script type="text/javascript" src="lib/js/elasticsearch.jquery.js"></script>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
    <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
    <title>Title</title>
    <style>
    </style>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-default" role="navigation">

    <div class="container">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header">
            <a class="navbar-brand" href="#" style="font-size: large">超市管理系统</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href=""><span class="glyphicon glyphicon-home">&nbsp;首页</span></a></li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-list-alt">
                    我的订单
                     </span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">新订单</a></li>
                        <li class="divider"></li>
                        <li><a href="#">已完成订单</a></li>
                    </ul>
                </li>
                <li><a href=""><span class="glyphicon glyphicon-barcode">&nbsp;货品申请</span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-user">&nbsp;超市信息</span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-envelope">&nbsp;站内信</span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-comment">&nbsp;问题帮助</span></a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <b style="margin-right: 20px">您好,${sm.name}</b>
                <button type="submit" class="btn btn-default">
                    登出
                </button>
            </form>
        </div>
    </div>
</div>
<div class="jumbotron" style="margin-top: 100px">
    <div class="container">
        <h2>订单提价成功</h2>
        <p>总部会及时处理！</p>
        <p><a href="#" class="btn btn-default">查看已完成订单</a></p>
    </div>
</div>

</body>
<script>
	
</script>
</html>