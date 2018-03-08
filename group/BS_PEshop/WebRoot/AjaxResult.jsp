<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
    request.setAttribute("decorator", "none"); 
//强制浏览器不缓存本页面内容 
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 
    //阻止浏览器直接从代理服务器取得本页面内容 
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%> 

服务器计数器: <s:property value="count"/> <br> 
当前时间是： <s:property value="serverTime"/> <br> 
服务器返回的提示是： <s:property value="data"/> 
