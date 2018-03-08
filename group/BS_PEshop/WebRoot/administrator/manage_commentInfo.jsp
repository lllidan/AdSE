<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,java.text.*;" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.commentInfo" name="ci"></s:set>
		<script type="text/javascript">
			function validate() {
				if(!confirm("确认删除该评论吗？"))
					return false;
				else
					return true;
			}
		</script>
		<style type="text/css">
			tr{
				font-size:130%;
				font-family:"楷体"
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
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2>评论信息</h2>
				<a href="showComments.action">返回评论列表页</a>
				<hr><br>
				<table width="65%" border="1" cellspacing="5" cellpadding="8">
					<tr>
						<th>评论ID</th><td><s:property value="#ci.id" /></td>
						<th>商品名</th>
						<td>
							<a href="showGoodsInfo.action?goods.id=<s:property value="#ci.goods.id"/>" target="_blank">
								<s:property value="#ci.goods.name" />
							</a>
						</td>
						<th>作者</th>
						<td>
							<a href="showUser.action?user.id=<s:property value="#ci.userInfo.id"/>" target="_blank">
								<s:property value="#ci.userInfo.username" />
							</a>
						</td>
	  				</tr>
	  				<tr>
	  					<th>所属订单ID</th>
	  					<td>
	  						<a href="showOrderInfo.action?order.id=<s:property value="#ci.order.id"/>" target="_blank">
	  							<s:property value="#ci.order.id" />
	  						</a>
	  					</td>
	  					<th>发表时间</th><td colspan="3"><s:date name="#ci.time" format="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<th>内容</th>
						<td colspan="5">
							<s:property value="#ci.content" />
						</td>
					</tr>
				</table>
				<s:form action="deleteComment.action" method="post" onsubmit="return validate();">
					<s:hidden name="comment.id" value="%{#ci.id}"></s:hidden>
					<s:submit value="删除评论" align="center" id="submit"/>
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
