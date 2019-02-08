<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
  <%@ page import="java.util.List, com.isp.seeds.service.ContenidoServiceImpl" %> 
  <%@ page import="com.isp.seeds.dao.impl.*" %> 
  <%@ page import="com.isp.seeds.model.*" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido a SeeDs</title>
</head>
<body>

	<form action="/SeeDsWebTraining/Contenido"  method="post">
	
		<input name="nombre" type="text" placeholder="nombre"/>
		<input name="fechaMin" type="text" placeholder="fecha MInima">
		<input name="fechaMax" type="text" placeholder="fecha Maxima">
		<input name="id" type="text" placeholder="id">
		
		<input type="submit" value="buscar"/>
	
	</form>

	<%
	
		List<Contenido> resultados = (List<Contenido>) request.getAttribute("resultados");
		if(resultados == null || resultados.isEmpty()) {
			%>
				<p> No hay resultados </p>
			<%
		}
		else {
			out.print("<ul>");
			for (Contenido c: resultados){
				out.print("<li>");
				out.print(c);
				out.print("</li>");
			}
			out.print("</ul>");
		}
	%>

</body>
</html>