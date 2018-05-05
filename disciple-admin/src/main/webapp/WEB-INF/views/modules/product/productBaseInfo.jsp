<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
			<legend>
				<i class="icon-sort-up hide"></i> <i class="icon-sort-down"></i>
				<spring:message code="admin.product.base" />
			</legend>
			<div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.name" />：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="255"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
						<form:errors path="name" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.articleNumber" />：</label>
					<div class="controls">
						<form:input path="articleNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge " />
						<form:errors path="articleNumber" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.subtitle" />：</label>
					<div class="controls">
						<form:input path="subtitle" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
						<form:errors path="subtitle" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.productCategoryId" />：</label>
					<div class="controls">
						<sys:treeselect id="productCategory" name="productCategory.id"
							value="${product.productCategory.id}"
							labelName="productCategoryName"
							labelValue="${product.productCategoryName}"
							title="<spring:message code='admin.productCategory'/>"
							url="/product/productCategory/treeData" cssClass=""
							allowClear="true" />
						<span class="help-inline"><font color="red">*</font> </span> <span
							class="text-warning">修改产品分类会使之前的添加的扩展的属性消息，请谨慎使用 </span>
						<form:errors path="productCategory.id" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.propertyTemplate" />：</label>
					<div class="controls">
						<form:hidden path="propertyTemplate.id" />
						<form:hidden path="propertyTemplateName"
							value="${propertyTemplate.name}" />
						${product.propertyTemplateName}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.productBrand" />：</label>
					<div class="controls">
						<sys:treeselect id="productBrand" name="productBrand.id"
							value="${product.productBrand.id}" labelName="productBrandName"
							labelValue="${product.productBrandName}"
							title="<spring:message code='admin.productBrand'/>"
							url="/product/productBrand/treeData" cssClass=""
							allowClear="true" />
						<span class="help-inline"><font color="red">*</font> </span>
						<form:errors path="productBrand.id" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.costPrice" />：</label>
					<div class="controls">
						<form:input path="costPrice" htmlEscape="false"
							class="input-xlarge money" />
						<span class="text-warning"><spring:message
								code="admin.product.costTitle" /></span>
						<form:errors path="costPrice" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.marketPrice" />：</label>
					<div class="controls">
						<form:input path="marketPrice" htmlEscape="false"
							class="input-xlarge required money" />
						<span class="help-inline"><font color="red">*</font> </span>
						<form:errors path="marketPrice" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.salesPrice" />：</label>
					<div class="controls">
						<form:input path="salesPrice" htmlEscape="false"
							class="input-xlarge required money" />
						<span class="help-inline"><font color="red">*</font> </span>
						<form:errors path="salesPrice" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.stock" />：</label>
					<div class="controls">
						<form:input path="stock" htmlEscape="false" maxlength="11"
							class="input-xlarge  digits" />
						<form:errors path="stock" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.areaId" />：</label>
					<div class="controls">
						<sys:treeselect id="area" name="area.id"
							value="${product.area.id}" labelName="area.name"
							labelValue="${product.area.name}"
							title="<spring:message code='admin.commmon.area'/>"
							url="/sys/area/treeData" cssClass="required" allowClear="true"
							notAllowSelectParent="true" />
						<span class="help-inline"><font color="red">*</font> </span>
						<form:errors path="area.id" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.defaultImage" />：</label>
					<div class="controls">
						<form:hidden path="defaultImage" htmlEscape="false"
							id="defaultImage" maxlength="255" class="input-xlarge" />
						<weimhc:selectImage input="defaultImage" />
						<span class="help-inline"><font color="red">*</font></span>
						<form:errors path="defaultImage" cssStyle="color:red" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message
							code="Product.imageMore" />：</label>
					<div class="controls">
						<input type="hidden" id="tmpImgs" />
						<weimhc:selectImage input="tmpImgs" />
						<form:errors path="imageMore" cssStyle="color:red" />
					</div>
				</div>
			</div>
		</fieldset>