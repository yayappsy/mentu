<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="content" type="java.lang.String" required="true"
	description="消息内容"%>
<div class="m-nodata">
	<div class="pic">
		<img src="${ctxStatic }/modules/blh/weixin/images/nodata.png" width="100" height="100" />
	</div>
	<p>${content }</p>
</div>