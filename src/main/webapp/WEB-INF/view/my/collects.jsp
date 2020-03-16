<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resource/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
<script type="text/javascript">
	//取消收藏
	function qx(id) {
		$.post("deleteCollect",{id:id},function(flag){
			if(flag){
				alert("取消收藏成功");
				window.location.reload();
			}else{
				alert("取消收藏失败");
			}
		},"json")
	}
	//分页
	function goPage(page) {
		$("#center").load("my/collects?page="+page);
	}
</script>
</head>
<body>
 	<c:forEach items="${info.list }" var="collect">
 		<div class="media">
 			<div class="media-body">
 				<h5 class="mt-0">${collect.text}</h5>
 				收藏时间：<fmt:formatDate value="${collect.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-outline-info" onclick="qx(${collect.id})">取消收藏</button>
				<hr/>
 			</div>
 		</div>
 	</c:forEach>
 	
 	<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
</body>
</html>