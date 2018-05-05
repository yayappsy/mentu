<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>


<form:form id="registerForm" action="${ctxApi}/member/add"
	modelAttribute="member">
	<div class="register_box left">
		<div class="close close1"></div>
		<div id="messageBox1" class="hide"></div>
		<p
			style="height: 50px; line-height: 50px; text-align: center; font-size: 20px; color: #2fa8e1;">账号注册</p>
	    <!-- <div class="item clearfix">
			<label class="fieldname"><span class="need">*</span>用户名：</label>
			<div class="text_box">
				<input type="text" id="rusername" name="username"
					class="text required" placeholder="请输入邮箱和手机号码" />
			</div>
		</div> -->
		<div class="item clearfix">
			<label class="fieldname"><span class="need">*</span>手机号码：</label>
			<div class="text_box">
				<input type="text" class="text  required" id="rmobile" name="mobile"
					placeholder="请输入手机号码" />
		</div>
		</div>
		<div class="item  clearfix">
			<label class="fieldname"><span class="need">*</span>密码：</label>
			<div class="text_box">
				<input type="password" class="text required" id="rpassword"
					name="password" placeholder="请输入密码" />
			</div>
		</div>
		<div class="item  clearfix">
			<label class="fieldname"><span class="need">*</span>确认密码：</label>
			<div class="text_box">
				<input type="password" class="text required" id="rePassword"
					name="rePassword" placeholder="再次输入密码" />
			</div>
		</div>
		<div class="item  clearfix">
			<label class="fieldname"><span class="need">*</span>短信验证码</label>
			<div class="text_box">
				<input type="text" class="text required" id="captcha" name="captcha"
					placeholder="输入短信验证码" style="width: 150px; margin-right: 8px;" />
				<!---触发状态加class="codebnt codebnt_on left"-->
				<button type="button" class="codebnt left" id="getcode">获取验证码</button>
			</div>
		</div>
		<div class="item" style="margin-top: 30px;">
			<label class="fieldname">&nbsp;</label> <input type="submit"
				id="rSubmit" class="bnt" value="注册" />
		</div>
		<div class="item agreement">
			<label class="fieldname">&nbsp;</label>
			<div class="text_box">
				<!--<span class="checkbox checked left"><input class="check1" type="checkbox" checked="checked"/></span>-->
				<input class="checkbox left" type="checkbox" checked="checked" ><a href="${ctx}/agreement" target="_blank">用户查看并同意（荣耀世华网用户协议）</a>
			</div>
		</div>
	</div>
</form:form>
<!--register end-->



