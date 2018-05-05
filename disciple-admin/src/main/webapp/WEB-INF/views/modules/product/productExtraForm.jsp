<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.productExtra" /><spring:message code="admin.common.manager" /></title>
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
		<li><a href="${ctx}/product/productExtra/"><spring:message code="admin.productExtra"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/product/productExtra/form?id=${productExtra.id}">
		    <shiro:hasPermission name="product:productExtra:edit">
		       <spring:message code="admin.productExtra"/>
		       <spring:message code="admin.common.${not empty productExtra.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="product:productExtra:edit">
		           <spring:message code="admin.productExtra"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productExtra" action="${ctx}/product/productExtra/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductExtra.productId"/>：</label>
			<div class="controls">
				<form:input path="product" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="product" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductExtra.vipMonth"/>：</label>
			<div class="controls">
				<form:input path="vipMonth" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="vipMonth" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductExtra.lessionNumber"/>：</label>
			<div class="controls">
				<form:input path="lessonNumber" htmlEscape="false" maxlength="11" 
				          class="input-xlarge  digits"/>
			    <form:errors path="lessonNumber" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ProductExtra.gift"/>：</label>
			<div class="controls">
				<form:input path="gift" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="gift" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="product:productExtra:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>