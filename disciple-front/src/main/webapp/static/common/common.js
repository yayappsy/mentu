/*
 * 
 * JavaScript - Common
 */

var weimhc = {
	base : "",
	locale : "zh_CN"
};

var setting = {
	priceScale : "2",
	priceRoundType : "roundHalfUp",
	currencySign : "￥",
	currencyUnit : "元",
	uploadImageExtension : "jpg,jpeg,bmp,gif,png",
	uploadFlashExtension : "swf,flv",
	uploadMediaExtension : "swf,flv,mp3,wav,avi,rm,rmvb",
	uploadFileExtension : "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};

var messages = {
	"front.message.success" : "操作成功",
	"front.message.error" : "操作错误",
	"front.dialog.ok" : "确&nbsp;&nbsp;定",
	"front.dialog.cancel" : "取&nbsp;&nbsp;消",
	"front.dialog.deleteConfirm" : "您确定要删除吗？",
	"front.dialog.clearConfirm" : "您确定要清空吗？",
	"front.dialog.loading" : "正在提交",
	"front.dialog.systemPrompt" : "佰佬荟提示",
	"front.dialog.alert" : "提示信息",
	"front.validate.required" : "必填",
	"front.validate.email" : "E-mail格式错误",
	"front.validate.url" : "网址格式错误",
	"front.validate.date" : "日期格式错误",
	"front.validate.dateISO" : "日期格式错误",
	"front.validate.pointcard" : "信用卡格式错误",
	"front.validate.number" : "只允许输入数字",
	"front.validate.digits" : "只允许输入零或正整数",
	"front.validate.minlength" : "长度不允许小于{0}",
	"front.validate.maxlength" : "长度不允许大于{0}",
	"front.validate.rangelength" : "长度必须在{0}-{1}之间",
	"front.validate.min" : "不允许小于{0}",
	"front.validate.max" : "不允许大于{0}",
	"front.validate.range" : "必须在{0}-{1}之间",
	"front.validate.accept" : "输入后缀错误",
	"front.validate.equalTo" : "两次输入不一致",
	"front.validate.remote" : "输入错误",
	"front.validate.integer" : "只允许输入整数",
	"front.validate.positive" : "只允许输入正数",
	"front.validate.negative" : "只允许输入负数",
	"front.validate.decimal" : "数值超出了允许范围",
	"front.validate.pattern" : "格式错误",
	"front.validate.extension" : "文件格式错误",
	"weight" : "重",
	"kg" : "kg",
	"number" : "件",
	"num" : "件"
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""),
				(options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 货币格式化
function currency(value, showSign, showUnit) {
	if (value != null) {
		var price;
		if (setting.priceRoundType == "roundHalfUp") {
			price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else if (setting.priceRoundType == "roundUp") {
			price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		} else {
			price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math
					.pow(10, setting.priceScale)).toFixed(setting.priceScale);
		}
		if (showSign) {
			price = setting.currencySign + price;
		}
		if (showUnit) {
			price += setting.currencyUnit;
		}
		return price;
	}
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(
							new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1),
						function(i, n) {
							content = content.replace(new RegExp("\\{" + i
									+ "\\}", "g"), n);
						});
				return content;
			}
		}
	}
}

