<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="意见反馈" /></title>
<meta name="decorator" content="front_home" />

<link href="${ctxStatic }/modules/rysh/front/css/singlepage.css?ver=17" rel="stylesheet" type="text/css" />
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
<style type="text/css">
.m-banner{ width:100%; height:249px; background:url(${ctxStatic }/modules/rysh/front/images/yijian_02.png) center no-repeat;}
</style>
</head>

<body>
<div class="m-banner">

</div>

<!--banner end--> 
<!--当前位置-->
<div class="m-position">
	<ul class="clearfix">
    	<li>当前位置</li><li>&gt;</li><li>首页</li><li>&gt;</li><li>意见反馈</li>
    </ul>
</div>
<!--当前位置 end-->
<!--main-->
<div class="m-main clearfix"> 
 <!--意见反馈-->
 <div class="s_box4">
	<div class="item clearfix">
  <label class="fieldname"><span class="need">*</span>反馈类型：</label>
  <div class="text_box">
  
  <p class="text">${suggestion.suggestionSubject.subjectTitle}</p>
    
  </div>
</div>
<div class="item  clearfix" style="height:auto;">
  <label class="fieldname"><span class="need">*</span>反馈内容：</label>
  <div class="text_box">
  <p class="text">${suggestion.content}</p>
  	 
  </div>
</div>
<div class="item  clearfix">
  <label class="fieldname">上传图片：</label>
  <div class="text_box">
  <img id="preview" src="${suggestion.images}" width="79" height="79" />
  </div>
   
</div>
<div class="item clearfix">
  <label class="fieldname"><span class="need">*</span>联系电话：</label>
  <div class="text_box">
   <p class="text">${suggestion.mobile}</p>
   
  </div>
</div>

        
        <div class="item" style="margin-top:30px;">
          <label class="fieldname">&nbsp;</label>
          <a type="submit" class="bnt" href="${ctx}/">回到首页</a>
        </div>
        
      </div>
      <!--register end--> 
  
      
        
 </div>
 <!--意见反馈 end-->

<!--main end--> 
</body>
</html>
