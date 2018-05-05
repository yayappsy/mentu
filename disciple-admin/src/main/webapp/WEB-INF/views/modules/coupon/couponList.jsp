<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="admin.coupon" /><spring:message code="admin.common.manager" /></title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){

			function saveMemberCoupon(url,couponId,memberIds){
				// console.log("进来了"+url+couponId+memberIds);		 	       
				$.ajax({
 	       			url:url,
 	       			data:{
 	       				"memberIds":memberIds,
 	       				"couponId":couponId
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
			
			function openLayerByMember(url,couponId){
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
	    	                saveMemberCoupon("${ctx}/member/memberCoupon/saveCoupon",couponId,memberIds);			    
	 	                    },
	 	                    cancel: function(){
	 	                        //右上角关闭回调
	 	                    }
	 			    });
				     
			}

			
			 $('.findMemberList').click(function(){
				 openLayerByMember("${ctx}/member/member/list",$(this).data("couponId"));
		   	 });	
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/coupon/coupon/"><spring:message code="admin.coupon"/><spring:message code="admin.common.list"/></a></li>
		<shiro:hasPermission name="coupon:coupon:edit"><li><a href="${ctx}/coupon/coupon/form">
		   <spring:message code="admin.coupon"/><spring:message code="admin.common.add"/></a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="coupon" action="${ctx}/coupon/coupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		<input id="memberId" name="memberId" type="hidden"/>
		<ul class="ul-form">
			<li>
				<label> <spring:message code="Coupon.name"/>：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th> <spring:message code="Coupon.name"/></th>
				<th> <spring:message code="Coupon.isEnabled"/></th>
				<th> <spring:message code="Coupon.priceLimit"/></th>
				<th> <spring:message code="Coupon.exchangeLimit"/></th>			
				<th> <spring:message code="Coupon.quantity"/></th>
				<th> <spring:message code="DataEntity.createDate"/></th>        
				<th> <spring:message code="DataEntity.remarks"/></th>        
				<shiro:hasPermission name="coupon:coupon:edit"><th><spring:message code="admin.common.handle"/></th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coupon">
			<tr>
				<td><a href="${ctx}/coupon/coupon/form?id=${coupon.id}">
					${coupon.name}
				</a></td>
				<td>
					${fns:getDictLabel(coupon.isEnabled,'true_false','')}
				</td>
				<td>
					${coupon.priceLimit}
				</td>
				<td>
					${coupon.exchangeLimit}
				</td>
				
				<td>
					${coupon.quantity}
				</td>
				<td>
					<fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${coupon.remarks}
				</td>
				<shiro:hasPermission name="coupon:coupon:edit"><td>
				  <a href="javascript:;" data-coupon-id="${coupon.id}" class="findMemberList"><spring:message code="发送优惠券"/></a>
    				<a href="${ctx}/coupon/coupon/form?id=${coupon.id}"><spring:message code="admin.common.modify"/></a>
					<a href="${ctx}/coupon/coupon/delete?id=${coupon.id}" 
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