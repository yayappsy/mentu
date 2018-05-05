<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function(){
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/sys/user/export");
					$("#searchForm").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
		$("#btnImport").click(function() {
			$.jBox($("#importBox").html(), {
				title : "导入数据",
				buttons : {
					"关闭" : true
				},
				bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
			});
		});
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/sys/user/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/user/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br />
			<br /> <input id="btnImportSubmit" class="btn btn-primary"
				type="submit" value="   导    入   " /> <a
				href="${ctx}/sys/user/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<shiro:hasPermission name="sys:user:edit">
			<li><a href="${ctx}/sys/user/form">用户添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user"
		action="${ctx}/sys/user/list" method="post"
		class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy"
			value="${page.pageable.orderBy}" callback="page();" />
		<ul class="ul-form">
		    <li><label>角色：</label>
		         <form:select path="role">
		         	 <form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
		             <form:options items="${allRoles }" itemLabel="name" itemValue="id"/>
		         </form:select>
		    </li>
			<li><label>登录名：</label>
			<form:input path="username" htmlEscape="false" maxlength="50"
					class="input-medium" /></li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label>
			<form:input path="name" htmlEscape="false" maxlength="50"
					class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" onclick="return page();" /> <%-- <input
				id="btnExport" class="btn btn-primary" type="button" value="导出" /> <input
				id="btnImport" class="btn btn-primary" type="button" value="导入" /> --%></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column login_name">登录名</th>
				<th class="sort-column name">姓名</th>
				<th>电话</th>
				<th>手机</th>
				<th>是否可登录</th>
				<shiro:hasPermission name="sys:user:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="user">
				<tr>
					<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.username}</a></td>
					<td>${user.name}</td>
					<td>${user.phone}</td>
					<td>${user.mobile}</td>
					<td>${fns:getDictLabel(user.ifEnabled, 'true_false', '')}</td>
					<shiro:hasPermission name="sys:user:edit">
						<td><a href="${ctx}/sys/user/form?id=${user.id}" class="btn btn-primary">修改</a> <a
							href="${ctx}/sys/user/delete?id=${user.id}" class="btn btn-danger"
							onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
							<a href="javascript:;" class="btn btn-primary">查看登录授权</a>
							</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>