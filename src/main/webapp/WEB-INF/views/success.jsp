<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Mensagens</title>

    <link href="<c:url value="/resources/theme1/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/nomalize.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/css/forms.css" />" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

    <link href="<c:url value="/resources/theme1/css/avgrund.css" />" rel="stylesheet">
    
    <script src="<c:url value="/resources/theme1/js/scripts.js" />"></script>

</head>
             
<body class="body" onload="redirect('<c:url value="/" />')"> 
<div class="container">
    <header>
        <div class="row">
            <div class="col-md-10">
                <h1>Mensagens</h1>
            </div>
            <div id="show" class="col-md-2">
                <img src="<c:url value='/resources/theme1/img/help.png' />" class="help" title="Help" alt="Help">
            </div>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <h1>${success}</h1>
	<br/>
	<br/>
	Aguarde, você está sendo redirecionado para a Home...
    </section>           
       
</div>                
<c:import url="includes/footer.jsp"></c:import> 

</body>
</html>