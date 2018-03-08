<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<s:set value="#request.guest" name="guest" />
		<s:set value="#request.img" name="img" />
		<script type="text/javascript">
			function defau(OGNL){
				if(OGNL == "none"){
					document.getElementById("img").src = "/BS_PEshop/images/head_180.jpeg";
		  		}
			}
		</script>
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
	<body bgcolor="#D9DFAA" onload="defau('<s:property value="#img" />');">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">		
				<h2>客户个人信息</h2>
				<a href="mainGuest_info.jsp">返回导航页</a>
				<hr><br>
				<table width="60%" border="1" cellspacing="5" cellpadding="5">
					<tr>
						<td align="center">用户ID：</td>
						<td align="center"><s:property value="#guest.id" /></td>
					</tr>
					<tr>
						<td align="center">用户名：</td>
						<td align="center"> 
							<s:property value="#guest.username" />
						</td>
					</tr>
					<tr>
						<td align="center">姓名：</td>
						<td align="center"> 
							<s:if test="#guest.name==null">无名氏</s:if>
							<s:else><s:property value="#guest.name" /></s:else>
						</td>
					</tr>
					<tr>
						<td align="center">性别：</td>
						<td align="center">
							<s:if test="#guest.sex==1">男</s:if>
							<s:else>女</s:else>
						</td> 
					</tr>
					<tr>
						<td align="center">身份：</td>
						<td align="center">
							<s:if test="#guest.limits=='a'">管理员</s:if>
							<s:else>一般会员</s:else>
						</td>
					</tr>
					<tr>
						<td align="center">出生时间：</td>
						<td align="center"><s:date name="#guest.birthday" format="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td align="center">E-mail：</td>
						<td align="center">
							<s:if test="#guest.email==null">&nbsp;</s:if>
							<s:else><s:property value="#guest.email"/></s:else>
						</td>
					</tr>
					<tr>
						<td align="center">联系方式：</td>
						<td align="center">
							<s:if test="#guest.phone==null">&nbsp;</s:if>
							<s:else><s:property value="#guest.phone"/></s:else>
						</td>
					</tr>
					<tr>
						<td align="center">地址：</td>
						<td align="center">
							<s:if test="#guest.address==null">&nbsp;</s:if>
							<s:else><s:property value="#guest.address"/></s:else>
						</td>
					</tr>
					<tr>
						<td align="center">头像：</td>
						<td align="center"><br>
							<img id="img" src="getPic.action?user.id=<s:property value="#guest.id" />" width="150" /><br>
						</td>
					</tr>		
			 	</table>
			 	
				<s:form action="updateUserInfo.action" method="post">
					<s:submit value="修改信息" id="submit"/>
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