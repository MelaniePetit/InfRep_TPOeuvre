<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<t:layout title="ListOfMembers" contentTitle="Members\' List">
	<jsp:attribute name="stylesheet_tag">
        <link rel="stylesheet"  href="lib/DataTables-1.10.13/media/css/dataTables.bootstrap.min.css"/>
    </jsp:attribute>

	<jsp:attribute name="content_tag">
		<t:table entities="${mesAdherents}"/>
	</jsp:attribute>

	<jsp:attribute name="javascripts_tag">
		<script type="application/javascript" src="lib/DataTables-1.10.13/media/js/jquery.dataTables.min.js"></script>
		<script type="application/javascript" src="lib/DataTables-1.10.13/media/js/dataTables.bootstrap.min.js"></script>
        <script>
			$(document).ready(function() {
				$('#DataTable').DataTable(
					{
						columnDefs: [
							{ orderable: false, targets: -1 }
						]
					}
				);
			} );
		</script>
    </jsp:attribute>

</t:layout>