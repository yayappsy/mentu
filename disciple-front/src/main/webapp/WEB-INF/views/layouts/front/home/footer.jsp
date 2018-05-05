<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="display">
	<sitemesh:getProperty property="meta.display" />
</c:set>
<!--footer-->
<div class="m-footer">
	<div class="inner clearfix">
		<div class="fl_box left clearfix">
			<dl>
				<dt>
					<a style="color: #fff" href="${ctx }/">首页</a>
				</dt>
				<dd>
					<a href="${ctx}/aboutUs/">关于我们</a>
				</dd>
				<dd>
					<a href="${ctx}/news/">资讯中心</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a style="color: #fff" href="${ctx}/news/">资讯中心</a>
				</dt>
				<c:forEach items="${rfns:getArticleCategory('1')}"
					var="articleCategory">
					<dd>
						<a href="${ctx}/news/index?categoryId=${articleCategory.id}">${articleCategory.name}</a>
					</dd>
				</c:forEach>

			</dl>
			
			<dl>
				<dt>
					<a style="color: #fff" href="">友情链接</a>
				</dt>
				<c:forEach items="${rfns:getArticleCategory('8')}" var="articleCategory">
					<dd>
						<a target="_blank"
							href="${articleCategory.url}">${articleCategory.name}</a>
					</dd>
				</c:forEach>
			</dl>
		</div>
		<div class="fr_box right clearfix">
			<div class="ewm right">
				<img class="left"
					src="${ctxStatic }/modules/rysh/front/images/ewm.gif" />
			</div>
			<div class="tel right">
				<p style="font-size: 20px; line-height: 30px;">
					<i class="iconfont" style="font-size: 20px; margin-right: 10px;">&#xe604;</i>010-58205392
				</p>
				<p class="p2">周一至周日 9：30—18:30（仅收市话费）</p>
			</div>
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
			<li><a href="${ctx}/contactUs">联系我们</a></li>
			<li>|</li>
			<li><a href="${ctx}/customerService">客服服务</a></li>
			<li>|</li>
			<li><a href="${ctx}/suggestion/form">意见反馈</a></li>
			<li>|</li>
			<li><a href="${ctx}/relief">免责声明</a></li>
			<li>|</li>
			<li><a href="${ctx}/recruit">招贤纳士</a></li>
			<li>|</li>
			<li><a href="#">官方微博</a></li>
		</ul>
	</div>
</div>
<!--bottom end-->
<!--联系-->
<link href="${ctxStatic }/modules/rysh/front/iconfont/iconfont.css"
	rel="stylesheet" type="text/css" />
<div class="m-content">
	<div class="item">
		<a><i class="iconfont">&#xe62d;</i></a>
	</div>
	<div class="item">
		<a id="c_message"><i class="iconfont">&#xe624;</i></a>
	</div>
	<div class="item">
		<a id="c_weixin"><i class="iconfont">&#xe66f;</i></a>
	</div>
	<div class="item">
		<a id="c_phone"><i class="iconfont">&#xe604;</i></a>
	</div>
	<div class="item">
		<a class="backtobottom"><i class="iconfont">&#xe648;</i></a>
	</div>
</div>
<!--联系 end-->
<div class="c_phone clearfix">
	<div class="phone_l left">
		服务热线
		<p>010-58205392</p>
	</div>
	<div class="phone_r right">
		<p>· 贴心的1对1咨询服务</p>
		<p>· 快速敏捷的服务相应</p>
		<p>· 专家团队保航服务</p>
	</div>
</div>
<div class="c_weixin clearfix">
	<img src="${ctxStatic }/modules/rysh/front/images/ewm.jpg" width="192"
		height="192" />
</div>
<div class="c_message clearfix">
	<div class="close"></div>
	<p
		style="height: 50px; line-height: 50px; font-size: 20px; color: #2fa8e1;">在线留言</p>
	<div class="item clearfix">
		<label class="fieldname"><span class="need">*</span>姓名：</label>
		<div class="text_box">
			<input type="text" class="text" placeholder="" />
		</div>
	</div>
	<div class="item clearfix">
		<label class="fieldname"><span class="need">*</span>电话：</label>
		<div class="text_box">
			<input type="text" class="text" placeholder="" />
		</div>
	</div>
	<div class="item clearfix">
		<label class="fieldname"><span class="need">*</span>E-mail：</label>
		<div class="text_box">
			<input type="text" class="text" placeholder="" />
		</div>
	</div>
	<div class="item clearfix">
		<label class="fieldname"><span class="need">*</span>主题：</label>
		<div class="text_box">
			<input type="text" class="text" style="width: 300px;" placeholder="" />
		</div>
	</div>
	<div class="item clearfix">
		<label class="fieldname"><span class="need">*</span>内容：</label>
		<div class="text_box">
			<textarea class="text" style="width: 300px; height: 60px;"></textarea>
		</div>
	</div>
	<div class="item" style="margin-top: 30px;">
		<label class="fieldname">&nbsp;</label> <input type="submit"
			style="width: 90px; margin-right: 15px;" class="bnt" value="确认提交" />
		<input type="button" style="width: 90px;" class="bnt" value="重置" />
	</div>
	<p
		style="height: 40px; line-height: 40px; font-size: 16px; color: #2fa8e1;">温馨提示</p>
	<p>为方便联系您，以上带“ * ”的部分必须填写！</p>
	<p>请认真填写您的联系方式，我们在收到您的留后后，会尽快处理回复！</p>
</div>