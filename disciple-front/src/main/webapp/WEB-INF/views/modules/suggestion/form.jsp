<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="意见反馈" /></title>
<meta name="decorator" content="front_home" />
<script type="text/javascript"
	src="${ctxStatic }/jquery-uploadPreview/jquery.uploadPreview.js"></script>
<link href="${ctxStatic }/modules/rysh/front/css/singlepage.css?ver=17"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$('.l_box .type li').mouseover(function() {
			$('.l_box .type li a').removeClass('current');
			$(this).children('a').addClass('current');

			var index = $(this).index();
			$('.news_box').hide();
			$(".l_box .news_box:eq(" + index + ")").show();
		});

		$("#picFile").uploadPreview({
			Img : "preview",
			Width : 76,
			Height : 79,
			Callback : function() {
				$("#preview").show();
			}
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
	    var $inputForm = $("#inputForm");
		var $captcha = $("#captcha");
	    var $captchaImage = $("#captchaImage");
	    var $changeCode = $("#change_code");

		// 更换验证码
		function changeCaptcha(){
			$captchaImage.attr("src", "${ctx}/common/getImgCaptcha?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
		}
	    $captchaImage.click(function() {
	    	changeCaptcha();
	    });
	    $changeCode.click(function() {
	    	changeCaptcha();
	    });
	    $inputForm.validate({
	        submitHandler: function(form) {
	            loading();
	            if (!$.checkLogin()) {
					alertx("<spring:message code='front.login.error.accessDenied'/>");
					closeTip();
					return false;
				}
	            form.submit();
	        }
	    });	    
	});
</script>
<style type="text/css">
.m-banner {
	width: 100%;
	height: 249px;
	background: url(${ctxStatic }/modules/rysh/front/images/yijian_02.png)
		center no-repeat;
}
</style>
</head>

<body>
	<div class="m-banner"></div>

	<!--banner end-->
	<!--当前位置-->
	<div class="m-position">
		<ul class="clearfix">
			<li>当前位置</li>
			<li>&gt;</li>
			<li>首页</li>
			<li>&gt;</li>
			<li>意见反馈</li>
		</ul>
	</div>
	<!--当前位置 end-->
	<!--main-->
	<div class="m-main clearfix">
		<!--意见反馈-->
		<form:form id="inputForm" action="${ctx}/suggestion/save"
			modelAttribute="suggestion" enctype="multipart/form-data">
			<div class="s_box4">

				<div class="item clearfix">
					<label class="fieldname"><span class="need">*</span>反馈类型：</label>
					<div class="text_box">
						<form:select class="text required" path="suggestionSubject">
							<option value="">请选择反馈类型</option>
							<c:forEach items="${suggestionSubjectList}"
								var="suggestionSubject">
								<option value="${suggestionSubject.id}">${suggestionSubject.subjectTitle}</option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="item  clearfix" style="height: auto;">
					<label class="fieldname"><span class="need">*</span>反馈内容：</label>
					<div class="text_box">
						<form:textarea class="text required" path="content" placeholder="请输入反馈内容"></form:textarea>
					</div>
				</div>
				<div class="item  clearfix">
					<label class="fieldname">上传图片：</label>
					<div class="text_box">
						<img id="preview" style="display: none" src="" width="79"
							height="79" /> <a class="bimg"><input style="padding-top: 8px;color: #a5a5a5;" id="picFile"
							type="file" name="file" class="file" /></a>
					</div>

				</div>
				<div class="item clearfix">
					<label class="fieldname"><span class="need">*</span>联系电话：</label>
					<div class="text_box">
						<form:input type="text" class="text required" path="mobile" 
							placeholder="请输入联系电话" />
					</div>
				</div>
				<div class="item  clearfix">
					<label class="fieldname"><span class="need">*</span>验证码</label>
					<div class="text_box">
						<input type="text" class="text required" placeholder="请输入验证码"
							style="width: 200px; margin-right: 8px;" />
						<!---触发状态加class="codebnt codebnt_on left"-->
						<a class="codebnt"><img  class="left"
								id="captchaImage" class="captchaImage"
								src="${ctx}/common/getImgCaptcha?captchaId=${captchaId}" width="100" height="37"
								title="<spring:message code='shop.captcha.imageTitle'/>" /></a><a id="change_code" class="change_code">看不清，换一张</a>
					</div>
				</div>
				<div class="item agreement">
					<label class="fieldname">&nbsp;</label>
					<div class="text_box">
						<input class="check1" type="checkbox" checked="checked"
							style="vertical-align: middle; margin-right: 5px;" />我已阅读并同意<a
							href="#">《隐私保护》</a>
					</div>
				</div>
				<div class="item" style="margin-top: 30px;">
					<label class="fieldname">&nbsp;</label> <input type="submit"
						class="bnt" value="提交反馈" />
				</div>

			</div>
		</form:form>
		<!--register end-->



	</div>
	<!--意见反馈 end-->
	</div>
	<!--main end-->
</body>
</html>
