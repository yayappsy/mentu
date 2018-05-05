<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.ad" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$inputForm.find(".icheck").iCheck({
			checkboxClass: "icheckbox_square-blue",
		    radioClass: "iradio_square-blue"
		});
		$("input[name='isForever']").on('ifChecked', function(event){
			 $("#endDate").val("");
		});
		$inputForm.on("click","#btnSelectLink", function() {
			var $this = $(this);
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
						$("#"+$this.data("for")).val(selectData[0].type+"Id="+selectData[0].id);
					}
					layer.close(index);
				},
				area : [ '800px', '360px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/article/news/?searchType=selectLink'
			});
		});
	});
    function endDatePicked(){
    	$("input[name='isForever']").iCheck("toggle");
    }
</script>
<style type="text/css">
.form-horizontal .controls-sub-label-horizontal {
	width: 100px;
	padding-top: 5px;
	text-align: right;
}

.form-horizontal .controls-sub-label-vertical {
	float: left;
	width: 100px;
	padding-top: 5px;
	text-align: right;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class=""><a
			href="${ctx}/ad/ad/list?adPositionId=${ad.adPosition.id}&dispalyMoreAdPosition=${dispalyMoreAdPosition}">
				${ad.adPosition.name } <spring:message code="admin.common.list" />
		</a></li>
		<li class="active"><a
			href="${ctx}/ad/ad/form?id=${ad.id}&adPositionId=${ad.adPosition.id}&dispalyMoreAdPosition=${dispalyMoreAdPosition}">
				${ad.adPosition.name } <shiro:hasPermission
					name="ad:ad:edit">
					<spring:message
						code="admin.common.${not empty ad.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="ad:ad:edit">
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ad"
		action="${ctx}/ad/ad/save?dispalyMoreAdPosition=${dispalyMoreAdPosition}" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="adPosition.id" id="adPositionId"/>
		<form:hidden path="isAutoplay" value="0"/>
		<weimhc:message resultMessage="${resultMessage}" />
		<fieldset>
			<legend>基本信息</legend>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Ad.title" />：</label>
				<div class="controls">
					<form:input path="title" htmlEscape="false" maxlength="255"
						class="input-xlarge required" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="title" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Ad.description" />：</label>
				<div class="controls">
					<form:textarea path="description" htmlEscape="false"
						maxlength="255" rows="4" cols="4" class="input-xlarge " />
					<form:errors path="description" cssStyle="color:red" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><spring:message
						code="Ad.beginDate" />：</label>
				<div class="controls">
					<input name="beginDate" type="text" readonly="readonly" id="beginDate"
						maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ad.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					<form:errors path="beginDate" cssStyle="color:red" />
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Ad.endDate" />：</label>
				<div class="controls">
					<input name="endDate" type="text" readonly="readonly"  id="endDate"
						maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ad.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,onpicked:endDatePicked});" />
					<form:errors path="endDate" cssStyle="color:red" />
					
					<label for="isForever"><spring:message
						code="Ad.isForever" /></label><form:checkbox path="isForever" class="icheck"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><spring:message
						code="Ad.isShow" />：</label>
				<div class="controls">
					<form:checkbox path="isShow"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />

					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isShow" cssStyle="color:red" />
				</div>
			</div>
		</fieldset>
		<fieldset id="adsSet">
			<legend>详细信息</legend>
			<div id="adsContainer">
			   <%@include file="/WEB-INF/views/modules/ad/details/normal/informationForm.jsp" %>
			</div>
		</fieldset>
		<div class="control-group hide">
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