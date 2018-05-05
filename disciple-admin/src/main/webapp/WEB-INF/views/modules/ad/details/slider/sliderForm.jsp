<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<style>
<!--
-->
.silderDetail {
	width: 95%;
	float: left;
	min-height: 1px;
	margin-left: 20px;
}

.silderDetail .thumbnail{
	overflow: hidden;
    padding: 35px;
}

.silderDetail .thumbnail .thumbnail-image{
	display: inline-block;
	margin-left: 0px;
    float: left;
    width: 43%;
}

.silderDetail .thumbnail .thumbnail-image img{
    height: 290px;
    width: 540px;
}

.silderDetail .thumbnail .caption {
	display: inline-block;
    width: 49%;
    float: left;
    margin-top: 30px;
}


.small {
	width: 95%;
	background-color: rgba(221, 221, 221, 1);
	margin-top: 10px;
	margin-left: 5px;
	display: block;
	padding: 4px;
	line-height: 20px;
	border: 1px solid #ddd;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.055);
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.055);
	box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.055);
	-webkit-transition: all .2s ease-in-out;
	-moz-transition: all .2s ease-in-out;
	-o-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
}

.silderThumb .thumbnail.active {
	border-color: red;
}
</style>
<div>
	<input type="hidden" id="sliderImages" value="" name="sliderImages" />
	<script type="text/plain" id="sliderImages_container"
		style="height: 5px; display: none;"></script>
	<weimhc:ueimage ueContainer="sliderImages_container"
		selectMultiple="true" type="images" uploadPath="${uploadFolder}"
		input="sliderImages" maxWidth="100" maxHeight="100" customer="true" callback="weimhc.imageCallBack"/>
	
	<span class="help-inline">最多选择${adBasic.adPosition.maxNumber}张图片</span>
</div>

<div id="silderDetails" class="row"></div>

<div id="small" class="row small"></div>

<script type="text/javascript">

$(function(){
	
	//最大图片数量
	var maxNumber = ('${adBasic.adPosition.maxNumber}' == '') ? 0 : ${adBasic.adPosition.maxNumber};
	//已有的图片数量
	var selectedNumber = 0;
	
	function imageCallBack(files){
		var data = {};
		//如果已有的图片数量小于允许图片数量，则可以添加，否则不允许添加图片
		var limit = ((files.length + selectedNumber) <= maxNumber)?files.length:(maxNumber - selectedNumber );
		for(var i=0; i<limit; i++){
			data.path = files[i].url;
			addRow('#silderDetails', propertyValueRowIdx, silderDetailTemplate, data);
			addRow('#small', propertyValueRowIdx, silderThumbTemplate, data);
			propertyValueRowIdx = propertyValueRowIdx + 1;
		}
		showFirstItem();
	}
	
	function showFirstItem(){
		$("#silderDetails").find(".silderDetail:first").show();
		$("#small").find(".silderThumb:first").find(".thumbnail").addClass("active");

		selectedNumber = $("#silderDetails").find(".silderDetail").length;
	}
	
	$("#small").on("click", ".thumbnail", function() {
		var $this = $(this);
		$(".silderDetail").hide();
		$("#" + $this.data("for")).show();
		$(".silderThumb .thumbnail").removeClass("active");
		$this.addClass("active");
	});
	
	var propertyValueRowIdx = 0, silderDetailTemplate = $("#silderDetailTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	var silderThumbTemplate = $("#silderThumbTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");

	var data = ${fns:toJson(adBasic.adDetailList)};
	for (var i=0; i<data.length; i++){
		addRow('#silderDetails', propertyValueRowIdx, silderDetailTemplate, data[i]);
		addRow('#small', propertyValueRowIdx, silderThumbTemplate, data[i]);
		propertyValueRowIdx = propertyValueRowIdx + 1;
	}

	function addRow(list, idx, tpl, row){
		row = row || {sort:propertyValueRowIdx};
		$(list).append(Mustache.render(tpl, {
			idx: idx, delBtn: true, row: row
		}));
		$(list+idx).find("select").each(function(){
			$(this).val($(this).attr("data-value"));
		});
		$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
			var ss = $(this).attr("data-value").split(',');
			for (var i=0; i<ss.length; i++){
				if($(this).val() == ss[i]){
					$(this).attr("checked","checked");
				}
			}
		});
	}
	function delRow(obj, prefix){
		var id = $(prefix+"_id");
		var delFlag = $(prefix+"_delFlag");
		if (id.val() == ""){
			$(obj).parent().parent().remove();
		}else if(delFlag.val() == "0"){
			delFlag.val("1");
			$(obj).html("&divide;").attr("title", message("admin.common.cancelDelete"));
			$(obj).parent().parent().addClass("error");
		}else if(delFlag.val() == "1"){
			delFlag.val("0");
			$(obj).html("&times;").attr("title",message("admin.common.delete"));
			$(obj).parent().parent().removeClass("error");
		}
	}	
	
	showFirstItem();
	
	weimhc.imageCallBack = imageCallBack;
})
</script>
<script type="text/template" id="silderDetailTemplate">
<div class="silderDetail hide" id="silderDetail{{idx}}">
			<input type="hidden" name="adDetailList[{{idx}}].id" value="{{row.id}}">
            <input type="hidden" name="adDetailList[{{idx}}].contentType" value="image">
            <input id="adDetailList{{idx}}_delFlag" name="adDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
            <input id="adDetailList{{idx}}_sort" name="adDetailList[{{idx}}].sort" type="hidden" value="{{idx}}"/>
            <input id="adDetailList{{idx}}_path" name="adDetailList[{{idx}}].path" type="hidden" value="{{row.path}}"/>
			<div class="thumbnail">
			   <div class="thumbnail-image">
				<img
					src="{{row.path}}"
					alt="广告图片">
				</div>	
				<div class="caption">
					<div class="control-group">
						<label class="control-label"><spring:message
								code="AdBasic.title" />：</label>
						<div class="controls">
							<input type="text" class="input-xlarge"
								id="adDetailList{{idx}}_title"
								name="adDetailList[{{idx}}].title" value="{{row.title}}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><spring:message
								code="AdBasic.description" />：</label>
						<div class="controls">
							<textarea id="adDetailList{{idx}}_description"
								name="adDetailList[{{idx}}].description"
								maxlength="255" rows="4" cols="4" class="input-xlarge">{{row.description}}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><spring:message
								code="AdDetail.url" />：</label>
						<div class="controls">
							<input type="text" class="input-xlarge" maxlength="255"
								id="adDetailList{{idx}}_url" value="{{row.url}}"
								name="adDetailList[{{idx}}].url">
							<button id="btnSelectLink" class="btn btn-primary" type="button"
								data-for="adDetailList{{idx}}_url">
								<i class="icon-link"></i>
								<spring:message code='选择' />
							</button>
							<span class="help-inline">可以手动输入广告链接地址，也可以点击选择按钮选择 </span>
						</div>
					</div>
				</div>
			</div>
</div>
</script>
<script type="text/template" id="silderThumbTemplate">
<div class="silderThumb span2">
			<div class="thumbnail"
				data-for="silderDetail{{idx}}">
				<img
					src="{{row.path}}"
					alt="广告图片">
			</div>
		</div>
</script>


