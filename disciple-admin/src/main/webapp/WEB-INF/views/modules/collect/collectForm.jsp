<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/modules/company/selectCompany.js" type="text/javascript"></script>
	<script src="${ctxStatic}/modules/job/selectJob.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			//$("#name").focus();
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/collect/collect/">收藏列表</a></li>
		<li class="active"><a href="${ctx}/collect/collect/form?id=${collect.id}">收藏<shiro:hasPermission name="collect:collect:edit">${not empty collect.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="collect:collect:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="collect" action="${ctx}/collect/collect/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户：</label>
			<div class="controls">
				<form:input path="userInfo.id" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
			<form:select path="category">
			<form:option value=""><spring:message code="admin.common.choose"/></form:option>
			<c:forEach items="${collectCategorys}" var="collectCategory">
			<form:option value="${collectCategory}"><spring:message code="CollectCategory.${collectCategory}"/></form:option>
			</c:forEach>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div id="companyDiv" class="control-group"  style="display: none">
			<label class="control-label">收藏企业：</label>
			<div class="controls">
				 <form:hidden id="companyId" path="company.id" /> 
						<form:input id="companyName" path="company.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnCompanySelect" class="btn btn-primary" type="button"
							value="选择企业" />
						<input id="btnCompanySelectRemove" class="btn btn-primary"
							type="button" value="清除选择的企业" />
			</div>
		</div>
		<div id="jobDiv" class="control-group" style="display: none">
			<label class="control-label">收藏职位：</label>
			<div class="controls">
			<form:hidden id="jobId" path="job.id" /> 
						<form:input id="jobName" path="job.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnJobSelect" class="btn btn-primary" type="button"
							value="选择职位" />
						<input id="btnJobSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的职位" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="collect:collect:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>