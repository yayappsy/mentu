<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="readOnly" type="java.lang.Boolean" required="false"
	description="是否只读"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false"
	description="CSS样式"%>	
<%@ attribute name="cssNameClass" type="java.lang.String"
	required="false" description="CSS样式"%>	
<%@ attribute name="jsEventName" type="java.lang.String" required="false"
	description="改变之后触发的js事件名称，默认为:member-change"%>
<c:if test="${fns:isBlank(jsEventName) }">
	<c:set var="jsEventName" value="member-change"/>
</c:if>	
<weimhc:selectEntityButton  id="${id }" name="${name}"  value="${value}" cssClass="required ${cssClass }" cssNameClass="${cssNameClass }" 
		readOnly="${readOnly }"
					 labelName="${labelName}" labelValue="${labelValue}" url="${ctx}/member/member/" jsEventName="${jsEventName }"/>
