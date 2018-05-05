<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏管理</title>
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
		<li class="active"><a href="${ctx}/collect/collect/">收藏列表</a></li>
		<shiro:hasPermission name="collect:collect:edit"><li><a href="${ctx}/collect/collect/form">收藏添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="collect" action="${ctx}/collect/collect/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
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
				<th>用户</th>
				<th>类别</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="collect:collect:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="collect">
			<tr>
				<td><a href="${ctx}/collect/collect/form?id=${collect.id}">
					${collect.userInfo.id}
				</a></td>
				<td>
					${collect.category}
				</td>
				<td>
					<fmt:formatDate value="${collect.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${collect.remarks}
				</td>
				<shiro:hasPermission name="collect:collect:edit"><td>
    				<a href="${ctx}/collect/collect/form?id=${collect.id}">修改</a>
					<a href="${ctx}/collect/collect/delete?id=${collect.id}" onclick="return confirmx('确认要删除该收藏吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>