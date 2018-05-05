$(document).ready(
		function() {
			var $loginForm = $("#loginForm");
			var $username = $("#username");
			var $password = $("#password");
			// var $captcha = $("#captcha");
			// var $captchaImage = $("#captchaImage");
			var $isRememberUsername = $("#isRememberUsername");
			var $submit = $("#loginSubmit");
			
			// 记住用户名
			if (getCookie("memberUsername") != null) {
				$isRememberUsername.prop("checked", true);
				$username.val(getCookie("memberUsername"));
				$password.focus();
			} else {
				$isRememberUsername.prop("checked", false);
				$username.focus();
			}
			// 避免刷新页面时，按钮不能点击
			if ($submit.prop("disabled")) {
				$submit.prop("disabled", false);
			}
			// 更换验证码
			// $captchaImage.click(function() {
			// $captchaImage.attr("src",
			// "${ctx}/common/getImgCaptcha?captchaId=${captchaId}&timestamp=" +
			// (new Date()).valueOf());
			// });

			// 表单验证、记住用户名
			$loginForm.validate({
				rules : {
					username : {
						required : true,
						minlength : 6
					},
					password : {
						required : true,
						pattern : /^[^\s&\"<>]+$/,
						minlength : 6
					},
					captcha : "required"
				},
				submitHandler : function(form) {
					loading();
					$.ajax({
						url : ctxApi+"/rsa/publicKey",
						type : "GET",
						dataType : "json",
						cache : false,
						beforeSend : function() {
							$submit.prop("disabled", true);
						},
						success : function(data) {
							var rsaKey = new RSAKey();
							rsaKey.setPublic(b64tohex(data.modulus),
									b64tohex(data.exponent));
							var enPassword = hex2b64(rsaKey.encrypt($password
									.val()));
							console.log(enPassword);
							var sendData = {username : $username.val(),
									enPassword : enPassword,ajax : true}
							$.ajax({
								url : $loginForm.attr("action"),
								type : "POST",
								data : JSON.stringify(sendData),
								dataType : "json",
								contentType: "application/json; charset=utf-8",
								cache : false,
								success : function(message) {
									closeTip();
									if ($isRememberUsername.prop("checked")) {
										addCookie("memberUsername", $username
												.val(), {
											expires : 7 * 24 * 60 * 60
										});
									} else {
										removeCookie("memberUsername");
									}
									$submit.prop("disabled", false);
									if (message.type == "success") {
										history.go(0);
									} else {
										$.messageBox(message);
									}
								},
								error : function(message) {
									console.log(message);
									closeTip();
									$submit.prop("disabled", false);
								}
							});
						}
					});

				}
			});

		});
