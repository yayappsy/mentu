/**
 * Created by admin on 2017/4/11.
 */
//折线图1
var visitDate=[];
var visitViewCount=[];
var visitCount=[];
var countdown=60; 
function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
	}


$(function () {
	findList(1,20);
	settime();
	function chastload(){
    $(window).resize(function(){
        //console.log($(".su_div4").width());
        $("#chart_vsline1").css("width",$(".vs_div2").width());
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart_vsline1'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '折线图'
        },
        tooltip: {},
        legend: {
            data:['浏览量(PV)','访客数(UV)']
        },
        xAxis: {
            data: visitDate
        },
        yAxis: {},
        series: [
            {
                name: '浏览量(PV)',
                type: 'line',
                data: visitViewCount
            },
            {
                name: '访客数(UV)',
                type: 'line',
                data: visitCount
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	}
	

	function settime() { 
	
	if (countdown == 0) { 
	countdown = 60; 
	realTimeload();
	visitDate=[];
	visitViewCount=[];
	visitCount=[];
	realTimeloadFold();
	$("#timer").text(countdown);
	} else { 
	countdown--; 
	$("#timer").text(countdown);
	} 
	setTimeout(function() { 
	settime() 
	},1000) 

	} 
	
	function realTimeloadFold(){
		$.ajax({
			url:"http://localhost:8080/yxxueyuan-admin/api/visit/track/findLatelyVisitor",
			data:{
				"searchDate":getNowFormatDate()
			},
			async:false,
			dateType:"json",
			error:function(e){console.log(e)},
			success:function(data){
				var realTimeVisit=data.result;
				for (var i = 0; i < realTimeVisit.length; i++) {
					var mm=realTimeVisit[i].realTime;
					var date=new Date(mm);	
					console.log(date);
					var h=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();		
					var m=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
							
					visitDate.push(h+":"+m);
					visitViewCount.push(realTimeVisit[i].realTimeViewCount);
					visitCount.push(realTimeVisit[i].realTimeVisitorCount);
				}	
				
				chastload();		
			}
			});
		}
	
	
	
	function realTimeload(){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/visit/track/findVisitorCount",
		data:{
			"searchDate":getNowFormatDate()
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			chastload();
			$("#thisVisitor").text(data.result.realTimeVisitor);
			$("#thisTime").text(getNowFormatDate());
		}
		});
	}
	var pageNo=1;
	var pageSize=20;
	function findList(pageNo,pageSize){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/visit/track/findList",
		data:{
			"pageNo":pageNo,
			"pageSize":pageSize
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			var visitTrack=data.result.dataList;
			var visitorList="";
			var page=""
			for (var i = 0; i < visitTrack.length; i++) {
				visitorList+="<tr class='line' id='table-tr_"+i+"'><td class='empty-col' data='"+i+"'  colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='expand-col' data='"+i+"' id='expand_table-tr_"+i+"' colspan=''><div class='td-content' title=''>&nbsp;</div></td><td class='number-col'   colspan=''><div class='td-content' title='"+(i+1)+"'>"+(i+1)+"</div></td><td class='table-index null' data='[object Object]'  colspan=''><div class='td-content' title=''></div></td><td class='start_time'   colspan=''><div class='td-content' title=''><span title='"+visitTrack[i].time+"'>"+visitTrack[i].time+"</span></div></td><td class='area'   colspan=''><div class='td-content' title=''><span title='"+visitTrack[i].city+"'>"+visitTrack[i].city+"</span></div></td><td class='url source'   colspan=''><div class='td-content' title=''><a href='"+visitTrack[i].searchSite+"' title='"+visitTrack[i].searchSite+"' target='_blank'>"+visitTrack[i].searchSite+"</a></div></td><td class='url access_page'   colspan=''><div class='td-content' title=''><a href='"+visitTrack[i].referrer+"' title='"+visitTrack[i].referrer+"' target='_blank'>"+visitTrack[i].referrer+"</a></div></td><td class='searchword'   colspan=''><div class='td-content' title=''><span title='"+visitTrack[i].searchKeywords+"'>"+visitTrack[i].searchKeywords+"</span></div></td><td class='ip ip'   colspan=''><div class='td-content' title=''><span title='"+visitTrack[i].ip+"'>"+visitTrack[i].ip+"</span><a href='javascript:void(0)' layer='#Operations' class='operate-btn' data-operatetype='ip' data-label="+visitTrack[i].ip+" data-showall='0'></a></div></td><td class='visitorId visitorId'   colspan=''><div class='td-content' title=''><span title='"+visitTrack[i].visitorId+"' class='ellipsis'>"+visitTrack[i].visitorId+"</span><a href='javascript:void(0)' layer='#Operations' class='operate-btn' data-operatetype='visitor' data-label='"+visitTrack[i].visitorId+"' data-showall='0'></a></div></td><td class='time2 visit_time'   colspan=''><div class='td-content' title=''>"+visitTrack[i].timeOnPage+"</div></td><td class='number visit_pages'   colspan=''><div class='td-content' title=''>"+visitTrack[i].pageViewDepth+"</div></td><td class='empty-col last'   colspan=''><div class='td-content' title=''>&nbsp;</div></td></tr>";
			}
			for (var int = 1; int < (data.result.lastCount)+1; int++) {
				page+="<li><a class='pageList' data-page="+int+" href='#'>"+int+"</a></li> "
			}
			if( data.result.lastCount>1){
				page=page+"<li><a id='nextPage' href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li><li class='vs_jumped'>跳转至</li><li><input id='pageNo' type='text' class='vs_jump' placeholder=''></li><li class='vs_jumped'>页</li><li class=''><button id='confirmPage' class='btn vs_btn7'>确定</button></li>";
			}
			 
			 document.getElementById('visitorList').innerHTML = visitorList;
			 document.getElementById('pagination').innerHTML = page;
			 realTimeload();
			 realTimeloadFold();
			 chastload();
			 pageNo=pageNo;
			 pageSize=pageSize;
		}
		});
}
	
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
	
	
	
	
});
$(".vs_ul1").on("click","li",function () {
    $(".vs_ul1 li").removeClass("vs_active");
    $(this).addClass("vs_active");
});

var vskey1 = true;
$(".vs_btn5").click(function () {
    $(".vs_span4").html("");
    if(vskey1 == true){
        $(".vs_div7").hide();
        $(".vs_span4").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        $(".vs_span5").html("展开筛选");
        vskey1 = false;
    }else{
        $(".vs_div7").fadeIn();
        $(".vs_span4").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".vs_span5").html("收起筛选");
        vskey1 = true;
    }
});
