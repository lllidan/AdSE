<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,java.text.*;" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<s:set value="#session.custom" name="custom"></s:set>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
			input{
				font-size:90%;
				font-family:"黑体"
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA" onload="defau('<s:date name="#custom.birthday" format="yyyyMMdd"/>')">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<h2>修改密码</h2>
				<a href="mainGuest_info.jsp">返回导航页</a>
				<hr><br>
				<s:form action="updatePassword.action" method="POST" enctype="multipart/form-data">
					<table border="1" cellspacing="5" cellpadding="8">
						
						<s:password id="ori_password" name="user.password" label="原密码" size="32"></s:password>
						
						<s:password id="new_password" name="new_password" label="新密码" size="32"></s:password>
						
						<s:password id="check_password" name="check_password" label="重复新密码" size="32"></s:password>
	
					</table>
					<table cellspacing="5" cellpadding="8">	
						<tr>
							<td><s:submit value="修改" align="center" theme="simple"/></td>
					  		<td><s:reset value="重置" align="center" theme="simple" /></td>
						</tr>
					</table>
				</s:form>
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
