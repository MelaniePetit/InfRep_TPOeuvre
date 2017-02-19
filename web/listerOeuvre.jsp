<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfWorksOfArt" contentTitle="Works Of Art\'s List">
    <jsp:attribute name="content_tag">
        <t:table headers="${['Title','Price','Last Name of Owner','First Name of Owner']}">
            <jsp:attribute name="table_body_tag">
                <c:forEach items="${mesOeuvres}" var="item">
                    <tr>
                        <td>${item.titreOeuvrevente}</td>
                        <td>${item.prixOeuvrevente}</td>
                        <td>${item.proprietaire.nomProprietaire}</td>
                        <td>${item.proprietaire.prenomProprietaire}</td>
                        <td>
                            <button type="button" class="btn btn-primary "><i class="fa fa-pencil"></i></button>
                            <a type="button" class="btn btn-danger" data-toggle="modal" data-target=".deleteModal_${item.titreOeuvrevente}"><i class="fa fa-times"></i></a>

                            <%--Delete Modal--%>
                            <div class="modal fade deleteModal_${item.titreOeuvrevente}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h4 class="modal-title">Deleting work of art...</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Please note, this will <strong>permanently</strong> remove the work of art!
                                                The effect is irremediable. Are you sure to perform this action? </p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                            <a href="ListeOeuvres?action=suppOeuvre&titre=${item.titreOeuvrevente}">
                                                <button type="button" class="btn btn-danger">Delete</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--End Of Modal--%>
                        </td>
                    </tr>
                </c:forEach>
            </jsp:attribute>
        </t:table>
    </jsp:attribute>
</t:layout>
