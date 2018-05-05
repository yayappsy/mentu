<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="false"
	description="输入框,暂时不使用"%>
<%@ attribute name="ueContainer" type="java.lang.String" required="true"
	description="构造隐藏编辑器需要的文本域"%>
<%@ attribute name="uploadPath" type="java.lang.String" required="true"
	description="打开文件管理的上传路径"%>
<%@ attribute name="宽度" type="java.lang.String" required="false"
	description="打开文件管理的上传路径"%>
<%@ attribute name="initialFrameHeight" type="java.lang.String" required="false"
	description="高度"%>
<script type="text/javascript">
	var ued${ueContainer} = UE.getEditor('${ueContainer}', {
		autoHeight : false,
		initialFrameWidth : 740,
		initialFrameHeight : 400,
		toolbars : [['fullscreen', 'source', 'preview', '|', 'undo', 'redo',
				'|', 'bold', 'italic', 'underline', 'fontborder',
				'strikethrough', 'superscript', 'subscript', 'removeformat',
				'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',
				'forecolor', 'backcolor', 'insertorderedlist',
				'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
				'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
				'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft',
				'imageright', 'imagecenter', '|', 'simpleupload',
				'insertimage', '|', 'inserttable', 'deletetable',
				'insertparagraphbeforetable', 'insertrow', 'deleterow',
				'insertcol', 'deletecol', 'mergecells', 'mergeright',
				'mergedown', 'splittocells', 'splittorows', 'splittocols',
				'charts', '|', 'searchreplace', 'help']],
		autoHeightEnabled : false,
		autoFloatEnabled : false
	});
	ued${ueContainer}.ready(function() {
		ued${ueContainer}.execCommand('serverparam', {
			'uploadFolder' : '${uploadPath}'
		});
	});
</script>