<%-- 
    Document   : cadastro
    Created on : 24/02/2018, 20:48:12
    Author     : ThigoYure
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>What's Up</title>
    </head>
    <body class='yellow'>
        <div class='container'>
            <div class='row'>
                <div class="col s4 offset-s4">
                    </br><img src="images/Logo.png" alt="" class="responsive-img" style="width: 300px;height: 200px">
                </div>
            </div>
            <div class="row">
                <form class="col s6 offset-s3" action="FrontController" method="POST">
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" type="email" class="validate">
                            <label for="email">Email</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="nick" type="text" class="validate">
                            <label for="nick">Nick</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="password" type="password" class="validate">
                            <label for="password">Password</label>
                        </div>
                        <div class='row red-text center'>
                            <span>${param.msg}</span>
                        </div>
                    </div>
                    <div class="row center-align">
                        <input class="light-blue waves-effect waves-light btn" type="submit" value="Cadastrar">
                    </div>
                    <div class="row">
                        <div class="input-field col s12"><input type="hidden" name="controller" value="Cadastro"></div>
                    </div>
                    <div class="row center-align">
                        Já possui conta? Acesse o sistema aqui <a href="index.jsp">aqui</a>
                    </div>
                </form>
            </div>

        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>

