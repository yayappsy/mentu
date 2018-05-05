/**
 * Created by admin on 2017/4/13.
 */
var statsVisit=[];
var addDayCount=0;
function GetDateStr(addDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+addDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth()+1;//获取当前月份的日期
	var d = dd.getDate();
	//y+"-"+m+"-"+d
	return "2017-04-10";
	}
//地图
function chastload(){
$(function () {
    $("#chart_gpmap").css("width",$(".gp_chartleft").width());
    $(window).resize(function(){
        $("#chart_gpmap").css("width",$(".gp_chartleft").width());
    });
    function randomValue() {
        return Math.round(Math.random()*500);
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart_gpmap'));
    var option = {
        title: {
            text: '折线图',
            left: 'right'
        },
        tooltip: {},
        visualMap: {//定义颜色
            min: 0,
            max: 1000,
            left: 'right',
            top: 'bottom',
            text: ['High','Low'],
            inRange: {
                color: ['#ebf3fc', '#c6ddf7', '#90bcf0', '#5b9ce9', '#3385e3']
            },
            calculable : true
        },

        toolbox: {//左上角定义下载
            show: true,
            //orient: 'vertical',
            left: 'left',
            top: 'top',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        legend: {
            data:[]
        },
        series: [
            {
                type: 'map',
                map: 'china',
                name:'浏览量(PV)',
                roam: false,
                itemStyle:{
                    emphasis:{label:{show:true}}
                },
                data:statsVisit
            }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});
};


$(function () {
	/*查询地域统计*/
	findList();
	function findList(){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/visit/findSiteVitit",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			 var visit=data.result.statsVisitDistricts;
			 var pageViewCount=0;
			 var visitorCount=0;
			 var ipCount=0;
			 var bounceRate=0;
			 var averageAccessTime="";
			 var countss=0;
			 var list=""; 
			 var visitList="";
			 var totalVisit="";
			 var count=0;
			 function ObjStory(name,value) //声明对象
			 {
			     this.name = name;
			     this.value= value;
			 }
			 
			 for (var i = 0; i < visit.length; i++) {
				 count=i+1;
				 pageViewCount=pageViewCount+visit[i].pageViewCount;
				 visitorCount =visitorCount+visit[i].visitorCount;
				 ipCount =ipCount+visit[i].ipCount;
				 bounceRate = bounceRate+visit[i].bounceRate;
				 Rate=(visit[i].pageViewCount)/pageViewCount*100;
				 averageAccessTime=visit[i].averageAccessTime;
				 var date=new Date(averageAccessTime);
				 var mm=date.getMinutes();
				 var ss=date.getSeconds();
				  countss=countss+(mm*60+ss);
				 var averageAccessTimes=  Math.floor((mm*60+ss)/3600)+":"+Math.floor((mm*60+ss)/60)+":"+((mm*60+ss)%60/100).toFixed(2).slice(-2);
				 pageViewCountRate=parseFloat(Rate.toFixed(2));
				/* {name:+'visit[i].province'+','value:+'visit[i].pageViewCount'+}*/
				 statsVisit.push(new ObjStory(visit[i].province+'', visit[i].pageViewCount))
				 list +="<tr class='line' area='"+visit[i].province+"'><td class='number-col' style='width: 25px'><div class='td-content'>"+(i+1)+"</div></td><td class='text-left' style='width: 80px'><div class='td-content' title='"+visit[i].province+"'>"+visit[i].province+"</div></td><td class='text-right url' style='width: 260px'><div class='td-content' title="+visit[i].pageViewCount+">"+visit[i].pageViewCount+"</div></td><td class='text-right ratio' style='width: 80px'><div class='td-content' title='"+pageViewCountRate+"%'>"+pageViewCountRate+"</div></td></tr>";	
				 visitList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'  colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index visit_district_title' data='[object Object]' colspan=''><div class='td-content' title=''>"+visit[i].province+"</div></td><td colspan='' class='operate-col single-operate' ><div class='td-content' data="+i+" title='查看历史趋势' layer='#Operations'>&nbsp;</div></td><td class='number group pv_count'  colspan=''><div class='td-content' title=''>"+visit[i].pageViewCount+"</div></td><td class='number visitor_count'  colspan=''><div class='td-content' title=''>"+visit[i].visitorCount+"</div></td><td class='number ip_count'  colspan=''><div class='td-content' title=''>"+visit[i].ipCount+"</div></td><td class='ratio group bounce_ratio'  colspan=''><div class='td-content' title=''>"+visit[i].bounceRate+"%</div></td><td class='time avg_visit_time'  colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'  colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>";
			 }
			 bounceRate= bounceRate/count;
			 countss=countss/count;
			 averageAccessTime=  Math.floor(countss/3600)+":"+Math.floor(countss/60)+":"+(countss%60/100).toFixed(2).slice(-2);
			 document.getElementById('visitDistrict').innerHTML = list;
			 document.getElementById('visitList').innerHTML = visitList;
			  $("#pageViewCount").text(pageViewCount);
			  $("#visitorCount").text(visitorCount);
			  $("#ipCount").text(ipCount);
			  $("#bounceRate").text(bounceRate+"%");
			  $("#averageAccessTime").text(averageAccessTime);
			  totalVisit+="<tr class='' ><td class='empty-col' data='-1' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='no-expand-col' data='-1' id='expand_table-tr_-1' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'  colspan=''><div class='td-content' title='&nbsp;'>&nbsp;</div></td><td class='summary visit_district_title'  colspan='2'><div class='td-content' title=''>当前汇总</div></td><td class='number group pv_count'  colspan=''><div class='td-content' title=''>"+pageViewCount+"</div></td><td class='number visitor_count'  colspan=''><div class='td-content' title=''>"+visitorCount+"</div></td><td class='number ip_count'  colspan=''><div class='td-content' title=''>"+ipCount+"</div></td><td class='ratio group bounce_ratio'  colspan=''><div class='td-content' title=''>"+bounceRate+"%</div></td><td class='time avg_visit_time'  colspan=''><div class='td-content' title=''>"+averageAccessTime+"</div></td><td class='empty-col last'  colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
			  document.getElementById('totalVisit').innerHTML = totalVisit;
			  
			  chastload();
		}
		});
	};
	
	 $(".searchTime").click(function () {
		 var $this = $(this);	
		 var searchType=$this.data("id");
		 console.log(searchType);
		 if(searchType=="today"){
			 addDayCount=0;
		 }else if(searchType=="yesterday"){
			 addDayCount=-1;
		 }else if(searchType=="lastweek"){
			 addDayCount=-7;
		 }else if(searchType=="lastmonth"){
			 addDayCount=-30;
		 }
		 chastload();
		 statsVisit=[];
		 findList();
		 
	 });

});
$(".gp_ul1").on("click","li",function () {
    $(".gp_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".gp_ul2").on("click","li",function () {
    $(".gp_ul2 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});
var chartline1 = true;
$(".chartline1").click(function () {
    $(".spanchart").html("");
    if(chartline1 == true){
        $(".gp_chartst").hide();
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        chartline1 = false;
    }else{
        $(".gp_chartst").fadeIn();
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        chartline1 = true;
    }
});
//黄色提示
$(".pv_icon").click(function () {
    $(".page-tip").hide();
});

