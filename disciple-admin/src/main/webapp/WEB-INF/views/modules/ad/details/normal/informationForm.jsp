<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<%@include
	file="/WEB-INF/views/modules/ad/details/adContentTypeForm.jsp"%>

<%@include file="/WEB-INF/views/modules/ad/details/adSizeForm.jsp"%>

<c:if test="${ adPositionCode eq 'pop'}">
	<%@include
		file="/WEB-INF/views/modules/ad/details/adPopSizeForm.jsp"%>
</c:if>

<%@include
	file="/WEB-INF/views/modules/ad/details/adLocationForm.jsp"%>

<%@include file="/WEB-INF/views/modules/ad/details/adUrlForm.jsp"%>


<script type="text/javascript"
	src="${ctxStatic }/modules/ad/js/contentTypeChange.js"></script>
