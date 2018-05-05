<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script src="${ctxStatic}/jquery-limitTextarea/limitTextarea.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/jquery-insertContent/jquery-insertContent.js?ver=1.0"
	type="text/javascript"></script>
<style type="text/css">
.yl_box {
	position: fixed;
	height: 200px;
	border: 1px solid #ccc;
	margin-right: 30px;
	top: 60px;
	right: 10px;
}

.yl_boxul {
	height: 160px;
	overflow-x: hidden;
	overflow-y: scroll;
	width: 150px;
	padding: 0;
}

.yl_boxul li {
	list-style: none;
	cursor: pointer;
	text-align: left;
	line-height: 27px;
	overflow: hidden;
}

.yl_boxul li span {
	float: left;
}

.yl_top {
	font-size: 12px;
	line-height: 28px;
	text-align: center;
	background: #f2f2f2;;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
				//设置选择样式
				var $inputForm = $("#subInputForm");
				$inputForm.find(".trueFalse").each(function() {
					$(this).bootstrapSwitch();
				});
				$inputForm.validate({
					submitHandler : function(form) {
						loading();
						$.ajax({
							url : $inputForm.attr("action"),
							type : "POST",
							dataType : "json",
							data : $inputForm.serialize(),
							cache : false,
							success : function(result) {
								closeTip();
								parent.layer.close(parent.layer
										.getFrameIndex(window.name));
							},
							error : function(result) {
								closeTip();
							}
						});
					}
				});
				$("#emailSetting").on(
						"click",
						function() {
							var index = parent.layer.open({
								title : "设置企业邮箱",
								type : 2,
								btn : [ '保存', '取消' ],
								yes : function(index, layero) {
									var $layerIframe = $(layero).find(
											"#layui-layer-iframe" + index);
									var $subForm = $layerIframe.contents()
											.find("#mailConfigForm");
									if ($subForm.valid()) {
										loading();
										$.ajax({
											url : $subForm.attr("action"),
											type : "POST",
											dataType : "json",
											data : $subForm.serialize(),
											cache : false,
											success : function(result) {
												closeTip();
												parent.layer.close(index);
											},
											error : function(result) {
												closeTip();
											}
										});
									}
								},
								area : [ '600px', '360px' ],
								shadeClose : true, //点击遮罩关闭
								content : "${ctx}/base/mailConfig/default"
							});
						});
				$('#displayContent').limitTextarea({
					maxNumber : ${remindTemplate.remindWay.maxLength}, //最大字数
					position : 'bottom', //提示文字的位置，top：文本框上方，bottom：文本框下方
					onOk : function() {
						$('#displayContent').css('background-color', 'white');
					}, //输入后，字数未超出时调用的函数
					onOver : function() {
						$('#displayContent').css('background-color', 'lightpink');
					} //输入后，字数超出时调用的函数，这里把文本区域的背景变为粉红色   
				});
				var $remindParameters = $("#remindParameters");
				$('#displayTitle,#displayContent').on("blur",function(){
					$remindParameters.data({
						"targetId":$(this).attr("id"),
						"targetPosition":$(this).getCursorPosition(),
					});
				});
				$remindParameters.on("click",".yl_boxul li",function(){
					var $target = $("#"+$remindParameters.data("targetId"));
					$target.insertContent("<("+$(this).data("name")+")>",
							$remindParameters.data("targetPosition"));
				})
			});
</script>

</head>

<body>
	<br />

	<form:form id="subInputForm" modelAttribute="remindTemplate"
		action="${ctx}/remind/remindTemplate/ajaxSave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />
		<c:set var="isEmail" value="${remindTemplate.remindWay.id eq '1' }" />
		<c:if test="${isEmail}">
			<div class="control-group ">
				<label class="control-label"><spring:message code="企业邮箱信息" />：</label>
				<div class="controls">
					${mailConfig.displayName }(${mailConfig.address })
					<button type="button" class="btn btn-primary" id="emailSetting">
						<spring:message code="admin.common.setting" />
					</button>
				</div>
			</div>
		</c:if>
		<div class="control-group ">
			<label class="control-label"><spring:message code="提醒状态" />：</label>
			<div class="controls btn-groups">
				<form:checkbox path="ifEnabled" data-on-text="启用" data-off-text="停用"
					class="trueFalse" />
				<button type="button" class="btn btn-primary" id="viewSample">
					<spring:message code="查看提醒样例" />
				</button>
			</div>
		</div>
		<c:if test="${remindTemplate.remind.targetType eq 'admin' }">
			<c:if test="${isEmail}">
				<div class="control-group ">
					<label class="control-label"><spring:message
							code="提醒接收者邮箱或手机号" />：</label>
					<div class="controls btn-groups">
						<form:input path="addressee" />
						<span class="help-inline">多个地址之间用分号进行分割 ，请输入准确的信息</span>
					</div>
				</div>
			</c:if>
		</c:if>
		<div class="control-group ">
			<label class="control-label"><spring:message code="提醒标题" />：</label>
			<div class="controls btn-groups">
				<form:input path="displayTitle" class="input-xlarge " />
			</div>
		</div>
		<div id="titleContainer" class="control-group ">
			<label class="control-label"><spring:message code="提醒文本" />：</label>
			<div class="controls btn-groups">
				<form:textarea path="displayContent" rows="6"
					maxlength="${remindTemplate.remindWay.maxLength}"
					class="input-xlarge "></form:textarea>

			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="remind:remindTemplate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
		</div>
	</form:form>
	<div id="remindParameters" class="yl_box">
		<div class="yl_top">点击下列信息插入光标处</div>
		<ul class="yl_boxul">
		     <c:forEach items="${remindParameters }" var="remindParameter">
			<li data-name="${remindParameter.name}"><span class="addicon">+</span><span
				class="text_over">${remindParameter.name}</span></li>
				</c:forEach>
		</ul>
	</div>
</body>
</html>