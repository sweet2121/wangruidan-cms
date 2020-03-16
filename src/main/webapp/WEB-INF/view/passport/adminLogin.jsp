<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="/resource/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resource/jquery/screen.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<span style="color: red">${msg }</span>
	<form id="form1">
		<div class="form-group">
			<label for="username">用户名</label>
			<input id="username" type="text" class="form-control" name="username">
		</div>
		<div class="form-group">
			<label for="password">密码</label>
			<input id="password" type="password" class="form-control" name="password">
		</div>
		
		<div class="form-group">
			<button type="submit" class="btn btn-outline-info">登录</button>
			<button type="reset" class="btn btn-outline-warning">重置</button>
			<span id="msg"></span>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$("#form1").validate({
			//定义规则
			rules:{
				username:{
					required:true,//用户名不能为空
				},
				password:{
					required:true,//密码不能为空
				}
			},
			//定义消息提示
			messages:{
				username:{
					required:"密码不能为空",
				},
				password:{
					required:"密码不能为空",
				}
			},submitHandler:function(flag){
				//如果校验通过。则执行登录
				$.post("/passport/login",$("#form1").serialize(),function(result){
					if(result.code==200){
						//alert("恭喜登录成功！");
						// $("#msg").html("<font color='red'>恭喜登录成功</font>");
						location.href="/admin";  //进入后台
					}else{
						//alert(result.setMsg());
						$("#msg").html("<font color='red'>"+result.msg+"</font>");
					}
				})
			}
		})
	})
</script>
</body>
</html>