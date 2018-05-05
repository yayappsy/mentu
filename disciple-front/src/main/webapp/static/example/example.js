$(function () {

    $("#btnPublisth").on("click",function(){
    	$.ajax({
    		 type: 'GET',
    		  url: ctxWx+'/task/publish',
    		  // data to be added to query string:
    		  data: { taskName: 'ceshi',bossId:"1" },
    		  // type of data we are expecting in return:
    		  dataType: 'json',
    		  success: function(data){
    	         alert("任务名称:"+data.task.name);
    		  },
    		  error: function(xhr, type){
    		    console.log(type);
    		  }
    	});
    });
    $("#btnAttention").on("click",function(){
    	$.ajax({
    		type: 'GET',
    		url: ctxWx+'/task/attention',
    		// data to be added to query string:
    		data: { taskName: 'ceshi',bossId:"1" },
    		// type of data we are expecting in return:
    		dataType: 'json',
    		success: function(data){
    			alert("任务名称:"+data.task.name);
    		},
    		error: function(xhr, type){
    			console.log(type);
    		}
    	});
    });
    $("#btnReceived").on("click",function(){
    	$.ajax({
    		type: 'GET',
    		url: ctxWx+'/task/received',
    		// data to be added to query string:
    		data: { taskId: '363cfa6ef05e4009b92d89ffbade98bd' },
    		// type of data we are expecting in return:
    		dataType: 'json',
    		success: function(data){
    			alert("任务名称:"+data.task.name);
    		},
    		error: function(xhr, type){
    			console.log(type);
    		}
    	});
    });
    $("#btnGetLink").on("click",function(){
    	$.ajax({
    		type: 'GET',
    		url: ctxWx+'/task/attention',
    		// data to be added to query string:
    		data: { taskName: 'ceshi',bossId:"1" },
    		// type of data we are expecting in return:
    		dataType: 'json',
    		success: function(data){
    			alert("任务名称:"+data.task.name);
    		},
    		error: function(xhr, type){
    			console.log(type);
    		}
    	});
    });
    $("#sendRedPack").on("click",function(){
    	
    	$.ajax({
    		type: 'GET',
    		url: ctxWx+'/payTransfer/sendRedPack',
    		// data to be added to query string:
    		data: { "redPack.id": '1',"memberId":"1" },
    		// type of data we are expecting in return:
    		dataType: 'json',
    		success: function(data){
    			if(data.message.type=="success"){
    				console.log("success")
    			}else{
    				console.log(data.message.code)
    			}
    		},
    		error: function(xhr, type){
    			console.log(type);
    		}
    	});
    });


    // .container 设置了 overflow 属性, 导致 Android 手机下输入框获取焦点时, 输入法挡住输入框的 bug
    // 相关 issue: https://github.com/weui/weui/issues/15
    // 解决方法:
    // 0. .container 去掉 overflow 属性, 但此 demo 下会引发别的问题
    // 1. 参考 http://stackoverflow.com/questions/23757345/android-does-not-correctly-scroll-on-input-focus-if-not-body-element
    //    Android 手机下, input 或 textarea 元素聚焦时, 主动滚一把
    if (/Android/gi.test(navigator.userAgent)) {
        window.addEventListener('resize', function () {
            if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
                window.setTimeout(function () {
                    document.activeElement.scrollIntoViewIfNeeded();
                }, 0);
            }
        })
    }
});
