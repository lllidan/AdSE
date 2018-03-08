<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Zy's Sport Club</title>
	</head>

	<body bgcolor="#E1E1E1">
		<center>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<table width="90%" border="0" cellpadding="0" cellspacing="0">
					<tr><td align="center" >
						<iframe id="main_top" src="mainGuest_top.jsp" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
					</td></tr>
				</table>
				<table width="90%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
					<tr>
						<td align="center" width="30%" height="49" valign="top">
							<h3><a href="showShopcar.action?order_id=0" target="_blank">
								查看购物车<img src="/BS_PEshop/images/shopCar.png" width="30%"/>
							</a></h3>
						</td>
						<td align="center" width="70%" height="100%" rowspan="2" bgcolor="white">
							<iframe id="listgoods" name="listgoods" src="listGoods.action" width="100%" height="100%" frameborder="1" scrolling="auto"></iframe>
						</td>
					</tr>
					<tr>
						<td valign="top" width="30%" background="/BS_PEshop/images/left_bk.jpg">
							<iframe id="listbrowsegoods" src="list_browsegoods.jsp" width="97%" height="383" frameborder="1" scrolling="auto"></iframe>
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
		</center>
	</body>
</html>