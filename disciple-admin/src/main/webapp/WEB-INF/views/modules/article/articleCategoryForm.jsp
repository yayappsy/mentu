<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.articleCategory" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script type="text/javascript">
		$(function(){
                        //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/article/articleCategory/"><spring:message code="admin.articleCategory.list"/></a></li>
		<li class="active"><a href="${ctx}/article/articleCategory/form?id=${articleCategory.id}">
		    <shiro:hasPermission name="article:articleCategory:edit"><spring:message code="admin.articleCategory.${not empty articleCategory.id?'edit':'add'}"/></shiro:hasPermission>
		    <shiro:lacksPermission name="article:articleCategory:edit"><spring:message code="admin.articleCategory.view"/></shiro:lacksPermission>
		    </a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="articleCategory" action="${ctx}/article/articleCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="admin.common.parent"/><spring:message code="admin.articleCategory"/>:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${articleCategory.parent.id}" labelName="parent.name" labelValue="${articleCategory.parent.name}"
					title="<spring:message code='admin.articleCategory'/>" url="/article/articleCategory/treeData" extId="${articleCategory.id}" cssClass="" allowClear="true"/>
			<span class="help-inline">“上级文章分类”表示当前文章分类的上级栏目，例如咨询中心下的公司新闻，行业新闻，学术新闻等... </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.module"/>：</label>
			<div class="controls">
				<form:input path="module" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			    <form:errors path="module" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font>“栏目名称”表示当前栏目的名称，便与添加文章时选择 </span>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.image"/>：</label>
			<div class="controls">
			<weimhc:selectImage input="image" />
			<span class="help-inline"><font color="red">*</font>“栏目图片”表示当前栏目的图片，例如首页公司新闻，行业新闻，学术新闻等展示的图片 </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.url"/>：</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			    <form:errors path="href" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.target"/>：</label>
			<div class="controls">
				<form:input path="target" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			    <form:errors path="target" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.description"/>：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline">“栏目描述”表示当前栏目的描述 </span>
			    <form:errors path="description" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.keywords"/>：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>“关键字”表示当前栏目的关键字，例如首页公司新闻，行业新闻，学术新闻下面的关键词：Company NEWS等</span>
			    <form:errors path="keywords" cssStyle="color:red"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.url"/>：</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font>“链接”表示当前栏目的跳转链接，如友情链接等</span>
			    <form:errors path="href" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.sort"/>：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge "/>
				<span class="help-inline">“排序”表示显示当前栏目的顺序先后，按从小到大顺序 </span>
			    <form:errors path="sort" cssStyle="color:red"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.inMenu"/>：</label>
			<div class="controls">
				
			    <form:select path="inMenu" id="inMenu">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.inList"/>：</label>
			<div class="controls">
				
			     <form:select path="inList" id="inList">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.showModes"/>：</label>
			<div class="controls">
				<form:input path="showModes" htmlEscape="false"  class="input-xlarge "/>
			    <form:errors path="showModes" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.allowComment"/>：</label>
			<div class="controls">
			     <form:select path="allowComment" id="allowComment">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.isAudit"/>：</label>
			<div class="controls">
			    <form:select path="isAudit" id="isAudit">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.customListView"/>：</label>
			<div class="controls">
				<form:input path="customListView" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			    <form:errors path="customListView" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.customContentView"/>：</label>
			<div class="controls">
				<form:input path="customContentView" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			    <form:errors path="customContentView" cssStyle="color:red"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="ArticleCategory.viewConfig"/>：</label>
			<div class="controls">
				<form:input path="viewConfig" htmlEscape="false" class="input-xlarge "/>
			    <form:errors path="viewConfig" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="article:articleCategory:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>