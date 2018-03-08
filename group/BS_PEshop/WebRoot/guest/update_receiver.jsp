<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,java.text.*;" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<s:set value="#request.receiver" name="rec"></s:set>
		<style type="text/css">
			tr{
				font-size:130%;
				font-family:"楷体"
			}
			input{
				font-size:90%;
				font-family:"黑体"
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">		
				<h2><s:property value="#request.test"/>收件人信息</h2>
				<a href="mainGuest_info.jsp">返回导航页</a>
				<hr><br>
				<s:form action="%{#request.show}Receiver.action" method="POST" enctype="multipart/form-data" >
					<table border="1" cellspacing="5" cellpadding="8">
					
					<s:if test="#request.show=='add'">
						<s:textfield id="name" name="receiver.name" value="" label="收件人姓名" size="32" ></s:textfield>
						
						<s:textfield id="phone" name="receiver.phone" value="" label="联系电话" size="32" ></s:textfield>
						
						<s:textfield id="address" name="receiver.address" value="" label="收件地址" size="32" ></s:textfield>
					</s:if>
					<s:else>
						<s:textfield id="name" name="receiver.name" label="收件人姓名" value="%{#rec.name}" size="32" ></s:textfield>
						
						<s:textfield id="phone" name="receiver.phone" label="联系电话" value="%{#rec.phone}" size="32" ></s:textfield>
						
						<s:textfield id="address" name="receiver.address" label="收件地址" value="%{#rec.address}" size="32" ></s:textfield>
					</s:else>
					
					</table>
					<table cellspacing="5" cellpadding="8">	
						<tr>
							<td><s:submit value="%{#request.test}" align="center" theme="simple"/></td>
					  		<td><s:submit value="重置" action="showReceiver" align="center" theme="simple" /></td>
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
