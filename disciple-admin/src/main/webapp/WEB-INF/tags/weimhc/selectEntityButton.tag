<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--通过列表选择实体 --%>
<%@ attribute name="id" type="java.lang.String" required="true"
	description="编号前缀"%>
<%@ attribute name="name" type="java.lang.String" required="true"
	description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true"
	description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true"
	description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true"
	description="输入框值（Name）"%>
<%@ attribute name="url" type="java.lang.String" required="true"
	description="需要选择的实体路径"%>
<%@ attribute name="buttonName" type="java.lang.String" required="false"
	description="按钮名称"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false"
	description="CSS样式"%>
<%@ attribute name="cssNameClass" type="java.lang.String"
	required="false" description="CSS样式"%>
<%@ attribute name="readOnly" type="java.lang.Boolean" required="false"
	description="是否只读"%>
<%@ attribute name="jsEventName" type="java.lang.String" required="false"
	description="改变之后触发的js事件名称"%>
<div class="form-input-append">
	<input id="${id}Id" name="${name}" class="${cssClass}" type="hidden"
		value="${value}" /> <input id="${id}Name" name="${labelName}"
		class="${cssNameClass}" type="hidden" value="${labelValue}" />
		<label id="${id}NameShow">${labelValue}</label>
	<c:if test="${!readOnly }">
		<button id="btn${id}Select" class="btn btn-primary " type="button">
			选择${buttonName }</button>
	</c:if>
</div>
<c:if test="${!readOnly }">
<script type="text/javascript">
	$(function() {
		$("#btn${id}Select").on(
				"click",
				function() {
				    if(typeof ${id}BeforeSelect == 'function'){
						if(!${id}BeforeSelect()){
			               return true;
						}
					}
					var url = "${url}?searchType=selectLink";
					//在获取数据路径时，传递额外参数
					if(typeof ${id}UrlAdditionalParameters == 'function'){
						url += ${id}UrlAdditionalParameters();
						//console.log(url);
					}
					console.log(url);
					// 
					layer.open({
						type : 2,
						btn : [ '保存', '取消' ],
						yes : function(index, layero) {
							var $layerIframe = $(layero).find(
									"#layui-layer-iframe" + index);
							var selectData = [];
							$layerIframe.contents().find(
									"input[name='id']:checked").each(
									function() {
										selectData.push($(this).data());
									});
							if (selectData.length > 0) {
								$("#${id}Id").val(selectData[0].id);
								$("#${id}Name").val(selectData[0].name);
								$("#${id}NameShow").html(selectData[0].name);
								//<c:if test="${fns:isNotBlank(jsEventName)}">
								$("#${id}Id,#${id}Name").trigger("${jsEventName }",selectData[0]);
								//</c:if>
							}
							layer.close(index);
						},
						area : [ '800px', '360px' ],
						shadeClose : true, //点击遮罩关闭
						content : url
					});
				});
	});
</script>
</c:if>