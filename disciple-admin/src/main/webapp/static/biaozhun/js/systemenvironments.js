/**
 * Created by admin on 2017/4/13.
 */
var system=[];
var type=[];
var typeName=[]
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
$(".pe_ul1").on("click","li",function () {
    $(".pe_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});
//柱形图
$(function () {
	findList();
	function findList(){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/browser/findSiteBrowser",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			 var Browse=data.result;
			 var list="";
			 var pageViewCount=0;
			 var visitorCount=0;
			 var ipCount=0;
			 var bounceRate=0;
			 var averageBrowsePageCount=0;
			
			 var averageAccessTime="";
			 var countss=0;
			 var count=0;
			 var systemList="";
			 for (var i = 0; i < Browse.length; i++) {
				 count++;
				 pageViewCount=pageViewCount+Browse[i].pageViewCount
				 visitorCount=visitorCount+Browse[i].visitorCount
				 ipCount=ipCount+Browse[i].ipCount
				 bounceRate=bounceRate+Browse[i].bounceRate
				 averageBrowsePageCount=averageBrowsePageCount+Browse[i].averageBrowsePageCount
				 averageAccessTime=Browse[i].averageAccessTime;
				 var date=new Date(averageAccessTime);
				 var mm=date.getMinutes();
				 var ss=date.getSeconds();
				  countss=countss+(mm*60+ss);
				  var averageAccessTimes=  Math.floor(mm*60+ss/3600)+":"+Math.floor(mm*60+ss/60)+":"+(mm*60+ss%60/100).toFixed(2).slice(-2);
			     system.push(Browse[i].pageViewCount)		
			     type.push(Browse[i].browserName)	
			     systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+i+1+"'>"+i+1+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+Browse[i].browserName+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+Browse[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+Browse[i].ipCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+Browse[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+Browse[i].bounceRate+"</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>";
				 document.getElementById('systemList').innerHTML = systemList;
			     	 
			}
			 typeName.push("浏览量(PV)");
			 console.log(count);
			 bounceRate=bounceRate/count;
			 countss=countss/count;
			 averageAccessTime=  Math.floor(countss/3600)+":"+Math.floor(countss/60)+":"+(countss%60/100).toFixed(2).slice(-2);
			  $("#bpageViewCount").text(pageViewCount);
			  $("#bvisitorCount").text(visitorCount);
			  $("#bipCount").text(ipCount);
			  $("#bbounceRate").text(bounceRate+"%");
			  $("#baverageBrowsePageCount").text(averageAccessTime);
			  
			  $("#pvCount").text(pageViewCount);
			  $("#visitorCount").text(visitorCount);
			  $("#ipCount").text(ipCount);
			  $("#bounceRate").text(bounceRate+"%");
			  $("#avgVisitTime").text(averageAccessTime);
			/* for (var i = 0; i < keywords.length; i++) {
				 var b=keywords[i].pageViewCount/pageViewCount*100
				 a=parseFloat(b.toFixed(2));
				 list +="<tr><td class='al url'><span class='ellipsis' title='百度推广客户端'>"+keywords[i].searchKeywords+"</span></td><td class='ar'>"+keywords[i].pageViewCount+"</td><td class='ratio'><div title='34.21%' style='background-color:#DCEBFE; width:"+a+"%;'>"+a+"%</div></td></tr>";
				
			}
			 document.getElementById('searchKeywordlist').innerHTML = list;*/
		}
		});
	};
	chastload();
	$(".system").click(function () {
		 typeName=[];
		var $this = $(this);	
		var url=$(".systemType.active").data("url");	
		var searchParameter=$this.data("id");
		console.log(searchParameter);
		$.ajax({
			url:url,
			async:false,
			data:{
                "searchDate":GetDateStr(addDayCount)
			},
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){			
				 var systemEnvironment=data.result;
				 system=[];
				 if(searchParameter=='pageViewCount'){	
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].pageViewCount)
					 }
					 typeName.push("浏览量(PV)");
				 }else if(searchParameter=='visitorCount'){
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].visitorCount)	
					 }
					 typeName.push("访客数(UV)");
				 }else if(searchParameter=='ipCount'){	
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].ipCount) 
					 }
					 typeName.push("IP数");
				 }else if(searchParameter=='bounceRate'){	
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].bounceRate)
					 }
					 typeName.push("跳出率");
				 }else if(searchParameter=='averageAccessTime'){
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].averageAccessTime)
					 }
					 typeName.push("平均访问时长");
				 }else if(searchParameter=='averageBrowsePageCount'){
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].averageBrowsePageCount)
					 }
					 typeName.push("平均访问页数"); 
				 }else{
					 for (var i = 0; i < systemEnvironment.length; i++) {
					 system.push(systemEnvironment[i].pageViewCount)
					 }
				 }
				
				 	 
			 chastload(); 
			 var systemList="";
			 for (var i = 0; i < systemEnvironment.length; i++) {
			 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].browserName+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].averageAccessTime+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
			 }
			 document.getElementById('systemList').innerHTML = systemList;
			 }
			
			});	
	});
	
	
