<%@ page import="com.isp.seeds.service.*, com.seeds.web.utils.*, com.seeds.web.model.*, com.isp.seeds.model.*, com.seeds.web.controller.*" %>
<div id="user-menu">


	<%
		Usuario u = (Usuario) request.getSession().getAttribute(SessionAttributeNames.USUARIO);
		if (u == null) {
		
			%><a href="/SeeDsWebTraining<%=ViewPath.ENTRAR%>"><button class="userButton">Entrar</button></a><%	
			%><a href="/SeeDsWebTraining<%=ViewPath.REGISTRO%>"><button class="userButton">Registrarse</button></a><%	
		
		} else {
			%>	
			<!-- usuario autenticado -->
			<div id="usuario">
				<p><%=u.getNombre()%></p>
				<a href="/SeeDsWebTraining<%=ViewPath.SALIR%>"><button class="userButton">Mi Perfil</button></a>
				<a href="/SeeDsWebTraining<%=ViewPath.SALIR%>"><button class="userButton">Subir Video</button></a>
				<a href="/SeeDsWebTraining<%=ViewPath.SALIR%>"><button class="userButton">Opciones</button></a>
				<a href="/SeeDsWebTraining<%=ViewPath.SALIR%>"><button class="userButton">Salir</button></a>
			</div>		
			<%
		}
	%>	
</div>