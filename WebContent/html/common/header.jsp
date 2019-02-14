<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.seeds.web.utils.*, com.seeds.web.model.*,com.isp.seeds.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!--  .js .css -->
</head>
<%
	Errors errors = (Errors) request.getAttribute(AttributeNames.ERRORS);
	if (errors == null) errors = new Errors();
%>
<body>
	<a href="/index.jsp">
		<img src="/EjemplosMVC/imgs/logo.jpg" width="100" height="60" alt="Ribeira Sacra"/>
	</a>
	
	<%@include file="/html/common/user-menu.jsp"%>
	
	<%@include file="/html/common/buscarCriteria.jsp"%> 