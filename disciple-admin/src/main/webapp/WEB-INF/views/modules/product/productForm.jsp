<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.product" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/ueditor.jsp"%>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script src="${ctxStatic}/jquery-ui/1.11.4/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('input[name="isInstalment"]').on('switchChange.bootstrapSwitch',
						function(event, state) {
							//console.log(state); // true | false
							if (state) {
								$("#propertyValueGroup").show();
							} else {
								$("#propertyValueGroup").hide();
							}
						});
				//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
				//设置选择样式
				var $inputForm = $("#inputForm");//输入表单
				$inputForm.find(".trueFalse").each(function() {
					$(this).bootstrapSwitch();
				});
				$inputForm.validate({
					submitHandler : function(form) {
						$("#customerProp").find(":text").each(
								function() {
									var $this = $(this);
									$("#" + $this.data("for") + "_names").val(
											$this.val());
								}).end().find("select option:selected").each(
								function() {
									var $this = $(this);
									$("#" + $this.data("for") + "_names").val(
											$this.data("name"));
									$("#" + $this.data("for") + "_ids").val(
											$this.val());
								}).end().find(":checkbox,:radio").each(
								function() {
									var $this = $(this);
									var selectVals = $(
											"#" + $this.data("for") + "_names")
											.val(), selectIds = $(
											"#" + $this.data("for") + "_ids")
											.val();
									if ($(this).prop("checked")) {
										selectVals += $this.data("name") + ",";
										selectIds += $this.val() + ",";
									}
									$("#" + $this.data("for") + "_names").val(
											selectVals);
									$("#" + $this.data("for") + "_ids").val(
											selectIds);
								});
						form.submit();

					}
				});
				$("legend").click(function() {
					var $this = $(this);
					$this.find("i").toggleClass(function() {
						if ($(this).hasClass('icon-sort-down')) {
							return 'icon-sort-up';
						} else {
							return 'icon-sort-down';
						}

					});
					$this.next().toggle("fade",20);
				});
				$("#propertyValueList").sortable({ 
					   cursor: "move", 
					   items :"tr", //只是td可以拖动 
					   opacity: 0.6, //拖动时，透明度为0.6 
					   revert: true, //释放时，增加动画 
					   update : function(event, ui){ //更新排序之后 
					      $.each($(this).sortable("toArray"),function(index,item){
				            $("#"+item+"_sort").val(index);
				   	  });
					   }
				});
				function addRow(list, idx, tpl, row){
					row = row || {sort:propertyValueRowIdx};
					$(list).append(Mustache.render(tpl, {
						idx: idx, delBtn: true, row: row
					}));
				}
				function delRow(obj, prefix){
					var id = $(prefix+"_id");
					var delFlag = $(prefix+"_delFlag");
					if (id.val() == ""){
						$(obj).parent().parent().remove();
					}else if(delFlag.val() == "0"){
						delFlag.val("1");
						$(obj).html("&divide;").attr("title", message("admin.common.cancelDelete"));
						$(obj).parent().parent().addClass("error");
					}else if(delFlag.val() == "1"){
						delFlag.val("0");
						$(obj).html("&times;").attr("title",message("admin.common.delete"));
						$(obj).parent().parent().removeClass("error");
					}
				}
			});
	function productBrandBeforeSelect() {
		if ($("#productCategoryId").val() == '') {
			alertx("必须先选择商品分类");
			return false;
		}
		return true;
	}
	function productBrandUrlAdditionalParameters() {
		return "&productCategoryId=" + $("#productCategoryId").val();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/product/product/"><spring:message
					code="admin.product.list" /></a></li>
		<li class="active"><a
			href="${ctx}/product/product/form?id=${product.id}"> <shiro:hasPermission
					name="product:product:edit">
					<spring:message
						code="admin.product.${not empty product.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="product:product:edit">
					<spring:message code="admin.product.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="product"
		action="${ctx}/product/product/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sn" />
		<weimhc:message resultMessage="${resultMessage}" />
				
		<%-- 基本属性 --%>
		<%@include file="/WEB-INF/views/modules/product/productBaseInfo.jsp"%>
		
		<%-- 展示属性 --%>
		<%@include file="/WEB-INF/views/modules/product/productShowInfo.jsp"%>

		<%-- 扩展属性 --%>
		<%@include file="/WEB-INF/views/modules/product/productPropertyInfo.jsp"%>

		<%-- 其他属性 --%>
		<%@include file="/WEB-INF/views/modules/product/productOtherInfo.jsp"%>
		
		<div class="form-actions">
			<shiro:hasPermission name="product:product:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="<spring:message code='admin.common.save'/>" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button"
				value="<spring:message code='admin.common.back'/>"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>