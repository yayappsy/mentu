<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.recruitment" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
           //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           //设置选择样式
           $("#inputForm").find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});
           $('#department').click(function(){
   			//iframe窗		
   			var ids = "";      	
   			    layer.open({
   			      type: 2,  	
   			      shadeClose: true,
   			      shade: false,
   			      maxmin: true, //开启最大化最小化按钮
   			      area: ['893px', '600px'],
   			      content: '${ctx}/base/department',
   			    	  btn: ['确定','关闭'],
   	                  yes: function(index){ 	    
   	                        //最后关闭弹出层
   	                        layer.close(index);
   	                       
   	                    },
   	                    cancel: function(){
   	                        //右上角关闭回调
   	                    }
   			    });
   			
   	        });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/recruit/recruitment/"><spring:message code="admin.recruitment"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/recruit/recruitment/form?id=${recruitment.id}">
		    <shiro:hasPermission name="recruit:recruitment:edit">
		       <spring:message code="admin.recruitment"/>
		       <spring:message code="admin.common.${not empty recruitment.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="recruit:recruitment:edit">
		           <spring:message code="admin.recruitment"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recruitment" action="${ctx}/recruit/recruitment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>	
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.employType"/>：</label>
			<div class="controls">
				<form:select path="employType.id" class="input-xlarge required">
				 <form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
				<form:options items="${recruitmentTypes}" itemLabel="name" itemValue="id"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="employType" cssStyle="color:red"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.jobTitle"/>：</label>
			<div class="controls">
				<form:input path="jobTitle" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="jobTitle" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.gender"/>：</label>
			<div class="controls">
				<form:select path="gender">
				<form:options items="${fns:getDictList('sex')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="gender" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.salaryRangeId"/>：</label>
			<div class="controls">
				<form:input path="salaryRangeId" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="salaryRangeId" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.areaId"/>：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${recruitment.area.id}" labelName="" labelValue="${recruitment.area.name}"
					title="<spring:message code='admin.commmon.area'/>" url="/sys/area/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="area.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.department"/>：</label>
			<div class="controls">
				<form:select path="department.id">
				 <form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
				<form:options items="${departments}" itemLabel="name" itemValue="id"/>
				</form:select>
				<a href="javascript:;" id="department" class="layui-btn layui-btn-normal">工作部门维护</a>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="department.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.workingProperty"/>：</label>
			<div class="controls">
				<form:select path="workingProperty">
				<form:options items="${fns:getDictList('working_property')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="department.id" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.resumeLanguage"/>：</label>
			<div class="controls">
			<form:checkboxes items="${fns:getDictList('resume_language')}" path="resumeLanguage" itemLabel="label" itemValue="value"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="department.id" cssStyle="color:red"/>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.ageLimit"/>：</label>
			<div class="controls">
				<form:input path="ageLimit" htmlEscape="false" maxlength="64" 
				          class="input-xlarge "/>
			    <form:errors path="ageLimit" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.deadline"/>：</label>
			<div class="controls">
				<input name="deadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${recruitment.deadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="deadline" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.keywords"/>：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="64" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="keywords" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.number"/>：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="11" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="number" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.minimumEducationId"/>：</label>
			<div class="controls">	
			<form:select path="minimumEducation" class="input-xlarge required">
			    <form:option value="0"><spring:message code="admin.common.unlimited"/></form:option>
				<form:options items="${educations}" itemLabel="name" itemValue="id"/>
				</form:select>	
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="minimumEducation" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.workingTimeId"/>：</label>
			<div class="controls">
				<form:select path="workingTime" class="input-xlarge required">
				<form:option value="0"><spring:message code="admin.common.unlimited"/></form:option>
				<form:options items="${workingYearsList}"
						itemLabel="name" itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="workingTime" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.releaseDate"/>：</label>
			<div class="controls">
				<input name="releaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${recruitment.releaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="releaseDate" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.isShow"/>：</label>
			<div class="controls">
			<form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isShow" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="Recruitment.positionMark"/>：</label>
			<div class="controls">
			<form:checkboxes path="positionMark" items="${fns:getDictList('position_mark')}" itemLabel="label" itemValue="value"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="positionMark" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="tabbable" id="tabs-793760" style="padding-left:100px">
				<ul class="nav nav-tabs">
					<li>
						<a href="#panel-684202" data-toggle="tab"><spring:message code="Recruitment.jobDescription"/></a>
					</li>
					<li class="active">
						<a href="#panel-523317" data-toggle="tab"><spring:message code="Recruitment.requirement"/></a>
					</li>
					<li>
						<a href="#panel-523319" data-toggle="tab"><spring:message code="Recruitment.other"/></a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane" id="panel-684202">
						<p>
						<form:textarea path="jobDescription" htmlEscape="false"
					id="j_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="j_ueditorupload"
					uploadPath="${uploadFolder }" ></weimhc:ueditor>
						</p>
					</div>
					<div class="tab-pane active" id="panel-523317">
						<p>
						<form:textarea path="requirement" htmlEscape="false"
					id="r_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="r_ueditorupload"
					uploadPath="${uploadFolder }" ></weimhc:ueditor>
						</p>
					</div>
					<div class="tab-pane " id="panel-523319">
						<p>
								<form:textarea path="other" htmlEscape="false"
					id="o_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="o_ueditorupload"
					uploadPath="${uploadFolder }" ></weimhc:ueditor>
						</p>
					</div>
				</div>
			</div>
		<div class="form-actions">
            <shiro:hasPermission name="recruit:recruitment:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>