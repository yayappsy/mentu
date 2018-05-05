<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html style="overflow-x: auto; overflow-y: auto;">
<head>
<title><sitemesh:title /></title>

<%@include file="/WEB-INF/views/include/front/head.jsp"%>
<sitemesh:head />
<script type="text/javascript">
	$(function() {
		$("#" + "${menu}").addClass("current");
	});
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/layouts/front/top.jsp"%>
	<sitemesh:body />
	<%@include file="/WEB-INF/views/layouts/front/home/footer.jsp"%>
</body>
</html>