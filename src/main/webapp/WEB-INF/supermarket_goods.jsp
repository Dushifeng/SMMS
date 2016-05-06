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
    <script type="text/javascript" src="lib/js/elasticsearch.js"></script>
    <script type="text/javascript" src="lib/js/elasticsearch.jquery.js"></script>
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
            可订购货品
        </div>
        <div class="panel-body">
            <button class="btn btn-primary pull-left" data-toggle="modal" data-target="#searcher" >设置精确查找条件</button>
            <a class="btn btn-danger pull-right" href="applyforgoods">清除查询条件</a>
        </div>
        <table class="table">
            <tr>
                <th>商品条码</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>订购数量</th>
            </tr>
            <c:forEach items="${goods.content}" var="good">
            <tr>
            	
                <td>${good.barCode }</td>
                <td>${good.name }</td>
                <td>${good.price }</td>
                <td>
                    <div class="input-group" style="width: 100px">
                        <input type="text" class="form-control num" value="${cart[good.id] }" >
                        <input type="hidden" value="${good.id }" class="h_id">
                        <span class="input-group-addon">${good.unit }</span>
                    </div>
                </td>
            </tr>
            </c:forEach>
            
        </table>
        <ul class="pagination" style="margin-left: 40%">
        	<c:if test="${goods.firstPage==true }">
        		<li class="disabled" value="pre"><a href="#" >&laquo;</a></li>
        	</c:if>
        	<c:if test="${goods.firstPage!=true }">
        		<li value="pre"><a href="#" >&laquo;</a></li>
        	</c:if>
        	<c:forEach var="i" begin="1" end="${goods.totalNum }">
        		<c:if test="${i==pno }">
        			<li class="active"><a href="#">${i}</a></li>
        		</c:if>
        		<c:if test="${i!=pno }">
        			<li><a href="#">${i}</a></li>
        		</c:if>
        	</c:forEach>
  
            <c:if test="${goods.lastPage==true }">
        		<li class="disabled" value="next"><a href="#" >&raquo;</a></li>
        	</c:if>
        	<c:if test="${goods.lastPage!=true }">
        		<li value="next"><a href="#">&raquo;</a></li>
        	</c:if>
            
        </ul>
        <div class="panel-footer">
            <a class="btn btn-info btn-block" href="submitSelect">提交订单</a>
        </div>
    </div>
</div>

<!-- 模态声明，show 表示显示 -->
<div class="modal fade" tabindex="-1" id="searcher">
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
                <h4 class="modal-title">请设置查找条件</h4>
            </div>
            <!-- 主体 -->
            <form action="applyforgoods" method="post" id="sform">
            <div class="modal-body">

                <table class="table">
                    <tr>
                        <th>货品名称</th>
                        <td><input type="text" style="width: 200px" class="form-control" name="name" value="${name }"></td>
                    </tr>
                    <tr>
                        <th>货品编号</th>
                        <td><input type="text" style="width: 200px" class="form-control" name="barcode" value="${barcode }"></td>
                    </tr>
                    <tr>
                        <th>价格区间</th>
                        <td>
                            <div class="input-group" style="width: 400px;" >
                                <input type="text" class="form-control" name="minprice" value="${minprice }">
                                <span class="input-group-addon">元</span>
                                <span class="input-group-addon" style="background-color: transparent;border: none"> </span>
                                <span class="input-group-addon" style="background-color: transparent;border: none"> </span>
                                <span class="input-group-addon" style="background-color: transparent;border: none">到</span>
                                <span class="input-group-addon" style="background-color: transparent;border: none"> </span>
                                <span class="input-group-addon" style="background-color: transparent;border: none"> </span>
                                <input type="text" class="form-control" name="maxprice" value="${maxprice }">
                                <span class="input-group-addon">元</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>计量单位</th>
                        <td><input type="text" style="width: 60px" class="form-control" name="unit" value="${unit }"></td>
                    </tr>
                </table>
            </div>
            <!-- 注脚 -->
            <div class="modal-footer">
                <input type="submit" class="btn btn-info btn-block" value="确认查询" id="sub">
            </div>
            <input type="hidden" value="${pno}" id="h_pno" name="pno">
			<input type="hidden" value="${size}" id="h_size" name="size">
            </form>
        </div>
    </div>
</div>


</body>
<script>
    $(function(){
        $(".pagination > li").on("click",function(){
			var pno = $("#h_pno").val();
			var size = $("#h_size").val();
            if($(this).attr("value")=="pre"){
                pno=parseInt(pno)-1;    
            }else if($(this).attr("value")=="next"){
                pno=parseInt(pno)+1
            }else{
                pno=($(this).children()[0].text)
            }
            $("#h_pno").val(pno)
            $("#sform").submit();
            return false;
        })
        
        $("#sub").on("click",function(){
        	$("#h_pno").val(1);
        	$("#sform").submit();
        	return false;
        })
        
        
        $(".num").on("change",function(){
      	 	var id = $(this).next(":hidden").val();
      	 	var num=$(this).val();
            $.post("addItem", { "id": id, "num": num })
  
        })
    })

</script>
</html>
