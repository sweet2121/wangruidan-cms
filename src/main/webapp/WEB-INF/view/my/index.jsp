 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条-个人中心</title>
<link rel="stylesheet" type="text/css"
	href="/resource/bootstrap.min.css" />
	<!-- 先引入jquery -->
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>

<script type="text/javascript" src="/resource/popper.min.js"></script>


<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
<!-- <style type="text/css">
a:hover {
    outline: 0;
    text-decoration: none;
}
a:visited {
    color: #999;
} -->

<script type="text/javascript">
	//为 li 添加点击事件
	$(function(){
		//默认显示我的文章
		$("#center").load("/my/articles");
		
		$("li").click(function(){
			var url=$(this).children().attr("data");
			//alert(url);
			//去除样式
			$("li").removeClass("active");
			//让当前点击的li 添加选中样式
			$(this).addClass("list-group-item active");
			//在中间区域显示url的内容
			$("#center").load(url);
		})
		
	})
</script>
</head>
<body>
<div class="container-fulid">
	<!-- 头 -->
	<div class="row">
		<div class="col-md-12"
			style="background-color: #593D7C; height: 60px">
			<img alt="" src="/resource/images/logo.jpeg"
				style="height: 55px; padding-top: 2px; padding-left: 4px" class="rounded"> <span
				style="color: white">今日头条-个人中心</span>
				
				<div style="float: right">
					
					<c:if test="${null!=sessionScope.user }">
						<div class="btn-group dropleft">
							<button  type="button" class="btn btn-link dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
								<font color="white" size="3px" style="line-height: 35px">登录信息</font>
							</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username }</a>
								<a class="dropdown-item" href="/my">个人中心</a>
								<a class="dropdown-item" href="/passport/logout">注销</a>
							</div>
						</div>
					</c:if>
			</div>
				
		</div>
	</div>
	<!-- body -->
	<div class="row" style="padding-top: 10px">
		<!--  左侧菜单 -->
		<div class="col-md-2 rounded"
			style="text-align: center;margin-left: 10px">

			<ul class="list-group">
				<li class="list-group-item active"><a href="#" data="/my/articles"><font color="tan">我的文章</font></a></li>
				<li class="list-group-item"><a href="#" data="/my/publish"><font color="tan">发布文章</font></a></li>
				<li class="list-group-item"><a href="#" data="/my/collects"><font color="tan">我的收藏</font></a></li>
				<li class="list-group-item"><a href="#"><font color="tan">我的评论</font></a></li>
				<li class="list-group-item"><a href="#"><font color="tan">个人信息</font></a></li>
				<li class="list-group-item"><a href="#" data="/my/publishVote"><font color="tan" >发起投票</font></a></li>
				<li class="list-group-item"><a href="#" data="/my/choose"><font color="tan">本月评选</font></a></li>
			</ul>

		</div>
		  <!-- 内容显示区域 -->
		<div class="col-md-9" id="center">
			<!-- 嵌入kindEdidor -->
			<dir style="display:none">
				<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
			</dir>
		  
		
		</div>

	</div>
</div>
</body>
</html>