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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/company/company/">企业列表</a></li>
		<%--<shiro:hasPermission name="company:company:edit"><li><a href="${ctx}/company/company/form">企业添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="company" action="${ctx}/company/company/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>企业简称：</label>
				<form:input path="shortName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>审核状态：</label>
				<form:radiobutton path="isPass" value=""  />全部
				<form:radiobutton path="isPass" value="0"  />不通过
				<form:radiobutton path="isPass" value="1"  />通过
				<form:radiobutton path="isPass" value="-1"  />审核中
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
				<th>企业名称</th>
				<th>企业简称</th>
				<th>企业所在区域</th>
				<%--<th>所属行业</th>--%>
				<th>公司规模</th>
				<th>成立日期</th>
				<th>成立资本</th>
				<shiro:hasPermission name="company:company:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="company">
			<tr>
				<td><a href="${ctx}/company/company/form?id=${company.id}">
					${company.name}
				</a></td>
				<td>
					${company.shortName}
				</td>
				<td>
					${company.area.name}
				</td>
<%--				<td>
					${company.industry.id}
				</td>--%>
				<td>
					${company.size}
				</td>
				<td>
					<fmt:formatDate value="${company.foundingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${company.capital}
				</td>
				<shiro:hasPermission name="company:company:edit"><td>
					<c:if test="${company.isPass=='-1'}">
    					<a href="${ctx}/company/company/formPass?id=${company.id}">审核</a>
					</c:if>
					<c:if test="${company.isPass!='-1'}">
    					<a href="${ctx}/company/company/formPass?id=${company.id}">修改</a>
					</c:if>
					<%--<a href="${ctx}/company/company/delete?id=${company.id}" onclick="return confirmx('确认要删除该企业吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>