<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="content_tag" fragment="true" %>
<%@attribute name="javascripts_tag" fragment="true" %>
<%@attribute name="contentTitle"%>
<%@attribute name="title"%>

<t:base_layout title="ArtZone | ${title}">
    <jsp:attribute name="body_tag">
        <header class="flex-center-center">
            <a href="index.jsp" class="back-to-home flex-center-center"><i class="fa fa-arrow-left fa-2x"></i></a>
            <h1>${contentTitle}</h1>
        </header>

        <div id="MainContainer">
            <div id="ContentContainer" class="container">
                <div class="col-xs-12">
                    <c:if test="${flashMessage_success != null}">
                        <div class="alert alert-success">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Success!</strong> ${flashMessage_success}
                        </div>
                    </c:if>
                    <c:if test="${flashMessage_error != null}">
                        <div class="alert alert-error">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error!</strong> ${flashMessage_error}
                        </div>
                    </c:if>


                    <jsp:invoke fragment="content_tag"/>
                </div>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <jsp:invoke fragment="javascripts_tag"/>
    </jsp:attribute>

</t:base_layout>