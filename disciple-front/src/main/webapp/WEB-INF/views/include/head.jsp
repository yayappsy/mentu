<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

<script src="${ctxStatic}/jquery-validation/1.14.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.14.0/additional-methods.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.14.0/localization/messages_${languageType}.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/jquery.validate.method.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-validation/jquery.validate.min.css" type="text/css" rel="stylesheet" />

<script src="${ctxStatic}/jquery-form/3.51.0/jquery.form.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins2/Blue/jbox.css" rel="stylesheet" />
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctxStatic}/mustache/mustache.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-selectCity/jquery.citySelect.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-tableCheckbox/jquery.tableCheckbox.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8"	src="${ctxStatic }/jquery-raty/2.7/lib/jquery.raty.js"></script>
<link href="${ctxStatic }/jquery-raty/2.7/lib/jquery.raty.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxStatic}/common/common.js"></script>
<script type="text/javascript" charset="utf-8">
	window.UEDITOR_HOME_URL = "${ ctxStatic}/ueditor/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
</script>
<script type="text/javascript" charset="utf-8" src="${ ctxStatic}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"	src="${ ctxStatic}/ueditor/ueditor.all.js"></script>
<!--s建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"	src="${ ctxStatic}/ueditor/lang/zh-cn/zh-cn.js"></script>
<link href="${ctxStatic}/modules/center/css/top.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxStatic}/common/top.js"></script>
<%--有H-ui打包 --%>
<link href="${ctxStatic}/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<%--直接从http://www.iconfont.cn下载的 --%>
<link href="${ctxStatic}/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/bootstrap/3.4.1/bootstrap-part.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="${ctxStatic}/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctxStatic}/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';weimhc.base=ctx;</script>

