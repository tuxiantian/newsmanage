<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function () {
	$(".delete").click(function(){
		
		var href=$(this).attr("href");
		$("#form1").attr("action",href).submit();
		return false;
	});
})
</script>
<style type="text/css">
tr:hover{background:#CCCCCC;}
</style>
<title>news list</title>
</head>
<body>
<form action="" method="post" id="form1">
<input type="hidden" name="_method" value="DELETE">
</form>
	<a href="${pageContext.request.contextPath }/item/input">添加新闻</a>
	<form action="${pageContext.request.contextPath }/item/search"  method="post">
	<input type="text" name="condition"><input type="submit" value="查询">
	</form>
	<c:if test="${empty requestScope.items }">
		<span>没有任何新闻信息。</span>
	</c:if>
	<c:if test="${!empty requestScope.items }">
		<table border="1">
			<th>ID</th>
			<th>标题</th>
			<th>类别</th>
			<th>状态</th>
			<th>是否删除</th>
			<th colspan="3">操作</th>
			<c:forEach items="${requestScope.items }" var="item">
				<tr>
					<td>${item.itemid }</td>
					<td>${item.title }</td>
					<td>${item.c.categoryName }</td>
					<td>${item.status==0 ? '正常':'屏蔽' }</td>
					<td>${item.deleteFlag==0 ? '否':'是' }</td>
					<td><a href="${pageContext.request.contextPath }/item/detail/${item.itemid }">查看</a></td>
					<td><a href="${pageContext.request.contextPath }/item/delete/${item.itemid }" class="delete">删除</a></td>
					<td><a href="${pageContext.request.contextPath }/item/input/${item.itemid }">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>