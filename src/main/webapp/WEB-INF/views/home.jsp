<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home - DBServer Teste Prático</title>
    
    <link href="<c:url value="/resources/theme1/css/avgrund.css" />" rel="stylesheet">
    
    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">   

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
    
    <link href="<c:url value="/resources/theme1/css/sweetalert.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/theme1/js/sweetalert.min.js" />"></script>    
    <script src="https://code.jquery.com/qunit/qunit-1.18.0.js"></script>
    
    <script src="<c:url value="/resources/theme1/js/scripts.js" />"></script>
    
</head>

<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <div class="col-md-10">
                <h1>${msgRestauranteDoDia}         
                <c:choose>
                    <c:when test="${sorteio}">
                            : <a href="<c:url value='/sorteio/${restauranteDoDia.id}' />">Escolher restaurante</a>
                    </c:when>
                </c:choose>
                </h1>
            </div>
            <div id="show" class="col-md-2">
                <img src="<c:url value='/resources/theme1/img/help.png' />" class="help" title="Help" alt="Help">
            </div>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
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
                                <td><a href="javascript:"  onclick="removeBox('<c:url value="/funcionario/delete/${funcionario.id}" />')" id="remover">Remover</a></td>
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
                                <td><a href="javascript:"  onclick="removeBox('<c:url value="/restaurante/delete/${restaurante.id}" />')" id="remover">Remover</a></td>
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
                                <td><a href="javascript:" onclick="removeBox('<c:url value="/voto/delete/${voto.id}" />')">Remover</a></td>
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
                                    <td><a href="javascript:" onclick="removeBox('<c:url value="/voto/delete/${voto.id}" />')">Remover</a></td>
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
    </section>
</div>                
<c:import url="includes/footer.jsp"></c:import>             
</body>
</html>