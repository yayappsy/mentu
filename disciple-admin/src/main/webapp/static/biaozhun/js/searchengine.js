/**
 * Created by admin on 2017/4/12.
 */

var sourceTypes=[];
var objSource=[];
var sourceName=[];
var pageNo=1;
var pageSize=20;
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
	 
	 
	 $(function () {
	    	findList(pageNo,pageSize);
	    	function findList(pageNo,pageSize){
	    	$.ajax({
				url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSiteEngine",
				data:{
					"searchDate":GetDateStr(addDayCount),
					"pageNo":pageNo,
	    			"pageSize":pageSize
				},
				async:false,
				dateType:"json",
				error:function(e){console.log(e)},
				success:function(data){	 
					 var source=data.result.dataList;
					 var pageViewCount=0;
					 var visitorCount=0;
					 var ipCount=0;
					 var bounceRate=0;
					 var averageBrowsePageCount=0;
					  var engineList="";
					 var averageAccessTime="";
					 var count=0;
					 var countss=0;
					 var page=""
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
						 engineList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index source_engine_title' data='[object Object]'  colspan=''><div class='td-content' title=''><a href='' title='"+source[i].searchSite+"'>"+source[i].searchSite+"</a></div></td><td colspan='' class='operate-col' ><div class='td-content' data='"+i+"' title='' layer='#Operations'>&nbsp;</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+source[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+source[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+source[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+source[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>";
						 document.getElementById('engineList').innerHTML = engineList;
					
						 var line="line";
						 averageAccessTime=source[i].averageAccessTime;
						 
						 objSource.push(new ObjSource(source[i].sourceTypeDescribe,source[i].pageViewCount));
						 sourceName.push(source[i].sourceTypeDescribe);
						 
						 GetHourSource(source[i].sourceType,source[i].sourceTypeDescribe,1);
					}
					for (var int = 1; int < (data.result.lastCount)+1; int++) {
		    				page+="<li><a class='pageList' data-page="+int+" href='#'>"+int+"</a></li> "
		    		}
		    		if( data.result.lastCount>1){
		    				page=page+"<li><a id='nextPage' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li><li class='vs_jumped'>跳转至</li><li><input id='pageNo' type='text' class='vs_jump' placeholder=''></li><li class='vs_jumped'>页</li><li class=''><button id='confirmPage' class='btn vs_btn7'>确定</button></li>";
		    		}
					 document.getElementById('pagination').innerHTML = page;
					 pageNo=pageNo;
	    			 pageSize=pageSize;
					 bounceRate=bounceRate/count;
					 countss=countss/count;
					 averageAccessTime=  Math.floor(countss/3600)+":"+Math.floor(countss/60)+":"+(countss%60/100).toFixed(2).slice(-2);
					 bounceRate=parseFloat(bounceRate.toFixed(2));
					 $("#eng_pv_count").text(pageViewCount);
					 $("#eng_visitor_count").text(visitorCount);
					 $("#eng_ip_count").text(ipCount);
					 $("#eng_bounce_ratio").text(bounceRate+"%");
					 $("#eng_avg_visit_time").text(averageAccessTime);

					 $("#engine_pv_count").text(pageViewCount);
					 $("#engine_visitor_count").text(visitorCount);
					 $("#engine_ip_count").text(ipCount);
					 $("#engine_bounce_ratio").text(bounceRate+"%");
					 $("#engine_avg_visit_time").text(averageAccessTime);
					 chastload();
				}
				});

	    	};
	    	$(".pageList").click(function () {
	    		var $this = $(this);
	    		pageNo=$this.data("page");
	    		findList(pageNo,pageSize);
	    	});
	    	
	    	$("#confirmPage").click(function () {	
	    		pageNo=$("#pageNo").val();
	    		findList(pageNo,pageSize);
	    	});
	    	
	    	$("nextPage").click(function () {
	    		var $this = $(this);
	    		pageNo=$this.data("page");
	    		findList(pageNo,pageSize);
	    	});
	    	$(".size").click(function () {
	    		var $this = $(this);
	    	    pageSize=$this.data("size");
	    		findList(pageNo,pageSize);
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
		 sourceTypes=[];
		 sourceName=[];
		 objSource=[];
		 findList(pageNo,pageSize);
		 
	 });
	 
	 $(".source").click(function () {
		 chastload();
		var $this = $(this);	
		var id=$this.data("id");
		sourceTypes=[];
		$.ajax({
			url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSiteEngineByDay",
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
	 });
	 function GetHourSource(sourceType,sourceTypeDescribe,id) {
			$.ajax({
				url:"http://localhost:8080/yxxueyuan-admin/api/stats/source/findSiteEngineByHour",
				data:{
					"searchDate":GetDateStr(addDayCount)
				},
				async:false,
				dateType:"json",
				error:function(e){console.log(e)},
				success:function(data){	 
					var sourceValue=[];
					 var source=data.result;		
					 for (var i = 0; i < source.length; i++) {
						 if(sourceType==source[i].sourceType){
							 if(id==1){
									//浏览量(PV)
								 sourceValue.push(source[i].pageViewCount);
								}else if(id==2){
									//访客数(UV)
									sourceValue.push(source[i].visitorCount);
								}else if(id==3){
									//IP数
									sourceValue.push(source[i].ipCount);
								}else if(id==4){
									//跳出率
									sourceValue.push(source[i].bounceRate);
								}else if(id==5){
									//平均访问时长
									sourceValue.push(source[i].averageAccessTime);
								}else if(id==6){
									//平均访问页数
									sourceValue.push(source[i].averageBrowsePageCount);
								}
							
						 }		 
					}
					 
					 sourceTypes.push(new ObjStory(sourceTypeDescribe, 'line', sourceValue));
					 chastload();
				}
				});
			}
			
			
});


	
	



