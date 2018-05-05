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
<div class="pagination" style="width: 100%;">
	<ul class="pull-left">

		<c:choose>
			<c:when test="${!pageable.isFirstPage() }">
				<li><a href="javascript:"
					onclick="${pageable.funcName}(1, ${pageable.pageSize},'${pageable.funcParam}');">首页</a></li>
				<li><a href="javascript:"
					onclick="${pageable.funcName}(${pageable.prev }, ${pageable.pageSize},'${pageable.funcParam}');">上一页</a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><a href="#">首页</a></li>
				<li class="disabled"><a href="#">上一页</a></li>
			</c:otherwise>
		</c:choose>
		<c:if test="${begin > pageable.first }">
			<c:forEach var="i" begin="${pageable.first }"
				end="${pageable.displayNumberSlider }">
				<li><a href="javascript:"
					onclick="${pageable.funcName}(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
			</c:forEach>
			<c:if
				test="${(pageable.first + pageable.displayNumberSlider ) < begin}">
				<li class="disabled"><a href="javascript:">...</a></li>
			</c:if>
		</c:if>

		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == current}">
					<li class="active"><a href="">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:"
						onclick="${pageable.funcName}(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${end < pageable.last }">
			<c:if test="${end < pageable.last}">
				<li class="disabled"><a href="javascript:">...</a></li>
			</c:if>
			<c:forEach var="i"
				begin="${pageable.last-pageable.displayNumberSlider+1 }"
				end="${pageable.last }">
				<li><a href="javascript:"
					onclick="${pageable.funcName}(${i}, ${pageable.pageSize},'${pageable.funcParam}');">${i}</a></li>
			</c:forEach>

		</c:if>
		<c:choose>
			<c:when test="${!pageable.isLastPage() }">
				<li><a href="javascript:"
					onclick="${pageable.funcName}(${pageable.next }, ${pageable.pageSize},'${pageable.funcParam}');">下一页</a></li>
				<li><a href="javascript:"
					onclick="${pageable.funcName}(${pageable.last }, ${pageable.pageSize},'${pageable.funcParam}');">末页</a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><a href="#">下一页</a></li>
				<li class="disabled"><a href="#">末页</a></li>
			</c:otherwise>
		</c:choose>
		
		<li class="disabled controls last">
			<span>
			当前 <input title="可以输入页码" id="pageNoInput"
				type="text" value="${pageable.pageNo}" onclick="this.select();" />
				/ 每页 <input  title="可以输入分页条数"  id="pageSizeInput" type="text" 
				value="${pageable.pageSize}" onclick="this.select();"/>
				 共${pageable.count }条
			</span>
		<button id="btnPage" class="btn btn-small btn-info" title="点击该按钮，则按照之前的条件进行分页查询，页码和每页展示多少条可以在前边的输入框设置">分页</button>
		</li>
	</ul>
	<script type="text/javascript">
		$(function(){
		    $("#btnPage").on("click",function(){
		        ${pageable.funcName}($("#pageNoInput").val(), $("#pageSizeInput").val(),'${pageable.funcParam}');
		    });
		})
	</script>
</div>

