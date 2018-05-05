<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset id="customerProp">
	<legend>
	    <i class="icon-sort-up"></i> <i class="icon-sort-down hide"></i>
		<spring:message code="admin.product.property" />
	</legend>
	<div class="hide">
		<c:forEach items="${propertyTemplate.propertyList}" var="property"
			varStatus="propStatus">
			<div class="control-group">
				<label for="prop${propStatus.index}_id" class="control-label">${property.name }：</label>
				<input type="hidden"
					name="productProperties[${propStatus.index}].propertyTemplate.id"
					value="${property.propertyTemplate.id }"> <input
					type="hidden"
					name="productProperties[${propStatus.index}].propertyName"
					value="${property.name }"> <input type="hidden"
					name="productProperties[${propStatus.index}].property.id"
					value="${property.id }"> <input
					id="prop${propStatus.index}_id_names" class="prop_n" type="hidden"
					name="productProperties[${propStatus.index}].propertyValueNames"
					value=""> <input class="prop_i" type="hidden"
					id="prop${propStatus.index}_id_ids"
					name="productProperties[${propStatus.index}].propertyValueIds"
					value="">
				<div class="controls">
					<c:choose>
						<c:when test="${'text' eq property.showType}">
							<input type="text" data-for="prop${propStatus.index}_id"
								class="input-large ${property.isRequired?'required':'' }"
								name="prop${propStatus.index}_id"
								id="prop${propStatus.index}_id"
								value="${product.getPropertyValue(property.id) }"
								placeholder="${property.name }">

						</c:when>
						<c:when test="${'checkbox' eq property.showType}">
							<c:forEach items="${property.propertyValueList }" var="opt"
								varStatus="otpSt">
								<input type="checkbox" data-for="prop${propStatus.index}_id"
									${product.isPropertyValueChecked(property.id,opt.id)?'checked':'' }
									id="prop${propStatus.index}_id${otpSt.index}"
									name="prop${propStatus.index}_id"
									id="property_c_${otpSt.index }"
									class="${property.isRequired?'required':'' }"
									value="${opt.id }" data-name="${opt.name }" />
								<label for="prop${propStatus.index}_id${otpSt.index}">${opt.name }</label>
							</c:forEach>

						</c:when>
						<c:when test="${'select' eq property.showType}">
							<select name="prop${propStatus.index}_id"
								class="ui-select ${property.isRequired?'required':'' }"
								id="prop${propStatus.index}_id">
								<c:forEach items="${property.propertyValueList }" var="opt">
									<option value="${opt.id }" data-name="${opt.name }"
										data-for="prop${propStatus.index}_id"
										${product.isAttributeChecked(property.id,opt.id)?'selected':'' }>${opt.name }</option>
								</c:forEach>
							</select>
						</c:when>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</div>
