<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.inquirySheet" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
<script type="text/javascript">
	$(function(){
		//默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
		//设置选择样式
		var $inputForm = $("#inputForm");
		$inputForm.find(".trueFalse").each(function() {
			$(this).bootstrapSwitch();
		});
		$("#btnValid").click(function(){
           $("#status").val("valid");
           $inputForm.submit();
		});
		$("#btnInValid").click(function(){
           $("#status").val("inValid");
           $inputForm.submit();
		});
	});
	function addRow(list, idx, tpl, row){
		row = row || {sort:propertyValueRowIdx};
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
		$(list+idx).find("select").each(function(){
			$(this).val($(this).attr("data-value"));
		});
		$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
			var ss = $(this).attr("data-value").split(',');
			for (var i=0; i<ss.length; i++){
				if($(this).val() == ss[i]){
					$(this).attr("checked","checked");
				}
			}
		});
	}
	function delRow(obj, prefix){
		var id = $(prefix+"_id");
		var delFlag = $(prefix+"_delFlag");
		if (id.val() == ""){
			$(obj).parent().parent().remove();
		}else if(delFlag.val() == "0"){
			delFlag.val("1");
			$(obj).html("&divide;").attr("title", message("admin.common.cancelDelete"));
			$(obj).parents("tr").addClass("error");
		}else if(delFlag.val() == "1"){
			delFlag.val("0");
			$(obj).html("&times;").attr("title",message("admin.common.delete"));
			$(obj).parents("tr").removeClass("error");
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inquiry/inquirySheet/"><spring:message
					code="admin.inquirySheet" /> <spring:message
					code="admin.common.list" /></a></li>
		<li class="active"><a
			href="${ctx}/inquiry/inquirySheet/view?id=${inquirySheet.id}"> <shiro:hasPermission
					name="inquiry:inquirySheet:edit">
					<spring:message code="admin.inquirySheet" />
					<spring:message
						code="admin.common.${not empty inquirySheet.id?'edit':'add'}" />
				</shiro:hasPermission> <shiro:lacksPermission name="inquiry:inquirySheet:edit">
					<spring:message code="admin.inquirySheet" />
					<spring:message code="admin.common.view" />
				</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="inquirySheet"
		action="${ctx}/inquiry/inquirySheet/updateStatus" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="status" />
		<weimhc:message resultMessage="${resultMessage}" />
		<fieldset>
			<legend>询价产品</legend>
			<div class="btn-group">
			     <button id="btnValid" class="btn btn-success" type="button">有效</button>
			     <button id="btnInValid" class="btn btn-danger" type="button">无效</button>
			</div>
		</fieldset>	
		<fieldset>
			<legend>询价产品</legend>
			<table id="productTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>产品编号</th>
						<th>名称</th>
						<th>单价</th>
						<th>期望单价</th>
						<th>预购数量</th>
					</tr>
				</thead>
				<tbody id="inquiryProductList" class="openSort">
				</tbody>
			</table>
			<script type="text/template" id="inquiryProductTpl">//<!--
						<tr id="inquiryProductList{{idx}}">
							<td class="hide">
								<input id="inquiryProductList{{idx}}_id" name="inquiryProductList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="inquiryProductList{{idx}}_delFlag" name="inquiryProductList[{{idx}}].delFlag" type="hidden" value="0"/>
                                <input id="inquiryProductList{{idx}}_sort" name="inquiryProductList[{{idx}}].sort" type="hidden" value="{{row.sort}}" maxlength="11" class="input-small integer "/>
								<input id="inquiryProductList{{idx}}_productSn" name="inquiryProductList[{{idx}}].productSn" type="hidden" value="{{row.productSn}}"/>
								<input id="inquiryProductList{{idx}}_productName" name="inquiryProductList[{{idx}}].productName" type="hidden" value="{{row.productName}}"/>
								<input id="inquiryProductList{{idx}}_productId" name="inquiryProductList[{{idx}}].product.id" type="hidden" value="{{row.product.id}}"/>
								<input id="inquiryProductList{{idx}}_salesPrice" name="inquiryProductList[{{idx}}].salesPrice" type="hidden" value="{{row.salesPrice}}"/>
                            </td>
							<td>
                                {{row.productSn}}
							</td>
							<td>
								 {{row.productName}}
							</td>
							<td>
								 {{row.salesPrice}}
							</td>
							<td>
								{{row.expectedPrice}}
							</td>
							<td>
								{{row.preOrderQuantity}}
							</td>
						</tr>//-->
					</script>
				<script type="text/javascript">
					var inquiryProductRowIdx = 0, inquiryProductTpl = $("#inquiryProductTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
					$(function(){
						var data = ${fns:toJson(inquirySheet.inquiryProductList)};
						for (var i=0; i<data.length; i++){
							addRow('#inquiryProductList', inquiryProductRowIdx, inquiryProductTpl, data[i]);
							inquiryProductRowIdx = inquiryProductRowIdx + 1;
						}
					});
				</script>
		</fieldset>
		<fieldset  class="">
			<legend>询价单信息</legend>

			<div class="control-group span6 ">
				<label class="control-label"><spring:message
						code="InquirySheet.name" />：</label>
				<div class="controls">
				    ${inquirySheet.name}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.email" />：</label>
				<div class="controls">
					${inquirySheet.email}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.phone" />：</label>
				<div class="controls">
					${inquirySheet.phone}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.mobile" />：</label>
				<div class="controls">
					${inquirySheet.mobile}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.faxNumber" />：</label>
				<div class="controls">
					${inquirySheet.faxNumber}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.detailedAddress" />：</label>
				<div class="controls">
					${inquirySheet.detailedAddress}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.zipcode" />：</label>
				<div class="controls">
					${inquirySheet.zipcode}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.detail" />：</label>
				<div class="controls">
					${inquirySheet.detail}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.isAcceptEmail" />：</label>
				<div class="controls">
					${fns:getDictLabel(inquirySheet.isAcceptEmail, 'true_false','')}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="InquirySheet.isAcceptSms" />：</label>
				<div class="controls">
					${fns:getDictLabel(inquirySheet.isAcceptSms, 'true_false','')}
				</div>
			</div>
			<div class="control-group span6">
				<label class="control-label"><spring:message
						code="DataEntity.remarks" />：</label>
				<div class="controls">
					${inquirySheet.remarks}
				</div>
			</div>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="inquiry:inquirySheet:edit">
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