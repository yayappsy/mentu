$(function () {

    var $registerForm = $("#registerForm");
    var $mobile = $("#rmobile");
    var $username = $mobile;
    var $password = $("#rpassword");
    var $captcha = $("#captcha");
    var $submit = $("#rSubmit");
    var $agreement = $("#agreement");
    var $getcode = $("#getcode");

    // 表单验证
    $username.bind("blur", function () {
        if ($username.val() == '') {
            return;
        }
        $.clearValidMessage();
        $.ajax({
            url : ctx + "/register/check_username",
            type : "GET",
            data : {
                username : $username.val(),
            },
            dataType : "json",
            cache : false,
            success : function (data) {
                if (!data) {
                    $getcode.prop("disabled", true);
                    $.messageBox({message:"手机号已存在"},$("#messageBox1"));
                } else {
                    $getcode.prop("disabled", false);
                }
            },
            error : function (data) {
                $submit.removeClass("weui_btn_disabled");
            }
        });
    });

    $getcode.on("click",function () {
        $.clearValidMessage();
        var reg = /^1[3|4|5|8][0-9]\d{8}$/;
        if (!reg.test($mobile.val())) {
            $.messageBox({message:"请输入正确的手机号"},$("#messageBox1"));
            $username.focus();
            return;
        }
        var sendData = {
            mobile : $mobile.val()
        }
        $getcode.prop("disabled", true);
        $.ajax({
            url : ctxApi + "/smsCaptcha/add",
            type : "POST",
            data : JSON.stringify(sendData),
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            cache : false,
            success : function (data) {
                if (data.code == '0') {
                    // 禁用获取验证码按钮，并倒计时
                    var count = 60;
                    var countdown = "";
                    function CountDown() {
                        $getcode.text(count + "秒后重试");
                        if (count == 0) {
                            $getcode.removeAttr("disabled");
                            $getcode.text("获取验证码");
                            clearInterval(countdown);
                        }
                        count--;
                    }
                    countdown = setInterval(CountDown, 1000);
                } else {
                    $getcode.prop("disabled", false);
                    $.messageBox({message:"验证码生成错误"},$("#messageBox1"));
                }
            },
            error : function (data) {
                $.messageBox({message:"验证码生成错误"},$("#messageBox1"));
                return false;
            }
        });
    });

    $registerForm
            .validate({
                rules : {
                    mobile : {
                        required : true,
                        minlength : 6
                    },
                    password : {
                        required : true,
                        pattern : /^[^\s&\"<>]+$/,
                        minlength : 6
                    },
                    // rePassword: {
                    // required: true,
                    // equalTo: "#password"
                    // },
                    captcha : {
                        required : true,
                    }
                },
                messages : {
                    // username: {
                    // remote: "<spring:message
                    // code='front.register.disabledExist' />"
                    // },
                    password : {
                        pattern : "<spring:message code='front.register.passwordIllegal' />"
                    }
                },
                submitHandler : function (form) {
                    $.ajax({
                                url : ctxApi+"/rsa/publicKey",
                                type : "GET",
                                dataType : "json",
                                cache : false,
                                beforeSend : function () {
                                    /*
                                     * if(!$("input[type='checkbox']").is(':checked')){
                                     * alertx("请阅读《用户使用协议》并勾选"); return false; }
                                     */
                                    $submit.prop("disabled", true);
                                    $submit.addClass("disabled");
                                },
                                success : function (data) {
                                    var rsaKey = new RSAKey();
                                    rsaKey.setPublic(b64tohex(data.modulus),
                                            b64tohex(data.exponent));
                                    var enPassword = hex2b64(rsaKey
                                            .encrypt($password.val()));
                                    var sendData = {
                                        username : $username.val(),
                                        mobile : $mobile.val(),
                                        enPassword : enPassword,
                                        captcha : $captcha.val()
                                    };

                                    $.ajax({
                                                url : $registerForm
                                                        .attr("action"),
                                                type : "POST",
                                                data : JSON.stringify(sendData),
                                                dataType : "json",
                                                contentType : "application/json; charset=utf-8",
                                                cache : false,
                                                success : function (result) {
                                                    console.log(result)
                                                    $.messageBox(result,$("#messageBox1"));
                                                    $submit.prop("disabled",
                                                            false);
                                                    if (result.code == 0) {
                                                        history.go(0);
                                                    } else {
                                                        $captcha.val("");
                                                    }
                                                },
                                                error : function(message) {
                                                    console.log(message);
                                                    $submit.prop("disabled", false);
                                                }
                                            });
                                }
                            });
                }
            });
});
