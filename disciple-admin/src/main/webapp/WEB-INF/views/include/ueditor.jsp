<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript" charset="utf-8">
	window.UEDITOR_HOME_URL = "${ctxStatic}/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
</script>
<script type="text/javascript" charset="utf-8" src="${ ctxStatic}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"	src="${ ctxStatic}/ueditor/ueditor.all.js?ver=1.8"></script>
<!--s建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"	src="${ ctxStatic}/ueditor/lang/zh-cn/zh-cn.js?ver=1.3"></script>