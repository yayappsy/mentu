<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="关于我们" /></title>
<meta name="decorator" content="front_home" />

<link href="${ctxStatic }/modules/rysh/front/css/about.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic }/modules/rysh/front/timeline/css/timeline.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxStatic }/modules/rysh/front/timeline/js/modernizr.js"></script>
	<script type="text/javascript">
$(function() {	
			$("#" + "${articleId}").addClass("current");
});
</script>
<script type="text/javascript">
	$(document).ready(function() {
        $('.l_box .type li').mouseover(function(){
			$('.l_box .type li a').removeClass('current');
			$(this).children('a').addClass('current');
			
			var index = $(this).index();
			$('.news_box').hide();
			$(".l_box .news_box:eq("+index+")").show();
			});
    });
</script>
</head>

<body>
<div class="m-banner">

</div>
<!--当前位置-->
<div class="m-position">
	<ul class="clearfix">
    	<li>当前位置</li><li>&gt;</li><li>${article.category.name}</li><li>&gt;</li><li>${article.title} </li>
    </ul>
</div>
<!--当前位置 end-->
<!--main-->
<div class="m-main clearfix"> 
<%@include file="/WEB-INF/views/modules/about/left.jsp"%>

<c:choose>
<c:when test="${article.id=='78b716c6944644ba92ebff4f826c7c27'}">
 <!--right-->
  <div class="r_box right">
   	<!--企业文化-->
    <div class="s_box2">
    	<p class="b_name">企业文化</p>
        <div class="q_box1">
        	<p class="p1">使命</p>
			<p class="p2">以量子医疗技术为手段，结合中国传统健康养生术，造福全人类千秋百岁的幸福安康；</p>
       		
            <p class="p1" style="margin-top:30px;">责任</p>
            <p class="p2">我们不是完全以盈利为目的的机构，我们肩负着全人类健康长寿超百岁的伟大使命。</p>
        </div>
        
        <div class="q_box2">
        	<div class="text left"  style="width:508px;">
            	<p class="p1">团队一致</p>
                <p class="p2">内涵：共同的目标，共同的价值观，共同创造、共享发展成果；</p>
                <p class="p3">1.要为共同的发展目标与方向而团结努力奋斗。<br/>
                2.理解、执行,遵循及传播共同的价值观，并作为共同的行为准则。<br/>
                3.共同利益，共同协作，共同努力；共同创造，共同分享。<br/>
                4.敬业，不抱怨，不要消极影响同事及团队；<br/>
                5.对工作、事业充满信心与激情，相互渲染，激发团队<br/>
                6.以大局为重，无私奉献。</p>
            </div>
            <div class="img right"><img src="${ctxStatic }/modules/rysh/front/images/q1.png"/></div>
        </div>
        
        <div class="q_box2">
        	<div class="text left">
            	<p class="p1">有效沟通</p>
                <p class="p2">内涵：以有效为导向进行沟通与接纳；</p>
                <p class="p3">1.坦诚沟通，真诚建议，乐意接受。<br/>
2.以有效、解决事情为目的，主动沟通。<br/>
3.以团队一致为核心，以维护共同价值观与目标为基础。<br/>
4.善于运用有效的沟通方式。<br/>
5.捍卫公司核心利，言行一致不动摇。</p>
            </div>
            <div class="img right"><img src="${ctxStatic }/modules/rysh/front/images/q2.png"/></div>
        </div>
        
        <div class="q_box2">
        	<div class="text left">
            	<p class="p1">认真执行</p>
                <p class="p2">内涵：对已定决策，坚决执行，严格遵循制度和标准；</p>
                <p class="p3">1.清楚自己的本职工作与职责。<br/>
2.按照既定的决策、流程、标准等去执行。<br/>
3.执行前坦诚沟通，可保留个人意见，但必须严肃、严谨地积极执行团队已定决策。<br/>
4.认真执行过程中，若出现与团队决策预想不一致的结果时候，即当抛开个人情绪思想，积极沟通，寻求最佳方案，及时调整。<br/>
5.为追求更好的结果，可在执行过程中，在保证不影响执行与价值利益前提下，可探索其它方式。</p>
            </div>
            <div class="img right"><img src="${ctxStatic }/modules/rysh/front/images/q3.png"/></div>
        </div>
        
        <div class="q_box2">
        	<div class="text left">
            	<p class="p1">拥抱变革</p>
                <p class="p2">内涵：以开放、接纳、信任的姿态面对公司的变革或者调整；</p>
                <p class="p3">1.改变是为了发展，发展离不开变革。<br/>
