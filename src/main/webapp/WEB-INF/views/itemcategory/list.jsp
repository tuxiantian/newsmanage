<%@page import="java.util.List"%>
<%@page import="com.tuxt.dao.ItemCategoryDao"%>
<%@page import="com.tuxt.domain.ItemCategorieInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
tr:hover{background:#CCCCCC;}
</style>
<title>类别</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/itemcategory/save" method="post">
		<table>
			<th>排序</th>
			<th>名称</th>
			<th colspan="2">管理</th>
				<%
				ItemCategoryDao itemcategorydao=new ItemCategoryDao();
					List<ItemCategorieInfo> olsInfos = itemcategorydao
							.GetCategoryTree();
					ItemCategorieInfo ociCurrent;
					ItemCategorieInfo ociNext;

					for (int i = 0; i < olsInfos.size(); i++) {
						ociCurrent = olsInfos.get(i);
						if (i < (olsInfos.size() - 1)) {
							ociNext = olsInfos.get(i + 1);
						} else {
							ociNext = null;
						}
				%>
			<tr>
				<td><input type="text" name="orderby" value="<%=(i + 1)%>"></td>
				<td style="padding-left:<%=(ociCurrent.getTreeLevel() - 1) * 20%>px">
					<%
						if (ociCurrent.getTreeLevel() != 1) {
								if (ociNext != null
										&& ociCurrent.getParent() == (ociNext
												.getParent())) {
									out.write("├ ");
								} else {
									out.write("└ ");
								}
							}
					%>
					<%= ociCurrent.getCategoryName()%>
				</td>
				<td><a href="${pageContext.request.contextPath }/itemcategory/input/<%=ociCurrent.getId()%>">添加子栏目</a></td>
				<td><a href="${pageContext.request.contextPath }/itemcategory/input2/<%=ociCurrent.getId()%>">修改栏目</a></td>
			</tr>
				<%
					}
				%>
		</table>
		<input type="submit" value="保存排序">
	</form>
</body>
</html>