$(document).ready(function() {

    $('#btnProductSelectRemove').on("click",function() {
        $("#productId").val("");
        $("#productName").val("");
    });
    $('#btnProductSelect').on("click",function() {
        layer.open({
            type: 2,
            shadeClose: true,
            shade: false,
            maxmin: true,
            //开启最大化最小化按钮
            area: ['800px', '600px'],
            content: ctx+'/product/product/list?searchType=selectLink',
            btn: ['确定', '关闭'],
            yes: function(index, layero) {
                var $layerIframe = $(layero).find("#layui-layer-iframe" + index);
                var selectData = [];
                $layerIframe.contents().find("input[name='id']:checked").each(function() {
                    selectData.push($(this).data());
                });
                if (selectData.length > 0) {
                    $("#productId").val(selectData[0].id);
                    $("#productName").val(selectData[0].name);                       
                }
                layer.close(index);

            },
            cancel: function() {
                //右上角关闭回调
            }
        });

    });
  
});