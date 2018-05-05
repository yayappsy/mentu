$(function(){
	//定义运费模板主体模板、头模板、单行显示模板
	
	var TransTpl = $("#J_TransTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	//头模板
	var headTpl = $("#J_RuleHeadTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	//单行内容模板
	var ruleCellTpl = $("#J_RuleCellTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	var batchSetTpl = $("#J_BatchSetTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	
	SpecialMessage = '';
	SpecialMessage += "<span  error_type=\"area\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">指定地区城市为空或指定错误</span></span>";
	SpecialMessage += "<span  error_type=\"start\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">首件应输入1至9999的数字</span></span>";
	SpecialMessage += "<span error_type=\"postage\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">首费应输入0.00至999.99的数字</span></span>";
	SpecialMessage += "<span error_type=\"plus\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">续件应输入1至9999的数字</span></span>";
	SpecialMessage += "<span error_type=\"postagePlus\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">续费应输入0.00至999.99的数字</span></span>";

	DefaultMessage = '';
	DefaultMessage += "<span error_type=\"start\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">首件应输入1至9999的数字</span></span>";
	DefaultMessage += "<span error_type=\"postage\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">首费应输入0.00至999.99的数字</span></span>";
	DefaultMessage += "<span error_type=\"plus\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">续件应输入1至9999的数字</span></span>";
	DefaultMessage += "<span error_type=\"postagePlus\" class=\"msg J_Message\" style=\"display:none\"><span class=\"error\">续费应输入0.00至999.99的数字</span></span>";

	/**获取模板需要的数据*/
	function getTplNeedData(){
		var data = {start:1,plus:1,withSpecial:true},
		pricingMode = $(':radio[name="pricingMode"]:checked').val();
		
		data.typeName = message($("#"+pricingMode).data("type-name"));
		data.typeNameExtra = message($("#"+pricingMode).data("type-name-extra"));
		return data;
	}
	/** 切换计价方式或卖家承担运费时，要清除已有的内容，并给出相应的提示 */
	function clearRules(msg,type){
		type = (type == undefined || type == '' ? 'confirm' : type);
		if(type == 'confirm'){
			if(!confirm(msg)){
				return false;
			};
		}else{			
			alert(msg);
		}
		$(".J_PostageDetail").hide().html("");
		$('.J_Delivery').prop("checked",false);
		return true;
	}
	
/*	为指定地区设置运费,增加一行运费规则*/
	$('.J_AddRule').live('click',function (){
		var $checkedPricingMode = $(':radio[name="pricingMode"]:checked');
		var $postageDetail = $(this).parents(".postage-detail");
		//记录当前起始值
		StartNum = $('.tbl-except>table>tbody>tr').size();
		var $tblExcept = $postageDetail.find(".tbl-except");
		if ($tblExcept.html().replace("\n",'')==''){
			$tblExcept.append(Mustache.render(headTpl, $.extend(getTplNeedData(),{
				delivery: $postageDetail.data("delivery"),gid:StartNum})
			));
		}
		cell = Mustache.render(ruleCellTpl, $.extend(getTplNeedData(),{
			delivery: $postageDetail.data("delivery"),gid:StartNum,str:"未添加地区",group:true})
		); 
		$tblExcept.find('table tbody').append(cell);

		/*//如果没有批量操作a链接，则添加
		if ($(this).parent().parent().find('input[type="checkbox"]').css('display') == 'none'){
			$(this).next().show().html('批量操作');
		}else{
			$(this).next().show().html('取消批量');
			$(this).parent().parent().find('.tbl-except').find('.J_BatchField:last').show();
		}*/
	});

/*	平邮、快递、EMS单击事件*/
	$('.J_Delivery').click(function (){
		//如果卖家承担运费选中
		if($(':radio[name="isFreeDelivery"]:checked').val() == "true"){
			return false;
		}
		
		var $checkedPricingMode = $(':radio[name="pricingMode"]:checked');
	    var $postageDetail = $(this).parents(".postage-tpl").find(".postage-detail");
	    var defaultsNum = $('.J_DefaultSet').size();
		if ($(this).prop('checked')){			
			if ($postageDetail.html()==""){
				$postageDetail.append(Mustache.render(TransTpl, $.extend(getTplNeedData(),{
					delivery: $postageDetail.data("delivery"),gid:defaultsNum})
				));
			}
		    $postageDetail.show();
			
			$('p[error_type="trans_type"]').hide();
		}else{
			$postageDetail.hide();
		}
	});

/*	自定义转成整形的方法*/
	jQuery.fn.toInt = function() {
		var s = parseInt(jQuery(this).val().replace( /^0*/,''));
		return isNaN(s) ? 0 : s;
	};

/*	关闭选择区域层*/
	$('.ks-ext-close-x').click(function(){
	    $("#dialog_areas").hide();
	    $("#dialog_batch").hide();
	    $('#ks-ext-mask').hide();
	    return false;
	});

/*	关闭选择区域层*/
	$('.J_Cancel').click(function(){
	    $("#dialog_areas").hide();
	    $("#dialog_batch").hide();
	    $('#ks-ext-mask').hide();
	});	

/*	选择完区域后，确定事件*/
	$('.J_Submit').click(function (){
		var CityText = '', CityText2 = '', CityValue = '';
		//记录已选择的所有省及市的value，SelectArea下标为value值，值为true，如江苏省SelectArea[320000]=true,南京市SelectArea[320100]=true
//		SelectArea = new Array();
		//取得已选的省市的text，返回给父级窗口，如果省份下的市被全选择，只返回显示省的名称，否则显示已选择的市的名称
		//首先找市被全部选择的省份
		$('#J_CityList').find('.ecity').each(function(){
			var a = $(this).find('input[type="checkbox"]').size();
			var b = $(this).find('input:checked').size();
			//市被全选的情况
			if (a == b){
				CityText += ($(this).find('.J_Province').next().html())+',';
			}else{
				//市被部分选中的情况
				$(this).find('.J_City').each(function(){
						//计算并准备传输选择的区域值（具体到市级ID），以，隔开
							if ($(this).attr('checked')){
								CityText2 += ($(this).next().html())+',';
							}
				});
			}
		});
		CityText += CityText2;

		//记录弹出层内所有已被选择的checkbox的值(省、市均记录)，记录到CityValue，SelectArea中
		$('#J_CityList').find('.province-list').find('input[type="checkbox"]').each(function(){
			if ($(this).attr('checked')){
				CityValue += $(this).val()+',';
			}
		});

		//去掉尾部的逗号
		CityText = CityText.replace(/(,*$)/g,'');
		CityValue = CityValue.replace(/(,*$)/g,'');

		//返回选择的文本内容
		if (CityText == '')CityText = '未添加地区';
		
		$(objCurlArea).find('.area-group>p').html(CityText);
		//返回选择的值到隐藏域
		 $('input[name="specials['+curIndex+'].areaNames"').val(CityText);
		 $('input[name="specials['+curIndex+'].areaIds"').val(CityValue);
		//关闭弹出层与遮罩层
	    $("#dialog_areas").hide();
	    $('#ks-ext-mask').hide();
	    //清空check_num显示的数量
		$(".check_num").html('');
		$('#J_CityList').find('input[type="checkbox"]').attr('checked',false);
		//如果该配送方式，地区都不为空，隐藏地区的提示层
		isRemove = true;
		$('div[data-delivery="'+curTransType+'"]').find('input[type="hidden"]').each(function(){
			if ($(this).val()==''){
				isRemove = false;return false;
			}
		});
		if (isRemove == true){
			$('div[data-delivery="'+curTransType+'"]').find('span[error_type="area"]').hide();
		}
	});

/*	省份点击事件*/
	$('.J_Province').click(function(){
		var $ecity = $(this).parents('.ecity');
		if ($(this).attr('checked') == "checked"){
			//选择所有未被disabled的子地区
			$ecity.find('.citys').children().find('input[type="checkbox"]').each(function(){
				if ($(this).attr('disabled') == undefined){
					$(this).attr('checked',true);
				}else{
					$(this).attr('checked',false);
				}
			});
			//计算并显示所有被选中的子地区数量
			num = '('+$ecity.find('.citys').eq(0).find('input:checked').size()+')';
			if (num == '(0)') num = '';
			$ecity.find(".check_num").eq(0).html(num);

			//如果该大区域所有省都选中，该区域选中
			input_checked 	= $(this).parent().parent().parent().find('input:checked').size();
			input_all 		= $(this).parent().parent().parent().find('input[type="checkbox"]').size();
			if (input_all == input_checked){
				$(this).parent().parent().parent().parent().find('.J_Group').attr('checked',true);
			}	

		}else{
			//取消全部子地区选择，取消显示数量
			$ecity.find(".check_num").eq(0).html('');
			$ecity.find('.citys').eq(0).find('input[type="checkbox"]').attr('checked',false);
			//取消大区域选择
			$(this).parent().parent().parent().parent().find('.J_Group').attr('checked',false);
		}
	});

/*	大区域点击事件（华北、华东、华南...）*/
	$('.J_Group').click(function(){
		if ($(this).attr('checked')){
			//区域内所有没有被disabled复选框选中，带disabled说明已经被选择过了，不能再选
			$(this).parent().parent().parent().find('input[type="checkbox"]').each(function(){
				if ($(this).attr('disabled') == undefined){
					$(this).attr('checked',true);
				}else{
					$(this).attr('checked',false);
				}				
			});
			//循环显示每个省下面的市级的数量
			$(this).parent().parent().parent().find('.province-list').find('.ecity').each(function(){
				//显示该省下面已选择的市的数量
				num = '('+$(this).find('.citys').find('input:checked').size()+')';
				//如果是0，说明没有选择，不显示数量
				if (num != '(0)'){
					$(this).find(".check_num").html(num);
				}
			});
		}else{
			//区域内所有筛选框取消选中
			$(this).parent().parent().parent().find('input[type="checkbox"]').attr('checked',false);
			//循环清空每个省下面显示的市级数量
			$(this).parent().parent().parent().find('.province-list').find('.ecity').each(function(){
				$(this).find(".check_num").html('');
			});
		}

	});

/*	关闭弹出的市级小层*/
	$('.close_button').click(function(){ 
	    $(this).parents('.ecity').removeClass('showCityPop');
	});

/*	市级地区单事件*/
	$('.J_City').click(function(){
		//显示选择市级数量，在所属省后面
		num = '('+$(this).parents('.citys').find('input:checked').size()+')';
		if (num=='(0)')num='';
		$(this).parents('.ecity').find(".check_num").eq(0).html(num);
		//如果市级地区全部选中，则父级省份也选中，反之有一个不选中,则省份和大区域也不选中
		if (!$(this).attr('checked')){
			//取消省份选择
			$(this).parents('.ecity').find('.J_Province').attr('checked',false);
			//取消大区域选择
			$(this).parent().parent().parent().parent().parent().parent().find('.J_Group').attr('checked',false);
		}else{
			//如果该省所有市都选中，该省选中
			input_checked 	= $(this).parents('.citys').find('input:checked').size();
			input_all 		= $(this).parents('.citys').find('input[type="checkbox"]').size();
			if (input_all == input_checked){
				$(this).parents('.ecity').find('.J_Province').attr('checked',true);
			}
			//如果该大区域所有省都选中，该区域选中
			input_checked 	= $(this).parent().parent().parent().parent().parent().find('input:checked').size();
			input_all 		= $(this).parent().parent().parent().parent().parent().find('input[type="checkbox"]').size();
			if (input_all == input_checked){
				$(this).parent().parent().parent().parent().parent().parent().find('.J_Group').attr('checked',true);
			}
		}
	});
	
/*	省份下拉事件*/
	$(".trigger").click(function () {
		objTrigger = this;objHead = $(this).parent();
		objPanel = $(this).parents('.ecity').find(".citys");
		if ($(this).parents('.ecity').find(".citys").css('display') == 'none'){
			//隐藏所有已弹出的省份下拉层，只显示当前点击的层
			$('.ks-contentbox').find('.ecity').removeClass('showCityPop');
			$(this).parents('.ecity').addClass('showCityPop');
		}else{
			//隐藏当前的省份下拉层
			$(this).parents('.ecity').removeClass('showCityPop');
		}
		//点击省，市所在的head与panel层以外的区域均隐藏当前层
        var oHandle = $(this);
//        oHandle = document.getElementById($(this).attr('id'));//不兼容Ie8,废弃
		var de = document.documentElement?document.documentElement : document.body;
        de.onclick = function(e){
	        var e = e || window.event;
	        var target = e.target || e.srcElement;
	        var getTar = target.getAttribute("id");
	        while(target){
	        	//循环最外层一个时，会出现异常
				try{
					//jquery 转成DOM对象，比较两个DOM对象
	                if(target==$(objHead)[0])return true;
	                if(target==$(objPanel)[0])return true;
	                //暂不考虑使用ID比较
//	                if(target.getAttribute("id")==$(objHead).attr('id'))return true;
//	                if(target.getAttribute("id")==$(objPanel).attr('id'))return true;
				}catch(ex){};
	            target = target.parentNode;
	        }
	        $(objTrigger).parents('.ecity').removeClass('showCityPop');
        }
	});

	/*	选择运送区域*/
	$('a[entype="J_EditArea"]').live('click',function () {
		curTransType = $(this).parents(".postage-tpl").find(".postage-detail").data("delivery");
		//取消所以已选择的checkbox
		$('#J_CityList').find('input[type="checkbox"]').attr('checked',false);//.attr('disabled','');
	
		//取消显示所有统计数量
		$('#J_CityList').find('.check_num').html('');

		//记录当前行的标识n1,n2,n3....
		curIndex = $(this).parent().parent().data('group');

		//记录当前操作的行，选择完地区会向该区域抛出值
		objCurlArea = $(this).parent();
	
		//记录已选择的所有省及市的value，SelectArea下标为value值，值为true，如江苏省SelectArea[320000]=true,南京市SelectArea[320100]=true
		SelectArea = new Array();

		//取得当前行隐藏域内的city值，放入SelectArea数组中
		var expAreaIds = $('input[name="specials['+curIndex+'].areaIds"').val();
		
		var expAreas = expAreaIds.split(',');
		try{
			if(expAreas[0] != ''){
				for(var v in expAreas){
					SelectArea[expAreas[v]] = true;
				}
			}
	
			//初始化已选中的checkbox
			$('#J_CityList').find('.ecity').each(function(){
				var count = 0;
				$(this).find('input[type="checkbox"]').each(function(){
					if(SelectArea[$(this).val()]==true){
						$(this).attr('checked',true);
						if($(this)[0].className!='J_Province') count++;
					}
				});
				if (count > 0){
					$(this).find('.check_num').html('('+count+')');
				}
	
			});

			//循环每一行，如果一行省都选中，则大区载选中
			$('#J_CityList>li').each(function(){
				$(this).find('.J_Group').attr('checked',true);
				father = this;
				$(this).find('.J_Province').each(function(){
					if (!$(this).attr('checked')){
						$(father).find('.J_Group').attr('checked',false);
						return ;
					}
				});
			});
		}catch(ex){}
		//其它行已选择的地区，不能再选择了
		$(objCurlArea).parent().parent().find('.area-group').each(function(){
			if ($(this).next().attr('name') != 'areas['+curTransType+']['+curIndex+']'){
				expAreas = $(this).next().val().split('|||');
				expAreas = expAreas[0].split(',');
				//重置SelectArea
				SelectArea = new Array();
				try{
					if(expAreas[0] != ''){
						for(var v in expAreas){
							SelectArea[expAreas[v]] = true;
						}
					}

					//其它行已选中的在这里都置灰
					$('#J_CityList').find('input[type="checkbox"]').each(function(){
						if(SelectArea[$(this).val()]==true){
							$(this).attr('disabled','disabled').attr('checked',false);
						}
					});
					//循环每一行，如果一行的省都被disabled，则大区域也disabled
					$('#J_CityList>li').each(function(){
						$(this).find('.J_Group').attr('disabled','disabled');
						father = this;
						$(this).find('.J_Province').each(function(){
							if (!$(this).attr('disabled')){
								$(father).find('.J_Group').attr('disabled','');
								return ;
							}
						});
					});				
				}catch(ex){}
			}
		});
		//定位弹出层的坐标
		var pos = $(this).position();
		var pos_x = pos.left-250;
		var pos_y = pos.top+20;
		$("#dialog_areas").css({'left' : pos_x, 'top' : pos_y,'position' : 'absolute','display' : 'block'});
		 $("#ks-ext-mask").css({"height":$(document).height(),"width":$(document).width()}).show();   
	
	});

	$('#title').blur(function(){
		if ($(this).val() !=''){
			$('p[error_type="title"]').hide();
		}
	});

	/*	首费离开校验*/
	$('input[data-field="postage"]').live('blur',function (){
		var oNum = new Number($(this).val());
		oNum = oNum.toFixed(2);
		if (oNum > 999.99) oNum = 999.99;
		if (oNum=='NaN') oNum = '0.00'; 
		$(this).val(oNum);
		if($(this)[0].className=='w50 mr5 input-error') $(this).removeClass('input-error');
		if($(this)[0].className=='w50 ml5 mr5 input-error') $(this).removeClass('input-error');
		
		if($(this).parent()[0].className=='default J_DefaultSet'){
			//首费不为空了，如果是默认的首费，隐藏提示层span
			$(this).parent().find('.J_DefaultMessage').find('span[error_type="postage"]').hide();
		}else{
			//如果是动态添加的首费,当所有首费输入框都不为空时，提示层span隐藏
			isRemove = true;
			$(this).parent().parent().parent().find('input[data-field="postage"]').each(function(){
				if ($(this).val()==''){
					isRemove = false;return false;
				}
			});
			//提示层span隐藏
			if (isRemove == true){
				$(this).parent().parent().parent().parent().parent().parent().find('.tbl-attach').find('.J_SpecialMessage').find('span[error_type="postage"]').hide();
			}
		}
	});

	/*	续费离开校验*/
	$('input[data-field="postagePlus"]').live('blur',function (){
		var oNum = new Number($(this).val());
		oNum = oNum.toFixed(2);
		if (oNum > 999.99) oNum = 999.99;
		if (oNum=='NaN') oNum = '0.00';
		$(this).val(oNum);
		if($(this)[0].className=='w50 mr5 input-error') $(this).removeClass('input-error');
		if($(this)[0].className=='w50 ml5 mr5 input-error') $(this).removeClass('input-error');

		if($(this).parent()[0].className=='default J_DefaultSet'){
			//续费不为空了，如果是默认的首费，隐藏提示层span
			$(this).parent().find('.J_DefaultMessage').find('span[error_type="postagePlus"]').hide();
		}else{
			//如果是动态添加的首费,当所有续费输入框都不为空时，提示层span隐藏
			isRemove = true;
			$(this).parent().parent().parent().find('input[data-field="postagePlus"]').each(function(){
				if ($(this).val()==''){
					isRemove = false;return false;
				}
			});
			//提示层span隐藏
			if (isRemove == true){
				$(this).parent().parent().parent().parent().parent().parent().find('.tbl-attach').find('.J_SpecialMessage').find('span[error_type="postagePlus"]').hide();
			}
		}			
	});

	/*	续件离开校验*/
	$('input[data-field="plus"]').live('blur',function (){
		if ($(this).val() != ''){
			$(this).val($(this).toInt());
		}else{
			$(this).val(1);
		}
	});
	
	/*	首件离开校验*/
	$('input[data-field="start"]').live('blur',function (){
		if ($(this).val() != ''){
			$(this).val($(this).toInt());
		}else{
			$(this).val(1);
		}
	});
	
	/*	删除一行运费规则*/
	$('.J_DeleteRule').live('click',function (){
		if (!confirm('确认删除吗?')) return false;
		curTransType = $(this).parent().parent().parent().find('input[type="hidden"]').eq(0).attr('name').substring(6,8);
		obj_parent = $(this).parent().parent().parent();
		$(this).parent().parent().remove();
		if ($(obj_parent).find('tr').html() == null){
			$(obj_parent).parent().parent().parent().find('.batch').hide();
			$(obj_parent).parent().parent().parent().find('.J_ToggleBatch').hide();
			$(obj_parent).parent().parent().parent().find('.batch').next().find('span').hide();
		}else{
			//如果该配送方式，地区都不为空，隐藏地区的提示层
			isRemove = true;
			$('div[data-delivery="'+curTransType+'"]').find('input[type="hidden"]').each(function(){
				if ($(this).val()==''){
					isRemove = false;return false;
				}
			});
			if (isRemove == true){
				$('div[data-delivery="'+curTransType+'"]').find('span[error_type="area"]').hide();
			}			
		}
	});
	
	/*批量操作*/
	$('.J_ToggleBatch').live('click',function(){
		var $batch = $(this).parents('.entity').find('.batch');
		if ($batch.css('display')=='none'){
			$batch.show();
			$batch.find('.J_BatchCheck').prop("checked",false);
			$(this).parents('.entity').find('.J_BatchField').show().prop("checked",false);
			$(this).html('取消批量');	
		}else{
			$batch.hide();
			$(this).parents('.entity').find('.J_BatchField').hide();
			$(this).html('批量操作');	
		}
	});
	
	/*运费规则单行复选框事件*/
	$('.J_BatchField').live('click',function(){
		if (!$(this).prop('checked')){
			obj_parent = $(this).parents('.entity');
			$(obj_parent).find('.J_BatchCheck').prop('checked',false);
		}else{
			obj_tbody = $(this).parents('.entity');
			checkbox_count = $(obj_tbody).find('.J_BatchField').size();
			checked_count = $(obj_tbody).find('input:checked').size();
			if (checkbox_count == checked_count){
				obj_parent = $(this).parents('.entity');
				$(obj_parent).find('.J_BatchCheck').prop('checked',true);			
			}
		}
	});
	
	/*批量设置全选*/
	$('.J_BatchCheck').live('click',function(){
		$('.J_BatchField').prop('checked',$(this).prop('checked'));
	});
	
	/*批量设置弹出层*/
	$('.J_BatchSet').live('click',function(){
		if($('.tbl-except').find('input:checked').size()==0){
			alert('请选择要批量设置的地区');return false;
		}
		//定义当前的父级框，来区分是在EXPRESS,EMS,POST哪种弹出的
		curTrans = $(this).parent().parent();
		//定位弹出层的坐标
		var pos = $(this).position();
		var pos_x = pos.left-20;
		var pos_y = pos.top+20;
		$("#dialog_batch").html(Mustache.render(batchSetTpl, {}));
		
		$("#dialog_batch").css({'left' : pos_x, 'top' : pos_y,'position' : 'absolute','display' : 'block'});
		$('#ks-ext-mask').show();	
	});
	
	/*批量删除*/
	$('.J_BatchDel').live('click',function(){
		if($(this).parent().parent().find('.tbl-except').find('input:checked').size()==0){
			alert('请选择要批量处理的地区');return false;
		}
		if (!confirm('确认批量删除吗')){
			return false;
		}
		
		$(this).parent().parent().find('.tbl-except>table>tbody>tr').each(function(){
			if ($(this).find('.J_BatchField').attr('checked')){
				$(this).remove();
			}
		});
		if ($(this).parent().parent().find('table>tbody>tr').html() == null){
			$(this).parent().parent().parent().find('.batch').hide();
			$(this).parent().parent().parent().find('.J_ToggleBatch').hide();
			$(this).parent().parent().parent().find('.batch').next().find('span').hide();		
		}else{
			curTransType = $(this).parent().prev().find('input[type="hidden"]').eq(0).attr('name').substring(6,8);
			//如果该配送方式，地区都不为空，隐藏地区的提示层
			isRemove = true;
			$('div[data-delivery="'+curTransType+'"]').find('input[type="hidden"]').each(function(){
				if ($(this).val()==''){
					isRemove = false;return false;
				}
			});
			if (isRemove == true){
				$('div[data-delivery="'+curTransType+'"]').find('span[error_type="area"]').hide();
			}			
		}
		
	});
	
	/*批量设置页面提交事件*/
	$('.J_SubmitPL').live('click',function(){
		var obj_this = $(this).parent().parent(); 
		$(curTrans).find('.tbl-except>table>tbody>tr').each(function(){
			if ($(this).find('.J_BatchField').attr('checked')){
				$(this).find('input[data-field="start"]').val($(obj_this).find('input[data-field="start"]').val()).removeClass('input-error');
				$(this).find('input[data-field="postage"]').val($(obj_this).find('input[data-field="postage"]').val()).removeClass('input-error');
				$(this).find('input[data-field="plus"]').val($(obj_this).find('input[data-field="plus"]').val()).removeClass('input-error');
				$(this).find('input[data-field="postagePlus"]').val($(obj_this).find('input[data-field="postagePlus"]').val()).removeClass('input-error');
			}
		});
	    $("#dialog_batch").hide();
	    $('#ks-ext-mask').hide();	
	});
	$(':radio[name="isFreeDelivery"]').click(function(){
		var msg = '';
		if($(this).val() == "true"){
			msg = '选择“卖家承担运费”，所有区域设置的运费将被设置为0且原运费设置不能恢复，是否继续？';
			clearRules(msg,"confirm");
		}else{
			msg = '您的运费设置将变为未设置状态，请设置运费';
			clearRules(msg,"alert");
		}
	});
	$(':radio[name="pricingMode"]').click(function(){
		var msg = "切换计价方式之后，所设置的当前模板的运输信息将被清空，确认继续吗？";
		/*如果选择不清楚，则返回*/
		if(!clearRules(msg)){
		   return false;
		};
	});
	/*保存运费模板*/
	$('#submit_tpl').click(function(){
		$('.J_SpecialMessage').html(SpecialMessage);
		$('.J_DefaultSet').find('.J_DefaultMessage').html(DefaultMessage);		
		
		//如果选择卖家承担运费，则不进行校验
		if($(':radio[name="isFreeDelivery"]:checked').val() == "true"){
			return true;
		}
			
		//请至少选择一种运送方式
		i=0;
		$('.J_Delivery').each(function(){
			if (!$(this).prop('checked')){
				i++;
			}
		});
		if (i==3){	
			$('p[data-error_type="trans_type"]').show();
			return false;
		}
		
		$('.postage-tpl').each(function(){
			//如果复选框选中了，才判断
			if (!$(this).find('.J_Delivery').prop('checked')){
				$(this).find("input").prop("disabled",true);
				return true;
			}
			//首件跟续件由于有默认值，鼠标离开也有默认值，这里只需判断首费与续费即可

			//首费JS空判断-------------------------------
			father = this;
			//只判断已显示的，即只判断EMS、平邮、快递中已选择的内容
			var obj = $(this).find('.J_DefaultSet').find('input[data-field="postage"]');
			if($(obj).val() != 'undefined'){
				isShowError = false;
				if($(obj).val()==''){
					$(obj).addClass('input-error'); isShowError = true; isSubmit = false;
				}else{
					$(this).removeClass('input-error');
				}

				if (isShowError){
					$(this).find('.J_DefaultSet').find('span[error_type="postage"]').show();
				}				
			}
			//续费JS空判断-------------------------------
			//只判断已显示的，即只判断EMS、平邮、快递中已选择的内容
			var obj = $(this).find('.J_DefaultSet').find('input[data-field="postagePlus"]');
			if($(obj).val() != 'undefined'){
				isShowError = false;
				if($(obj).val()==''){
					$(obj).addClass('input-error'); isShowError = true; isSubmit = false;
				}else{
					$(this).removeClass('input-error');
				}
				if (isShowError){
					$(this).find('.J_DefaultSet').find('span[error_type="postagePlus"]').show();
				}				
			}
			//地区空判断-------------------------------
			//只判断已显示的，即只判断EMS、平邮、快递中已选择的内容
			if($(this).find('.tbl-except').find('.cell-area').html() != null){
				isShowError = false;
				$(this).find('.tbl-except').find('tr').each(function(){
					if($(this).find('input[type="hidden"]').val()==''){
						isShowError = true; isSubmit = false; return false;
					}
				});
				if (isShowError){
					$(father).find('.tbl-attach').find('span[error_type="area"]').show();
				}
			}		
			//首费JS空判断-------------------------------
			//只判断已显示的，即只判断EMS、平邮、快递中已选择的内容
			if($(father).find('.tbl-except').find('.cell-area').html() != null){
				isShowError = false;
				$(this).find('.tbl-except').find('input[data-field="postage"]').each(function(){
					if ($(this).val()==''){
						$(this).addClass('input-error');isShowError = true; isSubmit = false;
					}
				});

				if (isShowError){
					$(father).find('.tbl-attach').find('span[error_type="postage"]').show();
				}
			}
			//续费JS空判断-------------------------------
			//只判断已显示的，即只判断EMS、平邮、快递中已选择的内容
			if($(father).find('.tbl-except').find('.cell-area').html() != null){
				isShowError = false;
				$(this).find('.tbl-except').find('input[data-field="postagePlus"]').each(function(){
					if ($(this).val()==''){
						$(this).addClass('input-error'); isShowError = true; isSubmit = false;
					}
				});

				if (isShowError){
					$(father).find('.tbl-attach').find('span[error_type="postagePlus"]').show();
				}
			}			

		});
	});
	//拼接字符串
	function getTranJsonStr(){
		var transportExtendListStr = "[";
		$("[name='tplType[]']:checked").each(function(x,v){
			//默认的运费
			var transportExtendDefStr = "{";
			var tranType = $(this).val();
			var $tranDiv = $("#"+tranType); 
			//默认运费
			var defStart = $("[name='default["+tranType+"][start]']").val();
			//默认起始价格
			var defPostage = $("[name='default["+tranType+"][postage]']").val();
			//每增加件数
			var defPlus = $("[name='default["+tranType+"][plus]']").val();
			//增加运费
			var defPostageplus = $("[name='default["+tranType+"][postagePlus]']").val();
			transportExtendDefStr += "\"type\":\"" + tranType + "\",\"snum\":\"" + defStart + "\",\"sprice\":\"" + defPostage + "\",\"xnum\":\"" + defPlus + "\",\"xprice\":\"" + defPostageplus + "\"";
			transportExtendDefStr += "},";
			transportExtendListStr += transportExtendDefStr;
			//运费特例
			$tranDiv.find("[rulecellgroup]").each(function(x,y){
				var transportExtendOthStr = "{";
				var num = $(this).attr("rulecellgroup");
				var area = $(this).find("[name='areas["+tranType+"]["+num+"]']").val();
				var areaArr = area.split("|||");
				//地区id
				var areaId = "," + areaArr[0] + ",";
				//地区名字
				var areaName = areaArr[1];
				//运费
				var othStart = $("[name='special["+tranType+"]["+num+"][start]']").val();
				//起始价格
				var othPostage = $("[name='special["+tranType+"]["+num+"][postage]']").val();
				//每增加件数
				var othPlus = $("[name='special["+tranType+"]["+num+"][plus]']").val();
				//增加运费
				var othPostageplus = $("[name='special["+tranType+"]["+num+"][postagePlus]']").val();
				transportExtendOthStr += "\"type\":\"" + tranType + "\",\"areaId\":\"" + areaId + "\",\"areaName\":\"" + areaName + "\",\"snum\":\"" + othStart + "\",\"sprice\":\"" + othPostage + "\",\"xnum\":\"" + othPlus + "\",\"xprice\":\"" + othPostageplus + "\"";
				transportExtendOthStr += "},";
				transportExtendListStr += transportExtendOthStr;
			});
		});
		if(transportExtendListStr != "["){
			transportExtendListStr = transportExtendListStr.substring(0,transportExtendListStr.length-1);
			return transportExtendListStr + "]";
		}else{
			return "";
		}
	}
});
