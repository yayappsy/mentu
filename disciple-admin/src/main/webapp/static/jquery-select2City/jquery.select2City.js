

;(function($, window, document, undefined) {

	// undefined作为形参的目的是因为在es3中undefined是可以被修改的
	// 比如我们可以声明var undefined =
	// 123,这样就影响到了undefined值的判断，幸运的是在es5中,undefined不能被修改了。
	// window和document本身是全局变量，在这个地方作为形参的目的是因为js执行是从里到外查找变量的（作用域），把它们作为局部变量传进来，就避免了去外层查找，提高了效率。

	// 声明默认属性对象
	var pluginName = "select2City", defaults = {
			pluginHomeUrl:"",
			url: "location.js",
            prov: null,
            city: null,
            dist: null,
            nodata: null,
            required: true,
            title: ["省份" , "地级市" , "市、县、区"],
            selectorPrefix:"",
            firstCityPrefix:"0,100000",
            output:"",

	};

	// 构造函数
	function Plugin(element, options) {
		this.element = element;
		// 将默认属性对象和传递的参数对象合并到第一个空对象中
		this.settings = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		//使用that 存储this 在获Ajax中使用
		var that = this;
		
		//获取城市列表
		$.getJSON(this.settings.pluginHomeUrl+this.settings.url,function(data) {
			//console.log(data.cityList);
			that.items = data.cityList;
			that.init();
        });

	}

	// 为了避免和原型对象Plugin.prototype的冲突，这地方采用继承原型对象的方法
	$.extend(Plugin.prototype, {
		init : function() {
			// 初始化，由于继承自Plugin原型，
			// 你可以在这里直接使用this.element或者this.settings
			
			var that = this,
			  settings = this.settings;
			
			//获得省市区id前缀
			var selectorPrefix = settings.selectorPrefix || this.element.attr("id");
			//console.log(selectorPrefix);
			
			var provinceSelector = this.provinceSelector = selectorPrefix+"_province",
			    citySelector = this.citySelector = selectorPrefix+"_city",
			    townSelector = this.townSelector = selectorPrefix+"_town";
			
			var firstCityPrefix = settings.firstCityPrefix;

			var province = settings.prov,
                city = settings.city,
                town = settings.dist,
                title	= settings.title;
			
			$.each(title , function(k , v) {
				title[k]	= '<option value="">'+v+'</option>';
			});
			
			var city_box = $(this.element);
			
			var provinceObj = $("#"+provinceSelector),
			    cityObj = $("#"+citySelector),
			    townObj = $("#"+townSelector);
			
			provinceObj.append(title[0]);
			cityObj.append(title[1]);
			townObj.append(title[2]);

			provinceObj.change(function() {
				cityObj.empty();
				cityObj.append(title[1]);				
				that.fillOption(citySelector, firstCityPrefix+","+provinceObj.val());
				cityObj.change();
			});
			
			cityObj.change(function() {
				townObj.empty();
				townObj.append(title[2]);
				that.fillOption(townSelector , firstCityPrefix+"," + provinceObj.val() + "," + cityObj.val());
			});
			
			townObj.change(function() {
				$(settings.output).val($(this).val());
			});
			
			if (province) {
				this.fillOption(provinceSelector , firstCityPrefix , province);
				
				if (city) {
					this.fillOption(citySelector, firstCityPrefix+","+province , city);
					
					if (town) {
						this.fillOption(townSelector , firstCityPrefix+","+province+","+city , town);
					}
				}
				
			} else {
				this.fillOption(provinceSelector , firstCityPrefix);
			}			
		},
		find : function(id) {
			if(typeof(this.items[id]) == "undefined")
				return false;
			return this.items[id];
		},
		fillOption: function(el_id , loc_id , selected_id) {
			var el	= $("#"+el_id); 
			var json	= this.find(loc_id); 
			if (json) {
				var index	= 1;
				var selected_index	= 0;
				$.each(json , function(k , v) {
					var option	= '<option value="'+k+'">'+v+'</option>';
					el.append(option);
					if (k == selected_id) {
						selected_index	= index;
					}
					index++;
				})
				//select2中不使用
				//el.attr("selectedIndex" , selected_index); 
			}
			el.select2("val", selected_id||"");
		}
	});

	// 对构造函数的一个轻量级封装，
	// 防止产生多个实例
	$.fn[pluginName] = function(options) {
		this
				.each(function() {
					if (!$.data(this, "plugin_" + pluginName)) {
						$.data(this, "plugin_" + pluginName, new Plugin(this,
								options));
					}
				});

		// 方便链式调用
		return this;
	};
	
	$.fn[pluginName].getSelectedVals = function(selectorPrefix) {
		var provinceObj = $("#"+selectorPrefix+"_province"),
	    cityObj =  $("#"+selectorPrefix+"_city"),
        townObj = $("#"+selectorPrefix+"_town");
	    return provinceObj.val() + " - " + cityObj.val()+ " - " + townObj.val();
	};
	
	$.fn[pluginName].getSelectedVals = function(selectorPrefix) {
		var provinceObj = $("#"+selectorPrefix+"_province"),
		cityObj =  $("#"+selectorPrefix+"_city"),
		townObj = $("#"+selectorPrefix+"_town");
		return provinceObj.select2("data").text + " - "+ cityObj.select2("data").text + " - "+ townObj.select2("data").text;
	};

})(jQuery, window, document);