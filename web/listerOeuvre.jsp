<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfWorksOfArt" contentTitle="Works Of Art\'s List">
    <jsp:attribute name="content_tag">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Title</th>
                <th>Price</th>
                <th>Last Name of Owner</th>
                <th>First Name of Owner</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${mesOeuvres}" var="item">
                    <tr>
                        <td>${item.titreOeuvrevente}</td>
                        <td>${item.prixOeuvrevente}</td>
                        <td>${item.proprietaire.nomProprietaire}</td>
                        <td>${item.proprietaire.prenomProprietaire}</td>
                        <td><button type="button" class="btn btn-primary "><i class="fa fa-pencil"></i></button>
                            <a href="ListeOeuvres?action=suppOeuvre&titre=${item.titreOeuvrevente}"><button type="button" class="btn btn-danger"><i class="fa fa-times"></i></button></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</t:layout>
