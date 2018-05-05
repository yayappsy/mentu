$(document).ready(function(){

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	var objSource=[];
	var resultList;
	 function ObjStory(title,start,end,allDay) //声明对象
	 {
	     this.title = title;
	     this.start = start;
	     this.end= end;  
	     this.allDay= allDay; 
	 }
	
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/a/course/timetable/findTimetable",
		data:{
			"courseId":$("#courseId").val(),
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){	
			resultList=data.result;
			console.log(resultList);
			for (var i = 0; i < resultList.length; i++) {
				var beginTime=new Date(resultList[i].courseDate);
				var arr=resultList[i].lessonPeriod
				var begin_strb=arr.substring(0,2);
				var begin_stre=arr.substring(3,4);
				var end_strb=arr.substring(6,8);
				var end_stre=arr.substring(9,10);
				beginTime=new Date(beginTime.getFullYear(),beginTime.getMonth(),beginTime.getDate(),begin_strb,begin_stre);
				var endTime=new Date(beginTime.getFullYear(),beginTime.getMonth(),beginTime.getDate(),end_strb,end_stre);
				objSource.push(new ObjStory(resultList[i].title,beginTime,endTime,false));
			}
		}
	});	
	
	if($('.calendar').length > 0){
		$('.calendar').fullCalendar({
			  dayClick: function(date, jsEvent, view) {
				 
			         var ids=$('input[name="ids"]');
			         ids.attr('checked',false);
			        alert('Clicked on: ' + date);		
			        $.ajax({
			    		url:"http://localhost:8080/yxxueyuan-admin/a/course/timetable/findTimetable",
			    		data:{
			    			"courseId":$("#courseId").val(),
			    			"courseDate":date
			    		},
			    		async:false,
			    		dateType:"json",
			    		error:function(e){console.log(e)},
			    		success:function(data){	
			    			$("#courseDate").val(date);
			    			resultList=data.result;
			    			console.log(resultList);
			    			for (var i = 0; i < resultList.length; i++) {
			    				$(".checkbox").each(function(){					
									if($(this).val()==resultList[i].lessonPeriod){
										console.log($(this).val());
										console.log(resultList[i].lessonPeriod);
										this.checked=true;
									}	
									});	
			    		}
			    		}
			    	});			        		      
			        /*console.log('Coordinates: ' + jsEvent + ',' + jsEvent);
			        console.log('Current view: ' + view);*/
			        // change the day's background color just for fun
			        //$(this).css('background-color', 'red');

			    },
			header: {
				left: 'prev,next,today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			buttonText:{
				today:'跳转到当天'
			},
			editable: true,
			events: objSource/*[
			{
				title: '全天事项',
				start: new Date(y, m, 1)
			},
			{
				title: '长距离项目',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			},
			{
				id: 999,
				title: '重复项目',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			},
			{
				id: 999,
				title: '重复项目',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			},
			{
				title: '会议',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			},
			{
				title: '午餐',
				start: new Date(y, m, d, 12, 0),
				end: new Date(y, m, d, 14, 0),
				allDay: false
			},
			{
				title: '生日聚会',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			},
			{
				title: '点击跳转谷歌',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://www.google.com/'
			}
			]*/
		});
		
	}
	
	
});