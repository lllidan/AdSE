<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,java.text.*;" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台管理</title>
		<s:set value="#request.goodsInfo" name="gi"></s:set>
		<script type="text/javascript">
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
		</script>
		<style type="text/css">
			tr{
				font-size:130%;
				font-family:"楷体"
			}
			input{
				font-size:90%;
				font-family:"黑体"
			}
		</style>
	</head>
	<body bgcolor="#D9DFAA">
		<center>
			<br><br>
			<s:if test="#session.custom!=null&&#session.custom.limits=='a'.toString()">
				<h2><s:property value="#request.test"/>商品信息</h2>
				<a href="showGoods.action">返回商品列表页</a>
				<hr><br>
				<s:form action="%{#request.show}Goods.action" method="POST" enctype="multipart/form-data" >
					<table width="65%" border="1" cellspacing="5" cellpadding="8">
					
					<s:if test="#request.show=='add'">
					
						<s:textfield id="name" name="goods.name" value="" label="商品名称" size="32" ></s:textfield>
						
						<s:textfield id="attribute" name="goods.attribute" value="" label="商品属性" size="32" ></s:textfield>
							
						<s:textfield id="listPrice" name="goods.listPrice" value="" label="定价" size="32" ></s:textfield>
									
						<s:textfield id="price" name="goods.price" value="" label="售价" size="32" ></s:textfield>
	
						<s:textfield id="type" name="goods.type" value="" label="类型" size="32" ></s:textfield>
						
						<s:textfield id="field" name="goods.field" value="" label="产地" size="32" ></s:textfield>
						
						<s:textfield id="stock" name="goods.stock" value="" label="库存" size="32" ></s:textfield>
						
						<tr>
							<td>图片：</td>
							<td>
								<img id="img" src="" width="180"/><br>
								<input type="file" name="pic_file" id="file" 
				    				accept="image/*" 
				    				onchange="previewImage(this);" />
				    		</td>
						</tr>
					</s:if>
					<s:else>
						<s:hidden name="goods.id" value="%{#gi.id}"></s:hidden>
						<s:hidden name="goods.sale" value="%{#gi.sale}"></s:hidden>
						
						<s:textfield id="name" name="goods.name" value="%{#gi.name}" label="商品名称" size="32" ></s:textfield>
						
						<s:textfield id="attribute" name="goods.attribute" value="%{#gi.attribute}" label="商品属性" size="32" ></s:textfield>
					
						<s:textfield id="listPrice" name="goods.listPrice" value="%{#gi.listPrice}" label="定价" size="32" ></s:textfield>
						
						<s:textfield id="price" name="goods.price" value="%{#gi.price}" label="售价" size="32" ></s:textfield>
						
						<s:textfield id="type" name="goods.type" value="%{#gi.type}" label="类型" size="32" ></s:textfield>
						
						<s:textfield id="field" name="goods.field" value="%{#gi.field}" label="产地" size="32" ></s:textfield>
						
						<s:textfield id="stock" name="goods.stock" value="%{#gi.stock}" label="库存" size="32" ></s:textfield>
						
						<tr>
							<td>图片：</td>
							<td>
								<img id="img" src="getGoodsPic.action?goods.id=<s:property value="%{#gi.id}" />" width="180"/><br>
				    			<input type="file" name="pic_file" id="file" 
				    				accept="image/*" 
				    				onchange="previewImage(this);" /><br>
							</td>
						</tr>
					</s:else>
					
					</table>
					<table cellspacing="5" cellpadding="8">	
						<tr>
							<td><s:submit value="%{#request.test}" align="center" theme="simple"/></td>
					  		<td><s:submit value="重置" action="showGoodsInfo" align="center" theme="simple" /></td>
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
