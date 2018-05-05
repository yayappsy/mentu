<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="imagePath" type="java.lang.String" required="true"
	description="图片访问路径"%>
<%@ attribute name="imageName" type="java.lang.String" required="false"
	description="图片名称"%>
<div class="imageView">
	<c:forTokens items="${imagePath }" delims="|" begin="0" end="1"
		var="imagePathV">
		<img alt="${imageName}" width="100" height="100" title="点击预览大图"
			data-image-url="${fns:getAccessUrl(imagePathV)}"
			onclick="showBigImage(this.dataset.imageUrl)"
			src="${fns:getDefaultTAccessThumbnailUrl(imagePathV)}" />
	</c:forTokens>
</div>
