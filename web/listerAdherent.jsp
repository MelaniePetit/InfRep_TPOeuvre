<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfMembers" contentTitle="Members\' List">
	<jsp:attribute name="content_tag">
		<table class="table table-bordered table-hover">
			<thead>
			<tr>
				<th>Id</th>
				<th>Lastname</th>
				<th>Firstname</th>
				<th>City</th>
                <th></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${mesAdherents}" var="item">
					<tr>
						<td>${item.idAdherent}</td>
						<td>${item.nomAdherent}</td>
						<td>${item.prenomAdherent}</td>
						<td>${item.villeAdherent}</td>
						<td><a href="ListeAdherents?action=editAdherent&id=${item.idAdherent}"><button type="button" class="btn btn-primary "><i class="fa fa-pencil"></i></button></a>
							<a href="ListeAdherents?action=suppAdherent&id=${item.idAdherent}"><button type="button" class="btn btn-danger"><i class="fa fa-times"></i></button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</t:layout>