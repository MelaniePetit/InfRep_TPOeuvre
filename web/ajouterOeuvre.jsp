<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:layout title="AddWorkOfArt" contentTitle="Add Work Of Art">
    <jsp:attribute name="content_tag">
        <div class="element">
            <div class="alert alert-danger col-lg-4" id="erreur" style="display: none">
                <button type="button" class="close">×</button>
                <p>Please, fill in all the fields.</p>
            </div>
            <div class="alert alert-success col-lg-4" id="reussite" style="display: none">
                <button type="button" class="close">×</button>
                <p>The new work was successfully add! </p>
            </div>
            <div class="jumbotron">
                <form  class="form-horizontal" name='identification' method="post" action="AjouterOeuvre?action=insererOeuvre" onsubmit="return verifForm(this)">
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Title</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="txttitre" value=""  id ="titre" placeholder="Title" onblur="verifTitre(this)" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Price</label>
                        <div class="col-sm-5">
                            <input type="number" name="txtprix"  id ="prix" class="form-control" placeholder="Price" onblur="verifPrix(this)" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Owner</label>
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
        <script type="text/javascript" src="js/formOeuvre.js"></script>
    </jsp:attribute>
</t:layout>
