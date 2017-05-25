<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>   
        <h2>Lista de Funcionarios</h2>	
	<table>
		<tr>
			<td>ID</td><td>Nome</td><td>Funcao</td><td></td>
		</tr>
		<c:forEach items="${funcionarios}" var="funcionario">
			<tr>
			<td>${funcionario.id}</td>
			<td><a href="<c:url value='/funcionario/edit/${funcionario.id}' />">${funcionario.nome}</a></td>
			<td>${funcionario.funcao}</td>
			<td><a href="<c:url value='/funcionario/delete/${funcionario.id}' />">remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/funcionario/new' />">Adicionar Funcionario</a>
        
        <h2>Lista de Restaurantes</h2>	
	<table>
		<tr>
			<td>ID</td><td>Nome</td><td></td>
		</tr>
		<c:forEach items="${restaurantes}" var="restaurante">
			<tr>
			<td>${restaurante.id}</td>
			<td><a href="<c:url value='/restaurante/edit/${restaurante.id}' />">${restaurante.nome}</a></td>
			<td><a href="<c:url value='/restaurante/delete/${restaurante.id}' />">remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/restaurante/new' />">Adicionar restaurante</a>
</body>
</html>