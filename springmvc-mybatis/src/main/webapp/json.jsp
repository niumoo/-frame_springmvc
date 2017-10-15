<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json test</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.min.js" ></script>
</head>
<body>
<input type="button" value="请求json,输出json" onclick="requestJson()">
<input type="button" value="请求对象,输出json" onclick="responseJson()">
</body>
</html>
<script>
	// 请求是json,输出是json
	function requestJson(){
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/requestJson',
			contentType:'application/json;charset=utf-8',
			// 请求json
			data:'{"name":"手机","price":999}',
			// 返回json
			success:function(data){
				alert(data.name);
			}
		});
	}
	
	// 请求是key/value,输出是json
	function responseJson(){
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/responseJson',
			//contentType:'application/json;charset=utf-8',
			// 请求json
			data:'name=手机&price=999',
			// 返回json
			success:function(data){
				alert(data.name);
			}
		});
	}
</script>