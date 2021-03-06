<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@attribute name="form_tag" fragment="true" %>
<%@attribute name="javascripts_tag" fragment="true" %>
<%@attribute name="contentTitle"%>
<%@attribute name="title"%>
<%@attribute name="action"%>
<%@attribute name="edit"%>
<%@attribute name="method"%>
<%@attribute name="typeOfEntity"%>
<%@ attribute name="test" %>

<t:layout title="${title}" contentTitle="${contentTitle}">

    <jsp:attribute name="stylesheet_tag">
        <link rel="stylesheet"  href="webjars/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.min.css"/>
    </jsp:attribute>

    <jsp:attribute name="content_tag">
        <div class="form">
            <div class="alert alert-danger" id="erreur" style="display: none">
                <button type="button" class="close">x</button>
                <p>Please, fill in all the fields correctly</p>
            </div>

            <div class="well">
                <form  class="form-horizontal" name='identification' method="${method}" action="${action}" onsubmit="return ${test}">
                    <jsp:invoke fragment="form_tag"/>

                    <!-- Boutons Ajouter -->
                    <div class="form-group flex-center-center">
                        <button type="submit" class="btn btn-info">${edit=='true' ? "Edit " : "Add "}${typeOfEntity}</button>
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
