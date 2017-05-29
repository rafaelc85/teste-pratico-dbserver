<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home - DBServer Teste Prático</title>
    
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>    
    
    <link rel="stylesheet" href="readable/readable.css"> 
  
</head>

<body>
<div class="container">
        <h1>${msgRestauranteDoDia}</h1>         
        <c:choose>
            <c:when test="${sorteio}">
                    <h2><a href="<c:url value='/sorteio/${restauranteDoDia.id}' />">Escolher restaurante</a></h2>
            </c:when>
        </c:choose>
                
        
        <div class="row">
            <div class="col-md-6">
                <h1>Funcionarios</h1>	
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                        <tr>
                                <td>ID</td><td>Nome</td><td>Função</td><td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${funcionarios}" var="funcionario">
                                <tr>
                                <td>${funcionario.id}</td>
                                <td><a href="<c:url value='/funcionario/edit/${funcionario.id}' />">${funcionario.nome}</a></td>
                                <td>${funcionario.funcao}</td>
                                <td><a href="<c:url value='/funcionario/delete/${funcionario.id}' />">Remover</a></td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h2><a href="<c:url value='/funcionario/new' />">Adicionar Funcionario</a></h2>
            </div>
            
            <div class="col-md-6">
                <h1>Restaurantes</h1>	
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                        <tr>
                                <td>ID</td><td>Nome</td><td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${restaurantes}" var="restaurante">
                                <tr>
                                <td>${restaurante.id}</td>
                                <td><a href="<c:url value='/restaurante/edit/${restaurante.id}' />">${restaurante.nome}</a></td>
                                <td><a href="<c:url value='/restaurante/delete/${restaurante.id}' />">Remover</a></td>
                                </tr>                     

                        </c:forEach>
                    </tbody>
                </table>
                <h2><a href="<c:url value='/restaurante/new' />">Adicionar Restaurante</a></h2>
            </div>
        </div>
            
        <hr />        
        
        <div class="row">
            <div class="col-md-6">
                <h1>Votos</h1>	
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                        <tr>
                                <td>ID</td><td>Funcionario</td><td>Restaurante</td><td>Data</td><td></td>
                        </tr>
                    </thead>
                    <tbody>
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
                                <td><a href="<c:url value='/voto/delete/${voto.id}' />">Remover</a></td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <h2><a href="<c:url value='/voto/new' />">Adicionar voto</a></h2>
            </div>
                        
            <div class="col-md-6">
                <div class="row">
                    <h1>Votos de hoje</h1>	
                    <table class="table table-striped table-bordered table-hover table-condensed">
                        <thead>
                            <tr>
                                    <td>ID</td><td>Funcionario</td><td>Restaurante</td><td>Data</td><td></td>
                            </tr>
                        </thead>
                        <tbody>
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
                                    <td><a href="<c:url value='/voto/delete/${voto.id}' />">Remover</a></td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>                    
                </div>
                
                <hr />    
                
                <div class="row">
                    <h1>Restaurantes escolhidos por dia</h1>	
                    <table class="table table-striped table-bordered table-hover table-condensed">
                        <thead>
                            <tr>
                                    <td>ID</td><td>Restaurante</td><td>Data</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${restaurantesDia}" var="restauranteDia">
                                    <tr>
                                    <td>${restauranteDia.id}</td>
                                    <td>
                                        ${restauranteDia.restaurante.nome}
                                    </td>
                                    <td>                       
                                        ${restauranteDia.data}
                                    </td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        
        
        </div>
       
        <br/>
        <br/>
        <br/>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="readable/readable.js"></script>
</body>
</html>