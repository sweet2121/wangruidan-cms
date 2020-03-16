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
<title>今日头条</title>
<link href="/resource/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resource/index.css" />
<script type="text/javascript" src="/resource/jquery-3.2.1.js"></script>

<script type="text/javascript" src="/resource/popper.min.js"></script>

<script type="text/javascript" src="/resource/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
	<!-- head -->	
	<div class="row">
		<div class="col-md-12" style="background-color: #222222;height: 35px">
			&nbsp;&nbsp;&nbsp;<font color="white" size="3px" style="line-height: 35px">下载APP</font>
			<!-- 登录注册 -->
			<div style="float: right">
				<!-- 从session获取当前有没有登录，如果用户已经登录，则不显示登录和注册 -->
				<c:if test="${null==sessionScope.user }">
					<button type="button" onclick="reg()" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
  						<font color="white" size="3px" style="line-height: 35px">注册</font>
					</button>
					<button type="button" onclick="login()" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
  						<font color="white" size="3px" style="line-height: 35px">登录</font>
					</button>
				</c:if>
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
	<div class="row">
		<!-- 左侧栏目 -->
		<div class="col-md-2" style="padding-top: 10px">
			<ul>
				<li style="margin-bottom: 10px">	<!-- href="/"刷新页面  -->
					<a class="channel" href="/">
						<img alt="" src="/resource/images/logo-index.png" style="width: 108px; height: 27px">
					</a>
				</li>
				<!-- 热点文章 -->
				<li>
					<a href="/?hot=1" 
						class="channel-item ${article.channelId==null?"active":""}">热点</a>
				</li>
				<!-- 遍历所有的栏目 -->
				<c:forEach items="${channels}" var="channel" varStatus="i">
						<li><a href="/?channelId=${channel.id }"  class="channel-item  ${article.channelId==channel.id?"active":""}">${channel.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<!-- 中间 -->
		<div class="col-md-7">
		<!-- 如果 栏目ID 为null 说明点击 左侧的栏目的热点  则显示轮播图 即广告 -->
		<c:if test="${article.channelId==null}">
			<div style="margin: 5px 5px 5px 5px">
					<div id="carouselExampleCaptions" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<c:forEach items="${slides }" var="slide" varStatus="i">
								<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }"
									class="active"></li>
							</c:forEach>	
						</ol>
						<div class="carousel-inner">
							<!-- 遍历轮播图 -->
							<c:forEach items="${slides }" var="slide" varStatus="i">
								<div class="carousel-item ${i.index==0?"active":"" }">
									<img src="/pic/${slide.url }" class="d-block w-100 rounded" alt="..."
										style="width: 350px; height: 320px">
									<div class="carousel-caption d-none d-md-block">
										<h5>${slide.title }</h5>
									</div>
								</div>
							</c:forEach>
							
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>

				</div>
			<hr>
		</c:if>
		
		<!-- 如果栏目ID 不为null 说明点击 左侧的栏目 -->
		<c:if test="${article.channelId!=null }">
			<!-- 显示子分类 -->
			<div class="subchannel">
				<ul>
					<li class="sub-item ${article.categoryId==null?"sub-selected":""}"><a href="/?channelId=${article.channelId }">全部</a></li>
					<c:forEach items="${categorys }" var="category">
						<li class="sub-item ${article.categoryId==category.id?"sub-selected":""} "><a href="/?channelId=${article.channelId }&categoryId=${category.id}">${category.name }</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
			<!-- 分类下的文章 -->
			<div>
				<c:forEach items="${info.list }" var="article">
					<div class="media">
  						<img src="/pic/${article.picture }" class="mr-3 rounded" alt="..." style="height: 105px;width: 190px">
  						<div class="media-body">
    						<h5 class="mt-0">
    							<a href="/articleDetail?id=${article.id}" target="_blank">${article.title }
    							</a>
    						</h5>
  							<p style="margin-top: 40px">${article.user.username }·
									${article.hits} 浏览 ·
								<fmt:formatDate value="${article.created}"
										pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
  						</div>
					</div>	
					<hr>
				</c:forEach>
				<!--文章分页 -->
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
			</div>
		</div>
		<!-- 右边 -->
		<div class="col-md-3">
			<div class="card" style="width: 18rem; margin-top: 6px">
					<div class="card-header">问卷调查</div>
					<div class="card-body">
						<!-- 问卷调查-->
						<c:forEach items="${voteArticles.list}" var="voteArticle">
							<div class="media">
							
								<div class="media-body">
									<p style="font-size: 12px"><a href="/voteDetail?id=${voteArticle.id }" target="_blank">${voteArticle.title }</a></p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
				<div class="card" style="width: 18rem;margin-top: 10px">
	 				 <div class="card-header" style="text-align: center">最新文章</div>
	    			  <div class="card-body">
						<!--提示最新的10篇文章  -->
						<c:forEach items="${lastArticles.list }" var="lastArticle">
							<div class="media">
			 				 	<img src="/pic/${lastArticle.picture }" style="width: 60px;height: 50px" class="mr-3 rounded" alt="...">
			  						<div class="media-body">
			    						<p style="font-size: 12px">${lastArticle.title }</p>
			   					 	</div>
							</div>
							<hr/>
						</c:forEach>
					</div>
				</div>
		</div>
	</div>
	
	<!-- 注册Modal/登录 -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalTitle"><span id="title"></span></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="passport">
		      </div>
		      
		    </div>
		  </div>
		</div>
	
</div>
<script type="text/javascript">

//注册
function reg() {
	$("#title").append("用户注册");
	$("#passport").load("/passport/reg");
}
//注册
function login() {
	$("#title").append("用户登录");
	$("#passport").load("/passport/login");
}
//分页
function goPage(page) {
	//栏目id
	var channelId ='${article.channelId}';
	//分类id
	var categoryId = '${article.categoryId}';
	var url = "/?channelId=" + channelId + "&page=" + page;
	//如果分类为空，则传递分类的查询条件
	if(categoryId!=""){
		url=url+"&categoryId="+categoryId;
	}
	location.href=url;
}
</script>
</body>
</html>