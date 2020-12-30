
// 展开按钮
$(".flag_ctrl").click(function(){
	if($("#flag_more").is(':hidden')){
		// 展开，并更改提示
		$("#flag_more").show();
		$("#crl-tip").empty();
		$("#crl-tip").append("收起");
	}else{
		// 折叠
		$("#flag_more").hide();
		$("#crl-tip").empty();
		$("#crl-tip").append("展开");
	}
});