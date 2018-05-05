<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.property" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/formHead.jsp"%>
	<script src="${ctxStatic}/jquery-ui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
           //默认validate校验在form.js中，如果需要可以自定义校验，请去掉form.js引用
           var $inputForm = $("#subInputForm");
           //设置选择样式
           $inputForm.find(".trueFalse").each(function(){
				$(this).bootstrapSwitch();
			}); 
           $inputForm.on("click",":radio[name='showType']",showValues);
          
           function showValues(){
        	   $this = $(this);
               if($this.hasClass('multiple')){
                   $("#propertyValueGroup").show();
               }else{
            	   $("#propertyValueGroup").hide();
               }
           }
           $inputForm.validate({
        	   submitHandler : function(form) {
            	   loading();
            	   $.ajax({
						url : $inputForm.attr("action"),
						type : "POST",
						data : $inputForm.serialize(),
						cache : false,
						success : function(resultMessage) {
							console.log(resultMessage);
							closeTip();
							closePropertyLayer();
						},
						error : function(resultMessage) {
							closeTip();
						}
					});
                  }
               });
           $inputForm.on("click","btnCancel",closePropertyLayer);
           function closePropertyLayer(){
        	   parent.layer.close(parent.layer.getFrameIndex(window.name));
        	   ("#btnRefresh",window.parent.document).click(); 
           }
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
           $inputForm.find(":radio[name='showType']:checked").click();
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
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title",message("admin.common.delete"));
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
	<style type="text/css">
	     #contentTable{
	         width:300px;
	     }
	</style>
</head>
<body>
	<form:form id="subInputForm" modelAttribute="property" action="${ctx}/property/property/ajaxSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="propertyTemplate.id"/>
		<weimhc:message resultMessage="${resultMessage}"/>		
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.name"/>：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="name" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.showType"/>：</label>
			<div class="controls">
				<form:radiobutton path="showType" value="text" label="文本框" checked="chekced" class="single"/>
				<form:radiobutton path="showType" value="radio" label="单选按钮" class="multiple"/>
				<form:radiobutton path="showType" value="checkbox" label="复选框" class="multiple"/>
				<form:radiobutton path="showType" value="select" label="下拉列表" class="multiple"/>
			    <form:errors path="showType" cssStyle="color:red"/>
			</div>
		</div>
		<div id="propertyValueGroup" class="control-group hide">
			<label class="control-label"><spring:message code="Property.propertyValues"/>：</label>
			<div class="controls">
			  <table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="hide"></th>
							<th><spring:message code="PropertyValue.name"/></th>
							<shiro:hasPermission name="property:property:edit"><th width="10">
							   <a href="javascript:" onclick="addRow('#propertyValueList', propertyValueRowIdx, propertyValueTpl);propertyValueRowIdx = propertyValueRowIdx + 1;" class="btn btn-primary">
							      <spring:message code="admin.common.add"/>
							   </a>
							</th></shiro:hasPermission>
						</tr>
					</thead>
					<tbody id="propertyValueList">
					</tbody>
				</table>
				<script type="text/template" id="propertyValueTpl">//<!--
						<tr id="propertyValueList{{idx}}">
							<td class="hide">
								<input id="propertyValueList{{idx}}_id" name="propertyValueList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="propertyValueList{{idx}}_delFlag" name="propertyValueList[{{idx}}].delFlag" type="hidden" value="0"/>
                                <input id="propertyValueList{{idx}}_sort" name="propertyValueList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="11" class="input-small integer "/>
                            </td>
							<td>
								<input id="propertyValueList{{idx}}_name" name="propertyValueList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="255" class="input-small required"/>
							</td>
							<shiro:hasPermission name="property:property:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#propertyValueList{{idx}}')" title="<spring:message code='admin.common.delete'/>">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
				<script type="text/javascript">
					var propertyValueRowIdx = 0, propertyValueTpl = $("#propertyValueTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
					$(function(){
						var data = ${fns:toJson(property.propertyValueList)};
						for (var i=0; i<data.length; i++){
							addRow('#propertyValueList', propertyValueRowIdx, propertyValueTpl, data[i]);
							propertyValueRowIdx = propertyValueRowIdx + 1;
						}
					});
				</script>	
				<form:hidden path="propertyValues" htmlEscape="false" maxlength="255" 
				          class="input-xlarge "/>
			    <form:errors path="propertyValues" cssStyle="color:red"/>
			    <!-- <span class="help-inline">用英文逗号分隔</span> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.isSearch"/>：</label>
			<div class="controls">
			    <form:checkbox path="isSearch"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class="trueFalse" />
			    <form:errors path="isSearch" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="Property.isRequired"/>：</label>
			<div class="controls">
			   <form:checkbox path="isRequired"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class="trueFalse" />
			    <form:errors path="isRequired" cssStyle="color:red"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><spring:message code="DataEntity.isShow"/>：</label>
			<div class="controls">
			  <form:checkbox path="isShow"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true')}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false')}"
						class="trueFalse" />
			    <form:errors path="isShow" cssStyle="color:red"/>
			</div>
		</div>
		<div class="form-actions">
            <shiro:hasPermission name="property:property:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.save'/>"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="<spring:message code='admin.common.back'/>"/>
		</div>
	</form:form>
</body>
</html>