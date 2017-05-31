<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="http://dbserver.com.br/" target="_blank"><img class="imgMaisInfo" src="<c:url value="/resources/theme1/img/maisInfo.png" />" alt="Mais Informa��es"></a>
<script src="<c:url value="/resources/theme1/js/jquery.avgrund.js" />"></script>
<script>
$(function() {
        $('#show').avgrund({
                height: 220,
                holderClass: 'custom',
                showClose: true,
                showCloseText: 'Fechar',
                setEvent: 'mouseover', 
                onBlurContainer: '',
                template: '<p>Este programa foi desenvolvido por Rafael Motta Coutinho como teste para empresa DBServer. Ele deve selecionar um entre os restaurantes mais votados pelos funcion�rios no dia obedecendo aos crit�rios. </p>' +
                '<p>1 - Cada funcion�rio s� pode votar uma vez por dia; </p>' +
                '<p>2 - Nenhum restaurante pode ser repetido na mesma semana.</p>' 
        });
});

</script> 

