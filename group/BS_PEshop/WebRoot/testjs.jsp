<%@ page language="java"  pageEncoding="UTF-8"%>
<% 
    request.setAttribute("decorator", "none"); 
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%> 

<script type="text/javascript"> 
    alert('Spring2.0宝典'); 
</script> 
轻量级J2EE企业应用实战 
<script type="text/javascript"> 
    alert('基于J2EE的Ajax宝典!'); 
</script> 