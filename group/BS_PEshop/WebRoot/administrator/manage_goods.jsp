<%@ page language="java" pageEncoding="UTF-8"%>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
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
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>商品信息</h2>
				<a href="mainAdmin.jsp">返回导航页</a>
				<hr><br>
				<table width='90%' border='1'>
					<tr>
						<th>商品名称</th><th>商品属性</th><th>售价</th><th>定价</th>
						<!-- <th>类型</th><th>产地</th><th>库存</th><th>销量</th> -->
						<th>操作</th>
		  			</tr>
	  			<s:iterator value="#request.goodsList" id="gl">
	  				<tr>
				  		<td align="center"><s:property value="#gl.name"/></td>
				  		<td align="center"><s:property value="#gl.attribute"/></td>
				  		<td align="center"><s:property value="#gl.price"/></td>
				  		<td align="center"><s:property value="#gl.listPrice"/></td>
				  		<!-- 
				  		<td align="center"><s:property value="#gl.type"/></td>
				  		<td align="center"><s:property value="#gl.field"/></td>
				  		<td align="center"><s:property value="#gl.stock"/></td>
				  		<td align="center"><s:property value="#gl.sale"/></td>
				  		 -->
				  		<td align="center">
	  						<a href="showGoodsInfo.action?goods.id=<s:property value="#gl.id"/>" >
	  							详情
	  						</a> | 
							<!-- 删除该信息，这里用JavaScript来确定是否删除-->
	  						<a href="deleteGoods.action?goods.id=<s:property value="#gl.id"/>" 
	  							onClick="return check('删除')">
	  							删除
	  						</a>
	  					</td>
	  				</tr>
	  			</s:iterator>
	  			</table><br><br>
	  			<a href="showGoodsInfo.action?goods.id=0">新增商品信息</a><br>
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
