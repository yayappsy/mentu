<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实时访客</title>
<meta name="decorator" content="stats_default"/>
    <link rel="stylesheet" href="${ctxStatic}/biaozhun/css/visitor.css">
    <script type="text/javascript" src="${ctxStatic}/biaozhun/js/visitor.js"></script>
    <style>

    </style>
</head>
<body>
<div class="selectbox">
    <div class="top">
        <div class="su_lef fl icon">实时访客<span class="glyphicon glyphicon-question-sign cs" aria-hidden="true"></span></div>
        <div class="su_rig fr">
            <span class="icon cs"><i class="glyphicon glyphicon-heart su_icon1" aria-hidden="true"></i>常用报告</span>
            <span class="icon cs"><i class="glyphicon glyphicon-download-alt su_icon1" aria-hidden="true"></i>下载</span>
            <span class="icon cs"><i class="glyphicon glyphicon-pushpin" aria-hidden="true"></i></span>
        </div>
    </div>

    <div class="vs_wrap1">
        <div class="vs_div1 fl">
            <div class="vs_daojishi">当前在线<span class="vs_miao" id="timer"></span>s之后更新数据</div>
            <div class="trend-overview-number-wrapper">
                <div class="trend-overview-date" id="thisTime">2017/04/12 09:44:01</div>
                <div class="trend-overview-number" id="thisVisitor" title="3">3</div>
                <div class="trend-overview-info">在线访客数</div>
            </div>
        </div>
        <div class="vs_div2 fl">
            <div class="vs_top1">最近30分钟访问情况</div>
            <div id="chart_vsline1" style="height:300px;"></div>
        </div>
    </div>
    <div class="vs_wrap2">
        <div class="vs_div3">
            <div class="vs_div4 fl">访问明细</div>
            <div class="vs_div5 fl">（仅提供两周内的访问明细，最多5000条）</div>
        </div>
        <div class="vs_div6">
            <span class="fl vs_span3">来源：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">全部</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">全部</a>
                    </li>
                    <li class=""><a href="#">直接访问</a></li>
                    <li><a href="#">搜索引擎</a></li>
                    <li><a href="#">外部链接</a></li>
                    
                </ul>
            </div>
            <span class="fl vs_span2">地域：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">全部</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
            
            </div>
            <span class="fl vs_span2">设备：</span>
            <ul class="vs_ul1">
                <li class="vs_active">全部</li>
                <li>计算机</li>
                <li style="border-right:1px solid #e1e3e4;">移动设备</li>
            </ul>
            <span class="fr icon cs vs_btn5"><span class="vs_span4"><span class="glyphicon glyphicon-chevron-up"></span></span><span class="vs_span5">收起筛选</span></span>
        </div>
        <div class="vs_div7">
            <div class="vs_div8">
                <span class="fl vs_span3">访客：</span>
                <ul class="vs_ul1">
                    <li class="vs_active">全部</li>
                    <li>新访客</li>
                    <li style="border-right:1px solid #e1e3e4;">老访客</li>
                </ul>
                <span class="fl vs_span2"></span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">访问频次（全部）</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li class=""><a href="#">访问频次（全部）</a></li>
                    <li><a href="#">1次</a></li>
                    <li><a href="#">2次</a></li>
                    <li><a href="#">3次</a></li>
                    <li><a href="#">4次</a></li>
                    <li><a href="#">5-10次</a></li>
                    <li><a href="#">11-20次</a></li>
                    <li><a href="#">20次以上</a></li>
                </ul>
            </div>
                <span class="fl vs_span2"></span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">访问深度（全部）</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li class=""><a href="#">访问深度（全部）</a></li>
                    <li><a href="#">1页</a></li>
                    <li><a href="#">2页</a></li>
                    <li><a href="#">3页</a></li>
                    <li><a href="#">4页</a></li>
                    <li><a href="#">5-10页</a></li>
                    <li><a href="#">11-20页</a></li>
                    <li><a href="#">20页以上</a></li>
                </ul>
            </div>
                <span class="fl vs_span2"></span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">访问时长（全部）</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li class=""><a href="#">访问时长（全部）</a></li>
                    <li><a href="#">0-30s</a></li>
                    <li><a href="#">31s-60s</a></li>
                    <li><a href="#">61s-90s</a></li>
                    <li><a href="#">91s-180s</a></li>
                    <li><a href="#">181s-300s</a></li>
                    <li><a href="#">301s-600s</a></li>
                </ul>
            </div>
            </div>
            <div class="vs_div8">
                <span class="fl vs_span3"></span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">日期（全部）</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li class=""><a href="#">日期（全部）</a></li>
                    <li><a href="#">04月12日（今天）</a></li>
                    <li><a href="#">04月11日（昨天）</a></li>
                    <li><a href="#">04月10日（前天）</a></li>
                    <li><a href="#">04月09日</a></li>
                    <li><a href="#">04月08日</a></li>
                    <li><a href="#">04月07日</a></li>
                </ul>
            </div>
            </div>
        </div>
        <div class="vs_div9">
            <form class="form-inline">
                <div class="form-group vs_form1">
                    <label for="exampleInputName2">关键词/搜索词</label>
                    <input type="text" class="form-control vs_input1" id="exampleInputName2" placeholder="请输入...">
                </div>
                <div class="form-group vs_form1">
                    <label for="exampleInputName3">入口页面</label>
                    <input type="text" class="form-control vs_input1" id="exampleInputName3" placeholder="请输入...">
                </div>
                <div class="form-group vs_form1">
                    <label for="exampleInputName4">访客标识码</label>
                    <input type="text" class="form-control vs_input1" id="exampleInputName4" placeholder="请输入...">
                </div>
                <div class="form-group vs_form1">
                    <label for="exampleInputName5">IP</label>
                    <input type="text" class="form-control vs_input1" id="exampleInputName5" placeholder="请输入...">
                </div>
                <button type="submit" class="btn btn-default vs_btn6">查询</button>
            </form>
        </div>
        <div id="table">
            <table class="table table-layout-03">
                <thead>
                <tr class="group-item" >
                    <td class="empty-col"   colspan="1" rowspan="1">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="no-expand-col"   colspan="1" rowspan="1">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="number-col"   colspan="1" rowspan="1">
                        <div class="td-content">&nbsp;</div>
                    </td>
                    <td class="table-index" data="null" id="null" colspan="1" rowspan="">
                        <div class="td-content"></div>
                    </td>
                    <td class=" sortable desc" data="start_time" id="start_time" colspan="" rowspan="">
                        <div class="td-content">访问时间</div>
                    </td>
                    <td class="" data="area" id="area" colspan="" rowspan="">
                        <div class="td-content">地域</div>
                    </td>
                    <td class="url sortable" data="source" id="source" colspan="" rowspan="">
                        <div class="td-content">来源</div>
                    </td>
                    <td class="url" data="access_page" id="access_page" colspan="" rowspan="">
                        <div class="td-content">入口页面</div>
                    </td>
                    <td class="" data="searchword" id="searchword" colspan="" rowspan="">
                        <div class="td-content">搜索词</div>
                    </td>
                    <td class="" data="ip" id="ip" colspan="" rowspan="">
                        <div class="td-content">访问IP</div>
                    </td>
                    <td class="" data="visitorId" id="visitorId" colspan="" rowspan="">
                        <div class="td-content"><span class="text">访客标识码</span>
                            <a href="javascript:void(0)" class="help" data="visitorId"></a>
                        </div>
                    </td>
                    <td class="time2 sortable" data="visit_time" id="visit_time" colspan="" rowspan="">
                        <div class="td-content">访问时长</div>
                    </td>
                    <td class="number sortable" data="visit_pages" id="visit_pages" colspan="" rowspan="">
                        <div class="td-content">访问页数</div>
                    </td>
                    <td class="empty-col last"   colspan="" rowspan="">
                        <div class="td-content">&nbsp;</div>
                    </td>
                </tr>
                </thead>
                <tbody id="visitorList">
                </tbody>
                <tfoot></tfoot>
            </table>
            <div class="table_btm">
                <span class="fl vs_span3">显示行数：</span><div class="btn-group fl">
                <button type="button" class="btn btn-default vs_btn1 vs_btn2">20</button>
                <button type="button" class="btn btn-default dropdown-toggle vs_btn1 vs_btn3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <ul class="dropdown-menu">
                    <li class=""><a class="size" href="#" data-size="20">20</a></li>
                    <li><a class="size" href="#" data-size="50">50</a></li>
                    <li><a class="size" href="#" data-size="100">100</a></li>
                </ul>
            </div>
                <nav aria-label="Page navigation" class="fr">
                    <ul class="pagination" id="pagination">                  
                     
                    </ul>
                </nav>
            </div>
            <div class="page-bottom-hint">
                小贴士：<br>
                其他国家指除中国（包括港澳台地区）外的国家和地区。                                <br>访问时长显示未知的原因:当用户快速关闭浏览器、长时间未对页面进行操作或出现网络问题时，系统会无法获取到页面的关闭信息，从而使最后一个页面的访问时长无法计算。
            </div>
        </div>
    </div>
</div>
</body>
</html>