<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static" />
<html>
<head>
<title>完整demo</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8">
	window.UEDITOR_HOME_URL = "${ ctxStatic}/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
</script>
<script type="text/javascript" charset="utf-8"
	src="${ ctxStatic}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ ctxStatic}/ueditor/ueditor.all.min.js"></script>

<!--s建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="${ ctxStatic}/ueditor/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
div {
	width: 100%;
}
</style>
</head>
<body>
	<script type="text/plain" id="j_ueditorupload"
		style="height: 5px; display: none;"></script>
	<script>
		//实例化编辑器
		var ued = UE.getEditor('j_ueditorupload', {
			autoHeightEnabled : false
		});
		ued.ready(function() {

			ued.hide();//隐藏编辑器

			//监听图片上传
			ued.addListener('beforeInsertImage', function(t, arg) {
				alertx('这是图片地址：' + arg[0].src);
			});

			/* 文件上传监听
			 * 需要在ueditor.all.min.js文件中找到
			 * d.execCommand("insertHtml",l)
			 * 之后插入d.fireEvent('afterUpfile',b)
			 */
			ued.addListener('afterUpfile', function(t, arg) {
				alertx('这是文件地址：' + arg[0].url);
			});
		});
		//弹出图片上传的对话框
		function upImage() {
			var myImage = UE.getEditor('j_ueditorupload').getDialog(
					"insertimage");
			myImage.open();
		}
		//弹出文件上传的对话框
		function upFiles() {
			var myFiles = ued.getDialog("attachment");
			myFiles.open();
		}
	</script>

	<button type="button" onClick="upImage()">调用上传图片模块</button>
	<br>
	<button type="button" onClick="upFiles()">调用上传文件模块</button>
    <iframe name="image"></iframe>
</body>
</html>