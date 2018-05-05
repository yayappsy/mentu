/**
 * Created by admin on 2017/4/13.
 */
$(".pe_ul1").on("click","li",function () {
    $(".pe_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

//黄色提示
$(".pv_icon").click(function () {
    $(".page-tip").hide();
});



var chartline2 = true;
$(".chartline").click(function () {
    $(".spanchart").html("");
    if(chartline2 == true){
        $(".pe_chart").hide();
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        chartline2 = false;
    }else{
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        chartline2 = true;
        $(".pe_chart").fadeIn();
    }
});


//环形图表
$(function () {
    $(window).resize(function(){
        //console.log($(".su_div4").width());
        $("#charts_piepe").css("width",$(".pe_chartleft").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts_piepe'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:120, name:'直接访问'},
                    {value:210, name:'邮件营销'},
                    {value:400, name:'联盟广告'},
                    {value:500, name:'视频广告'},
                    {value:100, name:'搜索引擎'}
                ]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});

//折线图表
$(function () {
    $(window).resize(function(){
        //console.log($(".su_div4").width());
        $("#charts_linepe").css("width",$(".pe_chartright").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts_linepe'));

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
