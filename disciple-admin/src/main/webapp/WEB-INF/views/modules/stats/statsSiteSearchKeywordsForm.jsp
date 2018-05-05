<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.statsSiteSearchKeywords" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/stats/statsSiteSearchKeywords/"><spring:message code="admin.statsSiteSearchKeywords"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/stats/statsSiteSearchKeywords/form?id=${statsSiteSearchKeywords.id}">
		    <shiro:hasPermission name="stats:statsSiteSearchKeywords:edit">
		       <spring:message code="admin.statsSiteSearchKeywords"/>
		       <spring:message code="admin.common.${not empty statsSiteSearchKeywords.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="stats:statsSiteSearchKeywords:edit">
		           <spring:message code="admin.statsSiteSearchKeywords"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="statsSiteSearchKeywords" action="${ctx}/stats/statsSiteSearchKeywords/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.pageViewCount"/>：</label>
			<div class="controls">
				<form:input path="pageViewCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="pageViewCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.visitorCount"/>：</label>
			<div class="controls">
				<form:input path="visitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="visitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.newVisitorCount"/>：</label>
			<div class="controls">
				<form:input path="newVisitorCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="newVisitorCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.newVisitorRate"/>：</label>
			<div class="controls">
				<form:input path="newVisitorRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="newVisitorRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.browseCount"/>：</label>
			<div class="controls">
				<form:input path="browseCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="browseCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.ipCount"/>：</label>
			<div class="controls">
				<form:input path="ipCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="ipCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.bounceRate"/>：</label>
			<div class="controls">
				<form:input path="bounceRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="bounceRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.conversionsCount"/>：</label>
			<div class="controls">
				<form:input path="conversionsCount" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="conversionsCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.conversionsRate"/>：</label>
			<div class="controls">
				<form:input path="conversionsRate" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="conversionsRate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.averageBrowsePageCount"/>：</label>
			<div class="controls">
				<form:input path="averageBrowsePageCount" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="averageBrowsePageCount" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.averageAccessTime"/>：</label>
			<div class="controls">
				<form:input path="averageAccessTime" htmlEscape="false" 
				          class="input-xlarge  digits"/>
			    <form:errors path="averageAccessTime" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.referrer"/>：</label>
			<div class="controls">
				<form:input path="referrer" htmlEscape="false" maxlength="1024" 
				          class="input-xlarge "/>
			    <form:errors path="referrer" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.searchSite"/>：</label>
			<div class="controls">
				<form:input path="searchSite" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="searchSite" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.sourceType"/>：</label>
			<div class="controls">
				<form:input path="sourceType" htmlEscape="false" maxlength="30" 
				          class="input-xlarge "/>
			    <form:errors path="sourceType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.searchKeywords"/>：</label>
			<div class="controls">
				<form:input path="searchKeywords" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="searchKeywords" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.deviceType"/>：</label>
			<div class="controls">
				<form:input path="deviceType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge "/>
			    <form:errors path="deviceType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.ifNewVisitor"/>：</label>
			<div class="controls">
				<form:input path="ifNewVisitor" htmlEscape="false" maxlength="1" 
				          class="input-xlarge "/>
			    <form:errors path="ifNewVisitor" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.dimensions"/>：</label>
			<div class="controls">
				<form:input path="dimensions" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="dimensions" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.year"/>：</label>
			<div class="controls">
				<form:input path="year" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="year" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.month"/>：</label>
			<div class="controls">
				<form:input path="month" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="month" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.day"/>：</label>
			<div class="controls">
				<form:input path="day" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="day" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="StatsSiteSearchKeywords.hour"/>：</label>
			<div class="controls">
				<form:input path="hour" htmlEscape="false" maxlength="5" 
				          class="input-xlarge "/>
			    <form:errors path="hour" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="stats:statsSiteSearchKeywords:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>