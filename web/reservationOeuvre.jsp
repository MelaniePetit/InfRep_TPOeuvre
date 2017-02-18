<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 09/02/2017
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Réservation</title>
        <link rel="stylesheet"  href="bootstrap/css/bootstrap.css"/>

    </head>
    <body>
        <div id="conteneur">
            <div class="element">
                <h1> Réserver une oeuvre </h1>
            </div>
            <div class="element">
                <div class="jumbotron">
                    <form  class="well form-horizontal" name='identification' method="post" action="Controleur?action=insererAdherent" onsubmit="return teste()">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Oeuvres disponibles</label>
                            <div class="col-sm-5">
                                <select class="form-control " name="txttitre onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                                    <c:forEach items="${mesOeuvres}" var="item">
                                        <option>${item.titreOeuvrevente}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Prix de l'oeuvre</label>
                            <div class="col-sm-5">
                                <input type="text" name="txtprix"  id ="prix" class="form-control" placeholder="Prix" required >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Date de réservation</label>
                            <div class="col-sm-5">
                                <input type="date" name="txtdate" id ="date" class="form-control" placeholder="Date" required >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Adhérent</label>
                            <div class="col-sm-5">
                                <select class="form-control " name="txtadherent" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                                    <c:forEach items="${mesAdherents}" var="item">
                                        <option>${item.nomAdherent}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Boutons Ajouter -->
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-2">
                                <button type="submit" class="btn btn-info">Valider</button>
                            </div>
                            <div>
                                <a class="btn btn-default" href="index.jsp">Retour</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
