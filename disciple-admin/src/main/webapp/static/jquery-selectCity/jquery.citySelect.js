/*
Ajax 三级省市联动
http://code.ciaoca.cn/
日期：2012-7-18

settings 参数说明
-----
url:省市数据josn文件路径
prov:默认省份
city:默认城市
dist:默认地区（县）
nodata:无数据状态
required:必选项
------------------------------ */
(function($) {
    $.fn.citySelect = function(settings) {
        if (this.length < 1) {
            return;
        };

        // 默认值
        settings = $.extend({
            url: "/static/jquery-selectCity/city.min.js",
            prov: null,
            city: null,
            dist: null,
            parentIds:null,
            nodata: null,
            required: true
        },
        settings);

        var box_obj = this;
        var prov_obj = box_obj.find(".prov");
        var city_obj = box_obj.find(".city");
        var dist_obj = box_obj.find(".dist");
        var prov_val = settings.prov;
        var city_val = settings.city;
        var dist_val = settings.dist;
        var select_prehtml = (settings.required) ? "": "<option value=''>请选择</option>";
        var city_json;

        // 赋值市级函数
        var cityStart = function() {
            var prov_id = prov_obj.get(0).selectedIndex;
            // console.log(prov_id);
            if (!settings.required) {
                prov_id--;
            };
            city_obj.empty().attr("disabled", true);
            dist_obj.empty().attr("disabled", true);

            if (prov_id < 0 || typeof(city_json.cityList[prov_id].child) == "undefined") {
                if (settings.nodata == "none") {
                    city_obj.css("display", "none");
                    dist_obj.css("display", "none");
                } else if (settings.nodata == "hidden") {
                    city_obj.css("visibility", "hidden");
                    dist_obj.css("visibility", "hidden");
                };
                return;
            };
             //console.log(prov_id);
            // 遍历赋值市级下拉列表
            temp_html = select_prehtml;
            $.each(city_json.cityList[prov_id].child,
            function(i, city) {
                temp_html += "<option value='" + (city.id || city.name) + "'>" + city.name + "</option>";
            });
            city_obj.html(temp_html).attr("disabled", false).css({
                "display": "",
                "visibility": ""
            });
            distStart();
        };

        // 赋值地区（县）函数
        var distStart = function() {
            var prov_id = prov_obj.get(0).selectedIndex;
            var city_id = city_obj.get(0).selectedIndex;
            if (!settings.required) {
                prov_id--;
                city_id--;
            };
            dist_obj.empty().attr("disabled", true);

            if (prov_id < 0 || city_id < 0 || typeof(city_json.cityList[prov_id].child[city_id].child) == "undefined") {
                if (settings.nodata == "none") {
                    dist_obj.css("display", "none");
                } else if (settings.nodata == "hidden") {
                    dist_obj.css("visibility", "hidden");
                };
                return;
            };

            // 遍历赋值市级下拉列表
            temp_html = select_prehtml;
            $.each(city_json.cityList[prov_id].child[city_id].child,
            function(i, dist) {
                temp_html += "<option value='" + (dist.id || dist.name) + "'>" + dist.name + "</option>";
            });
            dist_obj.html(temp_html).attr("disabled", false).css({
                "display": "",
                "visibility": ""
            });
        };

        var init = function() {
        	var areas;
            // 遍历赋值省份下拉列表
            temp_html = select_prehtml;
            $.each(city_json.cityList,
            function(i, prov) {
                //console.log(prov);
                temp_html += "<option value='" + (prov.id || prov.name) + "'>" + prov.name + "</option>";
            });
            prov_obj.html(temp_html);
            //如果parentIds有值，则使用,数据库中存储的路径是0,100000,110000,110100,110101
            //世界、国家、省（直辖市）、地区、县
            if(settings.parentIds){
            	areas=  settings.parentIds.split(",");
            	settings.prov = areas[2];
            	settings.city = areas[3];
            }
            // 若有传入省份与市级的值，则选中。（setTimeout为兼容IE6而设置）
            setTimeout(function() {
                if (settings.prov != null) {
                    prov_obj.val(settings.prov);
                    cityStart();
                    setTimeout(function() {
                        if (settings.city != null) {
                            city_obj.val(settings.city);
                            distStart();
                            setTimeout(function() {
                                if (settings.dist != null) {
                                    dist_obj.val(settings.dist);
                                };
                            },
                            1);
                        };
                    },
                    1);
                };
            },
            1);

            // 选择省份时发生事件
            prov_obj.bind("change",
            function() {
                cityStart();
            });

            // 选择市级时发生事件
            city_obj.bind("change",
            function() {
                distStart();
            });
        };

        // 设置省市json数据
        if (typeof(settings.url) == "string") {
            //console.log(true);
            //console.log(settings.url);
            $.getJSON(settings.url,
            function(json) {
                city_json = json;
                //	console.log(city_json);
                init();
            });
        } else {
            city_json = settings.url;
            init();
        };
    };
    $.fn.citySelect.getSelectVals = function(box_obj){
    	console.log(box_obj);
    	return box_obj.find(".prov").val() + ' - ' + box_obj.find(".city").val() + ' - ' +  box_obj.find(".dist").val();
    }
    $.fn.citySelect.getProv = function(box_obj){
    	return box_obj.find(".prov").val();
    }
    $.fn.citySelect.getCity = function(box_obj){
    	return box_obj.find(".city").val();
    }
    $.fn.citySelect.getDist = function(box_obj){
    	return box_obj.find(".dist").val();
    }
})(jQuery);