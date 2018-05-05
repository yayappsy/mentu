<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="display">
	<sitemesh:getProperty property="meta.display" />
</c:set>
<!--footer-->
<div class="m-footer">
	<div class="box clearfix">
		<div class="service left">
			<p class="p1">0312-3186667</p>
			<p class="p2">
				周一至周日 9：30—18:30<br />（仅收市话费）
			</p>
			<p class="p3">在线客服</p>
		</div>
		<div class="ewm right clearfix">
			<img class="left"
				src="${ctxStatic}/modules/rysh/front/images/ewm.jpg" />
			<p class="left">
				心智辅政<span>微信公众号</span>
			</p>
		</div>
	</div>
</div>
<!--footer end-->
<!--bottom-->
<div class="m-bottom">
	<div class="inner">
		<div class="copyright left">Copyright @ 2011 荣耀世华 版权所有 Powered
			By ieSDM</div>
		<ul class="bnav right">
			<li><a href="#">联系我们</a></li>
			<li>|</li>
			<li><a href="#">客服服务</a></li>
			<li>|</li>
			<li><a href="#">意见反馈</a></li>
			<li>|</li>
			<li><a href="#">免责声明</a></li>
			<li>|</li>
			<li><a href="#">招贤纳士</a></li>
			<li>|</li>
			<li><a href="#">官方微博</a></li>
		</ul>
	</div>
</div>
<!--bottom end-->