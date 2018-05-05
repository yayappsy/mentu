/**
 * Created by admin on 2017/4/14.
 */
$(".op_sectionright").css("width",$(".section").width()-181+"px");
$(window).resize(function(){
    $(".op_sectionright").css("width",$(".section").width()-181+"px");
});
$(".hy_li2").click(function () {
    $(".hy_li2").removeClass("hy_liactive");
    $(this).addClass("hy_liactive");
    console.log($(this).index());
    var $index = $(this).index()-1;
    $(".hy_navbox").removeClass("hy_disactive").eq($index).addClass("hy_disactive");
});

$(function () {
    var time = document.getElementById("hy_phonetime");
    timeget();
    setInterval(function () {
        timeget()
    },1000);
    function timeget(){
        var date = new Date();
        var a = date.getDate();
        var b = date.getFullYear();
        var c = date.getHours();
        var d = date.getMonth()+1;
        var e = date.getMinutes();
        var f = date.getDay();
        var g = date.getSeconds();
        time.innerHTML = b+"/"+d+"/"+"<span style='color:red;'>"+a+"</span><span style='margin-left: 50px'></span>"+c+":"+e+":"+g+"";
    }
});
function textlength() {
    var $hy_phonetexta = $("#hy_phonetexta").val();
    $(".hy_phonetextaboxbtmleft").html($hy_phonetexta.split("").length)
}
textlength();
document.getElementById("hy_phonetexta").oninput = function () {
    textlength();
};
$(".add_temp_list2").on("click","li",function () {
    console.log($(this).attr("data-href"));
    console.log($(this).index());
    $("#hy_phonetexta").append($(this).attr("data-href"));
    textlength();
});


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
        { id:1,  pId:0,aId:0,name:"对客户提醒", open:true},
        { id:11, pId:1,aId:1,aurl:"huiyuanzhuce",name:"会员注册"},
        { id:11, pId:1,aId:1,name:"会员审核通过"},
        { id:11, pId:1,aId:1,name:"修改密码"},
        { id:11, pId:1,aId:1,name:"找回密码"},
        { id:11, pId:1,aId:1,name:"到货提醒"},
        { id:11, pId:1,aId:1,name:"管理员回复留言"},
        { id:11, pId:1,aId:1,name:"提交简历"},
        { id:11, pId:1,aId:1,name:"订单提交(对会员)"},
        { id:11, pId:1,aId:1,name:"订单已发货(对会员)"},
        { id:11, pId:1,aId:1,name:"订单被管理员审核为无效"},
        { id:11, pId:1,aId:1,name:"订单提交(对收货人)"},
        { id:11, pId:1,aId:1,name:"订单已发货(对收货人)"},
        { id:2,  pId:0,aId:0,name:"对管理员提醒",open:true},
        { id:21, pId:2,aId:1,name:"会员注册"},
        { id:21, pId:2,aId:1,name:"提交留言"},
        { id:21, pId:2,aId:1,name:"提交简历"},
        { id:21, pId:2,aId:1,name:"提交订单"},
        { id:21, pId:2,aId:1,name:"询价提醒"}

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