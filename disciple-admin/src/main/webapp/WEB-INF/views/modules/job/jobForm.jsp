<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职位管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
	<script src="${ctxStatic}/modules/company/selectCompany.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/job/job/">职位列表</a></li>
		<li class="active"><a href="${ctx}/job/job/form?id=${job.id}">职位<shiro:hasPermission name="job:job:edit">${not empty job.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="job:job:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="job" action="${ctx}/job/job/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">发布企业：</label>
			<div class="controls">
			 <form:hidden id="companyId" path="company.id" /> 
						<form:input id="companyName" path="company.name" htmlEscape="false"
							maxlength="64" class="input-medium" />
						<input id="btnCompanySelect" class="btn btn-primary" type="button"
							value="选择企业" />
						<input id="btnCompanySelectRemove" class="btn btn-primary"
							type="button" value="清除选择的企业" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职位类别：</label>
			<div class="controls">
			<sys:treeselect id="industry" name="industry.id"
					value="${job.industry.id}" labelName=""
					labelValue="${job.industry.name}"
					title="行业分类"
					url="/industry/industry/treeData" extId="${job.industry.id}"
					cssClass="" allowClear="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职位名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作城市：</label>
			<div class="controls">
			<sys:treeselect id="city" name="city.id" value="${job.city.id}" labelName="city.name" labelValue="${job.city.name}"
					title="<spring:message code='admin.commmon.area'/>" url="/sys/area/treeDataCity" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实习人数：</label>
			<div class="controls">
				<form:input path="practiceNumber" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职位诱惑：</label>
			<div class="controls">
				<form:input path="jobAttract" htmlEscape="false" maxlength="60" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工作地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低日薪：</label>
			<div class="controls">
				<form:input path="lowest" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最高日薪：</label>
			<div class="controls">
				<form:input path="highest" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">每周天数：</label>
			<div class="controls">
				<form:input path="dayNumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历要求：</label>
			<div class="controls">
			<form:select path="education" class="input-xlarge required">
				<form:option value=""><spring:message code="admin.common.unlimited"/></form:option>
				<form:option value="博士">博士</form:option>
				<form:option value="硕士">硕士</form:option>
				<form:option value="本科">本科</form:option>
				<form:option value="专科">专科</form:option>
			</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实习开始月份：</label>
			<div class="controls">
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${job.beginDate}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实习结束月份：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${job.endDate}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">截止日期：</label>
			<div class="controls">
				<input name="expiryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${job.expiryDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转正机会：</label>
			<div class="controls">
				<form:select path="chance" class="input-xlarge required">
					<form:option value=""><spring:message code="admin.common.choose"/></form:option>
					<form:option value="yes">提供转正</form:option>
					<form:option value="no">不提供转正</form:option>
					<form:option value="negotiate">面议</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">申请情况：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">职位状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge required">
					<form:option value=""><spring:message code="admin.common.choose"/></form:option>
					<form:option value="1">上线</form:option>
					<form:option value="2">下线</form:option>
					<form:option value="3">过期</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首页显示：</label>
			<div class="controls">
				<form:select path="homeShow" class="input-xlarge required">
					<form:option value="-1">不显示</form:option>
					<form:option value="1">热门</form:option>
					<form:option value="2">急招</form:option>
					<form:option value="3">推荐</form:option>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">平台评论：</label>
			<div class="controls">
				<form:textarea path="comment" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
<%--		<div class="control-group">
			<label class="control-label">是否倾向海外留学生：</label>
			<div class="controls">
				<form:radiobuttons path="isOverseas" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否接受投递简历：</label>
			<div class="controls">
				<form:radiobuttons path="isResume" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">是否内推：</label>
			<div class="controls">
				<form:radiobutton path="isPush" value="0" class="required"/>是
				<form:radiobutton path="isPush" value="1" class="required" />否
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		 <%--<div class="control-group">
			<label class="control-label">职位标签:</label>
			<div class="controls">
				<form:checkboxes path="jobLabelIdList" items="${jobLabels}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">二维码地址：</label>
			<div class="controls">
				<input type="file" name="qrCodeFile">
				<img src="${job.qrCode}">
					<%--<form:input path="qrCode" htmlEscape="false" disabled="true" maxlength="255"
                              class="input-xlarge "/>
                    <form:errors path="qrCode" cssStyle="color:red"/>--%>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">职位描述：</label>
			<div class="controls">
			<form:textarea path="description" htmlEscape="false" id="j_ueditorupload"
			rows="4" style="display: inline-flex;" />
		    <weimhc:ueditor ueContainer="j_ueditorupload"
			uploadPath="${uploadFolder }"></weimhc:ueditor>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="job:job:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>