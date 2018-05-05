<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="control-group">
	<label class="control-label"><spring:message code="Ad.location" /><font
		color="red">*</font>：</label>
	<div class="controls">
		<label class="controls-sub-label-horizontal"><spring:message
				code="Ad.location.top" />：</label> <form:input path="locationTop"
			maxlength="11" class="input-medium required digits" /> <label
			class="controls-sub-label-horizontal"><spring:message
				code="Ad.location.left" />：</label> <form:input path="locationLeft"
			maxlength="11" class="input-medium required digits" />
	</div>
</div>

