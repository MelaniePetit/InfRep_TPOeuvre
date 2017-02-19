<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfWorksOfArt" contentTitle="Works Of Art\'s List">
    <jsp:attribute name="content_tag">
        <t:table entities="${mesOeuvres}"/>
    </jsp:attribute>
</t:layout>
