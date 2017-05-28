<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home - DBServer Teste Prático</title>

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
        
        <h2>Lista de Votos</h2>	
	<table>
		<tr>
			<td>ID</td><td>Funcionario</td><td>Restaurante</td><td>Data</td><td></td>
		</tr>
		<c:forEach items="${votos}" var="voto">
			<tr>
			<td>${voto.id}</td>
			<td>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <c:if test="${funcionario.id == voto.funcionario.id}">${voto.funcionario.nome}</c:if>
                            </c:forEach>
                        </td>
                        <td>                       
                            <c:forEach items="${restaurantes}" var="restaurante">
                                <c:if test="${restaurante.id == voto.restaurante.id}">${voto.restaurante.nome}</c:if>
                            </c:forEach>
                        </td>
                        <td>${voto.data}</td>
			<td><a href="<c:url value='/voto/delete/${voto.id}' />">remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/voto/new' />">Adicionar voto</a>
        
        <h2>Lista de Votos de hoje</h2>	
	<table>
		<tr>
			<td>ID</td><td>Funcionario</td><td>Restaurante</td><td>Data</td><td></td>
		</tr>
		<c:forEach items="${votosDia}" var="voto">
			<tr>
			<td>${voto.id}</td>
			<td>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <c:if test="${funcionario.id == voto.funcionario.id}">${voto.funcionario.nome}</c:if>
                            </c:forEach>
                        </td>
                        <td>                       
                            <c:forEach items="${restaurantes}" var="restaurante">
                                <c:if test="${restaurante.id == voto.restaurante.id}">${voto.restaurante.nome}</c:if>
                            </c:forEach>
                        </td>
                        <td>${voto.data}</td>
			<td><a href="<c:url value='/voto/delete/${voto.id}' />">remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>