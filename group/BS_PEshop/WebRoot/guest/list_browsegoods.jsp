<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:set value="#request.goodsInfo" name="goodsInfo" />
		<s:set value="#request.comments" name="comments" />
		<title>商品: <s:property value="#goodsInfo.name"/></title>
		<style type="text/css">
			tr{
				font-size:120%;
				font-family:"楷体";
				margin:10px 10px 10px 10px;
			}
			#query{
				font-size:70%;
				font-family:"宋体";
			}
			#submit{
				width:80%;
				height:30px;
				font-size:18px;
				margin:10px 10px 10px 10px;
				/*顺序为：上  右  下  左*/
			}
		</style>
	</head>
	<body>
		<center>
			<br><br>
			<s:form action="queryGoods.action" method="POST" target="listgoods">
				<s:select id="query" name="query_type" label="查询项目" list="%{#{'name':'商品名','type':'商品类型','field':'商品产地','attribute':'商品属性'}}" />
				<s:textfield id="query" name="query_content" label="查询关键字"></s:textfield>
				<s:submit value="查询" id="submit" align="center"></s:submit>
			</s:form>
		</center>
	</body>
</html>