<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/main.css">
<title>Insert title here</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath }/itemcategory/save2" method="post" modelAttribute="categorieInfo">
<c:if test="${categorieInfo.id!=0}">
<form:hidden path="id"/>
<input type="hidden" name="_method" value="PUT"/>
</c:if>
<table>
<tr><th>所属类别：</th>
<td><form:select path="parent.id"><option value="0">一级类别</option>
${categorylist }
</form:select></td></tr>
<tr><th>类别名称：</th>
<td><form:input path="categoryName"/></td></tr>
</table>
<input type="submit" value="提交">
</form:form>
</body>
</html>