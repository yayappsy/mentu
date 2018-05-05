/**
 * Created by admin on 2017/4/10.
 */
var stats=[];
var yesterdaystats=[];
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
$(function () {
	findList();
	function chastload(){
		 $(window).resize(function(){
		        //console.log($(".su_div4").width());
		        $("#chart_line1").css("width",$(".su_div4").width());
		    });
		    // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('chart_line1'));

		    // 指定图表的配置项和数据
		    var option = {
		        title: {
		            text: '折线图'
		        },
		        tooltip: {},
		        legend: {
		            data:["2017-04-10","2017-04-09"]
		        },
		        xAxis: {
		            data: ["0","","","","4","","","","8","","","","12","","","","16","","","","20","","","","24"]
		        },
		        yAxis: {},
		        series: [
		            {
		                name: "2017-04-10",
		                type: 'line',
		                data: stats
		            },
		            {
		                name: "2017-04-09",
		                type: 'line',
		                data: yesterdaystats
		            }
		        ]
		    };
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
	}
	function findList(pageNo,pageSize){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findSiteView",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			$("#todayPageViewCount").text(data.result.pageViewCount);
			$("#todayVisitorCount").text(data.result.visitorCount);
			$("#todayIpCount").text(data.result.ipCount);
			$("#todayBounceRate").text(data.result.bounceRate);
			$("#todayAverageAccessTime").text(data.result.averageAccessTime);
		}
		});
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findStatsSiteSearchKeyword",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			 var keywords=data.result.statsSiteSearchKeywords;
			 var list="";
			 var pageViewCount=0;
			 for (var i = 0; i < keywords.length; i++) {
				 pageViewCount=pageViewCount+keywords[i].pageViewCount
				
			}
			
			 for (var i = 0; i < keywords.length; i++) {
				 var b=keywords[i].pageViewCount/pageViewCount*100
				 a=parseFloat(b.toFixed(2));
				 list +="<tr><td class='al url'><span class='ellipsis' title='百度推广客户端'>"+keywords[i].searchKeywords+"</span></td><td class='ar'>"+keywords[i].pageViewCount+"</td><td class='ratio'><div title='34.21%' style='background-color:#DCEBFE; width:"+a+"%;'>"+a+"%</div></td></tr>";
				
			}
			 document.getElementById('searchKeywordlist').innerHTML = list;
		}
		});

	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findSiteView",
		data:{
			"searchDate":GetDateStr(addDayCount-1)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			$("#yesterDayPageViewCount").text(data.result.pageViewCount);
			$("#yesterDayVisitorCount").text(data.result.visitorCount);
			$("#yesterDayIpCount").text(data.result.ipCount);
			$("#yesterDayBounceRate").text(data.result.bounceRate);
			$("#yesterDayAverageAccessTime").text(data.result.averageAccessTime);
		}
		});
	
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findSiteViewTrend",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			 var site=data.result.statsSiteOverviews;
			 for (var i = 0; i < site.length; i++) {
				 console.log(site[i].pageViewCount);
				 stats.push(site[i].pageViewCount)
			}
			 
		}
		});
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findSiteViewTrend",
		data:{
			"searchDate":GetDateStr(addDayCount-1)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			 var yesterdaySiteView=data.result.statsSiteOverviews;
			 for (var i = 0; i < yesterdaySiteView.length; i++) {
				 console.log(yesterdaySiteView[i].pageViewCount);
				 yesterdaystats.push(yesterdaySiteView[i].pageViewCount)
			}
			 
		}
		});
	
	 /*查询地域统计*/
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
				 
				 function ObjStory(name,value) //声明对象
				 {
				     this.name = name;
				     this.value= value;  
				 }
				 
				 for (var i = 0; i < visit.length; i++) {
					/* {name:+'visit[i].province'+','value:+'visit[i].pageViewCount'+}*/
					 statsVisit.push(new ObjStory(visit[i].province+'', visit[i].pageViewCount))
				}
				 console.log(statsVisit);
			}
			});
		
		/*查询新旧访客*/
		$.ajax({
			url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findVisitor",
			data:{
				"ifNewVisitor":true,
				 "searchDate":GetDateStr(addDayCount)
			},
			async:false,
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){
				var newRate=data.result.pageViewCount;
				 $("#newPageViewCount").text(data.result.pageViewCount);
				$("#newVisitorCount").text(data.result.visitorCount);
				$("#newBounceRate").text(data.result.bounceRate);
				$("#newAverageAccessTime").text(data.result.averageAccessTime);
				$("#newAverageBrowsePageCount").text(data.result.averageBrowsePageCount);
				
				$.ajax({
					url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findVisitor",
					data:{
						"ifNewVisitor":false,
						"searchDate":GetDateStr(addDayCount)
					},
					async:false,
					dateType:"json",
					error:function(e){console.log(e)},
					success:function(data){
						var oldRate=data.result.pageViewCount
						    $("#oldPageViewCount").text(data.result.pageViewCount);
							$("#oldVisitorCount").text(data.result.visitorCount);
							$("#oldBounceRate").text(data.result.bounceRate);
							$("#oldAverageAccessTime").text(data.result.averageAccessTime);
							$("#oldAverageBrowsePageCount").text(data.result.averageBrowsePageCount);
							old=oldRate/(oldRate+newRate)*100
							old=parseFloat(old.toFixed(2));
							$("#oldRate").text(old);
						   console.log(statsVisit);
						   newRate=newRate/(newRate+oldRate)*100
					}
					});
				     newRate=parseFloat(newRate.toFixed(2));
				    $("#newRate").text(a);
				   
		
				 console.log(statsVisit);
			}
			});
		 chastload();
	};
	
	$(".stats").click(function () {
		stats=[];
		yesterdaystats=[];
		var $this = $(this);
		$.ajax({
			url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findSiteViewTrend",
			async:false,
			data:{
				"searchParameter":$(this).data("id"),
                "searchDate":GetDateStr(addDayCount)
			},
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){			
				 var site=data.result.statsSiteOverviews;
				 for (var i = 0; i < site.length; i++) {
				 if(data.searchParameter==1){	
						 stats.push(site[i].pageViewCount)						 
				 }else if(data.searchParameter==2){
						 stats.push(site[i].visitorCount)			 
				 }else if(data.searchParameter==3){	
						 stats.push(site[i].ipCount) 
				 }else if(data.searchParameter==4){				 
						 stats.push(site[i].bounceRate)
				 }else if(data.searchParameter==5){				
						 stats.push(site[i].averageAccessTime)	 
				 }else if(data.searchParameter==6){
						 stats.push(site[i].conversionsCount)
				 }else{
					 stats.push(site[i].pageViewCount)
				 }
				 }	 
				 if($('input[name="time"]').is(':checked')){
					 console.log($("input[name='time']:checked").data("value"));
					 $.ajax({
							 url:"http://localhost:8080/yxxueyuan-admin/api/stats/overview/findYesterdaySiteViewTrend",
							data:{
								"timeParameter":$("input[name='time']:checked").data("value"),
								"searchDate":GetDateStr(addDayCount-1)
							},
							async:false,
							dateType:"json",
							error:function(e){console.log(e)},
							success:function(data){								
								 var yesterdaySiteView=data.result.statsSiteOverviews;
								 for (var i = 0; i < yesterdaySiteView.length; i++) {
								 if(data.searchParameter==1){	
										 yesterdaystats.push(yesterdaySiteView[i].pageViewCount)				 
								 }else if(data.searchParameter==2){
										 yesterdaystats.push(yesterdaySiteView[i].visitorCount)
										 
								 }else if(data.searchParameter==3){	
										 yesterdaystats.push(yesterdaySiteView[i].ipCount) 
								 }else if(data.searchParameter==4){
										 yesterdaystats.push(yesterdaySiteView[i].bounceRate)
						 
								 }else if(data.searchParameter==5){
										 yesterdaystats.push(yesterdaySiteView[i].averageAccessTime)	 
								 }else if(data.searchParameter==6){
										 yesterdaystats.push(yesterdaySiteView[i].conversionsCount)
								 }else{
									 yesterdaystats.push(yesterdaySiteView[i].pageViewCount)
								 }
			
								}
								 chastload(); 
							}
							});
					 
				 }
				 
			}
			});	
	});
	

	

	
	
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
		 stats=[];
		 yesterdaystats=[];
		 statsVisit=[];
		 findList();	 
	});

    });


   
