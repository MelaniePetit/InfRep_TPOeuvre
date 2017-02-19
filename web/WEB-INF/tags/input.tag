<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@attribute name="label"%>
<%@attribute name="type"%>
<%@attribute name="name"%>
<%@attribute name="value"%>
<%@attribute name="id"%>
<%@attribute name="placeholder"%>
<%@attribute name="onblur"%>
<%@attribute name="required"%>


<div class="form-group flex-center-center">
    <label class="control-label col-sm-2" >${label}</label>
    <div class="col-sm-5">
        <input type="${type}" class="form-control" name="${name}" value="" id ="${id}" placeholder="${placeholder}" onblur="${onblur}" ${required} >
    </div>
</div>


