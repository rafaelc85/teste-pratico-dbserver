<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página para mensagens de ações bem sucedidas</title>
</head>
<body>
	Mensagem : ${success}
	<br/>
	<br/>
	Voltar para <a href="<c:url value='/list' />">Home</a>
	
</body>

</html>