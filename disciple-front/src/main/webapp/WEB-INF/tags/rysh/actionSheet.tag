<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="defaultName" type="java.lang.String" required="true"
	description="默认名称"%>
<%@ attribute name="list" type="java.util.List" required="true"
	description="展示列表"%>
<div id="actionSheet_wrap">
	<div class="weui_mask_transition" id="mask"></div>
	<div class="weui_actionsheet" id="weui_actionsheet">
		<div class="weui_actionsheet_menu">
			<div class="weui_actionsheet_cell" data-id=""
				data-name="${defaultName }">${defaultName }</div>
			<c:forEach items="${list}" var="item">
				<div class="weui_actionsheet_cell" data-id="${item.id }"
					data-name="${item.name }">${item.name }</div>
			</c:forEach>
		</div>
		<div class="weui_actionsheet_action">
			<div class="weui_actionsheet_cell" id="actionsheet_cancel">取消</div>
		</div>
	</div>
</div>