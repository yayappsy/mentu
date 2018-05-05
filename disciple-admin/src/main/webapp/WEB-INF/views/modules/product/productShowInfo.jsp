<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
			<legend>
			    <i class="icon-sort-up hide"></i> <i class="icon-sort-down"></i>
				<spring:message code="admin.product.showInfo" />
			</legend>
			<div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.body" />：</label>
				<div class="controls">
					<form:textarea path="body" htmlEscape="false" id="j_ueditorupload"
						rows="4" style="display: inline-flex;" />
					<weimhc:ueditor ueContainer="j_ueditorupload" uploadPath="product"></weimhc:ueditor>
					<form:errors path="body" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.keyword" />：</label>
				<div class="controls">
					<form:input path="keyword" htmlEscape="false" maxlength="255"
						class="input-xlarge " />
					<form:errors path="keyword" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.introduction" />：</label>
				<div class="controls">
					<form:input path="introduction" htmlEscape="false"
						class="input-xlarge " />
					<form:errors path="introduction" cssStyle="color:red" />
				</div>
			</div>
			</div>
		</fieldset>