<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:set value="#request.distributeList" name="distributeList"/>
		<style type="text/css">
			#submit{
				font-size:120%;
				color:#742;
				margin:10px 10px 10px 10px;
			}
		</style>
	</head>
  
	<body>
		<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
		   	<table border="1" width="80%">
				<s:iterator value="#distributeList" id="dl">
					<tr>
						<td align="center" width="20%"><s:date name="#dl.time" format="yyyy-MM-dd HH:mm:ss" /></td>
		  		<td align="center">
		  			<s:if test="#dl.express!=null&&#dl.express!=''">快递公司:<s:property value="#dl.express"/>&nbsp;</s:if>
			  		<s:if test="#dl.courier!=null&&#dl.courier!=''">快递员:<s:property value="#dl.courier"/>&nbsp;</s:if>
			  		位置:<s:property value="#dl.location"/>&nbsp;
			  		<s:property value="#dl.delivery"/>
		  		</td>
		  		</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
			<% response.setHeader("refresh","2;URL=/BS_PEshop/frame_main.html") ;%>
			<h1 style="color:#F00;">您还未登陆，请先登陆！！！</h1>
			<h3>两秒后自动跳转到登陆窗口！！！</h3>
			<h3>如果没有跳转，请按<a href="/BS_PEshop/frame_main.html">这里</a>！！！</h3>
		</s:else>
	</body>
</html>
