<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.memberCoupon" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script src="${ctxStatic}/modules/member/selectMember.js" type="text/javascript"></script>
	<script src="${ctxStatic}/modules/coupon/selectCoupon.js" type="text/javascript"></script>
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
		<li><a href="${ctx}/member/memberCoupon/?searchType=${searchType}&student.id=${memberCoupon.student.id}"><spring:message code="admin.memberCoupon"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/member/memberCoupon/form?id=${memberCoupon.id}&searchType=${searchType}&student.id=${memberCoupon.student.id}">
		    <shiro:hasPermission name="member:memberCoupon:edit">
		       <spring:message code="admin.memberCoupon"/>
		       <spring:message code="admin.common.${not empty memberCoupon.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="member:memberCoupon:edit">
		           <spring:message code="admin.memberCoupon"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberCoupon" action="${ctx}/member/memberCoupon/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberCoupon.couponId"/>：</label>
			<div class="controls">
			<form:hidden id="couponId" path="coupon.id" /> 
						<form:input id="couponName" path="coupon.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnCouponSelect" class="btn btn-primary" type="button"
							value="选择优惠券" />
						<input id="btnCouponSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的优惠券" />
				
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="coupon" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberCoupon.studentId"/>：</label>
			<div class="controls">
				<form:hidden id="memberId" path="student.id" /> 
						<form:input id="memberName" path="student.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnMemberSelect" class="btn btn-primary" type="button"
							value="选择会员" />
						<input id="btnMemberSelectRemove" class="btn btn-primary"
							type="button" value="清除选择的会员" />
			    <form:errors path="student" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberCoupon.code"/>：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="code" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberCoupon.isUsed"/>：</label>
			<div class="controls">
			<form:checkbox path="isUsed"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isUsed" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="MemberCoupon.usedDate"/>：</label>
			<div class="controls">
				<input name="usedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${memberCoupon.usedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="usedDate" cssStyle="color:red"/>
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
            <shiro:hasPermission name="member:memberCoupon:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>