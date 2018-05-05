$(function() {
	$("#inputForm").on("click", ".contentType", function() {
		showAdContent();
	});
	function showAdContent() {
		$selObj = $("#inputForm").find(".contentType:checked");
		if ($selObj.val() == "image") {
			$(".imageAd").show();
			$(".textAd").hide();
		} else {
			$(".imageAd").hide();
			$(".textAd").show();
		}
	}
	showAdContent();
})