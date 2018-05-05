<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title/></title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<sitemesh:head/>
</head>
<body>
	<sitemesh:body/>
	
	<%@include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>