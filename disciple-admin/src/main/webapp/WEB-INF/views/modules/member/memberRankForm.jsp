<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberRank" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
           //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           //设置选择样式
           $("#inputForm").find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/memberRank/"><spring:message code="admin.memberRank.list"/></a></li>
		<li class="active"><a href="${ctx}/member/memberRank/form?id=${memberRank.id}">
		    <shiro:hasPermission name="member:memberRank:edit"><spring:message code="admin.memberRank.${not empty memberRank.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="member:memberRank:edit"><spring:message code="admin.memberRank.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberRank" action="${ctx}/member/memberRank/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>	
		
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.isDefault"/>：</label>
			<div class="controls">
				<form:select path="isDefault" class="input-xlarge required">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${fns:getDictList('true_false')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isDefault" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.isSpecial"/>：</label>
			<div class="controls">
				<form:select path="isSpecial" class="input-xlarge required">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${fns:getDictList('true_false')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isSpecial" cssStyle="color:red"/>
			</div>
		</div>
		
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.amount"/>：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="amount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.preferentia"/>：</label>
			<div class="controls">
				<form:input path="preferentia" htmlEscape="false" 
				          class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="preferentia" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberRank.imageUrl"/>：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="imageUrl" htmlEscape="false"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="nameImage" />
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
            <shiro:hasPermission name="member:memberRank:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>