<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:set value="#request.goodsInfo" name="goodsInfo" />
		<s:set value="#request.comments" name="comments" />
		<title>商品: <s:property value="#goodsInfo.name"/></title>
		<s:head theme="ajax"/>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体";
			}
			#comment{
				font-size:120%;
				font-family:"宋体";
			}
			#submit{
				width:100%;
				height:36px;
				font-size:21px;
				/*顺序为：上  右  下  左*/
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<h2>商品信息</h2>
				<a href="/BS_PEshop/guest/mainGuest.jsp">返回商城主页</a>
				<hr><br>
				<table border="1" width="65%" cellspacing="4" cellpadding="5" >	
					<tr>
						<td rowspan="2" align="center">
							<img id="img" height="180" src="getGoodsPic.action?goods.id=<s:property value="#goodsInfo.id"/>"><br>
						</td>
						<td rowspan="2" width="50%">
							商品名称：<s:property value="#goodsInfo.name"/><br>
							商品原价：<s:property value="#goodsInfo.listPrice"/>元<br>
							商品售价：<font color="red"><s:property value="#goodsInfo.price"/>元</font><br>
							商品类型：<s:property value="#goodsInfo.type"/><br>
							商品产地：<s:property value="#goodsInfo.field"/><br>
							销量：<s:property value="#goodsInfo.sale"/><br>
							库存:
							<s:if test="#goodsInfo.stock<=0">
								<font color="red">抱歉！暂时无货</font>
							</s:if>
							<s:else>
								<s:property value="#goodsInfo.stock"/><br>
							</s:else>
						</td>
						<s:if test="#goodsInfo.stock>0">
						<td align="center" width="20%">
							<s:form action="addShopcar.action" method="POST" theme="ajax">
								<s:hidden name="goods.id" value="%{#goodsInfo.id}" />
								<s:submit value="加入购物车" id="submit" executeScripts="true" targets="success"></s:submit>
								
							</s:form>
						</td>
						</s:if>
					</tr>
					<tr>
						<s:if test="#goodsInfo.stock>0">
						<td>
							<div id="success">&nbsp;</div>
						</td>
						</s:if>
					</tr>
					<tr valign="top">
						<td colspan="3" style="padding-left:60">
							<b>商品描述：</b><br>
							<s:property value="#goodsInfo.attribute"/><br>
						</td>
					</tr>
				</table>
				<!-- 评论区 -->
				<br>
				<h2>商品评论</h2>
				<hr color="black">
				<s:if test="#comments.size()==0"><h3>暂时无人评论</h3></s:if>
				<s:else>
					<s:iterator value="#request.comments" id="comment">
						<table width="80%">
							<tr id="comment">
								<th align="left">
									<s:if test="#comment.anonymity==0" >
										<s:property value="#comment.userInfo.username" />
									</s:if>
									<s:else>系统客户</s:else>
								</th>
								<td align="right"><s:date name="#comment.time" format="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr id="comment">
								<td colspan="2"><s:property value="#comment.content" /></td>
							</tr>
						</table>
						<hr width="80%">
					</s:iterator>
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