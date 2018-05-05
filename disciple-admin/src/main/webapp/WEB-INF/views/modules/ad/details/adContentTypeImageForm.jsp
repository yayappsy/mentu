<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:hidden path="contentType"
			class="contentType" value="image"/>

<div class="control-group imageAd">
	<label class="control-label"><spring:message
			code="AdDetail.path" />：</label>
	<div class="controls">

		<form:hidden path="path"/>

		<weimhc:selectImage input="path" />
	</div>
</div>
