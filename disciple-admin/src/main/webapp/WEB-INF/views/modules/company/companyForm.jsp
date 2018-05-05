<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
    <%@ include file="/WEB-INF/views/include/formHead.jsp"%>
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
		<li><a href="${ctx}/company/company/list">企业列表</a></li>
		<li class="active">企业<shiro:hasPermission name="company:company:edit">${not empty company.id?'审核':'添加'}</shiro:hasPermission><shiro:lacksPermission name="company:company:edit">查看</shiro:lacksPermission></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="company" action="${ctx}/company/company/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<%--		<div class="control-group">
			<label class="control-label">企业logo：</label>
			<div class="controls">
			<form:hidden path="logo" data-thumbnail="" />
		    <weimhc:selectImage input="logo" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业法律名称：</label>
			<div class="controls">
				<form:input path="legalName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业简称：</label>
			<div class="controls">
				<form:input path="shortName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主营业务描述：</label>
			<div class="controls">
				<form:textarea path="mainBusinessDescription" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">LOGO：</label>
			<div class="controls">
				<input type="file" name="logoFile">
				<img src="${company.logo}">
					<%--<form:input path="backImage" htmlEscape="false" disabled="true" maxlength="255"
                              class="-xlarge "/>
                    <form:errors path="backImage" cssStyle="color:red"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业所在区域：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${company.area.id}" labelName="area.name" labelValue="${company.area.name}"
					title="<spring:message code='admin.commmon.area'/>" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			    <form:errors path="area.id" cssStyle="color:red"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司类型：</label>
			<div class="controls">
			<form:select path="companyType">
			<c:forEach items="${companyTypes}" var="companyType">
			<form:option value="${companyType}"><spring:message code="CompanyType.${companyType}"/></form:option>
			</c:forEach>
			</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司规模：</label>
			<div class="controls">
			<form:select path="size" class="input-xlarge">
			<form:option value="少于15人">少于15人</form:option>
		    <form:option value="15-50人">15-50人</form:option>
		    <form:option value="50-150人">50-150人</form:option>
		    <form:option value="150-500人">150-500人</form:option>
		    <form:option value="500-2000人">500-2000人</form:option>
		    <form:option value="2000人以上">2000人以上</form:option>
			</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司网址：</label>
			<div class="controls">
				<form:input path="site" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">组织机构代码：</label>
			<div class="controls">
				<form:input path="organizationCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成立资本(元)：</label>
			<div class="controls">
				<form:input path="capital" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%--		<div class="control-group">
			<label class="control-label">验证完成阶段：</label>
			<div class="controls">
				<form:input path="stage" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">证明资料：</label>
			<div class="controls">
			<form:hidden path="proofData" data-thumbnail="" />
		    <weimhc:selectImage input="proofData" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">首页显示：</label>
			<div class="controls">
				<form:select path="homeShow" class="input-xlarge required">
					<form:option value="0">不显示</form:option>
					<form:option value="1">显示</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否通过审核：</label>
			<div class="controls">
		    <form:radiobutton path="isPass" value="1"  />通过
		    <form:radiobutton path="isPass" value="0"  />不通过
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成立日期：</label>
			<div class="controls">
				<input name="foundingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${company.foundingTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业简介：</label>
			<div class="controls">
				<form:textarea path="introduce" htmlEscape="false" rows="4" class="input-xxlarge required"/>
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
			<label class="control-label">企业标签:</label>
			<div class="controls">
				<form:checkboxes path="companyLabelIdList" items="${companyLabels}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/> 
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="company:company:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script>
        $(function () {
            $("#upload").click(function () {
        var form = new FormData(document.getElementById("formId"));
        //ajax 提交form 表单
        $.ajax({
            url:"${ctx}/company/company/upFile",
            type:"post",
            data:form,
            cache: false,
            processData: false,
            contentType: false,
            success:function(ret){
                if (ret == 1) {//返回1上传成功
                    alert("上传成功！");
                } else if (ret == 2) {//返回2文件类型不符合要求
                    alert("文件类型不符合要求！");
                } else if (ret == 3) {//返回3请选择文件
                    alert("请选择文件！");
                }
            },
            error:function(e){
                alert("上传失败！！");
            }
        })}};

	</script>
</body>
</html>