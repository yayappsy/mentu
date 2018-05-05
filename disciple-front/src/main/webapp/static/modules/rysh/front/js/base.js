$(document).ready(function(){
	$('.m-content .backtobottom').hide();
	
	$('.m-top .login .register_bnt').click(function(){
		$('.user_box').show();
		$('.register_box').show();
		$('.login_box').hide();
		});
	$('.user_box .close1').click(function(){
		$('.user_box').hide();
		$('.register_box').hide();
		});
	$('.m-top .login .login_bnt').click(function(){
		$('.user_box').show();
		$('.login_box').show();
		$('.register_box').hide();
		});
	$('.user_box .close2').click(function(){
		$('.user_box').hide();
		$('.login_box').hide();
		});
	$('.m-content').hide();
	$(window).scroll(function() {
			var jilu = $(window).scrollTop();
	
		
			if(jilu > 3000){
				$(".s1").show();
				$(".s1").animate({top:'70px'},"slow");
			}	
			if(jilu > 3050){
				$(".s2").show();
				$(".s2").animate({top:'70px'},"slow");
			}	
			if(jilu > 3100){
				$(".s3").show();
				$(".s3").animate({top:'70px'},"slow");
			}	
			if(jilu > 3150){
				$(".s4").show();
				$(".s4").animate({top:'70px'},"slow");
			}	
			if(jilu > 3200){
				$(".s5").show();
				$(".s5").animate({top:'70px'},"slow");
			}	
			
			/*
			console.log(jilu);
			*/	
			var body_h = $("body").height();
			var window_h = window.screen.height;
			var height = body_h-jilu-window_h;
	
			if(jilu>=430){
				$('.m-content').show();
			}else{
				$('.m-content').hide();
			}
			if(height<300){
				$('.m-content .backtobottom').show();
				}else{
					$('.m-content .backtobottom').hide();
					}				
					
		});
		
		
			
		/*返回顶部*/
		$('.backtobottom').click(function() {
       $('html, body').animate({
            scrollTop: 0
        },
        'slow');
        return false;
    });
	
	$('#c_phone').mouseover(function(){
		$('.c_phone').show();
		}).mouseout(function(){
			$('.c_phone').hide();
			});
			
	$('#c_weixin').mouseover(function(){
		$('.c_weixin').show();
		}).mouseout(function(){
			$('.c_weixin').hide();
			});
	
	$('#c_message').click(function(){
		$('.c_message').show();
		});
	$('.c_message .close').click(function(){
		$('.c_message').hide();
		});
		
	

});