<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>订单信息管理</title>
		<s:set value="#request.orders" name="orders"/>
		<style type="text/css">
			tr{
				font-size:130%;
				font-family:"楷体"
			}
			#title{
				font-size:150%;
				font-family:"微软雅黑";
			}
		</style>
	</head>
	
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<h2>订单信息列表</h2>
				<a href="/BS_PEshop/guest/mainGuest.jsp">返回商城主页</a>
				<hr><br>
				<table width="80%" border="1" cellspacing="5" cellpadding="8">
					<s:if test="#orders.size()==0">
						<caption id="title">您还没有订单。</caption>
					</s:if>
					<s:else>
						<caption id="title">您的全部订单如下：</caption>
						<tr>
							<th>订单ID</th><th>生成时间</th><th>总价</th><th>订单状态</th>
							<th>操作</th>
			  			</tr>
		  				<s:iterator value="#orders" id="order">
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
								<a href="showUserOrder.action?order_id=<s:property value="#order.id" />" target="_blank">查看订单详情</a>
		  					</td>
		  				</tr>
		  				</s:iterator>
		  			</s:else>
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
