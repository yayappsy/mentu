<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteJavaEnabled" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/stats/statsSiteJavaEnabled/"><spring:message code="admin.statsSiteJavaEnabled"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/stats/statsSiteJavaEnabled/form?id=${statsSiteJavaEnabled.id}">
		    <shiro:hasPermission name="stats:statsSiteJavaEnabled:edit">
		       <spring:message code="admin.statsSiteJavaEnabled"/>
		       <spring:message code="admin.common.${not empty statsSiteJavaEnabled.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="stats:statsSiteJavaEnabled:edit">
		           <spring:message code="admin.statsSiteJavaEnabled"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="statsSiteJavaEnabled" action="${ctx}/stats/statsSiteJavaEnabled/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.pageViewCount"/>：</label>
			<div class="controls">
				<form:input path="pageViewCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="pageViewCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.visitorCount"/>：</label>
			<div class="controls">
				<form:input path="visitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="visitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.newVisitorCount"/>：</label>
			<div class="controls">
				<form:input path="newVisitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="newVisitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.newVisitorRate"/>：</label>
			<div class="controls">
				<form:input path="newVisitorRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="newVisitorRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.browseCount"/>：</label>
			<div class="controls">
				<form:input path="browseCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="browseCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.ipCount"/>：</label>
			<div class="controls">
				<form:input path="ipCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="ipCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.bounceRate"/>：</label>
			<div class="controls">
				<form:input path="bounceRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="bounceRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.conversionsCount"/>：</label>
			<div class="controls">
				<form:input path="conversionsCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="conversionsCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.conversionsRate"/>：</label>
			<div class="controls">
				<form:input path="conversionsRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="conversionsRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.averageBrowsePageCount"/>：</label>
			<div class="controls">
				<form:input path="averageBrowsePageCount" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="averageBrowsePageCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.averageAccessTime"/>：</label>
			<div class="controls">
				<form:input path="averageAccessTime" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="averageAccessTime" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.javaEnabled"/>：</label>
			<div class="controls">
				<form:input path="javaEnabled" htmlEscape="false" maxlength="1" 
				          class="input-xlarge "/>
			    <form:errors path="javaEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.dimensions"/>：</label>
			<div class="controls">
				<form:input path="dimensions" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="dimensions" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.year"/>：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="year" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.month"/>：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="month" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.day"/>：</label>
			<div class="controls">
				<form:input path="day" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="day" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteJavaEnabled.hour"/>：</label>
			<div class="controls">
				<form:input path="hour" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="hour" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="stats:statsSiteJavaEnabled:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>