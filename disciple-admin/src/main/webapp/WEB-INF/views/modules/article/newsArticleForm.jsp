<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.article" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		$("#inputForm").find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/article/news/?category.parent.id=${article.category.parent.id}"><spring:message
					code="admin.article.list" /></a></li>
		<li class="active"><a
			href="${ctx}/article/news/form?id=${article.id}"> <shiro:hasPermission
					name="article:article:edit">
					<spring:message
						code="admin.article.${not empty article.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="article:article:edit">
					<spring:message code="admin.article.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="article"
		action="${ctx}/article/news/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="category.parent.id" />
		<weimhc:message resultMessage="${resultMessage}" />

		<div class="control-group">
			<label class="control-label"><spring:message
					code="Article.image" />：</label>
			<div class="controls">
				<form:hidden path="image" data-thumbnail="" />
				<%--供临时插入数据使用 --%>
				<weimhc:selectImage input="image" />
				<form:errors path="image" cssStyle="color:red" />
			</div>
		</div>

				<div class="control-group">
			<label class="control-label"><spring:message code="Article.categoryId"/>：</label>
			<div class="controls">
			
			<sys:treeselect id="category" name="category.id" value="${article.category.id}" labelName="" labelValue="${article.category.name}"
					title="文章类别" url="/article/articleCategory/treeData" extId="${article.category.parent.id}" cssClass="required" allowClear="true"/>
			
				<span class="help-inline"><font color="red">*</font>“文章类别”表示文章的类别，用来区分显示 </span>
			    <form:errors path="category" cssStyle="color:red"/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Article.title" />：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
			<span class="help-inline"><font color="red">*</font>“标题”表示该文章的标题 </span>
				<form:errors path="title" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Article.keywords" />：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<span class="help-inline">“关键字”表示当前文章的关键词汇，例如在资讯详情左下角使用 </span>
				<form:errors path="keywords" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Article.description" />：</label>
			<div class="controls">
				<form:textarea path="description" rows="4" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<span class="help-inline">“描述，摘要”表示当前文章的摘要，在资讯中心列表中展示</span>
				<form:errors path="description" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Article.articleSource" />：</label>
			<div class="controls">
				<form:input path="articleSource" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<span class="help-inline">“文章来源”表示当前文章的来源，出处，在资讯详情中展示</span>
				<form:errors path="articleSource" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Article.author" />：</label>
			<div class="controls">
				<form:input path="author" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
					<span class="help-inline">“作者”表示当前文章的作者，在资讯详情中展示</span>
				<form:errors path="author" cssStyle="color:red" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="作者身份" />：</label>
			<div class="controls">
				<form:input path="authorIdentity" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
				<form:errors path="authorIdentity" cssStyle="color:red" />
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message
					code="Article.isRecommend" />:</label>
			<div class="controls">
				<form:select path="isRecommend">
					<form:options items="${fns:getDictList('true_false')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Article.isHot" />：</label>
			<div class="controls">
				<form:select path="isHot">
					<form:options items="${fns:getDictList('true_false')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="DataEntity.sort" />:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11"
					class="input-xlarge digit" />
					<span class="help-inline">“排序”表示当前文章的显示顺序，在资讯中心列表中的展示顺序，按从小到大顺序</span>
				<form:errors path="sort" cssStyle="color:red" />
			</div>
		</div>
		
		<div class="control-group" style="height: auto;">
			<label class="control-label"><spring:message
					code="Article.content" />：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false"
					id="j_ueditorupload" rows="4" style="display: inline-flex;" />
				<weimhc:ueditor ueContainer="j_ueditorupload"
					uploadPath="${uploadFolder }" ></weimhc:ueditor>
					<span class="help-inline">“文章内容”表示当前文章的内容及展示效果，例如资讯详情，联系我们，招贤纳士...等中展示</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.remarks"/>：</label>        
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			    <form:errors path="remarks" cssStyle="color:red"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="article:article:edit">
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