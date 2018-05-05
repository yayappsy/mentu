jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span",
    errorClass : 'haohan-field-error',  
    focusInvalid : false,
    highlight : function(element) {  
        $(element).closest('.control-group').addClass('has-error');  
    },  

    success : function(label) {  
        label.closest('.control-group').removeClass('has-error');  
        label.remove();  
    },  

    errorPlacement : function(error, element) {  
    	element.parent('div').append(error);  
    },  

    submitHandler : function(form) {  
        form.submit();  
    } 
});
//去除隐藏
$.validator.setDefaults({ignore: ""});