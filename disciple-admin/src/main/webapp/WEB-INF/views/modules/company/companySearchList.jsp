<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnClear").click(function() {
				top.$.jBox.confirm(
						"是否清除选择？",
						"系统提示",
						function(v, h, f) {
							if (v == "ok") {
								//清除text
								$("#btnClear").parents(".ul-form").find("li :text").each(function(){
									$(this).val("");
								});
								//清除select
								$("#btnClear").parents(".ul-form").find("li select").each(function(){
									 $(this).select2().val(null).trigger("change");
								});
							};
						}, {
							buttonsFocus : 1,
						});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});				
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		// 全选
		var $selectAll = $("#selectAll");
		$selectAll.click( function() {
			var $this = $(this);
			var $enabledIds = $listTable.find("input[name='id']:enabled");
			if ($this.prop("checked")) {
				$enabledIds.prop("checked", true);
				if ($enabledIds.filter(":checked").size() > 0) {
					$deleteButton.removeClass("disabled");
					$contentRow.addClass("selected");
				} else {
					$deleteButton.addClass("disabled");
				}
			} else {
				$enabledIds.prop("checked", false);
				$deleteButton.addClass("disabled");
				$contentRow.removeClass("selected");
			}
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/company/company/?searchType=selectLink">企业列表</a></li>
		<%-- <shiro:hasPermission name="company:company:edit"><li><a href="${ctx}/company/company/form">企业添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="company" action="${ctx}/company/company/?searchType=selectLink" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>企业简称：</label>
				<form:input path="shortName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="清除"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<c:choose>
				<c:when test="${isMultiple}">
				<th><input type="checkbox" id="selectAll"/><spring:message code="admin.common.choose" /></th>
				</c:when>
				<c:otherwise>
				<th><spring:message code="admin.common.choose" /></th>
				</c:otherwise>
				</c:choose>
				<th>企业名称</th>
				<th>企业简称</th>
				<th>企业所在区域</th>
				<th>所属行业</th>
				<th>公司规模</th>
				<th>成立日期</th>
				<th>成立资本</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="company">
			<tr>
			<td><c:choose>
							<c:when test="${isMultiple}">
								<input type="checkbox" name="id" value="${company.id}"
									data-type="company" data-id="${company.id}"
									data-name="${company.name}" />
							</c:when>
							<c:otherwise>
								<input type="radio" name="id" value="${company.id}"
									data-type="company" data-id="${company.id}"
									data-name="${company.name}"/>
							</c:otherwise>
						</c:choose></td>
				<td><a href="${ctx}/company/company/form?id=${company.id}">
					${company.name}
				</a></td>
				<td>
					${company.shortName}
				</td>
				<td>
					${company.area.id}
				</td>
				<td>
					${company.industry.id}
				</td>
				<td>
					${company.size}
				</td>
				<td>
					<fmt:formatDate value="${company.foundingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${company.capital}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>