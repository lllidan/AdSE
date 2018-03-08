<%@ page language="java" pageEncoding="UTF-8"%>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.comments" name="comments"/>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>评论信息</h2>
				<a href="mainAdmin.jsp">返回导航页</a>
				<hr><br>
				<table width='90%' border='1' cellspacing="3" cellpadding="3">
					<tr>
						<th>ID</th><th>发表时间</th><th>商品名</th><th>作者</th>
						<th>操作</th>
		  			</tr>
	  				<s:iterator value="#comments" id="co">
	  				<tr>
				  		<td align="center"><s:property value="#co.id"/></td>
				  		<td align="center"><s:date name="#co.time" format="yyyy-MM-dd HH:mm:ss" /></td>
				  		<td align="center"><s:property value="#co.goods.name"/></td>
				  		<td align="center"><s:property value="#co.userInfo.username"/></td>
				  		<td align="center">
	  						<a href="showCommentInfo.action?comment.id=<s:property value="#co.id"/>" >
	  							查看详情
	  						</a>
	  					</td>
	  				</tr>
	  				</s:iterator>
	  			</table><br><br>
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
