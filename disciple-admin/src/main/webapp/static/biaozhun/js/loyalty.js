/**
 * Created by admin on 2017/4/13.
 */
$(".la_ul1").on("click","li",function () {
    $(".la_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".la_ul2").on("click","li",function () {
    $(".la_ul2 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});


//柱形图
$(function () {
    $(window).resize(function(){
        $("#chart_sebar").css("width",$(".se_chartbox").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart_sebar'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: ''
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["10-19岁","20-29岁","30-39岁","40-49岁","50-59岁"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [25, 52, 45, 5, 30]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});