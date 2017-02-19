<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:form title="AddWorkOfArt" contentTitle="Add Work Of Art" action="AjouterOeuvre?action=insererOeuvre" typeOfEntity="work of art">
    <jsp:attribute name="form_tag">
        <t:input type="text" name="txttitre" id="titre" placeholder="Title" onblur="verifTitre(this)" required="required" label="Title"/>
        <t:input type="number" name="txtprix" id="prix" placeholder="Price" onblur="verifPrix(this)" required="required" label="Price"/>

        <div class="form-group flex-center-center">
            <label class="control-label col-sm-2" >Owner</label>
            <div class="col-sm-5">
                <select class="form-control " name="txtnomproprio" onChange="combo(this, 'theinput')" onMouseOut="comboInit(this, 'theinput')">
                    <c:forEach items="${mesProprietaires}" var="item">
                        <option>${item.nomProprietaire}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="text/javascript" src="js/formOeuvre.js"></script>
    </jsp:attribute>
</t:form>
