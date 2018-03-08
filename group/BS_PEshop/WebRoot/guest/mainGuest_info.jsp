<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<s:set value="#session.custom" name="custom" />
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br><br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<h2 style="color:#158;">您好! <s:property value="#custom.username"/></h2>
				<h1 style="color:#58F;">这里是个人信息管理导航页</h1>
				<br><hr><br>
				
				<table cellspacing="5" cellpadding="5">
					<tr><td>
						<a href="showUserInfo.action">查看个人信息</a>
					</td></tr>
					<tr><td>	
						<a href="updateUserInfo.action">修改个人信息</a>
					</td></tr>
					<tr><td>	
						<a href="update_password.jsp">修改密码</a>
					</td></tr>
					<tr><td>	
						<a href="showReceivers.action">查看收件人信息列表</a>
					</td></tr>
					<tr><td>	
						<a href="mainGuest.jsp">返回商城主页</a>
					</td></tr>
				</table>
			</s:if>
			<s:else>
				<% response.setHeader("refresh","2;URL=/BS_PEshop/frame_main.html") ;%>
				<h1 style="color:#F00;">您还未登陆，请先登陆！！！</h1>
				<h3>两秒后自动跳转到登陆窗口！！！</h3>
				<h3>如果没有跳转，请按<a href="/BS_PEshop/frame_main.html">这里</a>！！！</h3>
			</s:else>
		</center>
	</body>
</html>