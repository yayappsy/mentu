<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.rssContent" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
           //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           //设置选择样式
           $("#inputForm").find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			});

           $("#btnSelectLink").on("click", function() {
   			layer.open({
   				type : 2,
   				btn: ['选择', '取消'],
   				yes:function(index,layero){
   					var $layerIframe = $(layero).find("#layui-layer-iframe"+index);
   					var selectData = [];
   					$layerIframe.contents().find("input[name='id']:checked").each(function(){
   						selectData.push($(this).data());
   					});
   					if(selectData.length > 0){
   						$("#rssName").val(selectData[0].name);
   						$("#rssUrl").val(selectData[0].url);
   					}
   					layer.close(index);
   				},
   				area : [ '800px', '360px' ],
   				shadeClose : true, //点击遮罩关闭
   				content : '${ctx}/article/rssFeed/?searchType=selectLink'
   			});
   		});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/article/rssContent/"><spring:message code="admin.rssContent"/><spring:message code="admin.common.list"/></a></li>
		<li class="active"><a href="${ctx}/article/rssContent/form?id=${rssContent.id}">
		    <shiro:hasPermission name="article:rssContent:edit">
		       <spring:message code="admin.rssContent"/>
		       <spring:message code="admin.common.${not empty rssContent.id?'edit':'add'}"/>
		    </shiro:hasPermission>
		    <shiro:lacksPermission name="article:rssContent:edit">
		           <spring:message code="admin.rssContent"/><spring:message code="admin.common.view"/>
		    </shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="rssContent" action="${ctx}/article/rssContent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="RssContent.rssName"/>：</label>
			<div class="controls">
				<form:input path="rssName" htmlEscape="false" maxlength="100" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<button type="button" id="btnSelectLink" class="btn btn-primary">选择RSS</button>
			    <form:errors path="rssName" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssContent.rssUrl"/>：</label>
			<div class="controls">
				<form:input path="rssUrl" htmlEscape="false" maxlength="255" 
				          class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="rssUrl" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="RssContent.articleCategoryId"/>：</label>
			<div class="controls">
			    <sys:treeselect id="articleCategoryId" name="articleCategory.id"
					value="${rssContent.articleCategory.id}" labelName="articleCategoryName"
					labelValue="${rssContent.articleCategoryName}" title="文章类别"
					url="/article/articleCategory/treeData" cssClass="required"
					allowClear="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="articleCategory.id" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssContent.keywords"/>：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="50" 
				          class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font>表示新闻搜索的关键词，目前通过标题或描述过滤 </span>
			    <form:errors path="keywords" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="RssContent.isEnabled"/>：</label>
			<div class="controls">
				<form:checkbox path="isEnabled" data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
			         data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
			          class="required trueFalse"/>			   
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="isEnabled" cssStyle="color:red"/>
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
            <shiro:hasPermission name="article:rssContent:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>