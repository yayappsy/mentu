<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.member" /><spring:message
		code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(function () {
    });
    // 全选
    var $selectAll = $("#selectAll");
    $selectAll.click(function () {
        var $this = $(this);
        var $enabledIds = $listTable.find("input[name='id']:enabled");
        if ($this.prop("checked")) {
            $enabledIds.prop("checked", true);
            if ($enabledIds.filter(":checked").size() > 0) {
                $deleteButton.removeClass("disabled");
                $contentRow.addClass("selected");
            } else {
                $deleteButton.addClass("disabled");
            }
        } else {
            $enabledIds.prop("checked", false);
            $deleteButton.addClass("disabled");
            $contentRow.removeClass("selected");
        }
    });
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/member/member/?searchType=selectLink"><spring:message
					code="admin.member.list" /></a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="member"
		action="${ctx}/member/member/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType}" />
		<ul class="ul-form">
			<li><label> <spring:message code="Member.mobile" />：
			</label> <form:input path="mobile" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="Member.nickname" />：
			</label> <form:input path="nickname" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="Member.realName" />：
			</label> <form:input path="name" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" />
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><c:choose>
						<c:when test="${isMultiple}">
							<input type="checkbox" id="selectAll" />
							<spring:message code="admin.common.choose" />
						</c:when>
						<c:otherwise>
							<spring:message code="admin.common.choose" />
						</c:otherwise>
					</c:choose></th>
				<th><spring:message code="Member.mobile" /></th>
				<th><spring:message code="Member.realName" /></th>
				<th><spring:message code="Member.sn" /></th>
				<th><spring:message code="Member.nickname" /></th>
				<th><spring:message code="Member.imUsername" /></th>
				<th><spring:message code="Member.sex" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="member">
				<tr>
					<td><c:choose>
							<c:when test="${isMultiple}">
								<input type="checkbox" name="id" value="${member.id}"
									data-type="member" data-id="${member.id}"
									data-name="${member.name}" data-mobile="${member.mobile}"
									data-email="${member.email}" data-phone="${member.phone}"
									data-sn="${member.sn}" data-nickname="${member.nickname}" 
									data-im-username="${member.imUsername }"
									 />
							</c:when>
							<c:otherwise>
								<input type="radio" name="id" value="${member.id}"
									data-type="member" data-id="${member.id}"
									data-name="${member.name}" data-mobile="${member.mobile}"
									data-email="${member.email}" data-phone="${member.phone}"
									data-sn="${member.sn}" data-nickname="${member.nickname}"
									data-im-username="${member.imUsername }"
									 />
							</c:otherwise>
						</c:choose></td>
					<td><a href="${ctx}/member/member/form?id=${member.id}">${member.mobile}</a></td>
					<td>${member.name}</td>
					<td>${member.sn}</td>
					<td>${member.nickname}</td>
					<td>${member.imUsername}</td>
					<td>${fns:getDictLabel(member.gender, 'sex', '')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>