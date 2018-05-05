/*
 * Created by jhf on 2017/6/1 14:10
 */
$(".nav-pills").on("click","li",function () {
    $(this).parents(".nav-pills").find("li").removeClass("active");
    $(this).addClass("active");
});
$(".nav_tixing").on("click","li",function () {
    $(".navtixing_box").hide().eq($(this).index()).show();
});

$(".peizhi_btn").on("click",function () {
    parent.layer.open({
        type: 2,
        title: '配置企业邮箱',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['600px' , '400px'],
        content: ['peizhi.html','no'],
        btn:['确认','取消'],
        btnAlign: 'c' //按钮居中
    });
});
$(".youjiantixing").on("click",function () {
    parent.layer.open({
        type: 2,
        title: '编辑内容提醒模板',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['700px' , '600px'],
        content: ['neirongtixingyoujian.html','no'],
        btn:['确认','预览样例','邮件测试','恢复默认','取消'],
        btnAlign: 'c' //按钮居中
    });
});
$(".duanxinmoban").on("click",function () {
    parent.layer.open({
        type: 2,
        title: '编辑内容提醒模板',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['700px' , '600px'],
        content: ['neirongtixingduanxin.html','no'],
        btn:['确认','预览样例','邮件测试','恢复默认','取消'],
        btnAlign: 'c' //按钮居中
    });
});
$(".youjianyulan").click(function () {
    window.open("yulanyoujian.html")
})