(function($) {

	var zIndex = 100;

	// 检测登录
	$.checkLogin = function() {
		var result = false;
		$.ajax({
			url : weimhc.base + "/login/check",
			type : "GET",
			dataType : "json",
			cache : false,
			async : false,
			success : function(data) {
				result = data;
			}
		});
		return result;
	}

	// 跳转登录
	$.redirectLogin = function(redirectUrl, message) {
		var href = weimhc.base + "/login";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			$.message("warn", message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	}

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {}; 
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
			if(typeof arguments[1] === "object"){
				$message = arguments[1];
			}
		} else if (typeof arguments[0] === "string"
				&& typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.code = arguments[1];
			if(typeof arguments[2] === "object"){
				$message = arguments[2];
			}
		} else {
			return false;
		}
		if (message.type == null || message.code == null) {
			return false;
		}
        if($message == null){
		    $message = $("#messageBox");
        }
		if (!window.XMLHttpRequest) {
			$message.append('<iframe class="messageIframe"><\/iframe>');
		}
		$message.html('<label class="fieldError">' + message.content
				+ '</label>');
		$message.show();

		return $message;
	}
	//added by zsm
	$.messageBox = function() {
	    var result = {}; 
	    var $messageBox;
	    if ($.isPlainObject(arguments[0])) {
	        result = arguments[0];
	        if(typeof arguments[1] === "object"){
	            $messageBox = arguments[1];
	        }
	    } else {
	        return false;
	    }
	    if (result.message == null) {
	        return false;
	    }
	    if($messageBox == null){
	        $messageBox = $("#messageBox");
	    }
	    if (!window.XMLHttpRequest) {
	        $messageBox.append('<iframe class="messageIframe"><\/iframe>');
	    }
	    $messageBox.html('<label class="fieldError">' + result.message
	            + '</label>');
	    $messageBox.show();
	    
	    return $messageBox;
	}
	
	$.clearValidMessage = function(){
	    $(".fieldError").remove();
	}
	
	$.showValidMessage = function() {
	    var result = {}; 
	    var $validToSelector;
	    var error;
	    if ($.isPlainObject(arguments[0])) {
	        result = arguments[0];
	        if(typeof arguments[1] === "object"){
	            $validToSelector = arguments[1];
	        }
	    } else {
	        return false;
	    }
	    if (result.message == null) {
	        return false;
	    }
	    if($validToSelector == null){
	        $validToSelector = $("#messageBox");
	    }
	    error = $('<label class="fieldError">' + result.message
                + '</label>');
	    error.appendTo($validToSelector.parent());  
	    return $validToSelector;
	}

	// 令牌
	$(document).ajaxSend(
			function(event, request, settings) {
				if (!settings.crossDomain && settings.type != null
						&& settings.type.toLowerCase() == "post") {
					var token = getCookie("token");
					if (token != null) {
						request.setRequestHeader("token", token);
					}
				}
			});

	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");

		if (loginStatus == "accessDenied") {
			$.redirectLogin(location.href, "请登录后再进行操作");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global : false,
					headers : {
						token : token
					}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$()
		.ready(
				function() {

					$("form")
							.submit(
									function() {
										var $this = $(this);
										if ($this.attr("method") != null
												&& $this.attr("method")
														.toLowerCase() == "post"
												&& $this.find(
														"input[name='token']")
														.size() == 0) {
											var token = getCookie("token");
											if (token != null) {
												$this
														.append('<input type="hidden" name="token" value="'
																+ token
																+ '" \/>');
											}
										}
									});

				});

// 验证消息
if ($.validator != null) {
	$.extend($.validator.messages,
			{
				required : message("front.validate.required"),
				email : message("front.validate.email"),
				url : message("front.validate.url"),
				date : message("front.validate.date"),
				dateISO : message("front.validate.dateISO"),
				pointcard : message("front.validate.pointcard"),
				number : message("front.validate.number"),
				digits : message("front.validate.digits"),
				minlength : $.validator
						.format(message("front.validate.minlength")),
				maxlength : $.validator
						.format(message("front.validate.maxlength")),
				rangelength : $.validator
						.format(message("front.validate.rangelength")),
				min : $.validator.format(message("front.validate.min")),
				max : $.validator.format(message("front.validate.max")),
				range : $.validator.format(message("front.validate.range")),
				accept : message("front.validate.accept"),
				equalTo : message("front.validate.equalTo"),
				remote : message("front.validate.remote"),
				integer : message("front.validate.integer"),
				positive : message("front.validate.positive"),
				negative : message("front.validate.negative"),
				decimal : message("front.validate.decimal"),
				pattern : message("front.validate.pattern"),
				extension : message("front.validate.extension")
			});

	$.validator.setDefaults({
		errorClass : "fieldError",
		ignore : ".ignore",
		ignoreTitle : true,
		errorPlacement : function(error, element) {
			var fieldSet = element.closest("span.fieldSet");
			if (fieldSet.size() > 0) {
				error.appendTo(fieldSet);
			} else {
				error.insertAfter(element);
			}
		},
		submitHandler : function(form) {
			$(form).find(":submit").prop("disabled", true);
			form.submit();
		}
	});
}

// 恢复提示框显示
function resetTip() {
	top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip() {
	top.$.jBox.closeTip();
}

// 显示提示框
function showTip(mess, type, timeout, lazytime) {
	resetTip();
	setTimeout(function() {
		top.$.jBox.tip(mess, (type == undefined || type == '' ? 'info' : type),
				{
					opacity : 0,
					timeout : timeout == undefined ? 2000 : timeout
				});
	}, lazytime == undefined ? 500 : lazytime);
}

// 显示加载框
function loading(mess) {
	if (mess == undefined || mess == "") {
		mess = message("front.dialog.loading");
	}
	resetTip();
	top.$.jBox.tip(mess, 'loading', {
		opacity : 0
	});
}

// 警告对话框
function alertx(mess, closed) {
	top.$.jBox.info(mess, message("front.dialog.alert"), {
		closed : function() {
			if (typeof closed == 'function') {
				closed();
			}
		}
	});
	top.$('.jbox-body .jbox-icon').css('top', '40px');
}

// 确认对话框
// href 代表要执行的回调函数
function confirmx(mess, href, closed) {
	top.$.jBox.confirm(mess, message("front.dialog.systemPrompt"), function(v,
			h, f) {
		if (v == 'ok') {
			if (typeof href == 'function') {
				href();
			} else if (typeof href == 'string') {
				resetTip(); // loading();
				location = href;
			}
		}
	}, {
		buttonsFocus : 1,
		closed : function() {
			if (typeof closed == 'function') {
				closed();
			}
		}
	});
	top.$('.jbox-body .jbox-icon').css('top', '40px');
	return false;
}

// 提示输入对话框
function promptx(title, lable, href, closed) {
	top.$
			.jBox(
					"<div class='form-search' style='padding:20px;text-align:center;'>"
							+ lable
							+ "：<input type='text' id='txt' name='txt'/></div>",
					{
						title : title,
						submit : function(v, h, f) {
							if (f.txt == '') {
								top.$.jBox.tip(
										message("admin.dialog.pleaseInput")
												+ lable + "。", 'error');
								return false;
							}
							if (typeof href == 'function') {
								href();
							} else {
								resetTip(); // loading();
								location = href + encodeURIComponent(f.txt);
							}
						},
						closed : function() {
							if (typeof closed == 'function') {
								closed();
							}
						}
					});
	return false;
}

// 添加TAB页面
function addTabPage(title, url, closeable, $this, refresh) {
	top.$.fn.jerichoTab.addTab({
		tabFirer : $this,
		title : title,
		closeable : closeable == undefined,
		data : {
			dataType : 'iframe',
			dataLink : url
		}
	}).loadData(refresh != undefined);
}

// 从list页面移动到此处
function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#inputForm").submit();
	return false;
}
// menu 菜单切换
function showMenu(total, id) {
	for (var i = 0; i < total; i++) {

		var nav = document.getElementById('nav_' + i);
		var box = document.getElementById('box_' + i);

		if (i == id) {
			nav.className = 'on';
			box.style.display = "block";
		} else {
			nav.className = '';
			box.style.display = "none";
		}
	}
}
// 获取图片缩略图路径
// var urlPattern =
// /((http|ftp|https):\/\/)(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(\/)?/
function getThumbnailPath(realPath, width, height) {
	// prefix = realPath.match(urlPattern)[0],
	var thumbnail = realPath + "_" + width + "x" + height + ".jpg";
	return thumbnail;
}
function removeEmptyItem(arrs) {
	for (var i = 0; i < arrs.length; i++) {
		if (arrs[i] == "" || typeof (arrs[i]) == "undefined") {
			arrs.splice(i, 1);
			i = i - 1;

		}
	}
	return arrs;
}
// 模拟点击事件
function simulatedClick(url, target) {
	var el = document.createElement("a");
	document.body.appendChild(el);
	el.href = url; // url 是你得到的连接
	if(typeof target != "undefined"){
	   el.target = target; //指定在新窗口打开
	}
	el.click();
	document.body.removeChild(el);
}
jQuery.extend({get:function( url, data, callback, type ) {
    // shift arguments if data argument was omitted
    if ( jQuery.isFunction( data ) ) {
        type = type || callback;
        callback = data;
        data = undefined;
    }
    url += url.match(/\?/) ? "&" : "?";
    url += "_dc="+new Date().getTime();
    return jQuery.ajax({
        type: 'get',
        url: url,
        data: data,
        success: callback,
        dataType: type
    });
}
});


weimhc.setArea = function (parentIds,dist) {
    parentIds = parentIds||"0,100000,110000,110100";
        dist =  dist || "110101";
    $("#city").citySelect({
        url: "../../res/jquery-selectCity/city.min.js",
        parentIds: parentIds,
        dist: dist,
        nodata: "none", //当子集无数据时，隐藏select
        required: false
    });
};
//将对象转为json字符串存储
weimhc.setJsonToSessionStorage = function (key,object) {
    sessionStorage.setItem(key,JSON.stringify(object));
};
//获取son字符串存储
weimhc.getJsonFromSessionStorage = function (key) {
    return sessionStorage.getItem(key);
};
//获取json字符串代表的对象
weimhc.getObjectFromSessionStorage = function (key) {
    return JSON.parse(weimhc.getJsonFromSessionStorage(key));
};
//删除对象
weimhc.removeItemKeyFromSessionStorage = function (key) {
     sessionStorage.removeItem(key);
};
weimhc.clearSessionStorage = function (key) {
    sessionStorage.clear();
};
