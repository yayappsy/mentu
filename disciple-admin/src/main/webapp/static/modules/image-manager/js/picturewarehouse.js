/**
 * Created by admin on 2017/4/17.
 */
$(".op_leixing").on("click","li",function () {
    $(".op_leixing li").removeClass("tj_active");
    $(this).addClass("tj_active");
});
$(".op_riqi").on("click","li",function () {
    $(".op_riqi li").removeClass("tj_active");
    $(this).addClass("tj_active");
    if($(this).index()=="4"){
        $("#pe_time").show();
    }
});
$(".op_shuiying").on("click","li",function () {
    $(".op_shuiying li").removeClass("tj_active");
    $(this).addClass("tj_active");
});
$(".op_wenjian").on("click","li",function () {
    $(".op_wenjian li").removeClass("tj_active");
    $(this).addClass("tj_active");
    if($(this).index()=="3"){
        $("#wenjianxuanze").show();
    }
});
var iconnavtopiconkey = true;
$(".iconnavtopicon").click(function () {
    $(this).html("");
    if(iconnavtopiconkey==true){
        $(".navbtmbox").show();
        $(this).append('<span class="iconfont icon-sanjiao1"></span>');
        iconnavtopiconkey = false;
    }else{
        $(".navbtmbox").hide();
        $(this).append('<span class="iconfont icon-sanjiao"></span>');
        iconnavtopiconkey = true;
    }
});
var iconshaixuan = true;
$(".pw_shaixuanbtn").click(function () {
    $(".iconshaixuan").html("");
    if(iconshaixuan==true){
        $(".navtopbox").hide();
        $(".iconshaixuan").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        iconshaixuan = false;
    }else{
        $(".navtopbox").show();
        $(".iconshaixuan").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        iconshaixuan = true;
    }
});
//树形菜单
$(document).ready(function(){
    var curMenu = null, zTree_Menu = null;
    var setting = {
        view: {
            showLine: false,
            showIcon: false,
            selectedMulti: false,
            dblClickExpand: false,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick
        }
    };

    var zNodes =[
        { id:1,  pId:0,aId:0,name:"产品内容维护图片"},
        { id:1,  pId:0,aId:0,name:"产品品牌维护图片"},
        { id:1,  pId:0,aId:0,name:"产品规格维护图片"},
        { id:1,  pId:0,aId:0,name:"图册管理图片"},
        { id:1,  pId:0,aId:0,name:"幻灯管理图片"},
        { id:1,  pId:0,aId:0,name:"友情链接图片"},
        { id:1,  pId:0,aId:0,name:"资讯内容图片"},
        { id:1,  pId:0,aId:0,name:"广告管理图片"},
        { id:1,  pId:0,aId:0,name:"招聘管理图片"},
        { id:1,  pId:0,aId:0,name:"内容管理图片"},
        { id:1,  pId:0,aId:0,name:"其它图片"},
        { id:1,  pId:0,aId:0,name:"111"},
        { id:11, pId:1,aId:1,name:"222"},
        { id:11, pId:1,aId:1,name:"33333"}

    ];
    function addDiyDom(treeId, treeNode) {
        var spaceWidth = 5;
        var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
            switchObj.before(spaceStr);
        }
    }

    function beforeClick(treeId, treeNode) {
        if(treeNode.aurl!=undefined){
            console.log(treeNode.aurl);
        }
        if (treeNode.level == 0 ) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.expandNode(treeNode);
            return false;
        }
        return true;
    }

    var treeObj = $("#treeDemo");
    $.fn.zTree.init(treeObj, setting, zNodes);
    zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
    //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
    zTree_Menu.selectNode(curMenu);
});
//树形菜单
$(document).ready(function(){
    var curMenu = null, zTree_Menu = null;
    var setting = {
        view: {
            showLine: false,
            showIcon: false,
            selectedMulti: false,
            dblClickExpand: false,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick
        }
    };

    var zNodes =[
        { id:1,  pId:0,aId:0,name:"产品内容维护图片"},
        { id:1,  pId:0,aId:0,name:"产品品牌维护图片"},
        { id:1,  pId:0,aId:0,name:"产品规格维护图片"},
        { id:1,  pId:0,aId:0,name:"图册管理图片"},
        { id:1,  pId:0,aId:0,name:"幻灯管理图片"},
        { id:1,  pId:0,aId:0,name:"友情链接图片"},
        { id:1,  pId:0,aId:0,name:"资讯内容图片"},
        { id:1,  pId:0,aId:0,name:"广告管理图片"},
        { id:1,  pId:0,aId:0,name:"招聘管理图片"},
        { id:1,  pId:0,aId:0,name:"内容管理图片"},
        { id:1,  pId:0,aId:0,name:"其它图片"},
        { id:1,  pId:0,aId:0,name:"111"},
        { id:11, pId:1,aId:1,name:"222"},
        { id:11, pId:1,aId:1,name:"33333"}
    ];
    function addDiyDom(treeId, treeNode) {
        var spaceWidth = 5;
        var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
            switchObj.before(spaceStr);
        }
    }

    function beforeClick(treeId, treeNode) {
        if(treeNode.aurl!=undefined){
            console.log(treeNode.aurl);
        }
        if (treeNode.level == 0 ) {
            var zTree = $.fn.zTree.getZTreeObj("treepropo");
            zTree.expandNode(treeNode);
            return false;
        }
        return true;
    }

    var treeObj = $("#treepropo");
    $.fn.zTree.init(treeObj, setting, zNodes);
    zTree_Menu = $.fn.zTree.getZTreeObj("treepropo");
    //curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
    zTree_Menu.selectNode(curMenu);
});
//图片上传
jQuery(function() {
    var $ = jQuery,
        state = 'pending',
        uploader;

    uploader = WebUploader.create({
        // 不压缩image
        resize: true,
        // 文件接收服务端。
        server: 'http://114.215.18.56/api/fileupload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker1'
    });
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        uploader.upload();
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css( 'width', percentage * 100 + '%' );
    });
    uploader.on( 'uploadSuccess', function( file,ret ) {
        //console.log(file);
        console.log(ret.data.url);
        $( '#'+file.id ).find('p.state').text('已上传');
    });
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
});
//弹窗逻辑
//分类管理
$(".chufa").click(function () {
    $(".propobox").fadeIn();
    var maghei1 = ($(window).innerHeight() - 400) / 2;
    $(".proposmall").show().css("margin-top", maghei1 + "px");
});
$(".close_fenlei").click(function () {
    $(".propobox").fadeOut();
    $(".proposmall").hide();
});
//添加分类
$(".chufaaddfenlei").click(function () {
    var top = ($(window).innerHeight() - 400) / 2;
    var left = ($(window).innerWidth() - 550) / 2;
    $(".proposaddlei").fadeIn().css({
        "left":left,
        "top":top
    });
});
$(".close_addfenlei").click(function () {
    $(".proposaddlei").fadeOut();
});
$(".startdrag").click(function () {
    $(this).hide(0);
    $(".pol_tuozhuai").fadeIn(500);
    showmessage("您可以手动拖拽内容进行排序了");
});
$(".pol_quxiao").click(function () {
    $(".pol_tuozhuai").hide(0);
    $(".startdrag").fadeIn(500);
    showmessage("关闭拖拽排序");
});
//水印设置
$(".op_shuiyin").click(function () {
    $(".propobox").fadeIn();
    var maghei1 = ($(window).innerHeight() - 400) / 2;
    $(".proposshuiyin").show().css("margin-top", maghei1 + "px");
});
$(".close_shuiyin").click(function () {
    $(".propobox").fadeOut();
    $(".proposshuiyin").hide();
});
//图片显示与隐藏
$(".op_tupianbujuul li").mouseenter(function () {
    $(".op_tupianbujucheck").hide();
    $(this).find(".op_tupianbujucheck").show();
});
$(".op_tupianbujucheck").mouseleave(function () {
    $(this).find(".xialachufabox").hide();
});
$(".xialachufa").click(function () {
    $(this).nextAll(".xialachufabox").show();
});
$(".op_articleleft").hover(function () {
    $(".op_tupianbujucheck").hide();
});
//图片删除
$(".delate_chufa").click(function () {
    $(this).parent().nextAll(".delate_photo").show();
});
$(".delate_phototextbtnquxiao").click(function () {
    $(".delate_photo").hide();
});
//图片浏览弹窗
$(".photo_liulanchufa").click(function () {
    $(".propobox").fadeIn();
    var img = $(this).parents("li").find(".op_tuimg");
    var obg = {
        "width":img[0].naturalWidth,
        "height":img[0].naturalHeight+36,
        "top":($(window).height() - img[0].naturalHeight - 36) / 2,
        "left":($(window).width() - img[0].naturalWidth) / 2
    };
    $(".propophoto_liuimgbox").html("").append('<img src="'+img.attr("src")+'" alt="">');
    $(".propophoto_liulan").show().css(obg);
});
$(".close_liulan").click(function () {
    $(".propobox").fadeOut();
    $(".propophoto_liulan").hide();
});
//修改弹窗
$(".photo_xiugaichufa").click(function () {
    var img = $(this).parents("li").find(".op_tuimg").attr("src");
    $(".bianji_img").attr("src",img);
    $(".propobox").fadeIn();
    var maghei1 = ($(window).innerHeight() - 400) / 2;
    $(".propo_bianji").show().css("margin-top", maghei1 + "px")
});
$(".close_xiugai").click(function () {
    $(".propobox").fadeOut();
    $(".propo_bianji").hide();
});
//图片上传
jQuery(function() {
    var $ = jQuery,
        state = 'pending',
        uploader;
    uploader = WebUploader.create({
        // 不压缩image
        resize: true,
        // 文件接收服务端。
        server: 'http://114.215.18.56/api/fileupload',
        // 选择文件的按钮。可选。

        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker2'
    });
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        uploader.upload();
        console.log(6);
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        console.log(7);
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css( 'width', percentage * 100 + '%' );
    });
    uploader.on( 'uploadSuccess', function( file,ret ) {
        //console.log(file);
        console.log(8);
        console.log(ret.data.url);
        $( '#'+file.id ).find('p.state').text('已上传');
    });
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
});