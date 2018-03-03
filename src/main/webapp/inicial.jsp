<%-- 
    Document   : inicial
    Created on : 02/03/2018, 22:47:19
    Author     : ThigoYure
--%>

<%@taglib prefix="MyTags" uri="/WEB-INF/tlds/MyTags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Tatooine</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body class="light-blue accent-3">
        <div class="row">
            <%@include file="menu.jsp" %>
            <div class="col s9 offset-s3 light-blue accent-3 white-text" style="min-height: 100%;height: auto;position: relative; background-repeat: repeat">
                <div class="container">
                    <div class="row center">
                        <a href="pesquisaLocal.jsp" class="waves-effect waves-light btn light-blue">Locais</a> 
                        <a href="pesquisaEvento.jsp" class="waves-effect waves-light btn light-blue">Eventos</a>
                        <a href="pesquisaUsuario.jsp" class="waves-effect waves-light btn light-blue">Usuários</a>
                    </div>
                </div>
                </br>
                <div class="center-align">
                    <h3>Eventos Compartilhados com você!</h3>
                </div><div class="container"><div class="divider"></div></div>
                </br>
                <div class="container">
                    <ul>
                        <MyTags:buscaDivulgados user="${user.email}"/>
                        <c:choose>
                            <c:when test='${empty Divulgados}'>
                                <h5>Não há eventos divulgados por seus amigos...</h5>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var='eventos' items='${Divulgados}'>
                                    <a href='perfilEvento.jsp?idevento=${eventos.id}'>
                                        <li class='collection-item avatar amber darken-3 white-text'>
                                            <p> <b>Nome:</b>${eventos.nome}</br>
                                                <b>Local:</b> ${eventos.local}</br>
                                                <b>Descrição:</b> ${eventos.descricao}</br>
                                                <b>Responsável:</b> ${eventos.responsavel}
                                            </p>
                                        </li>
                                    </a>
                                    <div class="divider"></div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
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
