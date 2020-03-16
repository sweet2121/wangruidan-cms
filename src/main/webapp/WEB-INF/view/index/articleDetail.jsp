<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${article.title}</title>
<link href="/resource/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
	<!-- head -->	
	<div class="row">
		<div class="col-md-12" style="background-color: #222222;height: 35px">
			&nbsp;&nbsp;&nbsp;<font color="white" size="3px" style="line-height: 35px">${article.title}</font>
		</div>
	</div>
	<!-- 文章内容 -->
	<div class="row" style="margin-top: 15px">
		<div class="col-md-1"></div>
		<div class="col-md-8">
			<h2>${article.title}</h2>
			<p>${article.user.username}
				<fmt:formatDate value="${article.created }" 
				pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<!-- 是否收藏 -->
			<c:if test="${collect!=null }">
				<button type="button" onclick="deleteCollect()" class="btn btn-link">
					★&nbsp;&nbsp;取消收藏
				</button>
			</c:if>
			<c:if test="${collect==null }">
				<button type="button" onclick="collect(1)" class="btn btn-link">
					☆&nbsp;&nbsp;未收藏
				</button>
			</c:if>
			<hr/>
			
			${article.content}
			<hr/>
			<!-- 文章评论 -->
			<div>
				<h5><font color="tan">文章评论</font></h5>
				<textarea rows="8" cols="20"  style="width: 753px" name="content"></textarea><br/>
				<button type="button" onclick="addComment()" class="btn btn-outline-info">提交评论</button>
			</div>
			<div>
			<!-- 显示评论内容 -->
			<c:forEach items="${info.list }" var="comment">
				<h5><font color="tan">${comment.user.username} 
					<fmt:formatDate value="${comment.created}"
					pattern="yyyy-MM-dd HH:mm:ss"/></font></h5>
				 <hr/>
				 ${comment.content }
				
			</c:forEach>
			</div>
			
		</div>
			<div class="col-md-3">
			
				<div class="card" style="width: 18rem; margin-top: 6px">
					<div class="card-header">评论排行榜</div>
					<div class="card-body">
						<!-- 最新文章 --10篇 -->
						<c:forEach items="${info2.list}" var="article" varStatus="i">
							 <p>  ${i.count} ${article.title }</p>
							 <hr>
						</c:forEach>
					</div>
				</div>
			
			</div>
	</div>
</div>
<script type="text/javascript">
	//取消收藏
	function deleteCollect() {
		var id='${collect.id}';
		//alert(id);
		$.post("/deleteCollect",{id:id},function(flag){
			if(flag){
				alert("取消收藏--成功！");
				window.location.reload();
			}else{
				alert("取消收藏--失败！需要登录后才能取消收藏！");
			}
		})
	}
	//收藏文章
	function collect() {
		//文章标题
		var title='${article.title}';
		//文章的url
		var url=window.location.href;
		$.post("/collect",{text:title,url:url},function(flag){
			if(flag){
				alert("收藏成功！");
				window.location.reload();
			}else{
				alert("收藏失败！需要登录后才能收藏！");
			}
		})
	}
	//增加评论
	function addComment() {
		var articleId='${article.id}';
		var content=$("[name='content']").val();
		$.post("/addComment",{articleId:articleId,content:content},function(flag){
			if(flag){
				alert("评论成功");
				window.location.reload();
			}else{
				alert("评论失败，需要登录后才能评论")
			}
		})
	}
</script>
</body>
</html>