<%@tag pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="pageable"
	type="com.thinkgem.javamg.common.persistence.Pageable" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer"
	required="true"%>
<%
	int current = pageable.getPageNo();
	int displayNumberLength = pageable.getDisplayNumberLength();
	int first = pageable.getFirst();
	int last = pageable.getLast();
	int begin = current - (displayNumberLength / 2);
	if (begin < first) {
		begin = first;
	}

	int end = begin + displayNumberLength - 1;

	if (end >= last) {
		end = last;
		begin = end - displayNumberLength + 1;
		if (begin < first) {
			begin = first;
		}
	}

	request.setAttribute("pageable", pageable);
	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
%>
<div id="m-page-1" class="m-page right" >
	<ul >

		<c:choose>
			<c:when test="${!pageable.isFirstPage() }">
<!-- 				<li><a href="javascript:" class="prev" -->
<%-- 					onclick="page(1, ${pageable.pageSize},'${pageable.funcParam}');">首页</a></li> --%>
				<li><a class="prev" href="javascript:"
					onclick="page(${pageable.prev }, ${pageable.pageSize},'${pageable.funcParam}');"> &lt; </a></li>
			</c:when>
			<c:otherwise>
<!-- 				<li class="disabled"><a class="prev" href="#">首页</a></li> -->
				<li class="disabled"><a class="prev" href="#"> &lt; </a></li>
			</c:otherwise>
		</c:choose>
		<c:if test="${begin > pageable.first }">
			<c:forEach var="i" begin="${pageable.first }"
				end="${pageable.displayNumberSlider }">
				<li><a class="num" href="javascript:"
					onclick="page(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
			</c:forEach>
			<c:if
				test="${(pageable.first + pageable.displayNumberSlider ) < begin}">
				<li class="disabled"><a class="num" href="javascript:">...</a></li>
			</c:if>
		</c:if>

		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == current}">
					<li class="active"><a class="current" href="javascript:">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="num" href="javascript:"
						onclick="page(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${end < pageable.last }">
			<c:if test="${end < pageable.last}">
				<li class="disabled"><a class="num" href="javascript:">...</a></li>
			</c:if>
			<c:forEach var="i"
				begin="${pageable.last-pageable.displayNumberSlider+1 }"
				end="${pageable.last }">
				<li><a class="num" href="javascript:"
					onclick="page(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
			</c:forEach>

		</c:if>
		<c:choose>
			<c:when test="${!pageable.isLastPage() }">
				<li><a class="prev" href="javascript:"
					onclick="page(${pageable.next }, ${pageable.pageSize},'${pageable.funcParam}');"> > </a></li>
<!-- 				<li><a class="prev" href="javascript:" -->
<%-- 					onclick="page(${pageable.last }, ${pageable.pageSize},'${pageable.funcParam}');">末页</a></li> --%>
			</c:when>
			<c:otherwise>
				<li class="disabled"><a class="prev" href="#"> > </a></li>
<!-- 				<li class="disabled"><a class="prev" href="#">末页</a></li> -->
			</c:otherwise>
		</c:choose>

		<%-- <li class="disabled controls"><a href="javascript:">当前 <input
				type="text" value="${pageable.pageNo}" onclick="this.select();" />
				/ 每页 <input type="text" value="${pageable.pageSize}"
				onclick="this.select();" /> 共${pageable.count }条
		</a></li> --%>
	</ul>

</div>
<script type="text/javascript">
  $(function(){
	  var $page = $("#m-page-1");
	  var pageWidth = $page.width();
	  var liWidth = $page.find("li").width()
	  liSize = $page.find("li").length;
	  var marginRight = (pageWidth - liWidth * liSize) /2;
	  $page.find("ul").css({"margin-left": marginRight+"px"})
  });
</script>
