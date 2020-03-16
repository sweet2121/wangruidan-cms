<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的文章</title>

 </head>
<body>
	<c:forEach items="${info.list}" var="article">
		<div class="media">
			<img src="/pic/${article.picture }" class="mr-3 rounded" alt="..." style="height: 100px;width: 200px">
 			<div class="media-body">
    			<h5 class="mt-0">${article.title }</h5>
    			<div style="float: right;padding-top: 40px">
    				<!-- Button trigger modal -->
					<button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">
  						详情
					</button>
    			</div>
   			</div>
		</div>
		 <hr/>
	</c:forEach>
	
	<!-- 引入的标签必须放body里面  那样分页才会在下面 -->
	<jsp:include page="/WEB-INF/view/common/pages.jsp"/>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document" style="color: white;widows: 1200px">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">
        	<span id="title"></span>
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
//文章详情
function articleDetail(id) {
	//为模态框赋值，查询单个文章
	$.get("/my/articleDetail",{id:id},function(article){
		$("#title").append(article.title);
		$("#content").append(article.content);
	}) 
}
//分页
function goPage(page) {
	$("#center").load("/my/articles?page="+page);
}
</script>
</html>