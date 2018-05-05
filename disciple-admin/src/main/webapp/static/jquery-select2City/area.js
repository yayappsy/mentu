$(function() {
	$('#city_1').defaultPluginName('110000');
	$('#btnval').click(
			function() {
				alert($('#loc_province').val() + ' - ' + $('#loc_city').val()
						+ ' - ' + $('#loc_town').val())
			});
	$('#btntext').click(
			function() {
				alert($('#loc_province').select2('data').text + ' - '
						+ $('#loc_city').select2('data').text + ' - '
						+ $('#loc_town').select2('data').text)
			});
});