<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>忠诚度</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/systemenvironments.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/loyalty.js"></script>
    <style>
    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="sclef fl icon">忠诚度(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="scrig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart scicon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt scicon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="sr_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul fl la_ul1">
                <li class="tj_active">今天</li>
                <li>昨天</li>
                <li>最近7天</li>
                <li>最近30天</li>
            </ul>
            <input type="text" name="reservation" id="pe_time" class="form-control timechange" value="" placeholder="请选择时间段"/>
            <div class="checkbox fl">
                <label>
                    <input type="checkbox">对比时间段
                </label>
            </div>
            <span class="fr icon cs gp_btn5" style="margin-right: 20px"><span class="ta_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="ta_span5">收起筛选</span></span>
        </div>
        <div class="ta_div1">
            <span class="fl span_gao ta_riqi">来源：</span><div class="btn-group fl">
            <button type="button" class="btn btn-default btn_gao">全部</button>
            <button type="button" class="btn btn-default dropdown-toggle btn_gao1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <div class="col-lg-6 tj_div6">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="请输入关键词">
                            <span class="input-group-btn tj_span1">
                                     <button class="btn btn-default vs_btn4" type="button"><span class="glyphicon glyphicon-zoom-in"></span></button>
                                </span>
                        </div>
                    </div>
                </li>
                <li class=""><a href="#">全部</a></li>
                <li><a href="#">全国</a></li>
                <li><a href="#">省自治区</a></li>
                <li><a href="#">其它</a></li>
            </ul>
        </div>
            <span class="fl ta_span2">访客：</span>
            <ul class="tj_ul fl la_ul2">
                <li class="tj_active">全部</li>
                <li>新访客</li>
                <li>老访客</li>
            </ul>
        </div>
    </div>

    <div class="pe_wrap2">
        <div class="tj_navtap">
            <ul class="nav nav-tabs tj_ultap">
                <li role="presentation" class="active"><a href="#">访问次数</a></li>
                <li role="presentation"><a href="#">访问深度</a></li>
                <li role="presentation"><a href="#">访问时长</a></li>
                <li role="presentation"><a href="#">上次访问时间</a></li>
                <li role="presentation"><a href="#">访问频次</a></li>
            </ul>
        </div>
        <div class="section">
            <table class="summary table">
                <tbody>
                <tr>
                    <td><span class="text">访问次数<a href="javascript:void(0);" data="visit_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="670">670</div>
                    </td>
                    <td class="last"><span class="text">所占比例<a href="javascript:void(0);" data="ratio_visit_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="100%">100%</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="se_chart">
            <div class="se_chartbox">
                <div id="chart_sebar" style="height:300px;"></div>
            </div>
            <div class="chartline cs">
                <span class="icon spanchart"><span class="glyphicon glyphicon-chevron-up"></span></span>
            </div>
        </div>
        <div id="table">
            <table class="table table-layout-03">
                <thead>
                <tr class="group-item">
                    <td class="number-col" colspan="1" rowspan="1">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index" data="character_pages" id="character_pages" colspan="1" rowspan="">
                        <div class="td-content">访问页数
                            <a href="javascript:void(0);" data="character_pages" class="help">&nbsp;</a>
                        </div>
                    </td>
                    <td class="number sortable desc" data="visit_count" id="visit_count" colspan="" rowspan="">
                        <div class="td-content">访问次数</div>
                    </td>
                    <td class="ratio" data="ratio_visit_count" id="ratio_visit_count" colspan="" rowspan="">
                        <div class="td-content">所占比例</div>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr class="line" id="table-tr_0">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="1">1</div>
                    </td>
                    <td class="table-index character_pages" colspan="">
                        <div class="td-content" title="">1页</div>
                    </td>
                    <td class="number visit_count" colspan="">
                        <div class="td-content" title="">620</div>
                    </td>
                    <td class="ratio ratio_visit_count" colspan="">
                        <div class="td-content" title="">92.54%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_1">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="2">2</div>
                    </td>
                    <td class="table-index character_pages" colspan="">
                        <div class="td-content" title="">2页</div>
                    </td>
                    <td class="number visit_count" colspan="">
                        <div class="td-content" title="">30</div>
                    </td>
                    <td class="ratio ratio_visit_count" colspan="">
                        <div class="td-content" title="">4.48%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_2">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="3">3</div>
                    </td>
                    <td class="table-index character_pages" colspan="">
                        <div class="td-content" title="">3页</div>
                    </td>
                    <td class="number visit_count" colspan="">
                        <div class="td-content" title="">13</div>
                    </td>
                    <td class="ratio ratio_visit_count" colspan="">
                        <div class="td-content" title="">1.94%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_3">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="4">4</div>
                    </td>
                    <td class="table-index character_pages" colspan="">
                        <div class="td-content" title="">4页</div>
                    </td>
                    <td class="number visit_count" colspan="">
                        <div class="td-content" title="">7</div>
                    </td>
                    <td class="ratio ratio_visit_count" colspan="">
                        <div class="td-content" title="">1.04%</div>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="&nbsp;">&nbsp;</div>
                    </td>
                    <td class="summary character_pages" colspan="1">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number visit_count" colspan="">
                        <div class="td-content" title="">670</div>
                    </td>
                    <td class="ratio ratio_visit_count" colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
</html>