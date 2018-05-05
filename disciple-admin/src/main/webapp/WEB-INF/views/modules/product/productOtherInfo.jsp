<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<fieldset>
			<legend>
			    <i class="icon-sort-up"></i> <i class="icon-sort-down hide"></i>
				<spring:message code="admin.product.otherInfo" />
			</legend>
			<div class="hide">
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.isGift" />：</label>
				<div class="controls">
					<form:checkbox path="isGift"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isGift" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.isList" />：</label>
				<div class="controls">
					<form:checkbox path="isList"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isList" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.isMarketable" />：</label>
				<div class="controls">
					<form:checkbox path="isMarketable"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isMarketable" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><spring:message
						code="Product.isTop" />：</label>
				<div class="controls">
					<form:checkbox path="isTop"
						data-on-text="${fns:getMessage(languageType, 'admin.common.true',null)}"
						data-off-text="${fns:getMessage(languageType, 'admin.common.false',null)}"
						class="trueFalse" />
					<span class="help-inline"><font color="red">*</font> </span>
					<form:errors path="isTop" cssStyle="color:red" />
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
			</div>
		</fieldset>