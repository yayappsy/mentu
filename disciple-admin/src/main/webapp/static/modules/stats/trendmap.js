/**
 * Created by admin on 2017/4/12.
 */

var sourceTypes=[];
var packages=[];
var a=[];
var objSource=[];
var sourceName=[];


var sourceTypesb=[];
var packagesb=[];
var ab=[];
var objSourceb=[];
var sourceNameb=[];

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
	            text: '套餐累计金额折线图'
	        },
	        tooltip: {},
	        legend: {
	            data:['总金额']
	        },
	        xAxis: {
	            data:sourceName
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
	                name:'套餐所占套餐订单比例',
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
	    
	    
	    
	    
	    $(window).resize(function(){
	        //console.log($(".su_div4").width());
	        $("#chart_srline2").css("width",$(".sr_div3").width());
	    });
	    // 基于准备好的dom，初始化echarts实例
	    var myChart2 = echarts.init(document.getElementById('chart_srline2'));

	    // 指定图表的配置项和数据
	    var option = {
	        title: {
	            text: '课时累计金额折线图'
	        },
	        tooltip: {},
	        legend: {
	            data:['总金额']
	        },
	        xAxis: {
	            data:sourceNameb
	        },
	        yAxis: {},
	        series: sourceTypesb

	             
	    };
	    // 使用刚指定的配置项和数据显示图表。
	    myChart2.setOption(option);
	    
	    
	    $(window).resize(function(){
	        //console.log($(".su_div4").width());
	        $("#chart_srpie2").css("width",$(".sr_div2").width());
	    });
	    // 基于准备好的dom，初始化echarts实例
	    var myChart3 = echarts.init(document.getElementById('chart_srpie2'));

	    // 指定图表的配置项和数据
	    var option = {
	        tooltip: {
	            trigger: 'item',
	            formatter: "{a} <br/>{b}: {c} ({d}%)"
	        },
	        legend: {
	            orient: 'vertical',
	            x: 'left',
	            data:sourceNameb
	        },
	        series: [
	            {
	                name:'课时所占课时订单比例',
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
	                data:objSourceb
	            }
	        ]
	    };
	    // 使用刚指定的配置项和数据显示图表。
	    myChart3.setOption(option);
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
		url:"http://123.56.126.209:10080/yxxueyuan-admin/api/stats/package/findSitePackage",
		data:{
			"beginDate":$("#beginDate").val(),
			"endDate" :$("#endDate").val(),
			"categoryId" :'3'
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			 var source=data.result;
			 console.log(source);
			 var amount=0;
			 var count=0;
			 var productName="";					
			 for (var i = 0; i < source.length; i++) {
				 console.log(source[i].salesCount);
				 objSource.push(new ObjSource(source[i].productName,source[i].salesCount));
				 sourceName.push(source[i].productName);
				 a.push(source[i].amount);
			}
			 sourceTypes.push(new ObjStory('消费总金额', 'line', a));
			 console.log(packages);
			  chastload();
		}
		
		});
	
	$.ajax({
		url:"http://123.56.126.209:10080/yxxueyuan-admin/api/stats/package/findSitePackage",
		data:{
			"beginDate":$("#beginDate").val(),
			"endDate" :$("#endDate").val(),
			"categoryId" :'1'
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	 
			 var source=data.result;
			 console.log(source);
			 var amount=0;
			 var count=0;
			 var productName="";					
			 for (var i = 0; i < source.length; i++) {
				 console.log(source[i].salesCount);
				 objSourceb.push(new ObjSource(source[i].productName,source[i].salesCount));
				 sourceNameb.push(source[i].productName);
				 ab.push(source[i].amount);
			}
			 sourceTypesb.push(new ObjStory('消费总金额', 'line', ab));
			  chastload();
		}
		
		});
	
	
	
	 };
	$("#search").click(function () {
		 sourceTypes=[];
		 packages=[];
		 a=[];
		 objSource=[];
		 sourceName=[];


		 sourceTypesb=[];
		 packagesb=[];
		 ab=[];
		 objSourceb=[];
		 sourceNameb=[];
		 chastload();
		 findList();	
	});
	

});


