<%-- 
    Document   : menu
    Created on : 05/03/2018, 21:01:37
    Author     : ThigoYure
--%>

<div class="row">
    <a href='inicial.jsp'>
        <div class="col s3 offset-s3">
            </br><img src="images/Logo.png" alt="" class="responsive-img" style="width: 300px;height: 200px">
        </div>
    </a></br></br>
    <div class="col s3 center">
        <div class="row light-blue darken-1" style=" border-radius: 10px">
            <span class="white-text" style="font-size: 24px">
                <b>Olá ${user.nick}!</b> 
            </span>       
        </div></br>
        <div class="row center">
            <a href="FrontController?controller=Sair" class="waves-effect waves-light btn light-blue darken-1">Tchau!</a>
        </div>
    </div>

</div>