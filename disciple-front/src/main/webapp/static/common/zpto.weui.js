//微信dialog
$(function() {
	$alert = $("#dialog_alert");
	$confirm = $("#dialog_confirm");

	// 确定按钮事件
	var btnOk = function(obj, callback) {
		var $dialog = $(obj);
		$dialog.on('click', '.ok', function() {
			$dialog.off('click').hide();
			if (typeof (callback) == 'function') {
				callback();
			}
		});
	}

	// 取消按钮事件
	var btnCancell = function(obj) {
		var $dialog = $(obj);
		$dialog.on('click', '.cancell', function() {
			$dialog.off('click').hide();
		});
	}
	var defaults = {
		title : '温馨提示',
		message : '',
		callback : ''
	}
	// 确认对话框
	$.weuiConfirm = function(options) {
		var $dialog = $confirm;
		var settings = $.extend(defaults, options);
		$dialog.find(".message").html(settings.message);
		$dialog.find(".title").html(settings.title);
		$dialog.css("display", "block");
		btnOk($dialog, settings.callback);
		btnCancell($dialog);
	}
	// alert对话框
	$.weuiAlert = function(options) {
		var $dialog = $alert;
		var settings = $.extend(defaults, options);
		$dialog.find(".message").html(settings.message);
		$dialog.find(".title")
				.html(settings.title);
		$dialog.css("display", "block");
		btnOk($dialog);
	}
});
// 微信弹出框
$(function() {

	var defaults = {
		callback : ''// function(obj) obj 点击事件触发的方法
	}
	$.weuiActionsheet = function(options) {
		var settings = $.extend(defaults, options);
		var mask = $('#mask');
		var weuiActionsheet = $('#weui_actionsheet');
		weuiActionsheet.addClass('weui_actionsheet_toggle');
		mask.show().focus()// 加focus是为了触发一次页面的重排(reflow or layout
							// thrashing),使mask的transition动画得以正常触发
		.addClass('weui_fade_toggle').one('click', function() {
			hideActionSheet(weuiActionsheet, mask);
		});
		$('#actionsheet_cancel').one('click', function() {
			hideActionSheet(weuiActionsheet, mask);
		});
		mask.unbind('transitionend').unbind('webkitTransitionEnd');
		weuiActionsheet.find(".weui_actionsheet_cell").click(function() {
			if (typeof (settings.callback) == 'function') {
				settings.callback($(this));
			}
			hideActionSheet(weuiActionsheet, mask);
		});

		function hideActionSheet(weuiActionsheet, mask) {
			weuiActionsheet.removeClass('weui_actionsheet_toggle');
			mask.removeClass('weui_fade_toggle');
			mask.on('transitionend', function() {
				mask.hide();
			}).on('webkitTransitionEnd', function() {
				mask.hide();
			})
		}

	}
});