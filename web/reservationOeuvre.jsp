<%--
  Created by IntelliJ IDEA.
  User: Mel
  Date: 09/02/2017
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:form title="AddMember" contentTitle="Add Member" action="Reservation?action=insererReservation" typeOfEntity="reservation" method="post">
    <jsp:attribute name="form_tag">
         <div class="form-group flex-center-center">
             <label class="control-label col-sm-2" >Work of art available</label>
             <div class="col-sm-5">
                 <select class="form-control " name="txttitre" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                    <c:forEach items="${mesOeuvres}" var="item">
                        <option>${item.titreOeuvrevente}</option>
                    </c:forEach>
                 </select>
             </div>
         </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Date of reservation</label>
            <div class="col-sm-5">
                 <div class="input-group date">
                    <input id="date" name="txtdate" type="text" class="form-control" required><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                </div>
            </div>
        </div>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Adhérent</label>
            <div class="col-sm-5">
                <select class="form-control " name="txtadherent" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                    <c:forEach items="${mesAdherents}" var="item">
                        <option>${item.nomAdherent}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="text/javascript" src="lib/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
        <script>
            $('.input-group.date').datepicker({
                format: "dd/mm/yyyy",
                todayHighlight: true
            });
        </script>
    </jsp:attribute>
</t:form>
