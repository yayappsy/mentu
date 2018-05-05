<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
	<legend>
		<spring:message code="admin.member.status" />
		<i class="icon-sort-down"></i>
	</legend>
	<div>
		<form:hidden path="userLoginAuthorization.businessSystem"/>
		<form:hidden path="userLoginAuthorization.userInfo.id" value="${member.id }"/>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="UserInfo.registerIp" />：</label>
			<div class="controls">${member.registerIp}</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Member.imUsername" />：</label>
			<div class="controls">${member.imUsername}
			<span class="help-inline">由系统自动创建 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="UserLoginAuthorization.loginIp" />：</label>
			<div class="controls">${member.userLoginAuthorization.loginIp}</div>
		</div>		
		<div class="control-group">
			<label class="control-label"><spring:message
					code="UserLoginAuthorization.ifEnabled" />：</label>
			<div class="controls">
				<form:select path="userLoginAuthorization.ifEnabled">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> <spring:message
						code="admin.help.tips.loginEnabeld" /> </span>
			</div>
		</div>
		<c:if test="${member.userLoginAuthorization.ifLocked}">
			<div class="control-group">
				<label class="control-label"><spring:message
						code="UserLoginAuthorization.ifLocked" />：</label>
				<div class="controls">
					${fns:getDictLabel(member.userLoginAuthorization.ifLocked,'true_false','')}
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="UserLoginAuthorization.lockedDate" />：</label>
				<div class="controls">
					<fmt:formatDate value="${member.userLoginAuthorization.lockedDate}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
			</div>
		</c:if>
	</div>
</fieldset>
