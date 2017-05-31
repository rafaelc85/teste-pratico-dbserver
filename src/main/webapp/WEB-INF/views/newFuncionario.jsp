<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cadastro de novo Funcionario</title>

    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/nomalize.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/forms.css" />" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

    <link href="<c:url value="/resources/theme1/css/avgrund.css" />" rel="stylesheet">

</head>
             
<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <div class="col-md-10">
                <h1>Cadastro de novo Funcionario</h1>
            </div>
            <div id="show" class="col-md-2">
                <img src="<c:url value='/resources/theme1/img/help.png' />" class="help" title="Help" alt="Help">
            </div>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <form:form method="POST" modelAttribute="funcionario">
            <form:input type="hidden" path="id" id="id"/>                    
            <fieldset>
              <legend><span class="number">1</span>Informações básicas</legend>
              <label for="nome">Nome: </label>
              <form:input path="nome" id="nome"/>
              <form:errors path="nome" cssClass="error"/>

              <label for="funcao">Função:</label>
              <form:input path="funcao" id="funcao"/>
              <form:errors path="funcao" cssClass="error"/> 

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