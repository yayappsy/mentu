<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" 
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!--top-->
<c:set value="${rfns:getCurrentMember()}" var="currentMember"></c:set>
<div class="m-top">
	<div class="inner" style="position: relative; z-index: 3000;"> 
		<ul class="login right">
			<c:choose>
				<c:when test="${currentMember.id ne null }">
					<li><a href="#" class="login_bnt"><c:choose>
								<c:when test="${currentMember.nickname != null }">
		                       ${fns:abbr(currentMember.nickname,12)}
		                  </c:when>
								<c:otherwise>
		                       ${fns:abbr(currentMember.username,12)}
		                  </c:otherwise>
							</c:choose></a></li>
					<li>|</li>
					<li><a href="${ctx}/logout" class="register_bnt">退出</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="login_bnt">登录</a></li>
					<li>|</li> 
					<li><a href="#" class="register_bnt">请注册</a></li>
				</c:otherwise>

			</c:choose>
		</ul>
		<ul class="user right">
			<li><a href="#">会员中心</a></li>
			<li>|</li>
			<li><a href="#">APP下载</a></li>
		</ul>
		<ul class="tel right">
			<li><a><i class="iconfont" style="display: block;">&#xe604;</i></a></li>
			<li><a>服务热线：0312-3186667</a></li>
		</ul>
		<!--登录注册-->
		<div class="user_box clearfix">
			<!--register-->

			<%@include file="/WEB-INF/views/layouts/front/register.jsp"%>
			<!--register end-->
			<!--login-->
			<%@include file="/WEB-INF/views/layouts/front/login.jsp"%>
			<!--login end-->
		</div>
		<!--登录注册 end-->

	</div>
</div>
<!--top end-->
<!--head-->
<div class="m-head">
	<div class="inner">
		<div class="logo left">
			<a href="#"><img
				src="${ctxStatic }/modules/rysh/front/images/logo.png" /></a>
		</div>
		<ul class="nav right">
			<li><a href="${ctx }/" id="index">首页</a></li>
			<li><a href="${ctx}/aboutUs/" id="aboutUs">关于东诺 </a></li>
			<li><a href="${ctx}/services/" id="services">部门服务 </a></li>
			<li><a href="${ctx}/news/" id="news">公司动态</a></li>
			<li><a href="${ctx}/news/social?categoryId=8" id="social">社会责任 </a></li>	
			<li><a href="${ctx}/contactUs" id="contactUs">联系我们 </a></li>		
		</ul>
	</div>
</div>
<!--head end-->
<!--banner-->


