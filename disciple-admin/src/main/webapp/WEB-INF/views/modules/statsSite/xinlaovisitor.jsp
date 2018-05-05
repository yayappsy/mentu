<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	 <title>新老访客</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/xinlaovisitor.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/xinlaovisitor.js"></script>
    <style>

    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="sclef fl icon">新老访客(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="scrig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart scicon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt scicon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="sr_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul fl xl_ul1">
                <li data-id="today" class="tj_active searchTime">今天</li>
                <li data-id="yesterday" class="searchTime">昨天</li>
                <li data-id="lastweek" class="searchTime">最近7天</li>
                <li data-id="lastmonth" class="searchTime">最近30天</li>
            </ul>
       
            <span class="fr icon cs gp_btn5" style="margin-right: 20px"><span class="ta_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="ta_span5">收起筛选</span></span>
        </div>
   
    </div>

    <div class="pe_wrap2">
        <div class="li_div1">
            <div class="newvisitor fl">
                <div class="visitorbox">
                    <table cellpadding="0" cellspacing="0" class="visitor new-visitor" width="100%">
                        <tbody>
                        <tr>
                            <td class="visitor-logo" align="right">
                                <div class="title">新访客</div><img src="${ctxStatic}/biaozhun/css/img/xl_log1.gif"></td>
                            <td class="v ratio" id="newVisitorRate">
                                86.41%
                            </td>
                        </tr>
                        <tr>
                            <td style="height:15px;" colspan="2"></td>
                        </tr>
                        <tr>
                            <td class="k">浏览量：</td>
                            <td class="v" id="nPageViewCount">1016</td>
                        </tr>
                        <tr>
                            <td class="k">访客数：</td>
                            <td class="v" id="nVisitorCount">776</td>
                        </tr>
                        <tr>
                            <td class="k">跳出率：</td>
                            <td class="v" id="nBounceRate">90.73%</td>
                        </tr>
                        <tr>
                            <td class="k">平均访问时长：</td>
                            <td class="v" id="nAverageAccessTime">00:03:26</td>
                        </tr>
                        <tr>
                            <td class="k">平均访问页数：</td>
                            <td class="v" id="nAverageBrowsePageCount">1.17</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="newvisitor fr">
                <div class="visitorbox">
                    <table cellpadding="0" cellspacing="0" class="visitor old-visitor" width="100%">
                        <tbody>
                        <tr>
                            <td class="visitor-logo" align="right">
                                <div class="title">老访客</div><img src="${ctxStatic}/biaozhun/css/img/xl_log2.gif"></td>
                            <td class="v ratio" id="oldVisitorRate">
                                13.05%
                            </td>
                        </tr>
                        <tr>
                            <td style="height:15px;" colspan="2"></td>
                        </tr>
                        <tr>
                            <td class="k">浏览量：</td>
                            <td class="v" id="oPageViewCount">178</td>
                        </tr>
                        <tr>
                            <td class="k">访客数：</td>
                            <td class="v" id="oVisitorCount">125</td>
                        </tr>
                        <tr>
                            <td class="k">跳出率：</td>
                            <td class="v" id="oBounceRate">94.23%</td>
                        </tr>
                        <tr>
                            <td class="k">平均访问时长：</td>
                            <td class="v" id="oAverageAccessTime">00:03:04</td>
                        </tr>
                        <tr>
                            <td class="k">平均访问页数：</td>
                            <td class="v" id="oAverageBrowsePageCount">1.1</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="li_div1">
            <div class="oldvisitor fl">
                <div class="new-visitor-table-title">访问来源网站 <strong style="color: #00ABEF;">TOP</strong> 5</div>
                <table class="table new-visitor-dg table-layout-01" cellpadding="0" cellspacing="0">
                    <tbody id="sourceSiteList">
                   
                    </tbody>
                </table>
            </div>
            <div class="oldvisitor fr">
                <div class="old-visitor-table-title">访问来源网站 <strong style="color: #9A9A9A;">TOP</strong> 5</div>
                <table class="table old-visitor-dg table-layout-01" cellpadding="0" cellspacing="0">
                    <tbody id="sourceSiteoOldList">
                    
                    </tbody>
                </table>
            </div>
        </div>
        <div class="li_div1">
            <div class="oldvisitor fl">
                <div class="new-visitor-table-title">访问来源网站 <strong style="color: #00ABEF;">TOP</strong> 5</div>
                <table class="table new-visitor-dg table-layout-01" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr class="th nob">
                        <td class="c">排名</td>
                        <td class="url">入口页链接</td>
                        <td class="tdr">访问次数</td>
                    </tr>
                    <tr>
                        <td class="c">1</td>
                        <td title="http://editor.baidu.com" class="url">
                            <a target="_blank" href="http://editor.baidu.com">http://editor.baidu.com</a>
                        </td>
                        <td class="tdr">912</td>
                    </tr>
                    <tr>
                        <td class="c">2</td>
                        <td title="http://editor.baidu.com/?qq-pf-to=pcqq.c2c" class="url">
                            <a target="_blank" href="http://editor.baidu.com/?qq-pf-to=pcqq.c2c">http://editor.baidu.com/?qq-pf-to=pcqq.c2c</a>
                        </td>
                        <td class="tdr">19</td>
                    </tr>
                    <tr>
                        <td class="c">3</td>
                        <td title="http://editor.baidu.com/function" class="url">
                            <a target="_blank" href="http://editor.baidu.com/function">http://editor.baidu.com/function</a>
                        </td>
                        <td class="tdr">9</td>
                    </tr>
                    <tr>
                        <td class="c">4</td>
                        <td title="http://editor.baidu.com/search" class="url">
                            <a target="_blank" href="http://editor.baidu.com/search">http://editor.baidu.com/search</a>
                        </td>
                        <td class="tdr">6</td>
                    </tr>
                    <tr>
                        <td class="c">5</td>
                        <td title="http://editor.baidu.com/account" class="url">
                            <a target="_blank" href="http://editor.baidu.com/account">http://editor.baidu.com/account</a>
                        </td>
                        <td class="tdr">5</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="oldvisitor fr">
                <div class="old-visitor-table-title">访问来源网站 <strong style="color: #9A9A9A;">TOP</strong> 5</div>
                <table class="table old-visitor-dg table-layout-01" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr class="th nob">
                        <td class="c">排名</td>
                        <td class="url">入口页链接</td>
                        <td class="tdr">访问次数</td>
                    </tr>
                    <tr>
                        <td class="c">1</td>
                        <td title="http://editor.baidu.com" class="url">
                            <a target="_blank" href="http://editor.baidu.com">http://editor.baidu.com</a>
                        </td>
                        <td class="tdr">132</td>
                    </tr>
                    <tr>
                        <td class="c">2</td>
                        <td title="http://editor.baidu.com/?_t_t_t=0.5967946627642959" class="url">
                            <a target="_blank" href="http://editor.baidu.com/?_t_t_t=0.5967946627642959">http://editor.baidu.com/?_t_t_t=0.5967946627642959</a>
                        </td>
                        <td class="tdr">30</td>
                    </tr>
                    <tr>
                        <td class="c">3</td>
                        <td title="http://www.test.com" class="url">
                            <a target="_blank" href="http://www.test.com">http://www.test.com</a>
                        </td>
                        <td class="tdr">3</td>
                    </tr>
                    <tr>
                        <td class="c">4</td>
                        <td title="http://editor.baidu.com/?qq-pf-to=pcqq.c2c" class="url">
                            <a target="_blank" href="http://editor.baidu.com/?qq-pf-to=pcqq.c2c">http://editor.baidu.com/?qq-pf-to=pcqq.c2c</a>
                        </td>
                        <td class="tdr">2</td>
                    </tr>
                    <tr>
                        <td class="c">5</td>
                        <td title="http://editor.baidu.com/?qq-pf-to=pcqq.group" class="url">
                            <a target="_blank" href="http://editor.baidu.com/?qq-pf-to=pcqq.group">http://editor.baidu.com/?qq-pf-to=pcqq.group</a>
                        </td>
                        <td class="tdr">1</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="pe_wrap2">
        <div id="table">
            <table class="table table-layout-03">
                <thead>
                <tr class="group-title" >
                    <td class="number-col"   colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index" data="visit_type_title" id="visit_type_title" colspan="1" rowspan="2">
                        <div class="td-content">新老访客</div>
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
                </tr>
                </thead>
                <tbody>
                <tr class="line" id="table-tr_0">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="1">1</div>
                    </td>
                    <td class="table-index visit_type_title" data="[object Object]"  colspan="">
                        <div class="td-content" title="">新访客</div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="0" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="" id="vnPageViewCount">1,124</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="" id="vnVisitorCount">863</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="" id="vnIpCount">779</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="" id="vnBounceRate">90.58%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="" id="vnAverageAccessTime">00:03:23</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_1">
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="2">2</div>
                    </td>
                    <td class="table-index visit_type_title" data="[object Object]"  colspan="">
                        <div class="td-content" title="">老访客</div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="1" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="" id="onPageViewCount">188</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="" id="onVisitorCount">131</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="" id="onIpCount">125</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="" id="onBounceRate">93.71%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="" id="onAverageAccessTime">00:03:03</div>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="" >
                    <td class="number-col"   colspan="">
                        <div class="td-content" title="&nbsp;">&nbsp;</div>
                    </td>
                    <td class="summary visit_type_title"   colspan="2">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div class="td-content" title="" id="pageViewCount">1,312</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div class="td-content" title="" id="visitorCount">994</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div class="td-content" title="" id="ipCount">904</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div class="td-content" title="" id="bounceRate">91.06%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div class="td-content" title="" id="averageAccessTime">00:03:20</div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>
</html>