<%-- 
    Document   : conversa
    Created on : 03/03/2018, 15:53:28
    Author     : Ricarte
--%>

<%@taglib prefix="MyTags" uri="/tlds/MyTags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Principal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body class="light-blue accent-3">
        <ul class="collection">
            <MyTags:Mensagens usuario="${user.email}" destinatario="${param.emailCont}"/>
            <c:forEach var='mensagem' items='${Mensagens}'>
                <li class='collection-item avatar amber darken-3 white-text'>
                    <p> <b></b>${remetente.email}</br>
                        <b></b> ${mensagem.texto}</br>
                    </p>
                </li>
                <div class="divider"></div>
            </c:forEach>
        </ul>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".button-collapse").sideNav();
            });
        </script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
