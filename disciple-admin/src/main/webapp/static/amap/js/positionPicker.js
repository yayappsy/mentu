$(function() {
	// 拖拽选址
	var map = new AMap.Map('container', {
		zoom : 16,
		scrollWheel : false
	})
	AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale', 'AMap.OverView' ], function() {
		map.addControl(new AMap.ToolBar());
	});
	AMapUI.loadUI([ 'misc/PositionPicker' ], function(PositionPicker) {
		var positionPicker = new PositionPicker({
			mode : 'dragMarker',
			map : map
		});

		positionPicker.on('success', onSuccess);

		positionPicker.on('fail', onFail);

		var startPosition = [ parseFloat($("#longitude").val()), parseFloat($("#latitude").val()) ];
		console.log($("#longitude").val());
		if (startPosition[0] != "" && startPosition[1] != null) {
			positionPicker.start(startPosition);
		} else {
			positionPicker.start();
		}

	});
	// 需要自定义 onSuccess onFail
});