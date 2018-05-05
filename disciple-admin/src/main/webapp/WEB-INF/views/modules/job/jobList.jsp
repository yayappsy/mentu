<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职位管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/modules/company/selectCompany.js" type="text/javascript"></script>
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
		<li class="active"><a href="${ctx}/job/job/">职位列表</a></li>
		<shiro:hasPermission name="job:job:edit"><li><a href="${ctx}/job/job/form">职位添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="job" action="${ctx}/job/job/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
		<li><label>发布企业：</label>
				 <form:hidden id="companyId" path="company.id" /> 
						<form:input id="companyName" path="company.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnCompanySelect" class="btn btn-primary" type="button"
							value="选择企业" />
						<input id="btnCompanySelectRemove" class="btn btn-primary"
							type="button" value="清除选择的企业" />
			</li>
			<li><label>职位类别：</label>
				<sys:treeselect id="industry" name="industry.id"
					value="${job.industry.id}" labelName=""
					labelValue="${job.industry.name}"
					title="行业分类"
					url="/industry/industry/treeData" extId="${job.industry.id}"
					cssClass="" allowClear="true" />
			</li>
			<li><label>职位名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>首页显示：</label>
				<form:select path="homeShow">
					<form:option value="-1">不显示</form:option>
					<form:option value="1">热门</form:option>
					<form:option value="2">急招</form:option>
					<form:option value="3">推荐</form:option>
				</form:select>
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
				<th>发布企业</th>
				<th>职位类别</th>
				<th>职位名称</th>
				<th>工作城市</th>
				<th>实习人数</th>
				<th>最低日薪</th>
				<th>最高日薪</th>
				<th>学历要求</th>
				<th>截止日期</th>
				<shiro:hasPermission name="job:job:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="job">
			<tr>
				<td><a href="${ctx}/job/job/form?id=${job.id}">
					${job.company.shortName}
				</a></td>
				<td>
					${job.industry.name}
				</td>
				<td>
					${job.name}
				</td>
				<td>
					${job.city.name}
				</td>
				<td>
					${job.practiceNumber}
				</td>
				<td>
					${job.lowest}
				</td>
				<td>
					${job.highest}
				</td>
				<td>
					${job.education}
				</td>
				<td>
					<fmt:formatDate value="${job.expiryDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="job:job:edit"><td>
    				<a href="${ctx}/job/job/form?id=${job.id}">修改</a>
					<a href="${ctx}/job/job/delete?id=${job.id}" onclick="return confirmx('确认要删除该职位吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>