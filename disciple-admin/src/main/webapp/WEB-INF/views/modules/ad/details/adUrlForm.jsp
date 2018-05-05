<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<div class="control-group">
	<label class="control-label"><spring:message
			code="Ad.url" />：</label>
	<div class="controls">
		<form:input path="url" class="input-xlarge" maxlength="255"/>
		<button id="btnSelectLink" class="btn btn-primary" type="button"
			data-for="url">
			<i class="icon-link"></i>
			<spring:message code='选择' />
		</button>
		<span class="help-inline">可以手动输入广告链接地址，也可以点击选择按钮选择 </span>
	</div>
</div>

