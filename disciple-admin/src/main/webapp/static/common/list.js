/*
* 
 * 
 * 
 * 
 * JavaScript - List
 * 
 */

$().ready( function() {
	
	var $deleteButton = $("#btnDelete");
	var $clearButton = $("#btnClear");
	var $listTable = $("#contentTable");
	var $selectAll = $("#selectAll");
	var $ids = $("#contentTable input[name='ids']");
	var $contentRow = $("#contentTable tr:gt(0)");
	// 删除
	$deleteButton.click( function() {
		var $this = $(this);
		if ($this.hasClass("disabled")) {
			return false;
		}
		var $checkedIds = $("#contentTable input[name='ids']:enabled:checked");
		var ids = [];       	
		   if($('input[name="ids"]').is(':checked')){ 
			   $("input[name='ids']:checked").each(function() {
					ids.push($(this).val());

				});				    
			   swal({ 
				    title: "批量删除", 
				    text: "您确定都需要删除了吗？", 
				    type: "warning", 
				    showCancelButton: true, 
				    closeOnConfirm: false, 
				    confirmButtonText: "是的，需要删除", 
				    confirmButtonColor: "#ec6c62" 
				}, function() { 
					$.ajax( {
						url : $deleteButton.data("url"),
						type : 'post',
						data : {
							"ids" : ids.join(",")	
						},
						success : function(data) {
							swal("Good!", "弹出了一个操作成功的提示框", "success");
							history.go(0);					
						},
						}); 
				});
		    }else{		    	
		    	swal("请先选择您要删除的项目!");     
					return false        
			    }   
		
	});
	
	// 全选
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $listTable.find("input[name='ids']:enabled");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$deleteButton.removeClass("disabled");
				$contentRow.addClass("selected");
			} else {
				$deleteButton.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$deleteButton.addClass("disabled");
			$contentRow.removeClass("selected");
		}
	});
	
	// 选择
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			$deleteButton.removeClass("disabled");
		} else {
			$this.closest("tr").removeClass("selected");
			if ( $listTable.find("input[name='ids']:enabled:checked").size() > 0) {
				$deleteButton.removeClass("disabled");
			} else {
				$deleteButton.addClass("disabled");
			}
		}
	});
	//清除查询条件
	$clearButton.click(function() {
		top.$.jBox.confirm(
				message("admin.dialog.clearConfirm"),
				message("admin.dialog.systemPrompt"),
				function(v, h, f) {
					if (v == "ok") {
						//清除text
						$clearButton.parents(".ul-form").find("li :text").each(function(){
							$(this).val("");
						});
						//清除select
						$clearButton.parents(".ul-form").find("li select").each(function(){
							 $(this).select2().val(null).trigger("change");
						});
					};
				}, {
					buttonsFocus : 1,
				});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
	});	

});