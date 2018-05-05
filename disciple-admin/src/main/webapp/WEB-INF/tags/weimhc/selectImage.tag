<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true"
	description="输入框,图片路径实际存储位置"%>
<%@ attribute name="selectMultiple" type="java.lang.Boolean"
	required="false" description="是否允许多选"%>	
<%@ attribute name="readonly" type="java.lang.Boolean" required="false"
	description="是否查看模式"%>	
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
	//<c:if test="${!readonly}">	
	function ${input}FinderOpen() {
		layer.open({
			type : 2,
			btn: ['保存', '取消'],
			yes:function(index,layero){
				var $layerIframe = $(layero).find("#layui-layer-iframe"+index);
				var selectData = [];
				$layerIframe.contents().find("input[name='id']:checked").each(function(){
					selectData.push($(this).data());
				});
				if(selectData.length > 0){
					var imageData = null,url=null;
					//<c:if test="${selectMultiple}">
					for(var i=0;i<selectData.length;i++){
						imageData = selectData[i];
						console.log(imageData);
						if(imageData.pathType == 'local'){
							url = imageData.storagePath;
						}else if(imageData.pathType == 'remote'){
							url = imageData.url;
						}
						$("#${input}").val($("#${input}").val()+($("#${input}").val(url)==""?url:url+"|"));
					}
					//</c:if><c:if test="${!selectMultiple}">
					imageData = selectData[0];
					if(imageData.pathType == 'local'){
						url = imageData.storagePath;
					}else if(imageData.pathType == 'remote'){
						url = imageData.url;
					}
					$("#${input}").val(url);
					//</c:if>
				}
				layer.close(index);
				//<c:if test="${!customer}">	
				${input}Preview();
				//</c:if>
			},
			area : [ '800px', '600px' ],
			shadeClose : true, //点击遮罩关闭
			content : '${ctx}/image/image/list?searchType=selectLink&isMultiple=${selectMultiple}'
		});
	}
	//</c:if>	
	//<c:if test="${!customer}">	
	function ${input}Preview(){
		var transTpl = $("#${input}ImageTemplate").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
		var urls = $("#${input}").val().split("|"),thumbnails=[];
		urls = removeEmptyItem(urls);//去除空行
		var thumbnail;
		for(var i=0; i<urls.length; i++){
			thumbnails.push(getThumbnailUrl(urls[i],100,100));
		}
		$("#${input}Preview").children().remove();
		for (var i=0; i<thumbnails.length; i++){
			$("#${input}Preview").append(
				Mustache.render(transTpl, 
					{thumbnail:thumbnails[i],url:getImageUrl(urls[i]),readonly:${!!readonly}})
			);
		}
		if ($("#${input}Preview").text() == ""){
			$("#${input}Preview").html("<li style='list-style:none;padding-top:5px;'>无</li>");
		}
	}
	${input}Preview();
	//<c:if test="${!readonly}">		
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
	//</c:if>	
	//</c:if>	
</script>