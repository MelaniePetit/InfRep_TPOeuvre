<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="menuLabelList" value="${['List Of Members','Add Member','List Of Works Of Art','Add Work Of Art','Reservation']}"/>
<c:set var="iconList" value="${['fa-users','fa-user-plus','fa-paint-brush','fa-desktop', 'fa-bookmark']}"/>
<c:set var="controllerList" value="${['ListeAdherents?action=listerAdherent','AjouterAdherent?action=ajouterAdherent','ListeOeuvres?action=listerOeuvre','AjouterOeuvre?action=ajouterOeuvre','Reservation?action=reserverOeuvre']}"/>

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
    <c:forEach var="menuLabelVar" items="${menuLabelList}" varStatus="status">
        <div class="menu flex-center-center" menu-label="${menuLabelVar}">
            <a href="${controllerList[status.index]}" class="flex-center-center"><i class="fa ${iconList[status.index]} flex-center-center fa-2x"></i></a>
        </div>
    </c:forEach>
</div>



