<%@ page import="com.seeds.web.utils.*" %>
<%@ page import="java.util.*" %>       

<div id="buscador-form">
	
		<form action="<%=ControllerPath.USUARIO%>" method="post">	
			<input type="hidden" name="<%=ParameterNames.ACTION%>" value="<%=Actions.REGISTRO%>"/>
			
			<input name="ParameterNames.TIPO"   type="hidden" value="1">
			<input name="ParameterNames.AUTOR"   type="hidden" value="null">
			
			<input type="text"
					name="<%=ParameterNames.NOMBRE%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE) %>"/>
					
			<input type="text"
					name="<%=ParameterNames.EMAIL%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.EMAIL) %>"/>
					
			<input type="text"
					name="<%=ParameterNames.PASSWORD%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.PASSWORD) %>"/>
					
			<input type="text"
					name="<%=ParameterNames.FECHA_NAC%>" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.FECHA_NAC) %>"/>
					
			<input name="<%=ParameterNames.NOMBRE_REAL%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.NOMBRE_REAL) %>"/>
					
			<input name="<%=ParameterNames.APELLIDOS%>" type="text" 
					value="<%=ParameterUtils.getParameter(request, ParameterNames.APELLIDOS) %>"/>
					

			<input type="submit" name="registro" value="Registro"/>
		</form>
		
</div>