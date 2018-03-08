<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新订单</title>
		<s:set value="#request.shopList" name="shopList"/>
		<s:set value="#request.receivers" name="receivers"/>
		<script type="text/javascript">
			function validate() {
				obj = document.getElementsByName("receiver_id");
				for(i=0;i<obj.length;i++) {//循环查找这个radio
					 if (obj[i].checked == true) {
						return true;
               		}
				}
				alert("尚未选择收件人信息!");
				return false;
			}
		</script>
		<style type="text/css">
			#total{
				font-size:180%;
				font-family:"楷体";
				color:#00F;
			}
			#submit{
				font-size:180%;
				font-family:"微软雅黑";
				color:#F0F;
			}
		</style>
	</head>
	
	<body>
		<center>	
			<h2 id="total">新订单</h2>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<s:form action="addUserOrder.action" method="POST" onsubmit="return validate();">
					<table border="1" width="90%">
						<tr height="30">
							<td align="center" valign="top">
								<s:if test="#receivers.size()==0">您还没有添加收件人信息。</s:if>
								<s:else>
									<br><h4>请选择收件人信息</h4>
									<s:iterator value="#receivers" id="rec">
										<table width="85%" border="0" cellspacing="3" cellpadding="3">
											<tr>
												<td rowspan="2" width="5%" align="center">
													<!--<s:radio name="receiver_id" list="%{#rec.id}" value="1" theme="simple" />-->
													<input type="radio" name="receiver_id" value="<s:property value="#rec.id"/>" />
												</td>
												<td width="40%">收件人姓名：<s:property value="#rec.name" /></td>
												<td width="40%">联系电话：<s:property value="#rec.phone" /><td>
												<td align="right">
													<a href="showReceiver.action?receiver.id=<s:property value="#rec.id"/>" 
							  							onClick="return check('修改')" target="_blank">
							  							修改
							  						</a>
							  					</td>
											</tr>
											<tr>
												<td colspan="4">收件地址：<s:property value="#rec.address" /></td>
											</tr>
											<tr><td colspan="5"><hr color="black"></td></tr>
										</table>
									</s:iterator>
								</s:else>
								<br><br>
								<table border="1" width="80%">
									<caption align="top">订单中包含的商品如下</caption>
									<tr>
										<th>商品名</th><th>属性</th><th>单价</th><th>数量</th><th>总价</th>
					  				</tr>
				  					<s:iterator value="#shopList" id="sl">
					  				<tr>
								  		<td align="center"><s:property value="#sl.goods.name"/></td>
								  		<td align="center"><s:property value="#sl.goods.attribute"/></td>
								  		<td align="center"><s:property value="#sl.price"/>元</td>
								  		<td align="center"><s:property value="#sl.amount"/></td>
								  		<td align="center"><s:property value="#sl.price*#sl.amount"/>元</td>
					  				</tr>
					  				</s:iterator>
								</table><br><br>
							</td>
						</tr>
						<tr>
							<td align="center" id="total"><b>购物总额： <s:property value="#session.newOrder.totalprices" />元</b></td>
						</tr>	
					</table><br><br>
		  			<s:submit value="确认付款" align="center" id="submit"></s:submit>
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