<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用Map来绑定参数测试</title>

</head>
<body> 
<!-- 使用Map来绑定参数测试 -->
<form id="itemForm" action="${pageContext.request.contextPath }/items/addItemsSubmit.action" method="post" >
	名称：<input type="text" name="itemsMap['name']"/><br/>
	价格：<input type="text" name="itemsMap['price']"/>
	<input  type="submit" value="添加" >
</form>
</body>

</html>