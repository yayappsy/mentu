<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>培训课程<spring:message code="admin.common.manager"/></title>
	<meta name="decorator" content="default"/>
    <%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function() {
            //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           var $inputForm = $("#inputForm");
           $inputForm.find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/course/">培训课程<spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/course/course/form?id=${course.id}">
		    <shiro:hasPermission name="course:course:edit">
		       培训课程
		       <spring:message code="admin.common.${not empty course.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="course:course:edit">
		           培训课程<spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="course" action="${ctx}/course/course/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>
		<div class="control-group">
			<label class="control-label">课程名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程标签：</label>
			<div class="controls">
				<sys:treeselect id="courseLabel" name="courseLabel.id"
								value="${course.courseLabel.id}" labelName=""
								labelValue="${course.courseLabel.name}"
								title="课程标签"
								url="/course/course/treeData" extId="${course.courseLabel.id}"
								cssClass="" allowClear="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">班级大小：</label>
			<div class="controls">
                <form:radiobutton path="size" value="1"/>小班
                <form:radiobutton path="size" value="2"/>中班
                <form:radiobutton path="size" value="3"/>大班
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${course.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="startDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${course.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			    <form:errors path="endDate" cssStyle="color:red"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">内容：</label>
            <div class="controls">
                <form:textarea path="content" htmlEscape="false" id="j_ueditorupload"
                               rows="4" style="display: inline-flex;" />
                <weimhc:ueditor ueContainer="j_ueditorupload"
                                uploadPath="${uploadFolder }"></weimhc:ueditor>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">学到内容：</label>
            <div class="controls">
                <form:textarea path="learn" htmlEscape="false" id="j_ueditorupload"
                               rows="4" style="display: inline-flex; width: 90%;" />
                <weimhc:ueditor ueContainer="j_ueditorupload"
                                uploadPath="${uploadFolder }"></weimhc:ueditor>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">行业名称：</label>
			<div class="controls">
				<form:input path="industryName" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="industryName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二维码地址：</label>
			<div class="controls">
				<input type="file" name="qrCodeFile">
				<img src="${course.qrCode}">
				<%--<form:input path="qrCode" htmlEscape="false" disabled="true" maxlength="255"
				          class="input-xlarge "/>
			    <form:errors path="qrCode" cssStyle="color:red"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">适合人群：</label>
			<div class="controls">
				<form:input path="fitPeople" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="fitPeople" cssStyle="color:red"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">课程简介：</label>
            <div class="controls">
                <form:input path="intro" htmlEscape="false" maxlength="255"
                            class="input-xlarge "/>
                <form:errors path="fitPeople" cssStyle="color:red"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">授课老师：</label>
			<div class="controls">
				<form:input path="teacher" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="teacher" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="price" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="email" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="address" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否滚动：</label>
			<div class="controls">
                <form:radiobutton path="isRoll" value="0"/>不滚动
                <form:radiobutton path="isRoll" value="1"/>滚动
			    <form:errors path="isRoll" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">背景图片地址：</label>
			<div class="controls">
				<input type="file" name="backImageFile">
				<img src="${course.backImage}">
				<%--<form:input path="backImage" htmlEscape="false" disabled="true" maxlength="255"
				          class="-xlarge "/>
			    <form:errors path="backImage" cssStyle="color:red"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="course:course:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>