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
                        <label class="control-label col-sm-2" >Last Name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="nom" id ="nom" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >First Name</label>
                        <div class="col-sm-5">
                            <input type="text" name="prenom"  id ="prenom" class="form-control" placeholder="Firstname" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >City</label>
                        <div class="col-sm-5">
                            <input type="text" name="ville" id ="ville" class="form-control" placeholder="City" required >
                        </div>
                    </div>
                    <!-- Boutons Modifier -->
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-info">Edit</button>
                        </div>
                        <div>
                            <a class="btn btn-default" href="index.jsp">Return</a>
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
