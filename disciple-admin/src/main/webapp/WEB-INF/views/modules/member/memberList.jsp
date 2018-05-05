<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title><spring:message code="admin.member" /><spring:message code="admin.common.manager" /></title>
<meta name="decorator" content="default" />
<script type="text/javascript">
    $(document).ready(function () {

        $("#btnExport").click(function () {
            top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function (v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/member/member/export");
                    $("#searchForm").submit();
                }
            }, {
                buttonsFocus : 1
            });
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });
        $("#btnImport").click(function () {
            $.jBox($("#importBox").html(), {
                title : "导入数据",
                buttons : {
                    "关闭" : true
                },
                bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
            });
        });

        $("#contentTable").find("a.modifyPwd").on("click", function () {
            var $this = $(this);
            modifyPwd($this.data("id"));
        });

        function modifyPwd(userId) {
            layer.open({
                type : 2,
                shadeClose : true,
                title : '重置密码',
                shade : false,
                maxmin : true, //开启最大化最小化按钮
                area : [ '800px', '400px' ],
                content : '${ctx}/user/userInfo/modifyPwdForm?id=' + userId,
                btn : [ '确定', '关闭' ],
                yes : function (index, layero) {
                    var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
                    var $subForm = $layerIframe.contents().find("#modifyPasswordForm");
                    if ($subForm.valid()) {
                        loading();
                        $.ajax({
                            url : $subForm.attr("action"),
                            type : "POST",
                            dataType : "json",
                            data : $subForm.serialize(),
                            cache : false,
                            success : function (result) {
                                closeTip();
                                layer.close(index);
                            },
                            error : function (result) {
                                closeTip();
                            }
                        });
                    }
                },
                cancel : function () {
                    //右上角关闭回调
                }
            });
        }
        function openLayer(url, memberId) {
            layer.open({
                type : 2,
                shadeClose : true,
                shade : false,
                maxmin : true, //开启最大化最小化按钮
                area : [ '800px', '600px' ],
                content : url + "?member.id=" + memberId + "&searchType=particularMember",
                btn : [ '确定', '关闭' ],
                yes : function (index) {
                    //最后关闭弹出层
                    layer.close(index);
                },
                cancel : function () {
                    //右上角关闭回调
                }
            });
        }

    });
</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/member/member/import"
			method="post" enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br /> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " /> <a
				href="${ctx}/member/member/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/member/member/"><spring:message
					code="admin.member.list" /></a></li>
		<c:if test="${searchType ne 'selectLink' }">
			<shiro:hasPermission name="member:member:edit">
				<li><a href="${ctx}/member/member/form"> <spring:message
							code="admin.member.add" /></a></li>
			</shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="member"
		action="${ctx}/member/member/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden"
			value="${page.pageable.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageable.pageSize}" />
		<input id="searchType" name="searchType" type="hidden"
			value="${searchType }" />
		<ul class="ul-form">
			<li><label> <spring:message code="Member.mobile" />：
			</label> <form:input path="mobile" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li><label> <spring:message code="Member.nickname" />：
			</label> <form:input path="nickname" htmlEscape="false" maxlength="255"
					class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="<spring:message code='admin.common.search'/>" />
				<input id="btnClear" class="btn btn-primary" type="button"
				value="<spring:message code='admin.common.clear'/>" /> <!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/> -->
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${searchType eq 'selectLink' }">
					<th><spring:message code="admin.common.choose" /></th>
				</c:if>
				<th><spring:message code="Member.name" /></th>
				<th><spring:message code="Member.sn" /></th>
				<th><spring:message code="Member.nickname" /></th>
				<th><spring:message code="Member.mobile" /></th>
				<th><spring:message code="Member.qq" /></th>
				<th><spring:message code="Member.sex" /></th>
				<th><spring:message code="UserLoginAuthorization.ifEnabled" /></th>
				<th><spring:message code="DataEntity.createDate" /></th>
				<shiro:hasPermission name="member:member:edit">
					<th><spring:message code="admin.common.handle" /></th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="member">
				<tr>
					<c:if test="${searchType eq 'selectLink' }">
						<td><c:choose>
								<c:when test="${isMultiple}">
									<input type="checkbox" name="id" value="${member.id}"
										data-type="member" data-id="${member.id}"
										data-name="${member.name}" data-mobile="${member.mobile}"
										data-email="${member.email}" data-phone="${member.phone}"
										data-sn="${member.sn}" data-nickname="${member.nickname}" />
								</c:when>
								<c:otherwise>
									<input type="radio" name="id" value="${member.id}"
										data-type="member" data-id="${member.id}"
										data-name="${member.name}" data-mobile="${member.mobile}"
										data-email="${member.email}" data-phone="${member.phone}"
										data-sn="${member.sn}" data-nickname="${member.nickname}" />
								</c:otherwise>
							</c:choose></td>
					</c:if>
					<td>${member.name }</td>
					<td><a href="${ctx}/member/member/form?id=${member.id}">
							${member.sn} </a></td>
					<td>${member.nickname }</td>
					<td>${member.mobile }</td>
					<td>${member.qq }</td>
					<td>${fns:getDictLabel(member.gender,'sex','')}</td>
					<td>${fns:getDictLabel(member.userLoginAuthorization.ifEnabled,'true_false','')}</td>
					<td><fmt:formatDate value="${member.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="member:member:edit">
						<td> <a class="btn btn-primary"
							href="${ctx}/member/member/form?id=${member.id}"><spring:message
									code="admin.common.modify" /></a> <a	 class="btn btn-danger"
							href="${ctx}/member/member/delete?id=${member.id}"
							onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
								<spring:message code="admin.common.delete" />
						</a>
						<a class="btn btn-danger modifyPwd" data-id="${member.id}"
							href="javascript:;"><spring:message
									code="admin.common.resetPwd" /></a>
						<a class="btn btn-danger" data-id="${member.id}"
							href="${ctx}/user/userLoginAuthorization?userInfo.id=${member.id}&businessSystem=front"><spring:message
									code="admin.common.resetPwd" /></a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sys:pagination paginationSize="1" pageable="${page.pageable }" />
</body>
</html>