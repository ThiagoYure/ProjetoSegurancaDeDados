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
    <body class="yellow">
        <%@include file="menu.jsp" %>
        <div class='container'>
            <div class='row'>
                <div class='col s6 offset-s3'>
                    <div class='light-blue darken-1' style='border-radius: 10px'><h3 class=" center white-text">${param.emailCont}</h3></div><div class='light-blue darken-1 divider'></div></br>
                    <div class="row">
                        <MyTags:Mensagens usuario="${user.email}" destinatario="${param.emailCont}"/>
                        <c:forEach var='mensagem' items='${Mensagens}'>
                            <c:choose>
                                <c:when test="${mensagem.remetente eq user.email}">
                                    <div class="row">
                                        <div class=' col s6 offset-s6 card light-blue darken-1 white-text'>
                                            <p> <b class='yellow-text'>${mensagem.remetente}</b></br>
                                                ${mensagem.texto}</br>
                                            </p>
                                        </div>
                                    </div></br>
                                </c:when>
                                <c:otherwise>
                                    <div class="row">
                                        <div class=' col s6 card light-blue darken-1 white-text'>
                                            <p> <b class='yellow-text'>${mensagem.remetente}</b></br>
                                                ${mensagem.texto}</br>
                                            </p>
                                        </div></br>
                                    </div>
                                </c:otherwise>
                            </c:choose> 
                        </c:forEach>
                    </div>
                </div>
            </div>                    
            <div class='row'>
                <form class="col s6 offset-s3" action="FrontController" method="POST">
                    <div class="row">
                        <div class="input-field col s12 white" style='border-radius: 10px'>
                            <textarea id="texto" name='texto' class="materialize-textarea"></textarea>
                        </div>
                    </div>
                    <div class='row red-text'>
                        <span>${param.msg}</span>
                    </div>
                    <div class="row center-align">
                        <input class="light-blue waves-effect waves-light btn" type="submit" value="Enviar">
                    </div>
                    <div class="row">
                        <div class="input-field col s12"><input type="hidden" name="controller" value="EnviarMensagem"></div>
                        <div class="input-field col s12"><input type="hidden" name="destinatario" value="${param.emailCont}"></div>

                    </div>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".button-collapse").sideNav();
            });
        </script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>

