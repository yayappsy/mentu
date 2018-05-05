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
		$("#inputForm").find(".trueFalse").bootstrapSwitch();
		$('#memebrSwitch').on('switchChange.bootstrapSwitch', function (event, state) {
		    if(state){
		    	$("#memberSelect").show();
			}else{
				$("#memberSelect").hide();
			}
		});
        if('true' === '${activityParticipant.isMember}'){
        	$("#memberSelect").show();
        }
        
		$("#btnAdd").on("click", function() {
			layer.open({
				type : 2,
				btn: ['保存', '取消'],
				yes:function(index,layero){
					var $layerIframe = $(layero).find("#layui-layer-iframe"+index);
					var $productContentTable = $layerIframe.contents().find("#contentTable");
					var selectProducts = [];
					$productContentTable.find(":checked[name='id']").each(function(){
						selectProducts.push($(this).data());
						});
					addProduct(selectProducts);
					layer.close(index);
				},
				area : [ '800px', '400px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/product/product?searchType=selectLink&isMultiple=true'
			});
		});
		
		var inquiryProductTpl = $("#inquiryProductTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		function addProduct(selectProducts){
			console.log(selectProducts);
			for (var i=0; i<selectProducts.length; i++){
				selectProducts[i].product = {};
				selectProducts[i].product.id = selectProducts[i].productId;
				addRow('#inquiryProductList',inquiryProductRowIdx,inquiryProductTpl,selectProducts[i])
				inquiryProductRowIdx = inquiryProductRowIdx + 1;
			}
			
	    }

		$("#btnSelectLink").on("click", function() {
			layer.open({
				type : 2,
				btn: ['保存', '取消'],
				yes:function(index,layero){
					var $layerIframe = $(layero).find("#layui-layer-iframe"+index);
					var selectData = [];
					$layerIframe.contents().find("input[name='id']:checked").each(function(){
						selectData.push($(this).data());
					});
					if(selectData.length > 0){
						$("#name").val(selectData[0].name);
						$("#mobile").val(selectData[0].mobile);
						$("#email").val(selectData[0].email);
						$("#phone").val(selectData[0].phone);
						$("#memberId").val(selectData[0].id);
						$("#memberNickName").val(selectData[0].nickname);
						$("#showNickname").html(selectData[0].nickname);
					}
					layer.close(index);
				},
				area : [ '800px', '360px' ],
				shadeClose : true, //点击遮罩关闭
				content : '${ctx}/member/member/?searchType=selectLink'
			});
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
			href="${ctx}/inquiry/inquirySheet/form?id=${inquirySheet.id}"> <shiro:hasPermission
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
		action="${ctx}/inquiry/inquirySheet/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="status" />
		<weimhc:message resultMessage="${resultMessage}" />
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
						<th><button type="button" id="btnAdd" class="btn btn-primary">添加</button></th>
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
								<input id="inquiryProductList{{idx}}_expectedPrice" name="inquiryProductList[{{idx}}].expectedPrice" type="text" value="{{row.expectedPrice}}" maxlength="255" class="input-small money required"/>
							</td>
							<td>
								<input id="inquiryProductList{{idx}}_preOrderQuantity" name="inquiryProductList[{{idx}}].preOrderQuantity" type="text" value="{{row.preOrderQuantity}}" maxlength="255" class="input-small integer required"/>
							</td>
							<shiro:hasPermission name="property:property:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#inquiryProductList{{idx}}')" title="<spring:message code='admin.common.delete'/>">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
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
		<fieldset>
			<legend>询价单信息</legend>

			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.isMember" />：</label>
				<div class="controls" id="memebrSwitch">
					<form:checkbox path="isMember"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class=" trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isMember" cssStyle="color:red" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.name" />：</label>
				<div class="controls">
					<form:input path="name" htmlEscape="false" maxlength="100"
						class="input-xlarge required" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="name" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group hide" id="memberSelect">
				<label class="control-label"><spring:message
						code="InquirySheet.memberId" />：</label>
				<div class="controls">
					<form:hidden path="member.id" id="memberId" />
					<form:hidden path="memberNickname" />
					<label id="showNickname"></label>
					<button id="btnSelectLink" class="btn btn-primary" type="button">
						选择会员</button>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.email" />：</label>
				<div class="controls">
					<form:input path="email" htmlEscape="false" maxlength="20"
						class="input-xlarge required email" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="email" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.phone" />：</label>
				<div class="controls">
					<form:input path="phone" htmlEscape="false" maxlength="20"
						class="input-xlarge phone" />
					<form:errors path="phone" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.mobile" />：</label>
				<div class="controls">
					<form:input path="mobile" htmlEscape="false" maxlength="20"
						class="input-xlarge required mobile" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="mobile" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.faxNumber" />：</label>
				<div class="controls">
					<form:input path="faxNumber" htmlEscape="false" maxlength="20"
						class="input-xlarge " />
					<form:errors path="faxNumber" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.detailedAddress" />：</label>
				<div class="controls">
					<form:input path="detailedAddress" htmlEscape="false"
						maxlength="100" class="input-xlarge " />
					<form:errors path="detailedAddress" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.zipcode" />：</label>
				<div class="controls">
					<form:input path="zipcode" htmlEscape="false" maxlength="6"
						class="input-xlarge " />
					<form:errors path="zipcode" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.detail" />：</label>
				<div class="controls">
					<form:input path="detail" htmlEscape="false" maxlength="255"
						class="input-xlarge " />
					<form:errors path="detail" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.isAcceptEmail" />：</label>
				<div class="controls">
					<form:checkbox path="isAcceptEmail"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class=" trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isAcceptEmail" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="InquirySheet.isAcceptSms" />：</label>
				<div class="controls">
					<form:checkbox path="isAcceptSms"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class=" trueFalse" />

					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isAcceptSms" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="DataEntity.remarks" />：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4"
						maxlength="255" class="input-xxlarge " />
					<form:errors path="remarks" cssStyle="color:red" />
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