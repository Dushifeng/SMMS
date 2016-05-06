<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0" name="viewport">
    <script type="text/javascript" src="lib/js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="lib/css/bootstrap.css">
    <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/js/bootstrap-table-expandable.js"></script>
    <link rel="stylesheet" href="lib/css/bootstrap-table-expandable.css">
    <title>Title</title>
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
            <li><a href=""><span class="glyphicon glyphicon-home">&nbsp;首页</span></a></li>
            <li  class="active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-list-alt">
                    订单查看
                     </span>
                    <b class="caret"></b>
                    </a>
                <ul class="dropdown-menu">
                    <li><a href="neworder">新订单</a></li>
                    <li class="divider"></li>
                    <li><a href="admin_showOldOrder">已完成订单</a></li>
                </ul>
            </li>
            <li><a href="admin_showAllGoods"><span class="glyphicon glyphicon-barcode">&nbsp;货品储备查看</span></a></li>
            <li><a href="admin_showAllSM"><span class="glyphicon glyphicon-user">&nbsp;加盟超市查看</span></a></li>
            <li><a href=""><span class="glyphicon glyphicon-envelope">&nbsp;站内信</span></a></li>
            <li><a href=""><span class="glyphicon glyphicon-comment">&nbsp;问题帮助</span></a></li>
        </ul>
            <form class="navbar-form navbar-right">
                <b style="margin-right: 20px">您好,XXX</b>

                <button type="submit" class="btn btn-default">
                    登出
                </button>
            </form>
        </div>

    </div>
</div>
<div class="container" style="margin-top: 100px">
    <h1>新订单统计</h1>
    <form action="dealOrder" method="post">
    <input type="hidden" name="size" value="cc">
         <table class="table table-expandable">
        <thead>
        <tr>
            <th>订单编号</th>
            <th>申请日期</th>
            <th>申请单位</th>
            <th>总价值</th>
            <td>期望处理方式</td>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${orders }" var="order">
         <tr class="it">
            <td>${order.id }</td>
            <td><fmt:formatDate value="${order.createTime }" pattern="yyyy/MM/dd hh:mm"/></td>
            <td>${order.sm.name }</td>
            <td class="sum"></td>
            <td class="ignore">
                <label class="radio-inline" >
                    <input type="radio" name="${order.id }" value="p">优先分配
                </label>
                <label class="radio-inline" >
                    <input type="radio" name="${order.id }" value="c">按比例分配
                </label>
                <label class="radio-inline"  >
                    <input type="radio" checked name="${order.id }" value="m">满足或放弃
                </label>
                <label class="radio-inline " >
                    <input type="radio" name="${order.id }" value="r">拒绝申请
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <table class="table table-responsive">
					
                    <tr>
                        <th>申请单位</th>
                        <td>${order.sm.name }</td>
                    </tr>
                    <tr>
                        <th>申请人</th>
                        <td>${order.proposer.name }</td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td>
                        ${order.desc }
                        </td>
                    </tr>
                </table>
                <table class="table">
                    <tr>
                        <th>商品条码</th>
                        <th>商品名称</th>
                        <th>商品单价</th>
                        <th>订购数量</th>
                        <th>单品总价</th>
                    </tr>
                    <c:forEach items="${order.order }" var="o">
                    	<tr>
	                         <td>${o.goods.barCode }</td>
	                         <td>${o.goods.name }</td>
	                         <td>${o.goods.price }</td>
	                         <td>${o.needNum }</td>
	                         <td>${o.price }</td>
                   		 </tr>
                    </c:forEach>
  
                </table>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <input class="btn btn-block btn-info"   autocomplete="off" type="submit" value="提交处理">
        
    </form>
   
</div>
</body>
<script>
    
</script>

</html>