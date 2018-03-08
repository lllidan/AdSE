<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html> 
	<head> 
	    <title>远程表单 </title> 
	    <s:head theme="ajax"/>
	    <script type="text/javascript">
	    	function click() {
	    		alert();
	    		return false;
	    	}
	    </script>
	</head> 
	<body> 

		<div id='show' style="background-color:#bbbbbb;width:360px;height:80px">原始静态文本 </div> 
	
		<b>使用表单请求的返回值来填充另一个"div"</b><br>
		<s:form id='theForm1' 
		        action='AjaxTest' 
		        method='post' 
		        theme="ajax"> 
		    <s:textfield name='data' label="请输入您喜欢的图书"/> 
		    <s:submit value="修改上面的静态文本" targets="show"/> 
		</s:form> 
		
		
		<b>使用表单请求的返回值来填充本Form</b><br>
		
		<div id="theForm2">
		<s:form
		        cssStyle="border: 1px solid black;" 
		        action='AjaxTest' 
		        method='post' 
		        theme="ajax"> 
		    <s:textfield name='data' label="请输入您喜欢的图书"/> 
		    <s:submit value="修改Form本身" targets="theForm2"/> 
		</s:form> 
		</div>
		
		<b>直接运行远程JavaScript(通过指定executeScripts="true")</b><br>
		<s:form id='theForm3' 
		        cssStyle="border: 1px solid black;" 
		        action='Test3' 
		        method='post' 
		        theme="ajax"> 
		    <s:textfield name='data' label="请输入您喜欢的图书"/> 
		    <s:submit value="执行远程JS" executeScripts="true" targets="show"/> 
		</s:form> 
		
	</body> 
</html> 