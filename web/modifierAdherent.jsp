<%--
  Created by IntelliJ IDEA.
  User: collin
  Date: 19/02/2017
  Time: 00:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="EditMember" contentTitle="Edit Member">
    <jsp:attribute name="content_tag">
        <div class="element">
            <div class="jumbotron">
                <form  class="well form-horizontal" name='identification' method="post" action="ListeAdherents?action=modifierAdherent" onsubmit="return teste()">
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Nom</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="nom" value=""  id ="nom" value="Un nom au hasard" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Prénom</label>
                        <div class="col-sm-5">
                            <input type="text" name="prenom"  id ="prenom" class="form-control" placeholder="Prénom de l'adhérent" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Ville</label>
                        <div class="col-sm-5">
                            <input type="text" name="ville" id ="ville" class="form-control" placeholder="Ville de l'adhérent" required >
                        </div>
                    </div>
                    <!-- Boutons Modifier -->
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-info">Modifier</button>
                        </div>
                        <div>
                            <a class="btn btn-default" href="index.jsp">Retour</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="text/javascript" src="js/fonctControle.js"></script>
    </jsp:attribute>

</t:layout>
