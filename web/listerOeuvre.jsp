<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfWorksOfArt" contentTitle="List Of Works Of Art">
    <jsp:attribute name="content_tag">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Titre</th>
                <th>Prix</th>
                <th>Nom proprietaire</th>
                <th>Prenom proprietaire</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${mesOeuvres}" var="item">
                    <tr>
                        <td>${item.titreOeuvrevente}</td>
                        <td>${item.prixOeuvrevente}</td>
                        <td>${item.proprietaire.nomProprietaire}</td>
                        <td>${item.proprietaire.prenomProprietaire}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</t:layout>
