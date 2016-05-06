<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <b style="margin-right: 20px">您好,${sm.name }</b>
                <button type="submit" class="btn btn-default">
                    登出
                </button>
            </form>
        </div>
    </div>
</div>
<div class="container" style="margin-top: 100px">
    <div class="panel panel-default">
        <div class="panel-heading">
            订单详情页
        </div>
		<form action="submitOrder" method="post">
		<div class="panel-body">
            <div class="well-sm">
                <h4>申请信息</h4>
            </div>
            
            <table class="table table-responsive">

                <tr>
                    <th>申请单位</th>
                    <td>${sm.name}</td>
                </tr>
                <tr>
                    <th>申请人</th>
                    <td>${sm.currentUser.name }</td>
                </tr>
            
                <tr>
                    <th>订单总价</th>
                    <td id="sum"></td>
                </tr>
                <tr>
                    <th>备注</th>
                    <td>
                        <textarea class="form-control" name="desc">

                        </textarea>
                    </td>
                </tr>

            </table>
            <div class="well-sm">
                <h4>订单明细</h4>
            </div>
        </div>

        <table class="table">

            <tr>
                <th>商品条码</th>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>订购数量</th>
                <th>单品总价</th>
            </tr>
            <c:forEach items="${order}" var="item">
            	<tr >
	                <td>${item.goods.barCode}</td>
	                <td>${item.goods.name}</td>
	                <td>${item.goods.price}</td>
	                <td>${item.needNum}</td>
	                <td class="item_price">${item.price}</td>
            	</tr>
            </c:forEach>
            
        

        </table>

        <div class="panel-footer">
        	<input type="submit" value="确认提交" class="btn btn-info btn-block">
   
        </div>
		</form>
        
    </div>
</div>

</body>
<script>
    $(function(){
        var sum = 0;
        $(".item_price").each(function(){
        	sum+=parseFloat($(this).text())
        })
        $("#sum").text(sum.toFixed(2))
    })
</script>
</html>