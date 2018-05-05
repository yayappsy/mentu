<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code="统计网站"/></title>
	<meta name="decorator" content="stats_default"/>
    <script type="text/javascript" src="${ctxStatic}/biaozhun/res/echarts/echarts.min.js"></script>
    <script type="text/javascript">
$(function () {
var $select = $("#select");
if(localStorage.getItem("data_href")!=null){
    $select.load(localStorage.getItem("data_href"));
}else{
    $select.load("${ctx}/stats/statsSiteOverview/survey");
}
$(".list-group").on("click",".list-group-item",function () {
    console.log($(this).data("href"));
    $select.load($(this).attr("data-href"));
    localStorage.setItem("data_href",$(this).attr("data-href"))
});
$select.css("width",window.innerWidth-172+"px");
$(window).resize(function(){
    //console.log(window.innerWidth);
    $select.css("width",window.innerWidth-172+"px")
});
});

</script>
   <style>
        .nav_left{
            position: fixed;
            left:0;
            top:72px;
            width:172px;
            overflow: hidden;
        }
        .select{
            margin-left: 172px;
            overflow: hidden;
        }
        .list-group-item {
            position: relative;
            display: block;
            padding: 10px 10px;
            margin-bottom: -1px;
            background-color: #fff;
            border: 1px solid #ddd;
        }
    </style>

</head>
<body>
<div class="main">
    <div class="header navbar navbar-default">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target="#myNavs">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">东北F4</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavs">
            <ul class="nav nav-pills nav-justified">
                <li class='active'>
                    <a href="#">张三</a>
                </li>
                <li>
                    <a href="#">李四</a>
                </li>
                <li>
                    <a href="#">王保长</a>
                </li>
                <li>
                    <a href="#">花花</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="sections">
        <div class="nav_left fl">
            <div class="list-group">
                <button type="button" class="list-group-item" data-href="survey">网页概况</button>
                <button type="button" class="list-group-item" data-href="visitor">实时访客</button>
              <!--   <button type="button" class="list-group-item" data-href="trendanalysis">趋势分析</button> -->
                <button type="button" class="list-group-item" data-href="sources">全部来源</button>
                <button type="button" class="list-group-item" data-href="sezrchengine">搜索引擎</button>
            </div>
            <div class="list-group">
               <!--  <button type="button" class="list-group-item" data-href="pageview.jsp">受访页面</button>
                <button type="button" class="list-group-item" data-href="pageentry.jsp">入口页面</button> -->
                <button type="button" class="list-group-item" data-href="geographical">地域分布</button>
                <button type="button" class="list-group-item" data-href="systemenvironments">系统环境</button>
                <button type="button" class="list-group-item" data-href="xinlaovisitor">新老访客</button>
            </div>
            <div class="list-group">
               <!--  <button type="button" class="list-group-item" data-href="loyalty.jsp">忠诚度</button> -->
            </div>
        </div>
        <div class="fl select" id="select">
            656
        </div>
    </div>
</div>
</body>

</html>