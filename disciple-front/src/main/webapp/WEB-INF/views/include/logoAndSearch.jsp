<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script type="text/javascript">
	$(function() {
		$("#inputForm").find("li").click(function() {
			var $li = $(this);
			$li.siblings().removeClass("on");
			$("#searchType").val($li.data("for"));
			$li.addClass("on");
		});
		if ('${searchDto.searchType }') {
			$("#inputForm").find("li[data-for='${searchDto.searchType }']")
					.addClass("on");
		} else {
			$("#inputForm").find("li[data-for='product']").addClass("on");
		}

	});
</script>
<div class="box">
	<div class="logo left">
		<a href="#"><img src="${ctxStatic}/modules/home/images/logo.png"
			width="205" height="106" /></a>
	</div>
	<div class="search left">
		<form id="inputForm" action="${ctx }/search/list" method="post">
		    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageable.pageNo}"/>
		    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageable.pageSize}"/>
		    <input id="area_id" name="area.id" type="hidden" value="${searchDto.area.id}"/>
			<input id="searchType" type="hidden" name="searchType"
				value="${searchDto.searchType }" /> <input id="productCategory_id"
				type="hidden" name="productCategory.id"
				value="${searchDto.productCategory.id }" /> <input id="beginPrice"
				type="hidden" name="beginPrice" value="${searchDto.beginPrice }" />
			<input id="endPrice" type="hidden" name="endPrice"
				value="${searchDto.endPrice }" /> <input id="sortBy" name="sortBy"
				type="hidden" value="${searchDto.sortBy }"> <input
				id="direction" type="hidden" name="direction"
				value="${searchDto.direction }">
			<ul class="tab_nav">
				<li data-for="product"><a href="javascript:void(0);"><spring:message
							code="shop.search.product" /></a></li>
				<li>|</li>
				<li data-for="store"><a href="javascript:void(0);"><spring:message
							code="shop.search.store" /></a></li>
			</ul>

			<div class="search_box">
				<input type="text" name="keywords" value="${searchDto.keywords }"
					placeholder="请输入产品名称" class="text" /> <input type="submit"
					value="搜索" class="submit right" />
			</div>
		</form>
	</div>
</div>

