/*
 * 
 * 
 * 
 * 
 * JavaScript - form
 * 
 */

$().ready(
		function() {

			var $inputForm = $("#inputForm");//输入表单

			// 校验
			var $inputForm.validate(
					{
						submitHandler : function(form) {
							loading();
							form.submit();
						},
						errorContainer : "#messageBox",
						errorPlacement : function(error, element) {
							$("#messageBox").text(
									message("admin.validate.error"));
							if (element.is(":checkbox") || element.is(":radio")
									|| element.parent().is(".input-append")) {
								error.appendTo(element.parent().parent());
							} else {
								error.insertAfter(element);
							}
						}
					});

		});