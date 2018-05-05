<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>行业分类管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#treeTable").treeTable({
					expandLevel : 1
				}).show();
				$("#btnUpdateSort").on(
						"click",
						function() {
							loading('正在提交，请稍等...');
							$("#listForm").attr("action",
									"${ctx}/industry/industry/updateSort");
							$("#listForm").submit();
						});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/industry/industry/?topLevelId=${industry.topLevelId}">行业分类列表</a></li>
		<shiro:hasPermission name="industry:industry:edit">
			<li><a
				href="${ctx}/industry/industry/form?topLevelId=${industry.topLevelId}">
					行业分类添加
			</a></li>
		</shiro:hasPermission>
	</ul>
	<weimhc:message resultMessage="${resultMessage}" />
	<form id="listForm" method="post">
		<table id="treeTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>行业分类名称</th>
					<th style="text-align: center;">排序</th>
					<th>是否显示</th>
					<th>更新时间</th>
					<shiro:hasPermission name="industry:industry:edit">
						<th><spring:message code="admin.common.handle" /></th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody id="treeTableList">
				<c:set var="searchIndustry" value="${industry }" />
				<c:forEach items="${list}" var="industry">
					<c:choose>
						<c:when test="${industry.id ne searchIndustry.topLevelId }">
							<tr id="${industry.id}" pId="${industry.parent.id}">
								<td><a
									href="${ctx}/industry/industry/form?id=${industry.id}">
										${industry.name} </a></td>
								<td style="text-align: center;"><shiro:hasPermission
										name="industry:industry:edit">
										<input type="hidden" name="ids" value="${industry.id}" />
										<input name="sorts" type="text" value="${industry.sort}"
											style="width: 50px; margin: 0; padding: 0; text-align: center;">
									</shiro:hasPermission> <shiro:lacksPermission name="industry:industry:edit">
							${industry.sort}
						</shiro:lacksPermission></td>
								<td>${fns:getDictLabel(industry.isShow, 'show_hide', '显示')}</td>
								<td><fmt:formatDate value="${industry.updateDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<shiro:hasPermission name="industry:industry:edit">
									<td><a
										href="${ctx}/industry/industry/form?id=${industry.id}&topLevelId=${industry.topLevelId}"><spring:message
												code="admin.common.modify" /></a> <a
										href="${ctx}/industry/industry/delete?id=${industry.id}&topLevelId=${industry.topLevelId}"
										onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message
												code="admin.common.delete" /></a> <a
										href="${ctx}/industry/industry/form?parent.id=${industry.id}&topLevelId=${industry.topLevelId}"><spring:message
												code="admin.dialog.addSub" />
											行业分类</a></td>
								</shiro:hasPermission>
							</tr>
						</c:when>
						<c:otherwise>
							<tr id="${industry.id}">
								<td>${industry.name}</td>
								<td style="text-align: center;">
							        ${industry.sort}
						            </td>
								<td>${fns:getDictLabel(industry.isShow, 'show_hide', '显示')}</td>
								<td><fmt:formatDate value="${industry.updateDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<shiro:hasPermission name="industry:industry:edit">
									<td></td>
								</shiro:hasPermission>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</tbody>
		</table>
		<shiro:hasPermission name="industry:industry:edit">
			<div class="form-actions pagination-left">
				<input id="btnUpdateSort" class="btn btn-primary" type="button"
					value="保存排序" />
			</div>
		</shiro:hasPermission>
	</form>
</body>
</html>