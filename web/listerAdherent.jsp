<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfMembers" contentTitle="List Of Members">
	<jsp:attribute name="content_tag">
		<table class="table table-bordered table-hover">
			<thead>
			<tr>
				<th class="hidden-xs">Numéro</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Ville</th>
                <th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${mesAdherents}" var="item">
					<tr>
						<td class="hidden-xs">${item.idAdherent}</td>
						<td>${item.nomAdherent}</td>
						<td>${item.prenomAdherent}</td>
						<td>${item.villeAdherent}</td>
						<td><button type="button" class="btn btn-primary "><i class="fa fa-pencil"></i></button>
							<a href="ListeAdherents?action=suppAdherent&id=${item.idAdherent}"><button type="button" class="btn btn-danger"><i class="fa fa-times"></i></button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:layout>