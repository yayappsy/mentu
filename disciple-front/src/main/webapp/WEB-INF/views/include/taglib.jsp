<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys"%>
<%@ taglib prefix="weimhc" tagdir="/WEB-INF/tags/weimhc"%>
<%@ taglib prefix="rysh" tagdir="/WEB-INF/tags/rysh"%>
<%@ taglib prefix="rfns" uri="/WEB-INF/tlds/rfns.tld"%>
<c:set var="ctx"
	value="${pageContext.request.contextPath}${fns:getFrontPath()}" />
	
<c:set var="ctxApi"
	value="${pageContext.request.contextPath}${fns:getApiPath()}" />
	
<c:set var="webURL"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<c:set var="languageType"
	value="${not empty cookie.lang.value ? cookie.lang.value : 'zh_CN'}" />
<c:set var="imgURL"	value="${fns:getConfig('userfiles.baseURL')}" />	
<c:set var="imgThumbURL" value="${fns:getConfig('userfiles.baseURL')}/small" />		
