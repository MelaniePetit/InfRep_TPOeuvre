<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout adhérent</title>
        <link rel="stylesheet"  href="bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="css/StyleAddAdherent.css">
    </head>
    <SCRIPT language="Javascript" type="text/javascript">
    <script type="text/javascript" src="js/foncControle.js"></script>


    <body>
        <div id="conteneur">
            <div class="element">
                <h1> Ajout d'un adhérent </h1>
            </div>
            <div class="element">
                <div class="jumbotron">
                    <form  class="well form-horizontal" name='identification' method="post" action="Controleur?action=insererAdherent" onsubmit="return teste()">
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Nom</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="txtnom" value=""  id ="nom" placeholder="Nom de l'adhérent" required >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Prénom</label>
                            <div class="col-sm-5">
                                <input type="text" name="txtprenom"  id ="prenom" class="form-control" placeholder="Prénom de l'adhérent" required >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >Ville</label>
                            <div class="col-sm-5">
                                <input type="text" name="txtville" id ="ville" class="form-control" placeholder="Ville de l'adhérent" required >
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