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
		top.$.jBox.warning(mmessage("admin.dialog.deleteConfir"), message("admin.dialog.systemPrompt"), function(v, h, f) {
			if (v == "yes") {
				alert($deleteButton.data("url"));
				$.ajax({
					url: $deleteButton.data("url"),
					type: "POST",
					data: $checkedIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$pageTotal.text(parseInt($pageTotal.text()) - $checkedIds.size());
							$checkedIds.closest("tr").remove();
							if ($listTable.find("tr").size() <= 1) {
								setTimeout(function() {
									location.reload(true);
								}, 3000);
							}
						}
						$deleteButton.addClass("disabled");
						$selectAll.prop("checked", false);
						$checkedIds.prop("checked", false);
					}
				});
			}
		}, {
			buttonsFocus : 1
		});
		
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