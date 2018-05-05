<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职位标签管理</title>
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
		<li class="active"><a href="${ctx}/job/jobLabel/">职位标签列表</a></li>
		<shiro:hasPermission name="job:jobLabel:edit"><li><a href="${ctx}/job/jobLabel/form">职位标签添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="jobLabel" action="${ctx}/job/jobLabel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li><label>标签名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>标签名称</th>
				<th>排序（升序）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="job:jobLabel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="jobLabel">
			<tr>
				<td><a href="${ctx}/job/jobLabel/form?id=${jobLabel.id}">
					${jobLabel.name}
				</a></td>
				<td>
					${jobLabel.sort}
				</td>
				<td>
					<fmt:formatDate value="${jobLabel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${jobLabel.remarks}
				</td>
				<shiro:hasPermission name="job:jobLabel:edit"><td>
    				<a href="${ctx}/job/jobLabel/form?id=${jobLabel.id}">修改</a>
					<a href="${ctx}/job/jobLabel/delete?id=${jobLabel.id}" onclick="return confirmx('确认要删除该职位标签吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>