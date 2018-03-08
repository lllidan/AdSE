<%@ page language="java" pageEncoding="UTF-8"%>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<script language="javascript">
			function check(str){
				if(!confirm('您确定' + str + '该条信息吗？')) 
					return false;
				else 
					return true;
			}
		</script>
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
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<h2>收件人信息列表</h2>
				<a href="mainGuest_info.jsp">返回导航页</a>
				<hr><br>
				<table width='90%' border='1' cellspacing="5" cellpadding="8">
					<tr>
						<th>收件人姓名</th><th>联系电话</th><th>收件地址</th>
						<th>操作</th>
		  			</tr>
	  			<s:iterator value="#request.receivers" id="rec">
	  				<tr>
				  		<td align="center"><s:property value="#rec.name"/></td>
				  		<td align="center"><s:property value="#rec.phone"/></td>
				  		<td align="center"><s:property value="#rec.address"/></td>
				  		<td align="center">
	  						<a href="showReceiver.action?receiver.id=<s:property value="#rec.id"/>">
	  							修改</a>&nbsp; |&nbsp;
							<!-- 删除该信息，这里用JavaScript来确定是否删除-->
	  						<a href="deleteReceiver.action?receiver.id=<s:property value="#rec.id"/>" 
	  							onClick="return check('删除')">
	  							删除
	  						</a>
	  					</td>
	  				</tr>
	  			</s:iterator>
	  			</table><br><br>
	  			<a href="showReceiver.action?receiver.id=0">新增收件人信息</a><br>
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
