<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:set value="#session.custom" name="custom"/>
	</head>
	<body background="/BS_PEshop/images/top.jpg">
		<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
			<table width="100%" border="0" cellpadding="2" cellspacing="3">
				<tr>
					<td rowspan="3" align="left">
						<h1><i>&nbsp;&nbsp;Zy's Sport Club</i></h1>
					</td>
					<td align="right" width="30%">
						<s:property value="#custom.username"/>
						<s:if test="#custom.sex==0">女士</s:if>
						<s:else>先生</s:else> 您好! | 
						<a href="../frame_main.html" target="_top">注销</a>
					</td>
				</tr>
				<tr>
					<td align="right">
						<s:if test="#custom.pic!=null" >
							<img id="img" src="getPic.action?user.id=<s:property value="#custom.id"/>" height="70" />
						</s:if>
						<s:else><img id="img" src="/BS_PEshop/images/head_180.jpeg" height="70" /></s:else>
						&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center" width="30%">
						<table>
							<tr>
								<td align="center" width="15%">
									<a href="mainGuest_info.jsp" target="_blank">信息管理</a>
								</td>
								<td align="center" width="15%">
									<a href="showUserOrders.action" target="_blank">订单管理</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
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
