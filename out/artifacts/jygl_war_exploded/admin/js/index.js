function pageClick(k) {
	$(k).parent().find("div").removeClass("active");
	$(k).addClass("active");
	$("#flTitle").text($(k).text());
	var url=$(k).attr("data-url");
	var iframe=$("#iframe");
	iframe.attr("src",url);
	//iframe.height=$(window).height-100;

	//$("#iframe").attr("src",url);
}
