var cityInit = ['请选择省份'];
var cityArr = new Array();  

var Search = function(){
	return {
	
		initArea : function(){
		
			var provinceLen = cityArr.length;//共多少个省市自治区
			
			var areaArr = [];
			var provinceArr = [];
			areaArr.push('<div class="sw-ui-area-box"><div class="sw-ui-area-bg"></div><div class="sw-ui-area-body"><div class="sw-ui-area-ab-all">');
			
			areaArr.push('<ul class="sw-ui-area-ab-prov">');//构造省
			for(var i = 0; i< provinceLen; i ++){
				var p = cityArr[i];
				var pArr = new Array();
				var csArr = cityArr[i].child;
				var csLen = csArr.length;
				
				pArr.push('<li class="sw-ui-area-box-item sw-ui-area-abProv-im">');
				pArr.push('<a class="sw-ui-area-box-link sw-ui-area-ab-prov-itemLink " href="javascript:void(0)" data-prov="'+p.id+'" data-city="" data-value="'+p.id+'">'+p.name+'</a>');
				pArr.push('<ul class="sw-ui-area-ab-prov-items">');
			
				for(var j = 0;j < csLen ;j++){//构造市
					var c = csArr[j];
					pArr.push('<li class="sw-ui-area-box-item">');
					pArr.push('<a class="sw-ui-area-box-link sw-ui-area-abProv-itemsubLink " href="javascript:void(0)" data-prov="'+p.id+'" data-city="'+c.id+'" data-value="'+c.id+'">'+c.name+'</a>');
					pArr.push('</li>');
				}
				
				pArr.push('</ul>');
				var pStr = pArr.join("");
				areaArr.push(pStr);
			}//end for
			
			areaArr.push('</ul>');//结束省
			areaArr.push('</div></div></div>');
			var areaStr = areaArr.join("");
			$("#all-area").append(areaStr);
			
		},
		
		//选择地区
		areaEffect : function(){
		
			//显示全部区域及省份
			$("#all-area").hover(function(){
				$(this).find(".sw-ui-area-box").show();
			},function(){
				$(this).find(".sw-ui-area-box").hide();
			});
			
			//显示省级以下的市级城市
			$(".sw-ui-area-box-item").hover(function(){
				$(this).css("z-index","90").find(".sw-ui-area-ab-prov-items").show();
			},function(){
				$(this).css("z-index","0").find(".sw-ui-area-ab-prov-items").hide();
			});
			
		}
		
	}
}();
