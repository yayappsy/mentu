<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="accordion" id="accordion-member">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion-member" href="#accordion-element-member">首页</a>
		</div>
		<div id="accordion-element-member" class="accordion-body collapse in">
			<div class="accordion-inner">
				<ul class="nav nav-list">
					<c:forEach items="${list}" var="remind" varStatus="remindIndex">
						<li class="${remindIndex.first?'active':'' }"><a target="remindFrame"
							href="${ctx }/remind/remind/form?id=${remind.id}">${remind.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion-admin" href="#accordion-element-admin">功能列表</a>
		</div>
		<div id="accordion-element-admin" class="accordion-body collapse">
			<div class="accordion-inner">
				<ul class="nav nav-list">
					<li><a href="#">资料</a></li>
					<li><a href="#">设置</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>