<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<title>add news</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath }/item/save" method="post" modelAttribute="itemInfo">
<c:if test="${itemInfo.itemid!=0}">
<form:hidden path="itemid"/>
<input type="hidden" name="_method" value="PUT"/>
</c:if>
标题：<form:input path="title"/><br>
类别：<form:select path="c.id">${strcategory }</form:select><br>
内容：<form:textarea rows="20" cols="20" path="detail"></form:textarea><br>
<input type="submit" value="添加">
</form:form>

</body>
</html>