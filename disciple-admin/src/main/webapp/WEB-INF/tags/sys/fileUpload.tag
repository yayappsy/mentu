<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true" description="输入框"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="files、images、flash、thumb"%>
<%@ attribute name="uploadPath" type="java.lang.String" required="true" description="打开文件管理的上传路径"%>
<%@ attribute name="selectMultiple" type="java.lang.Boolean" required="false" description="是否允许多选"%>
<%@ attribute name="readonly" type="java.lang.Boolean" required="false" description="是否查看模式"%>
<%@ attribute name="maxWidth" type="java.lang.String" required="false" description="最大宽度"%>
<%@ attribute name="maxHeight" type="java.lang.String" required="false" description="最大高度"%>
<ol id="${input}Preview"></ol><c:if test="${!readonly}"><a href="javascript:" onclick="${input}FinderOpen();" class="btn">${selectMultiple?'添加':'选择'}</a>&nbsp;<a href="javascript:" onclick="${input}DelAll();" class="btn">清除</a></c:if>
<script type="text/javascript">
/* $('#fileupload').fileupload({
autoUpload : true,
dataType : 'json',
singleFileUploads: false,
formData:{"uploadFolder":"logo"},
done : function(e, data) {
    console.log(data.result);
    $('#logo').val(data.result.filePath);
    $('#logoImg').attr("src",ctx+data.result.filePath);
},
 progressall: function (e, data) {
    var progress = parseInt(data.loaded / data.total * 100, 10);
    $('#progress .bar').css(
        'width',
        progress + '%'
    );
}, 

}); */
/* $('#fileupload').fileupload({
dataType: 'json',
// Enable image resizing, except for Android and Opera,
// which actually support image resizing, but fail to
// send Blob objects via XHR requests:
disableImageResize: /Android(?!.*Chrome)|Opera/
    .test(window.navigator && navigator.userAgent),
previewMaxWidth: 800,
previewMaxHeight: 800,
previewCrop: true ,// Force cropped images
add: function (e, data) {
    data.context = $('<p/>').text('Uploading...').appendTo(document.body);
    console.log(data);
    data.submit();
},
}).on('fileuploadprocessalways', function (e, data) {
var index = data.index,
    file = data.files[index],
    node = $(data.context.children()[index]);
if (file.preview) {
  node
    .prepend('<br/>')
    .prepend(file.preview)
}
console.log(12312);
if (file.error) {
  node
    .append('<br/>')
    .append($('<span class="text-danger"/>').text(file.error));
}
}); */
uploadImageAjaxDelete = function (url,obj){
$.ajax({url:url,async:false,dataType:"text",success:function(data){
      if(data=='1'){
	       //如果删除成功，恢复file的使用，同时是图片渐变消失
	      $(obj).parent().children("input[type='file']").removeAttr("disabled");
		  $(obj).parent().children("img").fadeOut("slow",function(){
	            $(this).add($(obj).parent().children("a")).add($(obj).parent().children("input:hidden")).add($(obj).parent().children("br")).remove();
	      });  
	  }else{
	      alert('图片删除失败！');
	  }
}});
}

$("input[type='file']").fileupload({
dataType: 'json',
autoUpload: true,
singleFileUploads:false,
acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
maxFileSize: 204800,// 2 MB
maxNumberOfFiles:1,
messages: {
    maxNumberOfFiles: 'maxNumberOfFiles',
    acceptFileTypes: 'acceptFileTypes',
    maxFileSize: 'maxFileSize',
    minFileSize: 'minFileSize'
}
}).on('fileuploadadd', function (e, data) {
  $(this).parent().children("label").remove();
   $("<p class='uploadImgLoad'>上传中... 0%</p>").appendTo($(this).parent());
 // $(this).attr("disabled",true);
}).on('fileuploadprocessalways', function (e, data) {
console.log(data.files);
if(data.files.error){
   $(this).parent().children("p").remove();
   $(this).removeAttr("disabled");
   if(data.files[0].error=='acceptFileTypes'){
       $(this).parent().append("<label class='error'>图片类型错误</label>");
   }
   if(data.files[0].error=='maxFileSize'){
      $(this).parent().append("<label class='error'>图片不能大于2M</label>");
   }  
} 
}).on('fileuploadprogressall', function (e, data) {
//console.log(data);
var $p = $(this).parent().children("p");
var progress = parseInt(data.loaded / data.total * 100, 10);
if($p.length==0){
    $("<p class='uploadImgLoad'>上传中... "+progress+"%</p>").appendTo($(this).parent());
}else{
   console.info(progress);
   $p.text('上传中... '+progress+'%');
   if(progress==100){
      $p.fadeOut("slow",function(){
	     $(this).remove();
	  });
   }
} 
}).on('fileuploaddone', function (e, data) {
console.log(data);
  console.log($(this));
  if(data.result.result=='error'){
     $(this).removeAttr("disabled");
     alert('抱歉，上传过快，请稍等！');
  }else if(data.result.result=='success'){
    $(this).parent().prepend($("<a href='#' >  删除</a>").attr("onclick","uploadImageAjaxDelete('image_ajax_delete.action?dbFileName="+data.result.dbFileName+"',this)").add("<br/>"))
	.prepend($("<img  width='140' height='90' border='0' />").attr("src",data.result.url))
	.prepend($("<input type='hidden' name="+data.result.hiddenName+" id="+data.result.hiddenName+" value='"+data.result.dbFileName+"' />"));
  }
});
</script>