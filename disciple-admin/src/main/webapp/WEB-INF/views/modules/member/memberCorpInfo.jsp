<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
	<legend>
		<spring:message code="admin.member.corp" />
		<i class="icon-sort-up"></i>
	</legend>
	<div class="hide">
	    <form:hidden path="userCorpInfo.userInfo.id" value="${member.id }"/>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpName"/>：</label>
			<div class="controls">
				<form:input path="userCorpInfo.corpName" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="userCorpInfo.corpName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpWebsite"/>：</label>
			<div class="controls">
				<form:input path="userCorpInfo.corpWebsite" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="userCorpInfo.corpWebsite" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpPhone"/>：</label>
			<div class="controls">
				<form:input path="userCorpInfo.corpPhone" htmlEscape="false" maxlength="20" 
				          class="input-xlarge phone"/>
			    <form:errors path="userCorpInfo.corpPhone" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpJob"/>：</label>
			<div class="controls">
				<form:input path="userCorpInfo.corpJob" htmlEscape="false" maxlength="100" 
				          class="input-xlarge "/>
			    <form:errors path="userCorpInfo.corpJob" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpIndustryId"/>：</label>
			<div class="controls">
				<form:select path="userCorpInfo.corpIndustry">
					<form:options items="${indsutryList}" itemLabel="name" itemValue="id"
						htmlEscape="false" />
				</form:select>
			    <form:errors path="userCorpInfo.corpIndustry" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpAddress"/>：</label>
			<div class="controls">
				<weimhc:regionSelect id="corpAddress" name="userCorpInfo.corpAddress.id"
							value="${member.userCorpInfo.corpAddress.id}" labelName="userCorpInfo.corpAddressName"
							labelValue="${member.userCorpInfo.corpAddressName}"
							cssClass="" allowClear="true"
							notAllowSelectParent="true" />
			    <form:errors path="userCorpInfo.corpAddress.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserCorpInfo.corpDetailedAddress"/>：</label>
			<div class="controls">
				<form:input path="userCorpInfo.corpDetailedAddress" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="userCorpInfo.corpDetailedAddress" cssStyle="color:red"/>
			</div>
		</div>
	</div>
</fieldset>