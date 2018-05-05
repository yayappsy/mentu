$(document).ready(function() {

    $('#btnMemberSelectRemove').on("click",function() {
        $("#memberId").val("");
        $("#memberName").val("");
    });
    $('#btnMemberSelect').on("click",function() {
        layer.open({
            type: 2,
            shadeClose: true,
            shade: false,
            maxmin: true,
            //开启最大化最小化按钮
            area: ['800px', '600px'],
            content: ctx+'/member/member/list?searchType=selectLink',
            btn: ['确定', '关闭'],
            yes: function(index, layero) {
                var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
                var selectData = [];
                $layerIframe.contents().find("input[name='id']:checked").each(function() {
                    selectData.push($(this).data());
                });
                if (selectData.length > 0) {
                    $("#memberId").val(selectData[0].id);
                    $("#memberName").val(selectData[0].name);
                }
                layer.close(index);

            },
            cancel: function() {
                //右上角关闭回调
            }
        });

    });
    $('#btnTeacherSelectRemove').on("click",function() {
        $("#teacherId").val("");
        $("#teacherName").val("");
    });
    $('#btnTeacherSelect').on("click",function() {
    	var gameId=$("#gameId").val();
   	    if( $("#gameId").val() == '' ){
				alertx("必须先选择游戏");
				return false;
		}		
        layer.open({
            type: 2,
            shadeClose: true,
            shade: false,
            maxmin: true,
            //开启最大化最小化按钮
            area: ['800px', '600px'],
            content: ctx+'/member/teacher/list?searchType=selectLink&&game.id'+gameId,
            btn: ['确定', '关闭'],
            yes: function(index, layero) {
                var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
                var selectData = [];
                $layerIframe.contents().find("input[name='id']:checked").each(function() {
                    selectData.push($(this).data());
                });
                if (selectData.length > 0) {
                    $("#teacherId").val(selectData[0].id);
                    $("#teacherName").val(selectData[0].nickname);
                }
                layer.close(index);

            },
            cancel: function() {
                //右上角关闭回调
            }
        });

    });
    $('#btnTeachersSelect').on("click",function() { 
        layer.open({
            type: 2,
            shadeClose: true,
            shade: false,
            maxmin: true,
            //开启最大化最小化按钮
            area: ['800px', '600px'],
            content: ctx+'/member/teacher/list?searchType=selectLink',
            btn: ['确定', '关闭'],
            yes: function(index, layero) {
                var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
                var selectData = [];
                $layerIframe.contents().find("input[name='id']:checked").each(function() {
                    selectData.push($(this).data());
                });
                if (selectData.length > 0) {
                    $("#teacherId").val(selectData[0].id);
                    $("#teacherName").val(selectData[0].nickname);
                }
                layer.close(index);

            },
            cancel: function() {
                //右上角关闭回调
            }
        });

    });
});