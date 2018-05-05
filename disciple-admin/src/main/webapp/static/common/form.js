/*
 * 
 * 
 * 
 * 
 * JavaScript - form
 * 
 */

;
$(function() {

	var $inputForm = $("#inputForm");// 输入表单

	// 校验
	$inputForm.validate({
		submitHandler : function(form) {
			loading();
			form.submit();
		},

	});

});