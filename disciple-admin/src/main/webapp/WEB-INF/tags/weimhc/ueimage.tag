<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true"
	description="输入框,图片路径实际存储位置"%>
<%@ attribute name="ueContainer" type="java.lang.String" required="true"
	description="构造隐藏编辑器需要的文本域"%>
<%@ attribute name="uploadPath" type="java.lang.String" required="true"
	description="打开文件管理的上传路径"%>
<%@ attribute name="type" type="java.lang.String" required="false" 
	description="files、images、flash、thumb"%>
<%@ attribute name="selectMultiple" type="java.lang.Boolean"
	required="false" description="是否允许多选"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false"
	description="是否查看模式"%>
<%@ attribute name="maxWidth" type="java.lang.String" required="false"
	description="最大宽度"%>
<%@ attribute name="maxHeight" type="java.lang.String" required="false"
	description="最大高度"%>
<%@ attribute name="customer" type="java.lang.Boolean" required="false"
	description="是否自定义回调函数，如果是callback必须指定"%>
<%@ attribute name="callback" type="java.lang.String" required="false"
	description="自定义回调函数"%>
<c:if test="${ type eq null or type eq ''}">
<c:set var="type" value="images"/>
</c:if>
<ol id="${input}Preview" class="imagesPreview thumbnails"></ol>
<c:if test="${!readonly}">
    <button type="button" onClick="${input}FinderOpen();" class="btn">${selectMultiple?'添加图片':'选择图片'}</button>
	&nbsp;
	<button type="button" onclick="${input}DelAll();" class="btn">清除</button>
</c:if>
<script type="text/tempalte" id="${input}ImageTemplate">
<li>
   <div class="">
        <img src="{{thumbnail}}" data-image-url="{{url}}"
			onclick="showBigImage(this.dataset.imageUrl)"
			height="100" width="100">
		{{^readonly}}
       	<a href='javascript:' onclick='${input}Del(this);'>×</a>
		{{/readonly}}
    </div>
</li>
</script>
<script>
	//实例化编辑器
	var ued${ueContainer} = UE.getEditor('${ueContainer}', {
		autoHeightEnabled : false,selectMultiple:${selectMultiple?true:false},
		toolbars: [[
            'insertimage', 'insertremoteimage','insertuploadimage'
        ]]
	});
	ued${ueContainer}.ready(function() {
		ued${ueContainer}.hide(); //隐藏编辑器
		//监听图片上传
		ued${ueContainer}.addListener('beforeinsertimage', function(t, arg) {
			//<c:if test="${!customer}">	
			var url="",files=arg;
			for(var i=0; i<files.length; i++){
				//<c:if test="${type eq 'thumb'}">
				url += files[i]._src;				
				//</c:if><c:if test="${type ne 'thumb'}">
				url += files[i]._src;				
				//</c:if>
				if (i<files.length-1) {
					url+="|";
				}
			}
			//<c:if test="${selectMultiple}">
			$("#${input}").val($("#${input}").val()+($("#${input}").val(url)==""?url:url+"|"));
			//</c:if><c:if test="${!selectMultiple}">
			$("#${input}").val(url);
			//</c:if>
			${input}Preview();
			//</c:if>
			$("#${input}").trigger("change",files);
			//<c:if test="${customer}">
			${callback}(arg);
			//</c:if>
		});
		/* 文件上传监听
		 * 需要在ueditor.all.min.js文件中找到
		 * d.execCommand("insertHtml",l)
		 * 之后插入d.fireEvent('afterUpfile',b)
		 */
		ued${ueContainer}.addListener('afterUpfile', function(t, arg) {
			console.log(arg[0].url);
		});
		ued${ueContainer}.execCommand('serverparam', {
			'uploadFolder' : '${uploadPath}',
			'maxWidth' : '${maxWidth}',
			'maxHeight' : '${maxHeight}',
			'type' : '${type}',
		});
	});
	//弹出图片上传的对话框
	function ${input}FinderOpen() {
		var myImage = ued${ueContainer}.getDialog("insertuploadimage");
		myImage.open();
	}
	//<c:if test="${!customer}">	
	function ${input}Preview(){
		var transTpl = $("#${input}ImageTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var li, urls = $("#${input}").val().split("|"),thumbnails=[];
		urls = removeEmptyItem(urls);//去除空行
		for(var i=0; i<urls.length; i++){
			thumbnails.push(getThumbnailUrl(urls[i],100,100));
		}
		$("#${input}Preview").children().remove();
		for (var i=0; i<thumbnails.length; i++){
			$("#${input}Preview").append(Mustache.render(transTpl, 
					{thumbnail:thumbnails[i],url:getImageUrl(urls[i]),readonly:${!!readonly}})
					);
		}
		if ($("#${input}Preview").text() == ""){
			$("#${input}Preview").html("<li style='list-style:none;padding-top:5px;'>无</li>");
		}
	}	
	//删除图片
	function ${input}Del(obj){
		var $prev = $(obj).prev();
		var url = $prev.attr("url");
		$("#${input}").val($("#${input}").val().replace("|"+url,"","").replace(url+"|","","").replace(url,"",""));
		${input}Preview();
	}
	//清除图片
	function ${input}DelAll(){
		$("#${input}Preview").empty();
		$("#${input}").val("");
	}
	${input}Preview();	
	//</c:if>	
</script>