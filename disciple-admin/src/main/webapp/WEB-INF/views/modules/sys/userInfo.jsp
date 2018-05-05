<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>	
	 <%@include file="/WEB-INF/views/include/ueditor.jsp" %>
    <script src="${ctxStatic}/jquery-select2City/jquery.select2City.js" type="text/javascript"></script>	
	<script type="text/javascript">
		$(function(){
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});	
			$('#btnval').click(
					function() {
						console.log($.fn["select2City"].getSelectedVals("city1"));
					});
			$('#btntext').click(
					function() {
						$.fn["select2City"].getSelectedVals("city1");
					});			
			$('#city1').select2City({
				pluginHomeUrl:"${ctxStatic}/jquery-select2City/",
				selectorPrefix:"city1"
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal"><%--
		<form:hidden path="email" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		<sys:ckfinder input="email" type="files" uploadPath="/mytask" selectMultiple="false"/> --%>
		<sys:message content="${message}"/>
		<%--<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="avatar" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<weimhc:selectImage input="nameImage"></weimhc:selectImage>
			</div>
		</div>--%>
		  	
		<%-- <div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls" id="city1">
	  		<select id="city1_province" class="prov"></select> 
	    	<select id="city1_city" class="city" disabled="disabled"></select>
	    	<select id="city1_town" class="town" disabled="disabled"></select>
	    	    <input type="button" id="btnval" value="获取值" style="padding:5px 15px;"/>
                <input type="button" id="btntext" value="获取文本" style="padding:5px 15px;" />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称:</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<label class="lbl">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</label>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次登录:</label>
			<div class="controls">
				<label class="lbl">IP: ${user.userLoginAuthorization.oldLoginIp}
					&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.userLoginAuthorization.oldLoginDate}" 
				type="both" dateStyle="full"/></label>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>