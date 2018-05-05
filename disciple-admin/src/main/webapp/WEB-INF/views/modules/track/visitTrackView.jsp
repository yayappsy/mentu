<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.visitTrack" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/echarts.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/track/visitTrack/"><spring:message code="admin.visitTrack"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="track:visitTrack:edit"><li><a href="${ctx}/track/visitTrack/form">
		   <spring:message code="admin.visitTrack"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="visitTrack" action="${ctx}/track/visitTrack/" method="post" class="breadcrumb form-search">	
		<ul class="ul-form">
			<li class="btns">
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='今天'/>"/>
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='昨天'/>"/>
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='最近7天'/>"/>
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='最近30天'/>"/>
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='上周'/>"/>
			    <input id="btnSearch" class="btn btn-primary" type="submit" value="<spring:message code='上月'/>"/>
			   </li>
			   <li id="time">
				<label> <spring:message code="按月选择"/>：</label>
				
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${applicationWithdrawal.beginDate}" pattern="yyyy"/>"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>-
				
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${applicationWithdrawal.endDate}" pattern="MM"/>"
					onclick="WdatePicker({dateFmt:'MM',isShowClear:false});"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>
	<div class="right-container" style="width: 60%; left: 40%;"><div id="chart-panel" class="right-panel" _echarts_instance_="ec_1491557148733" style="-webkit-tap-highlight-color: transparent; -webkit-user-select: none; background: transparent;"><div style="position: relative; overflow: hidden; width: 636px; height: 841px; padding: 0px; margin: 0px; border-width: 0px; cursor: default;"><canvas width="636" height="841" data-zr-dom-id="zr_0" style="position: absolute; left: 0px; top: 0px; width: 636px; height: 841px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;"></canvas></div><div></div></div></div>
			
</body>
</html>