2.变化、变革，常会触动部分群体的习惯、利益，要以团队利益为重，大家好才是真的好，要热情拥抱与执行，应主动调整自己，适应改变，多沟通，多倾听！<br/>
3.要信任我们的团队，改变是为了更好的发展，要心态开阔、诚心接纳和信任团队。</p>
            </div>
            <div class="img right"><img src="${ctxStatic }/modules/rysh/front/images/q4.png"/></div>
        </div>
       
    </div>
    <!--企业文化 end-->
  </div>

</c:when>
<c:when test="${article.id=='92e784d2467c464e86526c400589020c'}">
<div class="r_box right"> 
    <!--发展历程-->
    <div class="s_box3">
      <p class="b_name">发展历程</p>
      <p style="font-size:35px;color:#036eb8; text-align:center;">2016</p>
      <!--time line-->
      <div class="time_line">
        <section id="cd-timeline" class="cd-container">
          
          <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">12月  荣耀世华科技股份官方网站正式上线，届时广大用户可以直接登录我们的官方网站对我们的企业及产品和服务进一步的了解。</p>
               <span class="cd-date">12</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
           <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">11月  荣耀世华健康体验店正式运营，可以为用户提供线下检测、健康咨询、健康理疗等服务 。我们专业的健康管理师为您进行专业的健康检测与健康咨询服务。</p>
               <span class="cd-date">11</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
          <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">10月  荣耀世华“耳麦式移动采集器”研发成功并生产出来，它更加轻巧，更容易操作和使用；适用于家庭和个人，用户接上安装了检测软件的笔记本、Pad等移动终端即可使用；该设备同时具有细胞能量修复功能。</p>
               <span class="cd-date">10</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
          <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">09月  荣耀世华与多家健康用品公司达成战略合作，将进一步加快我们建设“健康商城”的步伐。</p>
               <span class="cd-date">9</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
          <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">08月  荣耀世华公司乔迁中国北京市最繁华的商圈——国贸CBD，它处于国贸CBD 商圈的中心位置——万达广场；它可以更好、更方便的为广大用户提服务。荣耀世华公司正式获得“中硅融德投资集团”的首轮5000万元融资，未来1-3年我们将继续投入3亿元的资金进行“健康康养云平台服务”建设。</p>
               <span class="cd-date">8</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
          <div class="cd-timeline-block">
            <div class="cd-timeline-img"> </div>
            <!-- cd-timeline-img -->
            <div class="cd-timeline-content">
              <p style="font-family: 'songti', 宋体;">07月  荣耀世华科技股份公司正式成立，一个为造福全人类千秋百岁的幸福安康的量子医疗健康服务公司诞生了。</p>
               <span class="cd-date">7</span> </div>
            <!-- cd-timeline-content --> 
          </div>
          <!-- cd-timeline-block --> 
          
          
        </section>
        <!-- cd-timeline --> 
        
        <script>
$(function(){
	var $timeline_block = $('.cd-timeline-block');
	//hide timeline blocks which are outside the viewport
	$timeline_block.each(function(){
		if($(this).offset().top > $(window).scrollTop()+$(window).height()*0.75) {
			$(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
		}
	});
	//on scolling, show/animate timeline blocks when enter the viewport
	$(window).on('scroll', function(){
		$timeline_block.each(function(){
			if( $(this).offset().top <= $(window).scrollTop()+$(window).height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
				$(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
			}
		});
	});
});
</script> 
      </div>
      <!--time line end--> 
      <!--
    	<img src="images/about_12.png" width="858" height="1234"/>
        --> 
    </div>
    <!--发展历程 end--> 
</div>
</c:when>
<c:otherwise>
  <!--right-->
  <div class="r_box right">
   	<!--公司介绍-->
  
    <div class="s_box1">
    	${fns:unescapeHtml(article.content)}
    </div>
    <!--公司介绍 end-->
  </div>
  <!--right end--> 

</c:otherwise>


</c:choose>
  
 
</div>
<!--main end--> 
</body>
</html>
