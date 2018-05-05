<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>受访页面</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/pageview.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/pageview.js"></script>
    <style>
    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="sclef fl icon">受访页面(2017/04/12)<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="scrig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart scicon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt scicon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="sr_wrap1">
        <div class="ta_div6">
            <span class="fl ta_span2">时间：</span>
            <ul class="tj_ul fl pv_ul1">
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
            <span class="fr icon cs pv_btn5" style="margin-right: 20px"><span class="ta_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="ta_span5">收起筛选</span></span>
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
            <ul class="tj_ul fl pv_ul2">
                <li class="tj_active">全部</li>
                <li>新访客</li>
                <li>老访客</li>
            </ul>
        </div>
    </div>

    <div class="page-tip">
        <a class="tip-close open trackable"  href="javascript:void(0);"></a>
        <div class="text">2012年11月1日起，受访页面新增访客数（UV）和IP数指标，11月1日前该指标数值为0。</div>
        <span class="icon pv_icon cs"><span class="glyphicon glyphicon-remove"></span></span>
    </div>

    <div class="pv_wrap2">
        <div class="tj_navtap">
            <ul class="nav nav-tabs tj_ultap">
                <li role="presentation" class="active"><a href="#">指标概览</a></li>
                <li role="presentation"><a href="#">页面价值分析</a></li>
                <li role="presentation"><a href="#">入口页分析</a></li>
                <li role="presentation"><a href="#">退出页分析</a></li>
            </ul>
        </div>
        <div class="section pv_tabletop">
            <table class="summary">
                <tbody>
                <tr>
                    <td><span class="text">浏览量(PV)<a href="javascript:void(0);" data="pv_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="438">438</div>
                    </td>
                    <td><span class="text">访客数(UV)<a href="javascript:void(0);" data="visitor_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="380">380</div>
                    </td>
                    <td><span class="text">退出页次数<a href="javascript:void(0);" data="exit_count" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="320">320</div>
                    </td>
                    <td class="last"><span class="text">退出率<a href="javascript:void(0);" data="exit_ratio" class="help">&nbsp;</a></span>
                        <div class="value summary-ellipsis" title="73.06%">73.06%</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="vs_div9 pv_div1">
            <form class="form-inline">
                <div class="form-group vs_form1">
                    <label for="exampleInputName2">关键词/搜索词</label>
                    <input type="text" class="form-control vs_input1" id="exampleInputName2" placeholder="请输入...">
                </div>
                <button type="submit" class="btn btn-default vs_btn6">查询</button>
            </form>
        </div>

        <div id="table">
            <table class="table table-layout-09">
                <thead>
                <tr class="group-title" >
                    <td class="number-col" colspan="1" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index url" data="visit_page_title" id="visit_page_title" colspan="1" rowspan="2">
                        <div class="td-content">页面URL</div>
                    </td>
                    <td class="no-operate-col" colspan="" rowspan="2">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="group-name2 group" data="2" id="2" colspan="2" rowspan="">
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
                    <td class="number sortable desc group" data="exit_count" id="exit_count" colspan="" rowspan="">
                        <div class="td-content">退出页次数</div>
                    </td>
                    <td class="ratio sortable" data="exit_ratio" id="exit_ratio" colspan="" rowspan="">
                        <div class="td-content">退出率</div>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr class="line" id="table-tr_0">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="1">1</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com" target="_blank" title="http://editor.baidu.com">http://editor.baidu.com</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="0" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">390</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">343</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">289</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">74.1%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_1">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="2">2</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/?_t_t_t=0.5967946627642959" target="_blank" title="http://editor.baidu.com/?_t_t_t=0.5967946627642959">http://editor.baidu.com/?_t_t_t=0.5967946627642959</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="1" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">12</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">11</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">91.67%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_2">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="3">3</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/function" target="_blank" title="http://editor.baidu.com/function">http://editor.baidu.com/function</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="2" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">12</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">12</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">5</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">41.67%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_3">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="4">4</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/?qq-pf-to=pcqq.c2c" target="_blank" title="http://editor.baidu.com/?qq-pf-to=pcqq.c2c">http://editor.baidu.com/?qq-pf-to=pcqq.c2c</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="3" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">9</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">9</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">4</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">44.44%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_4">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="5">5</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/account" target="_blank" title="http://editor.baidu.com/account">http://editor.baidu.com/account</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="4" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">6</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">6</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">4</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">66.67%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_5">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="6">6</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/search" target="_blank" title="http://editor.baidu.com/search">http://editor.baidu.com/search</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="5" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_6">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="7">7</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/?qq-pf-to=pcqq.group" target="_blank" title="http://editor.baidu.com/?qq-pf-to=pcqq.group">http://editor.baidu.com/?qq-pf-to=pcqq.group</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="6" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">3</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">66.67%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_7">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="8">8</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://fanyi.baidu.com/referrer" target="_blank" title="http://fanyi.baidu.com/referrer">http://fanyi.baidu.com/referrer</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="7" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">2</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">100%</div>
                    </td>
                </tr>
                <tr class="line" id="table-tr_8">
                    <td class="number-col" colspan="">
                        <div class="td-content" title="9">9</div>
                    </td>
                    <td class="table-index url visit_page_title" data="[object Object]" colspan="">
                        <div class="td-content" title="">
                            <a href="http://editor.baidu.com/union" target="_blank" title="http://editor.baidu.com/union">http://editor.baidu.com/union</a>
                        </div>
                    </td>
                    <td colspan="" class="operate-col single-operate" >
                        <div class="td-content" data="8" title="查看历史趋势" layer="#Operations">&nbsp;</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">1</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">0</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">0%</div>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="" >
                    <td class="number-col" colspan="">
                        <div class="td-content" title="&nbsp;">&nbsp;</div>
                    </td>
                    <td class="summary visit_page_title" colspan="2">
                        <div class="td-content" title="">当前汇总</div>
                    </td>
                    <td class="number group pv_count" colspan="">
                        <div class="td-content" title="">438</div>
                    </td>
                    <td class="number visitor_count" colspan="">
                        <div class="td-content" title="">380</div>
                    </td>
                    <td class="number group exit_count" colspan="">
                        <div class="td-content" title="">320</div>
                    </td>
                    <td class="ratio exit_ratio" colspan="">
                        <div class="td-content" title="">73.06%</div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>

        <div class="page-bottom-hint">
            小贴士：<br>
            您可以在“页面上下游”报告中将贡献下游浏览量高的页面设置为监测页面。<br>
            系统默认存储3000个受访页面，若您需要分析的页面多于3000，请点此<a href="http://datamarket.baidu.com/web/app.html#app/detail?appId=1" target="_blank">提升上限</a>。
        </div>
    </div>
</div>
</body>
</html>