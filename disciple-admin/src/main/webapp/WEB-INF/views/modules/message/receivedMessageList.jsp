<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.receivedMessage" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
					
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/message/receivedMessage/"><spring:message code="admin.receivedMessage.list"/></a></li>
		<%-- <shiro:hasPermission name="message:receivedMessage:edit"><li><a href="${ctx}/message/receivedMessage/form">
		   <spring:message code="admin.receivedMessage.add"/></a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="receivedMessage" action="${ctx}/message/receivedMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<ul class="ul-form">
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th> <spring:message code="ReceivedMessage.recipientId"/></th>  
			    <th> <spring:message code="ReceivedMessage.messageId"/></th> 
			    <th> <spring:message code="InternalMessage.content"/></th> 
			    <th> <spring:message code="ReceivedMessage.isRead"/></th>    
			    
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="message:receivedMessage:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="receivedMessage">
			<tr>
			   <td><a href="${ctx}/message/receivedMessage/form?id=${receivedMessage.id}">
					${receivedMessage.recipientId}
				</a></td>
				<td>
					${receivedMessage.message.title}
				</td>
				<td>
					${receivedMessage.message.content}
				</td>
				<td>
					${fns:getDictLabel(receivedMessage.isRead,'true_false','')}
				</td>
				<td>
					<fmt:formatDate value="${receivedMessage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${receivedMessage.remarks}
				</td>
				<shiro:hasPermission name="message:receivedMessage:edit"><td>
    				<a href="${ctx}/message/receivedMessage/form?id=${receivedMessage.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/message/receivedMessage/delete?id=${receivedMessage.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>