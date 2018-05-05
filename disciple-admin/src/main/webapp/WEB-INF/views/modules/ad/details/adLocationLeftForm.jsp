<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:if test="${orientation eq null }">
	<c:set var="orientation" value="left" />
</c:if>
<div class="control-group">
	<label class="control-label"><spring:message
			code="AdDetail.location" /><font color="red">*</font>：</label>
	<div class="controls">
		<form:hidden path="locationTop" id="${orientation}LocationTop" />
		<label class="controls-sub-label-horizontal"><spring:message
				code="AdDetail.location.${orientation}" />：</label>
		<form:input path="locationLeft" maxlength="11"
			class="input-medium required digits" />
	</div>
</div>

