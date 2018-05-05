<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.navigation" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#treeTable").treeTable({
					expandLevel : 2
				}).show();
				$("#btnUpdateSort").on(
						"click",
						function() {
							loading('正在提交，请稍等...');
							$("#listForm").attr("action",
									"${ctx}/navigation/navigation/updateSort");
							$("#listForm").submit();
						});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/navigation/navigation/?topLevelId=${navigation.topLevelId}"><spring:message
					code="admin.navigation" /> <spring:message
					code="admin.common.list" /></a></li>
		<shiro:hasPermission name="navigation:navigation:edit">
			<li><a
				href="${ctx}/navigation/navigation/form?topLevelId=${navigation.topLevelId}">
					<spring:message code="admin.navigation" /> <spring:message
						code="admin.common.add" />
			</a></li>
		</shiro:hasPermission>
	</ul>
	<weimhc:message resultMessage="${resultMessage}" />
	<form id="listForm" method="post">
		<table id="treeTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><spring:message code="Navigation.name" /></th>
					<th><spring:message code="Navigation.target" /></th>
					<th style="text-align: center;"><spring:message
							code="Navigation.sort" /></th>
					<th><spring:message code="Navigation.isShow" /></th>
					<th><spring:message code="DataEntity.updateDate" /></th>
					<shiro:hasPermission name="navigation:navigation:edit">
						<th><spring:message code="admin.common.handle" /></th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody id="treeTableList">
				<c:set var="searchNavigation" value="${navigation }" />
				<c:forEach items="${list}" var="navigation">
					<c:choose>
						<c:when test="${navigation.id ne searchNavigation.topLevelId }">
							<tr id="${navigation.id}" pId="${navigation.parent.id}">
								<td><a
									href="${ctx}/navigation/navigation/form?id=${navigation.id}">
										${navigation.name} </a></td>
								<td>${fns:getDictLabel(navigation.target, 'target_type', '本页面')}</td>
								<td style="text-align: center;"><shiro:hasPermission
										name="navigation:navigation:edit">
										<input type="hidden" name="ids" value="${navigation.id}" />
										<input name="sorts" type="text" value="${navigation.sort}"
											style="width: 50px; margin: 0; padding: 0; text-align: center;">
									</shiro:hasPermission> <shiro:lacksPermission name="navigation:navigation:edit">
							${navigation.sort}
						</shiro:lacksPermission></td>
								<td>${fns:getDictLabel(navigation.isShow, 'show_hide', '显示')}</td>
								<td><fmt:formatDate value="${navigation.updateDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<shiro:hasPermission name="navigation:navigation:edit">
									<td><a
										href="${ctx}/navigation/navigation/form?id=${navigation.id}&topLevelId=${navigation.topLevelId}"><spring:message
												code="admin.common.modify" /></a> <a
										href="${ctx}/navigation/navigation/delete?id=${navigation.id}&topLevelId=${navigation.topLevelId}"
										onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message
												code="admin.common.delete" /></a> <a
										href="${ctx}/navigation/navigation/form?parent.id=${navigation.id}&topLevelId=${navigation.topLevelId}"><spring:message
												code="admin.dialog.addSub" />
											<spring:message code="admin.navigation" /></a></td>
								</shiro:hasPermission>
							</tr>
						</c:when>
						<c:otherwise>
							<tr id="${navigation.id}">
								<td>${navigation.name}</td>
								<td>${fns:getDictLabel(navigation.target, 'target_type', '本页面')}</td>
								<td style="text-align: center;">
							        ${navigation.sort}
						            </td>
								<td>${fns:getDictLabel(navigation.isShow, 'show_hide', '显示')}</td>
								<td><fmt:formatDate value="${navigation.updateDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<shiro:hasPermission name="navigation:navigation:edit">
									<td></td>
								</shiro:hasPermission>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</tbody>
		</table>
		<shiro:hasPermission name="navigation:navigation:edit">
			<div class="form-actions pagination-left">
				<input id="btnUpdateSort" class="btn btn-primary" type="button"
					value="保存排序" />
			</div>
		</shiro:hasPermission>
	</form>
</body>
</html>