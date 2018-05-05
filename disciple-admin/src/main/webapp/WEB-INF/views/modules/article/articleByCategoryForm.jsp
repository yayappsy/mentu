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
		$("#inputForm").on("click",".articleType",function() {
			showArticleContent();
		});
		function showArticleContent(){
			$selObj = $("#inputForm").find(".articleType:checked");
            if($selObj.val() == "1"){
				$(".normal").show();
				$(".link").hide();
			}else{
				$(".normal").hide();
				$(".link").show();
			}
		}
		showArticleContent();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/article/byCategory/?category.id=${article.category.id}"><spring:message
					code="admin.article.list" /></a></li>
		<li class="active"><a
			href="${ctx}/article/byCategory/form?id=${article.id}&category.id=${article.category.id}">
				<shiro:hasPermission name="article:article:edit">
					<spring:message
						code="admin.article.${not empty article.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="article:article:edit">
					<spring:message code="admin.article.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="article"
		action="${ctx}/article/byCategory/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<weimhc:message resultMessage="${resultMessage}" />

		<%@include file="/WEB-INF/views/modules/article/articleFormBase.jsp" %>
	</form:form>
</body>
</html>