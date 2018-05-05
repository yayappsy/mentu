<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="control-group">
	<label class="control-label"> <spring:message code="Ad.size" /><font
		color="red">*</font>：
	</label>
	<div class="controls">
		<label class="controls-sub-label-horizontal"><spring:message
				code="Ad.height" />：</label> <form:input path="height"
			maxlength="11" class="input-medium required digits" /> <label
			class="controls-sub-label-horizontal"><spring:message
				code="Ad.width" />：</label> <form:input path="width" maxlength="11"
			class="input-mediume required digits" />
	</div>
</div>

