<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteBrowser" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/stats/statsSiteBrowser/"><spring:message code="admin.statsSiteBrowser"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/stats/statsSiteBrowser/form?id=${statsSiteBrowser.id}">
		    <shiro:hasPermission name="stats:statsSiteBrowser:edit">
		       <spring:message code="admin.statsSiteBrowser"/>
		       <spring:message code="admin.common.${not empty statsSiteBrowser.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="stats:statsSiteBrowser:edit">
		           <spring:message code="admin.statsSiteBrowser"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="statsSiteBrowser" action="${ctx}/stats/statsSiteBrowser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.pageViewCount"/>：</label>
			<div class="controls">
				<form:input path="pageViewCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="pageViewCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.visitorCount"/>：</label>
			<div class="controls">
				<form:input path="visitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="visitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.newVisitorCount"/>：</label>
			<div class="controls">
				<form:input path="newVisitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="newVisitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.newVisitorRate"/>：</label>
			<div class="controls">
				<form:input path="newVisitorRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="newVisitorRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.browseCount"/>：</label>
			<div class="controls">
				<form:input path="browseCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="browseCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.ipCount"/>：</label>
			<div class="controls">
				<form:input path="ipCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="ipCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.bounceRate"/>：</label>
			<div class="controls">
				<form:input path="bounceRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="bounceRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.conversionsCount"/>：</label>
			<div class="controls">
				<form:input path="conversionsCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="conversionsCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.conversionsRate"/>：</label>
			<div class="controls">
				<form:input path="conversionsRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="conversionsRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.averageBrowsePageCount"/>：</label>
			<div class="controls">
				<form:input path="averageBrowsePageCount" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="averageBrowsePageCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.averageAccessTime"/>：</label>
			<div class="controls">
				<form:input path="averageAccessTime" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="averageAccessTime" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.browserName"/>：</label>
			<div class="controls">
				<form:input path="browserName" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="browserName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.browserType"/>：</label>
			<div class="controls">
				<form:input path="browserType" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="browserType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.dimensions"/>：</label>
			<div class="controls">
				<form:input path="dimensions" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="dimensions" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.year"/>：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="year" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.month"/>：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="month" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.day"/>：</label>
			<div class="controls">
				<form:input path="day" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="day" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteBrowser.hour"/>：</label>
			<div class="controls">
				<form:input path="hour" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="hour" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="stats:statsSiteBrowser:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>