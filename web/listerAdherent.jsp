<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ArtZone | ListOfFollower">
	<jsp:attribute name="body_tag">
		<P>
			<A href="index.jsp"><FONT face="Arial" color="#004080">Retour
				Accueil</FONT></A>
		</P>
		<P align="center">
			<FONT face="Arial" size="5" color="#004080"><U> <STRONG>Listing&nbsp;des
				Adhérents </STRONG></U></FONT>
		</P>

		<table class="table table-hover">
			<thead>
			<tr>
				<th>Numéro</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Ville</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${mesAdherents}" var="item">
					<tr>
						<td>${item.idAdherent}</td>
						<td>${item.nomAdherent}</td>
						<td>${item.prenomAdherent}</td>
						<td>${item.villeAdherent}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:layout>