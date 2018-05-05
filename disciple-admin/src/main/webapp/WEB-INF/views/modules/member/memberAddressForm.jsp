<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberAddress" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript" src="${ctxStatic }/modules/member/selectMember.js"></script>	
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
		<li><a href="${ctx}/member/memberAddress/?member.id=${memberAddress.member.id}&searchType=${searchType}"><spring:message code="admin.memberAddress.list"/></a></li>
		<li class="active"><a href="${ctx}/member/memberAddress/form?id=${memberAddress.id}&member.id=${memberAddress.member.id}&searchType=${searchType}">
		    <shiro:hasPermission name="member:memberAddress:edit"><spring:message code="admin.memberAddress.${not empty memberAddress.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="member:memberAddress:edit"><spring:message code="admin.memberAddress.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberAddress" action="${ctx}/member/memberAddress/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.memberId"/>：</label>
			<div class="controls">
			<c:choose>
					<c:when test="${'particularMember' eq searchType}">
					<form:hidden id="memberId" path="member.id" /> 
			         ${fns:isNotBlank(memberAddress.member.name)? memberAddress.member.name : memberAddress.member.mobile}
			     </c:when>
					<c:otherwise>
						<form:hidden id="memberId" path="member.id" /> 
						<form:input id="memberName" path="member.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnMemberSelect" class="btn btn-primary" type="button"
							value="选择会员" />
						<input id="btnMemberSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的会员" />
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="member.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.area.id|name"/>：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${memberAddress.area.id}" labelName="area.name" labelValue="${memberAddress.area.name}"
					title="<spring:message code='admin.commmon.area'/>" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			    <form:errors path="area.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.contactName"/>：</label>
			<div class="controls">
				<form:input path="contactName" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="contactName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.phone"/>：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="phone" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.mobile"/>：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="mobile" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.detailedAddress"/>：</label>
			<div class="controls">
				<form:input path="detailedAddress" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="detailedAddress" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.isDefault"/>：</label>
			<div class="controls">
			<form:radiobuttons path="isDefault" items="${fns:getDictList('true_false')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="isDefault" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberAddress.sort"/>：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" 
				          class="input-xlarge digits"/>
				<span class="help-inline"><font color="red">*</font></span>
			    <form:errors path="sort" cssStyle="color:red"/>
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
           <%--  <shiro:hasPermission name="member:memberAddress:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>