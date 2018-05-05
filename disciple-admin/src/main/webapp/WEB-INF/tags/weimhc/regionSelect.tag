<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="标题"%>
<%@ attribute name="title" type="java.lang.String" required="false" description="输入框值（Name）"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="隐藏域css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="输入框css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<%@ attribute name="jsEventName" type="java.lang.String" required="false"	description="改变之后触发的js事件名称，默认为:{id}-region-change"%>
<c:if test="${fns:isBlank(jsEventName) }">
	<c:set var="jsEventName" value="${id }-region-change"/>
</c:if>	
<c:if test="${fns:isBlank(title) }">
	<c:set var="title" value="地址选择"/>
</c:if>	
<sys:treeselect id="${id}" 
					name="${name }" value="${name }" 
					labelName="${labelName }" labelValue="${labelValue }" checked="${checked }" extId="${extId }"
					title="${title }" url="/base/region/treeData" jsEventName="${id }-region-change"
					hideBtn="${hideBtn }" smallBtn="${smallBtn }" disabled="${disabled }"
					notAllowSelectRoot="${ notAllowSelectRoot}"  notAllowSelectParent="${ notAllowSelectParent}" 
					allowClear="${allowClear }"  cssClass="${cssClass }" cssStyle="${cssStyle }"/>
<script type="text/javascript">
;$(function(){
    $("#${id}Id").on("${jsEventName}",function(event,data){
        if(data && data.mergerName){
            $("#${id}Name").val(data.mergerName);
        }
    });
});
</script>					