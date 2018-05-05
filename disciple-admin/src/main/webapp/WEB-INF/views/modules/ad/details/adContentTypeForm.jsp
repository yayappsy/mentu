<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<div class="control-group">
	<label class="control-label"><spring:message
			code="Ad.contentType" />：</label>
	<div class="controls">
	    <form:radiobutton path="contentType"
			class="contentType" value="image" checked="checked"/> <label>图片</label>
		<form:radiobutton path="contentType"
			class="contentType" value="text"/> <label>文字</label>
	</div>
</div>

<div class="control-group textAd">
	<label class="control-label"><spring:message
			code="Ad.content" />：</label>
	<div class="controls">
	    <form:textarea path="content" rows="4" cols="4" class="input-xxlarge " style="display: inline-flex;"/>
		<weimhc:ueditor ueContainer="content"
			uploadPath="${uploadFolder }"></weimhc:ueditor>
	</div>
</div>

<div class="control-group imageAd">
	<label class="control-label"><spring:message
			code="Ad.path" />：</label>
	<div class="controls">
		<form:hidden path="path"/>
		<weimhc:selectImage input="path" />
	</div>
</div>
