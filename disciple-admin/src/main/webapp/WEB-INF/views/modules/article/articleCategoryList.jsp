<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.articleCategory" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(function(){
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "0";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 1});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {}, pid: (root?0:pid), row: row
					}));
					console.log(row.id);
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/article/articleCategory/"><spring:message code="admin.articleCategory.list"/></a></li>
		<shiro:hasPermission name="article:articleCategory:edit"><li><a href="${ctx}/article/articleCategory/form">
		   <spring:message code="admin.articleCategory.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th> <spring:message code="ArticleCategory.name"/></th>
				<th> <spring:message code="DataEntity.updateDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="article:articleCategory:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
	
		<tbody id="treeTableList"></tbody>
		</table>
		<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/article/articleCategory/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.updateDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="article:articleCategory:edit"><td>
   				<a href="${ctx}/article/articleCategory/form?id={{row.id}}"><spring:message code="admin.common.modify"/></a>
				<a href="${ctx}/article/articleCategory/delete?id={{row.id}}" onclick="return confirmx('<spring:message code="admin.dialog.deleteSubConfirm"/>', this.href)"><spring:message code="admin.common.delete"/></a>
				<a href="${ctx}/article/articleCategory/form?parent.id={{row.id}}"><spring:message code="admin.dialog.addSub"/><spring:message code="admin.articleCategoryMenu"/></a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>