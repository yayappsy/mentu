/**
 * Created by admin on 2017/4/10.
 */
/**
 * Created by admin on 2017/4/10.
 */
$(document).ready(function() {
    $('#pe_time').daterangepicker({
        timePicker: true,//是否开启小时选择
        timePickerIncrement: 10,//分钟选择精确度
        format: 'YYYY/DD/MM/h/mm'//时间格式
    }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
    });
});
$(".tj_ultap").on("click","li",function () {
    $(".tj_ultap li").removeClass("active");
    $(this).addClass("active");
});
var gpkey1 = true;
$(".gp_btn5").click(function () {
    $(".ta_span4").html("");
    if(gpkey1 == true){
        $(".ta_div1").hide();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        $(".ta_span5").html("展开筛选");
        gpkey1 = false;
    }else{
        $(".ta_div1").fadeIn();
        $(".ta_span4").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".ta_span5").html("收起筛选");
        gpkey1 = true;
    }
});
var chartline = true;
$(".chartline").click(function () {
    $(".spanchart").html("");
    if(chartline == true){
        $(".se_chartbox").hide();
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-down"></span>');
        chartline = false;
    }else{
        $(".spanchart").append('<span class="glyphicon glyphicon-chevron-up"></span>');
        $(".se_chartbox").fadeIn();
        chartline = true;
    }
});