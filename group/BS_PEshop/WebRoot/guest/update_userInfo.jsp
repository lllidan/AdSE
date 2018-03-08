<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,java.text.*;" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个人信息管理</title>
		<s:set value="#request.guest" name="guest" />
		<s:set value="#request.img" name="img" />
		<script type="text/javascript">
			//图片上传预览    IE是用了滤镜。
			function previewImage(file)
			{
				if (file.files && file.files[0])
				{
					var img = document.getElementById("img");
					var reader = new FileReader();
					reader.onload = function(evt){img.src = evt.target.result;};
					reader.readAsDataURL(file.files[0]);
				}
				else //兼容IE
				{
					file.select();
					var src = document.selection.createRange().text;
					var img = document.getElementById("img");
					img.src = src;
				}
			}
			function time(){
				var year = document.getElementById('year');
				var month = document.getElementById('month');
				var day = document.getElementById('day');
				
				var y = parseInt(year.value)+parseInt("1900");
				var m = parseInt(month.value)+parseInt("1");
			
				var run = isRunYear(y) ? "yes" : "no";
				var days = 30 + month_days(m, run);
				
				if(day.value > days){
					day.value = days;
				}
				while(day.options.length < days){
					day.options.add(new Option(day.options.length + 1, day.options.length + 1));
				}
				day.options.length = days;
			}
			function isRunYear(y){
				if(y % 400 == 0 || (y % 100 != 0 && y % 4 == 0)){
					return true;
				}else{
					return false;
				}
			}
			function month_days(month, run){
				switch (month){
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						return 1;
					case 4:
					case 6:
					case 9:
					case 11:
						return 0;
				}
				return run == "yes" ? -1 : -2;
			}
			function defau(OGNL, img){
		  		if(img == "none"){
		  			document.getElementById("img").src = "/BS_PEshop/images/head_180.jpeg";
		  		}
		  		
		  		var year = document.getElementById('year');
				var month = document.getElementById('month');
				var day = document.getElementById('day');
				
				var y = parseInt(OGNL.substr(0, 4)) - 1900 ;
				var m = parseInt(OGNL.substr(4, 2)) - 1;
				var d = parseInt(OGNL.substr(6, 2)) ;
				
				year.options[y-90].selected = true;
				month.options[m].selected = true;
				day.options[d-1].selected = true;
		  	}
		</script>
		<style type="text/css">
			tr{
				font-size:150%;
				font-family:"楷体"
			}
			input{
				font-size:90%;
				font-family:"微软雅黑";
			}
			select{
				font-size:90%;
				font-family:"微软雅黑";
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA" onload="defau('<s:date name="%{#guest.birthday}" format="yyyyMMdd"/>','<s:property value="#img"/>' );">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='g'.toString()">	
				<h2>修改个人信息</h2>
				<a href="mainGuest_info.jsp">返回导航页</a>
				<hr><br>
				<s:form action="updateUser.action" method="POST" enctype="multipart/form-data" >
					<s:hidden name="user.id" value="%{#guest.id}"/>
					<table border="1" cellspacing="5" cellpadding="5">
						<s:textfield id="username" name="user.username" label="用户名" value="%{#guest.username}" size="32" readonly="true"></s:textfield>
						
						<s:textfield id="name" name="user.name" label="姓名" value="%{#guest.name}" size="32" />
						
						<s:radio list="#{1:'男',0:'女'}" value="%{#guest.sex}" label="性别" name="user.sex" />
						
						<tr>
							<td>出生时间:</td>
							<td>
								<%! int year, month, day;
									SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
									String time = sdf.format(new Date());
									int now_year = Integer.parseInt(time.substring(0, 4));
								%>	
								<select name="birthday.year" id="year" onchange="time()">
									<%for(year = 1990; year < now_year - 5; year ++){ %>
										<option value="<%= year - 1900%>"><%= year%></option>
									<%} %>
								</select>年
								<select name="birthday.month" id="month" onchange="time()">
									<%for(month = 1; month < 13; month ++){ %>
										<option value = "<%= month - 1%>"><%= month%></option>
									<%} %>
								</select>月
								<select name="birthday.date" id="day" onchange="time()">
									<%for(day = 1; day < 32; day ++){ %>
										<option value = "<%= day%>"><%= day%></option>
									<%} %>
								</select>日
							</td>
						</tr>
						
						<s:textfield id="email" name="user.email" label="E-mail" value="%{#guest.email}" size="32" />
						
						<s:textfield id="phone" name="user.phone" label="手机号" value="%{#guest.phone}" size="32" />
						
						<s:textfield id="address" name="user.address" label="居住地址" value="%{#guest.address}" size="32" />
					
						<tr>
				    		<td>照片:</td>
				    		<td>
								<img id="img" width="180" src="getPic.action?user.id=<s:property value="%{#guest.id}" />"><br>
				    			<input type="file" name="pic_file" id="file" 
				    				accept="image/*" 
				    				onchange="previewImage(this);" /><br>
				    		</td>
						</tr>
					
					</table>
					<br>
					<table cellspacing="5" cellpadding="8">	
						<tr>
							<td><s:submit value="修改" align="center" theme="simple"/></td>
					  		<td><s:submit value="重置" action="updateUserInfo" align="center" theme="simple" /></td>
						</tr>
					</table>
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
