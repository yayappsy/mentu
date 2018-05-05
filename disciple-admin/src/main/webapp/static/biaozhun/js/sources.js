/**
 * Created by admin on 2017/4/12.
 */

var sourceTypes=[];
var objSource=[];
var sourceName=[];
var addDayCount=0;
var searchVisit="all";
function GetDateStr(addDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+addDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth()+1;//获取当前月份的日期
	var d = dd.getDate();
	//y+"-"+m+"-"+d
	return "2017-04-10";
	}
function chastload(){
	//折线图表
	$(function () {
	    $(window).resize(function(){
	        //console.log($(".su_div4").width());
	        $("#chart_srline1").css("width",$(".sr_div3").width());
	    });
	    // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('chart_srline1'));

	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: '折线图'
	        },
	        tooltip: {},
	        legend: {
	            data:sourceName
	        },
	        xAxis: {
	            data: ["0","","","3","","","6","","","9","","","12","","","15","","","18","","","21","","","",]
	        },
	        yAxis: {},
	        series: sourceTypes

	             
	    };
	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	    
	    
	    $(window).resize(function(){
	        //console.log($(".su_div4").width());
	        $("#chart_srpie1").css("width",$(".sr_div2").width());
	    });
	    // 基于准备好的dom，初始化echarts实例
	    var myChart = echarts.init(document.getElementById('chart_srpie1'));

	    // 指定图表的配置项和数据
	    var option = {
	        tooltip: {
	            trigger: 'item',
	            formatter: "{a} <br/>{b}: {c} ({d}%)"
	        },
	        legend: {
	            orient: 'vertical',
	            x: 'left',
	            data:sourceName
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
	                data:objSource
	            }
	        ]
	    };
	    // 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	    
	    
	});
	}
