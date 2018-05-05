/**
 * Created by admin on 2017/4/12.
 */


$(".ta_ul1").on("click","li",function () {
    $(".ta_ul1 li").removeClass("ta_active");
    $(this).addClass("ta_active");
});

$(".ta_ul2").on("click","li",function () {
    $(".ta_ul2 li").removeClass("ta_active");
    $(this).addClass("ta_active");
});

$(".ta_ul4").on("click","li",function () {
    $(".ta_ul4 li").removeClass("ta_active");
    $(this).addClass("ta_active");
});

$(".ta_ul5").on("click","li",function () {
    $(".ta_ul5 li").removeClass("ta_active");
    $(this).addClass("ta_active");
});


var takey1 = true;
$(".ta_btn5").click(function () {
    $(".ta_span4").html("");
    if(takey1 == true){
        $(".ta_div1").hide();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        $(".ta_span5").html("展开筛选");
        takey1 = false;
    }else{
        $(".ta_div1").fadeIn();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".ta_span5").html("收起筛选");
        takey1 = true;
    }
});
var takey2 = true;
$(".ta_chartline").click(function () {
    $(".ta_spanchart").html("");
    if(takey2 == true){
        $(".ta_chart").hide();
        $(".ta_spanchart").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        takey2 = false;
    }else{
        $(".ta_chart").fadeIn();
        $(".ta_spanchart").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        takey2 = true;
    }
});

//折线图1
$(function () {
    $(window).resize(function(){
        //console.log($(".su_div4").width());
        $("#chart_taline1").css("width",$(".ta_wrap2").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart_taline1'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '折线图'
        },
        tooltip: {},
        legend: {
            data:['销量','销量1']
        },
        xAxis: {
            data: ["0","6","12","18"]
        },
        yAxis: {},
        series: [
            {
                name: '销量',
                type: 'line',
                data: [5, 250, 200, 10]
            },
            {
                name: '销量1',
                type: 'line',
                data: [2, 30, 240, 21]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});