$(".systemType").click(function () {
		
		var $this = $(this);	
		var url=$this.data("url");	
		var id=$this.data("id");	
		$.ajax({
			url:url,
			async:false,
			data:{
                "searchDate":GetDateStr(addDayCount)
			},
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){			
				 var systemEnvironment=data.result;
				 system=[];
				 type=[];
				 typeName=[];
				 var pageViewCount=0;
				 var visitorCount=0;
				 var ipCount=0;
				 var bounceRate=0;
				 var averageBrowsePageCount=0;
				
				 var averageAccessTime="";
				 var countss=0;
				 var systemList="";
				 var count=0;
				for (var i = 0; i < systemEnvironment.length; i++) {
					count++;
					 pageViewCount=pageViewCount+systemEnvironment[i].pageViewCount;
					 visitorCount=visitorCount+systemEnvironment[i].visitorCount;
					 ipCount=ipCount+systemEnvironment[i].ipCount;
					 bounceRate=bounceRate+systemEnvironment[i].bounceRate;
					 averageAccessTime=systemEnvironment[i].averageAccessTime;
					 var date=new Date(averageAccessTime);
					 var mm=date.getMinutes();
					 var ss=date.getSeconds();
					  countss=countss+(mm*60+ss);
					  var averageAccessTimes=  Math.floor(mm*60+ss/3600)+":"+Math.floor(mm*60+ss/60)+":"+(mm*60+ss%60/100).toFixed(2).slice(-2);
					 system.push(systemEnvironment[i].pageViewCount)
					
					 if(id==1){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].browserName+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].browserName)
					 }else if(id==2){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].os+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].os);
						 $("#text_type").text("网络设备类型");
					 }else if(id==3){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].resolution+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].resolution);
						 $("#text_type").text("屏幕分辨率");
					 }else if(id==4){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].colorDepth+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].colorDepth);
						 $("#text_type").text("屏幕颜色");
					 }else if(id==5){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].flashVersion+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].flashVersion);
						 $("#text_type").text("flash版本");
					 }else if(id==6){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].javaEnabled+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].javaEnabled);
						 $("#text_type").text("是否支持java");
					 }else if(id==7){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].language+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].language);
						 $("#text_type").text("语言环境");
					 }else if(id==8){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].cookieEnabled+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].cookieEnabled);
						 $("#text_type").text("是否支持Cookie");
					 }else if(id==9){
						 systemList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index client_browser' data='[object Object]'  colspan=''><div class='td-content' title=''>"+systemEnvironment[i].isp+"</div></td><td class='number group pv_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].pageViewCount+"</div></td><td class='number visitor_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].visitorCount+"</div></td><td class='number ip_count'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].ipCount+"</div></td><td class='ratio group bounce_ratio'   colspan=''><div class='td-content' title=''>"+systemEnvironment[i].bounceRate+"%</div></td><td class='time avg_visit_time'   colspan=''><div class='td-content' title=''>"+averageAccessTimes+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>"
						 type.push(systemEnvironment[i].isp);
						 $("#text_type").text("网络提供商");
					 }
					
					}
					 typeName.push("浏览量(PV)");
					
			 chastload(); 
			 countss=countss/count;
			 averageAccessTime=  Math.floor(countss/3600)+":"+Math.floor(countss/60)+":"+(countss%60/100).toFixed(2).slice(-2);
			 document.getElementById('systemList').innerHTML = systemList;
			 bounceRate=bounceRate/count;
			 bounceRate=parseFloat(bounceRate.toFixed(2)); 
			  $("#bpageViewCount").text(pageViewCount);
			  $("#bvisitorCount").text(visitorCount);
			  $("#bipCount").text(ipCount);
			  $("#bbounceRate").text(bounceRate+"%");
			  $("#baverageBrowsePageCount").text(averageAccessTime);
			 
			  $("#pvCount").text(pageViewCount);
			  $("#visitorCount").text(visitorCount);
			  $("#ipCount").text(ipCount);
			  $("#bounceRate").text(bounceRate+"%");
			  $("#avgVisitTime").text(averageAccessTime);
			 }
			
			});	
	});
	
	function chastload(){
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
		            data:[typeName]
		        },
		        xAxis: {
		            data: type
		        },
		        yAxis: {},
		        series: [{
		            name: typeName,
		            type: 'bar',
		            data: system
		        }]
		    };

		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
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
		 system=[];
		 type=[];
		 typeName=[];
		 findList();
		 
	 });
	 
});