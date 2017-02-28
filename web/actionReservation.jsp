<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 25/02/2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:form title="${edit ? 'EditReservation' : 'AddReservation'}" contentTitle="${edit ? 'Edit Reservation' : 'Add Reservation'}" action="${edit ? 'ListeReservations?action=editReservation&id' : 'ListeReservations?action=editReservation&id'}" typeOfEntity="reservation" method="post" edit="${edit ? 'true' : ''}">
    <jsp:attribute name="form_tag">
       <div class="form-group flex-center-center">
           <label class="control-label col-sm-2" >Work of art available</label>
           <div class="col-sm-5">
               <select class="form-control " name="txttitre" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                    <c:forEach items="${mesOeuvres}" var="item">
                        <option ${edit ? (item.idOeuvrevente == maReservation.oeuvrevente.idOeuvrevente ? 'selected="selected"' : '') : ''}>${item.titreOeuvrevente}</option>
                    </c:forEach>
               </select>
           </div>
       </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Date of reservation</label>
            <div class="col-sm-5">
                <div class="input-group date">
                    <input value="${maReservation.date}" id="date" name="txtdate" type="text" class="form-control" required><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
            </div>
        </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Adhérent</label>
            <div class="col-sm-5">
                <select class="form-control " name="txtadherent" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                    <c:forEach items="${mesAdherents}" var="item">
                        <option ${edit ? (item.idAdherent == maReservation.adherent.idAdherent ? 'selected="selected"' : '') : ''}>${item.nomAdherent}</option>

                        <option></option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="text/javascript" src="js/formOeuvre.js"></script>
    </jsp:attribute>
</t:form>