/**
 * Created by admin on 2017/4/13.
 */
$(".pv_ul1").on("click","li",function () {
    $(".pv_ul1 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

$(".pv_ul2").on("click","li",function () {
    $(".pv_ul2 li").removeClass("tj_active");
    $(this).addClass("tj_active");
});

var pvkey1 = true;
$(".pv_btn5").click(function () {
    $(".ta_span4").html("");
    if(pvkey1 == true){
        $(".ta_div1").hide();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        $(".ta_span5").html("展开筛选");
        pvkey1 = false;
    }else{
        $(".ta_div1").fadeIn();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".ta_span5").html("收起筛选");
        pvkey1 = true;
    }
});

//黄色提示
$(".pv_icon").click(function () {
    $(".page-tip").hide();
});

$(".tj_ultap").on("click","li",function () {
    $(".tj_ultap li").removeClass("active");
    $(this).addClass("active");
});
