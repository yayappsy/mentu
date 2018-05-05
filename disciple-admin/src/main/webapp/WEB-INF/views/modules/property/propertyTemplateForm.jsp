<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.propertyTemplate" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script src="${ctxStatic}/jquery-ui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#inputForm");
	    $inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		
	    $inputForm.on("click",".editProperty", btnAdd);

		$("#btnAdd").on("click", btnAdd);

		function btnAdd() {
			$this = $(this);
			layer.open({
						id : "propety",
						type : 2,
						title : "添加属性",
						area : [ '90%', '90%' ],
						shade : 0.8,
						closeBtn : 1,
						shadeClose : false,
						content : '${ctx}/property/property/form?propertyTemplateId=${propertyTemplate.id}'+"&id="+$this.data("id")
					});
		}
		
	    var tpl = $("#propertyTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		function refreshTable() {
			$.ajax({
						url : "${ctx}/property/property/data",
						type : "POST",
						data : 'propertyTemplateId=${propertyTemplate.id}',
						cache : false,
						success : function(data) {
							$tbody = $("#propertyTable").find("tbody");
							$tbody.children().remove();
							for(var i=0;i<data.length;i++){
							   $tbody.append(Mustache.render(tpl, {
								   idx: i, delBtn: true, row: data[i]
							}));
							}
						},
						error : function(resultMessage) {
						}
			});
		}
		refreshTable();
		$("#btnRefresh").click(function(){
	        	refreshTable();
	    });
	         
		$(".openSort").sortable({ 
       	   cursor: "move", 
       	   disabled: true,
      	   items :"tr", //只是td可以拖动 
      	   opacity: 0.6, //拖动时，透明度为0.6 
      	   revert: true, //释放时，增加动画 
      	   update : function(event, ui){ //更新排序之后 
      	      $.each($(this).sortable("toArray"),function(index,item){
                     $("#"+item+"_sort").val(index);
            	  });
      	   }
         });
      
        $("#btnOpenSort").click(function(){
        	$(".openSort").sortable('enable');
            $(this).hide();
            $("#btnSaveSort").show();
         });
        $("#btnSaveSort").click(function(){
            $(".openSort").sortable('disable');
            $("#btnOpenSort").show();
            $(this).hide();
            saveSort();
         });
        function saveSort(){
        	var params = $("#propertyTable").find("input[name^='propertyList']").serialize();
        	$.ajax({
				url : "${ctx}/property/propertyTemplate/updatePropertySort",
				type : "POST",
				data : params,
				cache : false,
				success : function(data) {
					
				},
				error : function(resultMessage) {
				}
	       });
        }
	});
</script>
<script type="text/template" id="propertyTpl">
<tr id="propertyList{{idx}}">
    <td class="hide">
        <input id="propertyList{{idx}}_id" name="propertyList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
        <input id="propertyList{{idx}}_sort" name="propertyList[{{idx}}].sort" type="hidden" value="{{row.sort}}"/>
    </td>
	<td>{{row.name}}</td>
	<td>{{row.showType}}</td>
	<td>{{row.propertyValues}}</td>
	<td>
		<a class="editProperty" href="javascript:;" data-id="{{row.id}}"><spring:message code="admin.common.modify"/></a>
		<a href="${ctx}/property/property/delete?id={{row.id}}" 
					onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
		    <spring:message code="admin.common.delete"/>
		</a>
	</td>
</tr>
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/property/propertyTemplate/"><spring:message
					code="admin.propertyTemplate" /> <spring:message
					code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/property/propertyTemplate/form?id=${propertyTemplate.id}">
				<shiro:hasPermission name="property:propertyTemplate:edit">
					<spring:message code="admin.propertyTemplate" />
					<spring:message
						code="admin.common.${not empty propertyTemplate.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="property:propertyTemplate:edit">
					<spring:message code="admin.propertyTemplate" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="propertyTemplate"
		action="${ctx}/property/propertyTemplate/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="PropertyTemplate.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="PropertyTemplate.description" />：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.remarks" />：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
				<form:errors path="remarks" cssStyle="color:red" />
			</div>
		</div>
		<table id="propertyTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th class="hide"></th>
					<th>名称</th>
					<th>类型</th>
					<th>可选值</th>
					<th>
						<button type="button" id="btnAdd" class="btn btn-primary">添加</button>
						<button type="button" id="btnRefresh" class="btn btn-primary"><i class="icon-refresh"></i></button>
						<button type="button" id="btnOpenSort" class="btn btn-primary">开启排序</button>
						<button type="button" id="btnSaveSort" class="btn btn-danger hide">保存排序</button>
					</th>
				</tr>
			</thead>
			<tbody id="propertyList" class="openSort">
			</tbody>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="property:propertyTemplate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>