<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="form_tag" fragment="true" %>
<%@attribute name="javascripts_tag" fragment="true" %>
<%@attribute name="contentTitle"%>
<%@attribute name="title"%>
<%@attribute name="action"%>
<%@attribute name="method"%>
<%@attribute name="typeOfEntity"%>

<t:layout title="${title}" contentTitle="${contentTitle}">

    <jsp:attribute name="stylesheet_tag">
        <link rel="stylesheet"  href="lib/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css"/>
    </jsp:attribute>

    <jsp:attribute name="content_tag">
        <div class="form">
            <div class="alert alert-danger col-lg-4" id="erreur" style="display: none">
                <button type="button" class="close">×</button>
                <p>Please, fill in all the fields</p>
            </div>
            <div class="alert alert-success col-lg-4" id="reussite" style="display: none">
                <button type="button" class="close">×</button>
                <p>The new adherent was successfully add !</p>
            </div>

            <div class="well">
                <form  class="form-horizontal" name='identification' method="${method}" action="${action}" onsubmit="return verifForm(this)">
                    <jsp:invoke fragment="form_tag"/>

                    <!-- Boutons Ajouter -->
                    <div class="form-group flex-center-center">
                        <button type="submit" class="btn btn-info">Add ${typeOfEntity}</button>
                        <a class="btn btn-default" href="index.jsp">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <jsp:invoke fragment="javascripts_tag"/>
    </jsp:attribute>

</t:layout>