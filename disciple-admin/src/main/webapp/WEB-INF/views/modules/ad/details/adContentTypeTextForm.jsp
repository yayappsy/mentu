<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<form:hidden path="contentType"
			class="contentType" value="image"/>
			
<div class="control-group textAd">
	<label class="control-label"><spring:message
			code="AdDetail.content" />：</label>
	<div class="controls">
		 <form:textarea path="content" rows="4" cols="4" class="input-xxlarge " style="display: inline-flex;"/>
		<weimhc:ueditor ueContainer="content"
			uploadPath="${uploadFolder }"></weimhc:ueditor>
	</div>
</div>
