<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/survey.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/survey.js"></script>
    <script src="${ctxStatic}/biaozhun/res/echarts/china.js"></script>

    <style>
        .top{
            width:100%;
            padding:12px 20px;
            overflow: hidden;
            -webkit-box-shadow: 0 1px 3px rgba(0,0,0,.1);
            box-shadow: 0 1px 3px rgba(0,0,0,.1);
            background: #fff;
        }
        .su_lef{
            font-weight: bold;
        }
        .glyphicon{
            margin:0 3px;
        }
        .su_rig{
            width:150px;
        }
        .su_select{
            width:98%;
            overflow: hidden;
            margin:13px 1%;

        }
        .su_div1{
            width:100%;
            padding:10px 1%;
            background: #fff;
        }
        .su_div3{
            margin:18px 0;
            height:32px;
            width:100%;
            background: white;
        }
        .su_wrap1{
            width:100%;
            overflow: hidden;
        }
        .su_wrap2{
            width:100%;
            overflow: hidden;
            margin-top: 20px;
        }
        .su_div4{
            width:49%;
            background: white;
            height:390px;
            padding:10px;
        }
        .su_div5{
            width:49%;
            height:390px;
            background: white;
            padding:10px;
        }
        .su_div_8{
            width:100%;
            margin: 20px 0;
            overflow: hidden;
        }

    </style>

   
   
