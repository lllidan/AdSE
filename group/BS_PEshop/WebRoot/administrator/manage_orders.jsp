<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.orderList" name="orderList"/>
	</head>
	
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>订单列表</h2>
				<a href="mainAdmin.jsp">返回导航页</a>
				<hr>
				<s:if test="#orderList.size()==0"><h3>暂时还没有订单。</h3></s:if>
				<s:else>
					<h3>现有全部订单如下：</h3>
					<table width="86%" border="1" cellspacing="5" cellpadding="5">
						<tr>
							<th>订单ID</th><th>生成时间</th><th>总价</th><th>订单状态</th>
							<th>操作</th>
			  			</tr>
		  				<s:iterator value="#orderList" id="order">
		  				<tr>
		  					<td align="center"><s:property value="#order.id"/></td>
					  		<td align="center"><s:date name="#order.time" format="yyyy-MM-dd HH:mm:ss" /></td>
					  		<td align="center"><s:property value="#order.totalprices"/></td>
					  		<td align="center">
					  			<s:if test="#order.status==0">待审核状态</s:if>
					  			<s:if test="#order.status==1">待发货状态</s:if>
					  			<s:if test="#order.status==2">待收货状态</s:if>
					  			<s:if test="#order.status==3">完成状态</s:if>
					  			<s:if test="#order.status==4">申请售后状态</s:if>
					  		</td>
					  		<td align="center" width="30%">
								<a href="showOrderInfo.action?order.id=<s:property value="#order.id" />" target="_blank">查看订单详情</a>
		  					</td>
		  				</tr>
		  				</s:iterator>
	  				</table>
		  		</s:else>
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
