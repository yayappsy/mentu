<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!--login-->
<form id="loginForm" action="${ctxApi}/session/add" method="post">
	<div class="login_box right">
		<div class="close close2"></div>
		<div class="item clearfix">
			<label id="labelForUsername" class="fieldname">用户名</label>
			<div id="messageBox" class="hide"></div>
			<div class="text_box">
				<input type="text" id="username" name="username" class="text"
					placeholder="请输入邮箱和手机号码" required />
			</div>
		</div>
		<div class="item clearfix">
			<label class="fieldname">密码</label>
			<div class="text_box">
				<input type="password" id="password" name="password" class="text"
					placeholder="请输入密码" required />
			</div>
		</div>
		<div class="item clearfix"
			style="padding: 0px; margin: 0 auto; margin-bottom: 20px; width: 200px; margin-top: 10px;">
			<input type="submit" id="loginSubmit" class="bnt left" value="登录" />
			<input type="button" id="loginCancel" class="bnt right" value="取消" />
		</div>
		<div class="item clearfix"
			style="background:url(${ctxStatic }/modules/rysh/front/images/login_line.png) top center no-repeat; padding-top:15px;">
			<div class="left">
				<input class="check1" type="checkbox" id="isRememberUsername"
					name="isRememberUsername" checked="checked"
					style="vertical-align: middle; margin-right: 5px;" /> 十天内免登录
			</div>
			<a href="#" class="f_pass left">忘记密码</a>
		</div>
	</div>
</form>
<!--login end-->
<!--登录注册 end-->




