<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ArtZone | ListOfArts">
    <jsp:attribute name="body_tag">
        <P>
            <A href="index.jsp"><FONT face="Arial" color="#004080">Retour
                Accueil</FONT></A>
        </P>
        <P align="center">
            <FONT face="Arial" size="5" color="#004080"><U> <STRONG>Listing des
                Oeuvres </STRONG></U></FONT>
        </P>

        <table>
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
