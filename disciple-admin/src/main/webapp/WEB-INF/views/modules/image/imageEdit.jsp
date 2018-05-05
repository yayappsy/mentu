<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.image" /><spring:message code="admin.common.manager" /></title>
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
		var $inputForm = $("#inputForm");
		$("#pathType").on("change", function() {
			var $this = $(this);
			displayImage($this.val());
		});
		$("#url").on("change", function() {
			var urlP = $(this).val();
			$("#storagePath").val(urlP);
			$("#remotePreview").find("img").attr("src", urlP);
		});
		$("#storagePath").on("change", function(event,data) {
			$("#url").val($(this).val());
			$("#width").val(data.imgWidth);
			$("#height").val(data.imgHeight);
			$("#extension").val(data.ext);
		});
		function displayImage(selector) {
			$(".showImage").hide();
			$("#" + selector + "Image").show();
		}
		displayImage('${image.pathType}');
	});
</script>
<style type="text/css">
#remotePreview {
	clear: both;
	width: 200px;
	height: 120px;
	z-index: 9999;
	margin-top: 10px;
	margin-bottom: 10px;
	background-color: #eee;
	overflow: hidden;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/image/image/?searchType=${searchType }"><spring:message
					code="admin.image" /> <spring:message code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/image/image/form?id=${image.id}&searchType=${searchType }"> <shiro:hasPermission
					name="image:image:edit">
					<spring:message code="admin.image" />
					<spring:message
						code="admin.common.${not empty image.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="image:image:edit">
					<spring:message code="admin.image" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="image"
		action="${ctx}/image/image/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="extension" />
		<input id="searchType" name="searchType" type="hidden" value="${searchType }" />
		<weimhc:message resultMessage="${resultMessage}" />
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Image.name" />：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="name" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message
					code="Image.albumId" />：</label>
			<div class="controls">
				<sys:treeselect id="album" name="album.id" value="${image.album.id}"
					labelName="albumName" labelValue="${image.albumName}"
					title="<spring:message code='admin.ImageAlbum'/>"
					url="/image/imageAlbum/treeData" cssClass="" allowClear="true"
					notAllowSelectParent="false" notAllowSelectRoot="false" />

				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="album.id" cssStyle="color:red" />
			</div>
		</div>
		<c:choose>
			<c:when test="${fns:isBlank(image.id)}">
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Image.pathType" />：</label>
					<div class="controls">
						<form:select path="pathType" class="input-xlarge required">
							<c:forEach items="${pathTypes}" var="pathTypeVal">
								<form:option value="${pathTypeVal }">
									<spring:message code="PathType.${pathTypeVal }" />
								</form:option>
							</c:forEach>
						</form:select>
						<span class="help-inline"><font color="red">*选定之后不能修改</font>
							外网存储图片是指在互联网上找的的图片，不存储在自己的服务器上。 服务器存储是指在本地上传的自己服务器的图片。 </span>
						<form:errors path="pathType" cssStyle="color:red" />
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Image.pathType" />：</label>
					<div class="controls">
						<form:hidden path="pathType" />
						<spring:message code="PathType.${image.pathType}" />
						<span class="help-inline"><font color="red">*</font>
							外网存储图片是指在互联网上找的的图片，不存储在自己的服务器上。 服务器存储是指在本地上传的自己服务器的图片。 </span>
						<form:errors path="pathType" cssStyle="color:red" />
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div id="localImage" class="control-group showImage hide">
			<label class="control-label"><spring:message
					code="Image.storagePath" />：</label>
			<div class="controls">
				<form:hidden path="storagePath" id="storagePath" htmlEscape="false"
					maxlength="255" />
				<script type="text/plain" id="storagePathContainer"
					style="height: 5px; display: none;"></script>
				<weimhc:ueimage ueContainer="storagePathContainer" type="images"
					uploadPath="${uploadFolder}" input="storagePath" maxWidth="100"
					maxHeight="100" />
				<span class="help-inline"><font color="red">*</font> </span>
				<form:errors path="storagePath" cssStyle="color:red" />
			</div>
		</div>
		<div id="remoteImage" class="control-group showImage hide">
			<label class="control-label"><spring:message
					code="Image.storagePath" />：</label>
			<div class="controls">
				<div id="remotePreview">
					<img src="${fns:getDefaultTAccessThumbnailUrl(image.url)}">
				</div>
				<form:input path="url" maxlength="100"
					class="input-xxlarge required" />
				<span class="help-inline"><font color="red">*</font>
					将您在外网找到的图片链接地址粘贴到该输入框，将会看到预览效果。 </span>
				<form:errors path="storagePath" cssStyle="color:red" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Image.width"/>：</label>
			<div class="controls">
				<form:input path="width" htmlEscape="false" maxlength="20" min="0"
				          class="input-xlarge required integer"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="width" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Image.height"/>：</label>
			<div class="controls">
				<form:input path="height" htmlEscape="false" maxlength="20"  min="0"
				          class="input-xlarge required integer"/>
				<span class="help-inline"><font color="red">*</font> </span>
			    <form:errors path="height" cssStyle="color:red"/>
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
			<shiro:hasPermission name="image:image:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>