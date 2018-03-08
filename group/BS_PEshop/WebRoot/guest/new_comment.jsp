<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>订单评论</title>
		<s:set value="#request.list" name="list"/>
		<s:set value="#request.orderId" name="orderId" />
		<s:head theme="ajax"/>
		<script type="text/javascript">
			function validate(id) {
				if(!confirm('跳过评论？')) 
					return false;
				else
					location.href='showUserOrder.action?order_id=' + id;
			}
		</script>
		<style type="text/css">
			textarea{
			    width:100%;			/*初始宽度*/
			    height:80px;		/*初始高度*/
			    max-height:180px;	/*最大高度*/
			    
			    overflow-y:auto;	/*让滚动条自适应，保证兼容性*/
			    resize:vertical;
			    /*both（表示横向纵向均可拉动）horizontal（表示只有横向可以拉动）vertical（表示只有纵向才可以拉动）none（禁止拉动）*/
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
	
	<body>
		<center>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<h3>订单评论</h3>
				<s:iterator value="#list" id="gl">
					<div id="comment<s:property value="#gl.goods.id" />">
	  				<s:form action="addGoodsComment.action" method="POST" theme="ajax">
						<table width="75%" border="0" cellspacing="5" cellpadding="5">
							<tr>
								<td colspan="2">
									<s:hidden name="goods.id" value="%{#gl.goods.id}" />
									商品名称：<s:property value="#gl.goods.name" /><br>
									商品价格：<s:property value="#gl.goods.attribute" /><br>
									<a href="listGoodsInfo.action?goods.id=<s:property value="#gl.goods.id" />" target="_blank">查看详细信息</a>
								</td>
							</tr>
							<tr>
								<td width="10%" align="center">评论：</td>
								<td>
									<s:textarea label="评论" name="comment.content" rows="4" theme="simple"/>
								</td>
							</tr>
							<tr><td colspan="2" align="right">
								<s:radio name="comment.anonymity" list="%{#{'1':'匿名评论','0':'不匿名'}}" value="1" theme="simple"></s:radio>
							</td></tr>
							<s:hidden name="order_id" value="%{#orderId}"></s:hidden>
							<s:submit value="提交评论" targets="comment%{#gl.goods.id}"></s:submit>
						</table>
					</s:form>
					</div>
					<hr width="75%">
				</s:iterator>
				<hr>
				<s:submit align="center" value="返回查看订单详情" id="submit" onclick="return validate('%{#orderId}');"></s:submit>
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