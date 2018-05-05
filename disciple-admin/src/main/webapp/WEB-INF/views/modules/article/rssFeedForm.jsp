<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.rssFeed" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/article/rssFeed/"><spring:message code="admin.rssFeed"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/article/rssFeed/form?id=${rssFeed.id}">
		    <shiro:hasPermission name="article:rssFeed:edit">
		       <spring:message code="admin.rssFeed"/>
		       <spring:message code="admin.common.${not empty rssFeed.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="article:rssFeed:edit">
		           <spring:message code="admin.rssFeed"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rssFeed" action="${ctx}/article/rssFeed/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="RssFeed.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssFeed.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssFeed.url"/>：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="url" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssFeed.feedType"/>：</label>
			<div class="controls">
				<form:input path="feedType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge "/>
			    <form:errors path="feedType" cssStyle="color:red"/>
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
            <shiro:hasPermission name="article:rssFeed:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>