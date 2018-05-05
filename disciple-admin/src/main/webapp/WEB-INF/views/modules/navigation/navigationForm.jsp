<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.navigation" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		var $inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$inputForm.validate({
			submitHandler : function(form) {
				loading();
				form.submit();
			}
		});
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
</script>
<style type="text/css">
.form-horizontal .control-group .controls {
	margin-top: 10px;
}

.form-horizontal .control-group .controls>label {
	margin-right: 10px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/navigation/navigation/?topLevelId=${navigation.topLevelId}"><spring:message
					code="admin.navigation" /> <spring:message
					code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/navigation/navigation/form?id=${navigation.id}&topLevelId=${navigation.topLevelId}"> <shiro:hasPermission
					name="navigation:navigation:edit">
					<spring:message code="admin.navigation" />
					<spring:message
						code="admin.common.${not empty navigation.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="navigation:navigation:edit">
					<spring:message code="admin.navigation" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="navigation"
		action="${ctx}/navigation/navigation/save?topLevelId=${navigation.topLevelId}" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.common.parent" /> <spring:message
					code="admin.navigation" />:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id"
					value="${navigation.parent.id}" labelName=""
					labelValue="${navigation.parent.name}"
					title="<spring:message code='admin.navigation'/>"
					url="/navigation/navigation/treeData" extId="${navigation.id}"
					cssClass="" allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.image" />：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="image" htmlEscape="false"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="nameImage" />
				<form:errors path="image" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.target" />：</label>
			<div class="controls">
				<label><form:radiobutton path="target" class="required"
						value="_self" label="本页面" checked="checked" /></label> <label><form:radiobutton
						path="target" class="required" value="_blank" label="新页面" /></label>
				<form:errors path="target" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.description" />：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.keywords" />：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="keywords" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.linkType" />：</label>
			<div class="controls">
				<label><form:radiobutton path="linkType" class="required"
						checked="checked" value="customContent" label="导航内容" /></label> <span
					class="help-inline">连接类型为“导航内容”时，直接将内容展示为页面</span>
			</div>
			<div class="controls">
				<form:textarea path="customContent" htmlEscape="false"
					id="j_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="j_ueditorupload"
					uploadPath="navigation"></weimhc:ueditor>
				<form:errors path="customContent" cssStyle="color:red" />
				<span class="help-inline">“导航内容”对应的页面</span>
			</div>
			<div class="controls">
				<label><form:radiobutton path="linkType" class="required"
						value="module" label="指定模块" /></label>
				<div class="btn-group">

					<weimhc:treeButton id="productCategory" selectCallBack="callBack"
						title="产品分类" url="/product/productCategory/treeData" extId=""
						cssClass="btn-primary moduleSelect" allowClear="true" />

					<weimhc:treeButton id="articleCategory" selectCallBack="callBack"
						title="新闻资讯" url="/article/articleCategory/treeData"
						extId="" cssClass="btn-primary moduleSelect" allowClear="true" />
				</div>
				<span class="help-inline">选择指定的模块地址</span> <label class=""><spring:message
						code="Navigation.moduleId" />：</label>
				<form:input path="moduleId" htmlEscape="false" maxlength="20"
					class="input-medium " />
				<label class=""><spring:message code="Navigation.moduleName" />：</label>
				<form:input path="moduleName" htmlEscape="false" maxlength="255"
					class="input-medium " />

			</div>
			<div class="controls">
				<label><form:radiobutton path="linkType" class="required"
						value="link" label="链接地址" /></label>
				<form:input path="url" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="url" cssStyle="color:red" />
				<span class="help-inline">网请以http://开头，内网请写相对路径。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.isShow" />：</label>
			<div class="controls">
				<form:checkbox path="isShow"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
					class="required trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="isShow" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Navigation.sort" />：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
				<form:errors path="sort" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.remarks" />：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="remarks" cssStyle="color:red" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="navigation:navigation:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>