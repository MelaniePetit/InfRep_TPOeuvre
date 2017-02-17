<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base_layout title="ArtZone | Home">

    <jsp:attribute name="body_tag">
        <div id="fullScreen" class="flex-center-center col-xs-11 col-sm-6 col-md-4">
            <h2 class="menu-label">azerty</h2>

            <div id="brand">ArtZone</div>
            <div id="logo" class="flex-center-center col-xs-12 col-md-8">
                <div id="circleMenu" class="flex-center-center">
                    <div id="menu-toggle" class="flex-center-center-wrap">
                        <div id="hamburger">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div id="cross">
                            <span></span>
                            <span></span>
                        </div>
                    </div>
                </div>

                <div class="menu-list">
                    <div class="menu flex-center-center" menu-label="List Of Members">
                        <a href="Controleur?action=listerAdherent" class="flex-center-center"><i class="fa fa-users flex-center-center fa-2x"></i></a>
                    </div>
                    <div class="menu flex-center-center" menu-label="Add Member">
                        <a href="Controleur?action=ajouterAdherent" class="flex-center-center"><i class="fa fa-user-plus flex-center-center fa-2x"></i></a>
                    </div>
                    <div class="menu flex-center-center" menu-label="List Of Works Of Art">
                        <a href="Controleur?action=listerOeuvre" class="flex-center-center"><i class="fa fa-paint-brush flex-center-center fa-2x"></i></a>
                    </div>
                    <div class="menu flex-center-center" menu-label="Add Work Of Art">
                        <a href="Controleur?action=ajouterOeuvre" class="flex-center-center"><i class="fa fa-desktop flex-center-center fa-2x"></i></a>
                    </div>

                    //Here you can add some new links
                    // the attribute "menu-label" is the sentence whiwh appear on hover

                </div>
            </div>
        </div>
        <div id="overlay"></div>

    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="application/javascript" src="js/circle-menu.js"></script>
    </jsp:attribute>

</t:base_layout>

