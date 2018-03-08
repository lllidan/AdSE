<%@ page language="java" pageEncoding="UTF-8"%>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.userList" name="userList" />
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
				<h2>用户信息</h2>
				<a href="mainAdmin.jsp">返回导航页</a>
				<hr>
				<s:if test="#userList.size()==0"><h3>还没有用户使用本系统</h3></s:if>
				<s:else>
					<h3>现有注册客户信息如下：</h3>
					<table width='90%' border='1'>
						<tr>
							<th>用户id</th><th>用户名</th><th>姓名</th><th>性别</th>
							<!-- <th>生日</th><th>E-mail</th><th>电话</th><th>地址</th> -->
							<th>操作</th>
			  			</tr>
		  				<s:iterator value="#userList" id="ul">
		  				<tr>
					  		<td align="center"><s:property value="#ul.id"/></td>
					  		<td align="center"><s:property value="#ul.username"/></td>
					  		<td align="center"><s:property value="#ul.name"/></td>
					  		<td align="center">
					  			<s:if test="#ul.sex==1">男</s:if>
					  			<s:else>女</s:else>
					  		</td>
					  		<td align="center">
		  						<a href="showUser.action?user.id=<s:property value="#ul.id"/>" >
		  							详情
		  						</a>
								<!-- 删除该信息，这里用JavaScript来确定是否删除-->
		  						<a href="deleteUser.action?user.id=<s:property value="#ul.id"/>" 
		  							onClick="return check('删除')">
		  							删除
		  						</a>
		  					</td>
		  				</tr>
		  				</s:iterator>
		  			</table><br><br>
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
