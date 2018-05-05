<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.sn" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
                        //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sn/sn/"><spring:message code="admin.sn.list"/></a></li>
		<li class="active"><a href="${ctx}/sn/sn/form?id=${sn.id}">
		    <shiro:hasPermission name="sn:sn:edit"><spring:message code="admin.sn.${not empty sn.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="sn:sn:edit"><spring:message code="admin.sn.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sn" action="${ctx}/sn/sn/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Sn.lastValue"/>：</label>
			<div class="controls">
				<form:input path="lastValue" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="lastValue" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Sn.snType"/>：</label>
			<div class="controls">
				<form:select path="snType" class="input-xlarge required">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="snType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="sn:sn:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)""/>
		</div>
	</form:form>
</body>
</html>