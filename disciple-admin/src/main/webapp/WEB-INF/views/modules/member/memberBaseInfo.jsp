<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
	<legend>
		<spring:message code="admin.member.base" />
		<i class="icon-sort-up"></i>
	</legend>
	<div class="hide">
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.avatar" />：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="avatar" htmlEscape="false"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="nameImage" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.nickname" />：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="nickname" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.realName" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.username" />：</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="64"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="username" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.mobile" />：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20"
					class="input-xlarge mobilePhone required" />
				<form:errors path="mobile" cssStyle="color:red" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.email" />：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100"
					class="input-xlarge email" />
				<form:errors path="email" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.gender" />：</label>
			<div class="controls">
				<form:select path="gender">
					<form:option value="">
						<spring:message code="admin.common.defaultSelect" />
					</form:option>
					<form:option value="1">男</form:option>
					<form:option value="2">女</form:option>
				</form:select>

				<form:errors path="gender" cssStyle="color:red" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code="Member.qq" />：</label>
			<div class="controls">
				<form:input path="qq" htmlEscape="false" maxlength="20"
					class="input-xlarge mobilePhone" />
				<form:errors path="qq" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Member.birthplaceName" />：</label>
			<div class="controls">
				<weimhc:regionSelect id="birthplace" name="birthplace.id"
					value="${member.birthplace.id}" labelName="birthplaceName"
					labelValue="${member.birthplaceName}" title="出生地区"
					cssClass="" allowClear="true" />

				<form:errors path="birthplace" cssStyle="color:red" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message code="Member.residenceName" />：</label>
			<div class="controls">
				<sys:treeselect id="residence" name="residence.id"
					value="${member.residence.id}" labelName="residenceName"
					labelValue="${member.residenceName}" title="居住地区"
					url="/sys/area/treeData" cssClass="" allowClear="true" />

				<form:errors path="residence" cssStyle="color:red" />
			</div>
		</div>
	</div>
</fieldset>