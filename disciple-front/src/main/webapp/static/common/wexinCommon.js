(function($) {

	$.ajaxSetup({
		cache : false
	// 默认为true，所以会带上时间戳，设置为false将不会从浏览器缓存中加载请求信息
	});

})(jQuery);