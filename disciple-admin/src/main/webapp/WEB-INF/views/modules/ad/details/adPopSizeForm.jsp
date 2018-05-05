<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="control-group ">
	<label class="control-label"><spring:message code="Ad.popSize" /><font
		color="red">*</font>：</label>
	<div class="controls">
		<label class="controls-sub-label-horizontal"><spring:message
				code="Ad.pop.height" />：</label> <form:input path="popHeight"
			maxlength="11" class="input-medium required digits" /> <label
			class="controls-sub-label-horizontal"><spring:message
				code="Ad.pop.width" />：</label> <form:input path="popWidth"
			maxlength="11" class="input-medium required digits" />
	</div>
</div>
