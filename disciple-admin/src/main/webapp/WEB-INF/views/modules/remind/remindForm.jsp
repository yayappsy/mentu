<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$inputForm.find(".remindWay").iCheck({
			checkboxClass: "icheckbox_square-blue",
		    radioClass: "iradio_square-blue"
		});
		var $remindTemplateList = $("#remindTemplateList");
		var remindTemplateTpl = $("#remindTemplateTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		$("#inputForm").find(".remindWay").on("ifChanged",function(event){
            var $this = $(this);
			console.log($this);
            var prefix = "#remindWay_"+$this.val();
            var $obj = $(prefix);
            var id = $(prefix+"_id");
			var $delFlag = $(prefix+"_delFlag");
            if($this.prop("checked")){
                if($obj.length == 0){
                	$remindTemplateList.append(Mustache.render(remindTemplateTpl, {idx:$this.val(),
        				row: {id:"",remindWayId:$this.val()}
        			}));
                }else{
                	$delFlag.val('0'); 
                } 
            }else{
            	$delFlag.val('1'); 
            }           

		});
		
	});
</script>
<script type="text/template" id="remindTemplateTpl">
<div id="remindWay_{{idx}}" class="hide">
    <div>
        <input id="remindTemplateList{{idx}}_id" name="remindTemplateList[{{idx}}].id" id="remindWay_${remindWayId}_id"
                        type="hidden" value="{{row.id}}"/>
        <input type="hidden" value="0" 
						name="remindTemplateList[{{idx}}].delFlag"  id="remindWay_${remindWayId}_delFlag">
		<input type="hidden" value="{{row.remindWayId}}" 
						name="remindTemplateList[{{idx}}].remindWay.id"  id="remindWay_${remindWayId}_remindWay_id">	
    </div>
</div>
</script>
</head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/remind/remind/"><spring:message
					code="admin.remind" /> <spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/remind/remind/form?id=${remind.id}"> <shiro:hasPermission
					name="remind:remind:edit">
					<spring:message code="admin.remind" />
					<spring:message
						code="admin.common.${not empty remind.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="remind:remind:edit">
					<spring:message code="admin.remind" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="remind"
		action="${ctx}/remind/remind/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.description" />：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="admin.remind.ways" />：</label>
			<div id="remindTemplateList" class="controls">
				<c:forEach items="${remind.remindTemplateList}" var="remindTemplate">
				<c:set var="remindWayId" value="${remindTemplate.remindWay.id}"></c:set>
				<div id="remindWay_${remindWayId}">
					<input type="hidden" value="${remindTemplate.id}"
						name="remindTemplateList[${remindWayId}].id" id="remindWay_${remindWayId}_id">
					<input type="hidden" value="${remindTemplate.delFlag}" 
						name="remindTemplateList[${remindWayId}].delFlag"  id="remindWay_${remindWayId}_delFlag">
					<input type="hidden" value="${remindTemplate.remindWay.id}" 
						name="remindTemplateList[${remindWayId}].remindWay.id"  id="remindWay_${remindWayId}_remindWay_id">	
				</div>
				</c:forEach>
				<form:checkboxes items="${remindWays}" path="selectedRemindWayList" cssClass="remindWay"
				  itemLabel="name" itemValue="id" element="label"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.targetType" />：</label>
			<div class="controls">
				<form:select path="targetType">
					<c:forEach items="${targetTypes }" var="item">
						<form:option value="${item }">
							<spring:message code="Remind.TargetType.${item }" />
						</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="targetType" cssStyle="color:red" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message
					code="Remind.businessAction" />：</label>
			<div class="controls">
				<form:select path="businessAction">
					<c:forEach items="${businessActions }" var="item">
						<form:option value="${item }">
							<spring:message code="Remind.BusinessAction.${item }" />
						</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="businessAction" cssStyle="color:red" />
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
		<div class="form-actions">
			<shiro:hasPermission name="remind:remind:edit">
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