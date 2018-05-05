<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.remind" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body> 
<div class="container-fluid"> 
        <div class="row-fluid"> 
			<%@include file="/WEB-INF/views/modules/remind/remindIndexLeft.jsp"%>
		</div>
		<div id="remindOpenClose" class="close">&nbsp;</div>
		<div id="remindRight" class="left">
			<%@include file="/WEB-INF/views/modules/remind/remindIndexEdit.jsp"%>
		</div>
	</div>
	
</body>
</html>