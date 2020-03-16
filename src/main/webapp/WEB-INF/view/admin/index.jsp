<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>今日头条-管理员中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
	<!-- 先引入jquery -->
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>

<script type="text/javascript" src="/resource/popper.min.js"></script>

<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fulid">
	<!-- 头 -->
	<div class="row" style="background-color: #029FD9; height: 60px">
		<div class="col-md-12">
			<img alt="" src="/resource/images/17.jpeg"
				style="height: 55px; padding-top: 2px; padding-left: 4px" class="rounded"> 
				<span style="color: white">今日头条-管理员中心</span>
		
					<div class="btn-group dropleft" style="float: right;">
						<button  type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
							<font color="white" size="3px" style="line-height: 35px">登录信息</font>
						</button>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">${sessionScope.admin.username }</a>
							<a class="dropdown-item" href="/passport/logout">注销</a>
						</div>
					</div>
		
		</div>
	</div>
	<!--  -->
	<div class="row">
		<!-- 左侧菜单 -->
		<div class="col-md-2" style="padding-top: 15px;margin-left: 10px">
			<div class="list-group" style="text-align: center">
				  <a href="#" class="list-group-item list-group-item-action list-group-item-primary" data="/admin/articles">文章审核</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-warning" data="/admin/users">用户管理</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-success">栏目管理</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-danger">分类管理</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-info">系统设置</a>
			</div>
		</div>
		<!-- 右侧内容-->
		<div class="col-md-9" id="center">
			
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
//点击样式的切换
$(function(){
	//默认显示我的文章
	$("#center").load("/admin/articles");
	
	$("a").click(function(){
		var url=$(this).attr("data");
		//alert(url);
		//去除样式
		$("a").removeClass("active");
		//让当前点击的li 添加选中样式
		$(this).addClass("list-group-item active");
		//在中间区域显示url的内容
		$("#center").load(url);
	})
	
})
</script>
</html>