<%@ page import="com.ejemplo.web.util.*" %>
<%@ page import="java.util.*" %>       

<div id="buscador-form">
	
		<form action="<%=ControllerPaths.PRODUCTO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.BUSCAR%>"/>
			
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParamsUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>
					
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
