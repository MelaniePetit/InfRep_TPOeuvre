<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="AddMember" contentTitle="Add Member">
    <jsp:attribute name="content_tag">
        <div class="element">
            <div class="alert alert-danger col-lg-4" id="erreur" style="display: none">
                <button type="button" class="close">×</button>
                <p>Please, fill in all the fields</p>
            </div>
            <div class="alert alert-success col-lg-4" id="reussite" style="display: none">
                <button type="button" class="close">×</button>
                <p>The new adherent was successfully add !</p>
            </div>
            <div class="jumbotron">
                <form  class="well form-horizontal" name='identification' method="post" action="AjouterAdherent?action=insererAdherent" onsubmit="return verifForm(this)">
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Last Name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="txtnom" value="" id ="nom" placeholder="Lastname" onblur="verifNom(this)" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >First Name</label>
                        <div class="col-sm-5">
                            <input type="text" name="txtprenom"  id ="prenom" class="form-control" placeholder="Firstname" onblur="verifPrenom(this)" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >City</label>
                        <div class="col-sm-5">
                            <input type="text" name="txtville" id ="ville" class="form-control" placeholder="City" onblur="verifVille(this)" required >
                        </div>
                    </div>
                    <!-- Boutons Ajouter -->
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-info">Add</button>
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
        <script type="text/javascript" src="js/formAdherent.js"></script>
    </jsp:attribute>

</t:layout>
