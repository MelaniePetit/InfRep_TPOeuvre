<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:base_layout title="ArtZone | Home">

    <jsp:attribute name="body_tag">
        <div id="fullScreen" class="flex-center-center col-xs-11 col-sm-6 col-md-4">
            <h2 class="menu-label"></h2>
            <div id="brand">ArtZone</div>
            <div id="logo" class="flex-center-center col-xs-12 col-md-8">
                <jsp:include page="/WEB-INF/includes/circle_menu_layout.jsp"/>
            </div>
        </div>
        <div id="overlay"></div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="application/javascript" src="js/circle-menu.js"></script>
    </jsp:attribute>

</t:base_layout>

