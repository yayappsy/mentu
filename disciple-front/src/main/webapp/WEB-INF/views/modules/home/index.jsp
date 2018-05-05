<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="首页" /></title>
<meta name="decorator" content="front_home" />

<link href="${ctxStatic }/modules/rysh/front/css/index.css?ver=2216"
	rel="stylesheet" type="text/css" />
<script
	src="${ctxStatic }/modules/rysh/front/js/jquery.SuperSlide.2.1.1.js"></script>
<!--图片放大-->
<script type="text/javascript"
	src="${ctxStatic }/modules/rysh/front/js/jquery.zoomImgRollover.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('.bd .banner1').click(function(){
			var isLogin=$("#isLogin").val();
			if(isLogin==null || isLogin==""){
				$('.user_box').show();
				$('.register_box').show();
				}else{
					$('.user_box').show();
					$('.login_box').show();
					}		
			});
		
		$('.m-news .item').mouseover(function() {
			$(this).addClass('current');
		}).mouseout(function() {
			$(this).removeClass('current');
		});


		$('.m-news .item .typename').mouseover(function() {
			$(this).addClass('current');
			$(this).children().addClass('current');
			
		}).mouseout(function() {
			$(this).removeClass('current');
			$(this).children().removeClass('current');
		});

		$('.m-product .item').mouseover(function() {
			$(this).children('.shade').show();
		}).mouseout(function() {
			$(this).children('.shade').hide();
		});

		/*图片放大*/
		$(".m-news .item .thumb img").zoomImgRollover();
		$(".m-product .item .thumb img").zoomImgRollover();
		$(".about_thumb").zoomImgRollover();

	});
</script>
</head>

<body>
	<!--banner-->
	<input type="hidden" id="isLogin" value="${currentMember.id}">

	<div class="m-banner">
		<div id="slideBox" class="slideBox">
			<div class="hd">
				<ul>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
			<div class="bd"> 
				<ul>
					<c:forEach items="${rfns:getAdByKeyword('indexCarousel')}" var="ad" varStatus="s">
					<c:choose>
					<c:when test="${s.first}">
						<li><p 
							style="background:url(${imgURL}${ad.path}) center no-repeat;"><a href="#" class="banner1">&nbsp;</a></p></li>
							</c:when>
							<c:otherwise>
							<li><a href="${ctx }${ad.url}" 
							style="background:url(${imgURL}${ad.path}) center no-repeat;"></a></li>
							</c:otherwise>
						</c:choose>	
					</c:forEach>
				</ul>
			</div>

			<!-- 下面是前/后按钮代码，如果不需要删除即可 -->
			  <a class="prev" href="javascript:void(0)"></a> <a class="next" href="javascript:void(0)"></a>  
		</div>
		<script type="text/javascript">
			jQuery(".slideBox").slide({
				mainCell : ".bd ul",
				autoPlay : true
			});
		</script>
	</div>

	<!--banner end-->

	<!--关于我们-->
	<section class="m-block m-about">
		<div class="inner clearfix">
			<div class="m-title">
				关于我们
				<div class="line"></div>
				<div class="en">ABOUT US</div>
			</div>
			<div class="main">

				<div class="detail clearfix">
					<div class="content left">
						<p>
							公司成立于2016年我公司为各行各业提供最优质的企业综合管理服务。公司经营团队经过多年的企业服务经营，研发一整套卓越的企业综合管理服务体系；现拥有一支专业的管理和领导团队，以卓越的服务品质、专业安全的企业服务体系，为各行各业提供更优质的企业管理服务。
						</p>
						<p>公司畅想打造新的商业文明，以诚信开放具有责任感的企业取得成功。将中小企业抱成团，形成经济共同体，组成大的团结进行发展。公司不谋求暴利，以建设精神文明为主，突破发展禁锢，形成新的市场，新的发展体系。邀请更多的人融入其中，乐在其中，收在其中。</p>
					</div>
					<div class="pic right">
						<img class="about_thumb"
							src="${ctxStatic }/modules/rysh/front/images/about.gif" />
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--关于我们 end-->

	<!--资讯中心-->
	<%@include file="/WEB-INF/views/modules/home/newsCenter.jsp"%>
	
	<!--解决方案 end-->


</body>
</html>
