<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="nav-menu">

	

	<%
		if (request.getSession().getAttribute(SessionAttributeNames.USUARIO) != null) { /* Cargar opciones de usuario */
		
			%><a href="/SeeDsWebTraining<%=ViewPath.ENTRAR%>">Entrar</a><%	
		
		}
	%>	
</div>