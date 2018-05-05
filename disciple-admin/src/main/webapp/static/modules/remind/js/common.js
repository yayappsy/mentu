/**
 * Created by admin on 2017/4/14.
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
var messagekey = true;
function showmessage(data) {
    if(messagekey!=true){
        return;
    }
    messagekey = false;
    var $message = document.createElement("span");
    $message.className = "showmessageclass";
    $message.innerHTML = "";
    $message.innerHTML = '<span class="iconfont icon-gou fl"></span><span class="datamessage fl">'+data+'</span>';
    $("body").append($message);
    var $showmessageclass = $(".showmessageclass");
    var showtop = ($(window).height() - $showmessageclass.height()) / 2;
    var showleft = ($(window).width() - $showmessageclass.width()) / 2 - 40;
    var obj = {
        "left":showleft,
        "top":showtop
    };
    $(".datamessage").css({
        "font-size":"22px",
        "line-height":"25px"
    });
    $showmessageclass.css(obj);
    $showmessageclass.fadeIn(800);
    setTimeout(function () {
        $showmessageclass.fadeOut(600);
    },2000);
    setTimeout(function () {
        $("span").remove(".showmessageclass");
        messagekey = true;
    },2600);

}
