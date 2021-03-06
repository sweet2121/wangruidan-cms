<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章选举 </title>
<link href="/resource/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px">${article.title }</font>
			</div>

		</div>

		<div class="row" style="margin-top: 10px">
			<div class="col-md-1"></div>
			<div class="col-md-7">
				<h2>${article.title }</h2>
				<p>
					发起人：${article.user.username} &nbsp; 发起时间：
					<fmt:formatDate value="${article.created}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</p>

				<c:forEach items="${mapChoose}" var="vote">
					<input type="radio" name="option" value="${vote.key}"> ${vote.key}: ${vote.value }
				  </c:forEach>
				<br> <br>
				<button type="button" class="btn btn-info" onclick="choose()">投票</button>
				<hr>
				<h2>投票结果</h2>
				<!-- 遍历投票结果 -->
				<c:forEach items="${choose}" var="v">
					<div class="progress">
						${v.option} :
						<div class="progress-bar" role="progressbar"
							style="width: ${v.percent}%;" aria-valuenow="${v.percent}"
							aria-valuemin="0" aria-valuemax="100">${v.percent}%</div>${v.optionNum }票
					</div>
					<br>
				</c:forEach>


			</div>

			<div class="col-md-4"></div>
		</div>

	</div>

	<script type="text/javascript">
	//增加评论
	function choose(){
		var articleId='${article.id}';
		var option=$("[name='option']:checked").val();
		if(option==null){
			alert("至少选中一个再投票");
			return;
		}
			
		$.post("/addChoose",{articleId:articleId,option:option},function(flag){
			if(flag){
				alert("投票成功");
				window.location.reload();
			}else{
				alert("投票失败，只能投票一次或登陆后才能投票");
			}
		})
		
	}
	
	</script>
</body>
</html>