<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!--footer-->
<div class="m-footer">
	<div class="fnav clearfix">
		<ul>
			<li><a href="${fns:getSettingValue('siteUrl','site')}">${fns:getSettingValue('siteName','site')}</a></li>
			<li>|</li>
			<li><a href="#"><spring:message code="shop.footer.aboutus"/></a></li>
			<li>|</li>
			<li><a href="#"><spring:message code="shop.footer.weibo"/></a></li>
		</ul>
	</div>
	<p> ${fns:getSettingValue('copyright','site')}
        ${fns:getSettingValue('address','site')} 
	    ${fns:getSettingValue('phone','site')}
		${fns:getSettingValue('certtext','site')}</p>
</div>
<!--footer end-->