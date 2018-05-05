<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
		});
	</script>
</head>
<body>
	<form:form id="modifyPasswordForm" modelAttribute="userInfo" action="${ctx}/user/userInfo/modifyPwd" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.name"/>：</label>
			<div class="controls">
				${userInfo.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.sn"/>：</label>
			<div class="controls">
				${userInfo.sn}
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.nickname"/>：</label>
			<div class="controls">
				${userInfo.nickname}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="UserInfo.mobile"/>：</label>
			<div class="controls">
				${userInfo.mobile}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重置之后的密码:</label>
			<div class="controls">
				<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class=""/>
				<span class="help-inline"><font color="red">*</font> 如果密码为空，则默认将密码重置为手机号的最后六位</span>
			</div>
		</div>
	</form:form>
</body>
</html>