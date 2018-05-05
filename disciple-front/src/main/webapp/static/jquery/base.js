// JavaScript Document
//menu 菜单切换
function showMenu(total,id)
{
	for (var i = 0; i < total; i++)
	{
	
		var nav = document.getElementById('nav_'+i);
		var box = document.getElementById('box_'+i);
	
		if (i == id)
		{
			nav.className = 'on';
			box.style.display = "block";
		}
		else
		{
			nav.className = '';
			box.style.display = "none";																		
		}
	}
}

$(document).ready(function(){
  $(".m-nemu li").mouseover(function(){
  $(this).addClass('menu_on');
  $(this).children(".sub_menu").show();
  });
   $(".m-nemu li").mouseout(function(){
  $(this).removeClass('menu_on');
  $(this).children(".sub_menu").hide();
  });
});
//图片放大
$(function(){
var imgWid = 0 ;
var imgHei = 0 ; //变量初始化
var big = 1.1;//放大倍数
$(".m-index-box .l").hover(function(){
$(this).find("img").stop(true,true);
var imgWid2 = 0;var imgHei2 = 0;//局部变量,l
imgWid = $(this).find("img").width();
imgHei = $(this).find("img").height();
imgWid2 = imgWid * big;
imgHei2 = imgHei * big;
$(this).find("img").css({"z-index":2});
$(this).find(".shade").animate({"left":"211px"});
$(this).find("img").animate({"width":imgWid2,"height":imgHei2,"margin-left":"-10px","margin-top":"-19px"});
},function(){$(this).find("img").stop().animate({"width":imgWid,"height":imgHei,"margin-left":"0px","margin-top":"0px","z-index":1});
$(this).find(".shade").animate({"left":"-201px"});
});
	
})