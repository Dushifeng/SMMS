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
    <link rel="stylesheet" href="lib/css/bootstrap.css">
    <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
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
    <div class="page-header">
        <h1>加盟超市</h1>
    </div>
    <div class="panel panel-success">

        <div class="panel-body">
            <button class="btn btn-primary" id="new" data-toggle="modal" data-target="#newSM">添加新加盟超市</button>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>超市名称</th>
                <th>地址</th>
                <th>联系人</th>
                <th>电话</th>
                <th>编号</th>
                <th>相关操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sms }" var="sm">
            	 <tr>
	                <td>${sm.name } <input type="hidden" value="${sm.id }"></td>
	                <td>${sm.address }</td>
	                <td>${sm.linkman }</td>
	                <td>${sm.phone }</td>
	                <td>${sm.no }</td>         
	                <td class="dropdown">
	                    <button class="btn btn-default" data-toggle="dropdown">
	                        点击查看
	                        <span class="caret"></span>
	                    </button>
	                    <ul class="dropdown-menu">
	                        <li><a href="#">添加新用户</a></li>
	                        <li><a href="#">删除用户</a></li>
	                        <li><a href="#">删除该超市(谨慎)</a></li>
	                        <li><a href="#">修改超市信息</a></li>
	                    </ul>
	                </td>
	            </tr>
            </c:forEach>
           
            </tbody>
        </table>
    </div>

    <!--增  增加新超市 名称 地址 初始化距离
	        增加新用户 账号 密码
	    *删  删超市 删用户
	    改
	    查!
    -->
    <div class="modal" tabindex="-1" id="newSM">
        <!-- 窗口声明 -->
        <div class="modal-dialog">
            <!-- 内容声明 -->
            <div class="modal-content">
                <!-- 头部 -->
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                    <h4 class="modal-title">添加超市</h4>
                </div>
                <!-- 主体 -->
                <div class="modal-body">
                	<input value="" type="hidden" id="id">
                    <table class="table" >
                        <thead>
                            <tr >                       
                                <td style="vertical-align: middle">超市名称</td>
                                <td colspan="2"><input class="form-control" type="text" id="sm_name"></td>
                            </tr>
                            <tr >
                                <td style="vertical-align: middle">超市编号</td>
                                <td colspan="2"><input class="form-control" type="text" id="sm_no"></td>
                            </tr>
                            <tr>
                                <td style="vertical-align: middle">超市地址</td>
                                <td colspan="2"><input class="form-control" type="text" id="sm_address"></td>
                            </tr>
                            <tr>
                                <td style="vertical-align: middle">联系人</td>
                                <td colspan="2"><input class="form-control" type="text" id="sm_man"></td>
                            </tr>
                            <tr>
                                <td style="vertical-align: middle">联系电话</td>
                                <td colspan="2"><input class="form-control" type="text" id="sm_phone"></td>
                            </tr>
                            <tr>
                                <td rowspan="3" style="vertical-align: middle">初始化距离</td>
                                <td>与仓库1 的距离</td>
                                <td class="input-group">
                                    <input type="text" class="form-control distance" wh="1">
                                    <span class="input-group-addon" style="width: 30px">千米</span>
                                </td>
                            </tr>
                            <tr>

                                <td>与仓库2 的距离</td>
                                <td class="input-group">
                                    <input type="text" class="form-control distance" wh="2">
                                    <span class="input-group-addon" style="width: 30px">千米</span>
                                </td>
                            </tr>
                            <tr>

                                <td>与仓库3 的距离</td>
                                <td class="input-group">
                                    <input type="text" class="form-control distance" wh="3">
                                    <span class="input-group-addon" style="width: 30px">千米</span>
                                </td>
                            </tr>
                        </thead>
                    </table>
                </div>
                <!-- 注脚 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" id="sub_new">
                        确认</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $("#sub_new").on('click',function(){
			var id=$("#id").val()
            var name = $("#sm_name").val()
            var address = $("#sm_address").val()
            var man = $("#sm_man").val()
            var phone = $("#sm_phone").val()
            var distance_wh = new Array()
			var distance_dis = new Array()
            var no = $("#sm_no").val()
            $(".distance").each(function(){
                var wh = $(this).attr("wh")
                var dis = $(this).val()
                distance_wh.push(wh)
                distance_dis.push(dis)
            })
            console.log(distance_dis)
            console.log(distance_wh)
            var param = {"id":id,"name":name,"no":no,"address":address,"man":man,"phone":phone,"wh":distance_wh,"dis":distance_dis}
            $.post("newOrUpdateSM",param,function(data){
				
            },"json")

        })
    })
</script>
</html>