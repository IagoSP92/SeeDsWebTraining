<%@ page import="com.seeds.web.utils.*, com.seeds.web.controller.*" %>
<%@ page import="java.util.*" %>       

<div id="buscador-form">
	
		<form action="<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.BUSCAR%>"/>
			
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>		

			<input name="<%=ParameterNames.FECHA_MIN%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MIN) %>"/>
					
			<input name="<%=ParameterNames.FECHA_MAX%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_MAX) %>"/>
					
			<input name="<%=ParameterNames.ID_CONTENIDO%>" type="text"
					value="<%=ParameterUtils.getParameter(request, ParameterNames.ID_CONTENIDO) %>"/>
					
			<input type="submit" name="buscar" value="Buscar"/>
		</form>
	</p>
		
	<%		
		List<String> resultados = (List<String>) request.getAttribute(AttributeNames.RESULTADOS);	
		if (resultados!=null && !resultados.isEmpty()) {
			%>
			<p>Resultados:</p>
			<ul><%
			for (String resultado: resultados) {
				%>
					<li><%=resultado%></li>
				<%
			}
			%></ul><%
		}
	%>
</div>	
