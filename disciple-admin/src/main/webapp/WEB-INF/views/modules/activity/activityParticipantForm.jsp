<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.activityParticipant" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").bootstrapSwitch();
		$('#memebrSwitch').on('switchChange.bootstrapSwitch', function (event, state) {
		    if(state){
		    	$("#memberSelect").show();
			}else{
				$("#memberSelect").hide();
			}
		});
        if('true' === '${activityParticipant.isMember}'){
        	$("#memberSelect").show();
        }
		
		
		$("#btnSelectLink").on("click", function() {
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
						$("#name").val(selectData[0].name);
						$("#mobile").val(selectData[0].mobile);
						$("#email").val(selectData[0].email);
						$("#phone").val(selectData[0].phone);
						$("#memberId").val(selectData[0].id);
						$("#memberNickname").val(selectData[0].nickname);
						$("#showNickname").html(selectData[0].nickname);
					}
					layer.close(index);
				},
				area : [ '800px', '360px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/member/member/?searchType=selectLink'
			});
		});

		$("#btnActivitySelectLink").on("click", function() {
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
						$("#activityId").val(selectData[0].activityId);
						$("#showActivityName").html(selectData[0].name);
					}
					layer.close(index);
				},
				area : [ '800px', '360px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/activity/activity/?searchType=selectLink'
			});
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/activityParticipant/"><spring:message
					code="admin.activityParticipant" /> <spring:message
					code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/activity/activityParticipant/form?id=${activityParticipant.id}">
				<shiro:hasPermission name="activity:activityParticipant:edit">
					<spring:message code="admin.activityParticipant" />
					<spring:message
						code="admin.common.${not empty activityParticipant.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="activity:activityParticipant:edit">
					<spring:message code="admin.activityParticipant" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="activityParticipant"
		action="${ctx}/activity/activityParticipant/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="status" />
		<weimhc:message resultMessage="${resultMessage}" />

		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.activityId" />：</label>
			<div class="controls">
			    <form:hidden path="activity.id" id="activityId"/>
				<label id="showActivityName">
				  ${activityParticipant.activity.name }
				</label>
				<button id="btnActivitySelectLink" class="btn btn-primary" type="button">
					选择活动
				</button>
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="activity.id" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.isMember" />：</label>
			<div class="controls" id="memebrSwitch">
				<form:checkbox path="isMember"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
					class=" trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="isMember" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group hide" id="memberSelect">
			<label class="control-label"><spring:message
					code="ActivityParticipant.memberId" />：</label>
			<div class="controls">
				<form:hidden path="memberId" />
				<form:hidden path="memberNickname" />
				<label id="showNickname">${activityParticipant.memberNickname }</label>
				<button id="btnSelectLink" class="btn btn-primary" type="button">
					选择会员
				</button>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.email" />：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="20"
					class="input-xlarge " />
				<form:errors path="email" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.phone" />：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="20"
					class="input-xlarge " />
				<form:errors path="phone" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.mobile" />：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="mobile" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.detailedAddress" />：</label>
			<div class="controls">
				<form:input path="detailedAddress" htmlEscape="false"
					maxlength="100" class="input-xlarge " />
				<form:errors path="detailedAddress" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.zipcode" />：</label>
			<div class="controls">
				<form:input path="zipcode" htmlEscape="false" maxlength="6"
					class="input-xlarge " />
				<form:errors path="zipcode" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.detail" />：</label>
			<div class="controls">
				<form:input path="detail" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="detail" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.isAcceptEmail" />：</label>
			<div class="controls">
				<form:checkbox path="isAcceptEmail"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
					class=" trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="isAcceptEmail" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="ActivityParticipant.isAcceptSms" />：</label>
			<div class="controls">
				<form:checkbox path="isAcceptSms"
					data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
					data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
					class=" trueFalse" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="isAcceptSms" cssStyle="color:red" />
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
			<shiro:hasPermission name="activity:activityParticipant:edit">
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