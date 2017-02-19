<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:form title="AddMember" contentTitle="Add Member" action="AjouterAdherent?action=insererAdherent" typeOfEntity="member" method="post">
    <jsp:attribute name="form_tag">
        <t:input type="text" name="txtnom" id="nom" placeholder="LastName" onblur="verifNom(this)" required="required" label="Last name"/>
        <t:input type="text" name="txtprenom" id="prenom" placeholder="Firstname" onblur="verifPrenom(this)" required="required" label="First name"/>
        <t:input type="text" name="txtville" id="ville" placeholder="City" onblur="verifVille(this)" required="required" label="City"/>
    </jsp:attribute>

    <jsp:attribute name="javascripts_tag">
        <script type="application/javascript" src="js/formAdherent.js"></script>
    </jsp:attribute>
</t:form>