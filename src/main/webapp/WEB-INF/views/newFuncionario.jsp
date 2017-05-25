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

</head>

<body>

	<h2>Cadastro de novo Funcionario</h2>
 
	<form:form method="POST" modelAttribute="funcionario">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="nome">Nome </label> </td>
				<td><form:input path="nome" id="nome"/></td>
				<td><form:errors path="nome" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="funcao">Funcao </label> </td>
				<td><form:input path="funcao" id="funcao"/></td>
				<td><form:errors path="funcao" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Atualizar"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Cadastrar"/>
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