</fieldset>
<%--扩展属性--%>
<%-- <div id="customerAttr" class="form_item" style="height: auto;">
	<label for="宝贝属性" class="fieldname need" style="vertical-align: top;">宝贝属性</label>
	<div class="div_box item_box">
		<div class="form_explain" style="">填错宝贝属性，可能会引起宝贝下架，影响您的正常销售。请认真准确填写</div>

		<c:forEach items="${propertyList }" var="property"
			varStatus="propStatus">
			<c:choose>
				<c:when test="${'text' eq property.showType}">
					<div class="form_item">
						<label for="prop${propStatus.index}_id"
							class="fieldname ${property.isRequired?'need':'' }">${property.name }</label>
						<input type="text"
							class="ui-input middle  ${property.isRequired?'required':'' }"
							name="prop${propStatus.index}_id" id="prop${propStatus.index}_id"
							value="${product.getAttrValue(property.id) }"
							placeholder="${property.name }"> <input type="hidden"
							name="productProperties[${propStatus.index}].property.id"
							value="${property.id }"> <input class="prop_v"
							type="hidden"
							name="productProperties[${propStatus.index}].propertyValueNames" value="">
						<input class="prop_i" type="hidden"
							name="productProperties[${propStatus.index}].propertyValueIds"
							value=""> <input type="hidden"
							name="productProperties[${propStatus.index}].delFlag" value="0">
					</div>
				</c:when>
				<c:when test="${'dateselect' eq property.showType}">
					<div class="form_item">
						<label for="prop${propStatus.index}_id"
							class="fieldname  ${property.isRequired?'need':'' }">${property.name }</label>
						<input name="prop${propStatus.index}_id" type="text"
							readonly="readonly"
							class="input-medium Wdate  ${property.isRequired?'required':'' }"
							value="${product.getAttrValue(property.id) }"
							id="prop${propStatus.index}_id"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<input type="hidden"
							name="productProperties[${propStatus.index}].property.id"
							value="${property.id }"> <input class="prop_v"
							type="hidden"
							name="productProperties[${propStatus.index}].propertyValueNames" value="">
						<input class="prop_i" type="hidden"
							name="productProperties[${propStatus.index}].propertyValueIds"
							value=""> <input type="hidden"
							name="productProperties[${propStatus.index}].delFlag" value="0">
					</div>
				</c:when>
				<c:when test="${'select' eq property.showType}">
					<div class="form_item">
						<label for="prop${propStatus.index}_id"
							class="fieldname  ${property.isRequired?'need':'' }">${property.name }</label>
						<select name="prop${propStatus.index}_id"
							class="ui-select ${property.isRequired?'required':'' }"
							id="prop${propStatus.index}_id">
							<c:forEach items="${property.propertyValueList }" var="opt">
								<option value="${opt.id }" data-name="${opt.name }"
									${product.isAttributeChecked(property.id,opt.id)?'selected':'' }>${opt.name }</option>
							</c:forEach>
						</select> <input type="hidden"
							name="productProperties[${propStatus.index}].property.id"
							value="${property.id }"> <input class="prop_v"
							type="hidden"
							name="productProperties[${propStatus.index}].propertyValueNames" value="">
						<input class="prop_i" type="hidden"
							name="productProperties[${propStatus.index}].propertyValueIds"
							value=""> <input type="hidden"
							name="productProperties[${propStatus.index}].delFlag" value="0">
					</div>
				</c:when>
				<c:when test="${'checkbox' eq property.showType}">
					<div class="form_item">
						<label for="prop${propStatus.index}_id"
							class="fieldname  ${property.isRequired?'need':'' }">${property.name }</label>
						<div class="div_box" style="width: 540px;">
							<c:forEach items="${property.propertyValueList }" var="opt"
								varStatus="otpSt">
								<input type="checkbox"
									${product.isAttributeChecked(property.id,opt.id)?'checked':'' }
									id="prop${propStatus.index}_id${otpSt.index}"
									name="prop${propStatus.index}_id"
									id="property_c_${otpSt.index }"
									class="${property.isRequired?'required':'' }"
									value="${opt.id }" data-name="${opt.name }" />
								<label for="prop${propStatus.index}_id${otpSt.index}">${opt.name }</label>
							</c:forEach>
						</div>
						<input type="hidden"
							name="productProperties[${propStatus.index}].property.id"
							value="${property.id }"> <input class="prop_v"
							type="hidden"
							name="productProperties[${propStatus.index}].propertyValueNames" value="">
						<input class="prop_i" type="hidden"
							name="productProperties[${propStatus.index}].propertyValueIds"
							value=""> <input type="hidden"
							name="productProperties[${propStatus.index}].delFlag" value="0">
					</div>
				</c:when>
				<c:when test="${'radiobox' eq property.showType}">
					<div class="form_item">
						<label for="prop${propStatus.index}_id"
							class="fieldname  ${property.isRequired?'need':'' }">${property.name }</label>
						<div class="div_box" style="width: 540px;">
							<c:forEach items="${property.propertyValueList }" var="opt"
								varStatus="otpSt">
								<input type="radio"
									id="prop${propStatus.index}_id${otpSt.index}"
									name="prop${propStatus.index}_id"
									${product.isAttributeChecked(property.id,opt.id)?'checked':'' }
									class="${property.isRequired?'required':'' }"
									value="${opt.id }" data-name="${opt.name }" />
								<label for="prop${propStatus.index}_id${otpSt.index}">${opt.name }</label>
							</c:forEach>
						</div>
						<input type="hidden"
							name="productProperties[${propStatus.index}].property.id"
							value="${property.id }"> <input class="prop_v"
							type="hidden"
							name="productProperties[${propStatus.index}].propertyValueNames" value="">
						<input class="prop_i" type="hidden"
							name="productProperties[${propStatus.index}].propertyValueIds"
							value=""> <input type="hidden"
							name="productProperties[${propStatus.index}].delFlag" value="0">
					</div>
				</c:when>

				<c:otherwise></c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div> --%>
<%--宝贝属性 end--%>