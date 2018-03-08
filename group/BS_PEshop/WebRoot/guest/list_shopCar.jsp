<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>购物车管理</title>
		<s:set value="#request.shopCar" name="shopCar"/>
		<script type="text/javascript">
			function validate() {
				obj = document.getElementsByName("shopCar_id");
			    value = [];
			    for(k in obj){
			        if(obj[k].checked)
			            value.push(obj[k].value);
			    }
			    //alert(value.length);
			    if(value.length > 0)
			    	return true;
			    alert("尚未选择要购买的商品!");
				return false;
			}
			function check(OGNL) {
				if(OGNL == -1)
					alert("Sorry,您下手慢了点，有商品已无货！");
				else if(OGNL == "删除") {
					if(!confirm("确认删除该商品？")) 
						return false;
					else
						return true;
				}
			}
			function submit(id, obj) {
				if(!confirm("确认进行修改？")) 
					return false;
				func = "amount"+id;
				amount = document.getElementById(func).value;
				obj.href = 'updateShopcar.action?goodsList.id=' + id + '&goodsList.amount=' + amount;
				return true;
			}
		</script>
		<style type="text/css">
			tr{
				font-size:140%;
			}
			.amount{
				text-align: center;
			}
			#submit{
				width:286px;
				height:36px;
				font-size:18px;
				margin:10px 10px 10px 10px;
				/*顺序为：上  右  下  左*/
			}
		</style>
	</head>
	
	<body bgcolor="#D9DFAA" onload="check('<s:property value="#request.order_id" />');">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<h2>购物车列表</h2>
				<a href="/BS_PEshop/guest/mainGuest.jsp">返回商城主页</a>
				<hr>
				<s:if test="#shopCar.size()==0"><h3>您还没有挑选商品到购物车中。</h3></s:if>
				<s:else>
					<s:form action="createUserOrder.action" method="POST" onsubmit="return validate();">
						<h3>您的购物车内容如下：</h3>
						<table width="96%" border="1" cellspacing="5" cellpadding="5">
							<tr>
								<th width="5%">&nbsp;</th><th width="10%">商品名</th><th width="15%">属性</th>
								<th width="10%">单价</th><th width="10%">数量</th><th width="15%">总价</th>
								<th colspan="3">操作</th>
				  			</tr>
			  				<s:iterator value="#shopCar" id="gl">
			  				<tr>
			  					<td align="center">
			  						<s:if test="#gl.amount!=0">
			  							<s:checkbox name="shopCar_id" theme="simple" value="false" disabled="false" fieldValue="%{#gl.id}"/>
			  						</s:if>
			  						<s:else>
			  							<s:checkbox name="shopCar_id" theme="simple" value="false" disabled="true" fieldValue="%{#gl.id}"/>
			  						</s:else>
			  					</td>
						  		<td align="center">
						  			<a href="listGoodsInfo.action?goods.id=<s:property value="#gl.goods.id" />" target="_blank">
						  				<s:property value="#gl.goods.name"/>
						  			</a>
						  		</td>
						  		<td align="center"><s:property value="#gl.goods.attribute"/></td>
						  		<td align="center"><s:property value="#gl.price"/>元</td>
						  		<td align="center">
									<s:if test="#gl.amount!=0">
										<s:textfield name="amount" value="%{#gl.amount}" id="amount%{#gl.id}" theme="simple" cssClass="amount"/>
									</s:if>
									<s:else><font color="red">缺货</font></s:else>
								</td>
								<td align="center"><s:property value="#gl.price*#gl.amount"/>元</td>
								<td align="center" width="10%">	
									<s:if test="#gl.amount!=0">
										<a href="updateShopcar.action" id="update" 
											onClick="return submit('<s:property value="#gl.id"/>', this);">
											修改数量
										</a>
									</s:if>
									<s:else>修改数量</s:else>
								</td>
								<td align="center" width="10%">	
									<a href="deleteShopcar.action?goodsList.id=<s:property value="#gl.id"/>" 
										onClick="return check('删除');">
										删除商品
									</a>
								</td>
			  				</tr>
			  				</s:iterator>
		  				</table><br><br>
			  			<s:submit value="结算付款" align="center" id="submit"></s:submit>
	  				</s:form>
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
