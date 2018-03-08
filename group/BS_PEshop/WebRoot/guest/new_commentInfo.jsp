<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:set value="#request.commentInfo" name="commentInfo"/>
		<style type="text/css">
			textarea{
			    width:100%;			/*初始宽度*/
			    height:80px;		/*初始高度*/
			    max-height:180px;	/*最大高度*/
			    
			    overflow-y:auto;	/*让滚动条自适应，保证兼容性*/
			    resize:vertical;
			    /*both（表示横向纵向均可拉动）horizontal（表示只有横向可以拉动）vertical（表示只有纵向才可以拉动）none（禁止拉动）*/
			}
		</style>
	</head>
	
	<body>
		<center>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<table width="75%" border="0" cellspacing="5" cellpadding="5">
					<tr>
						<td colspan="2">
							商品名称：<s:property value="#commentInfo.goods.name" /><br>
							商品价格：<s:property value="#commentInfo.goods.attribute" /><br>
							<a href="listGoodsInfo.action?goods.id=<s:property value="#commentInfo.goods.id" />" target="_blank">查看详细信息</a>
						</td>
					</tr>
					<tr>
						<td width="10%" align="center">评论：</td>
						<td>
							<s:textarea label="评论" name="comment.content" rows="4" readonly="true" value="%{#commentInfo.content}" theme="simple"/>
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