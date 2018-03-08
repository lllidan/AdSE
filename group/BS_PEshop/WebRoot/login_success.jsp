<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>跳转中...</title>
		<s:set value="#session.custom" name="custom" />
		<script type="text/javascript">
			function go(){
				var url="${sessionScope.custom.limits}";
				if(url=='g')
					location.href='/BS_PEshop/guest/mainGuest.jsp';
				else
					location.href='/BS_PEshop/administrator/mainAdmin.jsp';
			}
		</script>
	</head>
	<body onload="setTimeout(go,3000);">
		<center>
			<br><br><br><br>
			<s:if test="#custom.limits=='g'.toString()">
				<h2 style="color:#1F1;">您好! <s:property value="#custom.username"/>, 欢迎您的登录 </h2>
				<h1 style="color:#58F;">3秒后自动转到商场主页</h1>
				<h2>如果没有跳转，请按<a href="/BS_PEshop/guest/mainGuest.jsp">这里</a></h2>
			</s:if>
			<s:else>
				<h2 style="color:#1F1;">管理员您好,欢迎您的登录 </h2>
				<h1 style="color:#58F;">3秒后自动转到管理主页</h1>
				<h2>如果没有跳转，请按<a href="/BS_PEshop/administrator/mainAdmin.jsp">这里</a></h2>
			</s:else>
		</center>
	</body>
</html>