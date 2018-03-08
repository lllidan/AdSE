<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.orderInfo" name="orderInfo"/>
		<s:set value="#request.shopList" name="shopList"/>
		<s:set value="#request.distributeList" name="distributeList"/>
		<s:head theme="ajax"/>
		<style type="text/css">
			#status{
				font-size:180%;
				font-family:"楷体";
				color:#00F;
			}
			#submit{
				font-size:120%;
				color:#742;
				margin:10px 10px 10px 10px;
			}
		</style>
	</head>
	
	<body>
		<center>
			<br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>单号为<s:property value="#orderInfo.id" />的订单内容如下：</h2>
				<table width="86%" border="1" cellspacing="4" cellpadding="4">
					<tr>
						<th>收件人姓名</th><th>联系电话</th><th>收件地址</th><th>总价</th><th>下单时间</th>
		  			</tr>
	  				<tr>
	  					<td align="center" width="15%"><s:property value="#orderInfo.name"/></td>
				  		<td align="center" width="20%"><s:property value="#orderInfo.phone"/></td>
				  		<td align="center"><s:property value="#orderInfo.address"/></td>
				  		<td align="center" width="15%"><s:property value="#orderInfo.totalprices"/></td>
				  		<td align="center" width="18%"><s:date name="#orderInfo.time" format="yyyy-MM-dd HH:mm:ss" /></td>
	  				</tr>
	  				<tr><td colspan="5" align="center"><br><h3>订单中包含的商品：</h3></td></tr>
					<tr><td colspan="5" align="center">
						<table width="96%" border="1" cellspacing="5" cellpadding="5">
			  				<tr>
								<th>商品名</th><th>属性</th><th>单价</th><th>数量</th><th>总价</th>
								<th>操作</th>
				  			</tr>
			  				<s:iterator value="#shopList" id="sl">
			  				<tr>
						  		<td align="center"><s:property value="#sl.goods.name"/></td>
						  		<td align="center"><s:property value="#sl.goods.attribute"/></td>
						  		<td align="center"><s:property value="#sl.price"/></td>
						  		<td align="center"><s:property value="#sl.amount"/></td>
						  		<td align="center"><s:property value="#sl.price*#sl.amount"/></td>
						  		<td align="center" width="30%">
									<a href="listGoodsInfo.action?goods.id=<s:property value="#sl.goods.id" />" target="_blank">查看商品信息</a>
			  					</td>
			  				</tr>
			  				</s:iterator>
			  			</table>
			  		</td></tr>
			  		<tr>
			  			<td colspan="5" align="center" id="status">
			  				<br>
			  				<s:if test="#orderInfo.status==0">订单为待审核状态<br>
			  					<a href="updateOrderInfo.action?order.id=<s:property value='#orderInfo.id' />&order.status=1">通过审核</a>
			  				</s:if>
			  				<s:if test="#orderInfo.status==1">订单为待发货状态<br>
			  					<a href="updateOrderInfo.action?order.id=<s:property value='#orderInfo.id' />&order.status=2">确认发货</a>
			  				</s:if>
			  				<s:if test="#orderInfo.status==2">订单为待收货状态<hr>
			  					<s:form action="addDistributeInfo.action" method="POST" theme="ajax">
			  						<input type="hidden" name="order.id" value="<s:property value='#orderInfo.id' />" />
				  					<table border="1">
				  						<s:textfield name="distribute.express" label="请输入快递公司名" size="32"></s:textfield>
				  						<s:textfield name="distribute.courier" label="请输入快递员名字(选填)" size="32"></s:textfield>
				  						<s:textfield name="distribute.location" label="请输入货物位置" size="32"></s:textfield>
				  						<s:textfield name="distribute.delivery" label="请输入配送信息" size="32"></s:textfield>
				  						<s:submit value="更新" align="center" id="submit" targets="show"></s:submit>
				  					</table>
			  					</s:form>
			  				</s:if>
			  				<s:if test="#orderInfo.status==3">订单已完成</s:if>
			  			</td>
			  		</tr>
			  		<tr>
			  			<!-- 物流信息 -->
			  			<td colspan="5" align="center" id="status">
			  				<div id="show">
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
			  				</div>
			  			</td>
			  		</tr>
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
