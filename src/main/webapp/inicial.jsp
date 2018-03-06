
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
        <div class=" container row">
            <div class="center">
                <h2 class="white-text">Contatos</h2>
                <ul class="collection container">
                    <MyTags:Contatos email="${user.email}"></MyTags:Contatos>
                    <c:forEach var='contato' items='${Contatos}'>
                        <a href='conversa.jsp?emailCont=${contato.email}'>
                            <li class='collection-item avatar light-blue darken-1 white-text'>
                                <p> <b>Nick:</b>${contato.nick}</br>
                                    <b>Email:</b> ${contato.email}</br>
                                </p>
                            </li>
                        </a>
                        <div class="divider"></div>
                    </c:forEach>
                </ul> 
            </div>
        </div>
        <div class="row">
            <div><p class="white-text center">&copy; 2017 Thiago Yure<p></div>
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
