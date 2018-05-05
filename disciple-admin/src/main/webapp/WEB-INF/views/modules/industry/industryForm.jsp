<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行业分类管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
    <%@ include file="/WEB-INF/views/include/formHead.jsp"%>
    <script src="${ctxStatic}/common/form.js" type="text/javascript"></script>
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
			function callBack(clicked,object,formInputs,selectedNodes){
				if(selectedNodes.length > 0){
					$("#moduleId").val(selectedNodes[0].id);
					$("#moduleName").val(selectedNodes[0].name);
				}
				
			}
			function parentUrlAdditionalParameters(){
				return "topLevelId=${navigation.topLevelId}";
			}
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/industry/industry/?topLevelId=${industry.topLevelId}">行业分类列表</a></li>
		<li class="active"><a href="${ctx}/industry/industry/form?id=${industry.id}&topLevelId=${industry.topLevelId}">行业分类<shiro:hasPermission name="industry:industry:edit">${not empty industry.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="industry:industry:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="industry" action="${ctx}/industry/industry/save?topLevelId=${industry.topLevelId}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">父集编号：</label>
			<div class="controls">
			<sys:treeselect id="parent" name="parent.id"
					value="${industry.parent.id}" labelName=""
					labelValue="${industry.parent.name}"
					title="行业分类"
					url="/industry/industry/treeData" extId="${industry.id}"
					cssClass="" allowClear="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">行业分类名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
				<form:hidden path="inMenu" value = "1"/>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
				<form:errors path="sort" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="industry:industry:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>