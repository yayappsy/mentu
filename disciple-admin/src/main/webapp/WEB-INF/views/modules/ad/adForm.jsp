<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.ad" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/jquery-select2City/jquery.select2City.js"
	type="text/javascript"></script>
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$("#inputForm").on("click","#btnSelectLink", function() {
			layer.open({
				type : 2,
				btn: ['保存', '取消'],
				yes:function(index,layero){
					var $layerIframe = $(layero).find("#layui-layer-iframe"+index);
					var selectData = [];
					$layerIframe.contents().find("input[name='id']:checked").each(function(){
						selectData.push($(this).data());
					});
					if(selectData.length > 0){
						$("#url").val(selectData[0].type+"="+selectData[0].id);
					}
					layer.close(index);
				},
				area : [ '800px', '360px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/article/news/?searchType=selectLink'
			});
		});

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ad/ad/"><spring:message
					code="admin.ad.list" /></a></li>
		<li class="active"><a href="${ctx}/ad/ad/form?id=${ad.id}"> <shiro:hasPermission
					name="ad:ad:edit">
					<spring:message code="admin.ad.${not empty ad.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="ad:ad:edit">
					<spring:message code="admin.ad.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ad"
		action="${ctx}/ad/ad/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Ad.adPositionId" />：</label>
			<div class="controls">

				<form:select path="adPosition.id" class="input-xlarge ">
					<form:options items="${adPositionList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font>“广告位”表示该
					广告的位置</span>
				<form:errors path="adPosition" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Ad.title" />：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font>“标题”表示该广告的标题
				</span>
				<form:errors path="title" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Ad.endDate" />：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${ad.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<span class="help-inline">“开始时间”表示该广告有效期的开始时间 </span>
				<form:errors path="endDate" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Ad.beginDate" />：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ad.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
				<span class="help-inline">“结束时间”表示该广告有效期的结束时间 </span>
				<form:errors path="beginDate" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Ad.path" />：</label>
			<div class="controls">

				<form:hidden id="nameImage" path="path" htmlEscape="false"
					maxlength="255" class="input-xlarge" />
				<weimhc:selectImage input="nameImage" />
				<span class="help-inline">“广告文件地址”表示展示该广告的内容文件 </span>
				<form:errors path="path" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Ad.content" />：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4"
					class="input-xxlarge " />
				<span class="help-inline">“广告内容”表示广告的内容 </span>
				<form:errors path="content" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Ad.url" />：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<button id="btnSelectLink" class="btn btn-primary" type="button">
					<i class="icon-link"></i>
					<spring:message code='选择' />
				</button>
				<span class="help-inline">可以手动输入广告链接地址，也可以点击选择按钮选择 </span>
				<form:errors path="url" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Ad.isEnabled" />：</label>
			<div class="controls">
				<form:select path="isEnabled" class="input-xlarge ">
					<form:options items="${fns:getDictList('true_false')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline">“是否启用”表示广告是否需要使用 </span>
				<form:errors path="isEnabled" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Ad.sort" />：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11"
					class="input-xlarge required digit" />
				<span class="help-inline">“排序”表示该广告的显示顺序，按照由小到大排列 </span>
				<form:errors path="sort" cssStyle="color:red" />
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
			<shiro:hasPermission name="ad:ad:edit">
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