<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.internalMessage" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){

			function saveMemberMessage(url,messageId,memberIds){
				// console.log("进来了"+url+messageId+memberIds);		 	       
				$.ajax({
	       			url:url,
	       			data:{
	       				"memberIds":memberIds,
	       				"messageId":messageId
	       			},
	       			async:false,
	       			dateType:"json",
	       			error:function(e){console.log(e)},
	       			success:function(data){	 
	       				 var source=data.result;	
	       				swal("Good!", "发送成功", "success");
	       			    console.log("发送成功");		 	       			               				 	
	       			}
	       			});
			}
			function openLayerByMember(url,messageId){
				layer.open({
	 			      type: 2,  	
	 			      shadeClose: true,
	 			      shade: false,
	 			      maxmin: true, //开启最大化最小化按钮
	 			      area: ['800px', '600px'],
	 			      content: url+"?searchType=selectLink&&isMultiple=true",
	 			    	  btn: ['确定','关闭'],
	 			    	 yes: function(index, layero){ 
		 	                	var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
		 	                    var selectData = [];
		 	                    $layerIframe.contents().find("input[name='id']:checked").each(function() {
		 	                        selectData.push($(this).data());
		 	                    });
		 	                   if (selectData.length > 0 && selectData.length<2) {             
		 	                      $("#memberId").val(selectData[0].id);
		 	                  }else if(selectData.length>1){
		 	                  	var name="";
		 	                  	for (var i = 0; i < selectData.length; i++) {
		 	                  		if(i==selectData.length-1){
		 	                  			name+=selectData[i].id;
		 	                  		}else{
		 	                  			name+=selectData[i].id+",";
		 	                  		}
		 	                  		
		 	  					}
		 	                   $("#memberId").val(name);
		 	                  }	 	            	              
		 	                       //最后关闭弹出层
		                        layer.close(index);
		                        var memberIds=$("#memberId").val();
		                        saveMemberMessage("${ctx}/message/receivedMessage/saveMessage",messageId,memberIds);			    
		 	                    },
	 	                    cancel: function(){
	 	                        //右上角关闭回调
	 	                    }
	 			    });
			}
			$('.findMemberInfo').click(function(){
				 openLayerByMember("${ctx}/member/member/list",$(this).data("messageId"));
		   	 });	
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/message/internalMessage/"><spring:message code="admin.internalMessage.list"/></a></li>
		<shiro:hasPermission name="message:internalMessage:edit"><li><a href="${ctx}/message/internalMessage/form">
		   <spring:message code="admin.internalMessage.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="internalMessage" action="${ctx}/message/internalMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="memberId" name="memberId" type="hidden"/>
		<ul class="ul-form">
		<li>
				<label> <spring:message code="InternalMessage.messageType"/>：</label>
				<form:select path="messageType">
				<form:option value=""><spring:message code="admin.common.defaultSelect"/></form:option>
				<c:forEach items="${messageTypes}" var="messageType">
				<form:option value="${messageType}"><spring:message code="MessageType.${messageType}"/></form:option>
				</c:forEach>
				</form:select>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="<spring:message code='admin.common.search'/>"/>
			    <input id="btnClear" class="btn btn-primary" type="button" value="<spring:message code='admin.common.clear'/>"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<weimhc:message resultMessage="${resultMessage}"/>		
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th> <spring:message code="InternalMessage.title"/></th> 
			<th> <spring:message code="InternalMessage.messageType"/></th>
			<th> <spring:message code="InternalMessage.isMass"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="message:internalMessage:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="internalMessage">
			<tr>
			<td><a href="${ctx}/message/internalMessage/form?id=${internalMessage.id}">
					${internalMessage.title}
				</a></td>
				<td>
					<spring:message code="MessageType.${internalMessage.messageType}"/>
				</td>
				<td>
				     ${fns:getDictLabel(internalMessage.isMass,'true_false','')}
				</td>
				<td>
					<fmt:formatDate value="${internalMessage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${internalMessage.remarks}
				</td>
				<shiro:hasPermission name="message:internalMessage:edit"><td>
				<a href="javascript:;" data-message-id="${internalMessage.id}" class="findMemberInfo"><spring:message
									code="选择会员发送" /></a>
    				<a href="${ctx}/message/internalMessage/form?id=${internalMessage.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/message/internalMessage/delete?id=${internalMessage.id}" 
					   onclick="return confirmx('<spring:message code="admin.dialog.deleteConfirm"/>', this.href)">
					   <spring:message code="admin.common.delete"/>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
    <sys:pagination paginationSize="1" pageable="${page.pageable }"/>
</body>
</html>