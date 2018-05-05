(function($) {
	// 在我们插件容器内，创造一个公共变量来构建一个私有方法
	var privateFunction = function() {

		// code here
	}

	// 通过字面量创造一个对象，存储我们需要的共有方法
	var methods = {

		// 在字面量对象中定义每个单独的方法
		init : function() {

			// 为了更好的灵活性，对来自主函数，并进入每个方法中的选择器其中的每个单独的元素都执行代码
			return this.each(function() {

				// 为每个独立的元素创建一个jQuery对象
				var $this = $(this);

				// 执行代码

				// 例如： privateFunction();
			});
		},
		destroy : function() {

			// 对选择器每个元素都执行方法
			return this.each(function() {

				// 执行代码
			});
		}
	};


	/**
	 * 模拟淘宝SKU添加组合
	 * 
	 * 
	 * @author sz
	 */
	$.fn.skuCheckbox = function(options) {

		var settings = $.extend({},$.fn.skuCheckbox.defaults , options);//将一个空对象做为第一个参数
		var selectedNodes = settings.selectedNodes || [];
		for (var i = 0; i < selectedNodes.length; i++) {
			$(this.selector + "_" + selectedNodes[i].id).iCheck("check");
		}
		
		return this;

	}

	/**
	 * 默认参数
	 * 
	 * 
	 * @author sz
	 */	
	$.fn.skuCheckbox.defaults = {
			
	}	
}(jQuery));
