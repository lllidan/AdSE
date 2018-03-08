<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.user" name="user" />
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
			#submit{
				width:286px;
				height:36px;
				font-size:18px;
				margin:10px 10px 10px 10px;
				/*顺序为：上  右  下  左*/
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>客户信息</h2>
				<a href="showUsers.action">返回客户列表</a>
				<hr><br>
				<table width="60%" border="1"  cellspacing="5" cellpadding="5">
					<tr>
						<td>用户ID：</td>
						<td> <s:property value="#user.id" /></td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td> 
							<s:property value="#user.username" />
						</td>
					</tr>
					<tr>
						<td>姓名：</td>
						<td> 
							<s:if test="#user.name==null">无名氏</s:if>
							<s:else><s:property value="#user.name" /></s:else>
						</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td> 
							<s:if test="#user.sex==1">男</s:if>
							<s:else>女</s:else>
						</td> 
					</tr>
					<tr>
						<td>身份：</td>
						<td>
							<s:if test="#user.limits=='a'">管理员</s:if>
							<s:else>一般会员</s:else>
						</td>
					</tr>
					<tr>
						<td>出生时间：</td>
						<td><s:date name="#user.birthday" format="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>E-mail：</td>
						<td>
							<s:if test="#user.email==null">&nbsp;</s:if>
							<s:else><s:property value="#user.email"/></s:else>
						</td>
					</tr>
					<tr>
						<td>联系方式：</td>
						<td>
							<s:if test="#user.phone==null">&nbsp;</s:if>
							<s:else><s:property value="#user.phone"/></s:else>
						</td>
					</tr>
					<tr>
						<td>地址：</td>
						<td>
							<s:if test="#user.address==null">&nbsp;</s:if>
							<s:else><s:property value="#user.address"/></s:else>
						</td>
					</tr>		
			 	</table>
			 	<s:form action="deleteUser.action" method="post">
					<s:submit value="删除该用户" id="submit" theme="simple"/>
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