//网站概况
var su_key = true;
var ul_key = true;

$(".su_btn1").click(function () {
    $(".su_icon2").html("");
    if(su_key==true){
        $(".su_hide").fadeIn();
        $(".su_icon2").append('<i class="glyphicon glyphicon-chevron-up"></i>')
        su_key = false;
    }else{
        $(".su_hide").hide();
        $(".su_icon2").append('<i class="glyphicon glyphicon-chevron-down"></i>')
        su_key = true;
    }
});

$(".su_ul1").on("click","li",function () {
    $(".su_ul1 li").removeClass("suactive");
    $(this).addClass("suactive");
});

$(".su_li2").click(function () {
    $(".su_li2").removeClass("suactive");
    $(this).addClass("suactive");
    if($(this).index()=="2"){
        if(ul_key == true){
            $(".su_ul2").fadeIn();
            ul_key = false;
        }else{
            $(".su_ul2").hide();
            ul_key = true;
        }
    }
});
//折线图1
$(function () {
	
   
});
//柱形图
$(function () {
    $(window).resize(function(){
        $("#charts_bar").css("width",$(".su_div4").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts_bar'));

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
            data: [5, 20, 36, 10, 10]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});
//地图
$(function () {
    $("#chart_map").css("width",$(".su_div4").width());
    $(window).resize(function(){
        $("#chart_map").css("width",$(".su_div4").width());
    });
    function randomValue() {
        return Math.round(Math.random()*1000);
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart_map'));
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
                name:'人口数',
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

