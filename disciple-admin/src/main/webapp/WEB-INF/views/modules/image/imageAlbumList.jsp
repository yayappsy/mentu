<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.imageAlbum" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(function(){
			$("#treeTable").treeTable({expandLevel : 3}).show();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/image/imageAlbum/"><spring:message code="admin.imageAlbum"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="image:imageAlbum:edit"><li><a href="${ctx}/image/imageAlbum/form">
		   <spring:message code="admin.imageAlbum"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="imageAlbum" action="${ctx}/image/imageAlbum/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<label> <spring:message code="ImageAlbum.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="ImageAlbum.name"/></th>
				<th> <spring:message code="ImageAlbum.description"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<shiro:hasPermission name="image:imageAlbum:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList">
			<c:forEach items="${list}" var="row">
				<tr id="${row.id}" pId="${pid}">
					<td><a href="${ctx}/image/imageAlbum/form?id=${row.id}">
						${row.name}
					</a></td>
					<td>
						${row.description}
					</td>
					<td>
						${row.updateDate}
					</td>
					<shiro:hasPermission name="image:imageAlbum:edit"><td>
		   				<a href="${ctx}/image/imageAlbum/form?id=${row.id}"><spring:message code="admin.common.modify"/></a>
						<a href="${ctx}/image/imageAlbum/delete?id=${row.id}" onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message code="admin.common.delete"/></a>
						<a href="${ctx}/image/imageAlbum/form?parent.id=${row.id}"><spring:message code="admin.dialog.addSub"/><spring:message code="admin.imageAlbum"/></a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>