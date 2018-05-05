<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>趋势分析</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/trendanalysis.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/trendanalysis.js"></script>
    <style>

    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="su_lef fl icon">趋势分析(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="su_rig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart su_icon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt su_icon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="ta_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul ta_ul1 fl">
                <li class="ta_active">今天</li>
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
            <ul class="tj_ul ta_ul2 fl">
                <li class="ta_active">按时</li>
                <li>按日</li>
                <li>按周</li>
                <li>按月</li>
            </ul>
            <span class="fr icon cs ta_btn5"><span class="ta_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="ta_span5">收起筛选</span></span>
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
            <span class="fl ta_span2">设备：</span>
            <ul class="tj_ul fl ta_ul4">
                <li class="ta_active">全部</li>
                <li>计算机</li>
                <li>移动设备</li>
            </ul>
            <span class="fl span_gao ta_riqi">地域：</span><div class="btn-group fl">
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
            <ul class="tj_ul fl ta_ul5">
                <li class="ta_active">全部</li>
                <li>新访客</li>
                <li>老访客</li>
            </ul>
        </div>

    </div>

    <div class="ta_wrap2">
        <div  class="section ta_div2">
            <table class="summary" style="width:100%;">
                <tbody>
                <tr>
                    <td><span class="text">浏览量(PV)<a href="javascript:void(0);" data="pv_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="1,225">1,225</div>
                    </td>
                    <td><span class="text">访客数(UV)<a href="javascript:void(0);" data="visitor_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="924">924</div>
                    </td>
                    <td><span class="text">IP数<a href="javascript:void(0);" data="ip_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="873">873</div>
                    </td>
                    <td><span class="text">跳出率<a href="javascript:void(0);" data="bounce_ratio" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="90.22%">90.22%</div>
                    </td>
                    <td class="last"><span class="text">平均访问时长<a href="javascript:void(0);" data="avg_visit_time" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="00:03:18">00:03:18</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="ta_chart">
            <div class="ta_change">
                <span class="fl span_gao ta_riqi">地域：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default btn_gao">指标：浏览量(PV)</button>
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
            </div>
            <div id="chart_taline1" style="height:300px;"></div>
            <div class="ta_duibi">
                <div class="ta_duibibox">
                    <span class="fl" style="margin-top: 2px">对比：</span>
                    <div class="checkbox fl">
                        <label>
                            <input type="checkbox" checked>前一日
                        </label>
                    </div>
                    <div class="checkbox fl">
                        <label>
                            <input type="checkbox">上周同期
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="ta_chartline cs">
            <span class="icon ta_spanchart"><span class="glyphicon glyphicon-chevron-up"></span></span>
        </div>
        <div id="table">
            <table class="table table-layout-03">
                <thead>
                <tr class="group-title" >
                    <td class="number-col"   colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index sortable desc" data="simple_date_title" id="simple_date_title" colspan="1" rowspan="2">
                        <div class="td-content">日期</div>
                    </td>
                    <td class="group-name2 group" data="2" id="2" colspan="3" rowspan="">
                        <div class="td-content">网站基础指标</div>
                    </td>
                    <td class="group-name3 group" data="3" id="3" colspan="2" rowspan="">
                        <div class="td-content">流量质量指标</div>
                    </td>
                </tr>
                <tr class="group-item" >
                    <td class="number sortable group" data="pv_count" id="pv_count" colspan="" rowspan="">
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
                </tr>
                </thead>
                <tbody>
                <tr class="line" id="table-tr_0">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="1">1</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">16:00 - 16:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">53</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">33</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">29</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">83.33%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:01:30</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_1">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="2">2</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">15:00 - 15:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">204</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">148</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">136</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">90.7%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:01</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_2">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="3">3</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">14:00 - 14:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">190</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">140</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">127</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">91.57%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:10</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_3">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="4">4</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">13:00 - 13:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">124</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">91</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">90</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">91.15%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:04:25</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_4">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="5">5</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">12:00 - 12:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">90</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">66</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">63</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">89.47%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:02:27</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_5">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="6">6</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">11:00 - 11:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">179</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">127</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">126</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">90%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:02:56</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_6">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="7">7</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">10:00 - 10:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">212</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">175</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">166</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">90.48%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:10</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_7">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="8">8</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">09:00 - 09:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">226</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">175</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">167</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">88.95%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:12</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_8">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="9">9</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">08:00 - 08:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">107</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">88</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">74</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">92.63%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:04:12</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_9">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="10">10</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">07:00 - 07:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">10</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">10</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">10</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:01:29</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_10">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="11">11</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">06:00 - 06:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">5</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:01:37</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_11">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="12">12</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">05:00 - 05:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:02:07</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_12">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="13">13</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">04:00 - 04:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">7</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">50%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:00:29</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_13">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="14">14</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">03:00 - 03:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:06:23</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_14">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="15">15</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">02:00 - 02:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">10</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">8</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">7</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">88.89%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:02:26</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_15">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="16">16</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">01:00 - 01:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">7</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">6</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">6</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:04:00</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_16">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="17">17</div>
                    </td>
                    <td class="table-index simple_date_title"   colspan="">
                        <div class="td-content" title="">00:00 - 00:59</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">32</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">14</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">14</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">71.43%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:03</div>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="" >
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="&nbsp;">&nbsp;</div>
                    </td>
                    <td class="summary simple_date_title"   colspan="1">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="">1,459</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="">1,089</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="">1,023</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="">90.26%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="">00:03:15</div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
</html>