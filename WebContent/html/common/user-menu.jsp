<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*" %>
<div id="user-menu">
	<%
		Usuario u = (Usuario) request.getSession().getAttribute(SessionAttributeNames.USUARIO);
		if (u == null) {
		
			%><a href="/SeeDsWebTraining<%=ViewPath.ENTRAR%>">Entrar</a><%	
		
		} else {
			%>	
			<!-- usuario autenticado -->
			<div id="usuario">
				<p><%=u.getNombre()%></p>
				<p><a href="/EjemplosMVC/usuario?action=logout">Salir</a></p>
			</div>		
			<%
		}
	%>	
</div>