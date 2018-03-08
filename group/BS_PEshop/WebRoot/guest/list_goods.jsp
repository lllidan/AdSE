<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品清单</title>
		<s:set value="#request.goodsList" name="goodsList"/>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体";
			}
		</style>
	</head>
	
	<body>
		<center>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">
				<table border="0" width="100%" cellspacing="3" cellpadding="5">
					<tr height="30">
						<td align="center" valign="top">
							<s:if test="#goodsList.size()==0">没有商品</s:if>
							<s:else>
								<s:iterator value="#request.goodsList" id="gl">
									<table width="99%" border="0" cellspacing="5" cellpadding="5">
										<tr><td>
											商品名称：<s:property value="#gl.name" /><br>
											商品价格：<s:property value="#gl.price" /><br>	
											<a href="listGoodsInfo.action?goods.id=<s:property value="#gl.id" />" target="_blank">查看详细信息</a>
										</td></tr>
										<tr><td colspan="4"><hr color="black"></td></tr>
									</table>
								</s:iterator>
							</s:else>			
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
