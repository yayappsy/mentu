/**
 * Created by admin on 2017/4/13.
 */
$(".xl_ul1").on("click","li",function () {
    $(".xl_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".xl_ul2").on("click","li",function () {
    $(".xl_ul2 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});
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
	function findList(){
	$.ajax({
		url:"http://localhost:8080/yxxueyuan-admin/api/stats/visitor/findSiteByVisitor",
		data:{
			"searchDate":GetDateStr(addDayCount)
		},
		async:false,
		dateType:"json",
		error:function(e){console.log(e)},
		success:function(data){
			var visitor=data.result;
			var visitorCount=0;
			var newVisitorCount=0;
			var oldVisitorCount=0;
			var newVisitRate=0;
			var oldVisitRate=0;
			var newPageViewCount=0;
			var oldPageViewCount=0;
			var newIpCount=0;
			var oldIpCount=0;
			var newBounceRate=0;
			var oldBounceRate=0;
			for (var i = 0; i < visitor.length; i++) {
				visitorCount=visitorCount+visitor[i].visitorCount;
				if(visitor[i].ifNewVisitor==true){
					newPageViewCount=visitor[i].pageViewCount
					newVisitorCount=visitor[i].visitorCount;
					newIpCount=visitor[i].ipCount;
					newBounceRate=visitor[i].bounceRate;
					$("#nPageViewCount").text(newPageViewCount);
					$("#nVisitorCount").text(newVisitorCount);
					$("#nIpCount").text(newIpCount);
					$("#nBounceRate").text(newBounceRate);
					$("#nAverageAccessTime").text(visitor[i].averageAccessTime);
					$("#nAverageBrowsePageCount").text(visitor[i].averageBrowsePageCount);	
					
					$("#vnPageViewCount").text(visitor[i].pageViewCount);
					$("#vnVisitorCount").text(visitor[i].visitorCount);
					$("#vnIpCount").text(visitor[i].ipCount);
					$("#vnBounceRate").text(visitor[i].bounceRate);
					$("#vnAverageAccessTime").text(visitor[i].averageAccessTime);
					
				}else{
					oldPageViewCount=visitor[i].pageViewCount
					oldVisitorCount=visitor[i].visitorCount;
					oldIpCount=visitor[i].ipCount;
					oldBounceRate=visitor[i].bounceRate;
					
					$("#oPageViewCount").text(oldPageViewCount);
					$("#oVisitorCount").text(oldVisitorCount);
					$("#oIpCount").text(oldIpCount);
					$("#oBounceRate").text(oldBounceRate);
					$("#oAverageAccessTime").text(visitor[i].averageAccessTime);
					$("#oAverageBrowsePageCount").text(visitor[i].averageBrowsePageCount);	
					
					$("#onPageViewCount").text(visitor[i].pageViewCount);
					$("#onVisitorCount").text(visitor[i].visitorCount);
					$("#onIpCount").text(visitor[i].ipCount);
					$("#onBounceRate").text(visitor[i].bounceRate);
					$("#onAverageAccessTime").text(visitor[i].averageAccessTime);
				}
			}
			newVisitRate=newVisitorCount/visitorCount*100;
			oldVisitRate=oldVisitorCount/visitorCount*100;
			newVisitRate=parseFloat(newVisitRate.toFixed(2));
			
			oldVisitRate=parseFloat(oldVisitRate.toFixed(2));
			$("#newVisitorRate").text(newVisitRate+'%');
			$("#oldVisitorRate").text(oldVisitRate+'%');
			
			$("#pageViewCount").text(newPageViewCount+oldPageViewCount);
			$("#visitorCount").text(newVisitorCount+oldVisitorCount);
			$("#ipCount").text(newIpCount+oldIpCount);
			$("#bounceRate").text((newBounceRate+oldBounceRate)/2+'%');
		}
		});
	
	
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
			var sourceSiteList="<tr class='th nob'><td class='c'>排名</td><td class='url'>来源网站</td><td class='tdr'>访客量(UV)</td></tr>"
			var sourceSiteoOldList="<tr class='th nob'><td class='c'>排名</td><td class='url'>来源网站</td><td class='tdr'>访客量(UV)</td></tr>"
			for (var i = 0; i < source.length; i++) {
				sourceSiteList+="<tr><td class='c'>"+(i+1)+"</td><td class='al url'><span title='"+source[i].searchSite+"'>"+source[i].searchSite+"</span></td><td class='tdr'>"+source[i].newVisitorCount+"</td></tr>"
				sourceSiteoOldList+="<tr><td class='c'>"+(i+1)+"</td><td class='al url'><span title='"+source[i].searchSite+"'>"+source[i].searchSite+"</span></td><td class='tdr'>"+(source[i].visitorCount-source[i].newVisitorCount)+"</td></tr>"
			}		
			document.getElementById('sourceSiteList').innerHTML = sourceSiteList;
			document.getElementById('sourceSiteoOldList').innerHTML = sourceSiteoOldList;
		
		}
		});
	};
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
		 findList();
		 
	 });
	
});