<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>订单信息管理</title>
		<s:set value="#request.orderInfo" name="orderInfo"/>
		<s:set value="#request.shopList" name="shopList"/>
		<s:set value="#request.distributeList" name="distributeList"/>
		<script type="text/javascript">
			function submit(id) {
				if(!confirm('您确定已经收到商品了吗？')) 
					return false;
				else
					//alert('showUserOrders.action?order_id='+id);
					window.location.href='confirmOrder.action?order_id='+id;
			}
		</script>
		<style type="text/css">
			tr{
				font-size:130%;
				font-family:"楷体"
			}
			#status{
				font-size:180%;
				font-family:"楷体";
				color:#00F;
			}
			#submit{
				font-size:120%;
				color:#742;
			}
		</style>
	</head>
	
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<h2>单号为：<s:property value="#orderInfo.id" /> 的订单内容如下：</h2>
				<a href="showUserOrders.action">返回订单管理主页</a>
				<hr><br>
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
			  				<s:if test="#orderInfo.status==0">订单已提交，请等待审核</s:if>
			  				<s:if test="#orderInfo.status==1">订单已通过审核，请等待发货</s:if>
			  				<s:if test="#orderInfo.status==2">订单正在运输途中，请耐心等待</s:if>
			  				<s:if test="#orderInfo.status==3">订单已完成</s:if>
			  			</td>
			  		</tr>
			  		
			  		<tr>
			  			<!-- 物流信息 -->
			  			<td colspan="5" align="center" id="status">
			  				<table border="0" width="90%">
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
							  	<tr><td colspan="2"><hr></td></tr>
			  				</s:iterator>
			  				</table>
			  			</td>
			  		</tr>
			  		<s:if test="#orderInfo.status==2">
			  		<tr><td colspan="5" align="center">
			  			<input type="button" value="确认收货" id="submit" onclick="return submit(<s:property value="#orderInfo.id" />)"/>
	  				</td></tr>
	  				</s:if>
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
