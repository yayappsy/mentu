<%@page import="com.thinkgem.javamg.common.utils.UserAgentUtils"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%
	if (UserAgentUtils.isWeixinBrowser(request)) {
%>
<%@include file="/WEB-INF/views/error/wx404.jsp"%>
<%
	} else {
%>
<%@include file="/WEB-INF/views/error/pc404.jsp"%>
<%
	}
%>