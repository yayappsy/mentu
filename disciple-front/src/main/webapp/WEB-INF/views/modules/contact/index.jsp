<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<title><spring:message code="联系我们" /></title>
<meta name="decorator" content="front_home" />
<link href="${ctxStatic }/modules/rysh/front/css/singlepage.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css">
	<style type="text/css">
	
	#allmap {height: 100%;width:100%;overflow: hidden;}
	
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.l_box .type li').mouseover(function() {
			$('.l_box .type li a').removeClass('current');
			$(this).children('a').addClass('current');

			var index = $(this).index();
			$('.news_box').hide();
			$(".l_box .news_box:eq(" + index + ")").show();
		});
	});
</script>

</head>

<body>
	<div class="m-banner"></div>
	<!--当前位置-->
	<div class="m-position">
		<ul class="clearfix">
			<li>当前位置</li>
			<li>&gt;</li>
			<li>首页</li>
			<li>&gt;</li>
			<li>${article.category.name}</li>
		</ul>
	</div>
	<!--当前位置 end-->
	<!--main-->
	<div class="m-main clearfix">
		<div class="s_name">${article.title}</div>
		<!--联系我们-->
		<div class="s_box3">${fns:unescapeHtml(article.content)}</div>
		<!--联系我们 end-->
		
		

<!--     
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.3&key=c307303d4ea2ab38296554859285ebe1"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

<div id="container"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
                <label>按关键字搜索：</label>
            </td>
            <td class="column2">
                <label>左击获取经纬度：</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
            </td>
            <td class="column2">
                <input type="text" readonly="true" id="lnglat">
            </td>
        </tr>
    </table>
</div>

<script>
    var map = new AMap.Map('container', {
        resizeEnable: true,
        zoom:13,
        mapStyle:'fresh', 
        center: [116.43434, 39.85806],
        isHotspot: true
    });

    new AMap.Marker({
        map: map,
        position: [116.368904, 39.923423]
    });
    new AMap.Marker({
        map: map,
        position: [116.387271, 39.922501]
    });

    new AMap.Marker({
        map: map,
        position: [116.418904, 39.823423]
    });
    new AMap.Marker({
        map: map,
        position: [116.427271, 39.872501]
    });
    
    AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView','AMap.Geolocation'],
    	    function(){
    	        map.addControl(new AMap.ToolBar());

    	        map.addControl(new AMap.Scale());

    	        map.addControl(new AMap.OverView({isOpen:true}));
   	 
    	        map.addControl(new AMap.Geolocation());
    	});
    new AMap.Marker({
        map: map,
		position: [116.43434, 39.85806],
        icon: new AMap.Icon({            
            size: new AMap.Size(40, 50),  //图标大小
            image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
            imageOffset: new AMap.Pixel(0, -60)
        })        
    });

    map.setFitView();
    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
        var lnglat= new AMap.LngLat(116.368904, 39.923423);
       
        var a=lnglat.distance([116.43434, 39.85806]);
        alert(a);
        //alert('两点间距离为：' + lnglat.distance([e.lnglat.getLng()+ ',' + e.lnglat.getLat()]) + '米');
       
    });
    var auto = new AMap.Autocomplete({
        input: "tipinput"
    });
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        if (e.poi && e.poi.location) {
            map.setZoom(15);
            map.setCenter(e.poi.location);
        }
    }
   
   

   
