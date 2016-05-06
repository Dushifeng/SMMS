<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <li class="active"><a href=""><span class="glyphicon glyphicon-home">&nbsp;首页</span></a></li>
                <li>
                    <a href="#"> <span class="glyphicon glyphicon-list-alt"></span>&nbsp;我的订单</a>

                </li>
                <li><a href=""><span class="glyphicon glyphicon-barcode">&nbsp;货品申请</span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-user">&nbsp;超市信息</span></a></li>
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
    <div class="page-header">
        <h1>我的订单</h1>
    </div>
    <table class="table table-expandable" >
        <thead>
            <tr>
                <th>订单编号</th>
                <th>申请时间</th>
                <th>申请人</th>
                <th>总价值</th>
                <th>处理时间</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${orders }" var="order">
        		<tr>
                	<td>${order.id }</td>
               		<td><fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd hh:mm"/></td>
                	<td>${order.proposer }</td>
                	<td></td>
                	<td>
                		<c:if test="${order.dealedTime==null }">
                			该订单尚未处理
                		</c:if>
                		<c:if test="${order.dealedTime!=null }">
                			${order.dealedTime}
                		</c:if>
                	</td>
            	</tr>
            <tr>
                <td colspan="5">
                    <table class="table">
                        <tr>
                            <th>商品条码</th>
                            <th>商品名称</th>
                            <th>商品单价</th>
                            <th>订购数量</th>
                            <c:if test="${order.dealedTime!=null }">
                           		<th>实际分配数量</th>
                            </c:if>
                            <th>单品预期总价</th>
                            <c:if test="${order.dealedTime!=null }"><th>应付总价</th></c:if>
                        </tr>
                        <c:forEach items="${order.order }" var="o">
                        <tr>
                            <td>${o.goods.barCode }</td>
                            <td>${o.goods.name }</td>
                            <td>${o.goods.price }</td>
                            <td>${o.needNum }</td>
                             <c:if test="${order.dealedTime!=null }">
                           		<td>${o.goods.realNum }</td>
                            </c:if>
                            <td>${o.price }</td>
                            <c:if test="${order.dealedTime!=null }"><th>xxxx</th></c:if>
                        </tr>   
                        </c:forEach>
                    </table>
                    <button class="btn btn-danger btn-block disabled">撤销订单</button>
            	</tr>
        	</c:forEach>
            
        </tbody>
    </table>

</div>
</body>
</html>