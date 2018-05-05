<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.member" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script src="${ctxStatic}/jquery-ui/1.11.4/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		var iconSorts = {
			'down':'icon-sort-down',
			'up':'icon-sort-up'
		}
		$("legend").click(function() {
			var $this = $(this);
			$this.find("i").toggleClass(function() {
				var $subThis = $(this);
				var ifDown = $subThis.hasClass(iconSorts.down);
				var ifUp = $subThis.hasClass(iconSorts.up);
				if(ifDown){
					$subThis.removeClass(iconSorts.down);
					return iconSorts.up;
				}else if(ifUp){
					$subThis.removeClass(iconSorts.up);
					return iconSorts.down;
				}
			});
			$this.next().toggle("fade", 20);
		});
	});
</script>
<style type="text/css">
legend {
	cursor: pointer;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/member/member/"><spring:message
					code="admin.member.list" /></a></li>
		<li class="active"><a
			href="${ctx}/member/member/form?id=${member.id}"> <shiro:hasPermission
					name="member:member:edit">
					<spring:message code="admin.member.view" />
				</shiro:hasPermission> <shiro:lacksPermission name="member:member:edit">
					<spring:message code="admin.member.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="member"
		action="${ctx}/member/member/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${message}" />

		<%-- 状态属性 --%>
		<%@include file="/WEB-INF/views/modules/member/memberStatusInfo.jsp"%>
		
		<%-- 基本属性 --%>
		<%@include file="/WEB-INF/views/modules/member/memberBaseInfo.jsp"%>
		
		<%-- 企业信息--%>
		<%@include file="/WEB-INF/views/modules/member/memberCorpInfo.jsp"%>
		
		<%-- 健康信息--%>
		<%@include file="/WEB-INF/views/modules/member/memberHealthInfo.jsp"%>


		<%-- <div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.remarks" />：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="remarks" cssStyle="color:red" />
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="member:member:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>