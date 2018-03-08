<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>注册页</title>
		<s:head theme="simple"/>
		<script language="javascript">
			function check(){
				s=document.getElementById("username").value;
				if(s == ""){
					alert("用户名不能为空！");
				}else if(s.length>=10){
					alert("用户名不能超过9位！");
				}else if(document.getElementById("password1").value == ""){
					alert("口令不能为空！");
				}else if(s.length>=12){
					alert("口令不能超过11位！");
				}else if(document.getElementById("password1").value != document.getElementById("password2").value){
					alert("两次口令不一致！");
				}else{
					return true;
				}
				document.getElementById("username").value="";
				document.getElementById("password1").value="";
				document.getElementById("password2").value="";
				return false;
			}
			function result(){
				s=document.getElementById("username").value;
				if(s != ""){
					alert("抱歉，用户名\"" + s + "\"已被注册！");
					document.getElementById("username").value="";
				}
			}
		</script>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体";
			}
			input{
				font-size:90%;
				font-family:"微软雅黑";
				margin:10px 10px 10px 10px;
			}
			.btn{
				width:286px;
				height:36px;
				font-size:18px;
				border:0;
				background:url("images/ab.jpg") no-repeat left top;
				color:#FFF;
				margin:10px 10px 10px 10px;
				/*顺序为：上  右  下  左*/
			}
		</style>
	</head>
	<body onload="result();">
		<center><br><br>
		
			<s:form action="register.action" method="post" target="_top" onsubmit="return check();">
				<table>
					<s:textfield id="username" name="user.username" label="请输入用户名" size="32"></s:textfield>
					
					<s:password id="password1" name="user.password" label="请输入密码" size="32"></s:password>
					
					<s:password id="password2" name="password" label="请确认密码" size="32"></s:password>
					
					<s:radio name="user.sex" list="%{#{'1':'男','0':'女'}}" label="请选择性别" value="1"></s:radio>
					
					<s:datetimepicker value="today" name="user.birthday" label="请选择出生年月"></s:datetimepicker>
					
				</table>
				<br><br>
				<s:submit value="注册" align="center" cssClass="btn"
				    onmouseover="this.style.backgroundPosition='left -36px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:form>
			<a href="frame_main.html" target="_parent">已有账号？立即前往登录</a>
		</center>
	</body>
</html>
