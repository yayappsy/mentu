<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	 <title>系统环境</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/systemenvironments.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/systemenvironments.js"></script>
    <style>

    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="sclef fl icon">系统环境(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="scrig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart scicon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt scicon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="se_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul fl pe_ul1">
                <li data-id="today" class="tj_active searchTime">今天</li>
                <li data-id="yesterday" class="searchTime">昨天</li>
                <li data-id="lastweek" class="searchTime">最近7天</li>
                <li data-id="lastmonth" class="searchTime">最近30天</li>
            </ul>
            
        </div>
    </div>

    <div class="pe_wrap2">
        <div class="tj_navtap">
            <ul class="nav nav-tabs tj_ultap">
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/browser/findSiteBrowser" data-id="1" role="presentation" class="systemType active" ><a href="#" data-id="1">浏览器</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/os/findSiteOs" data-id="2" role="presentation" class="systemType"><a href="#" data-id="2">网络设备类型</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/resolution/findSiteResolution" data-id="3" role="presentation" class="systemType"><a href="#" data-id="3">屏幕分辨率</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/color/findSiteColor" data-id="4" role="presentation" class="systemType"><a href="#" data-id="4">屏幕颜色</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/flashVersion/findSiteFlashVersion" data-id="5" role="presentation" class="systemType"><a href="#" data-id="5">flash版本</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/javaEnabled/findSiteJavaEnabled" data-id="6" role="presentation" class="systemType"><a href="#" data-id="6">是否支持java</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/language/findSiteLanguage" data-id="7" role="presentation" class="systemType"><a href="#" data-id="7">语言环境</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/cookieEnabled/findSiteCookieEnabled" data-id="8" role="presentation" class="systemType"><a href="#" data-id="8">是否支持cookie</a></li>
                <li data-url="http://localhost:8080/yxxueyuan-admin/api/stats/isp/findSiteIsp" data-id="9" role="presentation" class="systemType"><a href="#" data-id="9">网络提供商</a></li>
            </ul>
        </div>
        <div class="section">
            <table class="summary" style="width: 100%;">
                <tbody>
                <tr>
                    <td><span class="text">浏览量(PV)<a href="javascript:void(0);" data="pv_count" class="help">&nbsp;</a></span>
                        <div id="bpageViewCount" class="value summary-ellipsis" title="1,049">1,049</div>
                    </td>
                    <td><span class="text">访客数(UV)<a href="javascript:void(0);" data="visitor_count" class="help">&nbsp;</a></span>
                        <div id="bvisitorCount" class="value summary-ellipsis" title="795">795</div>
                    </td>
                    <td><span class="text">IP数<a href="javascript:void(0);" data="ip_count" class="help">&nbsp;</a></span>
                        <div id="bipCount" class="value summary-ellipsis" title="749">749</div>
                    </td>
                    <td><span class="text">跳出率<a href="javascript:void(0);" data="bounce_ratio" class="help">&nbsp;</a></span>
                        <div id="bbounceRate" class="value summary-ellipsis" title="91.36%">91.36%</div>
                    </td>
                    <td class="last"><span class="text">平均访问时长<a href="javascript:void(0);" data="avg_visit_time" class="help">&nbsp;</a></span>
                        <div id="baverageBrowsePageCount" class="value summary-ellipsis" title="00:03:21">1.14</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="se_chart">
            <div class="chart_list">
                <span class="fl span_gao ta_riqi">指标：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default btn_gao system" data-id="pageViewCount">浏览量（pv）</button>
                <button type="button" class="btn btn-default dropdown-toggle btn_gao1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">          
                    <li><a class="system" href="#" data-id="visitorCount">访客数(UV)</a></li>
                    <li><a class="system" href="#" data-id="ipCount">IP数</a></li>
                    <li><a class="system" href="#" data-id="bounceRate">跳出率</a></li>
                    <li><a class="system" href="#" data-id="averageAccessTime">平均访问时长</a></li>
                    <li><a class="system"href="#" data-id="averageBrowsePageCount">平均访问页数</a></li>
                </ul>
            </div>
            </div>
            <div class="se_chartbox">
                <div id="chart_sebar" style="height:300px;"></div>
            </div>
            <div class="chartline cs">
                <span class="icon spanchart"><span class="glyphicon glyphicon-chevron-up"></span></span>
            </div>
        </div>
        <div id="table">
            <table class="table table-layout-02">
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
                    <td class="table-index" data="client_browser" id="client_browser" colspan="1" rowspan="2">
                        <div class="td-content"><span class="text" id="text_type">浏览器</span>
                            <a href="javascript:void(0)" class="help" data="client_browser"></a>
                        </div>
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
                <tbody id="systemList">
                
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
                    <td class="summary client_browser"   colspan="1">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number group pv_count"   colspan="">
                        <div id="pvCount" class="td-content" title="">1,132</div>
                    </td>
                    <td class="number visitor_count"   colspan="">
                        <div id="visitorCount" class="td-content" title="">854</div>
                    </td>
                    <td class="number ip_count"   colspan="">
                        <div id="ipCount" class="td-content" title="">806</div>
                    </td>
                    <td class="ratio group bounce_ratio"   colspan="">
                        <div id="bounceRate" class="td-content" title="">91.13%</div>
                    </td>
                    <td class="time avg_visit_time"   colspan="">
                        <div id="avgVisitTime" class="td-content" title="">00:03:20</div>
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