$(".sr_ul1").on("click","li",function () {
    $(".sr_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".sr_ul2").on("click","li",function () {
    $(".sr_ul2 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".sr_ul3").on("click","li",function () {
    $(".sr_ul3 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

var srkey1 = true;
$(".sr_btn5").click(function () {
    $(".ta_span4").html("");
    if(srkey1 == true){
        $(".ta_div1").hide();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        $(".ta_span5").html("展开筛选");
        srkey1 = false;
    }else{
        $(".ta_div1").fadeIn();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".ta_span5").html("收起筛选");
        srkey1 = true;
    }
});

var takey2 = true;
$(".sr_chartbtn").click(function () {
    $(".ta_spanchart").html("");
    if(takey2 == true){
        $(".ta_chart").hide();
        $(".sr_div1").hide();
        $(".ta_spanchart").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        takey2 = false;
    }else{
        $(".ta_chart").fadeIn();
        $(".sr_div1").fadeIn();
        $(".ta_spanchart").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        takey2 = true;
    }
});

//环形图表
$(function () {
	
	 function ObjStory(name,type,data) //声明对象
	 {
	     this.name = name;
	     this.type = type;
	     this.data= data;  
	 }
	
	 function ObjSource(name,value) //声明对象
	 {
	     this.name = name;
	     this.value= value;  
	 }
	 
	 findList();
	 function findList(){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSiteSource",
		data:{
			"searchDate":GetDateStr(addDayCount)
			
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			 var source=data.result;
			 var list="";
			 var pageViewCount=0;
			 var visitorCount=0;
			 var ipCount=0;
			 var bounceRate=0;
			 var averageBrowsePageCount=0;
			
			 var averageAccessTime="";
			 var count=0;
			 var sourceList="";
			 var countss=0;
			 for (var i = 0; i < source.length; i++) {
				 count++;
				 pageViewCount=pageViewCount+source[i].pageViewCount;
				 visitorCount=visitorCount+source[i].visitorCount;
				 ipCount=ipCount+source[i].ipCount;
				 bounceRate=bounceRate+source[i].bounceRate;
				 averageBrowsePageCount=averageBrowsePageCount+source[i].averageBrowsePageCount;
				 averageAccessTime=source[i].averageAccessTime;
				 var date=new Date(averageAccessTime);
				 var mm=date.getMinutes();
				 var ss=date.getSeconds();
				  countss=countss+(mm*60+ss);
				  var averageAccessTimes= Math.floor((mm*60+ss)/3600)+":"+Math.floor((mm*60+ss)/60)+":"+((mm*60+ss)%60/100).toFixed(2).slice(-2);
				 
				  sourceList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col' colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index url source_type_title' data='[object Object]'  colspan=''><div class='td-content' title=''><a href='' title='"+source[i].searchSite+"'>"+source[i].searchSite+"</a></div></td><td colspan='' class='operate-col'><div class='td-content' data='"+i+"' title='' layer='#Operations'>&nbsp;</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+source[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+source[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+source[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+source[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>";
				 document.getElementById('sourceList').innerHTML = sourceList;
			     
				 //var dTime = new Date(source[i].averageAccessTime.replace(new RegExp("-", "g"), "/"));
				
				 //console.log("averageAccessTime:"+source[i]);
				 var line="line";
				
				 objSource.push(new ObjSource(source[i].sourceTypeDescribe,source[i].pageViewCount));
				 sourceName.push(source[i].sourceTypeDescribe);
				 
				 GetHourSource(source[i].sourceType,source[i].sourceTypeDescribe,1);
			}
			 countss=countss/count;
			
			
			 averageAccessTime=  Math.floor(countss/3600)+":"+Math.floor(countss/60)+":"+(countss%60/100).toFixed(2).slice(-2);
			 console.log(countss);
			 bounceRate=bounceRate/count;
			 bounceRate=parseFloat(bounceRate.toFixed(2));
			  $("#s_pv_count").text(pageViewCount);
			  $("#s_visitor_count").text(visitorCount);
			  $("#s_ip_count").text(ipCount);
			  $("#s_bounce_rate").text(bounceRate+"%");
			  $("#s_avg_visit_time").text(averageAccessTime);
			  
			  $("#spvCount").text(pageViewCount);
			  $("#svisitorCount").text(visitorCount);
			  $("#sipCount").text(ipCount);
			  $("#sbounceRate").text(bounceRate+"%");
			  $("#savgVisitTime").text(averageAccessTime);
			  chastload();
		}
		
		});
	 };
	$(".source").click(function () {
		 chastload();
		var $this = $(this);	
		var id=$this.data("id");
		sourceTypes=[];
		$.ajax({
			url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSiteSource",
			data:{
				"searchDate":GetDateStr(addDayCount)		
			},
			async:false,
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){	 
				 var source=data.result;					 
				 for (var i = 0; i < source.length; i++) {			 
					 GetHourSource(source[i].sourceType,source[i].sourceTypeDescribe,id);
				}		
			}
			});
	});
	
	function GetHourSource(sourceType,sourceTypeDescribe,id) {
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSourceByHour",
		data:{
			"searchDate":GetDateStr(addDayCount),
			"searchVisit":searchVisit
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			var sourceValue=[];
			 var sourceList=data.result;		
			 for (var i = 0; i < sourceList.length; i++) {
				 if(sourceType==sourceList[i].sourceType){
					
					 if(id==1){
							//浏览量(PV)
						 sourceValue.push(sourceList[i].pageViewCount);
						}else if(id==2){
							//访客数(UV)
							sourceValue.push(sourceList[i].visitorCount);
						}else if(id==3){
							//IP数
							sourceValue.push(sourceList[i].ipCount);
						}else if(id==4){
							//跳出率
							sourceValue.push(sourceList[i].bounceRate);
						}else if(id==5){
							//平均访问时长
							sourceValue.push(sourceList[i].averageAccessTime);
						}else if(id==6){
							//平均访问页数
							sourceValue.push(sourceList[i].averageBrowsePageCount);
						}
					
				 }		 
			}		 
			 sourceTypes.push(new ObjStory(sourceTypeDescribe, 'line', sourceValue));
			 chastload();
		}
		});
	}
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
		 sourceValue=[];
		 sourceTypes=[];
		 objSource=[];
		 findList();	 
	 });
	$(".visit").click(function () {	
	 var $this = $(this);	
	 var visitType=$this.data("id");
	 if(visitType=="all"){
		 searchVisit="all";
	 }else if(visitType=="new"){
		 searchVisit="new";
	 }else if(visitType=="old"){
		 searchVisit="old";
	 }
	 chastload();
	 sourceValue=[];
	 sourceTypes=[];
	 objSource=[];
	 findList();	
	});
});


