<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Zy's Sport Club</title>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体";
			}
			input{
				font-size:60%;
				font-family:"微软雅黑";
				margin:10px 10px 10px 10px;
			}
			.btn{
				width:286px;
				height:35px;
				font-size:18px;
				border:0;
				background:url("images/ab.jpg") no-repeat left top;
				color:#FFF;
				margin:30px 10px 10px 10px;
				/*顺序为：上  右  下  左*/
			}
		</style>
		<script language="javascript">
			function check(){
				s = document.getElementById("username").value;
				if(s == ""){
					alert("用户名不能为空！");
				}else if(document.getElementById("password").value == ""){
					alert("口令不能为空！");
				}else{
					return true;
				}
				document.getElementById("username").value="";
				return false;
			}
		</script>
	</head>
	<body>
		<center>
			<s:form action="login.action" method="post" target="_top">
				<table>
					<tr>
						<s:textfield id="username" name="user.username" label="用户名 " size="32"></s:textfield>
					</tr>
					<tr>
						<s:password id="password" name="user.password" label="密码 " size="32"></s:password>
					</tr>
					<s:submit value="登录" align="center" cssClass="btn" onclick="return check();"
					    onmouseover="this.style.backgroundPosition='left -36px'" onmouseout="this.style.backgroundPosition='left top'"/>
				</table>
			</s:form>
			<a href="register.jsp" target="center">还没账号?点此前往注册</a>
		</center>
	</body>
</html>
