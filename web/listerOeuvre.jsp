<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 03/02/2017
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Affichage de tous les adhérents</title>
</head>
<body>
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
        <th>Numéro</th>
        <th>Titre</th>
        <th>Nom proprietaire</th>
        <th>Prenom proprietaire</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mesOeuvres}" var="item">
        <tr>
            <td>${item.idOeuvrevente}</td>
            <td>${item.titreOeuvrevente}</td>
            <td>${item.proprietaire.nomProprietaire}</td>
            <td>${item.proprietaire.prenomProprietaire}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>