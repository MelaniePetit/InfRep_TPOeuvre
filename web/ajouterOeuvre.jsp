<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 03/02/2017
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter Oeuvre</title>
        <link rel="stylesheet"  href="bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="css/StyleAddAdherent.css">
    </head>

    <body>
        <div id="conteneur">
            <div class="element">
                <h1> Ajout d'une oeuvre </h1>
            </div>
            <div class="element">
                <div class="jumbotron">
                    <form  class="form-horizontal" name='identification' method="post" action="Controleur?action=insererOeuvre" onsubmit="return teste()">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Titre</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="txttitre" value=""  id ="titre" placeholder="Titre de l'oeuvre" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Prix</label>
                            <div class="col-sm-5">
                                <input type="text" name="txtprix"  id ="prix" class="form-control" placeholder="Prix de l'oeuvre" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Propiretaire</label>
                            <div class="col-sm-5">
                                <select class="form-control " name="txtnomproprio" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                                    <c:forEach items="${mesProprietaires}" var="item">
                                        <option>${item.nomProprietaire}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- Boutons Ajouter -->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-2">
                                <button type="submit" class="btn btn-info">Ajouter</button>
                            </div>
                            <div>
                                <a class="btn btn-default" href="index.jsp">Retour<a/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
