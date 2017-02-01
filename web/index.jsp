<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 01/02/2017
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="refresh" content="0;URL=javascript:fermer();">
        <link href="WEB-INF/lib/bootstrap/css/bootstrap.css"/>
        <title>Expo : Médiathèque De POLYTECH</title>
    </head>

    <%--<script language="JavaScript">--%>
        <%--function fermer() {--%>
        <%--}--%>
    <%--</script>--%>

    <body>

        <div class="col-xs-12">
            <h1 class="text-center"> Médiathèque de Polytech </h1>
            <h1 class="text-center"><small>Gestion de l'exposition 2016</small></h1>
        </div>

        <div class="col-xs-12">
            <div class="col-md-3"> Home </div>
            <a href="Controleur?action=listerAdherent"><div class="col-md-3"> Liste </div></a>
            <a href="Controleur?action=ajouterAdherent"><div class="col-md-3"> Add </div></a>
            <div class="col-md-3"> Reservation </div>
        </div>

        <%--<li><a href="javascript:fermer()"><font face="Arial">Quitter</font></a><font face="Arial"> </font></li>--%>

    </body>

</html>