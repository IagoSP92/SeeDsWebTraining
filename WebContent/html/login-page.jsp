<%@include file="/html/common/header.jsp"%>

<div class="mainWindow">
<h3><fmt:message key="Autenticarse" bundle="${messages}"/></h3>

<form action="/SeeDsWebTrainig/usuario" method="post">
	<input type="hidden" name="action" value="<%=Actions.ENTRAR%>"/>

	<input type="text" name="user"/>
	<br/>		

	<input type="password" name="password"/>		
  	<br/>
	
	<input type="submit" value="<fmt:message key="Enviar" bundle="${messages}"/>">	
</form>

</div>
<%@include file="/html/common/footer.jsp"%>