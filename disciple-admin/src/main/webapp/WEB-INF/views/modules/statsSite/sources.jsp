<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全部来源</title>
    <meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/sources.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/sources.js"></script>
    <style>

    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="sclef fl icon">全部来源(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="scrig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart scicon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt scicon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="sr_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul sr_ul1 fl">
                <li data-id="today" class="tj_active searchTime">今天</li>
                <li data-id="yesterday" class="searchTime">昨天</li>
                <li data-id="lastweek" class="searchTime">最近7天</li>
                <li data-id="lastmonth" class="searchTime">最近30天</li>
            </ul>
            <span class="fr icon cs sr_btn5"><span class="ta_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="ta_span5">收起筛选</span></span>
    </div>
       
    </div>

    <div class="sr_wrap2">
        <div  class="section ta_div2">
            <table class="summary" style="width:100%;">
                <tbody>
                <tr>
                    <td><span class="text">浏览量(PV)<a href="javascript:void(0);" data="pv_count" class="help">&nbsp;</a></span>
                        <div id="s_pv_count" class="value summary-ellipsis" title="1,225">1,225</div>
                    </td>
                    <td><span class="text">访客数(UV)<a href="javascript:void(0);" data="visitor_count" class="help">&nbsp;</a></span>
                        <div id="s_visitor_count" class="value summary-ellipsis" title="924">924</div>
                    </td>
                    <td><span class="text">IP数<a href="javascript:void(0);" data="ip_count" class="help">&nbsp;</a></span>
                        <div id="s_ip_count" class="value summary-ellipsis" title="873">873</div>
                    </td>
                    <td><span class="text">跳出率<a href="javascript:void(0);" data="bounce_ratio" class="help">&nbsp;</a></span>
                        <div id="s_bounce_rate" class="value summary-ellipsis" title="90.22%">90.22%</div>
                    </td>
                    <td class="last"><span class="text">平均访问时长<a href="javascript:void(0);" data="avg_visit_time" class="help">&nbsp;</a></span>
                        <div id="s_avg_visit_time" class="value summary-ellipsis" title="00:03:18">00:03:18</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="ta_chart">
            <div class="ta_change">
                <span class="fl span_gao ta_riqi">地域：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default btn_gao source" data-id="1">指标：浏览量(PV)</button>
                <button type="button" class="btn btn-default dropdown-toggle btn_gao1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">           
                    <li class="source" data-id="2"><a href="#">访客数(UV)</a></li>
                    <li class="source" data-id="3"><a href="#">IP数</a></li>
                    <li class="source" data-id="4"><a href="#">跳出率</a></li>
                    <li class="source" data-id="5"><a href="#">平均访问时长</a></li>
                    <li class="source" data-id="6"><a href="#">平均访问页数</a></li>
                </ul>
            </div>
            </div>
        </div>
        <div class="sr_div1">
            <div class="sr_div2 fl">
                <div id="chart_srpie1" style="height:300px;"></div>
            </div>
            <div class="sr_div3 fl">
                <div id="chart_srline1" style="height:300px;"></div>
            </div>
        </div>
        <div class="ta_chartline cs sr_chartbtn chartline">
            <span class="icon ta_spanchart"><span class="glyphicon glyphicon-chevron-up"></span></span>
        </div>
        <div id="table">
            <table class="table table-layout-02 table-layout-09">
                <thead>
                <tr class="group-title" >
                    <td class="empty-col"   colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="no-expand-col"   colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="number-col"   colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index url" data="source_type_title" id="source_type_title" colspan="1" rowspan="2">
                        <div class="td-content">来源类型</div>
                    </td>
                    <td class="no-operate-col"   colspan="" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="group-name2 group" data="2" id="2" colspan="3" rowspan="">
                        <div class="td-content">网站基础指标</div>
                    </td>
                    <td class="group-name3 group" data="3" id="3" colspan="2" rowspan="">
                        <div class="td-content">流量质量指标</div>
                    </td>
                    <td class="empty-col last"   colspan="" rowspan="">
                        <div class="td-content">&nbsp;</div>
                    </td>
                </tr>
                <tr class="group-item" >
                    <td class="number sortable desc group" data="pv_count" id="pv_count" colspan="" rowspan="">
                        <div class="td-content">浏览量(PV)</div>
                    </td>
                    <td class="number sortable" data="visitor_count" id="visitor_count" colspan="" rowspan="">
                        <div class="td-content">访客数(UV)</div>
                    </td>
                    <td class="number sortable" data="ip_count" id="ip_count" colspan="" rowspan="">
                        <div class="td-content">IP数</div>
                    </td>
                    <td class="ratio sortable group" data="bounce_ratio" id="bounce_ratio" colspan="" rowspan="">
                        <div class="td-content">跳出率</div>
                    </td>
                    <td class="time sortable" data="avg_visit_time" id="avg_visit_time" colspan="" rowspan="">
                        <div class="td-content">平均访问时长</div>
                    </td>
                    <td class="empty-col last"   colspan="" rowspan="">
                        <div class="td-content">&nbsp;</div>
                    </td>
                </tr>
                </thead>
                <tbody id="sourceList">
               
                </tbody>
                <tfoot>
                <tr class="" >
                    <td class="empty-col" data="-1"  colspan="">
                        <div class="td-content" title="">&nbsp;</div>
                    </td>
                    <td class="no-expand-col" data="-1" id="expand_table-tr_-1" colspan="">
                        <div class="td-content" title="">&nbsp;</div>
                    </td>
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="&nbsp;">&nbsp;</div>
                    </td>
                    <td class="summary source_type_title"   colspan="2">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div id="spvCount" class="td-content" title="">1,725</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div id="svisitorCount" class="td-content" title="">1,282</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div id="sipCount" class="td-content" title="">1,211</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div id="sbounceRate" class="td-content" title="">90.81%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div id="savgVisitTime" class="td-content" title="">00:03:14</div>
                    </td>
                    <td class="empty-col last"   colspan="">
                        <div class="td-content" title="">&nbsp;</div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
       
    </div>
</div>
</body>
</html>