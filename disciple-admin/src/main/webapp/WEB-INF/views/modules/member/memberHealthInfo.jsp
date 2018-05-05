<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset class="hide">
	<legend>
		<spring:message code="admin.member.health" />
		<i class="icon-sort-up"></i>
	</legend>
	<div class="hide">
	    <form:hidden path="userHealthInfo.userInfo.id" value="${member.id }"/>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.height"/>：</label>
			<div class="controls">
				<form:input path="userHealthInfo.height" htmlEscape="false" max="300" min="0"
				          class="input-xlarge  number"/>
			    <form:errors path="userHealthInfo.height" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.weight"/>：</label>
			<div class="controls">
				<form:input path="userHealthInfo.weight" htmlEscape="false"  max="300" min="0"
				          class="input-xlarge  number"/>
			    <form:errors path="userHealthInfo.weight" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.waistline"/>：</label>
			<div class="controls">
				<form:input path="userHealthInfo.waistline" htmlEscape="false"  max="300" min="0"
				          class="input-xlarge  number"/>
			    <form:errors path="userHealthInfo.waistline" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.bust"/>：</label>
			<div class="controls">
				<form:input path="userHealthInfo.bust" htmlEscape="false"  max="300" min="0"
				          class="input-xlarge  number"/>
			    <form:errors path="userHealthInfo.bust" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserHealthInfo.hipline"/>：</label>
			<div class="controls">
				<form:input path="userHealthInfo.hipline" htmlEscape="false"  max="300" min="0"
				          class="input-xlarge  number"/>
			    <form:errors path="userHealthInfo.hipline" cssStyle="color:red"/>
			</div>
		</div>
	</div>
</fieldset>