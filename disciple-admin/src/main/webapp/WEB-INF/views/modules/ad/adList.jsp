<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.ad" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ad/ad/"><spring:message code="admin.ad.list"/></a></li>
		<shiro:hasPermission name="ad:ad:edit"><li><a href="${ctx}/ad/ad/form">
		   <spring:message code="admin.ad.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ad" action="${ctx}/ad/ad/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Ad.title"/>：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
				<label> <spring:message code="Ad.adPositionId"/>：</label>
				<form:select path="adPosition.id" class="input-xlarge ">
					<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
					<form:options items="${fns:getAdPositonAll()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label> <spring:message code="Ad.endDate"/>：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ad.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li>
				<label> <spring:message code="Ad.beginDate"/>：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ad.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="Ad.title"/></th>
				<th> <spring:message code="Ad.adPositionId"/></th>
				<th> <spring:message code="Ad.endDate"/></th>
				<th> <spring:message code="Ad.beginDate"/></th>			      
				<shiro:hasPermission name="ad:ad:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ad">
			<tr>
				<td><a href="${ctx}/ad/ad/form?id=${ad.id}">
					${ad.title}
				</a></td>
				<td>
					${ad.adPosition.name}
				</td>
				<td>
					<fmt:formatDate value="${ad.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ad.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				<shiro:hasPermission name="ad:ad:edit"><td>
    				<a href="${ctx}/ad/ad/form?id=${ad.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/ad/ad/delete?id=${ad.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>