</head>
<div class="selectbox">
    <div class="top">
        <div class="su_lef fl icon">网站情况<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="su_rig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart su_icon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt su_icon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>
    <div class="su_select">
        <div class="su_div1">
            <div class="table-list" id="IndexPreview">
                <div class="overview-card-title-container"><label class="overview-card-title">今日流量</label></div>
                <table cellspacing="0" cellpadding="0" class="list" id="IndexPreviewTableList">
                    <tbody>
                    <tr class="title">
                        <th></th>
                        <th>浏览量(PV)</th>
                        <th>访客数(UV)</th>
                        <th>IP数</th>
                        <th>跳出率</th>
                        <th>平均访问时长</th>
                        <th>转化次数</th>
                        <th class="empty-tr0"></th>
                    </tr>
                    <tr class="highlight">
                        <td class="normal">今日</td>
                        <td id="todayPageViewCount" class="">1064</td>
                        <td id="todayVisitorCount" class="">431</td>
                        <td id="todayIpCount"  class="">394</td>
                        <td id="todayBounceRate" class="">89.8%</td>
                        <td id="todayAverageAccessTime" class="">00:03:25</td>
                        <td class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr>
                        <td class="normal">昨日</td>
                        <td id="yesterDayPageViewCount"class="">2,407</td>
                        <td id="yesterDayVisitorCount"class="">1,797</td>
                        <td id="yesterDayIpCount"class="">1,546</td>
                        <td id="yesterDayBounceRate"class="">89.14%</td>
                        <td id="yesterDayAverageAccessTime"class="">00:03:12</td>
                        <td id=""class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr>
                        <td class="normal">预计今日</td>
                        <td class="arrow-down">2,060</td>
                        <td class="arrow-down">1,487</td>
                        <td class="arrow-down">1,360</td>
                        <td class="">--</td>
                        <td class="">--</td>
                        <td class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr class="empty-tr1"></tr>
                    <tr class="fade empty-tr2">
                        <td colspan="9"></td>
                    </tr>
                    <tr class="su_hide">
                        <td class="normal">昨日此时</td>
                        <td class="">666</td>
                        <td class="">521</td>
                        <td class="">448</td>
                        <td class="">90.12%</td>
                        <td class="">00:03:00</td>
                        <td class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr class="su_hide">
                        <td class="normal">每日平均</td>
                        <td class="">1,676</td>
                        <td class="">1,253</td>
                        <td class="">1,145</td>
                        <td class="">90.34%</td>
                        <td class="">00:03:10</td>
                        <td class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr class="su_hide">
                        <td class="normal">历史峰值</td>
                        <td d="2017年04月10日" title="峰值日：2017/04/10" class="highlight-tip">2,407</td>
                        <td d="2017年04月10日" title="峰值日：2017/04/10" class="highlight-tip">1,797</td>
                        <td d="2017年04月10日" title="峰值日：2017/04/10" class="highlight-tip">1,546</td>
                        <td d="2017年04月01日" title="峰值日：2017/04/01" class="highlight-tip">91.78%</td>
                        <td d="2017年03月27日" title="峰值日：2017/03/27" class="highlight-tip">00:03:29</td>
                        <td class="">--</td>
                        <td class="empty-tr0"></td>
                    </tr>
                    <tr class="su_hide empty-tr3">
                        <td colspan="9"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="su_btn1 cs"><span class="icon su_icon2"><i class="glyphicon glyphicon-chevron-down"></i></span></div>
        </div>
        <div class="su_div3">
            <ul class="su_ul1">    
                <li data-id="today" class="suactive searchTime">今天</li>
                <li data-id="yesterday" class="searchTime">昨天</li>
                <li data-id="lastweek" class="searchTime">最近7天</li>
                <li data-id="lastmonth" class="searchTime">最近30天</li>
            </ul>
        </div>
        <div class="su_wrap1">
            <div class="su_div4 fl">
                <div class="su_div6"><span class="su_span1 fl">趋势图</span><span class="icon fr cs"><i class="glyphicon glyphicon-chevron-right"></i></span></div>
                <div class="su_nav2">
                    <div id="pageViewCount" data-id="1" class="su_li2 stats su_li3 suactive">浏览量(pv)</div>
                    <div id="visitorCount" data-id="2" class="su_li2 stats">访客数(uv)</div>
                    <div class="su_li2 su_li4">其它</div>
                    <ul class="su_ul2">
                        <li class="stats" id="3" data-id="ipCount">IP数</li>
                        <li class="stats" id="4" data-id="bounceRate">跳出率</li>
                        <li class="stats" id="5" data-id="averageAccessTime">平均访问时长</li>
                        <li class="stats" id="6" data-id="conversionsCount">转化次数</li>
                    </ul>
                    <div class="su_div7">
                        <span class="fl">对比：</span>
                        <span class="checkbox fl">
                            <label>
                                <input id="time" name="time" data-value="yesterDay" type="radio" checked> 前一日
                            </label>
                        </span>
                        <span class="checkbox fl">
                            <label>
                                <input id="time" name="time" data-value="lastWeek" type="radio"> 上周同期
                            </label>
                        </span>
                    </div>
                </div>

                <div class="su_div8">
                    <div id="chart_line1" style="height:300px;"></div>
                </div>
            </div>
            <div class="su_div5 fr">
                <div class="su_div6"><span class="su_span1 fl">Top10搜索词</span><span class="icon fr cs"><i class="glyphicon glyphicon-chevron-right"></i></span></div>
                <table  cellpadding="0" cellspacing="0" class="table-layout-01">
                    <thead>
                    <tr>
                        <td class="al url">搜索词</td>
                        <td class="ar" width="20%">浏览量(PV)</td>
                        <td class="ratio" width="20%">占比</td>
                    </tr>
                    </thead>
                    <tbody id="searchKeywordlist">
                    
                    </tbody>
                </table>
            </div>
        </div>
        <div class="su_wrap2">
            <div class="su_div4 fl">
                <div class="su_div6"><span class="su_span1 fl">地域分布</span><span class="icon fr cs"><i class="glyphicon glyphicon-chevron-right"></i></span></div>
                <div id="chart_map" style="height:360px;"></div>
            </div>

            <div class="su_div5 fr">
                <div class="su_div6"><span class="su_span1 fl">新老访客</span><span class="icon fr cs"><i class="glyphicon glyphicon-chevron-right"></i></span></div>
                <div class="su_div_8">
                    <div class="su_div9 su_div10"><img src="../css/img/visit-type-icon.png" alt=""></div>
                    <div class="su_div9">
                        <p class="su_p2">新访客</p>
                        <p class="su_p3 su_p4"><span id="newRate" class="su_span2">85.39</span><span class="su_span3">%</span></p>
                    </div>
                    <div class="su_div9" style="border: none;">
                        <p class="su_p2">老访客</p>
                        <p class="su_p3 su_p5"><span id="oldRate" class="su_span2">14.61</span><span class="su_span3">%</span></p>
                    </div>
                </div>
                <div class="visit-type-detail">
                    <table  cellpadding="0" cellspacing="1" class="table-layout-02">
                        <tr>
                            <td class="visit-type-detail-name">浏览量</td>
                            <td id="newPageViewCount" class="visit-type-detail-new">1197</td>
                            <td id="oldPageViewCount" class="visit-type-detail-old">215</td>
                        </tr>
                        <tr>
                            <td class="visit-type-detail-name">访客数</td>
                            <td id="newVisitorCount" class="visit-type-detail-new">920</td>
                            <td id="oldVisitorCount" class="visit-type-detail-old">156</td>
                        </tr>
                        <tr>
                            <td class="visit-type-detail-name">跳出率</td>
                            <td id="newBounceRate" class="visit-type-detail-new">90.35%</td>
                            <td id="oldBounceRate" class="visit-type-detail-old">89.08%</td>
                        </tr>
                        <tr>
                            <td class="visit-type-detail-name">平均访问时长</td>
                            <td id="newAverageAccessTime" class="visit-type-detail-new">00:03:25</td>
                            <td id="oldAverageAccessTime" class="visit-type-detail-old">00:03:31</td>
                        </tr>
                        <tr>
                            <td class="visit-type-detail-name">平均访问页数</td>
                            <td id="newAverageBrowsePageCount" class="visit-type-detail-new">1.16</td>
                            <td id="oldAverageBrowsePageCount" class="visit-type-detail-old">1.17</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>