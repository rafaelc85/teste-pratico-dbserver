<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Novo voto de Funcionario em Restaurante</title>
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
    $( function() {
      $( "#datepicker" ).datepicker().datepicker("setDate", new Date());
    } );
    </script>
    
    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/nomalize.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/forms.css" />" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

    <link href="<c:url value="/resources/theme1/css/avgrund.css" />" rel="stylesheet">

</head>
             
<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <div class="col-md-10">
                <h1>Novo voto de Funcionario em Restaurante</h1>
            </div>
            <div id="show" class="col-md-2">
                <img src="<c:url value='/resources/theme1/img/help.png' />" class="help" title="Help" alt="Help">
            </div>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <form:form method="POST" commandName="voto" modelAttribute="voto">
            <c:choose>
		<c:when test="${edit}">
                <form:input type="hidden" path="id" id="id"/>
		</c:when>
            </c:choose>
            
            <form:input type="hidden" path="id" id="id"/>                    
            <fieldset>
              <legend><span class="number">1</span>Informações básicas</legend>
              
                <label for="funcionario">Funcionario: </label>
                <select id="funcionario_id" name="funcionario_id">
                    <c:forEach items="${funcionarios}" var="funcionario">
                        <option value="${funcionario.id}">${funcionario.nome}</option>
                    </c:forEach>
                </select>
                
                <label for="restaurante">Restaurante </label>
                <select id="restaurante_id" name="restaurante_id">
                <c:forEach items="${restaurantes}" var="restaurante">
                    <option value="${restaurante.id}">${restaurante.nome}</option>
                </c:forEach>
                </select>

              <label for="data">Data</label>
              <form:input path="data" id="datepicker"/>
              <form:errors path="data" cssClass="error"/> 

                <c:choose>
                    <c:when test="${edit}">
                            <button type="submit"/>Atualizar</button>
                    </c:when>
                    <c:otherwise>
                            <button type="submit"/>Cadastrar</button>
                    </c:otherwise>
                </c:choose>
            </fieldset>

            <p>Voltar para a <a href="<c:url value='/list' />">Home</a></p>                
	</form:form>
    </section>           
       
</div>  
<c:import url="includes/footer.jsp"></c:import> 
</body>
</html>