</script>
</body>
</html> -->
		
		
			
	 <div class="lxwmMap" style="height: 800px;">
	
	<div id="allmap" style="overflow: hidden;position: relative;/* z-index: 0; */color: rgb(0, 0, 0);text-align: left;background-color: rgb(243, 241, 236); */">
	<div style="overflow: visible; position: absolute; z-index: 0; left: 0px; top: 0px; cursor: url(&quot;http://api0.map.bdimg.com/images/openhand.cur&quot;) 8 8, default;">
	<div class="BMap_mask" style="position: absolute; left: 0px; top: 0px; z-index: 9; overflow: hidden; -webkit-user-select: none; width: 375px; height: 375px;"></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 200;">
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 800;"></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 700;">
	<span class="BMap_Marker BMap_noprint" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; cursor: pointer; width: 200px; height: 100px; left: 69px; top: 113px; z-index: -7983162; background: url(&quot;http://api0.map.bdimg.com/images/blank.gif&quot;);" title=""></span>
	<span class="BMap_Marker BMap_noprint" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; cursor: pointer; width: 200px; height: 100px; left: 139326px; top: 296658px; z-index: -6245328; background: url(&quot;http://api0.map.bdimg.com/images/blank.gif&quot;);" title=""></span>
	<span class="BMap_Marker BMap_noprint" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; cursor: pointer; width: 18px; height: 18px; left: 329px; top: 108px; z-index: 19000000; -webkit-user-select: none; display: none; background: url(&quot;http://api0.map.bdimg.com/images/blank.gif&quot;);" title=""></span>
	</div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 600;"></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 500;">
	<label class="BMapLabel" unselectable="on" style="position: absolute; display: none; cursor: inherit; border: 1px solid rgb(190, 190, 190); padding: 1px; white-space: nowrap; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: arial, sans-serif; z-index: -20000; color: rgb(190, 190, 190); background-color: rgb(190, 190, 190);">shadow</label>
	<label class="BMapLabel" unselectable="on" style="position: absolute; cursor: inherit; border: 1px solid rgb(140, 140, 140); padding: 1px; white-space: nowrap; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 12px; line-height: normal; font-family: arial, sans-serif; z-index: -7983388; -webkit-user-select: none; color: rgb(77, 77, 77); left: 177px; top: 122px; display: none; background-color: rgb(255, 255, 225);">点击可查看详情</label>
	</div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 400;">
	<span class="BMap_Marker" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; width: 0px; height: 0px; left: 69px; top: 113px; z-index: -7983162;">
	<div style="position: absolute; margin: 0px; padding: 0px; width: 200px; height: 100px; overflow: hidden;">
	<img src="${ctxStatic }/modules/rysh/front/images/logo.png" style="display: block; border:none;margin-left:0px; margin-top:0px; "></div>
	</span>
	<span class="BMap_Marker" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; width: 0px; height: 0px; left: 139326px; top: 296658px; z-index: -6245328;">
	<div style="position: absolute; margin: 0px; padding: 0px; width: 200px; height: 100px; overflow: hidden;">
	<img src="${ctxStatic }/modules/rysh/front/images/logo.png" style="display: block; border:none;margin-left:0px; margin-top:0px; "></div>
	</span>
	<span class="BMap_Marker" unselectable="on" style="position: absolute; padding: 0px; margin: 0px; border: 0px; width: 0px; height: 0px; left: 329px; top: 108px; z-index: 19000000; display: none;">
	<div style="position: absolute; margin: 0px; padding: 0px; width: 18px; height: 18px; overflow: hidden;">
	<img src="http://api0.map.bdimg.com/images/spotmkrs.png" style="display: block; border:none;margin-left:-178px; margin-top:-298px; "></div>
	</span></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 300;"></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 201;"></div>
	<div style="position: absolute; height: 0px; width: 0px; left: 0px; top: 0px; z-index: 200;"></div>
	</div>
	<div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 1;">
	<div style="position: absolute; overflow: visible; z-index: -100; left: 188px; top: 188px; display: block; transform: translate3d(0px, 0px, 0px);">
	<img src="http://online4.map.bdimg.com/tile/?qt=tile&amp;x=12661&amp;y=4713&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: -427px; top: -298px; max-width: none; opacity: 1;">
	<img src="http://online4.map.bdimg.com/tile/?qt=tile&amp;x=12662&amp;y=4712&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: -171px; top: -42px; max-width: none; opacity: 1;">
	<img src="http://online0.map.bdimg.com/tile/?qt=tile&amp;x=12663&amp;y=4712&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: 85px; top: -42px; max-width: none; opacity: 1;">
	<img src="http://online0.map.bdimg.com/tile/?qt=tile&amp;x=12662&amp;y=4713&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: -171px; top: -298px; max-width: none; opacity: 1;">
	<img src="http://online1.map.bdimg.com/tile/?qt=tile&amp;x=12663&amp;y=4713&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: 85px; top: -298px; max-width: none; opacity: 1;">
	<img src="http://online3.map.bdimg.com/tile/?qt=tile&amp;x=12661&amp;y=4712&amp;z=16&amp;styles=pl&amp;scaler=1&amp;udt=20161216" style="position: absolute; border: none; width: 256px; height: 256px; left: -427px; top: -42px; max-width: none; opacity: 1;">
	</div></div>
	<div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 2; display: none;">
	<div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 0; display: none;">
	</div></div>
	<div style="position: absolute; overflow: visible; top: 0px; left: 0px; z-index: 3;"></div>
	</div>
	<div class="pano_close" title="退出全景" style="z-index: 1201; display: none;"></div>
	<a class="pano_pc_indoor_exit" title="退出室内景" style="z-index: 1201; display: none;">
	<span style="float:right;margin-right:12px;">出口</span>
	</a>
	<div class=" anchorBL" style="height: 32px; position: absolute; z-index: 30; bottom: 0px; right: auto; top: auto; left: 1px;">
	<a title="到百度地图查看此区域" target="_blank" href="http://map.baidu.com/?sr=1" style="outline: none;">
	<img style="border:none;width:77px;height:32px" src="http://api0.map.bdimg.com/images/copyright_logo.png">
	</a></div>
	<div id="zoomer" style="position:absolute;z-index:0;top:0px;left:0px;overflow:hidden;visibility:hidden;cursor:url(http://api0.map.bdimg.com/images/openhand.cur) 8 8,default">
	<div class="BMap_zoomer" style="top:0;left:0;"></div>
	<div class="BMap_zoomer" style="top:0;right:0;"></div>
	<div class="BMap_zoomer" style="bottom:0;left:0;"></div>
	<div class="BMap_zoomer" style="bottom:0;right:0;"></div>
	</div>
	<div unselectable="on" class=" BMap_cpyCtrl BMap_noprint anchorBL" style="cursor: default; white-space: nowrap; color: black; font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 11px; line-height: 15px; font-family: arial, sans-serif; bottom: 2px; right: auto; top: auto; left: 81px; position: absolute; z-index: 10; background: none;">
	<span _cid="1" style="display: inline;">
	<span style="font-size:11px">© 2016 Baidu&nbsp;- Data © 
	<a target="_blank" href="http://www.navinfo.com/" style="display:inline;">NavInfo</a> &amp; 
	<a target="_blank" href="http://www.cennavi.com.cn/" style="display:inline;">CenNavi</a> &amp; 
	<a target="_blank" href="http://www.365ditu.com/" style="display:inline;">道道通</a>
	</span></span></div></div>
	
	<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&amp;ak=lN12rZ2zs1We2NQvy4q1USRcXEMyVriV"></script><script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&amp;ak=lN12rZ2zs1We2NQvy4q1USRcXEMyVriV&amp;services=&amp;t=20161219171637"></script>
	<script type="text/javascript" src="https://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>

<script type="text/javascript">
	// 百度地图API功能
    var map = new BMap.Map('allmap');
    var poi = new BMap.Point(116.477581,39.915843);
    map.centerAndZoom(poi, 20);
    var contents = '<div style="margin:0;line-height:20px;padding:2px;">' +
                    '地址：北京市朝阳区建国路93号院万达广场8号楼1503 <br/>电话：010-58205391<br/> ' +
                  '</div>';		


    //创建检索信息窗口对象
    var searchInfoWindow = null;
	searchInfoWindow = new BMapLib.SearchInfoWindow(map, contents, {
			title  : "荣耀世华",      //标题
			width  : 240,             //宽度
			height : 80,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
		});
	var pos = new BMap.Point(116.477597,39.916099);
	var myIcon = new BMap.Icon("${ctxStatic }/modules/rysh/front/images/logo.png", new BMap.Size(200,100));
	var marker = new BMap.Marker(pos,{icon:myIcon});  // 创建标注
	map.addOverlay(marker);
/* 
	//样式2
    var contentshanghai = '<div style="margin:0;line-height:20px;padding:2px;">' +
                    '地址：上海黄浦区湖滨路168号企业天地商业中心3号楼31层 <br/>' +
                  '</div>';	
	var searchInfoWindow2 = new BMapLib.SearchInfoWindow(map, contentshanghai, {
			title  : "普思投资",      //标题
			width  : 200,             //宽度
			height : 80,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
	});
	function openInfoWindow2() {
		searchInfoWindow2.open(new BMap.Point(121.482582,31.226643));
	}
	var poss = new BMap.Point(121.482582,31.226643);
	var myIcons = new BMap.Icon("/Public/Home/img/map.png", new BMap.Size(200,100));
	var markers = new BMap.Marker(poss,{icon:myIcons});  // 创建标注
	map.addOverlay(markers); */	

	//样式3
    var contentbeijing = '<div style="margin:0;line-height:20px;padding:2px;">' +
                    '地址：北京市朝阳区建国路93号院万达广场8号楼1503  <br/>' +
                  '</div>';	
	var searchInfoWindow3 = new BMapLib.SearchInfoWindow(map, contentbeijing, {
			title  : "荣耀世华",      //标题
			width  : 200,             //宽度
			height : 80,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
	});
	function openInfoWindow3() {
		searchInfoWindow3.open(new BMap.Point(116.477581,39.915843));
	}
	
	
	marker.disableDragging();  
    marker.addEventListener("click", function(e){
	    searchInfoWindow.open(marker);
    })
    map.addOverlay(marker); //在地图中添加marker
	
</script>
	</div> 
	</div>
	<!--main end-->
</body>
</html>
