<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                    <jsp:invoke fragment="content_tag"/>
                </div>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <jsp:invoke fragment="javascripts_tag"/>
    </jsp:attribute>

</t:base_layout>