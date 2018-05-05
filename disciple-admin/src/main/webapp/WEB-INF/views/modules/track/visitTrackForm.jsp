<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.visitTrack" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/track/visitTrack/"><spring:message code="admin.visitTrack"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/track/visitTrack/form?id=${visitTrack.id}">
		    <shiro:hasPermission name="track:visitTrack:edit">
		       <spring:message code="admin.visitTrack"/>
		       <spring:message code="admin.common.${not empty visitTrack.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="track:visitTrack:edit">
		           <spring:message code="admin.visitTrack"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="visitTrack" action="${ctx}/track/visitTrack/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.siteId"/>：</label>
			<div class="controls">
				<form:input path="siteId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="siteId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.visitorId"/>：</label>
			<div class="controls">
				<form:input path="visitorId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="visitorId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.browseId"/>：</label>
			<div class="controls">
				<form:input path="browseId" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="browseId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.ifNewVisitor"/>：</label>
			<div class="controls">
				<form:input path="ifNewVisitor" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="ifNewVisitor" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.ifNewBrowse"/>：</label>
			<div class="controls">
				<form:input path="ifNewBrowse" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="ifNewBrowse" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.url"/>：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="url" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.title"/>：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="title" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.referrer"/>：</label>
			<div class="controls">
				<form:input path="referrer" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="referrer" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.searchKeywords"/>：</label>
			<div class="controls">
				<form:input path="searchKeywords" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="searchKeywords" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.os"/>：</label>
			<div class="controls">
				<form:input path="os" htmlEscape="false" maxlength="25" 
				          class="input-xlarge "/>
			    <form:errors path="os" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.deviceType"/>：</label>
			<div class="controls">
				<form:input path="deviceType" htmlEscape="false" maxlength="25" 
				          class="input-xlarge "/>
			    <form:errors path="deviceType" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.resolution"/>：</label>
			<div class="controls">
				<form:input path="resolution" htmlEscape="false" maxlength="20" 
				          class="input-xlarge "/>
			    <form:errors path="resolution" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.colorDepth"/>：</label>
			<div class="controls">
				<form:input path="colorDepth" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="colorDepth" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.browserName"/>：</label>
			<div class="controls">
				<form:input path="browserName" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="browserName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.browserVersion"/>：</label>
			<div class="controls">
				<form:input path="browserVersion" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="browserVersion" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.cookieEnabled"/>：</label>
			<div class="controls">
				<form:input path="cookieEnabled" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="cookieEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.javaEnabled"/>：</label>
			<div class="controls">
				<form:input path="javaEnabled" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="javaEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.flashEnabled"/>：</label>
			<div class="controls">
				<form:input path="flashEnabled" htmlEscape="false" maxlength="1" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="flashEnabled" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.flashVersion"/>：</label>
			<div class="controls">
				<form:input path="flashVersion" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="flashVersion" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.language"/>：</label>
			<div class="controls">
				<form:input path="language" htmlEscape="false" maxlength="10" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="language" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.ip"/>：</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="ip" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.country"/>：</label>
			<div class="controls">
				<form:input path="country" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="country" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.region"/>：</label>
			<div class="controls">
				<form:input path="region" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="region" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.province"/>：</label>
			<div class="controls">
				<form:input path="province" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="province" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.city"/>：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="city" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.isp"/>：</label>
			<div class="controls">
				<form:input path="isp" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isp" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.time"/>：</label>
			<div class="controls">
				<form:input path="time" htmlEscape="false" 
				          class="input-xlarge "/>
			    <form:errors path="time" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.timeOnPage"/>：</label>
			<div class="controls">
				<form:input path="timeOnPage" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="timeOnPage" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="VisitTrack.pageViewDepth"/>：</label>
			<div class="controls">
				<form:input path="pageViewDepth" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="pageViewDepth" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="track:visitTrack:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>