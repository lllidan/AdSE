<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理系统</title>
		<s:set value="#session.custom" name="custom" />
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
		</style>
	</head>
	<body>
		<center>
			<br><br><br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h1 style="color:#1FF;">您好! <s:property value="user.username"/>管理员, 欢迎您的登录 </h1>
				<h1 style="color:#58F;">这里是后台管理导航页</h1>
				<br><hr><br>
				<table cellspacing="5" cellpadding="5">
					<tr><td>
						<a href="showUsers.action">查看用户信息</a><br>
					</td></tr>
					<tr><td>
						<a href="showGoods.action">查看商品信息</a><br>
					</td></tr>
					<tr><td>
						<a href="showOrders.action">查看订单信息</a><br>
					</td></tr>
					<tr><td>
						<a href="showComments.action">查看评论信息</a><br>
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