<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de novo Funcionario</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>

</head>

<body>

	<h2>Novo voto de Funcionario em Restaurante</h2>
 
	<form:form method="POST" commandName="voto" modelAttribute="voto">
            <c:choose>
		<c:when test="${edit}">
                <form:input type="hidden" path="id" id="id"/>
		</c:when>
            </c:choose>
            
            <table>              
                <tr>                            
                    <td><label for="funcionario">Funcionario </label> </td>
                    <td>
                        <select id="funcionario_id" name="funcionario_id">
                        <c:forEach items="${funcionarios}" var="funcionario">
                            <option value="${funcionario.id}">${funcionario.nome}</option>
                        </c:forEach>
                        </select>
                    </td>
                    <td><form:errors path="funcionario" cssClass="error"/></td>
                </tr>
	
                <tr>                            
                    <td><label for="restaurante">Restaurante </label> </td>
                    <td>
                        <select id="restaurante_id" name="restaurante_id">
                        <c:forEach items="${restaurantes}" var="restaurante">
                            <option value="${restaurante.id}">${restaurante.nome}</option>
                        </c:forEach>
                        </select>
                    </td>
                    <td><form:errors path="restaurante" cssClass="error"/></td>
                </tr>
                    
                <tr>
                    <td><label for="data">Data </label> </td>
                    <td><form:input path="data" id="datepicker" /></td>
                    <td><form:errors path="data" cssClass="error"/></td>
                </tr>
	
                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                    <input type="submit" value="Atualizar"/>
                            </c:when>
                            <c:otherwise>
                                    <input type="submit" value="Registrar"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
	</form:form>
	<br/>
	<br/>
	Voltar para a <a href="<c:url value='/list' />">Home</a>
</body>
</html>